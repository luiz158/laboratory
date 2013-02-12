package br.gov.demoiselle.escola.util;

import java.sql.Connection;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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

		try{
			Context initCtx = new InitialContext();
	
			DataSource dataSource = (DataSource) initCtx.lookup(jdbcConfig.getJndi());
		
			connection = dataSource.getConnection();
		}
		catch(Exception e){
			throw new EscolaException(new DefaultMessage(e.getMessage(), e));
		}
		
		return connection;
	}

}
