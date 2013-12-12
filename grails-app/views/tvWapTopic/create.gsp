<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>新增专题</title>
</head>
<body>
<div class="nav">

    <span class="menuButton"><g:link class="list" action="list">专题列表</g:link></span>
</div>
<div class="body">
    <h1>新增专题</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${tvWapTopic}">
        <div class="errors">
            <g:renderErrors bean="${tvWapTopic}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save" method="post">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name">名称:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapTopic, field: 'name', 'errors')}">
                        <input type="text" id="name" name="name" value="${fieldValue(bean: tvWapTopic, field: 'name')}"
                                onkeyup="value = value.replace(/[\W]/g, '') "
                                />
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description">描述:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapTopic, field: 'description', 'errors')}">
                        <input type="text" id="description" name="description" value="${fieldValue(bean: tvWapTopic, field: 'description')}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="保存"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
