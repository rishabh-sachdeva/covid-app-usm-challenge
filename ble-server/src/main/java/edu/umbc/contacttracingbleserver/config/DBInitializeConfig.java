package edu.umbc.contacttracingbleserver.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			//statement.execute("DROP TABLE IF EXISTS InfectedDetails");
			
			String createQuery = "CREATE TABLE IF NOT EXISTS InfectedDetails("+
								"public_key BLOB Primary Key, "+
								"time_stamp timestamp not null)";
			System.out.println("Executing query - "+createQuery);
			//statement.executeUpdate(createQuery);
			statement.close();
			connection.close();
			
			
	
		}catch(SQLException ex) {
			ex.printStackTrace();
		}

	}

}
