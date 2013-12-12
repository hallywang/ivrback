<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>Create MagSysDept</title>
    <g:javascript>
    //保存
    function save(){
      document.forms[0].submit();
    }
  </g:javascript>
</head>
<body>

<div class="nav">

  <span class="menuButton"><g:link class="list" action="list">部门列表</g:link></span>
</div>

<div class="body">
  <h1>添加部门</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${dept}">
    <div class="errors">
      <g:renderErrors bean="${dept}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form action="save" method="post" onsubmit="return false">
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="deptName">部门名称:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: dept, field: 'deptName', 'errors')}">
            <input type="text" id="deptName" name="deptName" value="${dept?.deptName?.encodeAsHTML()}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="deptCode">部门编号:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: dept, field: 'deptCode', 'errors')}">
            <input type="text" id="deptCode" name="deptCode" value="${dept?.deptCode?.encodeAsHTML()}"/>
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

        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button"><input class="save" type="submit" value="创建部门" onClick="save();" /></span>
    </div>

  </g:form>

</div>
</body>
</html>

