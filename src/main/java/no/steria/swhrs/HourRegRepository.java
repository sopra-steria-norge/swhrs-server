package no.steria.swhrs;

import java.util.List;

import org.joda.time.LocalDate;

public interface HourRegRepository {

	List<HourRegistration> getAllHoursForDate(int i, LocalDate date);

	boolean validateUser(String username, String password, String country);

	List<WeekRegistration> getWeekSummary(String weekString);

	void deleteHourRegistration(String projectID);

	void saveHours(HourRegistration reg);

	void beginTransaction();

	void endTransaction(boolean b);

}
