package no.steria.swhrs;

import java.util.List;

import org.joda.time.LocalDate;

public interface PersonDao {

	void beginTransaction();

	void endTransaction(boolean b);
	
	boolean saveHours(HourRegistration reg);
	
	HourRegistration getHours(Long person_id, LocalDate date);
}