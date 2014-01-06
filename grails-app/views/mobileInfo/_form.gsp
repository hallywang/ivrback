<%@ page import="com.emag.gamecms.domain.city.MobileInfo" %>


<table>
  <tbody>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="mobilePrefix">
        <g:message code="mobileInfo.mobilePrefix.label" default="Mobile Prefix"/>
        <span class="required-indicator">*</span>
      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="mobilePrefix" required="" value="${mobileInfoInstance?.mobilePrefix}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="provinceId">
        <g:message code="mobileInfo.provinceId.label" default="Province Id"/>
        <span class="required-indicator">*</span>
      </label>
    </td>
    <td valign="top" class="value">
      <g:field name="provinceId" type="number" value="${mobileInfoInstance.provinceId}" required=""/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="provinceName">
        <g:message code="mobileInfo.provinceName.label" default="Province Name"/>
        <span class="required-indicator">*</span>
      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="provinceName" required="" value="${mobileInfoInstance?.provinceName}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="cityId">
        <g:message code="mobileInfo.cityId.label" default="City Id"/>
        <span class="required-indicator">*</span>
      </label>
    </td>
    <td valign="top" class="value">
      <g:field name="cityId" type="number" value="${mobileInfoInstance.cityId}" required=""/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="cityName">
        <g:message code="mobileInfo.cityName.label" default="City Name"/>
        <span class="required-indicator">*</span>
      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="cityName" required="" value="${mobileInfoInstance?.cityName}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="countyId">
        <g:message code="mobileInfo.countyId.label" default="County Id"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:field name="countyId" type="number" value="${mobileInfoInstance.countyId}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="countyName">
        <g:message code="mobileInfo.countyName.label" default="County Name"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="countyName" value="${mobileInfoInstance?.countyName}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="rese0">
        <g:message code="mobileInfo.rese0.label" default="Rese0"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="rese0" value="${mobileInfoInstance?.rese0}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="rese1">
        <g:message code="mobileInfo.rese1.label" default="Rese1"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="rese1" value="${mobileInfoInstance?.rese1}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="rese2">
        <g:message code="mobileInfo.rese2.label" default="Rese2"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="rese2" value="${mobileInfoInstance?.rese2}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="inDate">
        <g:message code="mobileInfo.inDate.label" default="In Date"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:datePicker name="inDate" precision="day" value="${mobileInfoInstance?.inDate}" default="none"
                    noSelection="['': '']"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="filename">
        <g:message code="mobileInfo.filename.label" default="Filename"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="filename" value="${mobileInfoInstance?.filename}"/>
    </td>
  </tr>

  </tbody>
</table>

