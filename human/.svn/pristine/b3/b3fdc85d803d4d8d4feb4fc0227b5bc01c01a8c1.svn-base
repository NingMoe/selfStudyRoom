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
			<form class="layui-form" id="addForm" action="" method="post" >
			  <input type="hidden" name="gradeSubjectId"  id="gradeSubjectId" value="${jzbUpClass.gradeSubjectId}">
			  <input type="hidden" name="nschoolid"  id="nschoolid" value="${schoolId}">								
				<div class="layui-form-item">
				<label class="layui-form-label">升班期规则:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select id="schoolSbqId" name="schoolSbqId" lay-verify="required">
							 <option value="">请选择</option>	
							 <c:forEach var="schoolSbq" items="${schoolSbqList }">
		       	              <option value="${schoolSbq.sWindowPeriodId }|${schoolSbq.sStageId }">${schoolSbq.sWindowPeriodName }-${schoolSbq.sStageName }</option>
		                     </c:forEach>
	    			   </select>
					</div>
				</div>
																																
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>		
	</body>
<script type="text/javascript" src="<%=basePath %>/static/jzbTest/jzbUpClass/add.js"></script>
</html>