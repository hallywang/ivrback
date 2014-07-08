
<%@ page import="com.emag.gamecms.domain.ivr.IvrChannelNotice" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav" role="navigation">
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div id="show-ivrChannelNotice" class="body" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
          <tbody>
            
            <g:if test="${ivrChannelNoticeInstance?.channelCode}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="channelCode-label" class="property-label"><g:message code="ivrChannelNotice.channelCode.label" default="Channel Code" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="channelCode-label"><g:fieldValue bean="${ivrChannelNoticeInstance}" field="channelCode"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrChannelNoticeInstance?.createTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="createTime-label" class="property-label"><g:message code="ivrChannelNotice.createTime.label" default="Create Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="createTime-label"><g:formatDate date="${ivrChannelNoticeInstance?.createTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrChannelNoticeInstance?.noticeUrl}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="noticeUrl-label" class="property-label"><g:message code="ivrChannelNotice.noticeUrl.label" default="Notice Url" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="noticeUrl-label"><g:fieldValue bean="${ivrChannelNoticeInstance}" field="noticeUrl"/></span>
                        
                    </td>
                </tr>
            </g:if>

          <g:if test="${ivrChannelNoticeInstance?.serviceClass}">
            <tr class="prop">
              <td valign="top" class="name"><span id="noticeUrl-label" class="property-label">
                <g:message code="ivrChannelNotice.serviceClass.label" default="serviceClass" /></span></td>
              <td valign="top" class="value">

                <span class="property-value" aria-labelledby="noticeUrl-label">
                  <g:fieldValue bean="${ivrChannelNoticeInstance}" field="serviceClass"/></span>

              </td>
            </tr>
          </g:if>
            
            <g:if test="${ivrChannelNoticeInstance?.serviceId}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="serviceId-label" class="property-label"><g:message code="ivrChannelNotice.serviceId.label" default="Service Id" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="serviceId-label"><g:fieldValue bean="${ivrChannelNoticeInstance}" field="serviceId"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrChannelNoticeInstance?.status}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="status-label" class="property-label"><g:message code="ivrChannelNotice.status.label" default="Status" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${ivrChannelNoticeInstance}" field="status"/></span>
                        
                    </td>
                </tr>
            </g:if>
            
            <g:if test="${ivrChannelNoticeInstance?.updateTime}">
                <tr class="prop">
                    <td valign="top" class="name"><span id="updateTime-label" class="property-label"><g:message code="ivrChannelNotice.updateTime.label" default="Update Time" /></span></td>
                    <td valign="top" class="value">
                        
                        <span class="property-value" aria-labelledby="updateTime-label"><g:formatDate date="${ivrChannelNoticeInstance?.updateTime}" /></span>
                        
                    </td>
                </tr>
            </g:if>
            
          </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${ivrChannelNoticeInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </g:form>
    </div>
</div>
</body>
</html>
