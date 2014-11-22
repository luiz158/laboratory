package br.gov.serpro.jpa_jdbc_sample.persistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.serpro.jpa_jdbc_sample.domain.Bookmark;
import br.gov.serpro.jpa_jdbc_sample.domain.Log;

@PersistenceController
public class LogDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Connection connection;

	public void insert(Bookmark bookmark, String operacao) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into log (datahora, operacao, registro) ");
		sql.append("values (now(), ?, ?)");

		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, operacao);
			pstmt.setString(2, bookmark.getId() + " # " + bookmark.getDescription() + " # " + bookmark.getLink());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			throw new DemoiselleException("Não foi possível gravar o log de operação");
		}

	}
	
	public List<Log> findAll() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from log");

		PreparedStatement pstmt = connection.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();
		List<Log> result = new ArrayList<Log>();

		while (rs.next()) {
			Log log = new Log();
			log.setId(rs.getLong("id"));
			log.setDatahora(rs.getString("datahora"));
			log.setOperacao(rs.getString("operacao"));
			log.setRegistro(rs.getString("registro"));
			result.add(log);
		}

		rs.close();
		pstmt.close();
		return result;
	}	

}
