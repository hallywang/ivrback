<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sysConfGroup.label', default: 'SysConfGroup')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
    <script type="text/javascript">
        // 校验数据的合法性
        function checkedData() {
            // 组名称
            var groupNameValue = $('#groupName').attr("value");
            // 是否存在相同组名的数据
            var groupNumValue = $('#groupNum').attr("value");

            if ("" == groupNameValue) {
                $('#groupNameError').show();
                $('#groupNumError').hide();
                return false;
            } else {
                $('#groupNameError').hide();
                $('#groupNumError').hide();
            }

            if ("0" != groupNumValue) {
                $('#groupNameError').hide();
                $('#groupNumError').show();
                return false;
            } else {
                $('#groupNameError').hide();
                $('#groupNumError').hide();
            }

            document.forms[0].submit();
        }
    </script>
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
    <g:hasErrors bean="${sysConfGroupInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${sysConfGroupInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="save" >
        <div class="dialog">
            <fieldset class="form">
                <g:render template="form"/>
            </fieldset>
        </div>

        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="${message(code: 'default.button.create.gainway.label', default: 'Create')}" onClick="checkDate();" /></span>
        </div>

    </g:form>

</div>
</body>
</html>

