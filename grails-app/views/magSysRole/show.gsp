<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>Show MagSysRole</title>
</head>

<body>

<div class="nav">
  <span class="menuButton"><g:link class="list" action="list">角色列表</g:link></span>
  <span class="menuButton"><g:link class="create" action="create">新建角色</g:link></span>
</div>

<div class="body">
  <h1>角色信息</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="dialog">
    <table>
      <tbody>

      <tr class="prop">
        <td valign="top" class="name">Id:</td>
        <td valign="top" class="value">${authority.id}</td>
      </tr>

      <tr class="prop">
        <td valign="top" class="name">角色名:</td>
        <td valign="top" class="value">${authority.authority.substring(5).toLowerCase()}</td>
      </tr>

      <tr class="prop">
        <td valign="top" class="name">所属部门:</td>
        <td valign="top" class="value">${authority?.dept?.deptName}</td>
      </tr>

      <tr class="prop">
        <td valign="top" class="name">描述:</td>
        <td valign="top" class="value">${authority.description}</td>
      </tr>

      <tr class="prop">
        <td valign="top" class="name">用户:</td>
        <td valign="top" class="value">${authority.people}</td>
      </tr>
      <tr class="prop">
        <td valign="top" class="name">权限:</td>
        <td valign="top" class="value">
          <g:each in="${rms}" status="index" var="rm">
           ${rm.name}
          </g:each>

        </td>
      </tr>

      </tbody>
    </table>
  </div>

</div>

</body>
</html>

