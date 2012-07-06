package no.steria.swhrs;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import no.steria.swhrs.Person;

import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.context.ThreadLocalSessionContext;
import org.hsqldb.jdbc.JDBCDataSource;
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
		HourRegistrationEntity newEntity = HourRegistrationEntity.createRegistration(reg);
		session().save(newEntity);
	}

	@Override
	public List<HourRegistration> getHours(int person_id, LocalDate date) {
		//This actually just gets all registrations in the database, so the parameters are useless atm
		List<HourRegistrationEntity> entityList = session().createCriteria(HourRegistrationEntity.class).list();
		
		//This is very ugly, but it's ok for now since all of this is only for local testing
		List<HourRegistration> list = new ArrayList<HourRegistration>();
		for (HourRegistrationEntity hr: entityList) {
			list.add(hr.createHourRegistrationFromEntity());
		}
		return list;
	}
	
	private Session session(){
		return sessionFactory.getCurrentSession();
	}
	
}
