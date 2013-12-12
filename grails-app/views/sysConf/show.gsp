<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'sysConf.label', default: 'SysConf')}" />
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
                <td valign="top" class="name"><g:message code="sysConf.sysconfKey.label" default="SysconfKey" />:</td>
                <td valign="top" class="value"><g:fieldValue bean="${sysConfInstance}" field="sysconfKey"/></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="sysConf.sysconfValue.label" default="SysconfValue" />:</td>
                <td valign="top" class="value"><g:fieldValue bean="${sysConfInstance}" field="sysconfValue"/></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="sysConf.status.label" default="Status" />:</td>
                <td valign="top" class="value">
                    <g:if test="${sysConfInstance?.status==0}">
                        无效/span>
                    </g:if>
                    <g:elseif test="${sysConfInstance?.status==1}">
                        有效
                    </g:elseif>
                </td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="sysConf.remark.label" default="Remark" />:</td>
                <td valign="top" class="value"><g:fieldValue bean="${sysConfInstance}" field="remark"/></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="sysConf.createTime.label" default="Create Time" />:</td>
                <td valign="top" class="value">${sysConfInstance?.createTime}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="sysConf.updateTime.label" default="Update Time" />:</td>
                <td valign="top" class="value"><g:formatDate date="${sysConfInstance?.updateTime}" /></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="sysConf.group.label" default="Group" />:</td>
                <td valign="top" class="value"><g:link controller="sysConfGroup" action="show" id="${sysConfInstance?.group?.id}">${sysConfInstance?.group?.groupName.encodeAsHTML()}</g:link></td>
            </tr>



            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <input type="hidden" name="id" value="${sysConfInstance.id}" />
            <span class="button"><g:actionSubmit class="edit" value="修改" action="Edit"/></span>

            <span class="button"><g:actionSubmit class="delete" onclick="return confirm('确定删除吗?');" action="Delete" value="删除" /></span>
        </g:form>
    </div>

</div>
</body>
</html>

