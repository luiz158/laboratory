package persistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import domain.Book;

@PersistenceController
public class BookDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Para utilizar uma conexão JDBC em seu código, basta injetá-la. 
	 * O Demoiselle se encarregará de produzir o tipo adequado de conexão.
	 */
	@Inject
	private Connection connection;

	@Transactional
	public void insert(Book book) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into book (id, description) ");
		sql.append("values (?, ?)");

		PreparedStatement pstmt = connection.prepareStatement(sql.toString());
		pstmt.setInt(1, book.getId());
		pstmt.setString(2, book.getDescription());

		pstmt.execute();
		pstmt.close();
	}

	
	@Transactional
	public void delete(int id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from book where id = ?");

		PreparedStatement pstmt = connection.prepareStatement(sql.toString());
		pstmt.setInt(1, id);

		pstmt.execute();
		pstmt.close();
	}

	@Transactional
	public void update(Book book) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update book set ");
		sql.append("description = ?");
		sql.append("where id = ? ");

		PreparedStatement pstmt = connection.prepareStatement(sql.toString());
		pstmt.setString(1, book.getDescription());
		pstmt.setInt(2, book.getId());

		pstmt.execute();
		pstmt.close();
	}

	public Book findById(int id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from book where id = ?");

		PreparedStatement pstmt = connection.prepareStatement(sql.toString());
		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();
		Book result = null;

		if (rs.next()) {
			result = new Book();
			result.setId(rs.getInt("id"));
			result.setDescription(rs.getString("description"));
		}

		rs.close();
		pstmt.close();

		return result;
	}

	public List<Book> findAll() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from book");

		PreparedStatement pstmt = connection.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();
		List<Book> result = new ArrayList<Book>();

		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setDescription(rs.getString("description"));
			result.add(book);
		}

		rs.close();
		pstmt.close();
		return result;
	}

}
