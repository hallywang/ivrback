<%@ page import="com.emag.gamecms.domain.job.GameCmsJobinfo" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo')}"/>
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
  <g:hasErrors bean="${gameCmsJobinfoInstance}">
    <div class="errors">
      <g:renderErrors bean="${gameCmsJobinfoInstance}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form action="save" method="post">
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="jobName"><g:message code="gameCmsJobinfo.jobName.label" default="Job Name"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsJobinfoInstance, field: 'jobName', 'errors')}">
            <g:textField name="jobName" value="${gameCmsJobinfoInstance?.jobName}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="jobType"><g:message code="gameCmsJobinfo.jobType.label" default="Job Type"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsJobinfoInstance, field: 'jobType', 'errors')}">
            <g:select name="jobType" from="[[type:'tenMin',name:'每十分钟运行一次'],[type:'thirtyMIn',name:'每三十分钟运行一次'],[type:'oneHour',name:'每小时运行一次'],[type:'oneDay',name:'每天运行一次'],[type:'oneOclock', name:'01时运行一次'],[type:'twoOclock',name:'02时运行一次'],[type:'eightOclock',name:'08时运行一次'],[type:'fourteenOclock',name:'14时运行一次'],[type:'oneWeek',name:'每周运行一次'],[type:'oneMonth',name:'每月运行一次']]" optionKey="type" optionValue="name"
                    noSelection="${['' : '-- 请选择 --']}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="jobName"><g:message code="gameCmsJobinfo.orders.label" default="orders"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsJobinfoInstance, field: 'orders', 'errors')}">
            <g:textField name="orders" value="${gameCmsJobinfoInstance?.orders}"/>
            (升序，数字越小，级别越高)
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="jobService"><g:message code="gameCmsJobinfo.jobService.label" default="Job Service"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsJobinfoInstance, field: 'jobService', 'errors')}">
            <g:textField name="jobService" value="${gameCmsJobinfoInstance?.jobService}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="jobMethod"><g:message code="gameCmsJobinfo.jobMethod.label" default="Job Method"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsJobinfoInstance, field: 'jobMethod', 'errors')}">
            <g:textField name="jobMethod" value="${gameCmsJobinfoInstance?.jobMethod}"/>
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
