<%@ page import="com.emag.gamecms.domain.job.GameCmsJobinfo" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo')}"/>
    <g:set var="jobTypes" value="['oneMonth':'每月运行一次','tenMin':'每十分钟运行一次','thirtyMIn':'每三十分钟运行一次','oneHour':'每小时运行一次','oneDay':'每天运行一次','oneOclock':'01时运行一次','twoOclock':'02时运行一次','eightOclock':'08时运行一次','fourteenOclock':'14时运行一次','oneWeek':'每周运行一次']" />
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">

    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'gameCmsJobinfo.id.label', default: 'Id')}"/>

                <g:sortableColumn property="jobName" title="${message(code: 'gameCmsJobinfo.jobName.label', default: 'Job Name')}"/>

                <g:sortableColumn property="jobType" title="${message(code: 'gameCmsJobinfo.jobType.label', default: 'Job Type')}"/>

                <g:sortableColumn property="orders" title="${message(code: 'gameCmsJobinfo.orders.label', default: 'orders')}"/>

                <g:sortableColumn property="jobService" title="${message(code: 'gameCmsJobinfo.jobService.label', default: 'Job Service')}"/>

                <g:sortableColumn property="jobMethod" title="${message(code: 'gameCmsJobinfo.jobMethod.label', default: 'Job Method')}"/>

                <g:sortableColumn property="updateTime" title="${message(code: 'gameCmsJobinfo.updateTime.label', default: 'Update Time')}"/>

                <th align="center">${message(code: 'game.cms.operate.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${gameCmsJobinfoInstanceList}" status="i" var="gameCmsJobinfoInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show" id="${gameCmsJobinfoInstance.id}">${fieldValue(bean: gameCmsJobinfoInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: gameCmsJobinfoInstance, field: "jobName")}</td>

                    <td>${jobTypes[gameCmsJobinfoInstance.jobType]}</td>

                    <td>${fieldValue(bean: gameCmsJobinfoInstance, field: "orders")}</td>

                    <td>${fieldValue(bean: gameCmsJobinfoInstance, field: "jobService")}</td>

                    <td>${fieldValue(bean: gameCmsJobinfoInstance, field: "jobMethod")}</td>

                    <td>
                        <g:formatDate date="${gameCmsJobinfoInstance?.updateTime}" />

                    </td>

                    <td align="center" class="actionButtons">
                        <span class="actionButton">
                            <g:link action="start" id="${gameCmsJobinfoInstance?.id}">启动</g:link>
                            <g:link action="view" id="${gameCmsJobinfoInstance?.id}">查看</g:link>
                         </span>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${gameCmsJobinfoInstanceTotal}"/>
    </div>
</div>
</body>
</html>
