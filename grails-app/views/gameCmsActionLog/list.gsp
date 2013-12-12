<%@ page import="com.emag.gamecms.domain.actionlog.GameCmsActionLog" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsActionLog.label', default: 'GameCmsActionLog')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
  <g:javascript library="jquery"/>
  <jq:plugin name="datePicker"/>
  <jq:plugin name="ui.datepicker-zh-CN"/>
  <script type="text/javascript">
    $().ready(function() { //jq start
      $("#startTime").datepicker();
      $("#endTime").datepicker();
    }); //jq end

  </script>
</head>
<body>
<div class="nav">
</div>
<div class="body">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>

   %{--添加查询条件--}%
    <g:form name="myForm" action="list" method="get">
        <div class="list">
            <tr>
              <td>
                用户名<input type="text" name="userName" value="${params['userName']}" maxlength="11" style="width:80px;">&nbsp;
                操作<input type="text" name="controllName" value="${params['controllName']}" maxlength="20" style="width:80px;">&nbsp;
                类型<input type="text" name="actionName" value="${params['actionName']}" maxlength="20" style="width:80px;">&nbsp;
                ID<input type="text" name="actionId" value="${params['actionId']}" maxlength="20" style="width:80px;">&nbsp;
                从<input type="text" style="width:80px" size="11" readonly="readonly" name="startTime" id="startTime" value="${params['startTime']}"/>
                至<input type="text" style="width:80px" size="11"  readonly="readonly" name="endTime" id="endTime" value="${params['endTime']}"/>&nbsp;
                <span class="button"><input class="save" type="submit" value="查询" /></span><br/>
              </td>
            </tr>
        </div>
      </g:form>

  <div class="list">
    <table>
      <thead>
      <tr>
        <g:sortableColumn property="id" title="${message(code: 'gameCmsActionLog.id.label', default: 'Id')}"/>
        <g:sortableColumn property="controllName" title="${message(code: 'gameCmsActionLog.controllName.label', default: 'Controll Name')}"/>
        <g:sortableColumn property="actionName" title="${message(code: 'gameCmsActionLog.actionName.label', default: 'Action Name')}"/>
        <g:sortableColumn property="actionId" title="${message(code: 'gameCmsActionLog.actionId.label', default: 'Action Id')}"/>
        <g:sortableColumn property="userName" title="${message(code: 'gameCmsActionLog.userName.label', default: 'User Name')}"/>
        <g:sortableColumn property="ipAddress" title="${message(code: 'gameCmsActionLog.ipAddress.label', default: 'ipAddress')}"/>
        <g:sortableColumn property="actionTime" title="${message(code: 'gameCmsActionLog.actionTime.label', default: 'actionTime')}"/>
      </tr>
      </thead>
      <tbody>
      <g:each in="${gameCmsActionLogInstanceList}" status="i" var="gameCmsActionLogInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
          <td>${fieldValue(bean: gameCmsActionLogInstance, field: "id")}</td>
          <td>${fieldValue(bean: gameCmsActionLogInstance, field: "controllName")}</td>
          <td>${fieldValue(bean: gameCmsActionLogInstance, field: "actionName")}</td>
          <td>${fieldValue(bean: gameCmsActionLogInstance, field: "actionId")}</td>
          <td>${fieldValue(bean: gameCmsActionLogInstance, field: "userName")}</td>
           <td>${fieldValue(bean: gameCmsActionLogInstance, field: "ipAddress")}</td>
          <td>
              <g:formatDate date="${gameCmsActionLogInstance?.actionTime}" />
          </td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="paginateButtons">
    <g:paginate total="${gameCmsActionLogInstanceTotal}" params="${params}" />
  </div>
</div>
</body>
</html>
