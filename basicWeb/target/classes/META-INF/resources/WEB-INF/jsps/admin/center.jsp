<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="shortcut icon" type="image/x-icon" href="images/admin/logos1.jpg">
</head>
<style>
/*	全局去边距*/* {margin: 0px;padding: 0px;border: 0px;} 
	
	/*	去滚动条*/html { overflow:hidden;}

	/*	背景大图*/.bj {
	overflow:hidden;
		position: relative;
		margin: auto;
		width:100%;
		min-width: 500px;
		max-width: 965px;
		height: 590px;
		background-image: url(images/admin/center.jpg);
		background-repeat: no-repeat;
		background-position: center;
		background-size: 100%;
	}
	
	/*	背景色*/body{
		background-color: #f1f1f1;
	}
</style>
<body  >
<div class="bj"></div>
</body>
</html>