package no.steria.swhrs;

import org.joda.time.LocalDate;

public class WeekRegistration {
	
	private int personId;
	private LocalDate date;
	private double weekHours;
	
	public WeekRegistration(int personId, LocalDate date, double weekHours){
		this.personId = personId;
		this.date = date;
		this.weekHours = weekHours;
	}
	
	public int getPersonId(){
		return personId;
	}
	
	public void setPersonId(int personId){
		this.personId = personId;
	}
	
	public LocalDate getDate(){
		return date;
	}
	
	public void setDate(LocalDate date){
		this.date = date;
	}
	
	public double getWeekHours(){
		return weekHours;
	}
	
	public void setWeekHours(double weekHours){
		this.weekHours = weekHours;
	}
	
}
