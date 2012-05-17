package br.org.frameworkdemoiselle.contas.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import org.slf4j.Logger;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.org.frameworkdemoiselle.contas.config.ContasConfig;
import br.org.frameworkdemoiselle.contas.domain.Compromisso;
import br.org.frameworkdemoiselle.contas.exception.CompromissoException;
import br.org.frameworkdemoiselle.contas.persistence.CompromissoDAO;

@BusinessController
public class CompromissoBC extends DelegateCrud<Compromisso, Long, CompromissoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger logger;
	
	@Inject
	private ResourceBundle bundle;
	
	@Inject
	private ContasConfig config;
	
	@Inject
	private MessageContext messageContext;
	
	@Override
	@Transactional
	public void insert (Compromisso compromisso){
		// Verificar se não está duplicado e se o valor é permitido
		if (estaDuplicado(compromisso) || compromisso.getValorCompromisso() < config.getValorMinimo()) {
			logger.warn(bundle.getString("compromisso.erro", config.getValorMinimo()) + compromisso.getNomeCompromisso());
			throw new CompromissoException();
		}
		atualizarAtraso(compromisso);
		super.insert(compromisso);
		logger.info(bundle.getString("compromisso-insert-ok", compromisso.getNomeCompromisso()));
		messageContext.add(bundle.getString("compromisso-insert-ok", compromisso.getNomeCompromisso()));		
	}
	
	@Override
	@Transactional
	public void update(Compromisso compromisso){
		atualizarAtraso(compromisso);
		// Verificar se respeita o valor mínimo
		if (compromisso.getValorCompromisso() < config.getValorMinimo()) {
			logger.warn(bundle.getString("compromisso.erro", config.getValorMinimo()) + compromisso.getNomeCompromisso());
			throw new CompromissoException();
		}
		super.update(compromisso);
		logger.info(bundle.getString("compromisso-update-ok", compromisso.getNomeCompromisso()));
		messageContext.add(bundle.getString("compromisso-update-ok", compromisso.getNomeCompromisso()));
	}
	
	private void atualizarAtraso(Compromisso compromisso){
		
				
		if (compromisso.getDataPagamento() != null && compromisso.getDataVencimento() != null){
			// verificar se está em atraso.   
			try {
				String sDataPagamento = new SimpleDateFormat("dd/MM/yyyy").format(compromisso.getDataPagamento());
				String sDataCompromisso = new SimpleDateFormat("dd/MM/yyyy").format(compromisso.getDataVencimento());
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date dataPagamento = format.parse(sDataPagamento);
				Date dataCompromisso = format.parse(sDataCompromisso);  
				if (dataCompromisso.before(dataPagamento)){
					compromisso.setAtrasado(true);
				}
			} catch (ParseException e) {
				logger.warn(bundle.getString("erro.imprevisto",e.getMessage()));
				throw new CompromissoException();
			}			
		}		

	}
	
	public boolean estaDuplicado(Compromisso compromisso) {
		return findAll().contains(compromisso);
	}
	
	
	public void carregarCompromissos(){
		if (findAll().isEmpty()) {
			insert(new Compromisso ("Conta Luz", new Date(), null, new Long(11), null));
			insert(new Compromisso ("Conta Água", new Date(), null, new Long(22), null));
		}
	
	}
	
	@ExceptionHandler
	public void tratar(CompromissoException e){			
		throw new CompromissoException(bundle.getString("compromisso.erro",config.getValorMinimo()));
	}
	
}
