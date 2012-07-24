package no.steria.swhrs;

import org.joda.time.LocalDate;


public class HourRegistration {
	
	private int item;
	private String projectnumber; //maybe this will be changed to type "Project" later
	private String activityCode;
	private String date;
	private double hours;
	private String description;
	
	
	
	public HourRegistration(int item2, String projectNumber2,
			String activityCode2, double hours2, String description2) {
		this.item = item2;
		this.projectnumber = projectNumber2;
		this.activityCode = activityCode2;
		this.hours = hours2;
		this.description = description2;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getHours() {
		return hours;
	}
	
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public String getProjectnumber() {
		return projectnumber;
	}
	
	public void setProjectnumber(String projectnumber) {
		this.projectnumber = projectnumber;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
