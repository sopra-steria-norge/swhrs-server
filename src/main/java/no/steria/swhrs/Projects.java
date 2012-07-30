package no.steria.swhrs;

public class Projects {
	
	private String projectNumber;
	private String activityCode;
	private String description;
	
	public Projects (String projectNumber, String activityCode, String description){
		this.projectNumber = projectNumber;
		this.activityCode = activityCode;
		this.description = description;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

}
