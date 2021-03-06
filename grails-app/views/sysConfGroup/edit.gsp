<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <g:set var="entityName" value="${message(code: 'sysConfGroup.label', default: 'SysConfGroup')}" />
    <meta name="layout" content="main"/>
    <title><g:message code="default.edit.label" args="[entityName]" /></title>

</head>
<body>

<div class="nav">

    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${sysConfGroupInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${sysConfGroupInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>


    <g:form method="post" >
        <g:hiddenField name="id" value="${sysConfGroupInstance?.id}" />
        <g:hiddenField name="version" value="${sysConfGroupInstance?.version}" />
        <div class="dialog">
            <fieldset class="form">
                <g:render template="form"/>
            </fieldset>
        </div>

        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </div>

    </g:form>

</div>
</body>
</html>

