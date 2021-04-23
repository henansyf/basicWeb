<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>" />
</head>
<body>
<center>
    <h2>警告：您的账户在其他位置登录！</h2>
    <a href="admin.jsp">返回登录页面</a>
</center>

</body>
</html>
