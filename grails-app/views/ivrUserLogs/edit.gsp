<%@ page import="com.emag.gamecms.domain.ivr.IvrUserLogs" %>
<!DOCTYPE html>
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ivrUserLogs.label', default: 'IvrUserLogs')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
				<span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
				%{--<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
	--}%	</div>
		<div id="edit-ivrUserLogs" class="body" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${ivrUserLogsInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${ivrUserLogsInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${ivrUserLogsInstance?.id}" />
				<g:hiddenField name="version" value="${ivrUserLogsInstance?.version}" />
				
				<div class="dialog">
					<g:render template="form"/>
				</div>
				<div class="buttons">
					<span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
					<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
				</div>
			</g:form>
		</div>
	</body>
</html>
