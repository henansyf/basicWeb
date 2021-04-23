<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=8" />

<title>优数-调查数据管理系统</title>
<link rel="stylesheet" type="text/css" href="css/admin/style.css"/>
<link rel="shortcut icon" type="image/x-icon" href="images/admin/logos.jpg"/>
    <script >

        function logout_f(){
            window.parent.location='<%=basePath%>'+'logout';
        }

    </script>
</head>
<body>
<div id="north" class="topbandj">
<span>
<table  border="0" cellspacing="0" cellpadding="0" style="padding-top: 15px">
  <tr>
    <td >   
    <div style="background-image:url('images/admin/top2.png');padding-right: 30px; height: 22px">
    <img src="images/admin/top_ico1.png" width="17" height="19" align="absmiddle" style="margin-bottom:4px;padding-left: 10px"/><font size=2 style="color: white; padding-right: 10px;">当前用户：${user.username}</font>
    <img src="images/admin/top_ico2.png" width="17" height="19" align="absmiddle" style="margin-bottom:4px;"/><a  href="javascript:void(0)" onclick="logout_f()" style="color: white;padding-right: 10px"><font size=2>退出</font></a>
    <img src="images/admin/top_ico3.png" width="17" height="19" align="absmiddle" style="margin-bottom:4px;" /><a href="javascript:void(0)" style="color: white;" onclick="window.parent.updatePass();return false"><font size=2>修改密码</font></a>
  	</div></td>
  </tr>
</table>
</span>
<img src="images/admin/logozone.png" width="363" height="24" class="imgs" style="padding: 15px"/></div>


</body> 
</html>