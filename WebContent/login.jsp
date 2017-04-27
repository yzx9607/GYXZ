<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<link href="assets/css/qianlogin.css" rel='stylesheet' type='text/css'
	media="all" />
<link rel="icon" type="image/png" href="assets/i/hgy.png">
<!-- /css files -->
<style type="text/css">
body {
	background: #195d97;
}
</style>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	Cookie[] cookies = request.getCookies();
	if (cookies != null&&cookies.length>0) {
		for (Cookie c : cookies) {
			if(c.getName().equals("checkname")){
				request.setAttribute("name", c.getValue());
			}
			if(c.getName().equals("checkpwd")){
				request.setAttribute("pwd", c.getValue());
			}
		}
		}
	String flag=(String)session.getAttribute("flag");
	String checkNull=(String)session.getAttribute("checkNull");
	if(checkNull=="false"){
		session.setAttribute("errorMsg","账号或者密码不能为空");
	}else{
		if(flag=="false"){
			session.setAttribute("errorMsg","账号或者密码错误");
		}else{
			session.setAttribute("errorMsg","");
		}
	}
	session.setAttribute("flag","true");
	session.setAttribute("checkNull","true");
%>
	<h1>
		<img width="35%" style="margin: 0 auto" src="img/managers.png">
	</h1>
	<div class="log">
		<div class="content1">
			<h2 style="font-family: '微软雅黑'">管理员登陆</h2>
			<form action="Login" method="post">
				<input type="text" name="userName" id="userName" value="${name}"
					placeholder="请输入管理员账号"> <input type="password"
					name="userPwd" id="userPwd" value="${pwd}" placeholder="请输入您的密码">
				<div class="button-row">
					<input style="width: 78%" type="submit" class="sign-in" value="登陆">
					<br> <br> <br> <span style="color: white">记住密码&nbsp;&nbsp;<input
						type="checkbox" name="autoLogin"></span>
					<div class="clear"></div>
				</div>
				<h4 id="error">${errorMsg}</h4>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<div class="footer">
		<p>Copyright &copy; 2017 www.hneeu.edu.cn. All Rights Reserved. By
			YZX 技术支持：杨振欣</p>
	</div>
</body>
</html>