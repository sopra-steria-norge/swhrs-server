package no.steria.swhrs;

import org.joda.time.LocalDate;


public class HourRegistration {
	
	private int taskNumber;
	private String projectnumber; //maybe this will be changed to type "Project" later
	private String activityCode;
	private String date;
	private double hours;
	private String description;
	private boolean submitted;
	private boolean approved;
	
	
	
	public HourRegistration(String date, int item2, String projectNumber2,
			String activityCode2, double hours2, String description2, boolean submitted, boolean approved) {
		this.taskNumber = item2;
		this.projectnumber = projectNumber2;
		this.activityCode = activityCode2;
		this.hours = hours2;
		this.description = description2;
		this.submitted =submitted;
		this.approved = approved;
		this.date = date;
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

	public int getTaskNumber() {
		return taskNumber;
	}

	public void setItem(int item) {
		this.taskNumber = item;
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

	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
