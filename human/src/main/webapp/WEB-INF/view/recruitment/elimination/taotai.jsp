<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
	    
<style type="text/css">
</style>
<body style="padding:20px;height:360px;position:relative;">
<form class="layui-form">
	<input type="hidden" name="flowId" id="flowId" value="${flowId }">
	<input type="hidden" name="resumeId" id="resumeId" value="${resumeId }">
	<input type="hidden" name="flowCode" id="flowCode" value="${flowCode }">
	<div class="layui-form-item layui-form-text" style="margin-top:20px;">
		<label class="layui-form-label" style="width:100px;">淘汰原因</label>
		<div class="layui-input-inline" style="width:300px;">
			<textarea placeholder="请输入淘汰原因(非必填)" id="remark" name="remark" class="layui-textarea"></textarea>
		</div>
    </div>
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:100px;"></label>
		<div class="layui-input-inline" style="width:300px;">
		     <input type="checkbox" id="rencai" name="rencai" lay-skin="primary" value="1" title="加入人才库" lay-filter="rencai">
		</div>
	</div>   
	<div class="layui-form-item rencai" style="display:none;">
		<label class="layui-form-label" style="width:100px;">部门</label>
		<div class="layui-input-inline" style="width:300px;">
			<select name="dept" id="dept" lay-filter="dept" >
				<option value="">请选择</option>
				<c:forEach items="${orgs }" var="org">
					<option value="${org.id }">${org.name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
			
	<div class="layui-form-item rencai" style="display:none;">
		<label class="layui-form-label" style="width:100px;">职位</label>
		<div class="layui-input-inline" style="width:300px;">
			<select name="position" id="position" lay-filter="position">
				<option value="">请选择</option>
			</select>
		</div>
	</div>
	
	<div class="layui-form-item" style="position:absolute;left:60px;bottom:0">
		<div class="layui-input-block" >
			<button type="button" id="tj" class="layui-btn">确定</button>
			<button type="button" id="qx" class="layui-btn">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/elimination/taotai.js"></script>
</body>
</html>