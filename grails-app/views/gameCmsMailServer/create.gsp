<%@ page import="com.emag.gamecms.domain.system.GameCmsMailServer" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer')}"/>
  <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
  <h1><g:message code="default.create.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${gameCmsMailServerInstance}">
    <div class="errors">
      <g:renderErrors bean="${gameCmsMailServerInstance}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form action="save" method="post">
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="serverName"><g:message code="gameCmsMailServer.serverName.label" default="Server Name"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsMailServerInstance, field: 'serverName', 'errors')}">
            <g:textField name="serverName" maxlength="60" value="${gameCmsMailServerInstance?.serverName}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="smtpPort"><g:message code="gameCmsMailServer.smtpPort.label" default="Smtp Port"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsMailServerInstance, field: 'smtpPort', 'errors')}">
            <g:textField name="smtpPort" value="${fieldValue(bean: gameCmsMailServerInstance, field: 'smtpPort')}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="username"><g:message code="gameCmsMailServer.username.label" default="Username"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsMailServerInstance, field: 'username', 'errors')}">
            <g:textField name="username" maxlength="60" value="${gameCmsMailServerInstance?.username}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="password"><g:message code="gameCmsMailServer.password.label" default="Password"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsMailServerInstance, field: 'password', 'errors')}">
            <g:textField name="password" maxlength="60" value="${gameCmsMailServerInstance?.password}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="localhost"><g:message code="gameCmsMailServer.localhost.label" default="Localhost"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsMailServerInstance, field: 'localhost', 'errors')}">
            <g:textField name="localhost" maxlength="60" value="${gameCmsMailServerInstance?.localhost}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="subject"><g:message code="gameCmsMailServer.subject.label" default="subject"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsMailServerInstance, field: 'subject', 'errors')}">
            <g:textField name="subject" maxlength="60" value="${gameCmsMailServerInstance?.subject}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="text"><g:message code="gameCmsMailServer.text.label" default="text"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsMailServerInstance, field: 'text', 'errors')}">
            <g:textField name="text" maxlength="60" value="${gameCmsMailServerInstance?.text}"/>
          </td>
        </tr>

        </tbody>
      </table>
    </div>
    <div class="buttons">
      <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
    </div>
  </g:form>
</div>
</body>
</html>
