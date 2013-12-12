
<%@ page import="com.emag.gamecms.domain.actionlog.GameCmsActionLog" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsActionLog.label', default: 'GameCmsActionLog')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsActionLog.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsActionLogInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsActionLog.userName.label" default="User Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsActionLogInstance, field: "userName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsActionLog.status.label" default="Status" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsActionLogInstance, field: "status")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsActionLog.actionName.label" default="Action Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsActionLogInstance, field: "actionName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsActionLog.actionId.label" default="Action Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsActionLogInstance, field: "actionId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsActionLog.controllName.label" default="Controll Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsActionLogInstance, field: "controllName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsActionLog.actionTime.label" default="Action Time" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${gameCmsActionLogInstance?.actionTime}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${gameCmsActionLogInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
