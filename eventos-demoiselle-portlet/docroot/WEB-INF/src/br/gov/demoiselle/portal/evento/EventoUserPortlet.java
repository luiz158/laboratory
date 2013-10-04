package br.gov.demoiselle.portal.evento;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.gov.demoiselle.portal.evento.model.EventoConfiguracao;
import br.gov.demoiselle.portal.evento.model.EventoParticipante;
import br.gov.demoiselle.portal.evento.model.impl.EventoParticipanteImpl;
import br.gov.demoiselle.portal.evento.service.EventoConfiguracaoLocalServiceUtil;
import br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil;

import com.liferay.portal.EmailAddressException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.RequiredFieldException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class EventoUserPortlet
 */
public class EventoUserPortlet extends MVCPortlet {

	/**
	 * Inscrição do participante no evento
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 * @throws PortalException 
	 */
	public void inscreverParticipanteEvento(ActionRequest request, ActionResponse response) throws SystemException, IOException, PortalException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		
		long eventoId = ParamUtil.getLong(request, "eventoId");
		String email = ParamUtil.getString(request, "email");
		
		User usuario = themeDisplay.getUser();
		
		if(usuario != null){
			
			EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoId, email);
			
			if(eventoParticipante == null){
				eventoParticipante = new EventoParticipanteImpl();

				eventoParticipante.setEmail(email);
				eventoParticipante.setNome(usuario.getFullName());
				
				if(!usuario.getOrganizations().isEmpty()){			
					eventoParticipante.setInstituicaoEmpresa(usuario.getOrganizations().get(0).getName());
				}
				
				eventoParticipante.setEventoId(eventoId);
				eventoParticipante.setCompanyId(companyId);
				eventoParticipante.setUserId(usuario.getUserId());
				eventoParticipante.setCreateDate(new Date());
				
				eventoParticipante.setParticipacaoConfirmada(Boolean.FALSE);
				eventoParticipante.setCertificadoImpresso(Boolean.FALSE);
				eventoParticipante.setConviteEnviado(Boolean.FALSE);
				
				eventoParticipante.setInscricaoConfirmada(Boolean.TRUE);
				
				EventoParticipanteLocalServiceUtil.addEventoParticipante(eventoParticipante);
			}
			else{
				eventoParticipante.setModifiedDate(new Date());
				eventoParticipante.setInscricaoConfirmada(Boolean.TRUE);
				EventoParticipanteLocalServiceUtil.updateEventoParticipante(eventoParticipante);
			}
			
		}
		
		sendRedirect(request, response);
		
	}
	
	/**
	 * Inscricao o convidado no evento por email
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws IOException
	 * @throws PortalException
	 */
	public void inscreverParticipanteEventoPorEmail(ActionRequest request, ActionResponse response) throws SystemException, IOException, PortalException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		
		long eventoId = ParamUtil.getLong(request, "eventoId");
		String email = ParamUtil.getString(request, "email");
		String nome = ParamUtil.getString(request, "nome");
		String instituicaoEmpresa = ParamUtil.getString(request, "instituicaoEmpresa");
		
		EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoId, email);
		
		if(email.isEmpty() || !Validator.isEmailAddress(email)){
			SessionErrors.add(request, EmailAddressException.class);
		}
		
		if(nome.isEmpty() && eventoParticipante == null){
			SessionErrors.add(request, RequiredFieldException.class);
		}
		
		if(SessionErrors.isEmpty(request)){
			
			if(eventoParticipante == null){
				eventoParticipante = new EventoParticipanteImpl();
		
				eventoParticipante.setEmail(email);
				eventoParticipante.setNome(nome);
				
				eventoParticipante.setInstituicaoEmpresa(instituicaoEmpresa);
				
				eventoParticipante.setEventoId(eventoId);
				eventoParticipante.setCompanyId(companyId);
				eventoParticipante.setCreateDate(new Date());
				
				eventoParticipante.setParticipacaoConfirmada(Boolean.FALSE);
				eventoParticipante.setCertificadoImpresso(Boolean.FALSE);
				eventoParticipante.setConviteEnviado(Boolean.FALSE);
				
				eventoParticipante.setInscricaoConfirmada(Boolean.TRUE);
				
				EventoParticipanteLocalServiceUtil.addEventoParticipante(eventoParticipante);
			}
			else{
				eventoParticipante.setModifiedDate(new Date());
				eventoParticipante.setInscricaoConfirmada(Boolean.TRUE);
				EventoParticipanteLocalServiceUtil.updateEventoParticipante(eventoParticipante);
			}
		}
		
		sendRedirect(request, response);
	}
	
	/**
	 * Remove o participante do evento
	 * 
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException 
	 */
	public void removerParticipanteEvento(ActionRequest request, ActionResponse response) throws PortalException, SystemException, IOException{
		long eventoParticipanteId = ParamUtil.getLong(request, "eventoParticipanteId");
		EventoParticipanteLocalServiceUtil.deleteEventoParticipante(eventoParticipanteId);
		
		sendRedirect(request, response);
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
	
			long eventoId = ParamUtil.getLong(request, "eventoId");
			String email = ParamUtil.getString(request, "email");
	
			EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoId, email);
	
			if (eventoParticipante != null) {
				
				EventoConfiguracao eventoConfiguracao = EventoConfiguracaoLocalServiceUtil.getEventoConfiguracaoPeloEvento(eventoId);
				
				String nomeCompleto = "";
				
				User usuario = null;
				
				try{
					usuario = UserLocalServiceUtil.getUserByEmailAddress(companyId, eventoParticipante.getEmail());
					nomeCompleto = usuario.getFullName();
				}
				catch(NoSuchUserException e){
					nomeCompleto = eventoParticipante.getNome();
				}
				
				CalEvent evento = CalEventLocalServiceUtil.getCalEvent(eventoId);
	
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
