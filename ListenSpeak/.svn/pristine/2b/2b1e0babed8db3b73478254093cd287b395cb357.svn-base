<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>SPT英语口语测评系统</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
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
				<a class="logo" href="http://hf.xdf.cn" title="logo" style="left:-5px"><p style="height:30px; line-height: 30px; border: 2px solid #64dcdc;  border-radius:5px; color: #64dcdc;font-size:25px;width: 50px;text-align: center;box-sizing: border-box;display:inline-block" >SPT</p><span style="font-size: 15px;color: #444;vertical-align:text-bottom;margin-left:7px;">英语口语测评系统</span></a>
				<div class="larry-side-menu">
					<i class="fa fa-exchange" aria-hidden="true"></i>
				</div>
			</div>
            <!-- 右侧导航 -->
            <ul class="layui-nav larry-header-item">
					<li class="layui-nav-item first">
						<a href="javascript:;">
							<img src="<%=basePath %>/static/framework/images/userimg.jpg" class="userimg">			
							<cite>${user.name }</cite>
							<span class="layui-nav-more"></span>
						</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:modifyPassword();"> <i class="iconfont icon-iconfuzhi01" aria-hidden="true"></i></i>修改密码</a>
								</dd>
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
				<li class="layui-this admin-home" lay-id="0"><i data-id='0'  class="fa fa-home layui-icon"></i><em>我的班级</em></li>
			</ul>
			<div class="layui-tab-content" style="min-height: 150px; ">
				<div class="layui-tab-item layui-show">
					<iframe class="larry-iframe" src="<%=basePath %>/lstclass/list.html"></iframe>
				</div>
			</div>
		</div>
	</div>
	<!-- 底部区域 -->
	<div class="layui-footer layui-larry-foot" id="larry-footer">
		<div class="layui-mian">
		    <div class="larry-footer-left">
		    	<%-- 合肥新东方学校_信息管理部 微信群：
		    	<a href=""><img border="0" src="<%=basePath %>/static/framework/images/group.png" title="信息管理部官方交流群"></a>
		    	 查看:<a href="" title="">作者信息</a> --%>
		    </div>
		    <p class="p-admin">
		    	<!-- <span>2016 &copy;</span>
		    	Written by hfxdf,<a href="">信息管理部</a>. 版权所有 -->
		    </p>
		</div>
	</div>
</div>
</body>
<!-- 加载js文件-->
	<script type="text/javascript" src="<%=basePath %>/static/framework/js/larry.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/framework/js/main.js"></script>
</html>