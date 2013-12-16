
<%@ page import="com.emag.gamecms.domain.ivr.IvrUserLogs" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'ivrUserLogs.label', default: 'IvrUserLogs')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav" role="navigation">
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div id="show-ivrUserLogs" class="body" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
          <tbody>
            
            <g:if test="${ivrUserLogsInstance?.callNumber}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="callNumber-label" class="property-label"><g:message code="ivrUserLogs.callNumber.label" default="Call Number" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="callNumber-label"><g:fieldValue bean="${ivrUserLogsInstance}" field="callNumber"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrUserLogsInstance?.callSecond}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="callSecond-label" class="property-label"><g:message code="ivrUserLogs.callSecond.label" default="Call Second" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="callSecond-label"><g:fieldValue bean="${ivrUserLogsInstance}" field="callSecond"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrUserLogsInstance?.callTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="callTime-label" class="property-label"><g:message code="ivrUserLogs.callTime.label" default="Call Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="callTime-label"><g:formatDate date="${ivrUserLogsInstance?.callTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrUserLogsInstance?.createTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="createTime-label" class="property-label"><g:message code="ivrUserLogs.createTime.label" default="Create Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="createTime-label"><g:formatDate date="${ivrUserLogsInstance?.createTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrUserLogsInstance?.endTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="endTime-label" class="property-label"><g:message code="ivrUserLogs.endTime.label" default="End Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="endTime-label"><g:formatDate date="${ivrUserLogsInstance?.endTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrUserLogsInstance?.msisdn}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="msisdn-label" class="property-label"><g:message code="ivrUserLogs.msisdn.label" default="Msisdn" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="msisdn-label"><g:fieldValue bean="${ivrUserLogsInstance}" field="msisdn"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrUserLogsInstance?.serviceId}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="serviceId-label" class="property-label"><g:message code="ivrUserLogs.serviceId.label" default="Service Id" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="serviceId-label"><g:fieldValue bean="${ivrUserLogsInstance}" field="serviceId"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
          </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${ivrUserLogsInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </g:form>
    </div>
</div>
</body>
</html>
