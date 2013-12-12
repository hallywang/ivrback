
<%@ page import="com.emag.gamecms.domain.system.GameCmsLoggingIp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'gameCmsLoggingIp.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="ip" title="${message(code: 'gameCmsLoggingIp.ip.label', default: 'Ip')}" />
                        
                            <g:sortableColumn property="status" title="${message(code: 'gameCmsLoggingIp.status.label', default: 'Status')}" />
                        
                            <g:sortableColumn property="supportRegex" title="${message(code: 'gameCmsLoggingIp.supportRegex.label', default: 'Support Regex')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'gameCmsLoggingIp.description.label', default: 'Description')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${gameCmsLoggingIpInstanceList}" status="i" var="gameCmsLoggingIpInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${gameCmsLoggingIpInstance.id}">${fieldValue(bean: gameCmsLoggingIpInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: gameCmsLoggingIpInstance, field: "ip")}</td>
                        
                            <td>${gameCmsLoggingIpInstance.status == 1 ? '有效' : '无效'}</td>
                        
                            <td>${gameCmsLoggingIpInstance.supportRegex == 1 ? '支持': '不支持'}</td>
                        
                            <td>${fieldValue(bean: gameCmsLoggingIpInstance, field: "description")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${gameCmsLoggingIpInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
