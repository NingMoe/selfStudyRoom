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
			<div class="layui-form">
				<input type="hidden" id="id" name="id" value="${teacher.id }">
				<input type="hidden" id="teacherCode" name="teacherCode" value="${teacher.teacherCode }">
				<input type="hidden" id="lockUser" name="lockUser" value="${teacher.lockUser }">
				<input type="hidden" id="lockState" name="lockState" value="${teacher.lockState }">
				<input type="hidden" id="currUser" name="currUser" value="${currUser }">
				<div class="layui-form-item">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-inline">
						<input type="text" id="teacherName" name="teacherName" value="${teacher.teacherName }" class="layui-input" readonly="readonly">
					</div>
					
					<label class="layui-form-label">性别</label>
					<div class="layui-input-inline">
						<input type="text" 
						<c:if test="${teacher.sex eq 'M'}">value="男"</c:if>
						<c:if test="${teacher.sex eq 'F'}">value="女"</c:if>
						class="layui-input" readonly="readonly">
						<input type="hidden" id="sex" name="sex" value="${teacher.sex }">
					</div>
					
					<label class="layui-form-label">所属部门</label>
					<div class="layui-input-inline">
						<input type="text" id="orgName" name="orgName" value="${teacher.orgName }" class="layui-input" readonly="readonly">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">教师带课属性</label>
					<div class="layui-input-inline">
						<select name="dksx" id="dksx" style="width: 150px;">
						<option value="">请选择</option>
    						<c:forEach items="${dksxs }" var="sx">
    							<option value="${sx.name }" <c:if test="${teacher.dksx eq sx.name }">selected="selected"</c:if>>${sx.name }</option>
    						</c:forEach>
      					</select>
					</div>
					
					<label class="layui-form-label">教研组</label>
					<div class="layui-input-inline">
						<select name="jyz" id="jyz" style="width: 150px;">
						<option value="">请选择</option>
    						<c:forEach items="${jyzs }" var="jyz">
    							<option value="${jyz.name }" <c:if test="${teacher.jyz eq jyz.name }">selected="selected"</c:if>>${jyz.name }</option>
    						</c:forEach>
      					</select>
					</div>
					
					<label class="layui-form-label">子部门</label>
					<div class="layui-input-inline">
						<select name="dept" id="dept" style="width: 150px;">
						<option value="">请选择</option>
    						<c:forEach items="${jwdepts }" var="dept">
    							<option value="${dept.name }" <c:if test="${teacher.dept eq dept.name }">selected="selected"</c:if>>${dept.name }</option>
    						</c:forEach>
      					</select>
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">带生年级</label>
					<div class="layui-input-inline">
   						<select name="grades" id="grades" lay-filter="grades" lay-verify="required" multiple="multiple" lay-search="">
   						<option value="">请选择</option>
   						<c:forEach items="${jwgrades }" var="grade">
   							<option value="${grade.name }"  <c:if test="${fn:contains(teacher.grades,grade.name)}">selected="selected"</c:if>>${grade.name }</option>
   						</c:forEach>
   						</select>
    				</div>
    				
    				
    				<label class="layui-form-label">校区</label>
					<div class="layui-input-inline">
   						<select name="sites" id="sites" lay-filter="sites" lay-verify="required" multiple="multiple" lay-search="">
   							<option value="">请选择</option>
   							<c:forEach items="${jwsites }" var="site">
   								<option value="${site.name }" <c:if test="${fn:contains(teacher.sites,site.name)}">selected="selected"</c:if>>${site.name }</option>
   							</c:forEach>
   						</select>
    				</div>
					
					<label class="layui-form-label">科目</label>
					<div class="layui-input-inline">
    					<select name="subject" id="subject" lay-verify="required">
   							<option value="">请选择</option>
   							<c:forEach items="${jwSubjects }" var="subject">
   								<option value="${subject.name }" <c:if test="${teacher.subject eq subject.name }">selected="selected"</c:if>>${subject.name }</option>
   							</c:forEach>
   						</select>
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">当前1对1班量</label>
					<div class="layui-input-inline">
						<input type="text" id="oneCurr" name="oneCurr" value="${teacher.oneCurr }" class="layui-input" readonly="readonly">
	      			</div>
	      			
	      			<label class="layui-form-label">1对1上限</label>
	      			<div class="layui-input-inline">	
	      				<input type="text" id="oneSx" name="oneSx" value="${teacher.oneSx }" class="layui-input" lay-verify="required|number" >
	      			</div>
	      			
	      			<label class="layui-form-label">1对1缺口</label>
	      			<div class="layui-input-inline">	
	      				<input type="text" id="oneQk" name="oneQk" class="layui-input" readonly="readonly">
					</div>
				</div>
				
				<div class="layui-form-item">
				    <label class="layui-form-label">当前1对6班量</label>
					<div class="layui-input-inline">
						<input type="text" id="sexCurr" name="sexCurr" value="${teacher.sexCurr }" class="layui-input" readonly="readonly">
	      			</div>
	      			
	      			<label class="layui-form-label">财年课时（小时）</label>
					<div class="layui-input-inline">
						<input type="text" id="cnHours" name="cnHours" value="${teacher.cnHours }" class="layui-input">
	      			</div>
	      			
	      			<label class="layui-form-label">备注</label>
					<div class="layui-input-inline">
						<input type="text" id="remark" name="remark" value="${teacher.remark }" class="layui-input">
	      			</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" id="bc" lay-submit="" lay-filter="ced">保存</button>
						<button type="button" id="qx" class="layui-btn">取消</button>
					</div>
				</div>
			</div>
		<script type="text/javascript" src="<%=basePath %>/static/jw/edit.js"></script>
	</body>
</html>