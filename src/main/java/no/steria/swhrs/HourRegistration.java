package no.steria.swhrs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.LocalDate;

@Entity
public class HourRegistration {
	
	@SuppressWarnings("unused")
	@Id
	@GeneratedValue
	private long Id;
	
	private int personId;
	private int projectnumber; //maybe this will be changed to type "Project" later
	private LocalDate date;
	private double hours;
	
	public static HourRegistration createRegistration(int personId, int projectnumber, LocalDate date, double hours){
		HourRegistration hr = new HourRegistration ();
		hr.personId = personId;
		hr.projectnumber = projectnumber;
		hr.date = date;
		hr.hours = hours;
		return hr;
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
