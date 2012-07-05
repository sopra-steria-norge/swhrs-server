package no.steria.swhrs;

import org.joda.time.LocalDate;

public class HourRegistration {
	private long personId;
	private int projectnumber; //maybe this will be type "Project" later
	private LocalDate date;
	private double hours;
	
	public static HourRegistration createRegistration(Long personId, int projectnumber, LocalDate date, double hours){
		HourRegistration hr = new HourRegistration ();
		hr.personId = personId;
		hr.projectnumber = projectnumber;
		hr.date = date;
		hr.hours = hours;
		return hr;
	}

}
