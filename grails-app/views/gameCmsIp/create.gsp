<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsIp.label', default: 'GameCmsIp')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${gameCmsIpInstance}">
            <div class="errors">
                <g:renderErrors bean="${gameCmsIpInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ip"><g:message code="gameCmsIp.ip.label" default="Ip" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsIpInstance, field: 'ip', 'errors')}">
                                    <g:textField name="ip" maxlength="15" value="${gameCmsIpInstance?.ip}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="flag"><g:message code="gameCmsIp.flag.label" default="Flag" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsIpInstance, field: 'flag', 'errors')}">
                                    <g:textField name="flag" value="${fieldValue(bean: gameCmsIpInstance, field: 'flag')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="gameCmsIp.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gameCmsIpInstance, field: 'description', 'errors')}">
                                    <g:textArea name="description" cols="40" rows="5" value="${gameCmsIpInstance?.description}" />
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
