package no.steria.swhrs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.LocalDate;

@Entity
public class HourRegistrationEntity {
	
	@SuppressWarnings("unused")
	@Id
	@GeneratedValue
	private long Id;
	
	private int personId;
	private int projectnumber; //maybe this will be changed to type "Project" later
	private LocalDate date;
	private double hours;
	
//	public static HourRegistrationEntity createRegistration(int personId, int projectnumber, LocalDate date, double hours){
//		HourRegistrationEntity hr = new HourRegistrationEntity ();
//		hr.personId = personId;
//		hr.projectnumber = projectnumber;
//		hr.date = date;
//		hr.hours = hours;
//		return hr;
//	}
	
	public static HourRegistrationEntity createRegistration(HourRegistration hr){
		HourRegistrationEntity hrEnt = new HourRegistrationEntity ();
		hrEnt.personId = hr.getPersonId();
		hrEnt.projectnumber = hr.getProjectnumber();
		hrEnt.date = hr.getDate();
		hrEnt.hours = hr.getHours();
		return hrEnt;
	}
	
	public HourRegistration createHourRegistrationFromEntity(){
		HourRegistration hr = HourRegistration.createRegistration(Id, getPersonId(), getProjectnumber(), getDate(), getHours());
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
