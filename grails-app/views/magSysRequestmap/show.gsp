<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>菜单信息</title>
	</head>
	<body>
		<div class="nav">
			<span class="menuButton"><g:link class="list" action="list">菜单列表</g:link></span>
			<span class="menuButton"><g:link class="create" action="create">新建菜单</g:link></span>
		</div>
		<div class="body">
			<h1>菜单信息</h1>
			<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>
			<div class="dialog">
				<table>
				<tbody>

				    <tr class="prop">
						<td valign="top" class="name">Id:</td>
						<td valign="top" class="value">${requestmap.id}</td>
					</tr>

                     <tr class="prop">
						<td valign="top" class="name">名称:</td>
						<td valign="top" class="value">${requestmap.name}</td>
					</tr>

                     <tr class="prop">
						<td valign="top" class="name">排序:</td>
						<td valign="top" class="value">${requestmap.orders}</td>
					</tr>

                   <tr class="prop">
						<td valign="top" class="name">REAL URL:</td>
						<td valign="top" class="value">${requestmap.realUrl}</td>
					</tr>

                  <tr class="prop">
						<td valign="top" class="name">URL:</td>
						<td valign="top" class="value">${requestmap.url}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">上级菜单:</td>
						<td valign="top" class="value">${requestmap.father?.name}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">Roles:</td>
	
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
						<td valign="top" class="value">${names.join(',')}</td>
					</tr>

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<g:form>
					<input type="hidden" name="id" value="${requestmap?.id}" />
					<span class="button"><g:actionSubmit class="edit" action="edit" id="${requestmap.id}" value="编辑" /></span>
					<span class="button"><g:actionSubmit class="delete" action="delete" onclick="return confirm('确定删除该菜单吗?');" value="删除" /></span>
				</g:form>
			</div>

		</div>
	</body>
</html>

