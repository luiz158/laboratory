<%@page import="com.liferay.portal.RequiredFieldException"%>
<%@page import="com.liferay.portal.EmailAddressException"%>
<%@page import="br.gov.demoiselle.portal.evento.model.EventoParticipante"%>
<%@page import="br.gov.demoiselle.portal.evento.service.EventoConfiguracaoLocalServiceUtil"%>
<%@page import="br.gov.demoiselle.portal.evento.model.EventoConfiguracao"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@include file="/html/evento/init.jsp"%>

<%
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	String redirect = PortalUtil.getCurrentURL(renderRequest);

	List<CalEvent> eventos = EventoParticipanteLocalServiceUtil.getListaDeEventos();
	
	Iterator<CalEvent> it = eventos.iterator();
	
	while(it.hasNext()){
		CalEvent evento = it.next();
		
		EventoConfiguracao eventoConfiguracao = EventoConfiguracaoLocalServiceUtil.getEventoConfiguracaoPeloEvento(evento.getEventId());
		
		//Verificar se usuario eh convidado
		if(!user.isDefaultUser()){
			EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(evento.getEventId(), user.getEmailAddress());
			
			//Se nao encontrou o eventoParticipante siginifica que o usuario nao foi convidado
			if(eventoParticipante == null){
				if(eventoConfiguracao == null || !eventoConfiguracao.isAbertoAoPublico()){
					it.remove();	
				}
			}
		}
		else{
			//Caso o usuario nao esteja logado
			if(eventoConfiguracao == null || !eventoConfiguracao.isAbertoAoPublico()){
				it.remove();
			}
		}
	}
		
%>

 
<liferay-ui:error exception="<%= EmailAddressException.class %>" message="E-mail inválido"/>
<liferay-ui:error exception="<%= RequiredFieldException.class %>" message="Nome deve ser preenchido"/>

<!-- Listagem de Eventos -->
<liferay-ui:search-container delta="10" emptyResultsMessage="Nenhum evento encontrado">

	<liferay-ui:search-container-results 
		results="<%= eventos %>"
		total="<%= eventos.size() %>"/>
	
		<liferay-ui:search-container-row
			className="com.liferay.portlet.calendar.model.CalEvent"
			keyProperty="eventId"
			modelVar="evento" escapedModel="<%= true %>" >
		
			<liferay-portlet:renderURL portletName="<%= PortletKeys.CALENDAR %>" var="calendarEventURL"  windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
				<portlet:param name="struts_action" value="/calendar/view_event" />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="eventId" value="<%= String.valueOf(evento.getEventId()) %>" />
			</liferay-portlet:renderURL>
			
			<liferay-ui:search-container-column-text name="title" property="title" href="${calendarEventURL}"/>
			
			<liferay-ui:search-container-column-text name="date" value="<%= formato.format(evento.getStartDate()) %>" />
			<liferay-ui:search-container-column-jsp align="left" path="/html/evento/user/actions.jsp"/>
		</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />

</liferay-ui:search-container>
