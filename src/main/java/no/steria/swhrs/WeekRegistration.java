package no.steria.swhrs;

public class WeekRegistration {
	
	private double hours;
	private int approved;
	private String date;
	
	public static WeekRegistration createWeekList(double hours, int approved, String date){
		WeekRegistration wl = new WeekRegistration();
		wl.hours = hours;
		wl.approved = approved;
		wl.date = date;
		return wl;
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
