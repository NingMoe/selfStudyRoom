<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="height: 100%;">
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<link rel="stylesheet" href="<%=basePath %>/static/studentpc/css/less.css">
</head>
<body style="height: 100%; background:#EFF2F9;">
<div class="public-mainbox">
		<c:if test="${studentClass ne null && studentClass.flag }">
			<c:forEach items="${studentClass.list}" var="clist">
				<div class="pubmain-one" style="border-radius:4px;width:30%">
					<div class="pubmain-top">
						<img src="<%=basePath %>/static/studentpc/images/8_21.png" alt="">
						<p>${clist.className }</p>
						<div class="clearfix"></div>
					</div>
					<div class="pubmain-main">
						<div class="pubmain-class">
							<img src="<%=basePath %>/static/studentpc/images/8_24.png" alt="">
							<p>${clist.grade }</p>
						</div>
						<div class="pubmain-teacher">
							<img src="<%=basePath %>/static/studentpc/images/8_27.png" alt="">
							<p>${clist.teacher_name }</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="pubmain-bottom">
						<img src="<%=basePath %>/static/studentpc/images/8_35.png" alt="">
						<p>结课时间：<fmt:formatDate value="${clist.validTime }" pattern="yyyy年MM月dd日"/></p>
						<div class="clearfix"></div>
					</div>
				</div>
			</c:forEach>
		</c:if>


		<div class="clearfix"></div>
	</div>
		<div class="pubmain-one" style="width:29.8%;margin-left:0.26rem;border-radius:2px;">
			<div class="pubmain-add" id="addClassView" style="cursor:pointer">
				<p>+申请加入新的班级</p>
				<div class="clearfix"></div>
			</div>
		</div>	
	<!-- 弹出框开始 -->
	<!-- 遮罩层 -->
	<div id="mask_dialog" style="display: none;" class="public-shadow"></div>
	<!-- 添加班级弹出 -->
	<div id="add_class_dialog" style="display: none;" class="class-add">
		<p onclick="hide_add_class_dialog();" style="position: absolute; top: 28%;right: 3%; z-index: 10000;color: #ffffff; cursor:pointer;">X</p>
		<img class="class-img" src="<%=basePath %>/static/studentpc/images/9_03.png" alt="">
		<div class="add-table">
			<div class="add-num">
				<img src="<%=basePath %>/static/studentpc/images/4_07.png" alt="">
				<input type="text" id="phone" placeholder="请输入手机号码">
				<div class="clearfix"></div>
			</div>
			<div class="add-code">
				<img src="<%=basePath %>/static/studentpc/images/9_04_03.png" alt="">
				<input type="text" id="invitation_code" placeholder="请输入邀请码">
				<div class="clearfix"></div>
			</div>
			<div class="add-button">
				<input type="button" id="addClassBtn" value="申请加入">
			</div>
		</div>
	</div>
	<!-- 添加班级完成弹出 -->
	<div id="add_class_end_dialog" style="display: none;" class="class-add">
		<p onclick="hide_add_class_dialog();" style="position: absolute; top: 28%;right: 3%; z-index: 10000;color: #ffffff; cursor:pointer;">X</p>
		<img class="class-img" src="<%=basePath %>/static/studentpc/images/10_03.png" alt="">
		<div class="add-table">
			<div class="add-p">
				<p>您的加入班级申请已经发出，请等待授课老师验证通过，等待的过程中您也可以进入模拟考试进行测试</p>
			</div>
			<div class="add-button2">
				<input type="button" id="addClassEndBtn" value="确定">
			</div>
		</div>
	</div>
	<!-- 弹出框结束 -->
</body>
<script type="text/javascript" src="<%=basePath %>/static/studentpc/js/studentclass.js"></script>
</html>