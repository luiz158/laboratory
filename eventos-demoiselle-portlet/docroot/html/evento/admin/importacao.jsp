<%@page import="com.liferay.portal.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.util.PwdGenerator"%>
<%@page import="com.liferay.portlet.calendar.service.CalEventLocalServiceUtil"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@include file="/html/evento/init.jsp" %>

<%
	long eventoId = ParamUtil.getLong(request, "eventoId");	
	CalEvent evento = CalEventLocalServiceUtil.getCalEvent(eventoId);
	String redirect = ParamUtil.getString(request, "redirect");
	String uploadProgressId = PwdGenerator.getPassword(PwdGenerator.KEY3, 4);
	
	String aba = ParamUtil.getString(request, "tabs1");
	
	PortletURL portletURL = renderResponse.createRenderURL();
	
	portletURL.setParameter("jspPage", "/html/evento/admin/importacao.jsp");
	portletURL.setParameter("eventoId", String.valueOf(eventoId));
	portletURL.setParameter("redirect", redirect);
	portletURL.setParameter("tabs1", aba);
	
	String nomeAbas = "Por Arquivo (CSV),Por Base de Dados";
	
	 
%>
 
<portlet:actionURL name="uploadParticipantes"
				   var="uploadParticipantesURL" windowState="normal">

	<portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>				   
	<portlet:param name="jspPage" value="/html/evento/admin/importacao.jsp"/>				   
	<portlet:param name="redirect" value="<%= redirect %>"/>				   

</portlet:actionURL>

<portlet:actionURL name="cadastrarParticipantesDaBase"
				   var="cadastrarParticipantesDaBaseURL" windowState="normal">

	<portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>				   
	<portlet:param name="jspPage" value="/html/evento/admin/importacao.jsp"/>				   
	<portlet:param name="redirect" value="<%= redirect %>"/>				   

</portlet:actionURL>
				   
<liferay-ui:header backURL="<%= redirect %>" title="<%= evento.getTitle() %>" />

<liferay-ui:tabs names="<%= nomeAbas %>" portletURL="<%= portletURL %>"/>

<%
	if(aba.equals("Por Arquivo (CSV)") || aba.isEmpty()){
%>
	<liferay-ui:upload-progress id="<%= uploadProgressId %>" message="uploading" redirect="<%= uploadParticipantesURL %>"/>
	
	<aui:form action="<%= uploadParticipantesURL %>" enctype="multipart/form-data" method="post" >
	
		<label class="aui-field-label">
			Para realizar a importação é necessário que o arquivo enviado seja um CSV(Comma-Separated Value) com as colunas separadas por ";".
			O arquivo deverá conter três colunas chamadas: nome, email e instituicaoEmpresa.
		</label>
		<br/>
		<aui:input type="file" name="fileName" size="75"/>
		
		<input type="submit" value="<liferay-ui:message key="upload" />" onClick="<%= uploadProgressId %>.startProgress(); return true;"/>
		
	</aui:form>

<%
	}
	else{
		if(aba.equals("Por Base de Dados")){
%>
	<aui:form action="<%= cadastrarParticipantesDaBaseURL %>" method="post" >
	
		<label class="aui-field-label">
			Selecione os usuários do Portal. 
		</label>
		
		<br/>
		
		<liferay-ui:search-container delta="75" emptyResultsMessage="Nenhum usuário encontrado">
	
			<liferay-ui:search-container-results 
				results="<%= UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd()) %>"
				total="<%= UserLocalServiceUtil.getUsersCount()%>"/>
			
				<liferay-ui:search-container-row
					className="com.liferay.portal.model.User"
					keyProperty="userId"
					modelVar="usuario" escapedModel="<%= true %>" >
					
					<% if(!usuario.isDefaultUser()){ %>
					<liferay-ui:search-container-column-text name="Selecionar">
					
						<input type="checkbox" id="selectUserCheckBox" 
							   class="selectUserCheckBox" name="selectUserCheckBox"
							   value="<%= usuario.getEmailAddress() %>" checked/>
								   
					</liferay-ui:search-container-column-text>
				
					<liferay-ui:search-container-column-text name="name" property="fullName"/>
					<liferay-ui:search-container-column-text name="email" property="emailAddress"/>
					<%} %>
					
				</liferay-ui:search-container-row>
			
			<liferay-ui:search-iterator />
		
		</liferay-ui:search-container>
		
		<input type="submit" value="Cadastrar"/>
		
	</aui:form>
<%
		}
	}
%>