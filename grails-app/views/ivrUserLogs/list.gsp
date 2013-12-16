
<%@ page import="com.emag.gamecms.domain.ivr.IvrUserLogs" %>
<!DOCTYPE html>
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ivrUserLogs.label', default: 'IvrUserLogs')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
				<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
		</div>
		<div id="list-ivrUserLogs" class="body" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="list">
				<table>
					<thead>
						<tr>
						
							<g:sortableColumn property="callNumber" title="${message(code: 'ivrUserLogs.callNumber.label', default: 'Call Number')}" />
						
							<g:sortableColumn property="callSecond" title="${message(code: 'ivrUserLogs.callSecond.label', default: 'Call Second')}" />
						
							<g:sortableColumn property="callTime" title="${message(code: 'ivrUserLogs.callTime.label', default: 'Call Time')}" />
						
							<g:sortableColumn property="createTime" title="${message(code: 'ivrUserLogs.createTime.label', default: 'Create Time')}" />
						
							<g:sortableColumn property="endTime" title="${message(code: 'ivrUserLogs.endTime.label', default: 'End Time')}" />
						
							<g:sortableColumn property="msisdn" title="${message(code: 'ivrUserLogs.msisdn.label', default: 'Msisdn')}" />
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${ivrUserLogsInstanceList}" status="i" var="ivrUserLogsInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td><g:link action="show" id="${ivrUserLogsInstance.id}">${fieldValue(bean: ivrUserLogsInstance, field: "callNumber")}</g:link></td>
						
							<td>${fieldValue(bean: ivrUserLogsInstance, field: "callSecond")}</td>
						
							<td><g:formatDate date="${ivrUserLogsInstance.callTime}" /></td>
						
							<td><g:formatDate date="${ivrUserLogsInstance.createTime}" /></td>
						
							<td><g:formatDate date="${ivrUserLogsInstance.endTime}" /></td>
						
							<td>${fieldValue(bean: ivrUserLogsInstance, field: "msisdn")}</td>
						
						</tr>
					</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${ivrUserLogsInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
