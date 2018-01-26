<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<script type="text/javascript" src="<%=basePath %>/static/rank/jscolor/jscolor.js"></script>
</head>
	<body style="padding:10px;">
		<div class="alertFrom">
			<div class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">规则名称:<span style="color:red;">*</span></label>
					<div class="layui-input-inline">
						<input name="rank_name" id="rank_name" style="width: 160px;" type="text" placeholder="请输入规则名称" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">部门名称:<span style="color:red;">*</span></label>
					<div class="layui-input-inline">
						<input name="dept_name" id="dept_name" style="width: 160px;" type="text" placeholder="请输入部门名称" class="layui-input">
					</div>
				</div>
				
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">显示条数:<span style="color:red;">*</span></label>
					<div class="layui-input-inline">
						<input name="ranke_num" id="ranke_num" style="width: 160px;" type="text" placeholder="请输入显示条数" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">上榜班级剩余人数：<span style="color:red;">*</span></label>
				    <div class="layui-input-inline">
				      <input name="ranke_lastnum" id="ranke_lastnum" style="width: 160px;" type="text" placeholder="请输入剩余人数" class="layui-input">
				    </div>
   			    </div>
   			    
   			    <div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">刷新时间:<span style="color:red;">*</span></label>
					<div class="layui-input-inline">
						<input name="refresh_time" id="refresh_time" style="width: 160px; float:left;" type="text" placeholder="请输入刷新时间" class="layui-input">
						<span style="line-height: 38px;">秒</span>
					</div>
				</div>
   			    
   			    <div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">跳转按钮名称：</label>
				    <div class="layui-input-inline">
				      <input name="link_name" id="link_name" style="width: 160px;" type="text" placeholder="请输入跳转按钮名称" class="layui-input">
				    </div>
   			    </div>
				
				<div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">跳转按钮url：</label>
				    <div class="layui-input-inline">
				      <input name="link_access" id="link_access" style="width: 160px;" type="text" placeholder="请输入跳转按钮url" class="layui-input">
				    </div>
   			    </div>
   			    
				<div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">页面颜色css码：</label>
				    <div class="layui-input-inline">
				      <input name="b_color_code" id="b_color_code" value="000000" class="color" style="width: 160px;" type="text" placeholder="请输入页面颜色css码" class="layui-input">
				    </div>
   			    </div>
   			    
   			    <div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">字体颜色css码：</label>
				    <div class="layui-input-inline">
				      <input name="font_color" id="font_color" class="color" style="width: 160px;" type="text" placeholder="请输入字体颜色css码" class="layui-input">
				    </div>
   			    </div>
   			    
   			    <div class="layui-form-item site-demo-upload">
   			    	<label class="layui-form-label">头部图片:</label>
   			    	<div class="layui-input-inline" style="width:260px;">
				  		<img style="width: 260px; height: 180px;" id="head_file_img" src="">
					  	<div class="site-demo-upbar" style="position: absolute; top: 40%; left: 20%;">
					    	<input name="fileA" lay-type="file" lay-title="选择图片" class="layui-upload-file" id="head_img" type="file">
					  	</div>
				  	</div>
				</div>
				
				<div class="layui-form-item site-demo-upload">
   			    	<label class="layui-form-label">底部图片:</label>
   			    	<div class="layui-input-inline" style="width:260px;">
				  		<img style="width: 260px; height: 180px;" id="foot_file_img" src="">
					  	<div class="site-demo-upbar" style="position: absolute; top: 40%; left: 20%;">
					    	<input name="fileB" lay-type="file" lay-title="选择图片" class="layui-upload-file" id="foot_img" type="file">
					  	</div>
				  	</div>
				</div>
   			    
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button id="mysubmit" class="layui-btn">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</div>
		</div>
		<!-- layui.use -->
		<script type="text/javascript" src="<%=basePath %>/static/rank/addrankinfo.js"></script>
	</body>
</html>