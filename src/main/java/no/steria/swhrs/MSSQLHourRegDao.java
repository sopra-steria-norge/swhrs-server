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
    private static final String SELECT_CURRENT_PERIOD = "select TP.[Startdato],TP.[Slutdato], TP.[Beskrivelse] FROM [Norge$Time Periods] TP WHERE Ressource=? AND TP.[Startdato] <= ? AND TP.[Slutdato] >= ? ORDER BY [Startdato] DESC";
	private static final String SELECT_USERS = "SELECT No_, \"WEB Password\" FROM \"Norge$Resource\" where No_ = ?";
	private static final String SELECT_REGISTRATIONS = "SELECT * FROM \"Norge$Time Entry\" WHERE Ressourcekode = ? AND Dato = ? AND Projektnr_ NOT LIKE 'FLEX'";
    private static final String SELECT_NORMTIME = "SELECT \"Norge$Norm Time Data\".Kode, \"Norge$Norm Time Data\".Mandag, \"Norge$Norm Time Data\".Tirsdag, \"Norge$Norm Time Data\".Onsdag, \"Norge$Norm Time Data\".Torsdag, \"Norge$Norm Time Data\".Fredag, \"Norge$Norm Time Data\".Lørdag, \"Norge$Norm Time Data\".Søndag from \"Norge$Norm Time Data\" INNER JOIN \"Norge$Resource\" ON \"Norge$Norm Time Data\".Kode = \"Norge$Resource\".\"Norm Tid\" WHERE \"Norge$Resource\".No_ = ?";
	private static final String SELECT_WEEKREGISTRATIONS = "SELECT Dato, sum(Antal), Godkendt FROM \"Norge$Time Entry\" where Ressourcekode = ? AND Projektnr_ NOT LIKE 'FLEX' AND Dato Between ? AND ? GROUP BY Dato, Godkendt";
	private static final String SELECT_SEARCHPROJECTS = "SELECT TOP 150 \"Norge$Tasklist\".Projektnr_, \"Norge$Tasklist\".Kode, \"Norge$Tasklist\".Beskrivelse FROM \"Norge$Tasklist\" WHERE Beskrivelse like ? OR Projektnr_ like ? ORDER BY Beskrivelse";
	private static final String SELECT_PERIODS = "SELECT Startdato, Slutdato, Beskrivelse, Bogført FROM \"Norge$Time Periods\" WHERE Ressource = ? AND Startdato <= ? AND Slutdato >= ?";
	private static final String INSERT_FAVOURITE = "INSERT into \"Norge$Favourite Task\" (Resourcekode, Projektnr_, Aktivitetskode) VALUES(?, ?, ?)";
	private static final String DELETE_FAVOURITE = "DELETE FROM \"Norge$Favourite Task\" WHERE Resourcekode = ? AND Projektnr_ = ? AND Aktivitetskode = ?";
    private static final String SELECT_FAVOURITES = "SELECT \"Norge$Favourite Task\".Projektnr_, \"Norge$Favourite Task\".Aktivitetskode, \"Norge$Tasklist\".Beskrivelse, \"Norge$Tasklist\".\"Ressource fakturerbar\", \"Norge$Job\".Description, \"Norge$Job\".Name,  \"Norge$Job\".\"Internt projekt\"  FROM \"Norge$Favourite Task\"  INNER JOIN \"Norge$Tasklist\" ON \"Norge$Favourite Task\".Projektnr_= \"Norge$Tasklist\".Projektnr_ AND \"Norge$Favourite Task\".Aktivitetskode = \"Norge$Tasklist\".Kode INNER JOIN \"Norge$Job\" ON \"Norge$Favourite Task\".Projektnr_= \"Norge$Job\".No_ WHERE \"Norge$Favourite Task\".Resourcekode = ? AND \"Norge$Tasklist\".Spærret NOT LIKE '1'";
    private static final String STORE_PROCEDURE_INSERT_HOURS = "{? = call dbo.uspSTE_InsertTimeEntry(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String STORE_PROCEDURE_DELETE_HOURS = "{call dbo.uspSTE_DeleteTimeEntry(?, ?, ?)}";
    private static final String STORE_PROCEDURE_UPDATE_HOURS = "{call dbo.uspSTE_UpdateTimeEntry(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String STORE_PROCEDURE_SUBMIT_HOURS = "{call dbo.uspSTE_EMPApprovePeriod(?, ?, ?, ?)}";
    private static final String STORE_PROCEDURE_REOPEN_HOURS = "{call dbo.uspSTE_ReopenPeriod(?, ?, ?, ?)}";
    private static final String STORE_PROCEDURE_GET_PERIOD_INFORMATION = "{? = call dbo.uspSTE_GetPeriodRegTime(?, ?, ?, ?, ?)}";
    public String asdf = "exec sp_executesql N' SELECT  TP.[Startdato] ,TP.[Ressource], TP.[Type], convert(bit, TP.[Bogført]) As Bogført, convert(bit, TP.[Klar til Batch bogføring]) As ReadyForBatchProcessing,TP.[Slutdato], TP.[Beskrivelse], TP.[Detaileniveau], TP.[Åben], TP.[Autojuster Flexsaldo] ,ConstructedDescription='''' ,Ct = (SELECT COUNT(*) FROM [Norge$Time Entry] TE  WHERE TE.Ressourcekode=TP.[Ressource] AND TE.Dato BETWEEN TP.[Startdato] AND TP.[Slutdato]) ,CtApprovedByEMP = (SELECT COUNT(*) FROM [Norge$Time Entry] TE  WHERE TE.Ressourcekode=TP.[Ressource] AND TE.Dato BETWEEN TP.[Startdato] AND TP.[Slutdato] AND Godkendt=1) ,CtApprovedByLMPM = (SELECT COUNT(*) FROM [Norge$Time Entry] TE  WHERE TE.Ressourcekode=TP.[Ressource] AND TE.Dato BETWEEN TP.[Startdato] AND TP.[Slutdato] AND [Approved By LM_PM]=1) ,CtRejected = (SELECT COUNT(*) FROM [Norge$Time Entry] TE  WHERE TE.Ressourcekode=TP.[Ressource] AND TE.Dato BETWEEN TP.[Startdato] AND TP.[Slutdato] AND [Ikke godkjent]=1) ,CtPosted = (SELECT COUNT(*) FROM [Norge$Time Entry] TE  WHERE TE.Ressourcekode=TP.[Ressource] AND TE.Dato BETWEEN TP.[Startdato] AND TP.[Slutdato] AND [Bogført]=1) ,SumChargeable = (SELECT coalesce(SUM(Antal),0) FROM [Norge$Time Entry] TE  WHERE TE.Ressourcekode=TP.[Ressource] AND Fakturerbart = 1  AND TE.Dato BETWEEN TP.[Startdato] AND TP.[Slutdato])  FROM [Norge$Time Periods] TP  WHERE Ressource=@ResourceNo  AND TP.[Startdato] >= @RequestedStartDate AND TP.[Startdato] <= TP.[Slutdato]  ORDER BY [Startdato] DESC ',N'@ResourceNo nvarchar(3),@RequestedStartDate datetime',@ResourceNo=N'ROR',@RequestedStartDate='2012-09-01 00:00:00:000'";

	public MSSQLHourRegDao(DataSource datasource) {
		this.datasource = datasource;
	}

    public static MSSQLHourRegDao createInstance() throws NamingException {
        DataSource dataSource = (DataSource) new InitialContext().lookup(JettyServer.DB_JNDI);
        if (dataSource == null) {
            throw new RuntimeException("Could not find datasource");
        }
        return new MSSQLHourRegDao(dataSource);
    }

    private Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

    public PeriodDetails getPeriodDetails(String user, DateTime date) {
        PeriodDetails periodDetails = new PeriodDetails();
        Date sqlDate = new Date(date.getMillis());

        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SELECT_CURRENT_PERIOD);
            statement.setString(1, user);
            statement.setDate(2, sqlDate);
            statement.setDate(3, sqlDate);

            ResultSet res = statement.executeQuery();
            if (res != null && res.next()) {
                periodDetails.setStartDate(new DateTime(res.getDate(1).getTime()));
                periodDetails.setEndDate(new DateTime(res.getDate(2).getTime()));
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
        return periodDetails;
    }

    @Override
    public Integer addHourRegistrations(String loggedInUser, String registeringForUser, String projectNumber,
                                        String activity, DateTime date, double hours, boolean isChargedHours,
                                        String workType, String description, boolean bypassChecks) {

        CallableStatement statement = null;
        try {
            statement = getConnection().prepareCall(STORE_PROCEDURE_INSERT_HOURS);
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
    public void updateHourRegistration(String userId, String taskNumber, String projectNumber, String activity,
                                       DateTime date, double hours, boolean isBillable, String workType,
                                       String description) {
        CallableStatement statement = null;

        try {
            statement = getConnection().prepareCall(STORE_PROCEDURE_UPDATE_HOURS);
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
    public void deleteHourRegistration(String userId, String taskNumber) {
        CallableStatement statement = null;
        try {
            statement = getConnection().prepareCall(STORE_PROCEDURE_DELETE_HOURS);
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
	public List<HourRegistration> getAllHoursForDate(String userId, DateTime date) {
		List<HourRegistration> result = new ArrayList<HourRegistration>();

		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(SELECT_REGISTRATIONS);
			statement.setString(1, userId);
			statement.setDate(2, new Date(date.getMillis()));
			ResultSet res = statement.executeQuery();
			while(res.next()){
				Integer taskNumber = res.getInt(2);
				String projectNumber = res.getString(3);
				String activityCode = res.getString(4);
				double hours = res.getDouble(8);
				String description = res.getString(9);
				boolean submitted = res.getBoolean(10);
				boolean approved = res.getBoolean(11);

                HourRegistration hourReg = new HourRegistration(taskNumber, projectNumber, activityCode, date, description,
                        hours,submitted, approved, null, null, null);

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

    @Override
    public List<ProjectDetail> searchProjects(String projectName) {
        List<ProjectDetail> result = new ArrayList<ProjectDetail>();
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
                ProjectDetail project = new ProjectDetail(projectNumber, activityCode, null, null, description);
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
    public WeekDetails getWeekList(String loggedInUser, String registeringForUser, String viewer, DateTime startDate) {
        CallableStatement statement = null;
        WeekDetails weekDetails = new WeekDetails();

        try {
            statement = getConnection().prepareCall(STORE_PROCEDURE_GET_PERIOD_INFORMATION);
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, COUNTRY);
            statement.setString(3, loggedInUser);
            statement.setString(4, viewer);
            statement.setString(5, registeringForUser);
            statement.setDate(6, new Date(startDate.getMillis()));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer recordId = resultSet.getInt(1);
                String projectNumber = resultSet.getString(2);
                String activityCode = resultSet.getString(3);
                Date date = resultSet.getDate(4);
                String entryDescription = resultSet.getString(5); // The users description for an hour
                double hours = resultSet.getDouble(6);
                boolean submitted = resultSet.getBoolean(9);
                boolean approved = resultSet.getBoolean(10);

                String projectName = resultSet.getString(15);
                String customerName = resultSet.getString(16);
                String activityDescription = resultSet.getString(17);

                weekDetails.addEntry(recordId, projectNumber, activityCode, date, entryDescription, hours, submitted,
                        approved, projectName, customerName, activityDescription);
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

        return weekDetails;
    }

    @Override
    public void submitHours(String loggedInUser, String registeringForUser, DateTime dayInPeriod) {
        CallableStatement statement = null;

        try {
            statement = getConnection().prepareCall(STORE_PROCEDURE_SUBMIT_HOURS);
            statement.setString(1, COUNTRY);
            statement.setString(2, loggedInUser);
            statement.setString(3, registeringForUser);
            statement.setDate(4, new Date(dayInPeriod.getMillis()));

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
    public void reopenHours(String loggedInUser, String registeringForUser, DateTime dayInPeriod) {
        CallableStatement statement = null;

        try {
            statement = getConnection().prepareCall(STORE_PROCEDURE_REOPEN_HOURS);
            statement.setString(1, COUNTRY);
            statement.setString(2, loggedInUser);
            statement.setString(3, registeringForUser);
            statement.setDate(4, new Date(dayInPeriod.getMillis()));

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
}