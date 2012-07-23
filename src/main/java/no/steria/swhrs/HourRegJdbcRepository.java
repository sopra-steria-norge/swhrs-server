package no.steria.swhrs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import org.joda.time.LocalDate;

public class HourRegJdbcRepository extends AbstractJdbcRepository implements HourRegRepository{
	
	private static final String SELECT_USERS = "select * from \"Norge$Resource\" where No_ = 'AK' and \"WEB Password\" = 'bingo521'";
	private static final String SELECT_REGISTRATIONS= "select Løbenr_, Projektnr_, Beskrivelse, Antal from \"Norge$Time Entry\" where Ressourcekode = 'AK' and Dato = '2012-05-30 00:00:00.0'";
	private static final String SELECT_FAVOURITES = "select \"Norge$Favourite Task\".Projektnr_, \"Norge$Favourite Task\".Aktivitetskode, \"Norge$Tasklist\".Beskrivelse from \"Norge$Favourite Task\"  INNER JOIN \"Norge$Tasklist\" ON \"Norge$Favourite Task\".Projektnr_= \"Norge$Tasklist\".Projektnr_ AND \"Norge$Favourite Task\".Aktivitetskode = \"Norge$Tasklist\".Kode WHERE \"Norge$Favourite Task\".Resourcekode = 'AK'";
	private static final String SELECT_PROJECTS = "select \"Norge$Tasklist\".Projektnr_, \"Norge$Tasklist\".Kode, \"Norge$Tasklist\".Beskrivelse FROM \"Norge$Tasklist\" WHERE Type=0 and Afsluttet=0 and Spærret=0 and Vis=1 and Status=2";
	private static final String SELECT_WEEKREGISTRATIONS = "select \"Norge$Time Entry\".Løbenr_, Beskrivelse, Antal, Godkendt, Dato from \"Norge$Time Entry\" where Ressourcekode ='AK' AND Dato Between '2012-06-10 00:00:00.0' AND '2012-06-15 00:00:00.0' ORDER BY Dato";
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
