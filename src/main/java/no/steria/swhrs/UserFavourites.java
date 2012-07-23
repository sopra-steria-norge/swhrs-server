package no.steria.swhrs;

public class UserFavourites {
	
	private String userName;
	private String projectNumber;
	private String activityCode;
	private String description;
	
	public static UserFavourites createFavouriteList(String userName, String projectNumber, String activityCode, String description){
		UserFavourites uf = new UserFavourites();
		uf.userName = userName;
		uf.projectNumber = projectNumber;
		uf.activityCode = activityCode;
		uf.description = description;
		return uf;
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
