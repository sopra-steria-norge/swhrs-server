package no.steria.swhrs;

import java.util.List;

import no.steria.swhrs.Person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.context.ThreadLocalSessionContext;
import org.joda.time.LocalDate;

public class HibernateHourRegDao implements HourRegDao{

	private SessionFactory sessionFactory;
	private Person loggedInUser = Person.createDummyPerson("Ola Nordmann");
	
	public HibernateHourRegDao(String jndi) {
		Configuration configuration = new Configuration();
		configuration.setProperty(Environment.DATASOURCE, jndi);
		configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, ThreadLocalSessionContext.class.getName());
		//configuration.addAnnotatedClass(Person.class);
		configuration.addAnnotatedClass(HourRegistration.class);
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
	public void saveHours(HourRegistration reg) {
		session().save(reg);
	}

	@Override
<<<<<<< HEAD:src/main/java/no/steria/swhrs/HibernateHourRegDao.java
	public List<HourRegistration> getHours(Long person_id, LocalDate date) {
		return session().createCriteria(HourRegistration.class).list();
=======
	public HourRegistration getHours(Long person_id, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
>>>>>>> 79cc1267e29ca047aa9dcfd2de3519ec71775881:src/main/java/no/steria/swhrs/HibernatePersonDao.java
	}
	
	private Session session(){
		return sessionFactory.getCurrentSession();
	}
	
}
