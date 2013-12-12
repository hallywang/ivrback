
<%@ page import="com.emag.gamecms.domain.sysconf.SysConfGroup" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'sysConfGroup.label', default: 'SysConfGroup')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
			</ul>
		</div>
		<div id="list-sysConfGroup" class="body" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:form name="myForm"  action="list">
                <div class="list">
                    <tr>
                        <td>
                            &nbsp;关键字:&nbsp;<g:textField name="groupName" value="${params['groupName']}" style="width:100px;"/>
                            <span class="button"><input class="save" type="submit" value="查询"/></span>
                            &nbsp;(可使用文件名、备注作为关键字模糊查询)
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
					
						<g:sortableColumn property="groupName" title="${message(code: 'sysConfGroup.groupName.label', default: 'Group Name')}" width="20%"/>
					
						<g:sortableColumn property="remark" title="${message(code: 'sysConfGroup.remark.label', default: 'Remark')}" width="30%"/>
					
						<g:sortableColumn property="createTime" title="${message(code: 'sysConfGroup.createTime.label', default: 'Create Time')}" width="18%"/>
					
						<g:sortableColumn property="updateTime" title="${message(code: 'sysConfGroup.updateTime.label', default: 'Update Time')}" width="18%"/>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${sysConfGroupInstanceList}" status="i" var="sysConfGroupInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;padding:2px;text-align:left;"><g:link action="show" id="${sysConfGroupInstance.id}">${fieldValue(bean: sysConfGroupInstance, field: "groupName")}</g:link></td>
					
						<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;padding:2px;text-align:left;" title=${sysConfGroupInstance?.remark}>${fieldValue(bean: sysConfGroupInstance, field: "remark")}</td>
					
						<td><g:formatDate date="${sysConfGroupInstance.createTime}" /></td>
					
						<td><g:formatDate date="${sysConfGroupInstance.updateTime}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${sysConfGroupInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
