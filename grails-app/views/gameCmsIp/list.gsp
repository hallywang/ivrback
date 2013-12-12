<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsIp.label', default: 'GameCmsIp')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
  <span class="menuButton"><g:link class="create" action="importFile">导入${message(code: 'gameCmsIp.label', default: 'GameCmsIp')}</g:link></span>
</div>
<div class="body">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>

%{--添加查询条件--}%
  <g:form name="myForm" action="list">
    <div class="list">
      <tr>
        <td>
          &nbsp;ip地址<input type="text" name="ip" value="${params['ip']}" maxlength="15" style="width:100px;">
          &nbsp;&nbsp;标志<input type="text" name="flag" value="${params['flag']}" maxlength="2" style="width:100px;">
          <span class="button"><input class="save" type="submit" value="查询"/></span>
        </td>
      </tr>
    </div>
  </g:form>

  <div class="list">
    <table>
      <thead>
      <tr>

        <g:sortableColumn property="id" title="${message(code: 'gameCmsIp.id.label', default: 'Id')}"/>

        <g:sortableColumn property="ip" title="${message(code: 'gameCmsIp.ip.label', default: 'Ip')}"/>

        <g:sortableColumn property="flag" title="${message(code: 'gameCmsIp.flag.label', default: 'Flag')}"/>

        <g:sortableColumn property="description" title="${message(code: 'gameCmsIp.description.label', default: 'Description')}"/>

        <g:sortableColumn property="createDate" title="${message(code: 'gameCmsIp.createDate.label', default: 'Create Date')}"/>

        <g:sortableColumn property="updateDate" title="${message(code: 'gameCmsIp.updateDate.label', default: 'Update Date')}"/>

      </tr>
      </thead>
      <tbody>
      <g:each in="${gameCmsIpInstanceList}" status="i" var="gameCmsIpInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

          <td><g:link action="show" id="${gameCmsIpInstance.id}">${fieldValue(bean: gameCmsIpInstance, field: "id")}</g:link></td>

          <td>${fieldValue(bean: gameCmsIpInstance, field: "ip")}</td>

          <td>${fieldValue(bean: gameCmsIpInstance, field: "flag")}</td>

          <td>${fieldValue(bean: gameCmsIpInstance, field: "description")}</td>

          <td>
              <g:formatDate date="${gameCmsIpInstance?.createDate}" />
          </td>

          <td>
              <g:formatDate date="${gameCmsIpInstance?.updateDate}" />
          </td>

        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="paginateButtons">
    <g:paginate total="${gameCmsIpInstanceTotal}" params="${params}"/>
  </div>
</div>
</body>
</html>
