<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>系统登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="assets/js/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="assets/style/User_Login.css" type="text/css"></link>
	</head>
	<body id="userlogin_body">
		<form method="post" name="loginForm" id="loginForm">
			<div id="user_login">
				<dl>
					<dd id="user_top">
						<ul>
							<li class="user_top_l"></li>
							<li class="user_top_c"></li>
							<li class="user_top_r"></li>
						</ul>
					<dd id="user_main">
						<ul>
							<li class="user_main_l"></li>
							<li class="user_main_c">

								<div class="user_main_box">
									<ul>
										<li class="user_main_text">
											登陆账号：
										</li>
										<li class="user_main_input">
											<input class="TxtUserNameCssClass" type="text" maxlength="20" id="username" name="username">
										</li>
									</ul>

									<ul>
										<li class="user_main_text">
											登陆密码：
										</li>
										<li class="user_main_input">
											<input class="TxtPasswordCssClass" type="password" maxlength="20" id="password" name="password">
										</li>
									</ul>

									<ul>
										<li class="user_main_text"></li>
										<li class="user_main_input">
											<input type="checkbox" name="record" value="1"/>
											记住账号
										</li>
									</ul>
								</div>

							</li>
							<li class="user_main_r">
								<input class="IbtnEnterCssClass" id=" " type="image" onclick="sub();return false;"
									src="assets/img/login/user_botton.gif" 
									style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"></input>
							</li>
						</ul>
					<dd id="user_bottom">
						<ul>
							<li class="user_bottom_l"></li>
							<li class="user_bottom_c"></li>
							<li class="user_bottom_r"></li>
						</ul>
					</dd>
				</dl>
			</div>
		</form>
	</body>
</html>
<script language="javascript">
$(function () {
	$("#username").val(getCookie("HTWXPTUSERNAME"));
})
function sub(){
	if(loginForm.username.value==""){
		alert("请输入登录帐号!");
		loginForm.username.focus();
		return;
	}
	if(loginForm.password.value==""){
		alert("请输入登录密码!");
		loginForm.password.focus();
		return;
	}
	$.ajax({
		type:'post',
		url:'login/login',
		data:$("#loginForm").serialize(),
		success: function(data){
			if(data=="captcha"){
				alert("验证码输入错误!");
			}else if(data=="notuser"){
				alert("该账户不存在!");
			}else if(data=="errorpassword"){
				alert("密码输入错误!");
			}else if(data =="success"){
				window.location= "main.jsp";
			}else{
				alert("系统错误，请稍后再试!");
			}
		}
 	})
}
function getCookie(objName){
	var arrStr = document.cookie.split("; "); 
	for(var i = 0;i < arrStr.length;i ++){ 
		var temp = arrStr[i].split("="); 
		if(temp[0] == objName){
			return unescape(temp[1]);
		}
	} 
}
</script>