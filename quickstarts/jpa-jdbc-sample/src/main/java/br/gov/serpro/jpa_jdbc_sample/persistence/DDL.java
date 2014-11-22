package br.gov.serpro.jpa_jdbc_sample.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;

public class DDL {

	@Inject
	private Connection connection;

	@Startup
	@Transactional
	public void dropAndCreate() throws Exception {
		dropTableIfExists("log");
		createTableLog();
	}

	private void dropTableIfExists(String tableName) throws Exception {
		PreparedStatement pstmt;

		String sql = "select (count(*) > 0) from information_schema.tables where table_name = ?; ";
		pstmt = connection.prepareStatement(sql.toString());
		pstmt.setString(1, tableName);

		ResultSet rs = pstmt.executeQuery();
		rs.next();
		boolean exists = rs.getBoolean(1);
		rs.close();
		pstmt.close();

		if (exists) {
			pstmt = connection.prepareStatement("DROP TABLE " + tableName  + "; ");
			pstmt.execute();
			pstmt.close();
		}
	}

	private void createTableLog() throws Exception {
		StringBuffer sql = new StringBuffer();

		sql.append("CREATE TABLE log ( ");
		sql.append("	id identity NOT NULL, ");
		sql.append("	datahora timestamp NOT NULL, ");
		sql.append("	operacao varchar(255) NOT NULL, ");
		sql.append("	registro varchar(255) NOT NULL, ");
		sql.append("CONSTRAINT log_pk PRIMARY KEY (id) ");
		sql.append("); ");

		PreparedStatement pstmt = connection.prepareStatement(sql.toString());
		pstmt.execute();
		pstmt.close();
	}
}
