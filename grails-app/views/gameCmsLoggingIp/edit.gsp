
<%@ page import="com.emag.gamecms.domain.system.GameCmsLoggingIp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${gameCmsLoggingIpInstance}">
            <div class="errors">
                <g:renderErrors bean="${gameCmsLoggingIpInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${gameCmsLoggingIpInstance?.id}" />
                <g:hiddenField name="version" value="${gameCmsLoggingIpInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="ip"><g:message code="gameCmsLoggingIp.ip.label" default="Ip" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsLoggingIpInstance, field: 'ip', 'errors')}">
                                    <g:textField name="ip" maxlength="100" value="${gameCmsLoggingIpInstance?.ip}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="status"><g:message code="gameCmsLoggingIp.status.label" default="Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsLoggingIpInstance, field: 'status', 'errors')}">
                                    <g:select name="status" from="${[[key:1, val:'有效'],[key:0, val:'无效']]}" optionKey="key" optionValue="val" value="${gameCmsLoggingIpInstance?.status}"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="supportRegex"><g:message code="gameCmsLoggingIp.supportRegex.label" default="Support Regex" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsLoggingIpInstance, field: 'supportRegex', 'errors')}">
                                  <g:select name="supportRegex" from="${[[key:0, val:'不支持'],[key:1, val:'支持']]}" optionKey="key" optionValue="val" value="${gameCmsLoggingIpInstance?.supportRegex}"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="gameCmsLoggingIp.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsLoggingIpInstance, field: 'description', 'errors')}">
                                  <g:textArea name="description" value="${gameCmsLoggingIpInstance?.description}" rows="5" cols="40" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
