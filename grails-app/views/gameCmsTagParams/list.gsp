
<%@ page import="com.emag.gamecms.domain.system.GameCmsTagParams" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'gameCmsTagParams.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="defaultValue" title="${message(code: 'gameCmsTagParams.defaultValue.label', default: 'Default Value')}" />
                        
                            <g:sortableColumn property="paramDesc" title="${message(code: 'gameCmsTagParams.paramDesc.label', default: 'Param Desc')}" />
                        
                            <g:sortableColumn property="nullAble" title="${message(code: 'gameCmsTagParams.nullAble.label', default: 'Null Able')}" />
                        
                            <g:sortableColumn property="paramName" title="${message(code: 'gameCmsTagParams.paramName.label', default: 'Param Name')}" />
                        
                            <th><g:message code="gameCmsTagParams.tagTip.label" default="Tag Tip" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${gameCmsTagParamsInstanceList}" status="i" var="gameCmsTagParamsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${gameCmsTagParamsInstance.id}">${fieldValue(bean: gameCmsTagParamsInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: gameCmsTagParamsInstance, field: "defaultValue")}</td>
                        
                            <td>${fieldValue(bean: gameCmsTagParamsInstance, field: "paramDesc")}</td>
                        
                            <td><g:formatBoolean boolean="${gameCmsTagParamsInstance.nullAble}" /></td>
                        
                            <td>${fieldValue(bean: gameCmsTagParamsInstance, field: "paramName")}</td>
                        
                            <td>${fieldValue(bean: gameCmsTagParamsInstance, field: "tagTip")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${gameCmsTagParamsInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
