<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsReferer.label', default: 'GameCmsReferer')}"/>
  <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
  <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
  <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${gameCmsRefererInstance}">
    <div class="errors">
      <g:renderErrors bean="${gameCmsRefererInstance}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form method="post">
    <g:hiddenField name="id" value="${gameCmsRefererInstance?.id}"/>
    <g:hiddenField name="version" value="${gameCmsRefererInstance?.version}"/>
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="referer"><g:message code="gameCmsReferer.referer.label" default="Referer"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsRefererInstance, field: 'referer', 'errors')}">
            <g:textField name="referer"  value="${gameCmsRefererInstance?.referer}"/>
          </td>
        </tr>

      %{--  <tr class="prop">
          <td valign="top" class="name">
            <label for="flag"><g:message code="gameCmsReferer.flag.label" default="Flag"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsRefererInstance, field: 'flag', 'errors')}">
            <g:textField name="flag" value="${fieldValue(bean: gameCmsRefererInstance, field: 'flag')}"/>
          </td>
        </tr>--}%

         <tr class="prop">
          <td valign="top" class="name">
            <label for="refererDesc"><g:message code="gameCmsReferer.refererDesc.label" default="refererDesc"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsRefererInstance, field: 'refererDesc', 'errors')}">
            <g:textField name="refererDesc" value="${fieldValue(bean: gameCmsRefererInstance, field: 'refererDesc')}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="refererStatus"><g:message code="gameCmsReferer.refererStatus.label" default="Referer Status"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsRefererInstance, field: 'refererStatus', 'errors')}">
               <g:select name="refererStatus" from="[1,0]" valueMessagePrefix="gameCmsReferer.refererStatus"
                    value="${gameCmsRefererInstance?.refererStatus}"/>
          </td>
        </tr>

     %{--   <tr class="prop">
          <td valign="top" class="name">
            <label for="updateTime"><g:message code="gameCmsReferer.updateTime.label" default="Update Time"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsRefererInstance, field: 'updateTime', 'errors')}">
            <g:datePicker name="updateTime" precision="day" value="${gameCmsRefererInstance?.updateTime}"/>
          </td>
        </tr>--}%

        </tbody>
      </table>
    </div>
    <div class="buttons">
      <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
      <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
    </div>
  </g:form>
</div>
</body>
</html>
