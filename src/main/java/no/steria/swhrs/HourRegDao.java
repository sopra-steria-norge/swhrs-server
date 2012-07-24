package no.steria.swhrs;

import java.util.List;


public interface HourRegDao {

	void beginTransaction();

	void endTransaction(boolean b);
	
	void saveHours(HourRegistration reg);
	
	List<HourRegistration> getAllHoursForDate(String person_id, String date);
	
	boolean validateUser(String username, String password);
	
	boolean deleteHourRegistration(String project_id);

	List<WeekRegistration> getWeekList(String userName, String dateFrom,
			String dateTo);
	
	boolean addFavourites(String username, String project_id, String activityCode);

	List<Projects> getProjects(String projectName);

	DatePeriod getPeriod(String userid, String date);

	boolean addHourRegistrations(String projectNumber, String activityCode,
			String workType, String date, String username, double hours,
			String description);

	

	List<UserFavourites> getUserFavourites(String userName);

	boolean addHourRegistrations(String projectNumber, String activityCode,
			String username, String workType, String date, double hours,
			String description, int submitted, int approved, int billable,
			int linenumber, int internalProject, int addNormTime,
			String departmentManager, String shortcutDimensionOneCode,
			String shortcutDimensionTwoCode, String resourceGroupNumber,
			int exportTieto, int notApproved, String notApprovedDescription,
			String notApprovedBy, String changedDate, String changedBy,
			String transferedTieto, int approvedByLMPM, int adjustFlexLimit);
	
}