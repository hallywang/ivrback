<%@ page import="com.emag.gamecms.domain.ivr.IvrChannelNotice" %>


<table>
  <tbody>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="channelCode">
        <g:message code="ivrChannelNotice.channelCode.label" default="Channel Code"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="channelCode" value="${ivrChannelNoticeInstance?.channelCode}"/>
    </td>
  </tr>


  <tr class="prop">
    <td valign="top" class="name">
      <label for="serviceId">
        <g:message code="ivrChannelNotice.serviceId.label" default="Service Id"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="serviceId" value="${ivrChannelNoticeInstance?.serviceId}"/>
    </td>
  </tr>
  <tr class="prop">
    <td valign="top" class="name">
      <label for="noticeUrl">
        <g:message code="ivrChannelNotice.noticeUrl.label" default="Notice Url"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="noticeUrl" value="${ivrChannelNoticeInstance?.noticeUrl}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label for="timeFormat">
        <g:message code="ivrChannelNotice.timeFormat.label" default="timeFormat"/>

      </label>
    </td>
    <td valign="top" class="value">
      <g:textField name="timeFormat" value="${ivrChannelNoticeInstance?.timeFormat}"/>
    </td>
  </tr>
  <tr class="prop">
    <td valign="top" class="name">
      <label for="status">
        <g:message code="status.label" default="Status"/>
        <span class="required-indicator">*</span>
      </label>
    </td>
    <td valign="top" class="value">
      <g:field name="status" type="number" value="${ivrChannelNoticeInstance.status}" required=""/>
    </td>
  </tr>

  </tbody>
</table>

