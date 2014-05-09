package br.gov.serpro.catalogo.bussiness;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.catalogo.entity.GrupoHistorico;
import br.gov.serpro.catalogo.entity.GrupoHistorico.OPERACAO;
import br.gov.serpro.catalogo.event.GrupoEvent;
import br.gov.serpro.catalogo.event.GrupoEvent.ATUALIZAR;
import br.gov.serpro.catalogo.event.GrupoEvent.CRIAR;
import br.gov.serpro.catalogo.event.GrupoEvent.EXCLUIR;
import br.gov.serpro.catalogo.event.GrupoEvent.INSERIR_USUARIO;
import br.gov.serpro.catalogo.persistence.GrupoHistoricoDAO;

@BusinessController
public class GrupoHistoricoBC {
	@Inject
	private Logger logger;
	
	@Inject
	private GrupoHistoricoDAO grupoHistoricoDAO;
	
	public void grupoCriar(@Observes @CRIAR GrupoEvent e){
        logger.debug("GrupoEvent CRIAR: " + e.getGrupo().getNome());
        grupoHistoricoDAO.insert(new GrupoHistorico(e.getGrupo(),OPERACAO.CRIAR));
    }
	
	public void grupoSalvo(@Observes @ATUALIZAR GrupoEvent e){
        logger.debug("GrupoEvent ATUALIZAR: " + e.getGrupo().getNome());
        grupoHistoricoDAO.insert(new GrupoHistorico(e.getGrupo(),OPERACAO.ATUALIZAR));     
    }	

	public void grupoExcluido(@Observes @EXCLUIR GrupoEvent e){
        logger.debug("GrupoEvent @EXCLUIR: " + e.getGrupo().getNome());
        grupoHistoricoDAO.insert(new GrupoHistorico(e.getGrupo(),OPERACAO.EXCLUIR));     
    }
	
	public void grupoInserido(@Observes @INSERIR_USUARIO GrupoEvent e){
        logger.debug("GrupoEvent @INSERIR_USUARIO: " + e.getUser().getName());
        grupoHistoricoDAO.insert(new GrupoHistorico(e.getListaGrupos(),OPERACAO.INSERIR_USUARIO,e.getUser()));     
    }
}