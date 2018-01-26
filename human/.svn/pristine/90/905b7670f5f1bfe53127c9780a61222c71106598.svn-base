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
			<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">基本信息</legend>              
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 11%;">职位名称</label>
					<div class="layui-input-inline" style="width: 15%;">
						<input type="text" id="name" name="name" lay-verify="required" class="layui-input">
					</div>
					
					<label class="layui-form-label" style="width: 11%;">所属学校</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<select name="comid" id="comid" lay-filter="comid" lay-verify="required">
    					<option value="${company.companyId }">${company.companyName }</option>
      					</select>
    				</div>
    			
      				<label class="layui-form-label" style="width: 11%;">所属部门</label>
      				<div class="layui-input-inline" style="width: 15%;">
        				<select name="dept" id="dept" lay-verify="required">
        					<option value="">请选择</option>
	        				<c:forEach items="${orgs }" var="org">
	        				<option value="${org.id }">${org.name }</option>
	        				</c:forEach>
        				</select>
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 11%;">职位属性</label>
    				<div class="layui-input-inline" style="width: 15%;">
    					<select name="positionAttribute" lay-verify="required" id="positionAttribute">
    						<option value="">请选择</option>
    						<c:forEach items="${attributes }" var="attr">
    							<option value="${attr.name }">${attr.name }</option>
    						</c:forEach>
    					</select>
    				</div>
    				
					<label class="layui-form-label" style="width: 11%;">职位性质</label>
    				<div class="layui-input-inline" style="width: 15%;">
    					<select name="positionNature" lay-verify="required" id="positionNature">
    						<option value="">请选择</option>
    						<c:forEach items="${natures }" var="nature">
    							<option value="${nature.name }">${nature.name }</option>
    						</c:forEach>
    					</select>
    				</div>
    				
    				<label class="layui-form-label" style="width: 11%;">所属分类</label>
					<div class="layui-input-inline" style="width: 15%;">
						<select name="postionClassification" lay-verify="required" id="postionClassification">
    						<option value="">请选择</option>
    						<c:forEach items="${classifications }" var="classification">
    							<option value="${classification.name }">${classification.name }</option>
    						</c:forEach>
    					</select>
    				</div>
				</div>
				
				<div class="layui-form-item">
						
					<label class="layui-form-label" style="width: 11%;">招聘人数</label>
					<div class="layui-input-inline" style="width: 15%;">
						<input type="text" id="recruitmentNumber" name="recruitmentNumber" lay-verify="required" class="layui-input">
					</div>
					
					<label class="layui-form-label" style="width: 11%;">学历要求</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<select name="requireDegree" lay-verify="required" id="requireDegree">
    						<option value="">请选择</option>
    						<c:forEach items="${degrees }" var="degree">
    							<option value="${degree.name }">${degree.name }</option>
    						</c:forEach>
      					</select>
    				</div>
					<label class="layui-form-label" style="width: 11%;">经验要求</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<select name="workingMonth" id="workingMonth">
    						<option value="">请选择</option>
    						<c:forEach items="${years }" var="year">
    							<option value="${year.name }">${year.name }</option>
    						</c:forEach>
      					</select>
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 11%;">工作城市</label>
					<div class="layui-input-inline" style="width: 15%;">
   						<select name="jobCity" id="jobCity" lay-verify="required" lay-search="">
   							<option value="">请选择</option>
   							<c:forEach items="${citys }" var="city">
   								<option value="${city.id }">${city.areaName }</option>
   							</c:forEach>
   						</select>
    				</div>
    				
    				<label class="layui-form-label" style="width: 11%;">具体地点</label>
					<div class="layui-input-inline" style="width: 15%;">
    					<input type="text" id="jobAddr" name="jobAddr" lay-verify="required" class="layui-input">
    				</div>
				</div>
				
				 <div class="layui-form-item">
				 	<div class="layui-inline" style="width: 100%">
				 		<label class="layui-form-label" style="width: 11%;">职位月薪</label>
				 		<div class="layui-input-inline" style="width:280px;">
							<c:forEach items="${salaryTypes }" var="type">
	   							<input type="radio" name="salaryType" title="${type.name }" lay-filter="salaryType" value="${type.name }">
	   						</c:forEach>
	    				</div>
				 		
						<div class="layui-input-inline xzfw" style="display:none;">
	    					<select name="positionSalaryRange" id="positionSalaryRange">
	    						<option value="">请选择</option>
	    						<c:forEach items="${salaryRanges }" var="range">
	    							<option value="${range.name }">${range.name }</option>
	    						</c:forEach>
	      					</select>
	    				</div>
	    				
				 		<div class="layui-input-inline sdtx"  style="width: 100px;display:none;">
			        		<input type="text" name="salary0" placeholder="￥" autocomplete="off" class="layui-input">
			      		</div>
			      		<div class="layui-form-mid sdtx" style="display:none;">-</div>
			      		<div class="layui-input-inline sdtx" style="width: 100px;display:none;">
			        		<input type="text" name="salary1" placeholder="￥" autocomplete="off" class="layui-input">
			      		</div>
				 	</div>
			  	</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label"  style="width: 11%;">职位亮点</label>
				    <div class="layui-input-inline" style="width:700px;">
   						<c:forEach items="${highlights }" var="highlight">
   							<input type="checkbox" name="highLight" title="${highlight.name }" value="${highlight.name }">
   						</c:forEach>
				    </div>
				</div>
				
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label"  style="width: 11%;">工作内容</label>
				    <div class="layui-input-inline" style="width:700px;">
				      <textarea class="layui-textarea layui-hide" id="obContent" name="obContent" lay-verify="content">
				      
				      </textarea>
				    </div>
				</div>
				
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label"  style="width: 11%;">任职资格</label>
				    <div class="layui-input-inline" style="width:700px;">
				      <textarea class="layui-textarea layui-hide" id="qualifications" name="qualifications" lay-verify="content">
				      
				      </textarea>
				    </div>
				</div>
				
				</fieldset>
				<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">系统设置</legend>
				<div class="layui-form-item">
					<label class="layui-form-label"  style="width: 11%;">对外发布</label>
					<div class="layui-input-inline" style="width:15%;">
    					<select name="isRelease" id="isRelease" >
    						<option value="1">是</option>
    						<option value="0">否</option>
      					</select>
    				</div>
    				
    				<label class="layui-form-label"  style="width: 11%;">职位状态</label>
					<div class="layui-input-inline" style="width:15%;">
						<select name="status" id="status" style="width: 150px;">
    						<option value="1">启用</option>
    						<option value="0">禁用</option>
      					</select>
    				</div>
    				 
    				<label class="layui-form-label"  style="width: 11%;">职位权重</label>
					<div class="layui-input-inline" style="width:15%;">
						<input type="text" id="priority" name="priority" placeholder="数字越小，优先级越高" lay-verify="required|number" class="layui-input">
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label"  style="width: 11%;">试用期时间</label>
					<div class="layui-input-inline" style="width:15%;">
    					<input type="text" id="probationPeriod" placeholder="单位为月" value="6" name="probationPeriod" lay-verify="required|number" class="layui-input">
    				</div>
					
					<label class="layui-form-label"  style="width: 11%;">长期有效</label>
					<div class="layui-input-inline" style="width:15%;">
    					<input type="checkbox" name="isLongEffective" value="1" lay-filter="effective" title="长期有效" >
    				</div>
					
    				
    				<label class="layui-form-label efftime" style="width: 11%;">职位有效期</label>
					<div class="layui-inline efftime" style="width:15%;">
						<input  class="layui-input" id="effectiveDate" name="effectiveDate" 
						placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
    				</div>
    			</div>	
    			<div class="layui-form-item">
    				<label class="layui-form-label"  style="width: 11%;">自动一面</label>
					<div class="layui-input-inline" style="width:15%;">
						<select name="isAotusub" id="isAotusub" style="width: 150px;">
    						<option value="0">是</option>
    						<option value="1" selected="selected">否</option>
      					</select>
    				</div>
				</div>
				</fieldset>
				
				<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">简历收取与反馈</legend>
				<div class="layui-form-item">
					<label class="layui-form-label"  style="width: 11%;">投递邮箱</label>
					<div class="layui-input-inline" style="width:15%;">
						<select name="mailType" id="mailType" lay-filter="mailType">
    						<option value="0">学校默认</option>
    						<option value="1">自定义</option>
      					</select>
      					<input type="hidden" id="srcmail" value="${company.recruitEmail }">
    				</div>
    				<div class="layui-input-inline">
    					<input type="text" id="mailbox" name="mailbox" value="${company.recruitEmail }" class="layui-input">
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label"  style="width: 11%;">联系电话</label>
					<div class="layui-input-inline" style="width:15%;">
						<select name="telType" id="telType" lay-filter="telType">
    						<option value="0">学校默认</option>
    						<option value="1">自定义</option>
      					</select>
      					<input type="hidden" id="srctel" value="${company.servicePhone }">
    				</div>
    				<div class="layui-input-inline">
    					<input type="text" id="telephone" name="telephone" value="${company.servicePhone }" class="layui-input">
    				</div>
				</div>
    				
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"  style="width: 11%;">投递反馈</label>
					<div class="layui-input-inline" style="width:15%;">
    					<input type="checkbox" name="isFeedback" value="1" title="短信反馈">
    				</div>
				</div>
				</fieldset>
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="tj">立即提交</button>
					</div>
				</div>
			</form>
		<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson.js"></script>
		<script type="text/javascript" src="<%=basePath %>/static/recruitment/position/add.js"></script>
	</body>
</html>