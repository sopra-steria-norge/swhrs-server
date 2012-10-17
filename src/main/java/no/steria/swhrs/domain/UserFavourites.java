package no.steria.swhrs.domain;

public class UserFavourites {

	private String projectNumber;
	private String activityCode;
	private String description;
	private String projectName;
	private int billable;
	private String customer;
	private int internalProject;
	
	public UserFavourites(String projectNumber, String activityCode, String description, int billable,
                          String projectName, String customer, int internalProject) {
		this.projectNumber = projectNumber;
		this.activityCode = activityCode;
		this.description = description;
		this.projectName = projectName;
		this.billable = billable;
		this.customer = customer;
		this.internalProject = internalProject;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public String getDescription() {
		return description;
	}

	public String getProjectName() {
		return projectName;
	}

	public int getBillable() {
		return billable;
	}

	public String getCustomer() {
		return customer;
	}

	public int getInternalProject() {
		return internalProject;
	}
}
