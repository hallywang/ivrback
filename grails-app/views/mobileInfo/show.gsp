<%@ page import="com.emag.gamecms.domain.city.MobileInfo" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'mobileInfo.label', default: 'MobileInfo')}"/>
  <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav" role="navigation">
  <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                             args="[entityName]"/></g:link></span>
  <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                         args="[entityName]"/></g:link></span>
</div>

<div id="show-mobileInfo" class="body" role="main">
  <h1><g:message code="default.show.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
  </g:if>
  <div class="dialog">
    <table>
      <tbody>

      <g:if test="${mobileInfoInstance?.mobilePrefix}">
        <tr class="prop">
          <td valign="top" class="name"><span id="mobilePrefix-label" class="property-label"><g:message
                  code="mobileInfo.mobilePrefix.label" default="Mobile Prefix"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="mobilePrefix-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                            field="mobilePrefix"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.provinceId}">
        <tr class="prop">
          <td valign="top" class="name"><span id="provinceId-label" class="property-label"><g:message
                  code="mobileInfo.provinceId.label" default="Province Id"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="provinceId-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                          field="provinceId"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.provinceName}">
        <tr class="prop">
          <td valign="top" class="name"><span id="provinceName-label" class="property-label"><g:message
                  code="mobileInfo.provinceName.label" default="Province Name"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="provinceName-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                            field="provinceName"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.cityId}">
        <tr class="prop">
          <td valign="top" class="name"><span id="cityId-label" class="property-label"><g:message
                  code="mobileInfo.cityId.label" default="City Id"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="cityId-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                      field="cityId"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.cityName}">
        <tr class="prop">
          <td valign="top" class="name"><span id="cityName-label" class="property-label"><g:message
                  code="mobileInfo.cityName.label" default="City Name"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="cityName-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                        field="cityName"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.countyId}">
        <tr class="prop">
          <td valign="top" class="name"><span id="countyId-label" class="property-label"><g:message
                  code="mobileInfo.countyId.label" default="County Id"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="countyId-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                        field="countyId"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.countyName}">
        <tr class="prop">
          <td valign="top" class="name"><span id="countyName-label" class="property-label"><g:message
                  code="mobileInfo.countyName.label" default="County Name"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="countyName-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                          field="countyName"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.rese0}">
        <tr class="prop">
          <td valign="top" class="name"><span id="rese0-label" class="property-label"><g:message
                  code="mobileInfo.rese0.label" default="Rese0"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="rese0-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                     field="rese0"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.rese1}">
        <tr class="prop">
          <td valign="top" class="name"><span id="rese1-label" class="property-label"><g:message
                  code="mobileInfo.rese1.label" default="Rese1"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="rese1-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                     field="rese1"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.rese2}">
        <tr class="prop">
          <td valign="top" class="name"><span id="rese2-label" class="property-label"><g:message
                  code="mobileInfo.rese2.label" default="Rese2"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="rese2-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                     field="rese2"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.inDate}">
        <tr class="prop">
          <td valign="top" class="name"><span id="inDate-label" class="property-label"><g:message
                  code="mobileInfo.inDate.label" default="In Date"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="inDate-label"><g:formatDate
                    date="${mobileInfoInstance?.inDate}"/></span>

          </td>
        </tr>
      </g:if>

      <g:if test="${mobileInfoInstance?.filename}">
        <tr class="prop">
          <td valign="top" class="name"><span id="filename-label" class="property-label"><g:message
                  code="mobileInfo.filename.label" default="Filename"/></span></td>
          <td valign="top" class="value">

            <span class="property-value" aria-labelledby="filename-label"><g:fieldValue bean="${mobileInfoInstance}"
                                                                                        field="filename"/></span>

          </td>
        </tr>
      </g:if>

      </tbody>
    </table>
  </div>

  <div class="buttons">
    <g:form>
      <g:hiddenField name="id" value="${mobileInfoInstance?.id}"/>
      <span class="button"><g:actionSubmit class="edit" action="edit"
                                           value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
      <span class="button"><g:actionSubmit class="delete" action="delete"
                                           value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                           onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
    </g:form>
  </div>
</div>
</body>
</html>
