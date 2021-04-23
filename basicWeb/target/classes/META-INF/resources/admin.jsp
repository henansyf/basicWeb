<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>系统</title>
	<link rel="stylesheet" type="text/css" href="css/admin/style.css" />
	<script type="text/javascript" src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript"  src="js/showwin.js"></script>
	<script type="text/javascript"  src="js/fastclick.js"></script>
	<style type="text/css">
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-color: #00a2ca;
		}

		.login_form_btn2 {
			BORDER-RIGHT: #06B0DE 1px solid;
			BORDER-TOP: #06B0DE 1px solid;
			DISPLAY: inline-block;
			FONT-SIZE: 14px;
			BACKGROUND: #00A2C9;
			BORDER-LEFT: #06B0DE 1px solid;
			WIDTH: 80px;
			COLOR: #fff;
			LINE-HEIGHT: 29px;
			MARGIN-RIGHT: 20px;
			BORDER-BOTTOM: #06B0DE 1px solid;
			HEIGHT: 33px;
			TEXT-ALIGN: center
		}

		.login_form_btn2:hover {
			COLOR: #F7FA06;
			TEXT-DECORATION: none
		}

		.login_form_ipt {
			BORDER-RIGHT: #e8e8e8 1px solid;
			PADDING-RIGHT: 5px;
			BORDER-TOP: #e8e8e8 1px solid;
			PADDING-LEFT: 5px;
			FONT-SIZE: 14px;
			PADDING-BOTTOM: 10px;
			VERTICAL-ALIGN: middle;
			BORDER-LEFT: #e8e8e8 1px solid;
			WIDTH: 160px;
			MARGIN-RIGHT: 5px;
			PADDING-TOP: 10px;
			BORDER-BOTTOM: #e8e8e8 1px solid;
			HEIGHT: 16px;
			box-shadow: 1px 1px 1px #f6f6f6
		}
	</style>
	<script type="text/javascript">
		function changeValidateCode(obj){
			var timenow = new Date().getTime();
			obj.src="<%=request.getContextPath()%>/alogin/getImage?d="+timenow;
		}


	</script>
</head>
<body>
<input type="hidden"
	   value="expired<%=request.getParameter("expired")%>" />


<div style="margin-top: 150px; width: 100%; margin: 0 auto;">
	<form action="loginCheck" method="post" id="loginForm1" name="form1"
		  onsubmit="return check();">
		<table width="649" height="278" border="0" align="center"
			   cellpadding="0" cellspacing="0" bgcolor="ffffff"
			   style="margin-top: 150px;">
			<tr><td><br/></td></tr>
			<tr ><td style='text-align:center;font-size:28px;font-weight:bold;color:#0c547c;font-family:stkaiti' >电子后台管理系统</td> </tr>
			<tr>
				<%--<td width="215" valign="middle">
                    <div align="center">
                        <img src="images/admin/login_logo.png" width="198" height="164" />
                    </div>
                </td>
                --%>
				<td width="422">
					<table width="367" height="120" align="center" class=register_tb>

						<tr>
							<td width="70">
								帐户名：
							</td>
							<td width="274">
								<strong><input id="username"
											  class=login_form_ipt type="text" />
								</strong>
							</td>
						</tr>
						<tr>
							<td>
								密码：
							</td>
							<td>
								<input id="password" id="password" type="password"
									   class=login_form_ipt />
							</td>
						</tr>
						<tr>
							<td>
								验证码：
							</td>
							<td>
								<input id="validatecode" type="text" class="login_form_ipt" />
								<img src="<%=request.getContextPath()%>/alogin/getImage" id="imageCode"
									 onclick="changeValidateCode(this)" width="60" height="20"
									 align="middle" />
							</td>
						</tr>
						<tr>
							<th></th>
							<td>
								<input id="sub_botton" type="button" class="login_form_btn2" value="登  录" />
								<input name="button2" type="button" class="login_form_btn2"
									   id="button2" value="清  空" onclick="clearValue()" />


							</td>
						</tr>

					</table>
				</td>
			</tr>
		</table>
	</form>
</div>

</body>

<script>

	$(function(){
		FastClick.attach(document.body);
		$("#sub_botton").on("click",function(){
			var matches = /^[a-zA-Z0-9_]{2,18}$/ ;
			var data={
				"username":$("#username").val(),
				"password":$("#password").val(),
				"validatecode":$("#validatecode").val()
			}
			if(isNull(data.username)){
				alert("用户名不能为空！");
				f.username.focus();
				return false;
			}else if(!matches.exec(data.username)){
				alert("用户名由2-18位字母、数字和下划线组成");
				f.username.focus();
				return false;
			}else if(isNull(data.password)){
				alert("密码不能为空！");
				f.password.focus();
				return false;
			}else if(isNull(data.validatecode)){
				alert("验证码不能为空");
				f.username.focus();
				return false;
			}

			sAlert("正在提交...");
			$.ajax({
				url : 'loginCheck',
				data :data,
				type : "post",
				dataType : "html",
				async : false,
				success : function(res){
					var resp = eval("("+res+")");
					if(resp.status == '1'){
						window.location.href='<%=basePath%>'+'alogin/index';
					}else if(resp.status == '2'){
						removeAlert();
						alert(resp.msg);
						changeValidateCode(document.getElementById('imageCode'));
						return;
					}else{
						removeAlert();
						alert("登录失败");
						changeValidateCode(document.getElementById('imageCode'));
						return;
					}
				},error: function(){  removeAlert();alert('操作失败！请稍后再试！');changeValidateCode(document.getElementById('imageCode'));}
			});

		});
	})

	function isNull(val){
		if(val.replace(/^\s+|\s+$/g,"").length==0)
		{
			return true;
		}
		return false;
	}

	function clearValue(){
		document.getElementById('loginForm1').reset();
		return false;
	}

</script>
</html>