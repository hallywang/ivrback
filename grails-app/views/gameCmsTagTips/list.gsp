
<%@ page import="com.emag.gamecms.domain.system.GameCmsTagTips" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'gameCmsTagTips.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="tagDesc" title="${message(code: 'gameCmsTagTips.tagDesc.label', default: 'Tag Desc')}" />
                        
                            <g:sortableColumn property="tagCode" title="${message(code: 'gameCmsTagTips.tagCode.label', default: 'Tag Code')}" />
                        
                            <th><g:message code="gameCmsTagTips.tagParams.label" default="Tag Params" /></th>
                   	    
                            <g:sortableColumn property="tagName" title="${message(code: 'gameCmsTagTips.tagName.label', default: 'Tag Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${gameCmsTagTipsInstanceList}" status="i" var="gameCmsTagTipsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${gameCmsTagTipsInstance.id}">${fieldValue(bean: gameCmsTagTipsInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: gameCmsTagTipsInstance, field: "tagDesc")}</td>
                        
                            <td>${fieldValue(bean: gameCmsTagTipsInstance, field: "tagCode")}</td>
                        
                            <td>${fieldValue(bean: gameCmsTagTipsInstance, field: "tagParams")}</td>
                        
                            <td>${fieldValue(bean: gameCmsTagTipsInstance, field: "tagName")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${gameCmsTagTipsInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
