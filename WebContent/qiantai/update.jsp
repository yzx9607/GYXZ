<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,java.sql.*,www.gyxz.qiantai.*,www.gyxz.util.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../assets/js/jquery-2.1.3.js"></script>
<title>修改信息</title>
<link rel="icon" type="image/png" href="../assets/i/hgy.png">
<link rel="stylesheet" type="text/css" href="../assets/css/upuser.css">
</head>
<body>
	<% 
User users=(User)session.getAttribute("upuser");
%>
	<nav> <img width="100%" src="../img/system.png">
	<p class="names">
		<a href="javascript:cl()" id="teachername">${realname}</a>老师,在线 <a
			href="json.jsp" class="message">返回主页</a> <a href="../QianExit">安全退出</a>
	</p>
	<p class="users">
		<span id="time"></span>
	</p>
	</nav>
	<content>
	<h2>修改教师信息</h2>
	</content>
	<div class="reg clear">
		<h3>输入修改信息</h3>
		<form id="ff" name="form1" method="post" action="../UserSave"
			onsubmit="return checkform()">

			<label for="newname">用户名</label> <input name="userid" type="hidden"
				id="userid" value="<%=users.getUserid()%>" readonly="readonly">
			<input name="newname1" type="hidden" id="newname1"
				value="<%=users.getLoginnname() %>"> <input name="newname"
				id="newname" value="<%=users.getLoginnname() %>"
				onblur="CheckUsername()" placeholder="请输入用户名"> <span
				id="tishi"></span> <label for="newpwd">密码</label> <input
				name="newpwd" onblur="checkpwd()" id="newpwd"
				value="<%=users.getUserpwd() %>" type="password" placeholder="请输入密码">
			<span id="tishi1"></span> <label for="newpwd1">确认密码</label>
			<input name="newpwd1" onblur="checkpwd1()" id="newpwd1"
				value="<%=users.getUserpwd() %>" type="password" placeholder="确认密码">
			<span id="tishi2"></span> <label for="newrealname">真实姓名</label>
			<input name="newrealname" onblur="checkwne()" id="newrealname"
				value="<%=users.getUsername() %>" placeholder="真实姓名"> <span
				id="tishi3"></span> <label for="newphone">手机号</label> <input
				name="newphone" onblur="checkphone()" id="newphone"
				value="<%=users.getUserphone() %>" placeholder="手机号"> <span
				id="tishi4"></span> <label for="newcard">身份证号码</label> <input
				name="newcard1" id="newcard1" type="hidden"
				value="<%=users.getUsercard()%>"> <input name="newcard"
				onblur="CheckCardHas()" id="newcard"
				value="<%=users.getUsercard()%>" placeholder="身份证号码"> <span
				id="tishi5"></span> <br> <br> <input
				class="submits" type="submit" value="提交"> <input
				class="submits" type="reset" value="重输">
		</form>
	</div>
	<div class="footer">
		<hr>
		<p>Copyright &copy; 2017 www.hneeu.edu.cn. All Rights Reserved. By
			YZX 技术支持：杨振欣</p>
	</div>
	<script type="text/javascript" src="../assets/js/update.js"></script>
</body>
</html>