<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
  <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                             args="[entityName]"/></g:link></span>
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

        <g:sortableColumn property="id" title="${message(code: 'gameCmsProtectUrl.id.label', default: 'Id')}"/>

        <g:sortableColumn property="url" title="${message(code: 'gameCmsProtectUrl.url.label', default: 'Url')}"/>

        <g:sortableColumn property="urlStatus"
                          title="${message(code: 'gameCmsProtectUrl.urlStatus.label', default: 'urlStatus')}"/>

        <g:sortableColumn property="byIp" title="是否需要ip验证"/>

        <g:sortableColumn property="allowReferer"
                          title="${message(code: 'gameCmsProtectUrl.allowReferer.label', default: 'Allow Referer')}"/>
        <g:sortableColumn property="blockReferer"
                          title="${message(code: 'gameCmsProtectUrl.blockReferer.label', default: 'Block Referer')}"/>
        <g:sortableColumn property="isAllowNull"
                          title="${message(code: 'gameCmsProtectUrl.isAllowNull.label', default: 'isAllowNull')}"/>

        <g:sortableColumn property="afterBlock"
                          title="${message(code: 'gameCmsProtectUrl.afterBlock.label', default: 'afterBlock')}"/>


        <g:sortableColumn property="startTime"
                          title="${message(code: 'gameCmsProtectUrl.startTime.label', default: 'Start Time')}"/>

        <g:sortableColumn property="endTime"
                          title="${message(code: 'gameCmsProtectUrl.endTime.label', default: 'End Time')}"/>

      </tr>
      </thead>
      <tbody>
      <g:each in="${gameCmsProtectUrlInstanceList}" status="i" var="gameCmsProtectUrlInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

          <td><g:link action="show"
                      id="${gameCmsProtectUrlInstance.id}">${fieldValue(bean: gameCmsProtectUrlInstance, field: "id")}</g:link></td>

          <td>${fieldValue(bean: gameCmsProtectUrlInstance, field: "url")}</td>

          <td>
            <g:if test="${gameCmsProtectUrlInstance?.urlStatus==1}">
              <span style="color: red; ">
            </g:if>
            <g:message code="gameCmsProtectUrl.urlStatus.${gameCmsProtectUrlInstance?.urlStatus}"/>
            <g:if test="${gameCmsProtectUrlInstance?.urlStatus==1}">
              </span>
            </g:if>
          </td>

          <td>
            <g:if test="${gameCmsProtectUrlInstance.byIp == 0}">  <span style="color: red; ">否</span></g:if>
            <g:else>是</g:else>
          </td>

          <td>${fieldValue(bean: gameCmsProtectUrlInstance, field: "allowReferer")}</td>

          <td>${fieldValue(bean: gameCmsProtectUrlInstance, field: "blockReferer")}</td>

          <td>${fieldValue(bean: gameCmsProtectUrlInstance, field: "isAllowNull")}</td>

          <td>${fieldValue(bean: gameCmsProtectUrlInstance, field: "afterBlock")}</td>

          <td><g:formatDate date="${gameCmsProtectUrlInstance.startTime}"/></td>

          <td><g:formatDate date="${gameCmsProtectUrlInstance.endTime}"/></td>

        </tr>
      </g:each>
      </tbody>
    </table>
  </div>

  <div class="paginateButtons">
    <g:paginate total="${gameCmsProtectUrlInstanceTotal}"/>
  </div>
</div>
</body>
</html>
