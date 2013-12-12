<%@ page import="com.emag.gamecms.domain.template.TvWapTopic" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>专题列表</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="create" action="create">新增专题</g:link></span>
</div>
<div class="body">
  <h1>专题列表</h1>
  %{--添加查询条件--}%
  <g:form name="myForm" id="listForm" action="list">
    <div class="list">
      <tr>
        <td>
          &nbsp;关键字:&nbsp;<g:textField name="name" value="${params['name']}" style="width:100px;"/>
          <span class="button"><input class="save" type="submit" value="查询"/></span>
          &nbsp;(可使用专题名称、描述作为关键字模糊查询)
        </td>
      </tr>
    </div>
  </g:form>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="list">
    <table>
      <thead>
      <tr>

        <g:sortableColumn property="id" title="编号" width="5%"/>
        <g:sortableColumn property="name" title="名称" width="5%"/>

        <g:sortableColumn property="description" title="描述"/>
        <g:sortableColumn property="createTime" title="创建时间"/>

        <th width="5%">创建人</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${tvWapTopicList}" status="i" var="tvWapTopic">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

          <td><g:link action="show" id="${tvWapTopic.id}">${fieldValue(bean: tvWapTopic, field: 'id')}</g:link></td>
          <td>${fieldValue(bean: tvWapTopic, field: 'name')}</td>
          <td>${fieldValue(bean: tvWapTopic, field: 'description')}</td>

          <td>
              <g:formatDate date="${tvWapTopic?.createTime}" />
          </td>

          <td>${fieldValue(bean: tvWapTopic, field: 'creator')}</td>
          <td>
            <g:link action="show" id="${tvWapTopic.id}">查看</g:link>

            <g:link action="edit" id="${tvWapTopic.id}">编辑</g:link>

            <g:link action="delete" onclick="return confirm('确定删除吗?');" id="${tvWapTopic.id}">删除</g:link>

          </td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="paginateButtons">
    <g:paginate total="${TvWapTopic.count()}"/>
  </div>
</div>
</body>
</html>
