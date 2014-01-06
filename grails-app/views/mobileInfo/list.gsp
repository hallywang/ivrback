<%@ page import="com.emag.gamecms.domain.city.MobileInfo" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'mobileInfo.label', default: 'MobileInfo')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav" role="navigation">
  <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                             args="[entityName]"/></g:link></span>
</div>

<div id="list-mobileInfo" class="body" role="main">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
  </g:if>
  <g:form name="myForm" action="list" method="get">
    <div class="list">

      号段（7位）：<g:textField name="mobilePrefix" value="${params['mobilePrefix']}" maxlength="20" style="width:120px;"/>&nbsp;

      <span class="button"><input class="save" type="submit" value="查询" /></span><br/>


    </div>
  </g:form>


  <div class="list">
    <table>
      <thead>
      <tr>

        <g:sortableColumn property="mobilePrefix"
                          title="${message(code: 'mobileInfo.mobilePrefix.label', default: 'Mobile Prefix')}"/>

        <g:sortableColumn property="provinceId"
                          title="${message(code: 'mobileInfo.provinceId.label', default: 'Province Id')}"/>

        <g:sortableColumn property="provinceName"
                          title="${message(code: 'mobileInfo.provinceName.label', default: 'Province Name')}"/>

        <g:sortableColumn property="cityId" title="${message(code: 'mobileInfo.cityId.label', default: 'City Id')}"/>

        <g:sortableColumn property="cityName"
                          title="${message(code: 'mobileInfo.cityName.label', default: 'City Name')}"/>

        <g:sortableColumn property="countyId"
                          title="${message(code: 'mobileInfo.countyId.label', default: 'County Id')}"/>

      </tr>
      </thead>
      <tbody>
      <g:each in="${mobileInfoInstanceList}" status="i" var="mobileInfoInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

          <td><g:link action="show"
                      id="${mobileInfoInstance.id}">${fieldValue(bean: mobileInfoInstance, field: "mobilePrefix")}</g:link></td>

          <td>${fieldValue(bean: mobileInfoInstance, field: "provinceId")}</td>

          <td>${fieldValue(bean: mobileInfoInstance, field: "provinceName")}</td>

          <td>${fieldValue(bean: mobileInfoInstance, field: "cityId")}</td>

          <td>${fieldValue(bean: mobileInfoInstance, field: "cityName")}</td>

          <td>${fieldValue(bean: mobileInfoInstance, field: "countyId")}</td>

        </tr>
      </g:each>
      </tbody>
    </table>
  </div>

  <div class="paginateButtons">
    <g:paginate total="${mobileInfoInstanceTotal}" params="${params}"/>
  </div>
</div>
</body>
</html>
