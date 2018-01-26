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
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	</head>
	<body style="padding:20px;">
			<form class="layui-form" id="addForm">
				<div class="layui-text" style="width: 85%;">
		             <table class="layui-table" lay-skin="nob" style="margin-left: 10%;">
		                <colgroup>
		                	<col width="100">
		                	<col width="200">
		                </colgroup>
		                <tbody>
		                	<tr><td>老师姓名:</td><td>${param.teacherName }</td></tr>
		                	<tr><td>上周时间:</td><td>${teacher.szSj }小时</td></tr>
		                	<tr><td>上月时间:</td><td>${teacher.sySj }小时</td></tr>
		                	<tr><td>下月时间:</td><td>${teacher.xySj }小时</td></tr>
		                	<tr><td>财年进度:</td>
		                		<td>
		                			<div style="margin-top:10px;width:80%;" class="layui-progress">
										<div class="layui-progress-bar" lay-percent="${teacher.cnSj*100/param.cnHours}%"
							 			style="width: ${teacher.cnSj*100/param.cnHours}%;">
							 			<span class="layui-progress-text">${teacher.cnSj }/${param.cnHours }</span>
							 			</div>
							 		</div>
		                		</td>
		                	</tr>
		                </tbody>
		                </table>
              </div>
			</form>
		<script type="text/javascript">
		layui.use(['form','element'], function(){
			var form = layui.form();
		});	
		</script>
	</body>
</html>