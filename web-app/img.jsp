<%@ page import="com.emag.util.img.RandImgCreater" %><%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.setContentType("image/jpeg");

RandImgCreater rc = new RandImgCreater(response, 4, "0123456789");
String rand = rc.createRandImage();
out.clear();
out = pageContext.pushBody();

session.setAttribute("rand",rand);
%>