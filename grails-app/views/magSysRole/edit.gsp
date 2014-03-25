<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes; org.springframework.context.ApplicationContext; com.emag.gamecms.domain.system.MagSysRequestmap" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:javascript library="jquery"/>
  <jq:plugin name="treeview"/>
  <title>Edit MagSysRole</title>
  <script type="text/javascript">
    $(document).ready(function() {
      $("#browser").treeview({
        toggle: function() {

        }
      });
    });
  </script>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="list" action="list">角色列表</g:link></span>
  <span class="menuButton"><g:link class="create" action="create">新建角色</g:link></span>
</div>
<div class="body">
  <h1>角色修改</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${authority}">
    <div class="errors">
      <g:renderErrors bean="${authority}" as="list"/>
    </div>
  </g:hasErrors>

  <g:form method="post">
    <input type="hidden" name="id" value="${authority?.id}"/>
    <div class="dialog">
      <table>
        <tbody>
        <tr class="prop">
          <td valign="top" class="name">
            <label for="authority">角色名:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: authority, field: 'authority', 'errors')}">
            <input type="text" id="authority" name="authority" value="${authority?.authority?.substring(5)?.toLowerCase()?.encodeAsHTML()}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label>所属部门:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: authority, field: 'dept', 'errors')}">
            <g:select id="dept.id" name="dept.id" from="${com.emag.gamecms.domain.system.MagSysDept.list()}"  optionValue="deptName" noSelection="['':'--请选择所属部门--']" optionKey="id" value="${authority?.dept?.id}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="description">描述:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: authority, field: 'description', 'errors')}">
            <input type="text" id="description" name="description" value="${authority?.description?.encodeAsHTML()}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="people">用户:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: authority, field: 'people', 'errors')}">
            <ul>
              <g:each var="p" in="${authority?.people}">
                <li>${p}</li>
              </g:each>
            </ul>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="description">权限:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: authority, field: 'description', 'errors')}">
             <ul id="browser" class="filetree treeview-gray" style="font-size:11px; ">
              <li>

                <span class="folder">语音管理系统</span>
                <ul>
                  <%
                    menuService.setMyUrl(myUrlList)
                    menuService.setRms(rms)
                  %>
                  <g:each in="${urls}" var="url" status="index">
               <%
                  menuService.initMenuBuff()
                  out.print(menuService.generateUrlList(url))
                  out.print("<br />")
                %>
            </g:each>
                </ul>

              </li>
            </ul>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button"><g:actionSubmit action="update" class="update" value="保存"/></span>
      <span class="button"><g:actionSubmit action="delete" class="delete" onclick="return confirm('确定删除该角色吗?');" value="删除"/></span>
    </div>

  </g:form>
</div>
</body>
</html>
