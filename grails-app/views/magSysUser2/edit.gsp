<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>Edit MagSysUser2</title>
</head>
<body>

<div class="nav">

  <span class="menuButton"><g:link class="list" action="list">用户列表</g:link></span>
  <span class="menuButton"><g:link class="create" action="create">添加用户</g:link></span>
</div>

<div class="body">
  <h1>编辑用户</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${person}">
    <div class="errors">
      <g:renderErrors bean="${person}" as="list"/>
    </div>
  </g:hasErrors>

  <div class="prop">
    <span class="name">编号:</span>
    <span class="value">${person?.id}</span>
  </div>

  <g:form method="post" action="update">
    <input type="hidden" name="id" value="${person?.id}"/>
    <input type="hidden" id="username" name="username" value="${person?.username}"/>
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="username">登录名:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'username', 'errors')}">
            ${person?.username?.encodeAsHTML()}
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="userRealName">真实姓名:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'userRealName', 'errors')}">
            <input type="text" id="userRealName" name="userRealName" value="${person?.userRealName?.encodeAsHTML()}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="passwd">密码:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'passwd', 'errors')}">
            <input type="password" id="passwd" name="passwd" value="${person?.passwd?.encodeAsHTML()}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="enabled">有效:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'enabled', 'errors')}">
            <g:checkBox name="enabled" value="${person?.enabled}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label>所属部门:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'deptList', 'errors')}">
            <g:select name="dept.id" from="${deptList}" noSelection="['':'--请选择所属部门--']" optionKey="id" optionValue="deptName" value="${person?.dept?.id}"  />
          </td>
        <tr class="prop">
          <td valign="top" class="name">
            <label for="description">描述:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'description', 'errors')}">
            <input type="text" id="description" name="description" value="${person?.description?.encodeAsHTML()}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="email">Email:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'email', 'errors')}">
            <input type="text" id="email" name="email" value="${person?.email?.encodeAsHTML()}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="emailShow">是否显示Email:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'emailShow', 'errors')}">
            <g:checkBox name="emailShow" value="${person?.emailShow}"/>
          </td>
        </tr>

         <tr class="prop">
          <td valign="top" class="name">
            <label for="mobile">手机号:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'mobile', 'errors')}">
            <input type="text" id="mobile" name="mobile" value="${person?.mobile?.encodeAsHTML()}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="checkSmsYn">是否需要短信验证:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'checkSmsYn', 'errors')}">
            <g:checkBox name="checkSmsYn" value="${person?.checkSmsYn}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="authorities">角色:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'authorities', 'errors')}">
              <table>
              <g:set var="list" value="[]"/>

              <g:each var="b" in="${authorityList}">
                <tr>
                <g:each var="a" in="${person?.authorities}">
                  <g:if test="${b.authority==~ a.authority}">
                    <% if (!list.contains(b.authority)) {
                      list.add(b.authority); %>
                    <td>${a.authority?.substring(5)?.toLowerCase()?.encodeAsHTML()}
                    <g:checkBox name="${a.authority}" value="${true}"/>(${a.description})
                    </td>
                    <% } %>
                  </g:if>
                </g:each>
                <% if (!list.contains(b.authority)) {
                  list.add(b.authority); %>
                <td>${b.authority.substring(5)?.toLowerCase()?.encodeAsHTML()}
                <g:checkBox name="${b.authority}" value="${false}"/>(${b.description})
                </td>
                <% } %>
                </tr>
              </g:each>

            </table>
          </td>
        </tr>

        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button"><g:actionSubmit class="save" value="更新" action="update" /></span>
      <span class="button"><g:actionSubmit class="delete" value="删除" action="delete" onclick="return confirm('确定删除吗?');"/></span>
    </div>

  </g:form>

</div>
</body>
</html>

