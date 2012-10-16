package no.steria.swhrs;

import org.joda.time.DateTime;

import java.sql.Date;

public class HourRegistration {
	
	private int taskNumber;
	private String projectNumber;
	private String activityCode;
	private DateTime date;
	private double hours;
	private String description;
	private boolean submitted;
	private boolean approved;
    private boolean rejected;
    private String projectName;
    private String customerName;
    private String activityDescription;

    public HourRegistration(Integer recordId, String projectNumber, String activityCode, DateTime date,
                            String entryDescription, double hours, boolean submitted, boolean approved,
                            boolean rejected, String projectName, String customerName, String activityDescription) {
        this.taskNumber = recordId;
        this.projectNumber = projectNumber;
        this.activityCode = activityCode;
        this.date = date;
        this.description = entryDescription;
        this.hours = hours;
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
        this.projectName = projectName;
        this.customerName = customerName;
        this.activityDescription = activityDescription;
    }

    public DateTime getDate() {
		return date;
	}

	public double getHours() {
		return hours;
	}
	
	public String getProjectNumber() {
		return projectNumber;
	}

	public int getTaskNumber() {
		return taskNumber;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public String getDescription() {
		return description;
	}

	public boolean isSubmitted() {
		return submitted;
	}

	public boolean isApproved() {
		return approved;
	}

	public boolean isRejected() {
		return rejected;
	}

    public String getProjectName() {
        return projectName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }
}
