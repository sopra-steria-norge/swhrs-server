package no.steria.swhrs.domain;

import no.steria.swhrs.domain.Password;

public class User {
	
	private String username;
	private Password password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(Password password) {
		this.password = password;
    }
}
