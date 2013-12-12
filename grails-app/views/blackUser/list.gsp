
<%@ page import="com.emag.gamecms.domain.system.BlackUser" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'blackUser.label', default: 'BlackUser')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link> &nbsp;&nbsp; <g:link class="create" action="importFile">导入</g:link></li>

			</ul>

		</div>
		<div id="list-blackUser" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="msisdn" title="${message(code: 'blackUser.msisdn.label', default: 'Msisdn')}" />

                        <g:sortableColumn property="flag" title="${message(code: 'blackUser.flag.label', default: 'Flag')}" />

						<g:sortableColumn property="description" title="${message(code: 'blackUser.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="createTime" title="${message(code: 'blackUser.createTime.label', default: 'Create Time')}" />
					
						<g:sortableColumn property="updateTime" title="${message(code: 'blackUser.updateTime.label', default: 'Update Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${blackUserInstanceList}" status="i" var="blackUserInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${blackUserInstance.id}">${fieldValue(bean: blackUserInstance, field: "msisdn")}</g:link></td>

                        <g:if test="${blackUserInstance.flag == 1}">
                            <td>挖宝达人</td>
                        </g:if>
					
						<td>${fieldValue(bean: blackUserInstance, field: "description")}</td>

						<td>
                            <g:formatDate date="${blackUserInstance?.createTime}" />
                        </td>
					
						<td>
                            <g:formatDate date="${blackUserInstance?.updateTime}" />
                        </td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${blackUserInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
