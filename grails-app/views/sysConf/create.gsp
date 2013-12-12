<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sysConf.label', default: 'SysConf')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
    <script type="text/javascript">
        // 判断数据是否合法
        function checkDate() {
            // 是否有相同的数据
            var gkeyNum = $('#gkeyNum').val();
            // key值
            var keyValue = $('#key').val();
            // value值
            var valueValue = $('#value').val();

            if ('' == keyValue) {
                $('#keyError').show();
                $('#gkeyError').hide();
                return false;
            } else if ("0" != gkeyNum) {
                $('#keyError').hide();
                $('#gkeyError').show();
                return false;
            } else {
                $('#keyError').hide();
                $('#gkeyError').hide();
            }

            if ('' == valueValue) {
                $('#valueError').show();
                return false;
            } else {
                $('#valueError').hide();
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
    <h1>添加资源文件</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:form action="save" >
        <div class="dialog">
            <fieldset class="form">
                <g:render template="form"/>
            </fieldset>
        </div>

        <div class="buttons">
            <span class="button"><input class="save" type="button" value="${message(code: 'default.button.create.gainway.label', default: 'Create')}" onClick="checkDate();" /></span>
        </div>

    </g:form>

</div>
</body>
</html>

