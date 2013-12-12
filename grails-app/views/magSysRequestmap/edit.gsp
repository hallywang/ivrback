<%@ page import="com.emag.gamecms.domain.system.MagSysRequestmap" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>修改菜单</title>
	</head>
	<body>
		<div class="nav">
			<span class="menuButton"><g:link class="list" action="list">菜单列表</g:link></span>
			<span class="menuButton"><g:link class="create" action="create">新建菜单</g:link></span>
		</div>
		<div class="body">
			<h1>修改菜单</h1>
			<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${requestmap}">
			<div class="errors">
			<g:renderErrors bean="${requestmap}" as="list" />
			</div>
			</g:hasErrors>

			<g:form method="post">
				<input type="hidden" name="id" value="${requestmap?.id}" />
				<div class="dialog">
					<table>
					<tbody>

                      <tr class="prop">
							<td valign="top" class="name">
								<label for="url">Id:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:requestmap,field:'url','errors')}">
								<span class="value">${requestmap?.id}</span>
							</td>
						</tr>

                       <tr class="prop">
							<td valign="top" class="name">
								<label for="url">名称:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:requestmap,field:'name','errors')}">
								<input type="text" id="name" name="name" value="${requestmap?.name?.encodeAsHTML()}"/>
							</td>
						</tr> 

                        <tr class="prop">
							<td valign="top" class="name">
								<label for="url">排序:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:requestmap,field:'orders','errors')}">
								<input type="text" id="orders" name="orders" value="${requestmap?.orders?.encodeAsHTML()}"/>
							</td>
						</tr>

						 <tr class="prop">
							<td valign="top" class="name">
								<label for="url">REAL URL:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:requestmap,field:'realUrl','errors')}">
								<input type="text" id="realUrl" name="realUrl" value="${requestmap?.realUrl?.encodeAsHTML()}"/>
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="url">URL:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:requestmap,field:'url','errors')}">
								<input type="text" id="url" name="url" value="${requestmap?.url?.encodeAsHTML()}"/>
							</td>
						</tr>


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
						<tr class="prop">
							<td valign="top" class="name">
								<label for="configAttribute">角色:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:requestmap,field:'configAttribute','errors')}">
								<input type="text" id="configAttribute" name="configAttribute" value="${names.join(',')}"/>
							</td>
						</tr>

                        <tr class="prop">
							<td valign="top" class="name">
								<label for="configAttribute">上级菜单:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:requestmap,field:'father','errors')}">
								<g:select name="father.id" from="${MagSysRequestmap.findAllByStatusAndLeaf(1,0)}" optionKey="id"
                                           value="${requestmap?.father?.id}" noSelection="${['':'--- 请选择 ---']}"></g:select>
							</td>
						</tr>

                        <tr class="prop">
							<td valign="top" class="name">
								<label for="configAttribute">是否生效:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:requestmap,field:'status','errors')}">
								<g:select name="status" from="${[[key:'1', val:'有效'], [key:'0', val:'无效']]}"
                                        optionKey="key" optionValue="val" value="${requestmap?.status}"></g:select>
							</td>
						</tr>

                        <tr class="prop">
							<td valign="top" class="name">
								<label for="configAttribute">节点类型:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:requestmap,field:'status','errors')}">
								<g:select name="leaf" from="${[[key:'0', val:'枝节点'], [key:'1', val:'叶节点']]}"
                                        optionKey="key" optionValue="val" value="${requestmap?.leaf}"></g:select>
							</td>
						</tr>

					</tbody>
					</table>
				</div>

				<div class="buttons">
					<span class="button"><g:actionSubmit class="save" action="Update" value="更新" /></span>
					<span class="button"><g:actionSubmit class="delete" onclick="return confirm('确定删除该菜单吗?');" value="删除" action="Delete"/></span>
				</div>

			</g:form>

		</div>
	</body>
</html>

