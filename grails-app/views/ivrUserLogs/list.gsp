<%@ page import="com.emag.gamecms.domain.ivr.IvrUserLogs" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'ivrUserLogs.label', default: 'IvrUserLogs')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav" role="navigation">
 %{-- <span class="menuButton"><g:link class="create" action="create">
    <g:message code="default.new.label"    args="[entityName]"/></g:link></span>--}%
</div>

<div id="list-ivrUserLogs" class="body" role="main">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
  </g:if>

  <g:form name="myForm" action="list" method="get">
    <div class="list">

      手机号：<g:textField name="msisdn" value="${params['msisdn']}" maxlength="20" style="width:120px;"/>&nbsp;
      业务代码：<g:select name="serviceId" from="${com.emag.gamecms.domain.ivr.IvrServiceInfo.list()}"
                     optionKey="serviceId"
                     value="${params?.serviceId}" optionValue="serviceName"
                     noSelection="${['': '--ALL--']}"/>
     操作类型: <g:select name="operateId" from="${com.emag.constants.IvrConstants.operateIds}"
                    value="${params?.operateId}"  optionKey="key" optionValue="value"
                    noSelection="${['': '--ALL--']}"/>

      <span class="button"><input class="save" type="submit" value="查询" /></span><br/>


    </div>
  </g:form>


  <div class="list">
    <table>
      <thead>
      <tr>

        <g:sortableColumn property="msisdn" title="${message(code: 'ivrUserLogs.msisdn.label', default: 'Msisdn')}"/>

        <g:sortableColumn property="serviceId"
                          title="${message(code: 'ivrUserLogs.serviceId.label', default: 'Service Id')}"/>

        <g:sortableColumn property="callNumber"
                          title="${message(code: 'ivrUserLogs.callNumber.label', default: 'Call Number')}"/>
        <g:sortableColumn property="provinceName"
                          title="${message(code: 'ivrUserLogs.provinceName.label', default: 'areaCode')}"/>
        <g:sortableColumn property="cityName"
                          title="${message(code: 'ivrUserLogs.cityName.label', default: 'areaCode')}"/>

        <g:sortableColumn property="callTime"
                          title="${message(code: 'ivrUserLogs.callTime.label', default: 'Call Time')}"/>
        <g:sortableColumn property="endTime"
                          title="${message(code: 'ivrUserLogs.endTime.label', default: 'End Time')}"/>
        <g:sortableColumn property="callSecond"
                          title="${message(code: 'ivrUserLogs.callSecond.label', default: 'Call Second')}"/>
        <g:sortableColumn property="operateId"
                          title="${message(code: 'ivrUserLogs.operateId.label', default: 'operateId')}"/>
        <g:sortableColumn property="touchButton"
                          title="${message(code: 'ivrUserLogs.touchButton.label', default: 'touchButton')}"/>

        <g:sortableColumn property="createTime"
                          title="${message(code: 'ivrUserLogs.createTime.label', default: 'Create Time')}"/>

      </tr>
      </thead>
      <tbody>
      <g:each in="${ivrUserLogsInstanceList}" status="i" var="ivrUserLogsInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

          <td>${fieldValue(bean: ivrUserLogsInstance, field: "msisdn")}</td>

          <td>${fieldValue(bean: ivrUserLogsInstance, field: "serviceId")}</td>

          <td>${fieldValue(bean: ivrUserLogsInstance, field: "callNumber")}</td>

          <td>${fieldValue(bean: ivrUserLogsInstance, field: "provinceName")}</td>
          <td>${fieldValue(bean: ivrUserLogsInstance, field: "cityName")}</td>

          <td><g:formatDate date="${ivrUserLogsInstance.callTime}"/></td>


          <td><g:formatDate date="${ivrUserLogsInstance.endTime}"/></td>


          <td>${fieldValue(bean: ivrUserLogsInstance, field: "callSecond")}</td>
          <td>${fieldValue(bean: ivrUserLogsInstance, field: "operateId")}</td>

          <td>${fieldValue(bean: ivrUserLogsInstance, field: "touchButton")}</td>


          <td><g:formatDate date="${ivrUserLogsInstance.createTime}"/></td>

        </tr>
      </g:each>
      </tbody>
    </table>
  </div>

  <div class="paginateButtons">
    <g:paginate total="${ivrUserLogsInstanceTotal}" params="${params}"/>
  </div>
</div>
</body>
</html>
