<%@ page import="com.emag.gamecms.domain.system.GameCmsTagParams" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${gameCmsTagParamsInstance}">
        <div class="errors">
            <g:renderErrors bean="${gameCmsTagParamsInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${gameCmsTagParamsInstance?.id}"/>
        <g:hiddenField name="version" value="${gameCmsTagParamsInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="defaultValue"><g:message code="gameCmsTagParams.defaultValue.label" default="Default Value"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: gameCmsTagParamsInstance, field: 'defaultValue', 'errors')}">
                        <g:textField name="defaultValue" value="${gameCmsTagParamsInstance?.defaultValue}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="paramDesc"><g:message code="gameCmsTagParams.paramDesc.label" default="Param Desc"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: gameCmsTagParamsInstance, field: 'paramDesc', 'errors')}">
                        <g:textField name="paramDesc" value="${gameCmsTagParamsInstance?.paramDesc}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="nullAble"><g:message code="gameCmsTagParams.nullAble.label" default="Null Able"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: gameCmsTagParamsInstance, field: 'nullAble', 'errors')}">
                        <g:checkBox name="nullAble" value="${gameCmsTagParamsInstance?.nullAble}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="paramType"><g:message code="gameCmsTagParams.paramType.label" default="Param Type"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: gameCmsTagParamsInstance, field: 'paramType', 'errors')}">

                        <g:select  name="paramType" from="[[type:'1',name:'分省展示']]" optionKey="type" optionValue="name"
                           value="${gameCmsTagParamsInstance?.paramType}"  noSelection="${['' : '-- 请选择 --']}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="paramName"><g:message code="gameCmsTagParams.paramName.label" default="Param Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: gameCmsTagParamsInstance, field: 'paramName', 'errors')}">
                        <g:textField name="paramName" value="${gameCmsTagParamsInstance?.paramName}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="tagTip"><g:message code="gameCmsTagParams.tagTip.label" default="Tag Tip"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: gameCmsTagParamsInstance, field: 'tagTip', 'errors')}">
                        <g:select name="tagTip.id" from="${com.emag.gamecms.domain.system.tagtip.GameCmsTagTips.list()}" optionKey="id" value="${gameCmsTagParamsInstance?.tagTip?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="paramCode"><g:message code="gameCmsTagParams.paramCode.label" default="Param Code"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: gameCmsTagParamsInstance, field: 'paramCode', 'errors')}">
                        <g:textField name="paramCode" value="${gameCmsTagParamsInstance?.paramCode}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
