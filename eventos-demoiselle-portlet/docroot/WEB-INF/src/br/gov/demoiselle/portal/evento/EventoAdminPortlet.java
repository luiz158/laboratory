package br.gov.demoiselle.portal.evento;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.pgxp.util.exception.PGXPException;
import br.gov.demoiselle.portal.evento.model.EventoConfiguracao;
import br.gov.demoiselle.portal.evento.model.EventoParticipante;
import br.gov.demoiselle.portal.evento.model.EventoParticipanteModel;
import br.gov.demoiselle.portal.evento.model.impl.EventoConfiguracaoImpl;
import br.gov.demoiselle.portal.evento.model.impl.EventoParticipanteImpl;
import br.gov.demoiselle.portal.evento.model.impl.EventoParticipanteModelImpl;
import br.gov.demoiselle.portal.evento.service.EventoConfiguracaoLocalServiceUtil;
import br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil;
import br.gov.demoiselle.portal.evento.util.CSV;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class AdminPortlet
 */
public class EventoAdminPortlet extends MVCPortlet {
	
	/**
	 * Atualiza os atributos de Incrição e Participação dos convidados do evento.
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 */
	public void updateEventoParticipante(ActionRequest request, ActionResponse response) throws SystemException, IOException{
		
		String [] inscricoes = ParamUtil.getParameterValues(request, "selectUserIncricaoCheckBox");
		String [] participantes = ParamUtil.getParameterValues(request, "selectUserParcipacaoCheckBox");

		popularParticipantes(request, inscricoes, participantes);
		
		sendRedirect(request, response);
		
	}
	
	/**
	 * Realiza o Upload do arquivo no formato CSV com os dados do convidado, preenche a entidade EventoParticipante e salva
	 * 
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 * @throws PGXPException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ParseException 
	 */
	public void uploadParticipantes(ActionRequest request, ActionResponse response) throws SystemException, IOException, ParseException, InstantiationException, IllegalAccessException, PGXPException{
		
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
		
		long eventoId = ParamUtil.getLong(uploadRequest, "eventoId");
		
		if(uploadRequest.getSize("fileName") == 0){
			SessionErrors.add(request, "error-upload-arquivo-vazio");
		}
		else{
			
			File arquivo = uploadRequest.getFile("fileName");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<EventoParticipanteModel> listaConvidados = new CSV().toEntity(br, EventoParticipanteModelImpl.class);
			
			Iterator<EventoParticipanteModel> itListaConvidados = listaConvidados.iterator();
			
			while(itListaConvidados.hasNext()){
				
				EventoParticipanteModel eventoParticipante = itListaConvidados.next();
				
				if(!eventoParticipante.getNome().isEmpty() && !eventoParticipante.getEmail().isEmpty()){
				
					if(!validacaoCamposImportacao(eventoParticipante.getNome(), eventoParticipante.getEmail())){
						SessionErrors.add(request, "error-arquivo-invalido");
						break;
					}
					
					if(!verificarParticipanteCadastroNoEvento(eventoId, eventoParticipante.getEmail())){
					
						popularEventoParticipante(request, (EventoParticipanteModel) eventoParticipante);
						
						eventoParticipante.setEventoId(eventoId);
						
						eventoParticipante.setConviteEnviado(Boolean.FALSE);
						eventoParticipante.setCertificadoImpresso(Boolean.FALSE);
						eventoParticipante.setInscricaoConfirmada(Boolean.FALSE);
						eventoParticipante.setParticipacaoConfirmada(Boolean.FALSE);
						
					}
					else{
						itListaConvidados.remove();
					}
				}
				else{
					SessionErrors.add(request, "error-arquivo-invalido");
					break;
				}
			}
			
			br.close();
			
			if(SessionErrors.isEmpty(request)){
				
				if(!listaConvidados.isEmpty()){
					
					Iterator<EventoParticipanteModel> it = listaConvidados.iterator();
					
					while(it.hasNext()){
						
						EventoParticipanteModel model = it.next();
						
						EventoParticipante eventoParticipante = new EventoParticipanteImpl();
						
						eventoParticipante.setEventoId(eventoId);
						eventoParticipante.setCompanyId(model.getCompanyId());
						eventoParticipante.setUserId(model.getUserId());
						eventoParticipante.setCreateDate(model.getCreateDate());
						
						eventoParticipante.setNome(model.getNome());
						eventoParticipante.setEmail(model.getEmail());
						eventoParticipante.setInstituicaoEmpresa(model.getInstituicaoEmpresa());
						
						EventoParticipanteLocalServiceUtil.addEventoParticipante(eventoParticipante);
					}
				
					SessionMessages.add(request, "success-upload");
				}
			}
			
		}
		
		sendRedirect(request, response);
		
	}
	
