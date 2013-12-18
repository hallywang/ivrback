<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.emag.gamecms.domain.system.MagSysRequestmap; org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes; org.springframework.context.ApplicationContext;  com.emag.gamecms.service.MenuService" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>后台管理系统</title>
  <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/newstyle/css.css"/>
  <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/newstyle/head.css"/>

  <g:javascript library="jquery"/>
  <jq:plugin name="treeview"/>
  <script type="text/javascript">
    var url = location.href;
    var index = url.indexOf('jsessionid');
    if (index != -1) {
      var sid = url.substr(index + 11);
      document.cookie = "JSESSIONID=" + sid + ";path=/;";
    }

    function resizeFrame() {
      document.getElementById("mainFrame").style.height =
              document.body.offsetHeight - document.getElementById("top").offsetHeight + "px";
    }
    $(window).load(function() {
      resizeFrame();
    });
    $(window).resize(function() {
      resizeFrame();
    });


    //注销登录
    function logout() {
      window.location = "<%=request.contextPath%>/logout";
    }
    //修改密码
    function editPwd() {
      window.frames['mainFrame'].window.frames['rightFrame'].location = "${request.contextPath}/magSysUser2/editPass"
    }
    //点击导航栏的菜单，展开对应的左边菜单
    function togTree(id, treeSize, obj) {
      var max = parseInt(treeSize) + 1;
      //给当前导航栏菜单加样式
      var navtree = $('#nav' + id);
      navtree.removeClass();
      navtree.addClass('current');
      //去除别的菜单样式
      for (var j = 0; j < max; j++) {
        if (id != j) {
          var navtree2 = $('#nav' + j);
          navtree2.removeClass('current');
        }
      }
      if ($.browser.mozilla) {     //火狐
        if (id == 0) {   //导航栏“首页”菜单
          for (var h = 1; h < max; h++) {
            var eUL2 = window.frames['mainFrame'].window.frames['leftFrame'].document.getElementById('id' + h);
            var eLI2 = eUL2.parentNode;
            var eDIV2 = eLI2.getElementsByTagName("div")[0];
            eLI2.setAttribute("class", "expandable");
            eDIV2.setAttribute("class", "hitarea expandable-hitarea");
            eUL2.setAttribute("style", "display: none");
          }
        } else { //导航栏“首页”菜单
          var eUL = window.frames['mainFrame'].window.frames['leftFrame'].document.getElementById('id' + id);
          var eLI = eUL.parentNode;
          var eDIV = eLI.getElementsByTagName("div")[0];
          //修改样式，展开对应的图标和二级菜单
          eLI.setAttribute("class", "collapsable");
          eDIV.setAttribute("class", "hitarea collapsable-hitarea");
          eUL.setAttribute("style", "display: block");

          //关闭其他的图标和二级菜单
          for (var i = 1; i < max; i++) {
            if (id == i) {
            } else {
              var eUL2 = window.frames['mainFrame'].window.frames['leftFrame'].document.getElementById('id' + i);
              var eLI2 = eUL2.parentNode;
              var eDIV2 = eLI2.getElementsByTagName("div")[0];
              eLI2.setAttribute("class", "expandable");
              eDIV2.setAttribute("class", "hitarea expandable-hitarea");
              eUL2.setAttribute("style", "display: none");
            }
          }
        }
      } else {   // ie 等
        if (id == 0) { //导航栏“首页”菜单
          for (var h = 1; h < max; h++) {
            var eUL2 = window.frames['mainFrame'].window.frames['leftFrame'].document.getElementById('id' + h);
            var eLI2 = eUL2.parentNode;
            var eDIV2 = eLI2.getElementsByTagName("div")[0];
            eLI2.setAttribute("className", "expandable");
            eDIV2.setAttribute("className", "hitarea expandable-hitarea");
            eUL2.style.display = 'none';
          }
        } else { //导航栏除首页外其他菜单
          var eUL = window.frames['mainFrame'].window.frames['leftFrame'].document.getElementById('id' + id);
          var eLI = eUL.parentNode;
          var eDIV = eLI.getElementsByTagName("div")[0];
          //修改样式，展开对应的图标和二级菜单
          eLI.setAttribute("className", "collapsable");
          eDIV.setAttribute("className", "hitarea collapsable-hitarea");
          eUL.style.display = 'block';

          //关闭其他的图标和二级菜单
          for (var i = 1; i < max; i++) {
            if (id == i) {
            }
            else {
              var eUL2 = window.frames['mainFrame'].window.frames['leftFrame'].document.getElementById('id' + i);
              var eLI2 = eUL2.parentNode;
              var eDIV2 = eLI2.getElementsByTagName("div")[0];
              eLI2.setAttribute("className", "expandable");
              eDIV2.setAttribute("className", "hitarea expandable-hitarea");
              eUL2.style.display = 'none';
            }
          }
        }
      }
    }
  </script>
