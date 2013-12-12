<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>Create MagSysUser2</title>
</head>
<body>

<div class="nav">
  <span class="menuButton"><g:link class="list" action="list">用户列表</g:link></span>
</div>

<div class="body">
  <h1>添加用户</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${person}">
    <div class="errors">
      <g:renderErrors bean="${person}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form action="save" method="post">
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="username">登录名:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'username', 'errors')}">
            <input type="text" id="username" name="username" value="${person?.username?.encodeAsHTML()}"/>
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
            <g:checkBox name="enabled" value="${person?.enabled}"></g:checkBox>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label>所属部门:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'deptList', 'errors')}">
            <g:select name="dept.id" from="${deptList}"  optionValue="deptName" noSelection="['':'--请选择所属部门--']" optionKey="id" value="${person?.dept?.id}"/>
          </td>
        </tr>

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
            <label for="emailShow">显示Email:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'emailShow', 'errors')}">
            <g:checkBox name="emailShow" value="${person?.emailShow}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="email">手机号:</label>
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
          <td valign="top" class="name">所属角色:</td>
          <td valign="top" class="value ${hasErrors(bean: person, field: 'authorities', 'errors')}">
              <table>
       <g:each in="${authorityList}">
            <tr>
           <td align="left" >
              ${it.authority?.substring(5)?.toLowerCase()?.encodeAsHTML()}<g:checkBox name="${it.authority}"/>(${it.description})
              &nbsp;
            </td>
              </tr>
         </g:each>
                </table>
         </td>
        </tr>

            </table>
          </td>
        </tr>

        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button"><input class="save" type="submit" value="创建用户" onClick="save();" /></span>
    </div>

  </g:form>

</div>
<script>
  var table = document.getElementById("magSysUser2Table");
  for(var i=9;i< table.rows.length;i++){
   table.rows[i].cells[0].innerText='';
   table.rows[i].cells[1].rowspan++;
  }

</script>
</body>
</html>

