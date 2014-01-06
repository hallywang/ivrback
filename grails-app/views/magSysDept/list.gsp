<%@ page import="com.emag.gamecms.domain.system.MagSysDept" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>magSysDept List</title>
</head>
<body>

<div class="nav">

  <span class="menuButton"><g:link class="create" action="create">添加新部门</g:link></span>
</div>

<div class="body">
  <h1>部门列表</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
    <g:form name="myForm" action="list" method="get">
  <div class="list">
            <tr>
                <td>
                    部门名称<g:textField name="deptName" value="${params['deptName']}" maxlength="11" style="width:80px;"/>&nbsp;
                    部门编码 <g:textField name="deptCode" value="${params['deptCode']}" maxlength="11" style="width:80px;"/>  &nbsp;
                    描述<g:textField name="description" value="${params['description']}" maxlength="20" style="width:80px;"/>&nbsp;

                    <span class="button"><input class="save" type="submit" value="查询" /></span><br/>
                </td>
            </tr>
        </div>
    </g:form>
  <div class="list">
    <table>
      <thead>
      <tr>
        <g:sortableColumn property="id" title="编号"/>
        <g:sortableColumn property="deptName" title="部门名称"/>
        <g:sortableColumn property="deptCode" title="部门编码"/>
        <g:sortableColumn property="description" title="描述"/>
        <th>&nbsp;</th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${deptList}" status="i" var="dept">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>${dept.id}</td>
            <td>${dept.deptName?.encodeAsHTML()}</td>
            <td>${dept.deptCode?.encodeAsHTML()}</td>
            <td>${dept.description?.encodeAsHTML()}</td>
            <td class="actionButtons">
              <span class="actionButton">
                <g:link action="show" id="${dept.id}">显示</g:link>
                <g:link action="edit" id="${dept.id}">编辑</g:link>
              </span>
            </td>
          </tr>
      </g:each>
      </tbody>
    </table>
  </div>

  <div class="paginateButtons">
    <g:paginate total="${deptsTotalCount}"  params="${params}"/>
  </div>

</div>
</body>
</html>

