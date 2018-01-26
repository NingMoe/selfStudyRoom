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
	<body>
		<div class="alertFrom">
				<input type="hidden" name="id" value="${qform.id}">
				<div class="layui-form-item">
				<label class="layui-form-label">标题:</label>
					<div class="layui-input-block">
						<label class="layui-form-label" style="text-align: left;font-weight: bold;">${qform.title}</label>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">简介:</label>
					<div class="layui-input-block">
						<label class="layui-form-label" style="text-align: left;font-weight: bold;">${qform.desc}</label>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">代码详情:</label>
					<div class="layui-input-block">
						<textarea placeholder="" class="layui-textarea" readonly="readonly" style="height: 400px;"><!-- 美工用form代码样例，可以按需要更改如添加class等，但必须保留name、action等属性 -->
<c:if test="${not empty qform.title}"><h3>${qform.title}</h3><!-- 标题 --></c:if>
<c:if test="${not empty qform.desc}"><h4>${qform.desc}</h4><!-- 简介 --></c:if>
<form>
    <input type="hidden" id="sm_id" name="id" value="${qform.id}" />
    <c:forEach var="pb" items="${pbList}"><span>${pb.name}</span><input type="text " id ="sm_${pb.key}" name="${pb.key}" />
    </c:forEach><div id="subBtn" class="login_btn" >提交</div>
</form>
<!--表单数据提交开始-->
<script type="text/javascript" src="http://images.xdf.cn/v4/js/jquery.js"></script>
<script type="text/javascript">
	$('#subBtn').click(function(){
		window.location.href ='${strBackUrl}+'&token='+'t_'+new Date().getTime();
	})</script>
						</textarea>
					</div>
				</div>
		</div>
		<script>
		</script>
	</body>
</html>