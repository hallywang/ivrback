<%@ page contentType="text/html;charset=utf-8" %><?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="Cache-Control" content="no-cache"/>
  <title>${template?.title}</title>
  ${template?.head}
</head>
<body>
<g:render template="/page/${topic.name}/${template.name}" model="${dataModel}"/>
</body>
</html>



