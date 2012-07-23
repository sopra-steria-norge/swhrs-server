package no.steria.swhrs;

import java.util.List;

import org.joda.time.LocalDate;

public interface HourRegDao {

	void beginTransaction();

	void endTransaction(boolean b);
	
	void saveHours(HourRegistration reg);
	
	List<HourRegistration> getAllHoursForDate(String person_id, String date);
	
	List<UserFavourites> getUserFavouirtes(String userName);

	boolean validateUser(String username, String password);
	
	HourRegistration getHourRegistration(int person_id, LocalDate date);
	
	boolean updataHourRegistration(int person_id, String project_id, LocalDate date);
	
	boolean deleteHourRegistration(String project_id);

	List<WeekRegistration> getWeekSummary(String weekNumber);

	List<WeekRegistration> getWeekList(String userName, String dateFrom,
			String dateTo);
	
}