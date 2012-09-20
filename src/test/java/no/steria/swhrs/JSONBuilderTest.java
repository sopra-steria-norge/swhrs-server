package no.steria.swhrs;

import org.junit.Test;

import java.sql.Date;

/**
 * Date: 20.09.12
 * Time: 08:51
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class JSONBuilderTest {

    private static String projectNumber = "projectNumber";
    private static String activityCode = "activityCode";
    private static Date date = new Date(23423423423L);
    private static String entryDescription = "description";
    private static double hours = 2.0;
    private static boolean submitted = false;
    private static boolean approved = false;
    private static String projectName = "projectName";
    private static String customerName = "customerName";
    private static String activityDescription = "activityDescription";


    @Test
    public void testGeneratingAdvancedMap() throws Exception {
        WeekDetails weekDetails = new WeekDetails();
        weekDetails.addEntry(123, projectNumber, activityCode, date, entryDescription, hours, submitted, approved,
                projectName, customerName, activityDescription);
        System.out.println(JSONBuilder.createFromWeekDetails(weekDetails).toJSONString());
    }
}
