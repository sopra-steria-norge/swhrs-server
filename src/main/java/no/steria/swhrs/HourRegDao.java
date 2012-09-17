package no.steria.swhrs;

import org.joda.time.DateTime;

import java.util.List;


public interface HourRegDao {

	/**
	 * This method attempts to establish a connection to the data source
	 */
	void beginTransaction();

	/**
	 * This method commits all the new changes, and releases the connection
     */
	void endTransaction();
	
	/**
	 * This method returns a list of hour registrations done on a specific date by a given user
	 * @param userId The userId, the initials of the user
	 * @param date The date is sent as a String with the format (yyyy-MM-dd)
	 * @return returns a List of HourRegistration objects
	 */
	List<HourRegistration> getAllHoursForDate(String userId, String date);
	
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
	 * This method takes in the task number and deletes it from the database
	 * @param taskNumber The taskNumber is sent as a String and represent a unique registration
	 * @return returns a boolean to confirm the deletion
	 */
    void deleteHourRegistration(String userId, String taskNumber);

	/**
	 * This method returns a list of hour registrations done in a specific period
	 * @param userId The userId is sent as a String(User initials)
	 * @param dateFrom The dateFrom is sent as a String with the format (yyyy-MM-dd)
	 * @param dateTo The dateTo is sent as a String with the format (yyyy-MM-dd)
	 * @return returns a List of WeekRegistration objects
	 */
	List<WeekRegistration> getWeekList(String userId, String dateFrom, String dateTo);
	
	/**
	 * This method adds new projects to the users favourite list
	 * @param userId The userId is sent as a String(User initials)
	 * @param project_id The project_id is sent as a String
	 * @param activityCode The activityCode is sent as a String
	 * @return returns a boolean to confirm the action
	 */
	boolean addFavourites(String userId, String project_id, String activityCode);

	/**
	 * This method searches for projects in the database, and returns a list of projects
	 * @param projectName The projectName is sent as a String and will search for both projectID and 
	 * project description. The projectID must be complete, but the project description can be incomplete.
	 * @return returns a list of Projects objects with affiliation to the search string
	 */
	List<Projects> searchProjects(String projectName);

	/**
	 * This method returns a work period for a specific user when given a date
	 * @param userId The userId is sent as a String(User initials)
	 * @param date The date is sent as a String with the format (yyyy-MM-dd)
	 * @return returns a DatePeriod object that contains a periods description as well as the start- and end-date
	 */
	DatePeriod getPeriod(String userId, String date);

	/**
	 * This method returns a List of the users favourite projects
	 * @param userId The userId is sent as a String(User initials)
	 * @return returns a list of UserFavourites objects
	 */
	List<UserFavourites> getUserFavourites(String userId);

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
    public Integer addHourRegistrations(String loggedInUser, String registeringForUser, String projectNumber,
                                        String activity, DateTime date, double hours, boolean isBillable,
                                        String workType, String description, boolean bypassChecks);


	/**
	 * This method removes a favourite from the users favourite list
	 * @param userId The userId is sent as a String(User initials)
	 * @param projectNumber The project_id is sent as a String
	 * @param activityCode The activity code is sent as a String
	 */
	void deleteFavourite(String userId, String projectNumber, String activityCode);

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
	void updateRegistration(String userId, String taskNumber, String projectNumber, String activity,
                            DateTime date, double hours, boolean isBillable, String workType,
                            String description);

	/**
	 * This method updates the time period of the user to either be submitted or reopened.
	 * The param option decides whether it should be submitted or reopened.
	 * @param userId The userId is sent as a String(User initials)
	 * @param option The option is sent as an Integer, and is 1 if you want to submit or 0 if you want to reopen 
	 * @param dateFrom The dateFrom is sent as a String with the format (yyyy-MM-dd)
	 * @param dateTo The dateTo is sent as a String with the format (yyyy-MM-dd)
	 */
	void updatePeriod(String userId, int option, String dateFrom, String dateTo);

	/**
	 * This method returns a list with norm time for each day
	 * @param username The username is sent as a String(User initials)
	 * @return list with NormTime objects
	 */
	List<NormTime> getNormTime(String username);

	
}