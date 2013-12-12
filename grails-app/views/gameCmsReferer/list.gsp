<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsReferer.label', default: 'GameCmsReferer')}"/>
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

        <g:sortableColumn property="id" title="${message(code: 'gameCmsReferer.id.label', default: 'Id')}"/>

        <g:sortableColumn property="referer" title="${message(code: 'gameCmsReferer.referer.label', default: 'Referer')}"/>
        <g:sortableColumn property="refererDesc" title="${message(code: 'gameCmsReferer.refererDesc.label', default: 'refererDesc')}"/>

        <g:sortableColumn property="flag" title="${message(code: 'gameCmsReferer.flag.label', default: 'Flag')}"/>

        <g:sortableColumn property="refererStatus" title="${message(code: 'gameCmsReferer.refererStatus.label', default: 'Referer Status')}"/>

        <g:sortableColumn property="updateTime" title="${message(code: 'gameCmsReferer.updateTime.label', default: 'Update Time')}"/>

      </tr>
      </thead>
      <tbody>
      <g:each in="${gameCmsRefererInstanceList}" status="i" var="gameCmsRefererInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

          <td><g:link action="show" id="${gameCmsRefererInstance.id}">${fieldValue(bean: gameCmsRefererInstance, field: "id")}</g:link></td>

          <td>${fieldValue(bean: gameCmsRefererInstance, field: "referer")}</td>
           <td>${fieldValue(bean: gameCmsRefererInstance, field: "refererDesc")}</td>

          <td>${fieldValue(bean: gameCmsRefererInstance, field: "flag")}</td>

          <td><g:message code="gameCmsReferer.refererStatus.${gameCmsRefererInstance.refererStatus}" default="Series Desc"/></td>

          <td>
              <g:formatDate date="${gameCmsRefererInstance?.updateTime}" />
          </td>

        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="paginateButtons">
    <g:paginate total="${gameCmsRefererInstanceTotal}"/>
  </div>
</div>
</body>
</html>
