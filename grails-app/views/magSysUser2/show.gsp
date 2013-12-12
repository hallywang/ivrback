<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>Show MagSysUser2</title>
	</head>
	<body>

		<div class="nav">

			<span class="menuButton"><g:link class="list" action="list">用户列表</g:link></span>
			<span class="menuButton"><g:link class="create" action="create">添加用户</g:link></span>
		</div>

		<div class="body">
			<h1>显示用户</h1>
			<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>
			<div class="dialog">
				<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name">编号:</td>
						<td valign="top" class="value">${person.id}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">登录名</td>
						<td valign="top" class="value">${person.username?.encodeAsHTML()}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">真实姓名:</td>
						<td valign="top" class="value">${person.userRealName?.encodeAsHTML()}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">是否有效:</td>
						<td valign="top" class="value">${person.enabled}</td>
					</tr>

                    <tr class="prop">
						<td valign="top" class="name">所属部门:</td>
						<td valign="top" class="value">${person?.dept?.deptName}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">描述:</td>
						<td valign="top" class="value">${person.description?.encodeAsHTML()}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">Email:</td>
						<td valign="top" class="value">${person.email?.encodeAsHTML()}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">显示Email:</td>
						<td valign="top" class="value">${person.emailShow}</td>
					</tr>

                    <tr class="prop">
						<td valign="top" class="name">手机号:</td>
						<td valign="top" class="value">${person.mobile}</td>
					</tr>

                    <tr class="prop">
						<td valign="top" class="name">是否需要短信验证:</td>
						<td valign="top" class="value">${person.checkSmsYn}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">角色:</td>
						<td valign="top" class="value">
							<ul>
							<g:each in="${person.authorities}" var="c">
                              <p>${c?.authority.substring(5)?.toLowerCase()}(${c?.description})</p>
							</g:each>
							</ul>
						</td>
					</tr>

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<g:form>
					<input type="hidden" name="id" value="${person.id}" />
					<span class="button"><g:actionSubmit class="edit" value="修改" action="Edit"/></span>
					<span class="button"><g:actionSubmit class="delete" onclick="return confirm('确定删除吗?');" action="Delete" value="删除" /></span>
				</g:form>
			</div>

		</div>
	</body>
</html>

