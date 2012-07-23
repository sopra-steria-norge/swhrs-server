package no.steria.swhrs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.LocalDate;

public class MSSQLHourRegDao implements HourRegDao {
	
	
	private final DataSource datasource;
	private Connection connection = null;
	private static final String SELECT_USERS = "select * from \"Norge$Resource\" where No_ = ? and \"WEB Password\" = ?";
	private static final String SELECT_REGISTRATIONS = "SELECT * FROM \"Norge$Time Entry\" WHERE Ressourcekode = ? AND Dato = ?";
	private static final String SELECT_FAVOURITES = "select \"Norge$Favourite Task\".Projektnr_, \"Norge$Favourite Task\".Aktivitetskode, \"Norge$Tasklist\".Beskrivelse from \"Norge$Favourite Task\"  INNER JOIN \"Norge$Tasklist\" ON \"Norge$Favourite Task\".Projektnr_= \"Norge$Tasklist\".Projektnr_ AND \"Norge$Favourite Task\".Aktivitetskode = \"Norge$Tasklist\".Kode WHERE \"Norge$Favourite Task\".Resourcekode = ?";
	//private static final String SELECT_PROJECTS = "select \"Norge$Tasklist\".Projektnr_, \"Norge$Tasklist\".Kode, \"Norge$Tasklist\".Beskrivelse FROM \"Norge$Tasklist\" WHERE Type=0 and Afsluttet=0 and Spærret=0 and Vis=1 and Status=2";
	private static final String SELECT_WEEKREGISTRATIONS = "select \"Norge$Time Entry\".Løbenr_, Beskrivelse, Antal, Godkendt, Dato from \"Norge$Time Entry\" where Ressourcekode = ? AND Dato Between ? AND ? ORDER BY Dato";
	private static final String SELECT_SEARCHPROJECTS = "select \"Norge$Tasklist\".Projektnr_, \"Norge$Tasklist\".Kode, \"Norge$Tasklist\".Beskrivelse FROM \"Norge$Tasklist\" WHERE Beskrivelse like ? OR Projektnr_ like ? ORDER BY Beskrivelse";
	private static final String SELECT_PERIODS = "select * from \"Norge$Time Periods\" WHERE Ressource = ? AND Startdato < ? AND Slutdato > ?";
	private static final String DELETE_REGISTRATION = "delete from \"Norge$Time Entry\" where Løbenr_ = ?";
	
	public MSSQLHourRegDao(DataSource datasource) {
		this.datasource = datasource;
		
	}

	@Override
	public void beginTransaction() {
		try {
			connection = datasource.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endTransaction(boolean b) {
		try {
			connection.rollback();
			connection.close();
			connection = null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void saveHours(HourRegistration reg) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<HourRegistration> getAllHoursForDate(String userName,
			String date) {
		List<HourRegistration> result = new ArrayList<HourRegistration>();
		
		
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_REGISTRATIONS);
			statement.setString(1, userName);
			statement.setString(2, date);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				HourRegistration hourReg = new HourRegistration();
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
	public boolean validateUser(String username, String password) {
		PreparedStatement statement = null;
		boolean userBoolean = false;
		String user = null;
		try {
			statement = connection.prepareStatement(SELECT_USERS);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				System.out.println(res.getString(2)+":"+res.getString(4));
				user = res.getString(2);
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
		if(user != null)return true;
		return false;	
	}

	@Override
	public HourRegistration getHourRegistration(int person_id, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updataHourRegistration(int person_id, String project_id,
			LocalDate date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteHourRegistration(String project_id) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(DELETE_REGISTRATION);
			statement.setString(1, project_id);
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
		return true;

	}

	@Override
	public List<WeekRegistration> getWeekSummary(String weekNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserFavourites> getUserFavouirtes(String userName) {
		List<UserFavourites> result = new ArrayList<UserFavourites>();
		PreparedStatement statement = null;
		int counter = 0;
		try {
			statement = connection.prepareStatement(SELECT_FAVOURITES);
			statement.setString(1, userName);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				UserFavourites userFav = new UserFavourites();
				result.add(userFav);
				counter++;
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
		System.out.println(counter);
		return result;
	}

	public List<Projects> getProjects(String projectName) {
		List<Projects> result = new ArrayList<Projects>();
		System.out.println(projectName);
		PreparedStatement statement = null;
		int counter = 0;
		String searchString = "%"+projectName+"%";
		System.out.println(searchString);
		try {
			statement = connection.prepareStatement(SELECT_SEARCHPROJECTS);
			statement.setString(1, searchString);
			statement.setString(2, projectName);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				Projects project = new Projects();
				result.add(project);
				counter++;
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
		System.out.println(counter);
		return result;
	}

	public List<WeekRegistration> getWeekList(String userName, String dateFrom,
			String dateTo) {
		List<WeekRegistration> result = new ArrayList<WeekRegistration>();
		PreparedStatement statement = null;
		int counter = 0;
		try {
			statement = connection.prepareStatement(SELECT_WEEKREGISTRATIONS);
			statement.setString(1, userName);
			statement.setString(2, dateFrom);
			statement.setString(3, dateTo);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				WeekRegistration weekReg = new WeekRegistration();
				result.add(weekReg);
				counter++;
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
		System.out.println(counter);
		return result;
		
	}

	public DatePeriod getPeriod(String userid, String date) {
		DatePeriod datePeriod = new DatePeriod();
		PreparedStatement statement = null;
		int counter = 0;
		try {
			statement = connection.prepareStatement(SELECT_PERIODS);
			statement.setString(1, userid);
			statement.setString(2, date);
			statement.setString(3, date);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				datePeriod.setFromDate(res.getString(2));
				datePeriod.setToDate(res.getString(5));
				datePeriod.setDescription(res.getString(6));
				counter++;
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
		System.out.println(counter);
		return datePeriod;
	}

}
