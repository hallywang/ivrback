
<%@ page import="com.emag.gamecms.domain.ivr.IvrChannelNotice" %>
<!DOCTYPE html>
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
				<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
		</div>
		<div id="list-ivrChannelNotice" class="body" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="list">
				<table>
					<thead>
						<tr>
						
							<g:sortableColumn property="channelCode" title="${message(code: 'ivrChannelNotice.channelCode.label', default: 'Channel Code')}" />
                            <g:sortableColumn property="serviceId" title="${message(code: 'ivrChannelNotice.serviceId.label', default: 'Service Id')}" />


                            <g:sortableColumn property="noticeUrl" title="${message(code: 'ivrChannelNotice.noticeUrl.label', default: 'Notice Url')}" />
                            <g:sortableColumn property="serviceClass" title="${message(code: 'ivrChannelNotice.serviceClass.label', default: 'serviceClass')}" />


                            <g:sortableColumn property="status" title="${message(code: 'status.label', default: 'Status')}" />
                            <g:sortableColumn property="createTime" title="${message(code: 'createTime.label', default: 'Create Time')}" />

                            <g:sortableColumn property="updateTime" title="${message(code: 'updateTime.label', default: 'Update Time')}" />
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${ivrChannelNoticeInstanceList}" status="i" var="ivrChannelNoticeInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td><g:link action="show" id="${ivrChannelNoticeInstance.id}">${fieldValue(bean: ivrChannelNoticeInstance, field: "channelCode")}</g:link></td>
                          <td>${fieldValue(bean: ivrChannelNoticeInstance, field: "serviceId")}</td>


                          <td>${fieldValue(bean: ivrChannelNoticeInstance, field: "noticeUrl")}</td>
                          <td>${fieldValue(bean: ivrChannelNoticeInstance, field: "serviceClass")}</td>


                          <td>${fieldValue(bean: ivrChannelNoticeInstance, field: "status")}</td>
                          <td><g:formatDate date="${ivrChannelNoticeInstance.createTime}" /></td>

                          <td><g:formatDate date="${ivrChannelNoticeInstance.updateTime}" /></td>
						
						</tr>
					</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${ivrChannelNoticeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
