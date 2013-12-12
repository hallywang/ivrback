
<%@ page import="com.emag.gamecms.domain.job.GameCmsJobinfo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo')}" />
        <g:set var="jobTypes" value="[[type:'tenMin',name:'每十分钟运行一次'],[type:'thirtyMIn',name:'每三十分钟运行一次'],[type:'oneHour',name:'每小时运行一次'],[type:'oneDay',name:'每天运行一次'],[type:'oneOclock', name:'01时运行一次'],[type:'twoOclock',name:'02时运行一次'],[type:'eightOclock',name:'08时运行一次'],[type:'fourteenOclock',name:'14时运行一次'],[type:'oneWeek',name:'每周运行一次'],[type:'oneMonth',name:'每月运行一次']]" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">

            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsJobinfo.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsJobinfoInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsJobinfo.jobName.label" default="Job Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsJobinfoInstance, field: "jobName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsJobinfo.jobType.label" default="Job Type" /></td>
                            
                            <td valign="top" class="value">${jobTypes[gameCmsJobinfoInstance.jobType]}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsJobinfo.jobService.label" default="Job Service" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsJobinfoInstance, field: "jobService")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsJobinfo.jobMethod.label" default="Job Method" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: gameCmsJobinfoInstance, field: "jobMethod")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="gameCmsJobinfo.updateTime.label" default="Update Time" /></td>
                            
                            <td valign="top" class="value">
                                <g:formatDate date="${gameCmsJobinfoInstance?.updateTime}" />
                            </td>
                            
                        </tr>

                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${gameCmsJobinfoInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
