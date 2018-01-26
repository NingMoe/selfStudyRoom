<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
</head>
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
			<div class="layui-form-item">
			<label class="layui-form-label">学号:</label>
				<div class="layui-input-inline">
					<input type="text" id="code" name="code" value="${cc.code}"  readonly="readonly"
						class="layui-input">
				</div>
			<label class="layui-form-label">姓名:</label>
				<div class="layui-input-inline">
					<input type="text" id="name" name="name" value="${cc.name}"  readonly="readonly"
						class="layui-input">
				</div>
			</div>	
			<div class="layui-form-item" style=" text-align: center;font-size: 24px;">
			<label class="">预备阶段评语</label>
			</div>
			<div class="layui-form-item pre">
				<label class="layui-form-label">听力口语:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="pretkComments" id="pretkComments"  value="${cc.pretkComments}"    lay-verify="maxlength"
						placeholder="" class="layui-textarea">${cc.pretkComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item pre">
				<label class="layui-form-label">语法词汇:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="preyfComments" id="preyfComments" lay-verify="remark" value="${cc.preyfComments}" 
						placeholder="" class="layui-textarea">${cc.preyfComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  pre">
				<label class="layui-form-label">阅读写作:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="predComments" id="predComments" lay-verify="remark" value="${cc.predComments}" 
						placeholder="" class="layui-textarea">${cc.predComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item" style=" text-align: center;font-size: 24px;">
			<label class="">基础阶段评语</label>
			</div>	
			<div class="layui-form-item  bas">
				<label class="layui-form-label">listening:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name=baslComments id="baslComments" lay-verify="remark"   value="${cc.baslComments}" 
						placeholder="" class="layui-textarea">${cc.baslComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  bas">
				<label class="layui-form-label">speaking:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="bassComments" id="bassComments" lay-verify="remark" value="${cc.bassComments}" 
						placeholder="" class="layui-textarea">${cc.bassComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  bas">
				<label class="layui-form-label">reading:</label>
				<div class="layui-input-inline" style="width: 67% !important;"> 
					<textarea name="basrComments" id="basrComments" lay-verify="remark" value="${cc.basrComments}" 
						placeholder="" class="layui-textarea">${cc.basrComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  bas">
				<label class="layui-form-label">writing:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="baswComments" id="baswComments" lay-verify="remark" value="${cc.baswComments}" 
						placeholder="" class="layui-textarea">${cc.baswComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item" style=" text-align: center;font-size: 24px;">
			<label class="">强化阶段评语</label>
			</div>		
			<div class="layui-form-item  str">
				<label class="layui-form-label">listening:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name=strlComments id="strlComments" lay-verify="remark" value="${cc.strlComments}" 
						placeholder="" class="layui-textarea">${cc.strlComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  str">
				<label class="layui-form-label">speaking:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="strsComments" id="strsComments" lay-verify="remark" value="${cc.strsComments}" 
						placeholder="" class="layui-textarea">${cc.strsComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  str">
				<label class="layui-form-label">reading:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="strrComments" id="strrComments" lay-verify="remark" value="${cc.strrComments}" 
						placeholder="" class="layui-textarea">${cc.strrComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  str">
				<label class="layui-form-label">writing:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="strwComments" id="strwComments" lay-verify="remark" value="${cc.strwComments}" 
						placeholder="" class="layui-textarea">${cc.strwComments}</textarea>
				</div>
			</div>	
			<div class="layui-form-item" style=" text-align: center;font-size: 24px;">
			<label class="">冲刺阶段评语</label>
			</div>	
			<div class="layui-form-item  das">
				<label class="layui-form-label">listening:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name=daslComments id="daslComments" lay-verify="remark" value="${cc.daslComments}" 
						placeholder="" class="layui-textarea">${cc.daslComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  das">
				<label class="layui-form-label">speaking:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="dassComments" id="dassComments" lay-verify="remark" value="${cc.dassComments}" 
						placeholder="" class="layui-textarea">${cc.dassComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  das">
				<label class="layui-form-label">reading:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="dasrComments" id="dasrComments" lay-verify="remark" value="${cc.dasrComments}" 
						placeholder="" class="layui-textarea">${cc.dasrComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item  das">
				<label class="layui-form-label">writing:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="daswComments" id="daswComments" lay-verify="remark"  value="${cc.daswComments}" 
						placeholder="" class="layui-textarea">${cc.daswComments}</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button  class="layui-btn"  onclick="return btnsumbit();" lay-filter="go"  lay-submit="">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/northamerica/examineelist/comments_edit.js"></script>
</html>