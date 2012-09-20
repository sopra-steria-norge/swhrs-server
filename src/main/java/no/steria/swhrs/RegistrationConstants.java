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

    public final static String TEXT_SUCCESS = "Success";
    public final static int HTTP_STATUS_CODE_SUCCESS = 200;

    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
}
