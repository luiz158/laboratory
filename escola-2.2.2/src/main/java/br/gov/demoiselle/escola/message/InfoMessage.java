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
package br.gov.demoiselle.escola.message;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.Message;
import br.gov.frameworkdemoiselle.message.SeverityType;

public enum InfoMessage implements Message{
	
	DISCIPLINA_INSERIDA_OK("disciplina_inserir_ok"),
	DISCIPLINA_ALTERADA_OK("disciplina_alterar_ok"),
	DISCIPLINA_EXCLUIDA_OK("disciplina_excluir_ok"), 
	
	ALUNO_INSERIDO_OK("aluno_inserido_ok"),
	ALUNO_ALTERADO_OK("aluno_alterado_ok"),
	ALUNO_MATRICULADO_OK("aluno_matriculado_ok"),
	ALUNO_EXCLUIDO_OK("aluno_excluido_ok"),

	NOTA_INSERIDA_OK("nota_inserida_ok"),
	NOTA_ALTERADA_OK("nota_alterada_ok"),
	NOTA_EXCLUIDA_OK("nota_excluida_ok"),
	
	FUNCIONARIO_INSERIDO_OK("funcionario_inserida_ok"), 
	FUNCIONARIO_ALTERADO_OK("funcionario_alterado_ok"), 
	FUNCIONARIO_EXCLUIDO_OK("funcionario_excluido_ok"),
	
	USUARIO_INSERIDO_OK("usuario_inserido_ok"),
	USUARIO_ALTERADO_OK("usuario_alterado_ok"),
	USUARIO_EXCLUIDO_OK("usuario_excluido_ok"),
	
	TURMA_INSERIDA_OK("turma_inserida_ok"),
	TURMA_ALTERADA_OK("turma_alterada_ok"),
	TURMA_EXCLUIDA_OK("turma_excluida_ok"),
	
	PROFESSOR_INSERIDO_OK("professor_inserido_ok"),
	PROFESSOR_ALTERADO_OK("professor_alterado_ok"),
	PROFESSOR_EXCLUIDO_OK("professor_excluido_ok");
	
	
	private final DefaultMessage msg;
	
	private InfoMessage(String key) {
		this.msg = new DefaultMessage("{" + key + "}");
	}

	
	public SeverityType getSeverity() {
		return SeverityType.INFO;
	}
	
	@Override
	public String getText() {
		return msg.getText();
	}
	
	

}
