<%@ page contentType="text/html;charset=utf-8" %><?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%
    // Copyright 2009 Google Inc. All Rights Reserved.



    def googleAnalyticsGetImageUrl = {def callback ->
        StringBuilder url = new StringBuilder();
        url.append("/gamecms/ga.jsp" + "?");
        url.append("utmac=").append("MO-15054840-1");
        url.append("&utmn=").append(Integer.toString((int) (Math.random() * 0x7fffffff)));
        String referer = request.getHeader("referer");
        String query = request.getQueryString();
        String path = request['javax.servlet.forward.request_uri'];
        if (referer == null || "".equals(referer)) {
            referer = "-";
        }
        url.append("&utmr=").append(URLEncoder.encode(referer, "UTF-8"));
        if (path != null) {
            if (query != null) {
                path += "?" + query;
            }
            url.append("&utmp=").append(URLEncoder.encode(path, "UTF-8"));
        }
        url.append("&guid=ON");

        callback(url.toString().replace("&", "&amp;"));

    }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${template?.title}</title>
    ${template?.head}
</head>
<body>
<g:render template="/page/part/head"/>
<g:render template="/page/sp/${digitDomain}/${topic.name}/${template.name}" model="${dataModel}"/>
<g:render template="/page/part/foot"/>
<% String googleAnalyticsImageUrl = ""

def c = {a ->
    googleAnalyticsImageUrl = a
}
googleAnalyticsGetImageUrl(c); %>
<img src="<%=googleAnalyticsImageUrl%>"/>
</body>
</html>



