package br.gov.serpro.catalogo.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseHistorico;
import br.gov.serpro.catalogo.entity.FaseHistorico.OPERACAO;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.event.FaseEvent;
import br.gov.serpro.catalogo.event.FaseEvent.ATUALIZAR;
import br.gov.serpro.catalogo.event.FaseEvent.CRIAR;
import br.gov.serpro.catalogo.event.FaseEvent.EXCLUIR;
import br.gov.serpro.catalogo.event.FaseEvent.FINALIZAR;
import br.gov.serpro.catalogo.persistence.FaseHistoricoDAO;

@BusinessController
public class FaseHistoricoBC {

	@Inject
	private Logger logger;
	
	@Inject
	private FaseHistoricoDAO faseHistoricoDAO;
	
	@Inject
	private FaseBC faseBC;
	
	public void faseCriar(@Observes @CRIAR FaseEvent e){
        logger.debug("FaseEvent CRIAR: " + e.getFase().getFase());
        faseHistoricoDAO.insert(new FaseHistorico(e.getFase(),OPERACAO.CRIAR));
    }
	
	public void faseSalva(@Observes @ATUALIZAR FaseEvent e){
        logger.debug("FaseEvent ATUALIZAR: " + e.getFase().getFase());
        faseHistoricoDAO.insert(new FaseHistorico(e.getFase(),OPERACAO.ATUALIZAR));     
    }	

	public void faseFinalizada(@Observes @FINALIZAR FaseEvent e){
		logger.debug("FinalizarFaseEvent: " + e.getFase().getFase());
        OPERACAO operacao = (e.getFase().getSituacao().equals(Situacao.APROVADO))?OPERACAO.APROVAR:OPERACAO.REPROVAR;
    	faseHistoricoDAO.insert(new FaseHistorico(e.getFase(),operacao));
    }
	
	public void faseExcluida(@Observes @EXCLUIR FaseEvent e){
        logger.debug("FaseEvent @EXCLUIR: " + e.getFase().getFase());
        faseHistoricoDAO.insert(new FaseHistorico(e.getFase(),OPERACAO.EXCLUIR));     
    }
	
	public List<FaseHistorico> obterHistorico(Long id) {
		List<Fase> fases = faseBC.obterCadeiaDasFasesComExcluidos(id);		
		List<Long> ids = new ArrayList<Long>();
		for (Fase fase : fases) {
			ids.add(fase.getId());
		}
		return faseHistoricoDAO.obterHistorico(ids);
	}
	
	
}
