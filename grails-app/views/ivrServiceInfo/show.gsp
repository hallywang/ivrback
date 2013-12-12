
<%@ page import="com.emag.gamecms.domain.ivr.IvrServiceInfo" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav" role="navigation">
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div id="show-ivrServiceInfo" class="body" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
          <tbody>
            
            <g:if test="${ivrServiceInfoInstance?.serviceName}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="serviceName-label" class="property-label"><g:message code="ivrServiceInfo.serviceName.label" default="Service Name" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="serviceName-label"><g:fieldValue bean="${ivrServiceInfoInstance}" field="serviceName"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrServiceInfoInstance?.serviceId}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="serviceId-label" class="property-label"><g:message code="ivrServiceInfo.serviceId.label" default="Service Id" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="serviceId-label"><g:fieldValue bean="${ivrServiceInfoInstance}" field="serviceId"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrServiceInfoInstance?.serviceDesc}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="serviceDesc-label" class="property-label"><g:message code="ivrServiceInfo.serviceDesc.label" default="Service Desc" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="serviceDesc-label"><g:fieldValue bean="${ivrServiceInfoInstance}" field="serviceDesc"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrServiceInfoInstance?.feeType}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="feeType-label" class="property-label"><g:message code="ivrServiceInfo.feeType.label" default="Fee Type" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="feeType-label"><g:fieldValue bean="${ivrServiceInfoInstance}" field="feeType"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrServiceInfoInstance?.fee}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="fee-label" class="property-label"><g:message code="ivrServiceInfo.fee.label" default="Fee" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="fee-label"><g:fieldValue bean="${ivrServiceInfoInstance}" field="fee"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrServiceInfoInstance?.serviceType}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="serviceType-label" class="property-label"><g:message code="ivrServiceInfo.serviceType.label" default="Service Type" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="serviceType-label"><g:fieldValue bean="${ivrServiceInfoInstance}" field="serviceType"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrServiceInfoInstance?.startTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="startTime-label" class="property-label"><g:message code="ivrServiceInfo.startTime.label" default="Start Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="startTime-label"><g:formatDate date="${ivrServiceInfoInstance?.startTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrServiceInfoInstance?.endTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="endTime-label" class="property-label"><g:message code="ivrServiceInfo.endTime.label" default="End Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="endTime-label"><g:formatDate date="${ivrServiceInfoInstance?.endTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrServiceInfoInstance?.createTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="createTime-label" class="property-label"><g:message code="ivrServiceInfo.createTime.label" default="Create Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="createTime-label"><g:formatDate date="${ivrServiceInfoInstance?.createTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrServiceInfoInstance?.updateTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="updateTime-label" class="property-label"><g:message code="ivrServiceInfo.updateTime.label" default="Update Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="updateTime-label"><g:formatDate date="${ivrServiceInfoInstance?.updateTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
          </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${ivrServiceInfoInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </g:form>
    </div>
</div>
</body>
</html>
