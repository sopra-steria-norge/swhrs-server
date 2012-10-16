package no.steria.swhrs;

import org.joda.time.DateTime;

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

    public void addEntry(Integer recordId, String projectNumber, String activityCode, DateTime date, String entryDescription,
                         double hours, String workType, boolean submitted, boolean approved, boolean rejected, String projectName, String customerName,
                         String activityDescription) {

        // All entries with a recordId is hours that has been entered, the ones without are just other projects without hours
        if (recordId != null && recordId != 0) {
            registerHour(recordId, projectNumber, activityCode, date, entryDescription, hours, workType, submitted, approved,
                    rejected, projectName, customerName, activityDescription);

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

    private void registerHour(Integer recordId, String projectNumber, String activityCode, DateTime date,
                              String entryDescription, double hours, String workType, boolean submitted, boolean approved,
                              boolean rejected, String projectName, String customerName, String activityDescription) {
        hourRegistrations.put(recordId.toString(), new HourRegistration(recordId, projectNumber, activityCode, date, entryDescription,
                hours, workType, submitted, approved, rejected, projectName, customerName, activityDescription));
    }

    public Map<String, HourRegistration> getHourRegistrations() {
        return hourRegistrations;
    }

    public Map<DateTime,List<HourRegistration>> getHourRegistrationsByDate(PeriodDetails periodDetails) {
        if (hourRegistrationsByDate.isEmpty()) populateHourRegistrationByDate(periodDetails);
        return hourRegistrationsByDate;
    }

    private void populateHourRegistrationByDate(PeriodDetails periodDetails) {
        populateDaysInList(periodDetails);

        for (Map.Entry<String,HourRegistration> entry : hourRegistrations.entrySet()) {
            List<HourRegistration> hourRegistrationList = hourRegistrationsByDate.get(entry.getValue().getDate());
            hourRegistrationList.add(entry.getValue());
        }
    }

    private void populateDaysInList(PeriodDetails periodDetails) {
        DateTime date = new DateTime(periodDetails.getStartDate());
        DateTime endDate = new DateTime(periodDetails.getEndDate().plusDays(1)); // cause of the nature of while loop
        do {
            hourRegistrationsByDate.put(date, new ArrayList<HourRegistration>());
            date = date.plusDays(1);
        } while (date.isBefore(endDate));
    }

    public List<ProjectDetail> getProjectDetailsAsList() {
        List<ProjectDetail> projectDetailsList = new ArrayList<ProjectDetail>();
        for (Map.Entry<String,ProjectDetail> entry : projectDetails.entrySet()) {
            projectDetailsList.add(entry.getValue());
        }
        return projectDetailsList;
    }
}

