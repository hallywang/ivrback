<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'sysConfGroup.label', default: 'SysConfGroup')}" />
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
                <td valign="top" class="name"><g:message code="sysConfGroup.groupName.label" default="Group Name" />:</td>
                <td valign="top" class="value"><g:fieldValue bean="${sysConfGroupInstance}" field="groupName"/></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="sysConfGroup.remark.label" default="Remark" />:</td>
                <td valign="top" class="value"><g:fieldValue bean="${sysConfGroupInstance}" field="remark"/></td>
            </tr>





            <tr class="prop">
                <td valign="top" class="name"><g:message code="sysConfGroup.createTime.label" default="Create Time" />:</td>
                <td valign="top" class="value"><g:formatDate date="${sysConfGroupInstance?.createTime}" /></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="sysConfGroup.updateTime.label" default="Update Time" />:</td>
                <td valign="top" class="value"><g:formatDate date="${sysConfGroupInstance?.updateTime}" /></td>
            </tr>




            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <input type="hidden" name="id" value="${sysConfGroupInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" value="修改" action="Edit"/></span>

            <span class="button"><g:actionSubmit class="delete" onclick="return confirm('确定删除吗?');" action="Delete" value="删除" /></span>
        </g:form>
    </div>

</div>
</body>
</html>

