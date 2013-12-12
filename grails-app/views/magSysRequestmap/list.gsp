<%@ page import="com.emag.gamecms.domain.system.MagSysRequestmap" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>MagSysRequestmap List</title>
	</head>
	<body>
		<div class="nav">
			<span class="menuButton"><g:link class="create" action="create">新建菜单</g:link></span>
		</div>
		<div class="body">
			<h1>菜单列表</h1>
			<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>
			<div class="list">
				<table>
				<thead>
					<tr>
						<g:sortableColumn property="id" title="id" />
                        <th>名称</th>
                        <g:sortableColumn property="id" title="上级菜单" />
                        <g:sortableColumn property="url" title="REAL URL" />
						<g:sortableColumn property="url" title="URL" />
						<g:sortableColumn property="configAttribute" title="角色" />
                        <th>节点类型</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${requestmapList}" status="i" var="requestmap">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td>${requestmap.id}</td>
                        <td>${requestmap.name}</td>
                        <td>${requestmap.father?.name}</td>
                        <td>${requestmap.realUrl?.encodeAsHTML()}</td>
						<td>${requestmap.url?.encodeAsHTML()}</td>
		
<%
def names = []
org.springframework.util.StringUtils.commaDelimitedListToStringArray(requestmap.configAttribute).each { role ->
	if (role.startsWith('ROLE_')) {
		names << role.substring(5).toLowerCase().encodeAsHTML()
	}
	else {
		names << role.encodeAsHTML()
	}
}
%>
						<td>${names.join(',')}</td>
                        <td>
                          <g:if test="${requestmap.leaf == 0}">枝节点</g:if>
                          <g:else>叶节点</g:else>
                        </td>
						<td class="actionButtons">
							<span class="actionButton">
							<g:link action="show" id="${requestmap.id}">查看</g:link>
                              <g:link action="edit" id="${requestmap.id}">编辑</g:link>
                              <g:link action="delete" id="${requestmap.id}" onclick="return confirm('确定删除该菜单吗?');" >删除</g:link>
							</span>
						</td>
					</tr>
					</g:each>
				</tbody>
				</table>
			</div>

			<div class="paginateButtons">
				<g:paginate total="${MagSysRequestmap.count()}" />
			</div>

		</div>
	</body>
</html>

