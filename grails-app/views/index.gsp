<%
    if (session.getAttribute("loginUser") == null)
        response.sendRedirect("${request.contextPath}/login")
    else
        response.sendRedirect("${request.contextPath}/main")
%>
