<%@ page import="com.emag.gamecms.domain.ivr.IvrBlackUser" %>


<table>
    <tbody>
    
<tr class="prop">
    <td valign="top" class="name">
        <label for="msisdn">
            <g:message code="ivrBlackUser.msisdn.label" default="Msisdn"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="msisdn" maxlength="20" required="" value="${ivrBlackUserInstance?.msisdn}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="userType">
            <g:message code="ivrBlackUser.userType.label" default="User Type"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:field name="userType" type="number" value="${ivrBlackUserInstance.userType}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="scope">
            <g:message code="ivrBlackUser.scope.label" default="Scope"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">
      <g:select name="scope" from="${com.emag.gamecms.domain.ivr.IvrServiceInfo.list()}"
                optionKey="serviceId"
                value="${ivrBlackUserInstance?.scope}" optionValue="serviceName"
                noSelection="${['0': '-- 全局 --']}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="status">
            <g:message code="ivrBlackUser.status.label" default="Status"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">
        <g:select name="status" from="${[1,0]}" valueMessagePrefix="ivrBlackUser.status" value="${ivrBlackUserInstance?.status}"/>

    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="comment">
            <g:message code="ivrBlackUser.comment.label" default="Comment"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="comment" maxlength="40" value="${ivrBlackUserInstance?.comment}"/>
    </td>
</tr>



    </tbody>
</table>

