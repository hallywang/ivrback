
<%@ page import="com.emag.gamecms.domain.ivr.IvrConfigData" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'ivrConfigData.label', default: 'IvrConfigData')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav" role="navigation">
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div id="show-ivrConfigData" class="body" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
          <tbody>
            
            <g:if test="${ivrConfigDataInstance?.operateId}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="operateId-label" class="property-label"><g:message code="ivrConfigData.operateId.label" default="Operate Id" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="operateId-label"><g:fieldValue bean="${ivrConfigDataInstance}" field="operateId"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrConfigDataInstance?.serviceId}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="serviceId-label" class="property-label"><g:message code="ivrConfigData.serviceId.label" default="Service Id" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="serviceId-label"><g:fieldValue bean="${ivrConfigDataInstance}" field="serviceId"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrConfigDataInstance?.content}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="content-label" class="property-label"><g:message code="ivrConfigData.content.label" default="Content" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="content-label"><g:fieldValue bean="${ivrConfigDataInstance}" field="content"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrConfigDataInstance?.status}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="status-label" class="property-label"><g:message code="ivrConfigData.status.label" default="Status" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${ivrConfigDataInstance}" field="status"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrConfigDataInstance?.comment}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="comment-label" class="property-label"><g:message code="ivrConfigData.comment.label" default="Comment" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="comment-label"><g:fieldValue bean="${ivrConfigDataInstance}" field="comment"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrConfigDataInstance?.paramA}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="paramA-label" class="property-label"><g:message code="ivrConfigData.paramA.label" default="Param A" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="paramA-label"><g:fieldValue bean="${ivrConfigDataInstance}" field="paramA"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrConfigDataInstance?.configType}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="configType-label" class="property-label"><g:message code="ivrConfigData.configType.label" default="Config Type" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="configType-label"><g:fieldValue bean="${ivrConfigDataInstance}" field="configType"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
          </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${ivrConfigDataInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </g:form>
    </div>
</div>
</body>
</html>
