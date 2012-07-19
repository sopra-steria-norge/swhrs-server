package no.steria.swhrs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import org.joda.time.LocalDate;

public class HourRegJdbcRepository extends AbstractJdbcRepository implements HourRegRepository{
	
	private static final String SELECT_USERS = "SELECT * FROM Users WHERE username=? AND Password=? AND country=?";
	private static final String SELECT_REGISTRATIONS = "SELECT * FROM Registrations WHERE userid=? AND date=?";
	private static final String SELECT_FAVOURITES = "SELECT * FROM Favourites WHERE userid=?";
	private static final String SELECT_WEEKREGISTRATIONS = "SELECT * FROM Registrations WHERE userid=? AND WHERE date BETWEEN ? AND ?";
	private static final String SELECT_PROJECTS = "SELECT * FROM Projects";
	//next value for registration_id
	private static final String INSERT_REGISTRATION = "INSERT INTO Registrations VALUES (?, ?, ?, ?)";
	private static final String INSERT_FAVOURITE = "INSERT INTO Favourites VALUES (?, ?)";
	private static final String DELETE_REGISTRATION = "DELETE FROM Registrations WHERE registration_id=?";

	//GetPeriod?, submitPeriodRegistrations, saveOvertime
	
	
	public HourRegJdbcRepository(DataSource dataSource){
		super(dataSource);
	}
	
	@Override
	public List<HourRegistration> getAllHoursForDate(int i, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateUser(String username, String password, String country) {
		try {
			PreparedStatement statement = prepareStatement(SELECT_USERS);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, country);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				System.out.println(res.getString(1));
				return true;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	@Override
	public List<WeekRegistration> getWeekSummary(String weekString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteHourRegistration(String projectID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveHours(HourRegistration reg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beginTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endTransaction(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
