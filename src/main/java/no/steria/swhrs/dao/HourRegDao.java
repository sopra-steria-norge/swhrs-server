package no.steria.swhrs.dao;

import no.steria.swhrs.domain.*;
import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.List;


public interface HourRegDao {

    /**
     * This method take it hour registrations on a specific day on a specific project for a specific resource.
     * @param loggedInUser String
     * @param registeringForUser String
     * @param projectNumber String
     * @param activity String
     * @param date Date
     * @param hours String
     * @param isBillable boolean
     * @param workType String
     * @param description String
     * @param bypassChecks boolean
     * @return The entry id in the database if successful.
     */
    Integer addHourRegistrations(String loggedInUser, String registeringForUser, String projectNumber,
                                        String activity, DateTime date, double hours, boolean isBillable,
                                        String workType, String description, boolean bypassChecks);

    /**
     * This method updates the number of hours and description logged on a specific
     * hour registration
     * @param userId string
     * @param taskNumber string
     * @param projectNumber string
     * @param activity string
     * @param date datetime
     * @param hours double
     * @param isBillable boolean
     * @param workType string
     * @param description string
     */
    void updateHourRegistration(String userId, String taskNumber, String projectNumber, String activity,
                                DateTime date, double hours, boolean isBillable, String workType,
                                String description);

    /**
     * This method takes in the task number and deletes it from the database
     * @param taskNumber The taskNumber is sent as a String and represent a unique registration
     */
    void deleteHourRegistration(String userId, String taskNumber);

    /**
     * This method adds new projects to the users favourite list
     * @param userId The userId is sent as a String(User initials)
     * @param project_id The project_id is sent as a String
     * @param activityCode The activityCode is sent as a String
     * @return returns a boolean to confirm the action
     */
    boolean addFavourites(String userId, String project_id, String activityCode);

    /**
     * This method returns a List of the users favourite projects
     * @param userId The userId is sent as a String(User initials)
     * @return returns a list of UserFavourites objects
     */
    List<UserFavourites> getUserFavourites(String userId);

    /**
     * This method removes a favourite from the users favourite list
     * @param userId The userId is sent as a String(User initials)
     * @param projectNumber The project_id is sent as a String
     * @param activityCode The activity code is sent as a String
     */
    void deleteFavourite(String userId, String projectNumber, String activityCode);

	/**
	 * This method returns a list of hour registrations done on a specific date by a given user
	 * @param userId The userId, the initials of the user
	 * @param date The date is sent as a String with the format (yyyy-MM-dd)
	 * @return returns a List of HourRegistration objects
	 */
	List<HourRegistration> getAllHoursForDate(String userId, DateTime date);

	/**
	 * This method returns a list of hour registrations done in a specific period
	 * @param loggedInUser The userId who is logged in
	 * @param registeringForUser The user which one does the changes on
	 * @param viewer EMP or BOSS
     * @param startDate start date of the period
	 * @return returns week details
	 */
	WeekDetails getWeekList(String loggedInUser, String registeringForUser, String viewer, DateTime startDate);

	/**
	 * This method searches for projects in the database, and returns a list of projects
	 * @param projectName The projectName is sent as a String and will search for both projectID and 
	 * project description. The projectID must be complete, but the project description can be incomplete.
	 * @return returns a list of ProjectDetail objects with affiliation to the search string
	 */
	List<ProjectDetail> searchProjects(String projectName);

    /**
     * This method submit hours in a time period
     * @param loggedInUser The user who is logged in
     * @param registeringForUser The user who is having his hour-period submitted
     * @param dayInPeriod A point of time inside the period
     */
    void submitHours(String loggedInUser, String registeringForUser, DateTime dayInPeriod) throws SQLException;

    /**
     * This method reopens hours in a time period for editing if possible
     * @param loggedInUser The user who is logged in
     * @param registeringForUser The user who is having his hour-period reopened
     * @param dayInPeriod A point of time inside the period
     */
    void reopenHours(String loggedInUser, String registeringForUser, DateTime dayInPeriod);

    /**
     * This method validate the user by both username and password.
     *
     *
     * @param userId The userId is sent as a String(User initials)
     * @param password The password is sent as a "salt_digest"
     * @return returns a boolean to give or deny access.
     */
    User findUser(String userId, Password password);

    /**
     * This method returns a work period for a specific user when given a date
     * @param userId The userId is sent as a String(User initials)
     * @param date Yodatime date representation
     * @return returns a PeriodDetails object that contains a periods description as well as the start- and end-date
     */
    PeriodDetails getPeriodDetails(String userId, DateTime date);

    /**
     * This method returns norm time detail for a specific user when given a start-date
     * @param loggedInUser The userId is sent as a String(User initials)
     * @param userId The userId is sent as a String(User initials)
     * @param date Yodatime date representation - have to be the start-date
     * @return returns NormTimeDetails object that contains a norm details for a period
     */
    NormTimeDetails getNormTimeDetails(String loggedInUser, String userId, DateTime date);
}