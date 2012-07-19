package no.steria.swhrs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class AbstractJdbcRepository {
	
	private final DataSource dataSource;
	private Connection connection;
	
	public AbstractJdbcRepository(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	protected PreparedStatement prepareStatement(String query) throws SQLException{
		return connection.prepareStatement(query);
	}
}
