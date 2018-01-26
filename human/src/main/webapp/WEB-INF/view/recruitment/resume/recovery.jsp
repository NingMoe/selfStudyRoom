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
<body style="padding:20px;height:400px;position:relative;">
<form class="layui-form" id="addForm" action="" method="post">
   <input type="hidden" name="resumeId" id="resumeId" value="${resumeId}">  
   <div class="layui-form-item">
		<label class="layui-form-label" style="width:100px;">回收类型</label>
		<div class="layui-input-inline" style="width:400px;">
			<input type="radio" name="recoveryType" title="直接淘汰" lay-filter="recoveryType" value="1">
			<input type="radio" name="recoveryType" title="更换面试岗位" lay-filter="recoveryType" value="2">
			<input type="radio" name="recoveryType" title="放入人才库" lay-filter="recoveryType" value="3">
		</div>
	</div>
	
	<div class="layui-form-item layui-form-text eliminate" style="display:none;margin-top:20px;">
		<label class="layui-form-label" style="width:100px;">淘汰原因</label>
		<div class="layui-input-inline" style="width:300px;">
			<textarea placeholder="请输入淘汰原因(必填)" id="remark" name="remark" class="layui-textarea"></textarea>
		</div>
    </div>
	
	<div class="layui-form-item changePosition" style="display:none;">
		<label class="layui-form-label" style="width:100px;">面试部门</label>
		<div class="layui-input-inline" style="width:300px;">
			<select name="dept" id="dept"  lay-filter="dept" >
				<option value="">请选择</option>
				<c:forEach items="${orgList }" var="org">
	    			<option value="${org.id }">${org.name }</option>
	    		</c:forEach>
			</select>
		</div>
	</div>			
	<div class="layui-form-item changePosition" style="display:none;">
		<label class="layui-form-label" style="width:100px;">面试职位</label>
		<div class="layui-input-inline" style="width:300px;">
			<select name="positionProcessId"  id="positionProcessId">
				<option value="">请选择</option>
			</select>
		</div>
	</div>
	
	<div class="layui-form-item changePosition" style="display:none;">
		<label class="layui-form-label" style="width:100px;">面试时间</label>
		<div class="layui-input-inline">
			<input class="layui-input" placeholder="开始日期"  id="interviewTime" name="interviewTime">
		</div>
	</div>
	
	<div class="layui-form-item changePosition" style="display:none;">
		<label class="layui-form-label" style="width:100px;">面试地点</label>
		<div class="layui-input-inline">
			<input class="layui-input" placeholder="面试地点"  id="interviewLocation" name="interviewLocation">
		</div>
	</div>
	
<!-- 	<div class="layui-form-item changePosition" style="display:none;"> -->
<!-- 		<label class="layui-form-label" style="width:100px;"></label> -->
<!-- 		<div class="layui-input-inline"> -->
<!-- 			<input type="checkbox" id="isNotice" name="isNotice" lay-skin="primary" value="1" title="短信通知应聘者"> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
	
	<div class="layui-form-item layui-form-text rencai" style="display:none;margin-top:20px;">
		<label class="layui-form-label" style="width:100px;">淘汰原因</label>
		<div class="layui-input-inline" style="width:300px;">
			<textarea placeholder="请输入淘汰原因(非必填)" id="rencaiRemark" name="rencaiRemark" class="layui-textarea"></textarea>
		</div>
    </div>
	
	<div class="layui-form-item rencai" style="display:none;">
		<label class="layui-form-label" style="width:100px;">部门</label>
		<div class="layui-input-inline" style="width:300px;">
			<select name="rencaiDept" id="rencaiDept"  lay-filter="rencaiDept" >
				<option value="">请选择</option>
				<c:forEach items="${orgList }" var="org">
	    			<option value="${org.id }">${org.name }</option>
	    		</c:forEach>
			</select>
		</div>
	</div>			
	<div class="layui-form-item rencai" style="display:none;">
		<label class="layui-form-label" style="width:100px;">职位</label>
		<div class="layui-input-inline" style="width:300px;">
			<select name="position"  id="position">
				<option value="">请选择</option>
			</select>
		</div>
	</div>

	<div class="layui-form-item" style="position:absolute;left:100px;bottom:0">
		<div class="layui-input-block" >
			<button  type="button"  class="layui-btn" id="tj">确定</button>
			<button  type="button"  class="layui-btn" onclick="cancle()">取消</button>
		</div>
	</div>
</form>
</body>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/resume/recovery.js"></script>
</html>