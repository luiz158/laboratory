 /* 
 * Demoiselle Sample
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 * 
 * Demoiselle Sample is a set of open source Java EE project samples using
 * the Demoiselle Framework
 *   
 * Demoiselle Sample is released under the terms of the GPL license 3
 * http://www.gnu.org/licenses/gpl.html  GPL License 3
 *   
 * This file is part of Demoiselle Sample.
 * 
 * Demoiselle Sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3.
 * 
 * Demoiselle Sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Demoiselle - Sample.  If not, see <http://www.gnu.org/licenses/>
 */ 
package br.gov.demoiselle.escola.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Funcionario;
import br.gov.demoiselle.escola.exception.EscolaException;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.demoiselle.escola.util.JDBCUtil;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@Alternative
public class FuncionarioDAOJDBC implements IFuncionarioDAO{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private JDBCUtil jdbcUtil;

	/**
	 * Lista todos os funcionarios
	 */
	public List<Funcionario> findAll() {
		List<Funcionario> result = new ArrayList<Funcionario>();
		PreparedStatement prepStmt = null;
		Connection con = null;
		try {
			con = jdbcUtil.getConnection();
			prepStmt = con.prepareStatement("SELECT * FROM Funcionario ORDER BY nome ASC");
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id_funcionario");
				String nome = rs.getString("nome");
				Date nascimento = rs.getDate("nascimento");
				String lotacao = rs.getString("lotacao");
				result.add(new Funcionario(id, nome, nascimento, lotacao));
			}
		} catch (SQLException e) {
			throw new EscolaException(ErrorMessage.FUNCIONARIO_005, e);
		} finally {
			closeStatement(prepStmt);
		}
		return result;
	}

	/**
	 * Verifica se existe um funcionario na base
	 */
	public boolean exists(Funcionario pojo) {
		return (load(pojo.getId()) != null);
	}

	/**
	 * Insere um funcionario
	 */
	public void insert(Funcionario pojo) {
		PreparedStatement prepStmt = null;
		Connection con = null;
		try {
			con = jdbcUtil.getConnection();
			String insertStatement = "INSERT INTO Funcionario (id_funcionario, nome, nascimento, lotacao) VALUES (null, ? , ? , ?)";
			prepStmt = con.prepareStatement(insertStatement);
			prepStmt.setString(1, pojo.getNome());
			if (pojo.getNascimento() != null ){
				prepStmt.setDate(2, (new java.sql.Date(pojo.getNascimento().getTime())));
			}
			else{
				prepStmt.setDate(2, null);
			}
			prepStmt.setString(3, pojo.getLotacao());
			prepStmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new EscolaException(ErrorMessage.FUNCIONARIO_001,	e);
		}
		finally {
			closeStatement(prepStmt);
		}
	}

	/**
	 * Remover um funcionario
	 */
	public void delete(Long id) {
		
		Connection con = null;
		PreparedStatement prepStmt = null;		
		try {
			con = jdbcUtil.getConnection();
			String insertStatement = "DELETE FROM Funcionario WHERE id_funcionario = ?";
			prepStmt = con.prepareStatement(insertStatement);
			prepStmt.setLong(1, id);
			prepStmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new EscolaException(ErrorMessage.FUNCIONARIO_003,	e);
		}
		finally {
			closeStatement(prepStmt);
		}
	}

	public void update(Funcionario pojo) {
		Connection con = null;
		PreparedStatement prepStmt = null;
		try {
			con = jdbcUtil.getConnection();
			String insertStatement = "UPDATE Funcionario SET id_funcionario=?, nome=?, nascimento=?, lotacao=? WHERE  id_funcionario = ?";
			prepStmt = con.prepareStatement(insertStatement);
			prepStmt.setLong(1, pojo.getId());
			prepStmt.setString(2, pojo.getNome());
			if (pojo.getNascimento() != null ){
				prepStmt.setDate(3, (new java.sql.Date(pojo.getNascimento().getTime())));
			}else{
				prepStmt.setDate(3, null);
			}
			prepStmt.setString(4, pojo.getLotacao());
			prepStmt.setLong(5, pojo.getId());
			prepStmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new EscolaException(ErrorMessage.FUNCIONARIO_001,	e);
		} 
		finally {
			closeStatement(prepStmt);
		}
	}
	
	/**
	 * Verifica se a conexão ao statemente existe e se está aberto e fecha
	 */
	private void closeStatement(Statement stmt){
		try {
			if (stmt != null){
				stmt.close();
			}
		} catch (SQLException e1) {					
			throw new EscolaException(ErrorMessage.FUNCIONARIO_001,	e1);
		}				
	}

	public Funcionario load(Long idFuncionario) {
		PreparedStatement prepStmt = null;
		Connection con = null;
		Funcionario retorno = null;
		try {
			con = jdbcUtil.getConnection();
			prepStmt = con.prepareStatement("SELECT * FROM Funcionario WHERE id_funcionario =?");			
			prepStmt.setLong(1, idFuncionario);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()){
				long id = rs.getLong("id_funcionario");
				String nome = rs.getString("nome");
				Date nascimento = rs.getDate("nascimento");
				String lotacao = rs.getString("lotacao");
				retorno = new Funcionario(id, nome, nascimento, lotacao);
			}else{
				return null;
			}			
		} 
		catch (SQLException e) {
			throw new EscolaException(ErrorMessage.FUNCIONARIO_007,	e);
		} 
		finally {
			closeStatement(prepStmt);
		}
		return retorno;
	}

	public List<Funcionario> filtrar(Funcionario pojo) {
		List<Funcionario> result = new ArrayList<Funcionario>();
		PreparedStatement prepStmt = null;
		Connection con = null;
		try {
			con = jdbcUtil.getConnection();
			prepStmt = con.prepareStatement("SELECT * FROM Funcionario WHERE nome like '%" + pojo.getNome() + "%' ORDER BY nome ASC");
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id_funcionario");
				String nome = rs.getString("nome");
				Date nascimento = rs.getDate("nascimento");
				String lotacao = rs.getString("lotacao");
				result.add(new Funcionario(id, nome, nascimento, lotacao));
			}
		} 
		catch (SQLException e) {
			throw new EscolaException(ErrorMessage.FUNCIONARIO_005,	e);
		} 
		finally {
			closeStatement(prepStmt);
		}
		return result;
	}


}
