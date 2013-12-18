<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>登陆页</title>
  <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/newstyle/style.css"/>
  <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/newstyle/public.css"/>
  <script type="text/javascript" src="${request.contextPath}/js/jquery/jquery-1.4.2.min.js"></script>
  <script type="text/javascript" src="${request.contextPath}/js/DownMenu.js"></script>

  <script type="text/javascript">
    $().ready(function() {
      $("#j_username").focus();
      topNav();

      // 点击登录按钮
      $("#loginbutton").click(function() {
        $.ajax({
          url: "checkSmsYn",
          dataType: 'html/xml',
          data: {'username':$("#j_username").val(), 'password':$(":password").val(), 't':new Date().getTime()},
          success: function(msg) {
            var validUser = msg.split('_')[0];
            var needSms = msg.split('_')[1];
            if (validUser == '0') {
              alert('您输入的密码和账户名不匹配，请重新输入');
              return false;
            }

            if (needSms == '2') {
              alert("号码有误，请联系管理员！");
              return false;
            } else if (needSms == '1') {
              $("#randDiv").show();
              $("#subRandom").focus();
              return false;
            }

            // 不需要短信验证码，直接提交表单
            $("form[name=loginform]").submit();
            return false;
          }
        });
      });

      // 重新发送短信验证码
      $("[href='#']").click(function() {
        $.ajax({
          url: "sendSmsAgain",
          dataType: 'html/xml',
          data: {'t':new Date().getTime()},
          success: function(msg) {
            if (msg == '0') {
              alert('请不要重复发送短信');
            }
          }
        });

      });

      // 图形验证码校验
      $("#smssub").click(function() {
        $.ajax({
          url: "checkSmsRandom",
          dataType: 'html/xml',
          data: {'subRandom':$("#subRandom").val(), 't':new Date().getTime(), 'username':$("#j_username").val()},
          success: function(msg) {
            if (msg == '0') {
              alert('短信验证码输入错误');
              return false;
            }

            $("form[name=loginform]").submit();
          }
        });
      });
    });
  </script>
</head>

<body class="Body">

<div id="randDiv" class="append_parent" style="display:none">
  <div class="append_childW">
    <div class="LoginPop">
      <h4>短信验证</h4>
      <h5>为保障您的信息安全，请输入收到的短信验证码</h5>
      <div class="confirm"><input class="shuru" name="subRandom" id="subRandom" type="text"/><input class="queren" name="" id="smssub" type="button"/></div>
      <h6>验证码在5分钟内有效</h6>
      <h6>如果您在5分钟内没有收到短信验证码</h6>
      <h6><a href="#">请点击这里重新发送</a></h6>
    </div>
  </div>
</div>

<div class="landBox">
  <div class="centerMatter">
    <div class="logo"></div>
    <div class="title"></div>
    <div class="LoginBox">
      <form id="loginform" name="loginform" action="${request.contextPath}/j_spring_security_check" method="post">
        <div class="CenterBack">
          <div class="importA">
            <label>用户名：</label>
            <input name="j_username" id="j_username" type="text"/>
          </div>
          <div class="importB">
            <label>密&nbsp;&nbsp;&nbsp;码：</label>
            <input name="j_password" type="password"/>
          </div>
          <div class="importD">
            <input class="as" id="loginbutton" type="button"/>
            <input class="cd" id="resetbutton" type="button" onclick="javascript:loginform.reset();"/>
          </div>
        </div>
      </form>
    </div>
    %{--<div class="footBox"><span>版权所有:中国移动通信集团&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术支持：幻方朗睿软件科技有限公司</span></div>
 --}% </div>
</div>

</body>
</html>
