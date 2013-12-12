<%@ page import="com.emag.gamecms.domain.system.GameCmsMailServer" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer')}"/>
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
        <td valign="top" class="name"><g:message code="gameCmsMailServer.id.label" default="Id"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsMailServerInstance, field: "id")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsMailServer.serverName.label" default="Server Name"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsMailServerInstance, field: "serverName")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsMailServer.smtpPort.label" default="Smtp Port"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsMailServerInstance, field: "smtpPort")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsMailServer.username.label" default="Username"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsMailServerInstance, field: "username")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsMailServer.password.label" default="Password"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsMailServerInstance, field: "password")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsMailServer.localhost.label" default="Localhost"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsMailServerInstance, field: "localhost")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsMailServer.subject.label" default="subject"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsMailServerInstance, field: "subject")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="gameCmsMailServer.text.label" default="text"/></td>

        <td valign="top" class="value">${fieldValue(bean: gameCmsMailServerInstance, field: "text")}</td>

      </tr>

      </tbody>
    </table>
  </div>
  <div class="buttons">
    <g:form>
      <g:hiddenField name="id" value="${gameCmsMailServerInstance?.id}"/>
      <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
      <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
    </g:form>
  </div>
</div>

<div class="body">
  <div>
    <a href="${request.contextPath}/gameCmsMailReceiver/create?mailServer.id=${gameCmsMailServerInstance.id}">+新增接收方</a>
  </div>
  <div class="list">
    <table>
      <thead>
      <tr>

        <th>${message(code: 'gameCmsMailReceiver.mailAddress.label', default: 'mailAddress')}</th>

        <th>操作</th>

      </tr>
      </thead>
      <tbody>
      <g:each in="${gameCmsMailServerInstance.receivers}" status="i" var="receiver">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

          <td><a href="${request.contextPath}/gameCmsMailReceiver/show/${receiver.id}?mailServer.id=${gameCmsMailServerInstance.id}">${receiver.mailAddress}</a></td>

          <td>
            <a href="${request.contextPath}/gameCmsMailReceiver/show/${receiver.id}?mailServer.id=${gameCmsMailServerInstance.id}">查看</a>

            <a href="${request.contextPath}/gameCmsMailReceiver/edit/${receiver.id}?mailServer.id=${gameCmsMailServerInstance.id}">编辑</a>
          </td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="paginateButtons">
  </div>
</div>

</body>
</html>
