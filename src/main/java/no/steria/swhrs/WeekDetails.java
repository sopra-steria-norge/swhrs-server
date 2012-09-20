package no.steria.swhrs;

import no.steria.swhrs.HourRegistration;
import no.steria.swhrs.ProjectDetail;
import org.joda.time.DateTime;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chrm@steria.no
 * Date: 19.09.12
 * Time: 13:16
 * All rights reserved Steria AS 2012
 */
public class WeekDetails {

    private Map<String, ProjectDetail> projectDetails = new HashMap<String, ProjectDetail>() {};
    private Map<String, HourRegistration> hourRegistrations = new HashMap<String, HourRegistration>() {};
    private Map<DateTime, List<HourRegistration>> hourRegistrationsByDate = new HashMap<DateTime, List<HourRegistration>>();

    public void addEntry(Integer recordId, String projectNumber, String activityCode, Date date, String entryDescription,
                         double hours, boolean submitted, boolean approved, String projectName, String customerName,
                         String activityDescription) {
        // All entries with a recordId is hours that has been entered, the ones without are just other projects without hours
        if (recordId != null && recordId != 0) {
            registerHour(recordId, projectNumber, activityCode, date, entryDescription, hours, submitted, approved,
                    projectName, customerName, activityDescription);
            if (!projectDetails.containsKey(UtilityMethods.getProjectKey(projectNumber, activityCode))) {
                registerProject(projectNumber, activityCode, projectName, customerName, activityDescription);
            }
        }
        else registerProject(projectNumber, activityCode, projectName, customerName, activityDescription);
    }

    private void registerProject(String projectNumber, String activityCode, String projectName, String customerName,
                                 String activityDescription) {
        projectDetails.put(UtilityMethods.getProjectKey(projectNumber, activityCode),
                new ProjectDetail(projectNumber, activityCode, projectName, customerName, activityDescription));
    }

    private void registerHour(Integer recordId, String projectNumber, String activityCode, Date date,
                              String entryDescription, double hours, boolean submitted, boolean approved,
                              String projectName, String customerName, String activityDescription) {
        DateTime dateTime = new DateTime(date.getTime());
        hourRegistrations.put(recordId.toString(), new HourRegistration(recordId, projectNumber, activityCode, dateTime, entryDescription,
                hours, submitted, approved, projectName, customerName, activityDescription));
    }

    public Map<String, HourRegistration> getHourRegistrations() {
        return hourRegistrations;
    }

    public Map<DateTime,List<HourRegistration>> getHourRegistrationsByDate() {
        if (hourRegistrationsByDate.isEmpty()) populateHourRegistrationByDate();
        return hourRegistrationsByDate;
    }

    private void populateHourRegistrationByDate() {
        for (Map.Entry<String,HourRegistration> entry : hourRegistrations.entrySet()) {
            DateTime entryDateTime = entry.getValue().getDate();

            if (!hourRegistrationsByDate.containsKey(entryDateTime)) {
                hourRegistrationsByDate.put(entryDateTime, new ArrayList<HourRegistration>() {
                });
            }

            List<HourRegistration> hourRegistrationList = hourRegistrationsByDate.get(entryDateTime);
            hourRegistrationList.add(entry.getValue());
        }
    }

    public List<ProjectDetail> getProjectDetailsAsList() {
        List<ProjectDetail> projectDetailsList = new ArrayList<ProjectDetail>();
        for (Map.Entry<String,ProjectDetail> entry : projectDetails.entrySet()) {
            projectDetailsList.add(entry.getValue());
        }
        return projectDetailsList;
    }
}

