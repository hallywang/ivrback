<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes; org.springframework.context.ApplicationContext; com.emag.gamecms.domain.system.MagSysRequestmap" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:javascript library="jquery"/>
  <jq:plugin name="treeview"/>
  <title>Create MagSysRole</title>
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
</div>
<div class="body">

  <h1>新建角色</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${authority}">
    <div class="errors">
      <g:renderErrors bean="${authority}" as="list"/>
    </div>
  </g:hasErrors>

  <g:form action="save" method="post">
    <div class="dialog">
      <table>
        <tbody>
        <tr class="prop">
          <td valign="top" class="name">
            <label for="authority">角色名:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: authority, field: 'authority', 'errors')}">
            <input type="text" id="authority" name="authority" value="${authority?.authority?.encodeAsHTML()}"/>
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
            <label for="description">权限:</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: authority, field: 'description', 'errors')}">

            <ul id="browser" class="filetree treeview-gray" style="font-size:11px; ">
              <li>

                <span class="folder">语音管理系统</span>
                <ul>
                  <%
                    menuService.setMyUrl(myUrlList)
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
      <span class="button"><input class="save" type="submit" value="保存"/></span>
    </div>
  </g:form>
</div>
</body>
</html>

