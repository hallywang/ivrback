
<%@ page import="com.emag.gamecms.domain.system.GameCmsMailServer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="serverName" title="${message(code: 'gameCmsMailServer.serverName.label', default: 'Server Name')}" />
                        
                            <g:sortableColumn property="smtpPort" title="${message(code: 'gameCmsMailServer.smtpPort.label', default: 'Smtp Port')}" />
                        
                            <g:sortableColumn property="username" title="${message(code: 'gameCmsMailServer.username.label', default: 'Username')}" />
                        
                            <g:sortableColumn property="password" title="${message(code: 'gameCmsMailServer.password.label', default: 'Password')}" />
                        
                            <g:sortableColumn property="localhost" title="${message(code: 'gameCmsMailServer.localhost.label', default: 'Localhost')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${gameCmsMailServerInstanceList}" status="i" var="gameCmsMailServerInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${gameCmsMailServerInstance.id}">${fieldValue(bean: gameCmsMailServerInstance, field: "serverName")}</g:link></td>
                        
                            <td>${fieldValue(bean: gameCmsMailServerInstance, field: "smtpPort")}</td>
                        
                            <td>${fieldValue(bean: gameCmsMailServerInstance, field: "username")}</td>
                        
                            <td>${fieldValue(bean: gameCmsMailServerInstance, field: "password")}</td>
                        
                            <td>${fieldValue(bean: gameCmsMailServerInstance, field: "localhost")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${gameCmsMailServerInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
