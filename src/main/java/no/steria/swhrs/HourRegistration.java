package no.steria.swhrs;

import org.joda.time.LocalDate;


public class HourRegistration {

	private long Id;
	private int personId;
	private int projectnumber; //maybe this will be changed to type "Project" later
	private LocalDate date;
	private double hours;
	
	public static HourRegistration createRegistration(long id, int personId, int projectnumber, LocalDate date, double hours){
		HourRegistration hr = new HourRegistration ();
		hr.personId = personId;
		hr.projectnumber = projectnumber;
		hr.date = date;
		hr.hours = hours;
		return hr;
	}
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}
	public int getPersonId() {
		return personId;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public double getHours() {
		return hours;
	}
	
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public int getProjectnumber() {
		return projectnumber;
	}
	
	public void setProjectnumber(int projectnumber) {
		this.projectnumber = projectnumber;
	}
}
