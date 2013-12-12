<%@ page import="com.emag.gamecms.domain.ivr.IvrServiceInfo" %>


<table>
    <tbody>
    
<tr class="prop">
    <td valign="top" class="name">
        <label for="serviceName">
            <g:message code="ivrServiceInfo.serviceName.label" default="Service Name"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="serviceName" maxlength="40" required="" value="${ivrServiceInfoInstance?.serviceName}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="serviceId">
            <g:message code="ivrServiceInfo.serviceId.label" default="Service Id"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="serviceId" maxlength="40" required="" value="${ivrServiceInfoInstance?.serviceId}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="serviceDesc">
            <g:message code="ivrServiceInfo.serviceDesc.label" default="Service Desc"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="serviceDesc" maxlength="80" value="${ivrServiceInfoInstance?.serviceDesc}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="feeType">
            <g:message code="ivrServiceInfo.feeType.label" default="Fee Type"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="feeType" maxlength="20" value="${ivrServiceInfoInstance?.feeType}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="fee">
            <g:message code="ivrServiceInfo.fee.label" default="Fee"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:field name="fee" type="number" value="${ivrServiceInfoInstance.fee}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="serviceType">
            <g:message code="ivrServiceInfo.serviceType.label" default="Service Type"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="serviceType" maxlength="20" value="${ivrServiceInfoInstance?.serviceType}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="startTime">
            <g:message code="ivrServiceInfo.startTime.label" default="Start Time"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:datePicker name="startTime" precision="day"  value="${ivrServiceInfoInstance?.startTime}" default="none" noSelection="['': '']" />
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="endTime">
            <g:message code="ivrServiceInfo.endTime.label" default="End Time"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:datePicker name="endTime" precision="day"  value="${ivrServiceInfoInstance?.endTime}" default="none" noSelection="['': '']" />
    </td>
</tr>



    </tbody>
</table>

