<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>教师登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<link href="../assets/css/qianlogin.css" rel='stylesheet'
	type='text/css' media="all" />
<link rel="icon" type="image/png" href="../assets/i/hgy.png">
<!-- /css files -->
<style type="text/css">
body {
	background: url(../img/banner.jpg) no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
}
</style>
</head>
<body>
	<%
		String flag = (String) session.getAttribute("flag");
		String checkNull = (String) session.getAttribute("checkNull");
		if (checkNull == "false") {
			session.setAttribute("error", "用户名或者密码不能为空");
		} else {
			if (flag == "false") {
				session.setAttribute("error", "用户名或者密码错误");
			} else {
				session.setAttribute("error", "");
			}
		}
		session.setAttribute("flag", "true");
		session.setAttribute("checkNull", "true");
	%>
	<h1>
		<img width="35%" style="margin: 0 auto" src="../img/teacher.png">
	</h1>
	<!-- <h2>教师工资查询系统</h2> -->
	<div class="log">
		<div class="content1">
			<h2 style="font-family: '微软雅黑'">教师登陆</h2>
			<form action="../UserLogin" method="post">
				<input type="text" name="username" id="username"
					placeholder="请输入登录名"> <input type="password" name="pwd"
					id="pwd" placeholder="请输入密码">
				<div class="button-row">
					<input type="submit" class="sign-in" value="登陆"> <a
						href="register.jsp" class="reset">注册</a> <br> <br> <br>
					<a href="pwd.jsp">忘记密码？</a>
					<div class="clear"></div>
				</div>
				<h4 id="error">${error}</h4>
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