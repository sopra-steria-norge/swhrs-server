package no.steria.swhrs;

/**
 * Date: 20.09.12
 * Time: 11:53
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class UtilityMethods {

    public static String getProjectKey(String projectNumber, String activityCode) {
        return projectNumber + "_" + activityCode;
    }
}
