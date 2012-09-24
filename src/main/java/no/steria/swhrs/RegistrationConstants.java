package no.steria.swhrs;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * User: chrm@steria.no
 * Date: 17.09.12
 * Time: 09:06
 * All rights reserved Steria AS 2012
 */
public interface RegistrationConstants {
    public final static String PROJECT_NUMBER = "projectNumber";
    public final static String ACTIVITY_CODE = "activityCode";
    public final static String DESCRIPTION = "description";
    public final static String TASK_NUMBER = "taskNumber";
    public final static String HOURS = "hours";
    public final static String BILLABLE = "billable";
    public final static String DATE = "date";
    public final static String WORK_TYPE = "workType";
    public final static String CUSTOMER_NAME = "customerName";
    public final static String PROJECT_NAME = "projectName";
    public final static String USER = "user";
    public final static String SEARCH = "search";

    public final static String TEXT_SUCCESS = "Success";

    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public final static String REQUEST_URL_HOURS_RETRIEVE_WEEK = "hours/week";
    public final static String REQUEST_URL_HOURS_RETRIEVE_DAY = "hours/daylist";
    public final static String REQUEST_URL_HOURS_ADD = "hours/registration";
    public final static String REQUEST_URL_HOURS_DELETE = "hours/deleteRegistration";
    public final static String REQUEST_URL_HOURS_UPDATE = "hours/updateRegistration";
    public final static String REQUEST_URL_SUBMIT = "hours/submitPeriod";
    public final static String REQUEST_URL_REOPEN = "hours/reopenPeriod";
    public final static String REQUEST_URL_FAVORITE_ADD = "hours/addFavourites";
    public final static String REQUEST_URL_FAVORITE_DELETE = "hours/deleteFavourite";
    public final static String REQUEST_URL_FAVORITE_GET = "hours/favourite";
    public final static String REQUEST_URL_FAVORITE_SEARCH = "hours/searchFavourites";
}
