<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>Show MagSysDept</title>
	</head>
	<body>

		<div class="nav">

			<span class="menuButton"><g:link class="list" action="list">部门列表</g:link></span>
			<span class="menuButton"><g:link class="create" action="create">添加部门</g:link></span>
		</div>

		<div class="body">
			<h1>显示部门</h1>
			<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>
			<div class="dialog">
				<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name">编号:</td>
						<td valign="top" class="value">${dept.id}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">部门名称:</td>
						<td valign="top" class="value">${dept.deptName?.encodeAsHTML()}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">部门编号:</td>
						<td valign="top" class="value">${dept.deptCode?.encodeAsHTML()}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">描述:</td>
						<td valign="top" class="value">${dept.description?.encodeAsHTML()}</td>
					</tr>
				</tbody>
				</table>
			</div>

			<div class="buttons">
				<g:form>
					<input type="hidden" name="id" value="${dept.id}" />
					<span class="button"><g:actionSubmit class="edit" value="修改" action="Edit"/></span>
					<span class="button"><g:actionSubmit class="delete" onclick="return confirm('确定删除吗?');" action="Delete" value="删除" /></span>
				</g:form>
			</div>

		</div>
	</body>
</html>

