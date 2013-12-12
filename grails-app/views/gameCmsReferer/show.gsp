<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsReferer.label', default: 'GameCmsReferer')}"/>
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
        <td valign="top" class="name"><g:message code="gameCmsReferer.id.label" default="Id"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsRefererInstance, field: "id")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsReferer.referer.label" default="Referer"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsRefererInstance, field: "referer")}</td>

      </tr>

       <tr class="prop">
          <td valign="top" class="name">
            <label for="refererDesc"><g:message code="gameCmsReferer.refererDesc.label" default="refererDesc"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsRefererInstance, field: 'refererDesc', 'errors')}">
         ${fieldValue(bean: gameCmsRefererInstance, field: 'refererDesc')}
          </td>
        </tr>

    %{--  <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsReferer.flag.label" default="Flag"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsRefererInstance, field: "flag")}</td>

      </tr>--}%

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsReferer.refererStatus.label" default="Referer Status"/></td>

        <td valign="top" class="value"><g:message code="gameCmsReferer.refererStatus.${gameCmsRefererInstance.refererStatus}" default="Series Desc"/></td></td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsReferer.updateTime.label" default="Update Time"/></td>

        <td valign="top" class="value">
            <g:formatDate date="${gameCmsRefererInstance?.updateTime}" />
        </td>

      </tr>

      </tbody>
    </table>
  </div>
  <div class="buttons">
    <g:form>
      <g:hiddenField name="id" value="${gameCmsRefererInstance?.id}"/>
      <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
      <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
    </g:form>
  </div>
</div>
</body>
</html>
