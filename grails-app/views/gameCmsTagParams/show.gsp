<%@ page import="com.emag.gamecms.domain.system.GameCmsTagParams" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="gameCmsTagParams.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: gameCmsTagParamsInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="gameCmsTagParams.defaultValue.label" default="Default Value"/></td>

                <td valign="top" class="value">${fieldValue(bean: gameCmsTagParamsInstance, field: "defaultValue")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="gameCmsTagParams.paramDesc.label" default="Param Desc"/></td>

                <td valign="top" class="value">${fieldValue(bean: gameCmsTagParamsInstance, field: "paramDesc")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="gameCmsTagParams.nullAble.label" default="Null Able"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${gameCmsTagParamsInstance?.nullAble}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="gameCmsTagParams.paramName.label" default="Param Name"/></td>

                <td valign="top" class="value">${fieldValue(bean: gameCmsTagParamsInstance, field: "paramName")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="gameCmsTagParams.paramType.label" default="Param Type"/></td>

                <td valign="top" class="value">
                    <g:if test="${gameCmsTagParamsInstance.paramType == 1}">
                        分省展示
                    </g:if>
                </td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="gameCmsTagParams.tagTip.label" default="Tag Tip"/></td>

                <td valign="top" class="value"><g:link controller="gameCmsTagTips" action="show" id="${gameCmsTagParamsInstance?.tagTip?.id}">${gameCmsTagParamsInstance?.tagTip?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="gameCmsTagParams.paramCode.label" default="Param Code"/></td>

                <td valign="top" class="value">${fieldValue(bean: gameCmsTagParamsInstance, field: "paramCode")}</td>

            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${gameCmsTagParamsInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
