<%@page import="br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil"%>
<%@include file="/html/evento/init.jsp"%>
<%
	String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<!-- Listagem de Eventos -->
 
<liferay-ui:search-container delta="75" emptyResultsMessage="Nenhum evento encontrado">

	<liferay-ui:search-container-results 
		results="<%= EventoParticipanteLocalServiceUtil.getListaDeEventos() %>"
		total="<%= EventoParticipanteLocalServiceUtil.getListaDeEventos().size()%>"/>
	
		<liferay-ui:search-container-row
			className="com.liferay.portlet.calendar.model.CalEvent"
			keyProperty="eventId"
			modelVar="evento" escapedModel="<%= true %>" >
		
			<liferay-ui:search-container-column-text name="title" property="title"/>
			<liferay-ui:search-container-column-text name="type" property="type"/>
			
			<liferay-ui:search-container-column-jsp align="left" path="/html/evento/admin/actions.jsp"/>
		</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />

</liferay-ui:search-container>