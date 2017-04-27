<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师管理</title>
<link rel="icon" type="image/png" href="assets/i/hgy.png">
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/admin.css">
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.5/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.5/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="assets/js/teacher.js"></script>
</head>
<body>

	<header class="am-topbar am-topbar-inverse admin-header">
	<div class="am-topbar-brand">
		<img width="200" alt="来自河南工学院的图片" src="img/logo.png"> <i>——教师薪资管理系统</i>
	</div>

	<button
		class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
		data-am-collapse="{target: '#topbar-collapse'}">
		<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
	</button>

	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

		<ul
			class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			<li><a href="javascript:;">
					<b>${name}&nbsp;你好&nbsp;</b><span class="am-badge am-badge-warning"></span></a></li>
			<li class="am-dropdown" data-am-dropdown><a
				class="am-dropdown-toggle" data-am-dropdown-toggle
				href="javascript:;"> <span class="am-icon-users"></span> 更多管理<span
					class="am-icon-caret-down"></span>
			</a>
				<ul class="am-dropdown-content">
					<li><a href="password.jsp"><span class="am-icon-user"></span>
							修改密码</a></li>
					<li><a href="update.jsp"><span class="am-icon-cog"></span>
							修改公告</a></li>
					<li><a href="AdminExit"><span class="am-icon-power-off"></span>
							安全退出</a></li>
				</ul></li>
			<li>&nbsp;&nbsp;</li>
		</ul>
	</div>
	</header>

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
			<div class="am-offcanvas-bar admin-offcanvas-bar">
				<ul class="am-list admin-sidebar-list">
					<li><a href="admin-index.jsp"><span class="am-icon-home"></span>
							首页</a></li>
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav'}"><span
							class="am-icon-file"></span> 管理模块 <span
							class="am-icon-angle-right am-fr am-margin-right"></span></a>
						<ul class="am-list # admin-sidebar-sub am-collapse"
							id="collapse-nav">
							<li><a href="group.jsp" class="am-cf"><span
									class="am-icon-check"></span> 系部管理<span
									class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
							<li><a href="teacher.jsp"><span
									class="am-icon-puzzle-piece"></span> 教师管理 <span
									class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
							<li><a href="salary.jsp"><span class="am-icon-calendar"></span>
									薪资管理<span
									class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
							<li><a href="admin-404.jsp"><span class="am-icon-bug"></span>
									404页面</a></li>
						</ul></li>
					<li><a href="password.jsp"><span class="am-icon-table"></span>
							密码管理</a></li>
					<li><a href="update.jsp"><span
							class="am-icon-pencil-square-o"></span> 信息修改</a></li>
					<li><a href="AdminExit"><span class="am-icon-sign-out"></span>
							注销</a></li>
				</ul>

				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p>
							<span class="am-icon-bookmark"></span> 公告
						</p>
						<p>${notice}</p>
					</div>
				</div>

				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p>
							<span class="am-icon-tag"></span><i>HGY</i>
						</p>
						<p>
							<i>Welcome to the HGY!</i>
						</p>
					</div>
				</div>
			</div>
		</div>
		<!-- sidebar end -->

		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<table id="sg" class="easyui-datagrid" title="教师信息管理"
						fitColumns="true" rownumbers="true" pagination="true" fit="true"
						toolbar="#TcBtn" url="TeacherList">
						<thead>
							<tr>
								<th field="" checkbox="true">复选框</th>
								<th field="teacher_id" width="10">教师号</th>
								<th field="teacher_name" width="10">教师姓名</th>
								<th field="teacher_sex" width="10">性别</th>
								<th field="teacher_group" width="10" hidden="true">系部编号</th>
								<th field="group_name" width="10">系部名称</th>
								<th field="teacher_tel" width="10">手机号</th>
								<th field="teacher_email" width="10">邮箱</th>
								<th field="teacher_card" width="10">身份证号</th>
								<th field="teacher_desc" width="10">教师备注</th>
							</tr>
						</thead>
					</table>
					<div id="TcBtn">
						<div>
							<a href="javascript:openAddTeacher()" class="easyui-linkbutton"
								iconCls="icon-add">添加教师</a> <a href="javascript:openTeacher()"
								class="easyui-linkbutton" iconCls="icon-edit">修改教师</a> <a
								href="javascript:TeacherDelete()" class="easyui-linkbutton"
								iconCls="icon-remove">删除教师</a>
						</div>
						<div>
							<div>
								教师姓名：<input size="10" type="text" id="S_Name" name="S_Name" />
								系部名称：<input id="s_groupId" name="s_groupId"
									class="easyui-combobox"
									data-options=" url:'GroupNameList',    
    valueField:'group_id',  
    textField:'group_name',
    editable:false">
								性别：<select id="cc" class="easyui-combobox"
									data-options="editable:false" name="S_sex"
									style="width: 100px;">
									<option value="">---请选择--</option>
									<option value="男">男</option>
									<option value="女">女</option>
								</select> <a id="btn4" href="javascript:searchTeacher()"
									class="easyui-linkbutton" iconCls="icon-search">查询</a> <a
									href="javascript:textReset()" class="easyui-linkbutton"
									iconCls="icon-cancel">清空</a>
							</div>
						</div>
						<div id="slg" class="easyui-dialog" closed="true"
							style="width: auto; height: auto;" button="#dlg-button">
							<form id="sfk" method="post">
								<table>
									<tr>
										<td>教师姓名</td>
										<td><input type="text" autocomplete="off"  name="teacher_name"
											id="teacher_name" class="easyui-validatebox" data-options="required:true,validType:'isNull'" /></td>
									</tr>
									<tr>
										<td>性别</td>
										<td><select id="teacher_sex" class="easyui-combobox"
											data-options="editable:false" class="easyui-combobox"
											name="teacher_sex" style="width: 100px;">
												<option value="男">男</option>
												<option value="女">女</option>
										</select></td>
									</tr>
									<tr>
										<td>系部名称</td>
										<td><input id="teacher_group" name="teacher_group"
											class="easyui-combobox"
											data-options=" url:'GroupNameList',    
											    valueField:'group_id',  
											    textField:'group_name',
											    editable:false"></td>
									</tr>

									<tr>
										<td>邮箱</td>
										<td><input rows="10" autocomplete="off" cols="20" name="teacher_email"
											id="teacher_email" class="easyui-validatebox"
											data-options="required:true,validType:'email'" /></td>
									</tr>
									<tr>
										<td>联系方式</td>
										<td><input rows="10" autocomplete="off"  cols="20" name="teacher_tel"
											id="teacher_tel" class="easyui-validatebox"
											data-options="required:true,validType:'phone'" /></td>
									</tr>
									<tr>
										<td>身份证号</td>
										<td><input rows="10" autocomplete="off"  cols="20" name="teacher_card"
											id="teacher_card" class="easyui-validatebox"
											data-options="required:true,validType:'card'" />	
											</td>
									</tr>
										<tr style="display:none;">
										<td>身份证号</td>
										<td><input rows="10" autocomplete="off"   cols="20" name="teacher_cards"
											id="teacher_cards" class="easyui-validatebox"  />	
											</td>
									</tr>
									<tr>
										<td>教师备注</td>
										<td><textarea rows="10" cols="20" name="teacher_desc"
												id="teacher_desc" class="easyui-validatebox"></textarea></td>
									</tr>
								</table>
							</form>
							<div id="dlg-button">
								<a href="javascript:saveTeacher()" class="easyui-linkbutton"
									iconCls="icon-ok">保存</a> <a href="javascript:close()"
									class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
							</div>
						</div>
					</div>
					<footer class="admin-content-footer">
					<hr>
					<p class="am-padding-left" style="text-align: center">Copyright
						© 2017 www.hneeu.edu.cn. All Rights Reserved. By YZX 技术支持：杨振欣</p>
					</footer>
				</div>
			</div>
		</div>
		<!-- content end -->
	</div>
</body>
</html>