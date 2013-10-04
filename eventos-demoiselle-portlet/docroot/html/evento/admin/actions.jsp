<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@include file="/html/evento/init.jsp" %>
 
 <%
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	CalEvent evento = (CalEvent)row.getObject();
	
	String name = CalEvent.class.getName();
	long eventoId = evento.getEventId();
	
	String redirect = PortalUtil.getCurrentURL(renderRequest);
 %>
 
 <liferay-ui:icon-menu>
	 <portlet:renderURL var="editURL">
		 <portlet:param name="jspPage" value="/html/evento/admin/edit.jsp" />
		 <portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
		 <portlet:param name="redirect" value="<%= redirect %>"/>
	 </portlet:renderURL>
	 
	 <liferay-ui:icon image="edit" url="<%= editURL.toString() %>"/>
 </liferay-ui:icon-menu>