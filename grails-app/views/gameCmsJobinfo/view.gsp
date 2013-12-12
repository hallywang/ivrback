<%@ page import="com.emag.gamecms.domain.job.GameCmsJobinfo" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo')}"/>
    <title>JOb启动记录</title>
</head>
<body>
<div class="nav">

    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1>JOb启动记录</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <th>id</th>
                <th>启动job开始时间</th>
                <th>启动job结束时间</th>
                <th>job启动时间</th>
                <th>job启动信息</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${jobRuninfos}" status="i" var="runInfo">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td>${runInfo?.id}</td>

                    <td>
                        <g:formatDate date="${runInfo?.startTime}"/>
                    </td>

                    <td>
                        <g:formatDate date="${runInfo?.endTime}"/>
                    </td>
                    <td>
                        ${runInfo.runTime}
                    </td>
                    <td>
                        ${runInfo.runInfo}
                    </td>
                    <td align="center" class="actionButtons">
                        <span class="actionButton">
                            <g:link action="delRunInfo" id="${runInfo?.id}" onclick="return confirm('${message(code: 'game.cms.delete.confirm.label')}');">删除</g:link>
                         </span>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${total}" params="${params}"/>
    </div>
</div>
</body>
</html>
