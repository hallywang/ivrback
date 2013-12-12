
<%@ page import="com.emag.gamecms.domain.system.GameCmsMailReceiver" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" controller="gameCmsMailServer" action="show" id="${params['mailServer.id']}">邮件服务器详情</g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${gameCmsMailReceiverInstance}">
            <div class="errors">
                <g:renderErrors bean="${gameCmsMailReceiverInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mailAddress"><g:message code="gameCmsMailReceiver.mailAddress.label" default="Mail Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsMailReceiverInstance, field: 'mailAddress', 'errors')}">
                                    <g:textField name="mailAddress" maxlength="60" value="${gameCmsMailReceiverInstance?.mailAddress}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mailServer"><g:message code="gameCmsMailReceiver.mailServer.label" default="Mail Server" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsMailReceiverInstance, field: 'mailServer', 'errors')}">
                                    <g:select name="mailServer.id" from="${com.emag.gamecms.domain.system.GameCmsMailServer.list()}" optionKey="id" optionValue="${{it.id + ':' + it.serverName}}" value="${gameCmsMailReceiverInstance?.mailServer?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
