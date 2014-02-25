<%@ page import="com.emag.gamecms.domain.system.MagSysUser2" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>MagSysUser2 List</title>
</head>
<body>

<div class="nav">

  <span class="menuButton"><g:link class="create" action="create">添加新用户</g:link></span>
</div>

<div class="body">
  <h1>用户列表</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
    <g:form name="myForm" action="list" method="get">
  <div class="list">
            <tr>
                <td>
                    登陆名<g:textField name="username" value="${params['username']}" maxlength="11" style="width:80px;"/>&nbsp;
                    真实姓名 <g:textField name="userRealName" value="${params['userRealName']}" maxlength="11" style="width:80px;"/>  &nbsp;
                    所属部门<g:select name="deptId" from="${deptList}"  optionValue="deptName" noSelection="['':'--请选择所属部门--']" optionKey="id" value="${params.deptId}"/>&nbsp;
                    手机号<g:textField name="mobile" value="${params['mobile']}" maxlength="20" style="width:80px;"/>&nbsp;
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
        <g:sortableColumn property="username" title="登录名"/>
        <g:sortableColumn property="userRealName" title="真实姓名"/>

        <th>所属部门</th>
        <g:sortableColumn property="enabled" title="是否有效"/>
        <g:sortableColumn property="mobile" title="手机号"/>
        <g:sortableColumn property="checkSmsYn" title="是否需要短信验证"/>
        <g:sortableColumn property="description" title="描述"/>
        <th>&nbsp;</th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${personList}" status="i" var="person">
         <g:if test="${person.username!='root'}">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>${person.id}</td>
            <td>${person.username?.encodeAsHTML()}</td>
            <td>${person.userRealName?.encodeAsHTML()}</td>

            <td>${person?.dept?.deptName}</td>
            <td>${person.enabled?.encodeAsHTML()}</td>
            <td>${person.mobile?.encodeAsHTML()}</td>
            <td>${person.checkSmsYn?.encodeAsHTML()}</td>
            <td>${person.description?.encodeAsHTML()}</td>
            <td class="actionButtons">
              <span class="actionButton">
                <g:link action="show" id="${person.id}">显示</g:link>
                <g:link action="edit" id="${person.id}">编辑</g:link>
              </span>
            </td>
          </tr>
         </g:if>
      </g:each>
      </tbody>
    </table>
  </div>

  <div class="paginateButtons">
    <g:paginate total="${personsTotalCoun}"  params="${params}"/>
  </div>

</div>
</body>
</html>

