<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>Smart Work 改变你的工作方式</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">
	
	 <%@include file="/WEB-INF/view/common/taglib.jsp" %>	
	<!-- load css -->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/common/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/framework/css/adminstyle.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin" id="layui_layout">
	<!-- 顶部区域 -->
	<div class="layui-header header header-demo">
		<div class="layui-main">
		    <!-- logo区域 -->
			<div class="admin-logo-box">
				<a class="logo" href="http://hf.xdf.cn" title="logo"><img src="<%=basePath %>/static/common/images/logo.png" alt=""></a>
				<div class="larry-side-menu">
					<i class="fa fa-exchange" aria-hidden="true"></i>
				</div>
			</div>
            <!-- 顶级菜单区域 -->
            <!-- <div class="layui-larry-menu">
                 <ul class="layui-nav clearfix">
                       <li class="layui-nav-item layui-this">
                 	   	   <a href="javascirpt:;"><i class="iconfont icon-wangzhanguanli"></i>内容管理</a>
                 	   </li>
                 	   <li class="layui-nav-item">
                 	   	   <a href="javascirpt:;"><i class="iconfont icon-weixin3"></i>微信公众</a>
                 	   </li>
                 	   <li class="layui-nav-item">
                 	   	   <a href="javascirpt:;"><i class="iconfont icon-ht_expand"></i>扩展模块</a>
                 	   </li>
                 </ul>
            </div> -->
            <!-- 右侧导航 -->
            <ul class="layui-nav larry-header-item">
					<div class="layui-nav-item" style="width: 300px;text-align: center;">
					<sec:authorize ifAnyGranted='ROLE_quick_search_seeker'> <form action="" method="post" autocomplete="off">
					<input type="text" id="mainSerach" autocomplete="off" placeholder="姓名/手机号" class="layui-input" style="width: 60%; display:inline;">
					<button id="mainSerach_btn" type="button" class="layui-btn" style="margin-top: -4px;">
							<li class="fa fa-search"></li> &nbsp;搜索
						</button>
						</div>
						</form></sec:authorize>
					<li class="layui-nav-item first">
						<a href="javascript:;">
							<img src="<%=basePath %>/static/framework/images/userimg.jpg" class="userimg">			
							<cite>${user.name }</cite>
							<span class="layui-nav-more"></span>
						</a>
							<dl class="layui-nav-child">
								<!-- <dd>
									<a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> 个人信息</a>
								</dd> -->
								<dd>
									<a href="javascript:modifyPassword();"> <i class="iconfont icon-iconfuzhi01" aria-hidden="true"></i></i>修改密码</a>
								</dd>
								<!-- <dd id="lock">
									<a href="javascript:;">
										<i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i> 锁屏 (Alt+L)
									</a>
								</dd> -->
								<dd>
									<a href="<%=basePath %>/manager/login.html""><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
								</dd>
							</dl>
					</li>
            </ul>
		</div>
	</div>
	<!-- 左侧侧边导航开始 -->
	<div class="layui-side layui-side-bg layui-larry-side" id="larry-side">
        <div class="layui-side-scroll" id="larry-nav-side" lay-filter="side">
		<%-- <div class="user-photo">
			<a class="img" title="我的头像" >
				<img src="<%=basePath %>/static/framework/images/user.jpg" class="userimg1"></a>
			<p>你好！${user.name }, 欢迎登录</p>
		</div> --%>
		<!-- 左侧菜单 -->
		<ul class="layui-nav layui-nav-tree">
					<c:forEach var="men" items="${menus}">
						<li class="layui-nav-item">
						<c:if test="${!empty men.href}">
							<a href="javascript:;"  data-url="<%=basePath %>${men.href}"> 
						</c:if>
						<c:if test="${empty men.href}">
							<a href="javascript:;"  > 
						</c:if>
							<c:if test="${!empty men.icon}">
								<i class="fa  ${men.icon}" data-icon='${men.icon}'></i> 
							</c:if>
							<c:if test="${empty men.icon}">
								<i class="fa  fa-th-list" data-icon='fa-th-list'></i> 
							</c:if>
							<span>${men.title}</span>
						</a>
						<c:if test="${!empty men.menu}">
						<dl class="layui-nav-child">
						<c:forEach  var="secMenu" items="${men.menu}">
							<dd>
							<c:if test="${!empty secMenu.href}">
							<a href="javascript:;"  data-url="<%=basePath %>${secMenu.href}"> 
						</c:if>
						<c:if test="${empty secMenu.href}">
							<a href="javascript:;"  > 
						</c:if>
                        <c:if test="${!empty secMenu.icon}">
								<i class="fa  ${secMenu.icon}" data-icon='${secMenu.icon}'></i> 
							</c:if>
							 <c:if test="${empty secMenu.icon}">
								<i class="fa  fa-sticky-note-o" data-icon='fa-sticky-note-o'></i> 
							</c:if>
                            <span>${secMenu.text}</span>
                        </a>
                    </dd>
						</c:forEach>
						</dl>
						</c:if>
						</li>
					</c:forEach>
		</ul>
	    </div>
	</div>

	<!-- 左侧侧边导航结束 -->
	<!-- 右侧主体内容 -->
	<div class="layui-body" id="larry-body" style="bottom: 0;border-left: solid 2px #1AA094;">
		<div class="layui-tab layui-tab-card larry-tab-box" id="larry-tab" lay-filter="demo" lay-allowclose="true">
			<ul class="layui-tab-title">
				<li class="layui-this" id="admin-home"><i class="fa fa-home"></i><em>后台首页</em></li>
			</ul>
			<div class="layui-tab-content" style="min-height: 150px; ">
				<div class="layui-tab-item layui-show">
					<iframe class="larry-iframe" data-id='0' src="<%=basePath %>/home/home.html"></iframe>
				</div>
			</div>
		</div>

        
	</div>
	<!-- 底部区域 -->
	<div class="layui-footer layui-larry-foot" id="larry-footer">
		<div class="layui-mian">
		    <div class="larry-footer-left">
		    	合肥新东方学校_信息管理部 微信群：
		    	<a href=""><img border="0" src="<%=basePath %>/static/framework/images/group.png" title="信息管理部官方交流群"></a>
		    	 查看:<a href="" title="">作者信息</a>
		    </div>
		    <p class="p-admin">
		    	<span>2016 &copy;</span>
		    	Written by hfxdf,<a href="">信息管理部</a>. 版权所有
		    	<!-- 前端框架layui，下载地址：www.layui.com -->
		    </p>
		</div>
	</div>
