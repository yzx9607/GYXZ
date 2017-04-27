<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>薪资管理</title>
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
<script type="text/javascript" src="assets/js/salary.js"></script>
<style type="text/css">
.left {
	width: 100px;
	text-align: right;
}
</style>
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
					<table id="sg" class="easyui-datagrid" title="薪资信息管理"
						fitColumns="true" rownumbers="true" pagination="true" fit="true"
						toolbar="#SalaryBtn" url="SalaryList">
						<thead>
							<tr>
								<th field="" checkbox="true">复选框</th>
								<th field="id"width="5">编号</th>
								<th field="s_date" width="10">月份</th>
								<th field="teacher_id" width="10">教师号</th>
								<th field="teacher_name" width="10">教师姓名</th>
								<th field="base_pay" width="10">基本工资</th>
								<th field="add_pay" width="10">加班工资</th>
								<th field="bonus_pay" width="10">奖金</th>
								<th field="tax_pay" width="10">个人所得税</th>
								<th field="subsidy_pay" width="10">补贴</th>
								<th field="deduction" width="10">扣除工资</th>

								<th field="other_pay1" width="10">其他1</th>
								<th field="other_pay2" width="10">其他2</th>
								<th field="sum_pay" width="10">实发工资</th>
								<th field="pay_status" width="10">发放状态</th>

							</tr>
						</thead>
					</table>
					<div id="SalaryBtn">
						<div>
							<a href="javascript:openAddsalary()" class="easyui-linkbutton"
								iconCls="icon-add">添加工资单</a> <a href="javascript:opensalary()"
								class="easyui-linkbutton" iconCls="icon-edit">修改（更新）工资单</a> <a
								href="javascript:salaryDelete()" class="easyui-linkbutton"
								iconCls="icon-remove">删除工资单</a> <a href="javascript:salaryPay()"
								class="easyui-linkbutton" iconCls="icon-save">发放工资</a> <a
								href="javascript:salaryPay1()" class="easyui-linkbutton"
								iconCls="icon-edit">取消发放</a>
						</div>
						<div>
							<div>
								教师姓名：<input size="10" type="text" id="S_Name" name="S_Name" />
								工资月份：<input id="db" size="10"></input> 发放状态： <select
									id="pay_status" class="easyui-combobox" name="pay_status"
									style="width: 150px;">
									<option value="">--请选择--</option>
									<option value="未发放">未发放</option>
									<option value="已发放">已发放</option>
								</select> <a id="btn4" href="javascript:saerchsalary()"
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
										<td>月份</td>
										<td><input name="s_date" id="s_date" type="text"
											size="13" class="easyui-datebox"
											data-options="required:true,validType:'month'" /></td>
									</tr>
									<tr>
										<td>教师号</td>
										<td><input size="13" id="teacher_id"
											class="easyui-numberbox" name="teacher_id" required="true" />
										</td>
									</tr>
									<tr>
										<td>基本工资</td>
										<td><input size="10" min="0" prefix="¥" id="base_pay"
											name="base_pay" value="0.0" type="text"
											class="easyui-numberbox" data-options="min:0,precision:2"></input>
										</td>
									</tr>
									<tr>
										<td>加班工资</td>
										<td><input size="10" min="0" prefix="¥" rows="10" cols="20"
											name="add_pay" id="add_pay" value="0.0"
											class="easyui-numberbox" data-options="required:true" /></td>
									</tr>
									<tr>
										<td>奖金</td>
										<td><input size="10" min="0" prefix="¥" rows="10" cols="20"
											name="bonus_pay" id="bonus_pay" value="0.0"
											class="easyui-numberbox" data-options="required:true" /></td>
									</tr>
									<tr>
										<td>个人所得税</td>
										<td><input size="10" min="0" prefix="¥" rows="10" cols="20"
											name="tax_pay" id="tax_pay" value="0.0"
											class="easyui-numberbox" data-options="required:true" /></td>
									</tr>
									<tr>
										<td>补贴</td>
										<td><input size="10" min="0" prefix="¥" rows="10" cols="20"
											name="subsidy_pay" id="subsidy_pay" value="0.0"
											class="easyui-numberbox" data-options="required:true" /></td>
									</tr>
									<tr>
										<td>扣除工资</td>
										<td><input size="10" min="0" prefix="¥" rows="13" cols="20"
											name="deduction" id="deduction" value="0.0"
											class="easyui-numberbox" data-options="required:true" /></td>
									</tr>
									<tr>
										<td>其他1</td>
										<td><input size="10" min="0" prefix="¥" rows="10" cols="20"
											name="other_pay1" id="other_pay1" value="0.0"
											class="easyui-numberbox" data-options="required:true" /></td>
									</tr>
									<tr>
										<td>其他2</td>
										<td><input size="10" min="0" prefix="¥" rows="10" cols="20"
											name="other_pay2" id="other_pay2" value="0.0"
											class="easyui-numberbox" data-options="required:true" /></td>
									</tr>
									<tr>
										<td>实发工资</td>
										<td><input size="10" rows="13" cols="20" name="sum_pay"
											id="sum_pay" value="自动计算" disabled="true"
											data-options="required:true" /></td>
									</tr>
									<tr>
										<td>发放状态</td>
										<td><input size="10" rows="10" cols="20"
											name="pay_status" id="pay_status" value="未发放"
											readonly="readonly" type="text" data-options="required:true" /></td>
									</tr>
								</table>
							</form>
							<div id="dlg-button">
								<a href="javascript:savasalary()" class="easyui-linkbutton"
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
	</div>
</body>
</html>









