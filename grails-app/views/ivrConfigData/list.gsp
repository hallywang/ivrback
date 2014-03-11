
<%@ page import="com.emag.gamecms.domain.ivr.IvrConfigData" %>
<!DOCTYPE html>
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ivrConfigData.label', default: 'IvrConfigData')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
				<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
		</div>
		<div id="list-ivrConfigData" class="body" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
          <g:form name="myForm" action="list" method="get">
            <div class="list">

              业务代码：<g:select name="serviceId" from="${com.emag.gamecms.domain.ivr.IvrServiceInfo.list()}"
                             optionKey="serviceId"
                             value="${params?.serviceId}" optionValue="serviceName"
                             noSelection="${['': '--ALL--']}"/>
              数据含义: <g:select name="operateId" from="${com.emag.constants.IvrConstants.operateIds}"
                              value="${params?.operateId}"  optionKey="key" optionValue="value"
                              noSelection="${['': '--ALL--']}"/>

              <span class="button"><input class="save" type="submit" value="查询" /></span><br/>


            </div>
          </g:form>
			<div class="list">
				<table>
					<thead>
						<tr>
						
							<g:sortableColumn property="operateId" title="${message(code: 'ivrConfigData.operateId.label', default: 'Operate Id')}" />
						
							<g:sortableColumn property="serviceId" title="${message(code: 'ivrConfigData.serviceId.label', default: 'Service Id')}" />
						
							<g:sortableColumn property="content" title="${message(code: 'ivrConfigData.content.label', default: 'Content')}" />
						
							<g:sortableColumn property="status" title="${message(code: 'ivrConfigData.status.label', default: 'Status')}" />
						
							<g:sortableColumn property="comment" title="${message(code: 'ivrConfigData.comment.label', default: 'Comment')}" />
						
							<g:sortableColumn property="paramA" title="${message(code: 'ivrConfigData.paramA.label', default: 'Param A')}" />
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${ivrConfigDataInstanceList}" status="i" var="ivrConfigDataInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td><g:link action="show" id="${ivrConfigDataInstance.id}">
                              ${com.emag.constants.IvrConstants.operateIds.get(ivrConfigDataInstance.operateId)}
                            </g:link></td>
						
							<td> ${ivrConfigDataInstance.serviceId} &nbsp; &nbsp;
                              ${com.emag.gamecms.domain.ivr.IvrServiceInfo.findByServiceId(ivrConfigDataInstance.serviceId)?.serviceName}
                            </td>
						
							<td>${fieldValue(bean: ivrConfigDataInstance, field: "content")}</td>
						
							<td>
                              <g:message code="ivrConfigDataInstance.status.${ivrConfigDataInstance.status}"/>
                            </td>
						
							<td>${fieldValue(bean: ivrConfigDataInstance, field: "comment")}</td>
						
							<td>${fieldValue(bean: ivrConfigDataInstance, field: "paramA")}</td>
						
						</tr>
					</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${ivrConfigDataInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
