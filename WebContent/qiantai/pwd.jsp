<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>找回密码</title>
<script type="text/javascript" src="../assets/js/jquery-3.1.0.min.js"></script>
<link rel="icon" type="image/png" href="../assets/i/hgy.png">
<link rel="stylesheet" type="text/css" href="../assets/css/pwd.css">
</head>
<body>
	<nav> <img width="100%" src="../img/system.png"> </nav>
	<content>
	<h2>找回密码</h2>
	</content>
	<div class="regs clear">
		<h3>输入依据信息</h3>
		<form id="ff" method="get" action="../Remeber" name="form1"
			onSubmit="return checkforms()">

			<label>你的用户名</label> <input name="newname" id="newname"
				onblur="CheckTeacher()" placeholder="请输入用户名"> <span
				id="tishi"></span> <label>新密码</label> <input name="newpwd"
				onblur="checkTpwd()" id="newpwd" type="password" placeholder="请输入密码">
			<span id="tishi1"></span> <label>确认密码</label> <input name="newpwd1"
				onblur="checkTpwd1()" id="newpwd1" type="password"
				placeholder="确认密码"> <span id="tishi2"></span> <label>注册时使用的手机号</label>
			<input name="newphone" onblur="checkTphone()" id="newphone"
				placeholder="手机号"> <span id="tishi4"></span> <label>注册时使用身份证号码</label>
			<input name="newcard" onblur="CheckTCardHas()" id="newcard"
				placeholder="身份证号码"> <span id="tishi5"></span> <br> <br>
			<input class="submits" type="submit" value="找回"> <input
				class="submits" type="reset" value="重输"> <a
				style="text-decoration: none;" href="qianlogin.jsp">返回登陆</a>
		</form>

	</div>
	<div align="center" style="font-size: 13px; margin-top: 35px;">
		<p>Copyright &copy; 2017 www.hneeu.edu.cn. All Rights Reserved. By
			YZX 技术支持：杨振欣</p>
	</div>
	<script type="text/javascript" src="../assets/js/pwd.js"></script>
</body>
</html>