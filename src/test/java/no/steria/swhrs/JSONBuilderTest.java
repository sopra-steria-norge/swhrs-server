package no.steria.swhrs;

import no.steria.swhrs.domain.HourRegistration;
import no.steria.swhrs.domain.NormTimeDetails;
import no.steria.swhrs.domain.PeriodDetails;
import no.steria.swhrs.domain.WeekDetails;
import no.steria.swhrs.util.JSONBuilder;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    private static DateTime date = new DateTime(2012, 9, 5, 0, 0);
    private static String entryDescription = "description";
    private static double hours = 2.0;
    private static boolean submitted = false;
    private static boolean approved = false;
    private static String projectName = "projectName";
    private static String customerName = "customerName";
    private static String activityDescription = "activityDescription";
    private PeriodDetails periodDetails;
    private NormTimeDetails normTimeDetails;

    @Before
    public void setup() throws Exception {
        periodDetails = new PeriodDetails();
        periodDetails.setStartDate(new DateTime(2012, 9, 3, 0, 0));
        periodDetails.setEndDate(new DateTime(2012, 9, 9, 0, 0));
        normTimeDetails = new NormTimeDetails();
        normTimeDetails.setPeriodNormTime(10);
    }

    @Test
    public void testGeneratingWeekDetailsJSONObject() throws Exception {
        WeekDetails weekDetails = new WeekDetails();
        weekDetails.addEntry(123, projectNumber, activityCode, date, entryDescription, hours, "", submitted, approved,
                false, projectName, customerName, activityDescription);
        weekDetails.addEntry(1233, projectNumber, activityCode, date, entryDescription, hours, "", submitted, approved,
                false, projectName, customerName, activityDescription);
        System.out.println(JSONBuilder.createFromWeekDetails(weekDetails, periodDetails, normTimeDetails).toJSONString());
    }

    @Test
    public void testGeneratingDailyHoursJSONObject() throws Exception {
        HourRegistration hour = new HourRegistration(123, projectNumber, activityCode, date, entryDescription, hours,
                "", submitted, approved, false, projectName, customerName, activityDescription);
        HourRegistration hour2 = new HourRegistration(1232, projectNumber, activityCode, date, entryDescription, hours,
                "", submitted, approved, false, projectName, customerName, activityDescription);
        List<HourRegistration> hours = new ArrayList<HourRegistration>();
        hours.add(hour);
        hours.add(hour2);
        System.out.println(JSONBuilder.createFromHours(hours, null).toString());
    }
}
