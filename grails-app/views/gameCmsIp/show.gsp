<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsIp.label', default: 'GameCmsIp')}" />
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
                            <td valign="top" class="name"><g:message code="gameCmsIp.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsIpInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsIp.ip.label" default="Ip" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsIpInstance, field: "ip")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsIp.flag.label" default="Flag" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsIpInstance, field: "flag")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsIp.description.label" default="Description" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsIpInstance, field: "description")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsIp.createDate.label" default="Create Date" /></td>
                            
                            <td valign="top" class="value">
                                <g:formatDate date="${gameCmsIpInstance?.createDate}" />
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsIp.updateDate.label" default="Update Date" /></td>
                            
                            <td valign="top" class="value">
                                <g:formatDate date="${gameCmsIpInstance?.updateDate}" />
                            </td>
                            
                        </tr>
                    
                        %{--<tr class="prop">--}%
                            %{--<td valign="top" class="name"><g:message code="gameCmsIp.urls.label" default="Urls" /></td>--}%
                            %{----}%
                            %{--<td valign="top" style="text-align: left;" class="value">--}%
                                %{--<ul>--}%
                                %{--<g:each in="${gameCmsIpInstance.urls}" var="u">--}%
                                    %{--<li><g:link controller="gameCmsProtectUrl" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>--}%
                                %{--</g:each>--}%
                                %{--</ul>--}%
                            %{--</td>--}%
                            %{----}%
                        %{--</tr>--}%
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${gameCmsIpInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
