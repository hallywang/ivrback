
<%@ page import="com.emag.gamecms.domain.ivr.IvrBlackUser" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'ivrBlackUser.label', default: 'IvrBlackUser')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav" role="navigation">
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div id="show-ivrBlackUser" class="body" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
          <tbody>
            
            <g:if test="${ivrBlackUserInstance?.msisdn}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="msisdn-label" class="property-label"><g:message code="ivrBlackUser.msisdn.label" default="Msisdn" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="msisdn-label"><g:fieldValue bean="${ivrBlackUserInstance}" field="msisdn"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrBlackUserInstance?.userType}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="userType-label" class="property-label"><g:message code="ivrBlackUser.userType.label" default="User Type" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="userType-label"><g:fieldValue bean="${ivrBlackUserInstance}" field="userType"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrBlackUserInstance?.scope}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="scope-label" class="property-label"><g:message code="ivrBlackUser.scope.label" default="Scope" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="scope-label"><g:fieldValue bean="${ivrBlackUserInstance}" field="scope"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrBlackUserInstance?.status}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="status-label" class="property-label"><g:message code="ivrBlackUser.status.label" default="Status" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${ivrBlackUserInstance}" field="status"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrBlackUserInstance?.comment}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="comment-label" class="property-label"><g:message code="ivrBlackUser.comment.label" default="Comment" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="comment-label"><g:fieldValue bean="${ivrBlackUserInstance}" field="comment"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrBlackUserInstance?.createTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="createTime-label" class="property-label"><g:message code="ivrBlackUser.createTime.label" default="Create Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="createTime-label"><g:formatDate date="${ivrBlackUserInstance?.createTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrBlackUserInstance?.updateTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="updateTime-label" class="property-label"><g:message code="ivrBlackUser.updateTime.label" default="Update Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="updateTime-label"><g:formatDate date="${ivrBlackUserInstance?.updateTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
          </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${ivrBlackUserInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </g:form>
    </div>
</div>
</body>
</html>
