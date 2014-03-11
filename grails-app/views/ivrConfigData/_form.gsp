<%@ page import="com.emag.gamecms.domain.ivr.IvrConfigData" %>


<table>
    <tbody>
    
<tr class="prop">
    <td valign="top" class="name">
        <label for="operateId">
            <g:message code="ivrConfigData.operateId.label" default="Operate Id"/>
            
        </label>
    </td>
    <td valign="top" class="value">
      <g:select name="operateId" from="${com.emag.constants.IvrConstants.operateIds}"
                value="${ivrConfigDataInstance?.operateId}"  optionKey="key" optionValue="value"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="serviceId">
            <g:message code="ivrConfigData.serviceId.label" default="Service Id"/>
            
        </label>
    </td>
    <td valign="top" class="value">

      <g:select name="serviceId" from="${com.emag.gamecms.domain.ivr.IvrServiceInfo.list()}"
                optionKey="serviceId"
                value="${ivrConfigDataInstance?.serviceId}" optionValue="serviceName"/>

    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="content">
            <g:message code="ivrConfigData.content.label" default="Content"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="content" maxlength="200" value="${ivrConfigDataInstance?.content}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="status">
            <g:message code="ivrConfigData.status.label" default="Status"/>
            <span class="required-indicator">*</span>
        </label>
    </td>
    <td valign="top" class="value">

      <g:select name="status" from="${[1,0]}" valueMessagePrefix="ivrConfigDataInstance.status"
                value="${ivrConfigDataInstance.status}"/>

    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="comment">
            <g:message code="ivrConfigData.comment.label" default="Comment"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="comment" maxlength="200" value="${ivrConfigDataInstance?.comment}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="paramA">
            <g:message code="ivrConfigData.paramA.label" default="Param A"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="paramA" value="${ivrConfigDataInstance?.paramA}"/>
    </td>
</tr>

<tr class="prop">
    <td valign="top" class="name">
        <label for="configType">
            <g:message code="ivrConfigData.configType.label" default="Config Type"/>
            
        </label>
    </td>
    <td valign="top" class="value">
        <g:textField name="configType" value="${ivrConfigDataInstance?.configType}"/>
    </td>
</tr>

    </tbody>
</table>

