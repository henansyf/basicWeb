<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<base href="<%=basePath%>">
<title>中国老年医学学会-后台管理系统</title>

<link rel="stylesheet" type="text/css" href="css/admin/icon.css" />
<link rel="stylesheet" type="text/css" href="ext5/ext-theme-neptune/resources/ext-theme-neptune-all.css" />
<link rel="stylesheet" type="text/css" href="ext5/ux/css/ItemSelector.css" />

</head>
<body onload="removeAlert()">
<input type="hidden" value="<%=basePath%>" id="basePath"/> 
<input type="hidden" id="username"  value="${user.username}"/>
<input type="hidden" id="userId"  value="${user.id}"/>
<input type="hidden" id="security"  value="${user.securityStr}"/>

</body>
<script type="text/javascript" language="javascript" src="js/showwin.js"></script>
<script type="text/javascript">sAlert('正在加载数据，请稍等···<br>')</script>
<script type="text/javascript" src="ext5/ext-all.js"></script>
<script type="text/javascript" src="ext5/ext-locale-zh_CN.js"></script>
<script type="text/javascript" src="ext5/ext-theme-neptune/ext-theme-neptune.js"></script>
<script type="text/javascript" src="admin/app.js"></script>
<script type="text/javascript" src="admin/admin.js"></script>
<script type="text/javascript">
function checkUserButton(button_code){
	if(security.indexOf(button_code) > -1){
		return true;
	} else {
		return false;
	}
}

Ext.Ajax.on('requestcomplete',checkUserSessionStatus, this); 
function checkUserSessionStatus(conn,response,options){ 
	if(response.responseText.indexOf("loginCheck") > 0){
		window.location.href ="<%=basePath%>"+"j_spring_security_logout";
	}
} 
</script>
</html>