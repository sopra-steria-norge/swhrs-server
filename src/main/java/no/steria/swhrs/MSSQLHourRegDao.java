package no.steria.swhrs;

import org.joda.time.DateTime;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSSQLHourRegDao implements HourRegDao {

    private static final String COUNTRY = "NO";
    private final DataSource datasource;
	private static final String SELECT_USERS = "SELECT No_, \"WEB Password\" FROM \"Norge$Resource\" where No_ = ?";
	private static final String SELECT_REGISTRATIONS = "SELECT * FROM \"Norge$Time Entry\" WHERE Ressourcekode = ? AND Dato = ? AND Projektnr_ NOT LIKE 'FLEX'";
	private static final String SELECT_FAVOURITES = "SELECT \"Norge$Favourite Task\".Projektnr_, \"Norge$Favourite Task\".Aktivitetskode, \"Norge$Tasklist\".Beskrivelse, \"Norge$Tasklist\".\"Ressource fakturerbar\", \"Norge$Job\".Description, \"Norge$Job\".Name,  \"Norge$Job\".\"Internt projekt\"  FROM \"Norge$Favourite Task\"  INNER JOIN \"Norge$Tasklist\" ON \"Norge$Favourite Task\".Projektnr_= \"Norge$Tasklist\".Projektnr_ AND \"Norge$Favourite Task\".Aktivitetskode = \"Norge$Tasklist\".Kode INNER JOIN \"Norge$Job\" ON \"Norge$Favourite Task\".Projektnr_= \"Norge$Job\".No_ WHERE \"Norge$Favourite Task\".Resourcekode = ? AND \"Norge$Tasklist\".Spærret NOT LIKE '1'";
	private static final String SELECT_WEEKREGISTRATIONS = "SELECT Dato, sum(Antal), Godkendt FROM \"Norge$Time Entry\" where Ressourcekode = ? AND Projektnr_ NOT LIKE 'FLEX' AND Dato Between ? AND ? GROUP BY Dato, Godkendt";
	private static final String SELECT_SEARCHPROJECTS = "SELECT TOP 150 \"Norge$Tasklist\".Projektnr_, \"Norge$Tasklist\".Kode, \"Norge$Tasklist\".Beskrivelse FROM \"Norge$Tasklist\" WHERE Beskrivelse like ? OR Projektnr_ like ? ORDER BY Beskrivelse";
	private static final String SELECT_PERIODS = "SELECT Startdato, Slutdato, Beskrivelse, Bogført FROM \"Norge$Time Periods\" WHERE Ressource = ? AND Startdato <= ? AND Slutdato >= ?";
	private static final String INSERT_FAVOURITE = "INSERT into \"Norge$Favourite Task\" (Resourcekode, Projektnr_, Aktivitetskode) VALUES(?, ?, ?)";
	private static final String DELETE_FAVOURITE = "DELETE FROM \"Norge$Favourite Task\" WHERE Resourcekode = ? AND Projektnr_ = ? AND Aktivitetskode = ?";
    private static final String UPDATE_REGISTRATION = "UPDATE \"Norge$Time Entry\" SET Godkendt=? WHERE Ressourcekode = ? AND Dato BETWEEN ? AND ?";
	private static final String SELECT_NORMTIME = "SELECT \"Norge$Norm Time Data\".Kode, \"Norge$Norm Time Data\".Mandag, \"Norge$Norm Time Data\".Tirsdag, \"Norge$Norm Time Data\".Onsdag, \"Norge$Norm Time Data\".Torsdag, \"Norge$Norm Time Data\".Fredag, \"Norge$Norm Time Data\".Lørdag, \"Norge$Norm Time Data\".Søndag from \"Norge$Norm Time Data\" INNER JOIN \"Norge$Resource\" ON \"Norge$Norm Time Data\".Kode = \"Norge$Resource\".\"Norm Tid\" WHERE \"Norge$Resource\".No_ = ?";
    private static final String INSERT_STORE_PROCEDURE = "{? = call dbo.uspSTE_InsertTimeEntry(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String DELETE_STORE_PROCEDURE = "{call dbo.uspSTE_DeleteTimeEntry(?, ?, ?)}";
    private static final String UPDATE_STORE_PROCEDURE = "{call dbo.uspSTE_UpdateTimeEntry(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

	public MSSQLHourRegDao(DataSource datasource) {
		this.datasource = datasource;
	}

    @Override
	public List<HourRegistration> getAllHoursForDate(String userId, String date) {
		List<HourRegistration> result = new ArrayList<HourRegistration>();

		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(SELECT_REGISTRATIONS);
			statement.setString(1, userId);
			statement.setString(2, date);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				int taskNumber = res.getInt(2);
				String projectNumber = res.getString(3);
				String activityCode = res.getString(4);
				double hours = res.getDouble(8);
				String description = res.getString(9);
				boolean submitted = res.getBoolean(10);
				boolean approved = res.getBoolean(11);

				HourRegistration hourReg = new HourRegistration(date, taskNumber, projectNumber, activityCode, hours, description, submitted, approved);
				result.add(hourReg);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			try {
				if(statement != null)statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

    private Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

    @Override
    public User findUser(String userId, Password password) {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SELECT_USERS);
            statement.setString(1, userId.toUpperCase());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                Password passwordInDb = Password.fromPlaintext(password.getSalt(), res.getString(2));
                if (password.equals(passwordInDb)) {
                    User user = new User();
                    user.setUsername(res.getString(1));
                    user.setPassword(passwordInDb);
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


	@Override
	public void deleteHourRegistration(String userId, String taskNumber) {
        CallableStatement statement = null;
        try {
            statement = getConnection().prepareCall(DELETE_STORE_PROCEDURE);
            statement.setString(1, COUNTRY);
            statement.setString(2, userId);
            statement.setString(3, taskNumber);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            try {
                if(statement != null)statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
	}

    @Override
    public List<UserFavourites> getUserFavourites(String userId) {
        List<UserFavourites> result = new ArrayList<UserFavourites>();
        PreparedStatement statement = null;
        try {
			statement = getConnection().prepareStatement(SELECT_FAVOURITES);
            statement.setString(1, userId);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String projectNumber = res.getString(1);
                String activityCode = res.getString(2);
                String description = res.getString(3);
                int billable = res.getInt(4);
                String projectName = res.getString(5);
                String customer = res.getString(6);
                int internalProject = res.getInt(7);
                UserFavourites userFav = new UserFavourites(projectNumber, activityCode, description, billable, projectName, customer, internalProject);
                result.add(userFav);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public List<Projects> searchProjects(String projectName) {
        List<Projects> result = new ArrayList<Projects>();
        PreparedStatement statement = null;
        String searchString = "%" + projectName + "%";
        try {
            statement = getConnection().prepareStatement(SELECT_SEARCHPROJECTS);
            statement.setString(1, searchString);
            statement.setString(2, projectName);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String projectNumber = res.getString(1);
                String activityCode = res.getString(2);
                String description = res.getString(3);
                Projects project = new Projects(projectNumber, activityCode, description);
                result.add(project);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public List<WeekRegistration> getWeekList(String userId, String dateFrom,
                                              String dateTo) {
        List<WeekRegistration> result = new ArrayList<WeekRegistration>();
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SELECT_WEEKREGISTRATIONS);
            statement.setString(1, userId);
            statement.setString(2, dateFrom);
            statement.setString(3, dateTo);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String date = res.getString(1);
                double hours = res.getDouble(2);
                int approved = res.getInt(3);
                WeekRegistration weekReg = new WeekRegistration(date, hours, approved);
                result.add(weekReg);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public DatePeriod getPeriod(String userId, String date) {
        DatePeriod datePeriod = new DatePeriod();
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SELECT_PERIODS);
            statement.setString(1, userId);
            statement.setString(2, date);
            statement.setString(3, date);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                datePeriod.setFromDate(res.getString(1));
                datePeriod.setToDate(res.getString(2));
                datePeriod.setDescription(res.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return datePeriod;
    }

    @Override
    public boolean addFavourites(String userId, String project_id, String activityCode) {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(INSERT_FAVOURITE);
            statement.setString(1, userId);
            statement.setString(2, project_id);
            statement.setString(3, activityCode);
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
	public Integer addHourRegistrations(String loggedInUser, String registeringForUser, String projectNumber,
                                        String activity, DateTime date, double hours, boolean isChargedHours,
                                        String workType, String description, boolean bypassChecks) {

        CallableStatement statement = null;
        try {
            statement = getConnection().prepareCall(INSERT_STORE_PROCEDURE);
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, COUNTRY);
            statement.setString(3, loggedInUser);
            statement.setString(4, registeringForUser);
            statement.setString(5, projectNumber);
            statement.setString(6, activity);
            statement.setDate(7, new Date(date.getMillis()));
            statement.setDouble(8, hours);
            statement.setBoolean(9, isChargedHours);
            statement.setString(10, workType);
            statement.setString(11, description);
            statement.setBoolean(12, bypassChecks);

            statement.execute();
            return statement.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void deleteFavourite(String userId, String projectNumber, String activityCode) {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(DELETE_FAVOURITE);
            statement.setString(1, userId);
            statement.setString(2, projectNumber);
            statement.setString(3, activityCode);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
	public void updateRegistration(String userId, String taskNumber, String projectNumber, String activity,
                                   DateTime date, double hours, boolean isBillable, String workType,
                                   String description) {
        CallableStatement statement = null;

        try {
            statement = getConnection().prepareCall(UPDATE_STORE_PROCEDURE);
            statement.setString(1, COUNTRY);
            statement.setString(2, userId);
            statement.setString(3, taskNumber);
            statement.setString(4, projectNumber);
            statement.setString(5, activity);
            statement.setDate(6, new Date(date.getMillis()));
            statement.setDouble(7, hours);
            statement.setBoolean(8, isBillable);
            statement.setString(9, workType);
            statement.setString(10, description);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void updatePeriod(String userId, int option, String fromDate, String toDate) {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(UPDATE_REGISTRATION);
            statement.setInt(1, option);
            statement.setString(2, userId);
            statement.setString(3, fromDate);
            statement.setString(4, toDate);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public List<NormTime> getNormTime(String username) {
        List<NormTime> result = new ArrayList<NormTime>();
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SELECT_NORMTIME);
            statement.setString(1, username);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String normcode = res.getString(1);
                int monday = res.getInt(2);
                int tuesday = res.getInt(3);
                int wednesday = res.getInt(4);
                int thursday = res.getInt(5);
                int friday = res.getInt(6);
                int saturday = res.getInt(7);
                int sunday = res.getInt(8);

                NormTime norm = new NormTime(normcode, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
                result.add(norm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static MSSQLHourRegDao createInstance() throws NamingException {
        DataSource dataSource = (DataSource) new InitialContext().lookup(JettyServer.DB_JNDI);
        if (dataSource == null) {
            throw new RuntimeException("Could not find datasource");
        }

        return new MSSQLHourRegDao(dataSource);
    }
}