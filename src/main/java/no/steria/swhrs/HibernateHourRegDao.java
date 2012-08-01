package no.steria.swhrs;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.context.ThreadLocalSessionContext;
import org.hibernate.criterion.Restrictions;

public class HibernateHourRegDao implements HourRegDao{

	private SessionFactory sessionFactory;
	
	public HibernateHourRegDao(String jndi) {
		Configuration configuration = new Configuration();
		configuration.setProperty(Environment.DATASOURCE, jndi);
		configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, ThreadLocalSessionContext.class.getName());
		configuration.addAnnotatedClass(HourRegistrationEntity.class);
		configuration.addAnnotatedClass(UserEntity.class);
		sessionFactory = configuration.buildSessionFactory();		
	}
	
	@Override
	public void beginTransaction() {
		session().beginTransaction();
	}

	@Override
	public void endTransaction(boolean b) {
		session().getTransaction().commit();
	}

	@Override
	public List<HourRegistration> getAllHoursForDate(String person_id, String date) {
		Criteria criteria = session().createCriteria(HourRegistrationEntity.class);
		criteria.add(Restrictions.and(Restrictions.eq("personId", person_id),Restrictions.eq("regDate", date)));
		List<HourRegistration> list = new LinkedList<HourRegistration>();
		return list;
	}
	
	@Override
	public boolean validateUser(String username, String password){
		Criteria crit = session().createCriteria(UserEntity.class);
		crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.eq("password", password));
		
		UserEntity user = (UserEntity)crit.uniqueResult();
		Long userid = user.getUserid();
		if(userid != null)return true;
		return false;
	}
	
	private Session session(){
		return sessionFactory.getCurrentSession();
	}


	@Override
	public boolean deleteHourRegistration(String projectID) {
		return false;
		// TODO Auto-generated method stub
	}

	public List<WeekRegistration> getWeekSummary(String week) {
		List<WeekRegistration> list = null;
		return list ;
	}



	@Override
	public List<WeekRegistration> getWeekList(String userName, String dateFrom,
			String dateTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addFavourites(String username, String project_id,
			String activityCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Projects> searchProjects(String projectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatePeriod getPeriod(String userid, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addHourRegistrations(String projectNumber,
			String activityCode, String workType, String date, String username,
			double hours, String description) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addHourRegistrations(String projectNumber,
			String activityCode, String workType, String date, String username,
			double hours, String description, int submitted, int approved,
			int billable, int linenumber, int internalProject, int addNormTime,
			String departmentManager, String shortcutDimensionOneCode,
			String shortcutDimensionTwoCode, String resourceGroupNumber,
			int exportTieto, int notApproved, String notApprovedDescription,
			String notApprovedBy, String changedDate, String changedBy,
			String transferedTieto, int approvedByLMPM, int adjustFlexLimit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserFavourites> getUserFavourites(String userName) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void deleteFavourite(String userid, String projectNumber,
			String activityCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRegistration(int taskNumber, double hours,
			String description) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updatePeriod(String username, int i, String fromDate,
			String toDate) {
		// TODO Auto-generated method stub
		
	}
}
