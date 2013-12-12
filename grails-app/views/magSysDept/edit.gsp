<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>Edit MagSysDept</title>
    <g:javascript>
    //更新
    function update(){
      document.forms[0].submit();
    }

    //删除
    function del(){
      if(confirm('确认删除吗?')){
        document.forms[0].action = "${request.contextPath}/magSysDept/delete";
        document.forms[0].submit();
      }
    }
  </g:javascript>
</head>
<body>

<div class="nav">

  <span class="menuButton"><g:link class="list" action="list">部门列表</g:link></span>
  <span class="menuButton"><g:link class="create" action="create">添加部门</g:link></span>
</div>

<div class="body">
  <h1>编辑部门</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${dept}">
    <div class="errors">
      <g:renderErrors bean="${dept}" as="list"/>
    </div>
  </g:hasErrors>

  <div class="prop">
    <span class="name">编号:</span>
    <span class="value">${dept?.id}</span>
  </div>

  <g:form method="post" action="update" onsubmit="return false">
    <input type="hidden" name="id" value="${dept?.id}"/>
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
            <label for="deptCode">部门编码:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: dept, field: 'deptCode', 'errors')}">
            <input type="text" id="deptCode" name="deptCode" value="${dept?.deptCode?.encodeAsHTML()}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="description">描述:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: dept, field: 'description', 'errors')}">
            <input type="text" id="description" name="description" value="${dept?.description?.encodeAsHTML()}"/>
          </td>
        </tr>

        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button"><g:actionSubmit class="save" value="更新" action="update" onClick="update();" /></span>
      <span class="button"><g:actionSubmit class="delete" onClick="del();" value="删除" action="delete"/></span>
    </div>

  </g:form>

</div>
</body>
</html>

