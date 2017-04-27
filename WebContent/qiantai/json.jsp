<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="../assets/i/hgy.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>薪资详情</title>
<script type="text/javascript" src="../assets/js/jquery-2.1.3.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../assets/css/qian-json.css">
</head>
<body>
	<nav>
		<img width="100%" src="../img/system.png">
		<p class="names">
			<a href="javascript:cl()" id="teachername">${realname}</a>老师,在线 <a
				href="../UpdateUser" class="message">修改信息</a> <a href="../QianExit">安全退出</a>
		</p>
		<p class="users">
			<span id="time"></span>
		</p>
	</nav>
	<div class="content .clear">
		<div id="searchtime">
			<h3>查询历史薪资单</h3>
			<input name="s_date" id="s_date" type="text" size="20"
				class="easyui-datebox" data-options="validType:'month'" /> <input
				type="button" onclick="javascript:update()" id="searchdates"
				value="查询" />
		</div>
		<table id="salary" border="1"
			style="margin: 0 auto; text-align: center;" width="80%"
			cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="14" height="50px;"><h2>
						<span id="timesalary"></span>工资单
					</h2></td>
			</tr>
			<tr height="30px">
				<td>薪资编号</td>
				<td>工资月份</td>
				<td>教师编号</td>
				<td>教师姓名</td>
				<td>基本工资</td>
				<td>加班工资</td>
				<td>奖金</td>
				<td>个人所得税</td>
				<td>补贴</td>
				<td>扣除工资</td>
				<td>其他1</td>
				<td>其他2</td>
				<td>实发工资</td>
				<td>发放状态</td>
			</tr>
		</table>
	</div>
	<div class="footer">
		<hr>
		<p>Copyright &copy; 2017 www.hneeu.edu.cn. All Rights Reserved. By
			YZX 技术支持：杨振欣</p>
	</div>
	<script type="text/javascript" src="../assets/js/qian-json.js"></script>
</body>
</html>