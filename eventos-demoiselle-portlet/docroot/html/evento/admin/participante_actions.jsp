<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="br.gov.demoiselle.portal.evento.model.EventoParticipante"%>
<%@include file="/html/evento/init.jsp" %>
 
<%
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	EventoParticipante eventoParticipante = (EventoParticipante)row.getObject();
	 
	long eventoParticipanteId = eventoParticipante.getEventoParticipanteId();
	long eventoId = eventoParticipante.getEventoId();
	 
	String redirect = PortalUtil.getCurrentURL(renderRequest);
%>
 
<liferay-ui:icon-menu>
	<portlet:renderURL var="editURL">
		<portlet:param name="jspPage" value="/html/evento/admin/participante.jsp" />
		<portlet:param name="eventoParticipanteId" value="<%= String.valueOf(eventoParticipanteId) %>"/>
		<portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
		<portlet:param name="redirect" value="<%= redirect %>"/>
	</portlet:renderURL>
	 
	<liferay-ui:icon image="edit" url="<%= editURL.toString() %>"/>
	 
	<portlet:actionURL name="removerParticipanteDoEvento" var="deleteURL">
		<portlet:param name="eventoParticipanteId" value="<%= String.valueOf(eventoParticipanteId) %>"/>
		<portlet:param name="redirect" value="<%= redirect %>"/>
	</portlet:actionURL>
	 
	<liferay-ui:icon image="delete" url="<%= deleteURL.toString() %>"/>
	
	<portlet:resourceURL id="emitirCertificadoParticipanteDoEvento" var="emitirCertificadoURL">
 		<portlet:param name="eventoParticipanteId" value="<%= String.valueOf(eventoParticipanteId) %>"/>
		<portlet:param name="redirect" value="<%= redirect %>"/>
	</portlet:resourceURL>
	 
	<liferay-ui:icon image="print" label="Imprimir Certificado" url="<%= emitirCertificadoURL.toString() %>"/>
	
</liferay-ui:icon-menu>