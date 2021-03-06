<%@ page import="com.emag.gamecms.domain.system.GameCmsExportSql" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="list">
    <table>
      <thead>
      <tr>

        <g:sortableColumn property="id" title="${message(code: 'gameCmsExportSql.id.label', default: 'Id')}"/>
        <g:sortableColumn property="sqlDesc" title="${message(code: 'gameCmsExportSql.sqlDesc.label', default: 'sqlDesc')}"/>
        <th class="sortable">操作</th>

      </tr>
      </thead>
      <tbody>
      <g:each in="${gameCmsExportSqlInstanceList}" status="i" var="gameCmsExportSqlInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

          <td><g:link action="show" id="${gameCmsExportSqlInstance.id}">${fieldValue(bean: gameCmsExportSqlInstance, field: "id")}</g:link></td>
          <td>${gameCmsExportSqlInstance.sqlDesc}</td>

          <td><g:link action="show" id="${gameCmsExportSqlInstance.id}">导出</g:link></td>

        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="paginateButtons">
    <g:paginate total="${gameCmsExportSqlInstanceTotal}"/>
  </div>
</div>
</body>
</html>
