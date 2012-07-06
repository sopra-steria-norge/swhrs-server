package no.steria.swhrs;

public class Parametere {
	private Parametere() {
		throw new IllegalArgumentException("Ikke instans");
	}
	
	public static final String DB_JNDI = "jdbc/registerHoursDS";
}
