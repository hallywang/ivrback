<%@ page import="com.emag.gamecms.domain.system.MagSysRole" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>MagSysRole List</title>
	</head>
	<body>
		<div class="nav">
			<span class="menuButton"><g:link class="create" action="create">新建角色</g:link></span>
		</div>
		<div class="body">
			<h1>角色列表</h1>
			<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>
			<div class="list">
				<table>
				<thead>
					<tr>
						<g:sortableColumn property="id" title="id" />
						<g:sortableColumn property="authority" title="角色名" />
                        <th>所属部门</th>
						<g:sortableColumn property="description" title="描述" />
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${authorityList}" status="i" var="authority">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td>${authority.id}</td>
						<td>${authority.authority?.substring(5)?.toLowerCase()?.encodeAsHTML()}</td>
					    <td>${authority?.dept?.deptName}</td>
                        <td>${authority.description?.encodeAsHTML()}</td>
						<td class="actionButtons">
							<span class="actionButton">
								<g:link action="show" id="${authority.id}">查看</g:link>
                                <g:link action="edit" id="${authority.id}">编辑</g:link>
                                <g:link action="delete" onclick="return confirm('确定删除该角色吗?');" id="${authority.id}">删除</g:link>
							</span>
						</td>
					</tr>
				</g:each>
				</tbody>
				</table>
			</div>

			<div class="paginateButtons">
				<g:paginate total="${MagSysRole.count()}" />
			</div>
		</div>
	</body>
</html>

