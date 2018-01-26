<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<style>
#navli{
 border-bottom: 1px solid #64dcdc;
}
</style>
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<script type="text/javascript" src="<%=basePath %>/static/studentpc/js/index.js"></script>
<link rel="stylesheet" href="<%=basePath %>/static/studentpc/css/layui-mycolor.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header my-bg-color" style="font-weight: bold; border-bottom: 1px solid #64dcdc;">
    <div class="layui-logo" style="width: 400px;font-size: 25px;">
    	<p class="my-logos" style="margin-left: 4%;width: 60px;font-size: 22px;border-radius:4px;">SPT</p>
    	<p class="logos" style="width:280px;    font-weight: 700;    color: #606060;font-size: 20px;">英语口语测评系统</p>
	</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-right my-font-color">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="<%=basePath %>/static/studentpc/images/8_03.png" class="layui-nav-img">
          <c:if test="${studentUser ne null && studentUser.name ne null && studentUser.name ne '' }">
          	${studentUser.name }
          </c:if>
          <c:if test="${studentUser == null  }">
          	请完善姓名
          </c:if>
        </a>
        <dl class="layui-nav-child">
          <dd><a href="<%=basePath %>/studentpc/studentinfo/changepasswordview.html">修改密码</a></dd>
          <dd><a href="javascript:void(0);" onclick="bindingout();">解除绑定</a></dd>
          <dd><a href="javascript:void(0);" onclick="loginout();">退出</a></dd>
        </dl>
      </li>
      <!-- <li class="layui-nav-item"><a href="javascript:void(0);" onclick="loginout();">退出</a></li> -->
    </ul>
  </div>
  
  <div class="layui-side" id="larry-side" style="box-shadow: 3px 0px 5px #dedddd;">
    <div class="layui-side-scroll" id="larry-nav-side" lay-filter="side">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree">
        <li class="layui-nav-item">
          <a href="javascript:;"><i class="fa  fa-bars" data-icon="fa-bars"></i><span style="margin-left: 5px;">我的班级</span></a>
          <dl class="layui-nav-child">
            <dd><a class="tab_click" href="javascript:;" data-url="<%=basePath %>/studentpc/studentclass/studentclassview.html"><i class="fa fa-sitemap" data-icon="fa-sitemap"></i><span style="margin-left: 5px; color: #64dcdc;">我的班级</span></a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;"><i class="fa  fa-bars" data-icon="fa-bars"></i><span style="margin-left: 5px;">我的作业</span></a>
          <dl class="layui-nav-child">
            <dd><a class="tab_click" href="javascript:;" data-url="<%=basePath %>/studentpc/studentzuoye/studentzuoyeview.html"><i class="fa fa-sticky-note-o" data-icon="fa-sticky-note-o"></i><span style="margin-left: 5px; color: #64dcdc;">我的作业</span></a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;"><i class="fa  fa-bars" data-icon="fa-bars"></i><span style="margin-left: 5px;">模拟考试</span></a>
          <dl class="layui-nav-child">
            <dd><a class="tab_click" href="javascript:;" data-url="<%=basePath %>/studentpc/studenttest/studenttestview.html"><i class="fa fa-sticky-note-o" data-icon="fa-sticky-note-o"></i><span style="margin-left: 5px; color: #64dcdc;">我的考试</span></a></dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body" id="larry-body" style="padding-left: 10px; margin: 0px; height: 100%;">
    <!-- 内容主体区域 -->
	<div style="margin: 6px 0px;">
		<div class="layui-tab layui-tab-card larry-tab-box" id="larry-tab" lay-filter="demo" lay-allowclose="true" style="margin: 0; background:#EFF2F9;">
		  <ul class="layui-tab-title" style="border-bottom: 1px solid #64dcdc;margin-left: 27px;margin-top: 5px;background-color: #fff;">
		  	<li id="navli" class="layui-this admin-home" lay-id="0"><i class="fa fa-home layui-icon" data-id='0' style="display: none;"></i><em>我的班级</em></li>
		  </ul>
		  <div class="layui-tab-content" style="min-height: 150px; margin: 0px; padding: 0px">
		 	<div class="layui-tab-item layui-show">
				<iframe class="larry-iframe" src="<%=basePath %>/studentpc/studentclass/studentclassview.html"></iframe>
			</div>
		  </div>
		</div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/studentpc/js/larry.js"></script>
</html>