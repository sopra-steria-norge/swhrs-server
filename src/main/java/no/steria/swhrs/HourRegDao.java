package no.steria.swhrs;

import java.util.List;

import org.joda.time.LocalDate;

public interface HourRegDao {

	void beginTransaction();

	void endTransaction(boolean b);
	
	void saveHours(HourRegistration reg);
	
	List<HourRegistration> getAllHoursForDate(int person_id, LocalDate date);

	boolean validateUser(String username, String password);
	
	HourRegistration getHourRegistration(int person_id, String project_id, LocalDate date);
	
	boolean updataHourRegistration(int person_id, String project_id, LocalDate date);
	
	boolean deleteHourRegistration(int person_id, String project_id, LocalDate date);

	List<WeekRegistration> getWeekSummary(String week);
	
}