package no.steria.swhrs;

public class Projects {
	
	private String projectNumber;
	private String activityCode;
	private String description;
	
	public static Projects createProjectList(String projectNumber, String activityCode, String description){
		Projects project= new Projects();
		project.projectNumber = projectNumber;
		project.activityCode = activityCode;
		project.description = description;
		return project;
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
