package no.steria.swhrs.domain;

/**
 * Date: 20.09.12
 * Time: 07:26
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class ProjectDetail {

    private String projectNumber;
    private String activityCode;
    private String projectName;
    private String customerName;
    private String activityDescription;

    public ProjectDetail(String projectNumber, String activityCode, String projectName, String customerName, String activityDescription) {
        this.projectNumber = projectNumber;
        this.activityCode = activityCode;
        this.projectName = projectName;
        this.customerName = customerName;
        this.activityDescription = activityDescription;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDescription() {
        return activityDescription;
    }
}
