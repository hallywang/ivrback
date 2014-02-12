<%@ page import="com.emag.gamecms.domain.system.GameCmsExportSql" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql')}"/>
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
  <g:form>
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="gameCmsExportSql.id.label" default="Id"/></td>

          <td valign="top" class="value">${fieldValue(bean: gameCmsExportSqlInstance, field: "id")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="content"><g:message code="gameCmsExportSql.sqlDesc.label" default="Content"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'sqlDesc', 'errors')}">
            <g:textField name="sqlDesc" value="${gameCmsExportSqlInstance?.sqlDesc}" readonly="true"/>
          </td>
        </tr>
        <tr class="prop">
          <td valign="top" class="name">
            <label for="content"><g:message code="gameCmsExportSql.content.label" default="Content"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'content', 'errors')}">
            <g:textArea name="content" style="height:100px" value="${gameCmsExportSqlInstance?.content}" readonly="true"/>
          </td>
        </tr>
        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramA"><g:message code="gameCmsExportSql.paramA.label" default="Param A"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramA', 'errors')}">
            <g:textField name="paramA" value="${gameCmsExportSqlInstance?.paramA}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramB"><g:message code="gameCmsExportSql.paramB.label" default="Param B"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramB', 'errors')}">
            <g:textField name="paramB" value="${gameCmsExportSqlInstance?.paramB}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramC"><g:message code="gameCmsExportSql.paramC.label" default="Param C"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramC', 'errors')}">
            <g:textField name="paramC" value="${gameCmsExportSqlInstance?.paramC}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramD"><g:message code="gameCmsExportSql.paramD.label" default="Param D"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramD', 'errors')}">
            <g:textField name="paramD" value="${gameCmsExportSqlInstance?.paramD}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramE"><g:message code="gameCmsExportSql.paramE.label" default="Param E"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramE', 'errors')}">
            <g:textField name="paramE" value="${gameCmsExportSqlInstance?.paramE}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramF"><g:message code="gameCmsExportSql.paramF.label" default="Param F"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramF', 'errors')}">
            <g:textField name="paramF" value="${gameCmsExportSqlInstance?.paramF}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramG"><g:message code="gameCmsExportSql.paramG.label" default="Param G"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramG', 'errors')}">
            <g:textField name="paramG" value="${gameCmsExportSqlInstance?.paramG}"/>
          </td>
        </tr>
        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramH"><g:message code="gameCmsExportSql.paramH.label" default="Param H"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramH', 'errors')}">
            <g:textField name="paramH" value="${gameCmsExportSqlInstance?.paramH}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramI"><g:message code="gameCmsExportSql.paramI.label" default="Param I"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramI', 'errors')}">
            <g:textField name="paramI" value="${gameCmsExportSqlInstance?.paramI}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="paramJ"><g:message code="gameCmsExportSql.paramJ.label" default="Param J"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsExportSqlInstance, field: 'paramJ', 'errors')}">
            <g:textField name="paramJ" value="${gameCmsExportSqlInstance?.paramJ}"/>
          </td>
        </tr>

        </tbody>
      </table>
    </div>
    <div class="buttons">

      <g:hiddenField name="id" value="${gameCmsExportSqlInstance?.id}"/>

      <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
      <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
      <span class="button"><g:actionSubmit class="export" action="exportSql" value="${message(code: 'default.button.export.label', default: 'export')}"/></span>
      <span class="button"><g:actionSubmit class="export" action="excuteSql" value="${message(code: 'default.button.excute.label', default: 'excuteSql')}"/></span>

    </div>
  </g:form>
</div>
</body>
</html>
