package no.steria.swhrs;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * User: chrm@steria.no
 * Date: 17.09.12
 * Time: 08:28
 * All rights reserved Steria AS 2012
 */
public class JSONBuilder {

    public static JSONObject createProjects(List<Projects> project) {
        int counter = 0;

        JSONObject projectJson = new JSONObject();
        for(Projects po : project){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(RegistrationConstants.PROJECT_NUMBER, po.getProjectNumber());
            map.put(RegistrationConstants.ACTIVITY_CODE, po.getActivityCode());
            map.put(RegistrationConstants.DESCRIPTION, po.getDescription());
            counter++;
            projectJson.put(counter, map);
        }

        return projectJson;
    }

    /**
     * Helper method to make a JSON object from a list of HourRegistrations
     * @param hourRegistrationList the list of HourRegistration objects
     * @param stringDate the date of the registrations
     * @return A json object of the format: key: taskNumber values: [description, hours]
     */
    public static JSONObject createFromHours(List<HourRegistration> hourRegistrationList, String stringDate) {
        JSONObject json = new JSONObject();
        for (HourRegistration hourRegistration : hourRegistrationList) {
            HashMap map = new HashMap();
            map.put(RegistrationConstants.PROJECT_NUMBER, hourRegistration.getProjectnumber());
            map.put(RegistrationConstants.ACTIVITY_CODE, hourRegistration.getActivityCode());
            map.put(RegistrationConstants.DESCRIPTION, hourRegistration.getDescription());
            map.put("approved", hourRegistration.isApproved());
            map.put("submitted", hourRegistration.isSubmitted());
            map.put(RegistrationConstants.HOURS, hourRegistration.getHours());
            json.put(hourRegistration.getTaskNumber(), map);
        }
        json.put("date", stringDate);
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
}
