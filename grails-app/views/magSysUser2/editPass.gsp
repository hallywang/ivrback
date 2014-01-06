%{--修改密码--}%
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>修改密码</title>
</head>
<body>

<div class="nav">
   %{--  <span class="menuButton"><g:link class="list" action="edit" id="${userInfo.id}" >修改资料</g:link></span>--}%
</div>

<div class="body">
  <h1>修改密码</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${userInfo}">
    <div class="errors">
      <g:renderErrors bean="${userInfo}" as="list"/>
    </div>
  </g:hasErrors>

  <g:form method="post">
    <input type="hidden" name="id" value="${userInfo?.id}"/>
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="passwd">您的旧密码:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'userRealName', 'errors')}">
            <input type="password" id="passwd" name="passwd" maxlength="20"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="passwd">输入新密码:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'userRealName', 'errors')}">
            <input type="password" id="pass" name="pass" maxlength="20"/>
          </td>
        </tr>

          <tr class="prop">
          <td valign="top" class="name">
            <label for="passwd">新密码确认:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'userRealName', 'errors')}">
            <input type="password" id="pass1" name="pass1" maxlength="20"/>
          </td>
        </tr>

        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button"><g:actionSubmit class="save" value="更新" action="updatePass"/></span>
    </div>

  </g:form>

</div>
</body>
</html>

