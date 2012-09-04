package no.steria.swhrs;

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
	 * @param userid The userid, the initials of the user  
	 * @param date The date is sent as a String with the format (yyyy-MM-dd)
	 * @return returns a List of HourRegistration objects
	 */
	List<HourRegistration> getAllHoursForDate(String userid, String date);
	
	/**
	 * This method validate the user by both username and password.
	 *
     *
     * @param userid The userid is sent as a String(User initials)
     * @param password The password is sent as a "salt_digest"
     * @return returns a boolean to give or deny access.
	 */
	User findUser(String userid, Password password);
	
	/**
	 * This method takes in the task number and deletes it from the database
	 * @param taskNumber The taskNumber is sent as a String and represent a unique registration
	 * @return returns a boolean to confirm the deletion
	 */
	boolean deleteHourRegistration(String taskNumber);

	/**
	 * This method returns a list of hour registrations done in a specific period
	 * @param userid The userid is sent as a String(User initials)
	 * @param dateFrom The dateFrom is sent as a String with the format (yyyy-MM-dd)
	 * @param dateTo The dateTo is sent as a String with the format (yyyy-MM-dd)
	 * @return returns a List of WeekRegistration objects
	 */
	List<WeekRegistration> getWeekList(String userid, String dateFrom,
			String dateTo);
	
	/**
	 * This method adds new projects to the users favourite list
	 * @param userid The userid is sent as a String(User initials)
	 * @param project_id The project_id is sent as a String
	 * @param activityCode The activitycode is sent as a String
	 * @return returns a boolean to confirm the action
	 */
	boolean addFavourites(String userid, String project_id, String activityCode);

	/**
	 * This method searches for projects in the database, and returns a list of projects
	 * @param projectName The projectName is sent as a String and will search for both projectID and 
	 * project description. The projectID must be complete, but the project description can be incomplete.
	 * @return returns a list of Projects objects with affiliation to the search string
	 */
	List<Projects> searchProjects(String projectName);

	/**
	 * This method returns a work period for a specific user when given a date
	 * @param userid The userid is sent as a String(User initials)
	 * @param date The date is sent as a String with the format (yyyy-MM-dd)
	 * @return returns a DatePeriod object that contains a periods description as well as the start- and end-date
	 */
	DatePeriod getPeriod(String userid, String date);

	/**
	 * This method returns a List of the users favourite projects
	 * @param userid The userid is sent as a String(User initials)
	 * @return returns a list of UserFavourites objects
	 */
	List<UserFavourites> getUserFavourites(String userid);

	
	/**
	 * This method adds an hour registration to a specific user
	 * @param projectNumber
	 * @param activityCode
	 * @param username
	 * @param workType
	 * @param date
	 * @param hours
	 * @param description
	 * @param submitted
	 * @param approved
	 * @param billable
	 * @param linenumber
	 * @param internalProject
	 * @param addNormTime
	 * @param departmentManager
	 * @param shortcutDimensionOneCode
	 * @param shortcutDimensionTwoCode
	 * @param resourceGroupNumber
	 * @param exportTieto
	 * @param notApproved
	 * @param notApprovedDescription
	 * @param notApprovedBy
	 * @param changedDate
	 * @param changedBy
	 * @param transferedTieto
	 * @param approvedByLMPM
	 * @param adjustFlexLimit
	 * @return
	 */
	boolean addHourRegistrations(String projectNumber, String activityCode,
			String username, String workType, String date, double hours,
			String description, int submitted, int approved, int billable,
			int linenumber, int internalProject, int addNormTime,
			String departmentManager, String shortcutDimensionOneCode,
			String shortcutDimensionTwoCode, String resourceGroupNumber,
			int exportTieto, int notApproved, String notApprovedDescription,
			String notApprovedBy, String changedDate, String changedBy,
			String transferedTieto, int approvedByLMPM, int adjustFlexLimit);
	

	boolean addHourRegistrations(String projectNumber, String activityCode,
			String workType, String date, String username, double hours,
			String description);


	/**
	 * This method removes a favourite from the users favourite list
	 * @param userid The userid is sent as a String(User initials)
	 * @param projectNumber The project_id is sent as a String
	 * @param activityCode The activity code is sent as a String
	 */
	void deleteFavourite(String userid, String projectNumber,
			String activityCode);

	/**
	 * This method updates the number of hours and description logged on a specific 
	 * hour registration 
	 * @param taskNumber The task number is sent as an Integer
	 * @param hours The hours is sent as a Double
	 * @param description The description is sent as a String
	 */
	void updateRegistration(int taskNumber, double hours, String description);

	/**
	 * This method updates the time period of the user to either be submitted or reopened.
	 * The param option decides whether it should be submitted or reopened.
	 * @param userid The userid is sent as a String(User initials)
	 * @param option The option is sent as an Integer, and is 1 if you want to submit or 0 if you want to reopen 
	 * @param dateFrom The dateFrom is sent as a String with the format (yyyy-MM-dd)
	 * @param dateTo The dateTo is sent as a String with the format (yyyy-MM-dd)
	 */
	void updatePeriod(String userid, int option, String dateFrom, String dateTo);

	/**
	 * This method returns a list with norm time for each day
	 * @param username The username is sent as a String(User initials)
	 * @return list with NormTime objects
	 */
	List<NormTime> getNormTime(String username);

	
}