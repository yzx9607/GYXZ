<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>教师注册</title>
<script type="text/javascript" src="../assets/js/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="../assets/css/register.css">
<link rel="icon" type="image/png" href="../assets/i/hgy.png">
</head>
<body>
	<nav>
		<img width="100%" src="../img/system.png">
	</nav>
	<content>
	<h2>教师注册</h2>
	</content>
	<div class="reg clear">
		<h3>输入注册信息</h3>
		<form id="ff" method="post" action="../UserSave" name="form1"
			onSubmit="return checkAll()">
			<label for="newname">用户名</label> <input name="newname" id="newname"
				onblur="checkName()" placeholder="请输入用户名"> <span id="tishi"></span>
			<label for="newpwd">密码</label> <input name="newpwd"
				onblur="checkpwds()" id="newpwd" type="password" placeholder="请输入密码">
			<span id="tishi1"></span> <label for="newpwd1">确认密码</label> <input
				name="newpwd1" onblur="checkpwds1()" id="newpwd1" type="password"
				placeholder="确认密码"> <span id="tishi2"></span> <label
				for="newrealname">真实姓名</label> <input name="newrealname"
				onblur="checkrealName()" id="newrealname" placeholder="真实姓名">
			<span id="tishi3"></span> <label for="newphone">手机号</label> <input
				name="newphone" onblur="checkmobile()" id="newphone"
				placeholder="手机号"> <span id="tishi4"></span> <label
				for="newcard">身份证号码</label> <input name="newcard"
				onblur="CheckCard()" id="newcard" placeholder="身份证号码"> <span
				id="tishi5"></span> <br> <br> <input class="submits"
				type="submit" value="注册"> <input class="submits"
				type="reset" value="重输"> <a style="text-decoration: none;"
				href="qianlogin.jsp">返回登陆</a>
		</form>
	</div>

	<br>
	<h6 class="notice">&nbsp;&nbsp;注意：请认真填写您的个人信息，如信息不真实，则您的薪资将不会被查询到。</h6>
	<div align="center" style="font-size: 13px; margin-top: 35px;">
		<p>Copyright &copy; 2017 www.hneeu.edu.cn. All Rights Reserved. By
			YZX 技术支持：杨振欣</p>
	</div>
</body>
<script type="text/javascript" src="../assets/js/register.js"></script>
</html>