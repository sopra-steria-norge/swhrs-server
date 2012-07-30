package no.steria.swhrs;

public class UserFavourites {
	
	private String userName;
	private String projectNumber;
	private String activityCode;
	private String description;
	private String projectName;
	private int billable;
	private String customer;
	private int internalProject;
	
	public UserFavourites(String projectNumber2, String activityCode2,
			String description2, int billable, String projectName, String customer, int internalProject) {
		this.projectNumber = projectNumber2;
		this.activityCode = activityCode2;
		this.description = description2;
		this.projectName = projectName;
		this.setBillable(billable);
		this.setCustomer(customer);
		this.setInternalProject(internalProject);
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getBillable() {
		return billable;
	}

	public void setBillable(int billable) {
		this.billable = billable;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getInternalProject() {
		return internalProject;
	}

	public void setInternalProject(int internalProject) {
		this.internalProject = internalProject;
	}


}
