<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl')}"/>
  <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
  <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
  <h1><g:message code="default.show.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="dialog">
    <table>
      <tbody>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsProtectUrl.id.label" default="Id"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsProtectUrlInstance, field: "id")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsProtectUrl.url.label" default="Url"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsProtectUrlInstance, field: "url")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsProtectUrl.startTime.label" default="Start Time"/></td>

        <td valign="top" class="value"><g:formatDate date="${gameCmsProtectUrlInstance?.startTime}"/></td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsProtectUrl.endTime.label" default="End Time"/></td>

        <td valign="top" class="value"><g:formatDate date="${gameCmsProtectUrlInstance?.endTime}"/></td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">是否需要ip验证</td>

        <td valign="top" class="value">
          <g:if test="${gameCmsProtectUrlInstance?.byIp == 0}">
            否
          </g:if>
          <g:else>
            是
          </g:else>
        </td>
      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsProtectUrl.allowReferer.label" default="Allow Referer"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsProtectUrlInstance, field: "allowReferer")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsProtectUrl.blockReferer.label" default="Block Referer"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsProtectUrlInstance, field: "blockReferer")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsProtectUrl.afterBlock.label" default="After Block"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsProtectUrlInstance, field: "afterBlock")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsProtectUrl.urlStatus.label" default="Url Status"/></td>

        <td valign="top" class="value">
          <g:message code="gameCmsProtectUrl.urlStatus.${gameCmsProtectUrlInstance?.urlStatus}"/>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsProtectUrl.isAllowNull.label" default="Is Allow Null"/></td>

        <td valign="top" class="value"><g:formatBoolean boolean="${gameCmsProtectUrlInstance?.isAllowNull}"/></td>

      </tr>

      </tbody>
    </table>
  </div>
  <div class="buttons">
    <g:form>
      <g:hiddenField name="id" value="${gameCmsProtectUrlInstance?.id}"/>
      <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
      <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
    </g:form>
  </div>
</div>
</body>
</html>
