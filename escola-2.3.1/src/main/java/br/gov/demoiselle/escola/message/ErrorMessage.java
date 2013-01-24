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

public enum ErrorMessage implements Message{
	
	DISCIPLINA_INSERIR_NOK("disciplina_inserir_nok"), 
	DISCIPLINA_ALTERAR_NOK("disciplina_alterar_nok"),
	DISCIPLINA_EXCLUIR_NOK("disciplina_excluir_nok"),
	
	PROFESSOR_001("professor_001"),
	PROFESSOR_002("professor_002"),
	PROFESSOR_003("professor_003"),
	
	TURMA_001("turma_001"), 
	ALUNO_001("aluno_001"), 
	
	ESCOLA_001("escola_001"),
	ALUNO_002("aluno_002"),
	ALUNO_002_01("aluno_002_01"), 
	ALUNO_002_02("aluno_002_02"),
	
	NOTA_001("nota_001"),
	NOTA_001_01("nota_001_01"),
	NOTA_002("nota_002"),
	NOTA_003("nota_003"),
	NOTA_004("nota_004"),
	
	FUNCIONARIO_001("funcionario_001"),
	FUNCIONARIO_002("funcionario_002"),
	FUNCIONARIO_003("funcionario_003"),
	FUNCIONARIO_004("funcionario_004"),
	FUNCIONARIO_005("funcionario_005"), 
	FUNCIONARIO_006("funcionario_006"),
	FUNCIONARIO_007("funcionario_007"),
	
	BANCO_DADOS_001("banco_dados_001"),
	BANCO_DADOS_002("banco_dados_002"),
	
	USUARIO_001("usuario_001"), 
	USUARIO_002("usuario_002");
	
	
	private final DefaultMessage msg;
	
	private ErrorMessage(String label) {
		this.msg = new DefaultMessage("{" + label + "}");
	}
	
	public SeverityType getSeverity() {
		return SeverityType.ERROR;
	}
	
	@Override
	public String getText() {
		return msg.getText();
	}

}