	/**
	 * Cadastra um novo particitante
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 */
	public void adicionarParticipanteAoEvento(ActionRequest request, ActionResponse response) throws SystemException, IOException{
		EventoParticipante eventoParticipante = popularEventoParticipante(request);
		
		if(!verificarParticipanteCadastroNoEvento(eventoParticipante.getEventoId(), eventoParticipante.getEmail())){
			EventoParticipanteLocalServiceUtil.addEventoParticipante(eventoParticipante);
		}
		else{
			SessionErrors.add(request, "error-pessoa-ja-cadastrada");
		}
		
		sendRedirect(request, response);
		
	}
	
	/**
	 * Cadastrar participantes da base de dados
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 * @throws PortalException 
	 */
	public void cadastrarParticipantesDaBase(ActionRequest request, ActionResponse response) throws SystemException, IOException, PortalException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		
		long eventoId = ParamUtil.getLong(request, "eventoId");
		String [] emails = ParamUtil.getParameterValues(request, "selectUserCheckBox");
		
		if(emails != null){
			
			for(String email : emails){
				
				if(!verificarParticipanteCadastroNoEvento(eventoId, email)){
				
					User usuario = UserLocalServiceUtil.getUserByEmailAddress(companyId, email);
				
					if(usuario != null){
					
						EventoParticipante eventoParticipante = new EventoParticipanteImpl();
						
						eventoParticipante.setNome(usuario.getFullName());
						eventoParticipante.setEmail(email);
						
						if(!usuario.getOrganizations().isEmpty()){
							eventoParticipante.setInstituicaoEmpresa(usuario.getOrganizations().get(0).getName());
						}
						
						eventoParticipante.setEventoId(eventoId);
						eventoParticipante.setCompanyId(companyId);
						eventoParticipante.setUserId(usuario.getUserId());
						eventoParticipante.setCreateDate(new Date());
						
						eventoParticipante.setConviteEnviado(Boolean.FALSE);
						eventoParticipante.setCertificadoImpresso(Boolean.FALSE);
						eventoParticipante.setInscricaoConfirmada(Boolean.FALSE);
						eventoParticipante.setParticipacaoConfirmada(Boolean.FALSE);
						
						EventoParticipanteLocalServiceUtil.addEventoParticipante(eventoParticipante);
					}
				}
				
			}
			
		}
		
		
	}
	
	/**
	 * Atualizar os dados do Participante como Nome, Instituição/Empresa e Data de Modificação
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 * @throws PortalException
	 */
	public void atualizarParticipanteEvento(ActionRequest request, ActionResponse response) throws SystemException, IOException, PortalException {
		
		long eventoParticipanteId = ParamUtil.getLong(request, "eventoParticipanteId");
		String nome = ParamUtil.getString(request, "nome");
		String instituicaoEmpresa = ParamUtil.getString(request, "instituicaoEmpresa");
		
		EventoParticipante eventoparticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoParticipanteId);
		eventoparticipante.setNome(nome);
		eventoparticipante.setInstituicaoEmpresa(instituicaoEmpresa);
		eventoparticipante.setModifiedDate(new Date());
		
		EventoParticipanteLocalServiceUtil.updateEventoParticipante(eventoparticipante);
		
	}
	
	/**
	 * Preenche os atributos do objeto EventoParticipante de acordo com os dados da request
	 * 
	 * @param request
	 * @return
	 */
	private EventoParticipante popularEventoParticipante(ActionRequest request){
		
		String nome = ParamUtil.getString(request, "nome");
		String email = ParamUtil.getString(request, "email");
		String instituicaoEmpresa = ParamUtil.getString(request, "instituicaoEmpresa");
		
		EventoParticipante eventoParticipante = new EventoParticipanteImpl();
		
		eventoParticipante.setNome(nome);
		eventoParticipante.setEmail(email);
		eventoParticipante.setInstituicaoEmpresa(instituicaoEmpresa);
		
		popularEventoParticipante(request, eventoParticipante);
		
		return eventoParticipante;
	}
	
	private void popularEventoParticipante(ActionRequest request, EventoParticipanteModel eventoParticipante){
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		long userId = themeDisplay.getUserId();
		long eventoId = ParamUtil.getLong(request, "eventoId");
	
		eventoParticipante.setEventoId(eventoId);
		eventoParticipante.setCompanyId(companyId);
		eventoParticipante.setUserId(userId);
		eventoParticipante.setCreateDate(new Date());
		
	}
	
	/**
	 * Verifica através do email se o participante já esta cadastro no evento
	 * 
	 * @param eventoId
	 * @param email
	 * @return
	 * @throws SystemException
	 */
	private Boolean verificarParticipanteCadastroNoEvento(long eventoId, String email) throws SystemException {
		
		EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoId, email);
		
		if(eventoParticipante != null){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

	/**
	 * Valida os campos principais da importação dos contatos
	 * 
	 * @param nome
	 * @param email
	 * @return
	 */
	private Boolean validacaoCamposImportacao(String nome, String email) {
		
		if(Validator.isNull(nome) || Validator.isNull(email)){
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	private void popularParticipantes(PortletRequest request, String [] incricoes, String [] participantes) throws SystemException{
		
		long eventoId = ParamUtil.getLong(request, "eventoId");
		int start = ParamUtil.getInteger(request, "start");
		int end = ParamUtil.getInteger(request, "end");

		//Antes de atualizar os eventos deve atualizar a inscrição e participação para falso
		List<EventoParticipante> lista = EventoParticipanteLocalServiceUtil.getListaDeParticipantes(eventoId, start, end);
		Iterator<EventoParticipante> itEventoParticipante = lista.iterator();
		
		while(itEventoParticipante.hasNext()){
			EventoParticipante eventoParticipante = itEventoParticipante.next();
			eventoParticipante.setInscricaoConfirmada(Boolean.FALSE);
			eventoParticipante.setParticipacaoConfirmada(Boolean.FALSE);
			EventoParticipanteLocalServiceUtil.updateEventoParticipante(eventoParticipante);
		}
		
		
		List<String> listaDeIncricoes = Arrays.asList(incricoes);
		List<String> listaDeParticipantes = Arrays.asList(participantes);
		
		Iterator<String> itIncricoes = listaDeIncricoes.iterator();
		
		while(itIncricoes.hasNext()){
			
			String email = itIncricoes.next();
			
			if(!email.isEmpty()){
				
				EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoId, email);
				
				eventoParticipante.setConviteEnviado(Boolean.FALSE);
				eventoParticipante.setCertificadoImpresso(Boolean.FALSE);
				eventoParticipante.setInscricaoConfirmada(Boolean.TRUE);
				
				if(listaDeParticipantes.contains(email)){
					eventoParticipante.setParticipacaoConfirmada(Boolean.TRUE);
				}
				else{
					eventoParticipante.setParticipacaoConfirmada(Boolean.FALSE);
				}
				
				EventoParticipanteLocalServiceUtil.updateEventoParticipante(eventoParticipante);
			}
		}
		
	}
	

	/**
	 * Salva as configurações do Evento
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 */
	public void salvarEventoConfiguracao(ActionRequest request, ActionResponse response) throws SystemException, IOException{
		long eventoId = ParamUtil.getLong(request, "eventoId");
		Boolean abertoAoPublico = ParamUtil.getBoolean(request, "abertoAoPublico");
		String cidadeDoEvento = ParamUtil.getString(request, "cidadeDoEvento");
		
		EventoConfiguracao eventoConfiguracao = EventoConfiguracaoLocalServiceUtil.getEventoConfiguracaoPeloEvento(eventoId);
		
		if(eventoConfiguracao != null){
			eventoConfiguracao.setAbertoAoPublico(abertoAoPublico);
			eventoConfiguracao.setCidadeDoEvento(cidadeDoEvento);
			eventoConfiguracao.setModifiedDate(new Date());
			
			EventoConfiguracaoLocalServiceUtil.updateEventoConfiguracao(eventoConfiguracao);
		}
		else{
			
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long companyId = themeDisplay.getCompanyId();
			long userId = themeDisplay.getUserId();
			
			eventoConfiguracao = new EventoConfiguracaoImpl();
			eventoConfiguracao.setEventoId(eventoId);
			eventoConfiguracao.setAbertoAoPublico(abertoAoPublico);
			eventoConfiguracao.setCidadeDoEvento(cidadeDoEvento);
			eventoConfiguracao.setCompanyId(companyId);
			eventoConfiguracao.setUserId(userId);
			eventoConfiguracao.setCreateDate(new Date());
			
			EventoConfiguracaoLocalServiceUtil.addEventoConfiguracao(eventoConfiguracao);
		}
	}
	
	/**
	 * Remove o participante do Evento 
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 * @throws PortalException 
	 */
	public void removerParticipanteDoEvento(ActionRequest request, ActionResponse response) throws SystemException, IOException, PortalException {
		long eventoParticipanteId = ParamUtil.getLong(request, "eventoParticipanteId");
		EventoParticipanteLocalServiceUtil.deleteEventoParticipante(eventoParticipanteId);
	}
	
	/**
	 * Envia por email um comunicado
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 * @throws PortalException
	 */
	public void enviarComunicadoEvento(ActionRequest request, ActionResponse response) throws SystemException, IOException, PortalException {
		
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
		
		long eventoId = ParamUtil.getLong(uploadRequest, "eventoId");
		String assunto = ParamUtil.getString(uploadRequest, "assunto");
		String emailFrom = ParamUtil.getString(uploadRequest, "emailFrom");
		String textoHTML = ParamUtil.getString(uploadRequest, "textoComunicado");
		String [] emails = ParamUtil.getParameterValues(uploadRequest, "selectUserEnvioComunicadoCheckBox");
		Boolean isConvite = ParamUtil.getBoolean(uploadRequest, "isConvite");
		
		File anexo = uploadRequest.getFile("fileName");
		
		Boolean temAnexo = Boolean.FALSE;
		
		if(uploadRequest.getSize("fileName") > 0){
			temAnexo = Boolean.TRUE;
		}
		
		if(emails.length > 0){
			
			for(String email : emails){
		
				try{
					
					EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoId, email);
					
					MailMessage mensagem = new MailMessage();
					mensagem.setFrom(new InternetAddress(emailFrom));
					mensagem.setSubject(assunto);
					
					mensagem.setTo(new InternetAddress(email));
					
					mensagem.setBody(textoHTML.replaceAll("NOME_CONVIDADO", eventoParticipante.getNome()));
					mensagem.setHTMLFormat(Boolean.TRUE);
					
					if(temAnexo){
						mensagem.addFileAttachment(anexo);
					}
					
					MailServiceUtil.sendEmail(mensagem);
					
					if(isConvite){
						eventoParticipante.setConviteEnviado(Boolean.TRUE);
					}
					
					EventoParticipanteLocalServiceUtil.updateEventoParticipante(eventoParticipante);
				}
				catch (AddressException e) {
					SessionErrors.add(request, "erro-endereco", email);
				}
				catch (Exception e){
					SessionErrors.add(request, "erro-envio-email", e.getMessage());
				}
			}
		}
		
	}
	
	/**
	 * Impressão do Certificado
	 */
	public void serveResource(ResourceRequest req, ResourceResponse res) throws IOException  {

        HttpServletRequest request = PortalUtil.getHttpServletRequest(req);
        
        try{
        	
        	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    		long companyId = themeDisplay.getCompanyId();
        
	        JasperReport report;
			report = JasperCompileManager.compileReport(getPortletContext().getRealPath("/reports/CertificadoWorkshopDemoiselle.jrxml"));
	
			long eventoParticipanteId = ParamUtil.getLong(request, "eventoParticipanteId");
	
			EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoParticipanteId);
	
			if (eventoParticipante != null) {
				
				EventoConfiguracao eventoConfiguracao = EventoConfiguracaoLocalServiceUtil.getEventoConfiguracaoPeloEvento(eventoParticipante.getEventoId());
				
				String nomeCompleto = "";
				
				User usuario = null;
				
				try{
					usuario = UserLocalServiceUtil.getUserByEmailAddress(companyId, eventoParticipante.getEmail());
					nomeCompleto = usuario.getFullName();
				}
				catch(NoSuchUserException e){
					nomeCompleto = eventoParticipante.getNome();
				}
				
				if(usuario != null && !eventoParticipante.getNome().isEmpty()){
					nomeCompleto = eventoParticipante.getNome();
				}
				
				CalEvent evento = CalEventLocalServiceUtil.getCalEvent(eventoParticipante.getEventoId());
	
				SimpleDateFormat formato = new SimpleDateFormat("dd' de 'MMMM' de 'yyyy");
		
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("participante", nomeCompleto);
				parametros.put("evento", evento.getTitle() + ",");
				parametros.put("data", formato.format(evento.getStartDate()) + ".");
				
				String localizacao = "";
				
				if(eventoConfiguracao != null){
					localizacao = eventoConfiguracao.getCidadeDoEvento();
				}
				
				if(localizacao != null && !localizacao.isEmpty()){
					parametros.put("localData", localizacao + ", " + formato.format(evento.getStartDate()));
				}
				else{
					parametros.put("localData", "");
				}
				
				parametros.put("fundo", getPortletContext().getRealPath("/reports/fundo.gif"));
		
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				list.add(parametros);
				
				JRBeanCollectionDataSource dataSourceWebContent = new JRBeanCollectionDataSource(list);
				JasperPrint print = JasperFillManager.fillReport(report, parametros,dataSourceWebContent);
				
				byte[] pdf = JasperExportManager.exportReportToPdf(print);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				baos.write(pdf);
				
				res.setContentType("application/pdf");
				res.setProperty("Content-Disposition", "attachment; filename=\"Certificado_Demoiselle.pdf\"");
		        
		        res.setContentLength(baos.size());
		        
		        OutputStream out = res.getPortletOutputStream();
		        baos.writeTo(out);
		        
		        out.flush();
		        out.close();
				
			}
        }
        catch(Exception e){
        	e.printStackTrace();
        }
	        
	}
}
