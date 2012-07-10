package no.steria.swhrs;

import java.util.List;

import org.joda.time.LocalDate;

public interface HourRegDao {

	void beginTransaction();

	void endTransaction(boolean b);
	
	void saveHours(HourRegistration reg);
	
	List<HourRegistration> getHours(int person_id, LocalDate date);

	boolean validateUser(String username, String password);
}