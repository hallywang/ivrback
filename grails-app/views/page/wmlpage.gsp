<?xml version="1.0" encoding="UTF-8"?><%@ page contentType="text/vnd.wap.wml;charset=utf-8" %><% response.setContentType("text/vnd.wap.wml")
request.setCharacterEncoding("UTF-8")
%><!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
  <head>
    <meta http-equiv="Cache-Control" content="max-age=0" forua="true"/>
    <meta http-equiv="Cache-Control" content="no-cache" forua="true"/>
    <meta http-equiv="Cache-Control" content="must-revalidate" forua="true"/>
  </head>
  <card title="${template?.description}" id="${template?.link}">
    <g:render template="/page/${topic.name}/${template.name}" model="${dataModel}"/>
  </card></wml>