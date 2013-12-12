
<%@ page import="com.emag.gamecms.domain.system.BlackUser" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main2">
		<g:set var="entityName" value="${message(code: 'blackUser.label', default: 'BlackUser')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-blackUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-blackUser" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list blackUser">
			
				<g:if test="${blackUserInstance?.msisdn}">
				<li class="fieldcontain">
					<span id="msisdn-label" class="property-label"><g:message code="blackUser.msisdn.label" default="Msisdn" /></span>
					
						<span class="property-value" aria-labelledby="msisdn-label"><g:fieldValue bean="${blackUserInstance}" field="msisdn"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blackUserInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="blackUser.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${blackUserInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blackUserInstance?.flag}==1">
				<li class="fieldcontain">
					<span id="flag-label" class="property-label"><g:message code="blackUser.flag.label" default="Flag" /></span>
					
						<span class="property-value" aria-labelledby="flag-label">挖宝达人</span>
					
				</li>
				</g:if>
			
				<g:if test="${blackUserInstance?.createTime}">
				<li class="fieldcontain">
					<span id="createTime-label" class="property-label"><g:message code="blackUser.createTime.label" default="Create Time" /></span>
					
						<span class="property-value" aria-labelledby="createTime-label">
                            <g:formatDate date="${blackUserInstance?.createTime}" />
                        </span>
					
				</li>
				</g:if>
			
				<g:if test="${blackUserInstance?.updateTime}">
				<li class="fieldcontain">
					<span id="updateTime-label" class="property-label"><g:message code="blackUser.updateTime.label" default="Update Time" /></span>
					
						<span class="property-value" aria-labelledby="updateTime-label">
                            <g:formatDate date="${blackUserInstance?.updateTime}" />
                        </span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${blackUserInstance?.id}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
