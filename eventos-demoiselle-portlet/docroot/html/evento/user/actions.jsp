<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil"%>
<%@page import="br.gov.demoiselle.portal.evento.model.EventoParticipante"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@page import="java.util.Date"%>
<%@include file="/html/evento/init.jsp" %>
 
 <%
	 ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	 CalEvent evento = (CalEvent)row.getObject();
	 
	 String name = CalEvent.class.getName();
	 long eventoId = evento.getEventId();
	 
	 EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoId, themeDisplay.getUser().getEmailAddress()) ;
	 
	 Boolean mostrarImpressao = Boolean.FALSE;
	 Boolean mostrarIncricao = Boolean.TRUE;
	 
	 if(eventoParticipante != null && eventoParticipante.getParticipacaoConfirmada()){
		 mostrarImpressao = Boolean.TRUE; 
	 }
	 
	 if(evento.getStartDate().before(new Date())){
		 mostrarIncricao = Boolean.FALSE;
	 }
 
%>
 
<liferay-ui:icon-menu>

	<portlet:renderURL var="inscricaoURL">
 		 <portlet:param name="jspPage" value="<%= user.isDefaultUser() ? \"/html/evento/user/inscricao_por_email.jsp\" : \"/html/evento/user/inscricao.jsp\" %>" />
 		 <portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
		 <portlet:param name="email" value="<%= user.isDefaultUser() ? \"\" : user.getEmailAddress()%>"/>
		 <portlet:param name="redirect" value="<%= themeDisplay.getURLCurrent() %>"/>
	</portlet:renderURL>
		
	<% if(!mostrarImpressao && mostrarIncricao){ %>
 	<aui:button value="Inscrição" onClick="<%= inscricaoURL.toString() %>"/>
 	<% } %>
 	
 	<portlet:resourceURL id="imprimir" var="imprimirURL">
 		 <portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
 		 <portlet:param name="email" value="<%= user.isDefaultUser() ? \"\" : user.getEmailAddress() %>"/>
		 <portlet:param name="redirect" value="<%= themeDisplay.getURLCurrent() %>"/>
	</portlet:resourceURL>

	<% if(mostrarImpressao){ %>
	<aui:button value="Imprimir Certificado" onClick="<%= imprimirURL.toString() %>"/>
	<% } %>
	 
</liferay-ui:icon-menu>