</head>

<body>
<!--头部-->
<div class="headbox" id="top">
  <div class="logobox">
    <div>
      <input class="xiugai" name="" type="button" onclick="editPwd()" value="修改密码"/><input class="tuichu" name=""
                                                                                           onclick="logout()"
                                                                                           type="button" value="退出"/>
    </div>

    <div style="width: 841px;height: 50px;font-size:35px; color:#FFF;">语音管理后台系统</div>
  </div>
  <!--导航栏 -->
  <div class="navbox">
    <a href="right.gsp" target="rightFrame" id="nav0"
       onclick="togTree('0', '${myRootUrlList.size()}', this)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首&nbsp;&nbsp;&nbsp;页</a>
    <%
      menuService.setMyUrl(myUrlList)
    %>
    <g:each in="${myRootUrlList}" status="index1" var="url">

      <%

        index1++     //一级菜单的id从1开始
        //去除不是自己拥有权限的根目录
        if (!myUrlList?.contains(url)) {
          return ""
        }

        menuService.initMenuBuff()
        Iterator iter = url.childs.iterator()
        while (iter.hasNext()) {
          MagSysRequestmap urlChild = iter.next()
          if (urlChild.status != 1) {
            iter.remove()
            continue;
          }


          //去除不是自己拥有权限的子目录
          if (!myUrlList?.contains(urlChild)) {
            iter.remove()
            continue;
          }
        }

      %>
      <g:if test="${url.childs.size()>0}">
        <a href="${request.contextPath}/${url.childs?.first()?.realUrl}" id="nav${index1}"
           onclick="togTree('${index1}', '${myRootUrlList.size()}', this)"
           target="rightFrame">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${url.name}</a>
      </g:if>
    </g:each>

    <h4>您好,${authenticateService.userDomain()?.userRealName}。
      <script language="JavaScript" type="text/javascript">
        <!--
        var enabled = 0;
        today = new Date();
        var day;
        var date;
        if (today.getDay() == 0) day = " 星期日"
        if (today.getDay() == 1) day = " 星期一"
        if (today.getDay() == 2) day = " 星期二"
        if (today.getDay() == 3) day = " 星期三"
        if (today.getDay() == 4) day = " 星期四"
        if (today.getDay() == 5) day = " 星期五"
        if (today.getDay() == 6) day = " 星期六"
        date = (today.getFullYear()) + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日&nbsp;" + day + "";
        document.write(date);
        // -->
      </script></h4>
  </div>
  <!--导航栏END-->
</div>
<!--头部END-->
<iframe id="mainFrame" name="mainFrame" src="welcome.gsp" frameborder="0" scrolling="no"
        style="width:100%;height: 350px;"></iframe>
<!--尾部-->
<div class="footbox">
  <h4>Powered by hally Copyright © 2014-2018</h4>
</div>
<!--尾部END-->
</body>

</html>
