<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>新增学员信息</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<script type="text/javascript" src="<%=basePath %>/static/ielts/teacher/certificateadd.js"></script>
</head>
	<body style="padding:10px;">
		<div class="alertFrom">
			<div class="layui-form">

				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">教师姓名:</label>
						<div class="layui-input-inline">
							<label class="layui-form-label" style="" id="add_teacher_name" ></label>
						</div>
					</div>
					
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">邮箱:</label>
						<div class="layui-input-inline">
							<label class="layui-form-label" style="" id="add_email" ></label>
						</div>
					</div>
				</div>

				 <div class="layui-form-item">
   			    	<div class="layui-inline" style="margin: 0 auto 10px auto; width: 100%;">
						<label class="layui-form-label" style="width: 100px;">教师资格证:</label>
						<div class="layui-input-block" style="width: 80%">
							<c:if test="${flag }">
								<c:if test="${ieltsTeacherCertificate.is_teacher_certificate eq 0 }">
									<input name="radio_is_certificate" type="radio"  value="1"  title="有">
									<input name="radio_is_certificate" checked="checked" type="radio" value="0"  title="无">
								</c:if>
								<c:if test="${ieltsTeacherCertificate.is_teacher_certificate eq 1 }">
									<input name="radio_is_certificate" checked="checked" type="radio"  value="1"  title="有">
									<input name="radio_is_certificate" type="radio" value="0"  title="无">
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
   			    	<div class="layui-inline" style="margin: 0 auto 10px auto; width: 100%;">
						<label class="layui-form-label" style="width: 100px;">celta证书:</label>
						<div class="layui-input-block" style="width: 80%">
							<c:if test="${flag }">
								<c:if test="${ieltsTeacherCertificate.is_celta eq 0 }">
									<input name="radio_is_celta" type="radio"  value="1"  title="有">
									<input name="radio_is_celta" checked="checked" type="radio" value="0"  title="无">
								</c:if>
								<c:if test="${ieltsTeacherCertificate.is_celta eq 1 }">
									<input name="radio_is_celta" checked="checked" type="radio"  value="1"  title="有">
									<input name="radio_is_celta" type="radio" value="0"  title="无">
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button id="certificate_add" class="layui-btn">立即提交</button>
					</div>
				</div>
			</div>
		</div>
		<!-- layui.use -->
	</body>
</html>