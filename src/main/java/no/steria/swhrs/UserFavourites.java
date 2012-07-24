package no.steria.swhrs;

public class UserFavourites {
	
	private String userName;
	private String projectNumber;
	private String activityCode;
	private String description;
	
	public UserFavourites(String projectNumber2, String activityCode2,
			String description2) {
		this.projectNumber = projectNumber2;
		this.activityCode = activityCode2;
		this.description = description2;
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


}
