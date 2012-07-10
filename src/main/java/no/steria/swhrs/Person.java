package no.steria.swhrs;

//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Person {
	private String full_name, username;
	//@SuppressWarnings("unused")
	//@Id
	//@GeneratedValue
	private Long id;
	
	public static Person createDummyPerson(String name) {
		Person person = new Person();
		person.full_name = name;
		person.id = new Long(1);
		return person;
	}

	public Long getId() {
		return id;
	}
}
