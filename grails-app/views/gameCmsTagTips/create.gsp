
<%@ page import="com.emag.gamecms.domain.system.GameCmsTagTips" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${gameCmsTagTipsInstance}">
            <div class="errors">
                <g:renderErrors bean="${gameCmsTagTipsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>

                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagName"><g:message code="gameCmsTagTips.tagName.label" default="Tag Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsTagTipsInstance, field: 'tagName', 'errors')}">
                                    <g:textField name="tagName" value="${gameCmsTagTipsInstance?.tagName}" />
                                </td>
                            </tr>
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagCode"><g:message code="gameCmsTagTips.tagCode.label" default="Tag Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsTagTipsInstance, field: 'tagCode', 'errors')}">
                                    <g:textField name="tagCode" value="${gameCmsTagTipsInstance?.tagCode}" />
                                </td>
                            </tr>

                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagContent"><g:message code="gameCmsTagTips.tagContent.label" default="tagContent" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsTagTipsInstance, field: 'tagContent', 'errors')}">
                                    <g:textArea name="tagContent" value="${gameCmsTagTipsInstance?.tagContent}" rows="10" cols="150" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagDesc"><g:message code="gameCmsTagTips.tagDesc.label" default="Tag Desc" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsTagTipsInstance, field: 'tagDesc', 'errors')}">
                                    <g:textField name="tagDesc" value="${gameCmsTagTipsInstance?.tagDesc}" />
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
