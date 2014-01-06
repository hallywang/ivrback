
<%@ page import="com.emag.gamecms.domain.ivr.IvrServiceInfo" %>
<!DOCTYPE html>
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
				<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
		</div>
		<div id="list-ivrServiceInfo" class="body" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

          <g:form name="myForm" action="list" method="get">
            <div class="list">

              业务代码：<g:textField name="serviceId" value="${params['serviceId']}" maxlength="20" style="width:120px;"/>&nbsp;
              业务名称：<g:textField name="serviceName" value="${params['serviceName']}" maxlength="20" style="width:120px;"/>&nbsp;

              <span class="button"><input class="save" type="submit" value="查询" /></span><br/>


            </div>
          </g:form>


			<div class="list">
				<table>
					<thead>
						<tr>
						
							<g:sortableColumn property="serviceName" title="${message(code: 'ivrServiceInfo.serviceName.label', default: 'Service Name')}" />
						
							<g:sortableColumn property="serviceId" title="${message(code: 'ivrServiceInfo.serviceId.label', default: 'Service Id')}" />
						
							<g:sortableColumn property="serviceDesc" title="${message(code: 'ivrServiceInfo.serviceDesc.label', default: 'Service Desc')}" />
						
							<g:sortableColumn property="feeType" title="${message(code: 'ivrServiceInfo.feeType.label', default: 'Fee Type')}" />
						
							<g:sortableColumn property="fee" title="${message(code: 'ivrServiceInfo.fee.label', default: 'Fee')}" />
						
							<g:sortableColumn property="serviceType" title="${message(code: 'ivrServiceInfo.serviceType.label', default: 'Service Type')}" />
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${ivrServiceInfoInstanceList}" status="i" var="ivrServiceInfoInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td><g:link action="show" id="${ivrServiceInfoInstance.id}">${fieldValue(bean: ivrServiceInfoInstance, field: "serviceName")}</g:link></td>
						
							<td>${fieldValue(bean: ivrServiceInfoInstance, field: "serviceId")}</td>
						
							<td>${fieldValue(bean: ivrServiceInfoInstance, field: "serviceDesc")}</td>
						
							<td>${fieldValue(bean: ivrServiceInfoInstance, field: "feeType")}</td>
						
							<td>${fieldValue(bean: ivrServiceInfoInstance, field: "fee")}</td>
						
							<td>${fieldValue(bean: ivrServiceInfoInstance, field: "serviceType")}</td>
						
						</tr>
					</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${ivrServiceInfoInstanceTotal}" params="${params}"/>
			</div>
		</div>
	</body>
</html>
