<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404展示</title>
<link rel="icon" type="image/png" href="assets/i/hgy.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/admin.css">
<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
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
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">404</strong> / <small>That’s
							an error</small>
					</div>
				</div>

				<hr>

				<div class="am-g">
					<div class="am-u-sm-12">
						<h2 class="am-text-center am-text-xxxl am-margin-top-lg">404.
							Not Found</h2>
						<p class="am-text-center">没有找到你要的页面</p>
						<pre class="page-404">
          .----.
       _.'__    `.
   .--($)($$)---/#\
 .' @          /###\
 :         ,   #####
  `-..__.-' _.-\###/
        `;_:    `"'
      .'"""""`.
     /,  ya ,\\
    //  404!  \\
    `-._______.-'
    ___`. | .'___
   (______|______)
        </pre>
					</div>
				</div>
			</div>

			<footer class="admin-content-footer">
			<hr>
			<p class="am-padding-left" style="text-align: center">Copyright ©
				2017 www.hneeu.edu.cn. All Rights Reserved. By YZX 技术支持：杨振欣</p>
			</footer>
		</div>
	</div>
	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="assets/js/jquery.min.js"></script>
	<!--<![endif]-->
	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/app.js"></script>
</body>
</html>