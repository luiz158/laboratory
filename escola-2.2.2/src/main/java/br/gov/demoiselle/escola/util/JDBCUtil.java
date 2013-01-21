package br.gov.demoiselle.escola.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.inject.Inject;

import br.gov.demoiselle.escola.config.JDBCConfig;
import br.gov.demoiselle.escola.exception.EscolaException;
import br.gov.frameworkdemoiselle.message.DefaultMessage;

public class JDBCUtil {
	
	@Inject
	private JDBCConfig jdbcConfig;
	
	public Connection getConnection(){
		Connection result = null;
		
		result = openConnectionURL();
		
		if (result == null)
			throw new EscolaException(new DefaultMessage("Error on open JDBC Connection! - Connection is null, plese verify the properties of connections."));
		
		return result;
		
	}
	
	private Connection openConnectionURL() {
		Connection connection = null;

		if (connection == null && jdbcConfig.getUrl() != null) {
			try {
				Class.forName(jdbcConfig.getDriver());
				if (jdbcConfig.getUser() != null && jdbcConfig.getUser().length() > 0 
						&& jdbcConfig.getPass() != null && jdbcConfig.getPass().length() > 0)
					connection = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUser(), jdbcConfig.getPass());
				else
					connection = DriverManager.getConnection(jdbcConfig.getUrl());
			} 
			catch(SQLException ex) {
				throw new EscolaException(new DefaultMessage("Error on open JDBC connection.", ex));
			} 
			catch(ClassNotFoundException ex) {
				throw new EscolaException(new DefaultMessage("Without class driver.", ex));
			} 
		}
		
		return connection;
	}

}
