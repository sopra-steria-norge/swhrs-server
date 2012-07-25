package no.steria.swhrs;

public class WeekRegistration {
	
	private double hours;
	private int approved;
	private String date;
	
	public WeekRegistration(String date2, double hours2, int approved2) {
		this.date = date2;
		this.hours = hours2;
		this.approved = approved2;
	}
	
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	

}
