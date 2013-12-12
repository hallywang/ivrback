<%@ page import="com.emag.gamecms.domain.system.MagSysRequestmap; org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes; org.springframework.context.ApplicationContext;  com.emag.gamecms.service.MenuService" %>
<html>
<head>
  <title>业务管理系统</title>
  <meta name="layout" content="main"/>

  <g:javascript library="jquery"/>
  <jq:plugin name="treeview"/>
    <script type="text/javascript">
    $(document).ready(function() {
      $("#browser").treeview({
        collapsed: true
      });
       // $("#browser").treeview({ animated: "fast", collapsed: true, unique: true, toggle: function() { window.console && console.log("%o was toggled", this); } });
    });
  function tt(id){

  }

  </script>
</head>
<body>
<div style=" width:235px!important; width:245px; height:100%; overflow-x:hidden; overflow-y:visible; border-right:1px solid #013F82; margin:0; padding:0 0 0 10px;">
  <ul id="browser" class="filetree treeview-gray" style="font-size:13px; ">
      <ul>
      <li style="background-image:none; padding:0 0 5px 0px;"> <img style=" float:left; margin:0px 5px 0px 0px;" src="${request.contextPath}/css/images/homepage.gif" /><span style=" font-size:14px; font-weight:bold; color:#000;">游戏cms系统</span>   </li>
        <%
          menuService.setMyUrl(myUrlList)
        %>
        <g:each in="${myRooturls}" status="index" var="url">
          <%
            menuService.initMenuBuff()
            out.print(menuService.generateMenuList(request.contextPath, url,index,url))
          %>
        </g:each>
      </ul>

  </ul>
</div>
</body>
</html>