</div>
<!-- 加载js文件-->
	<script type="text/javascript" src="<%=basePath %>/static/framework/js/larry.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/framework/js/main.js"></script>
<!-- 锁屏 -->
<div class="lock-screen" style="display: none;">
	<div id="locker" class="lock-wrapper">
		<div id="time"></div>
		<div class="lock-box center">
			<img src="<%=basePath %>/static/framework/images/userimg.jpg" alt="">
			<h1>admin</h1>
			<duv class="form-group col-lg-12">
				<input type="password" placeholder='锁屏状态，请输入密码解锁' id="lock_password" class="form-control lock-input" autofocus name="lock_password">
				<button id="unlock" class="btn btn-lock">解锁</button>
			</duv>
		</div>
	</div>
</div>
<!-- 菜单控件 -->
<!-- <div class="larry-tab-menu">
	<span class="layui-btn larry-test">刷新</span>
</div> -->
<!-- iframe框架刷新操作 -->
<!-- <div id="refresh_iframe" class="layui-btn refresh_iframe">刷新</div> -->
</body>
<script type="text/javascript">
	$("#mainSerach_btn").click(function() {
		var text = $.trim($("#mainSerach").val());
		if (text == "") {
			layer.tips('对不起，快捷搜索必须输入搜索条件!', '#mainSerach', {tips : [ 3, '#FF6838' ]});
			return;
		}
		var reg=/^1\d{10}$/;
		var tel="";
		var name="";
		if(reg.test(text)){
			//符合手机号规则
			tel=text;
		} else{
			//按照姓名规则
			name=text;
		}
		var data = {
				href: jsBasePath+"/recruit/acceptance/index.html?tel="+tel+"&name="+name,
				icon: 'fa-street-view',
				title: "应聘者查找("+text+")"
				};
		navtab.tabAdd(data);
		//$("#mainSerach").val("");
		/* $("#mainSerach_btn").parent().blur();*/
		//$("#mainSerach_btn").parent().parent().hide();
	});
	$("#mainSerach").off("keydown");
	$("#mainSerach").bind("keydown",function(event){
		if(event.keyCode == "13") { 
			$("#mainSerach_btn").click();
			return false;
		}
	}); 
</script>
</html>