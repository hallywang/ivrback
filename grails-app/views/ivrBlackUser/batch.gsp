<%@ page import="com.emag.gamecms.domain.ivr.IvrBlackUser" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'ivrBlackUser.label', default: 'IvrBlackUser')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav" role="navigation">
  <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
  <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>

</div>

<div id="list-ivrBlackUser" class="body" role="main">
  <h1><g:message code="default.batch.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
  </g:if>

  <g:uploadForm name="myForm" action="batchSave" method="post">


    <div class="dialog">
      <g:render template="batchform"/>
    </div>

    <div class="buttons">
      <span class="button"><g:submitButton name="create" class="save" value="导入" /></span>
    </div>

  </g:uploadForm>


  %{--<div class="list">
    <table>
      <thead>
      <tr>

        <g:sortableColumn property="msisdn" title="${message(code: 'ivrBlackUser.msisdn.label', default: 'Msisdn')}"/>

        <g:sortableColumn property="userType"
                          title="${message(code: 'ivrBlackUser.userType.label', default: 'User Type')}"/>

        <g:sortableColumn property="scope" title="${message(code: 'ivrBlackUser.scope.label', default: 'Scope')}"/>

        <g:sortableColumn property="status" title="${message(code: 'ivrBlackUser.status.label', default: 'Status')}"/>

        <g:sortableColumn property="comment"
                          title="${message(code: 'ivrBlackUser.comment.label', default: 'Comment')}"/>

        <g:sortableColumn property="createTime"
                          title="${message(code: 'ivrBlackUser.createTime.label', default: 'Create Time')}"/>

      </tr>
      </thead>
      <tbody>
      <g:each in="${ivrBlackUserInstanceList}" status="i" var="ivrBlackUserInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

          <td><g:link action="show"
                      id="${ivrBlackUserInstance.id}">${fieldValue(bean: ivrBlackUserInstance, field: "msisdn")}</g:link></td>

          <td>${fieldValue(bean: ivrBlackUserInstance, field: "userType")}</td>

          <td>${fieldValue(bean: ivrBlackUserInstance, field: "scope")}</td>

          <g:if test="${ivrBlackUserInstance?.status==0}">
            <td style="color: #ff0000">
          </g:if>
          <g:else>
            <td>
          </g:else>
          <g:message code="ivrBlackUser.status.${ivrBlackUserInstance?.status}"/>

        </td>

          <td>${fieldValue(bean: ivrBlackUserInstance, field: "comment")}</td>

          <td><g:formatDate date="${ivrBlackUserInstance.createTime}"/></td>

        </tr>
      </g:each>
      </tbody>
    </table>
  </div>

  <div class="paginateButtons">
    <g:paginate total="${ivrBlackUserInstanceTotal}" params="${params}"/>
  </div>--}%
</div>
</body>
</html>
