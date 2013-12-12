
<%@ page import="com.emag.gamecms.domain.sysconf.SysConf" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'sysConf.label', default: 'SysConf')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
			</ul>
		</div>
		<div id="list-sysConf" class="body" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:form name="myForm"  action="list">
                <div class="list">
                    <tr>
                        <td>
                            &nbsp;变量名:&nbsp;<g:textField name="sysconfKey" value="${params['sysconfKey']}" style="width:100px;"/>
                            状态:<g:select name="status" from="${[[k:'0',v:'无效'], [k:'1',v:'有效']]}"  noSelection="${['':'--- 请选择 ---']}"
                                      value="${params['status']}" optionKey="k" optionValue="v"/>
                            组:<g:select id="group" name="group.id" from="${com.emag.gamecms.domain.sysconf.SysConfGroup.list()}" optionKey="id" required="" optionValue="${{it.groupName}}" noSelection="${['':'--- 请选择 ---']}" value="${params['group.id']}" class="many-to-one"/>
                            <span class="button"><input class="save" type="submit" value="查询"/></span>
                        </td>
                    </tr>
                </div>
            </g:form>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table style="table-layout: fixed">
				<thead>
					<tr>
					
						<g:sortableColumn property="sysconfKey" title="${message(code: 'sysConf.sysconfKey.label', default: 'SysconfKey')}" width="20%"/>
					
						<g:sortableColumn property="sysconfValue" title="${message(code: 'sysConf.sysconfValue.label', default: 'sysconfValue')}" width="30%"/>
					
						<g:sortableColumn property="status" title="${message(code: 'sysConf.status.label', default: 'Status')}" width="10%"/>
					
						<g:sortableColumn property="remark" title="${message(code: 'sysConf.remark.label', default: 'Remark')}" width="10%"/>

                        <g:sortableColumn property="group" title="${message(code: 'sysConf.group.label', default: 'group')}" width="20%"/>
					

					</tr>
				</thead>
				<tbody>
				<g:each in="${sysConfInstanceList}" status="i" var="sysConfInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;padding:2px;text-align:left;"><g:link action="show" id="${sysConfInstance.id}">${fieldValue(bean: sysConfInstance, field: "sysconfKey")}</g:link></td>
					
						<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;padding:2px;text-align:left;" title="${sysConfInstance.sysconfValue}">${fieldValue(bean: sysConfInstance, field: "sysconfValue")}</td>

					    <g:if test="${sysConfInstance.status==1}">
                            <td>有效</td>
                        </g:if>
						<g:elseif test="${sysConfInstance.status==0}">
                            <td>无效</td>
						</g:elseif>

                        <td>${sysConfInstance.remark}</td>

						<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;padding:2px;text-align:left;"><g:link controller="sysConfGroup" action="show" id="${sysConfInstance?.group?.id}">${sysConfInstance?.group?.groupName.encodeAsHTML()}</g:link></td>

					

					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${sysConfInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
