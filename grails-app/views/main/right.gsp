<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>管理后台</title>
  <link href="${request.contextPath}/css/admin.css" rel="stylesheet" type="text/css"/>
  <link href="${request.contextPath}/css/theme.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="body-box">
  <div class="rhead">
    <div class="rpos">当前位置： 首页 - 欢迎页</div>
    <div class="clear"></div>
  </div>
  <%
    def props = System.getProperties()

    long totalMemory = Runtime.getRuntime().totalMemory();
    long freeMemoery = Runtime.getRuntime().freeMemory();

/*

    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }

    Enumeration<NetworkInterface> netInterfaces = null;
    try {
      netInterfaces = NetworkInterface.getNetworkInterfaces();
      while (netInterfaces.hasMoreElements()) {
        NetworkInterface ni = netInterfaces.nextElement();
        System.out.println("whl==DisplayName:" + ni.getDisplayName());
        System.out.println("whl==Name:" + ni.getName());
        Enumeration<InetAddress> ips = ni.getInetAddresses();
        while (ips.hasMoreElements()) {
          System.out.println("whl==IP:"
                  + ips.nextElement().getHostAddress());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
*/

  %>

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="border:#c8c8e7 1px solid; border-top:0; margin-top:5px;">
    <tr>
      <td height="26" colspan="2" align="left" background="${request.contextPath}/img/admin/msg_bg.jpg">
        &nbsp;&nbsp;<img src="${request.contextPath}/img/admin/ico1.gif" border="0" align="absmiddle"/> <strong  style=" color:#FFF;">欢迎使用</strong></td>
    </tr>
    <tr>
      <td width="18%" height="25" align="right" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;"></td>
      <td width="82%" align="left" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;"></td>
    </tr>
    <tr style="background-color:#F7F8FA">
      <td height="25" align="right" style="border-bottom:#cccccc 1px dashed;">操作系统版本：</td>
      <td align="left" style="border-bottom:#cccccc 1px dashed;">
        ${props['os.name']} ${props['os.version']}
      </td>
    </tr>
    <tr>
      <td height="25" align="right" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">操作系统类型：</td>
      <td align="left" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">
        ${props['os.arch']} ${props['sun.arch.data.model']}位
      </td>
    </tr>
    <tr>
      <td height="25" align="right" bgcolor="#F7F8FA" style="border-bottom:#cccccc 1px dashed;">用户,目录,临时目录：</td>
      <td align="left" bgcolor="#F7F8FA" style="border-bottom:#cccccc 1px dashed;">
        ${props['user.name']}, ${props['user.dir']}, ${props['java.io.tmpdir']}
      </td>
    </tr>
    <tr>
      <td height="25" align="right" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">JAVA运行环境：</td>
      <td align="left" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">
        ${props['java.runtime.name']} ${props['java.runtime.version']}
      </td>
    </tr>
    <tr>
      <td height="25" align="right" bgcolor="#F7F8FA" style="border-bottom:#cccccc 1px dashed;">JAVA虚拟机：</td>
      <td align="left" bgcolor="#F7F8FA" style="border-bottom:#cccccc 1px dashed;">
        ${props['java.vm.name']} ${props['java.vm.version']}
      </td>
    </tr>
    <tr>
      <td height="25" align="right" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">剩余内存/总内存：</td>
      <td align="left" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">
        ${(freeMemoery / 1024 / 1024).setScale(2, BigDecimal.ROUND_HALF_UP)} /${(totalMemory / 1024 / 1024).setScale(2, BigDecimal.ROUND_HALF_UP)}MB</td>
    </tr>
    <tr>
      <td height="25" align="right" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">服务器地址：</td>
      <td align="left" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">
        ${request.getLocalAddr()}
        <br/>

      </td>
    </tr>
  </table>
</div>
</body>
</html>