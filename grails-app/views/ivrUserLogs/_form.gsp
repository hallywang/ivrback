<%@ page import="com.emag.gamecms.domain.ivr.IvrUserLogs" %>


<table>
    <tbody>
    
<tr class="prop">
    <td valign="top" class="name">
        <label for="callNumber">
            <g:message code="ivrUserLogs.callNumber.label" default="Call Number"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="callNumber" value="${ivrUserLogsInstance?.callNumber}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="callSecond">
            <g:message code="ivrUserLogs.callSecond.label" default="Call Second"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">
        <g:field name="callSecond" type="number" value="${ivrUserLogsInstance.callSecond}" required=""/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="callTime">
            <g:message code="ivrUserLogs.callTime.label" default="Call Time"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">
        <g:datePicker name="callTime" precision="day"  value="${ivrUserLogsInstance?.callTime}"  />
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="createTime">
            <g:message code="ivrUserLogs.createTime.label" default="Create Time"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">
        <g:datePicker name="createTime" precision="day"  value="${ivrUserLogsInstance?.createTime}"  />
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="endTime">
            <g:message code="ivrUserLogs.endTime.label" default="End Time"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">
        <g:datePicker name="endTime" precision="day"  value="${ivrUserLogsInstance?.endTime}"  />
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="msisdn">
            <g:message code="ivrUserLogs.msisdn.label" default="Msisdn"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="msisdn" value="${ivrUserLogsInstance?.msisdn}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="serviceId">
            <g:message code="ivrUserLogs.serviceId.label" default="Service Id"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="serviceId" value="${ivrUserLogsInstance?.serviceId}"/>
    </td>
</tr>

    </tbody>
</table>

