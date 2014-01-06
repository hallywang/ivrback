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
            <g:form name="myForm" action="list" method="get">
			<div class="list">
                    <tr>
                        <td>
                            角色名<g:textField name="authority" value="${params['authority']}" maxlength="11" style="width:80px;"/>&nbsp;
                            所属部门 <g:select name="deptId" from="${deptList}"  optionValue="deptName" noSelection="['':'--请选择所属部门--']" optionKey="id" value="${params.deptId}"/>&nbsp;
                            描述<g:textField name="description" value="${params['description']}" maxlength="20" style="width:80px;"/>&nbsp;
                            <span class="button"><input class="save" type="submit" value="查询" /></span><br/>
                        </td>
                    </tr>
                </div>
            </g:form>
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
				<g:paginate total="${authoritiesTotalCount}"   params="${params}" />
			</div>
		</div>
	</body>
</html>

