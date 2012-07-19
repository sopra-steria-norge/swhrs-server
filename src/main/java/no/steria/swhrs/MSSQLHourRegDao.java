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
	public List<HourRegistration> getAllHoursForDate(int person_id,
			LocalDate date) {
		List<HourRegistration> resultat = new ArrayList<HourRegistration>();
		
		
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM \"Norge$Time Entry\" WHERE Ressourcekode = 'AK' AND Dato = '2012-05-25 00:00:00.0'");
			ResultSet res = statement.executeQuery();
			while(res.next()){
				HourRegistration hourReg = new HourRegistration();
				resultat.add(hourReg);	
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
		return resultat;
	}

	@Override
	public boolean validateUser(String username, String password, String country) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HourRegistration getHourRegistration(int person_id,
			String project_id, LocalDate date) {
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
	public void deleteHourRegistration(String project_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<WeekRegistration> getWeekSummary(String weekNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
