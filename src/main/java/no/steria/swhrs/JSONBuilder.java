package no.steria.swhrs;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: chrm@steria.no
 * Date: 17.09.12
 * Time: 08:28
 * All rights reserved Steria AS 2012
 */
public class JSONBuilder {

    public static JSONObject createProjects(List<ProjectDetail> projectDetails) {
        JSONObject projectJson = new JSONObject();
        for(ProjectDetail projectDetail : projectDetails){
            String projectNumber = projectDetail.getProjectNumber();
            String activityCode = projectDetail.getActivityCode();

            HashMap<String, String> map = new HashMap<String, String>();
            map.put(RegistrationConstants.PROJECT_NUMBER, projectNumber);
            map.put(RegistrationConstants.ACTIVITY_CODE, activityCode);
            map.put(RegistrationConstants.DESCRIPTION, projectDetail.getDescription());
            map.put(RegistrationConstants.CUSTOMER_NAME, projectDetail.getCustomerName());
            map.put(RegistrationConstants.PROJECT_NAME, projectDetail.getProjectName());
            projectJson.put(UtilityMethods.getProjectKey(projectNumber, activityCode), map);
        }

        return projectJson;
    }

    /**
     * Helper method to make a JSON object from a list of HourRegistrations
     * @param hourRegistrationList the list of HourRegistration objects
     * @param date the date of the registrations
     * @return A json object of the format: key: taskNumber values: [description, hours]
     */
    public static JSONObject createFromHours(List<HourRegistration> hourRegistrationList, DateTime date) {
        JSONObject json = new JSONObject();
        for (HourRegistration hourRegistration : hourRegistrationList) {
            HashMap map = new HashMap();
            map.put(RegistrationConstants.PROJECT_NUMBER, hourRegistration.getProjectNumber());
            map.put(RegistrationConstants.ACTIVITY_CODE, hourRegistration.getActivityCode());
            map.put(RegistrationConstants.DESCRIPTION, hourRegistration.getDescription());
            map.put("approved", hourRegistration.isApproved());
            map.put("submitted", hourRegistration.isSubmitted());
            map.put(RegistrationConstants.HOURS, hourRegistration.getHours());
            json.put(hourRegistration.getTaskNumber(), map);
        }
        return json;
    }

    /**
     * Helper method to create JSON object from a list of UserFavourites objects
     * @param userList The list containing user favourite objects stored in the database
     * @return JSON object with the format {"projectNumber":{"internalproject":value,"activitycode":value,"description": value,"projectname": value,"customername": value,"billable": value}
     *         Keys are generated from 1 and up so it's easy to sort later, they contain a map with the rest of the values
     */
    @SuppressWarnings("unchecked")
    public static JSONObject createFromFavourites(List<UserFavourites> userList) {
        JSONObject json = new JSONObject();
        int counter = 0;
        for (UserFavourites userFavourites: userList) {
            @SuppressWarnings("rawtypes")
            HashMap map = new HashMap();
            map.put(RegistrationConstants.PROJECT_NUMBER, userFavourites.getProjectNumber());
            map.put(RegistrationConstants.ACTIVITY_CODE, userFavourites.getActivityCode());
            map.put(RegistrationConstants.DESCRIPTION, userFavourites.getDescription());
            map.put("billable", userFavourites.getBillable());
            map.put("projectname", userFavourites.getProjectName());
            map.put("customername", userFavourites.getCustomer());
            map.put("internalproject", userFavourites.getInternalProject());

            json.put(counter++, map);
        }
        return json;
    }

    public static JSONObject createFromWeekDetails(WeekDetails weekDetails, PeriodDetails periodDetails) {
        JSONObject json = new JSONObject();
        json.put("days", createFromWeekDetails(weekDetails.getHourRegistrationsByDate(periodDetails))) ;
        json.put("projects", createProjects(weekDetails.getProjectDetailsAsList()));
        return json;
    }

    private static JSONObject createFromWeekDetails(Map<DateTime, List<HourRegistration>> hourRegistrations) {
        JSONObject json = new JSONObject();

        for(Map.Entry<DateTime, List<HourRegistration>> entry : hourRegistrations.entrySet()) {
            json.put(entry.getKey().toString(RegistrationConstants.dateTimeFormatter), createFromHours(entry.getValue(), entry.getKey()));
        }

        return json;
    }
}
