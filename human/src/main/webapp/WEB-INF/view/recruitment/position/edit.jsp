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
			<form class="layui-form" id="editForm">
			<c:if test="${!empty position.releaseTime }">
				<input id="isHasReleaseTime" type="hidden" name="isHasReleaseTime" value="1">
			</c:if>
			<c:if test="${empty position.releaseTime }">
				<input id="isHasReleaseTime" type="hidden" name="isHasReleaseTime" value="0">
			</c:if>
			<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">基本信息</legend>
				<div class="layui-form-item">
						<label class="layui-form-label" style="width:11%;">职位名称</label>
						<input type="hidden" id="id" name="id" value="${position.id }">
						<div class="layui-input-inline" style="width:15%;">
							<input type="text" id="name" name="name" lay-verify="required" value="${position.name }" class="layui-input">
						</div>
					
					<label class="layui-form-label" style="width:11%;">所属学校</label>
					<div class="layui-input-inline" style="width:15%;">
    					<select name="comid" id="comid" lay-filter="comid" lay-verify="required">
    						<option value="${company.companyId }">${company.companyName }</option>
      					</select>
    				</div>
    				
      				<label class="layui-form-label" style="width:11%;">所属部门</label>
      				<div class="layui-input-inline" style="width:15%;">
      					<input type="hidden" id="deptId" name="deptId" value="${position.dept }">
        				<select name="dept" id="dept" lay-verify="required">
        					<option value="">请选择</option>
	        				<c:forEach items="${orgs }" var="org">
	        				<option value="${org.id }" <c:if test="${position.dept eq org.id }">selected="selected"</c:if>>${org.name }</option>
	        				</c:forEach>
        				</select>
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:11%;">职位属性</label>
    				<div class="layui-input-inline" style="width:15%;">
    					<select name="positionAttribute" lay-verify="required" id="positionAttribute">
    						<option value="">请选择</option>
    						<c:forEach items="${attributes }" var="attr">
    							<option value="${attr.name }"
    							 <c:if test="${attr.name eq position.positionAttribute }">selected="selected"</c:if>
    							>${attr.name }</option>
    						</c:forEach>
    					</select>
    				</div>
    				
					<label class="layui-form-label" style="width:11%;">职位性质</label>
    				<div class="layui-input-inline" style="width:15%;">
    					<select name="positionNature" lay-verify="required" id="positionNature" value="${position.positionNature }">
    						<option value="">请选择</option>
    						<c:forEach items="${natures }" var="nature">
    							<option value="${nature.name }"
    							<c:if test="${nature.name eq position.positionNature }">selected="selected"</c:if>
    							>${nature.name }</option>
    						</c:forEach>
    					</select>
    				</div>
    				
    				<label class="layui-form-label" style="width:11%;">所属分类</label>
					<div class="layui-input-inline" style="width:15%;">
						<select name="postionClassification" lay-verify="required" id="postionClassification">
    						<option value="">请选择</option>
    						<c:forEach items="${classifications }" var="classification">
    							<option value="${classification.name }"
    							<c:if test="${classification.name eq position.postionClassification }">selected="selected"</c:if>
    							>${classification.name }</option>
    						</c:forEach>
    					</select>
    				</div>
				</div>
				
				<div class="layui-form-item">
						
					<label class="layui-form-label" style="width:11%;">招聘人数</label>
					<div class="layui-input-inline" style="width:15%;">
						<input type="text" id="recruitmentNumber" name="recruitmentNumber" value="${position.recruitmentNumber }" lay-verify="required" class="layui-input">
					</div>
					
					<label class="layui-form-label" style="width:11%;">学历要求</label>
					<div class="layui-input-inline" style="width:15%;">
    					<select name="requireDegree" lay-verify="required" id="requireDegree">
    						<option value="">请选择</option>
    						<c:forEach items="${degrees }" var="degree">
    							<option value="${degree.name }"
    								<c:if test="${degree.name eq position.requireDegree }">selected="selected"</c:if>
    							>${degree.name }</option>
    						</c:forEach>
      					</select>
    				</div>
					<label class="layui-form-label" style="width:11%;">经验要求</label>
					<div class="layui-input-inline" style="width:15%;">
    					<select name="workingMonth" id="workingMonth" value="${position.workingMonth }">
    						<option value="">请选择</option>
    						<c:forEach items="${years }" var="year">
    							<option value="${year.name }"
    							<c:if test="${year.name eq position.workingMonth }">selected="selected"</c:if>
    							>${year.name }</option>
    						</c:forEach>
      					</select>
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:11%;">工作城市</label>
					<div class="layui-input-inline" style="width:15%;">
   						<select name="jobCity" id="jobCity" lay-verify="required" multiple="multiple" lay-search="">
   							<option value="">请选择</option>
   							<c:forEach items="${citys }" var="city">
   								<option value="${city.id }">${city.areaName }</option>
   							</c:forEach>
   						</select>
    				</div>
    				
    				<label class="layui-form-label" style="width:11%;">具体地点</label>
					<div class="layui-input-inline" style="width:15%;">
    					<input type="text" id="jobAddr" name="jobAddr" lay-verify="required" value="${position.jobAddr }" class="layui-input">
    				</div>
				</div>
				
				 <div class="layui-form-item">
				 	<div class="layui-inline" style="width:100%;">
				 		<label class="layui-form-label" style="width:11%;">职位月薪</label>
				 		<div class="layui-input-inline" style="width:280px;">
							<c:forEach items="${salaryTypes }" var="type">
	   							<input type="radio" name="salaryType" title="${type.name }" 
	   							<c:if test="${type.name eq position.salaryType }">checked="checked"</c:if>
	   							lay-filter="salaryType" value="${type.name }">
	   						</c:forEach>
	    				</div>
				 		
						<div class="layui-input-inline xzfw" style="display:none;">
	    					<select name="positionSalaryRange" id="positionSalaryRange">
	    						<option value="">请选择</option>
	    						<c:forEach items="${salaryRanges }" var="range">
	    							<option value="${range.name }"
	    							<c:if test="${range.name eq position.positionSalaryRange }">selected="selected"</c:if>
	    							>${range.name }</option>
	    						</c:forEach>
	      					</select>
	    				</div>
	    				
				 		<div class="layui-input-inline sdtx"  style="width: 100px;display:none;">
			        		<input type="text" name="salary0" placeholder="￥" autocomplete="off" class="layui-input" value="${position.salary0 }">
			      		</div>
			      		<div class="layui-form-mid sdtx" style="display:none;">-</div>
			      		<div class="layui-input-inline sdtx" style="width: 100px;display:none;">
			        		<input type="text" name="salary1" placeholder="￥" autocomplete="off" class="layui-input" value="${position.salary1 }">
			      		</div>
				 	</div>
			  	</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:11%;">职位亮点</label>
				    <div class="layui-input-inline" style="width:700px;">
   						<c:forEach items="${highlights }" var="highlight">
   							<input type="checkbox" name="highLight" 
   							<c:if test="${range.name eq position.positionSalaryRange }">checked=""</c:if>
   							title="${highlight.name }" value="${highlight.name }">
   						</c:forEach>
				    </div>
				</div>
				
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label" style="width:11%;">工作内容</label>
				    <div class="layui-input-inline" style="width:700px;">
				      <textarea class="layui-textarea layui-hide" id="obContent" name="obContent" lay-verify="content">
				      ${position.obContent }
				      </textarea>
				    </div>
				</div>
				
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label" style="width:11%;">任职资格</label>
				    <div class="layui-input-inline" style="width:700px;">
				      <textarea class="layui-textarea layui-hide" id="qualifications" name="qualifications" lay-verify="content">
				      ${position.qualifications }
				      </textarea>
				    </div>
				</div>
				
				</fieldset>
				<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">系统设置</legend>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:11%;">对外发布</label>
					<input type="hidden" id="releaseTime" value="${position.releaseTime }">
					<div class="layui-input-inline" style="width:15%;">
    					<select name="isRelease" id="isRelease">
    						<option value="1"
    						<c:if test="${position.isRelease eq 1 }">selected="selected"</c:if>
    						>是</option>
    						<option value="0"
    						<c:if test="${position.isRelease eq 0}">selected="selected"</c:if>
    						>否</option>
      					</select>
    				</div>
    				
    				<label class="layui-form-label" style="width:11%;">职位状态</label>
					<div class="layui-input-inline" style="width:15%;">
						<select name="status" id="status" value="${position.status }">
    						<option value="1"
    						<c:if test="${position.status eq 1 }">selected="selected"</c:if>
    						>启用</option>
    						<option value="0"
    						<c:if test="${position.status eq 0 }">selected="selected"</c:if>
    						>禁用</option>
      					</select>
    				</div>
    				 
    				<label class="layui-form-label" style="width:11%;">职位权重</label>
					<div class="layui-input-inline" style="width:15%;">
						<input type="text" id="priority" name="priority" value="${position.priority }" placeholder="数字越小，优先级越高" lay-verify="required|number" class="layui-input">
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:11%;">试用期时间</label>
					<div class="layui-input-inline" style="width:15%;">
    					<input type="text" id="probationPeriod" name="probationPeriod" placeholder="单位为月" value="${position.probationPeriod }" lay-verify="required|number" class="layui-input">
    				</div>
					
					<label class="layui-form-label" style="width:11%;">长期有效</label>
					<div class="layui-input-inline" style="width:15%;">
    					<input type="checkbox" lay-filter="effective" name="isLongEffective" value="1" 
    						<c:if test="${position.isLongEffective eq 1 }">checked="checked"</c:if>
    					title="长期有效">
    				</div>
					
    				
    				<label style="width:11%;" class="layui-form-label efftime" <c:if test="${position.isLongEffective eq 1 }">style="display:none;"</c:if>>职位有效期</label>
					<div style="width:15%;" class="layui-inline efftime"  <c:if test="${position.isLongEffective eq 1 }">style="display:none;"</c:if>>
						<input class="layui-input" <c:if test="${position.isLongEffective eq 0 }">lay-verify="required"</c:if> id="effectiveDate" 
						name="effectiveDate" value='<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${position.effectiveDate }"/>'  
						placeholder="自定义日期格式" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
    				</div>
    			</div>	
    			<div class="layui-form-item">
    				<label class="layui-form-label" style="width:11%;">自动一面</label>
					<div class="layui-input-inline" style="width:15%;">
						<select name="isAotusub" id="isAotusub">
    						<option value="0"
    						<c:if test="${position.isAotusub eq 0 }">selected="selected"</c:if>
    						>是</option>
    						<option value="1"
    						<c:if test="${position.isAotusub eq 1 }">selected="selected"</c:if>
    						>否</option>
      					</select>
    				</div>
    				
    				
				</div>
				</fieldset>
				
				<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">简历收取与反馈</legend>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:11%;">投递邮箱</label>
					<div class="layui-input-inline" style="width:15%;">
						<select name="mailType" id="mailType" lay-filter="mailType">
    						<option value="0"
    						<c:if test="${position.mailType eq 0 }">selected="selected"</c:if>
    						>学校默认</option>
    						<option value="1"
    						<c:if test="${position.mailType eq 1 }">selected="selected"</c:if>
    						>自定义</option>
      					</select>
      					<input type="hidden" id="srcmail" value="${company.recruitEmail }">
    				</div>
    				<div class="layui-input-inline">
    					<input type="text" id="mailbox" name="mailbox" 
    					<c:if test="${position.telType eq 0 }">
    					value="${company.recruitEmail }"
    					</c:if>
    					<c:if test="${position.telType eq 1 }">
    					value="${position.mailbox }"
    					</c:if> 
    					class="layui-input">
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:11%;">联系电话</label>
					<div class="layui-input-inline" style="width:15%;">
						<select name="telType" id="telType" lay-filter="telType">
    						<option value="0"
    						<c:if test="${position.telType eq 0 }">selected="selected"</c:if>
    						>学校默认</option>
    						<option value="1"
    						<c:if test="${position.telType eq 1 }">selected="selected"</c:if>
    						>自定义</option>
      					</select>
      					<input type="hidden" id="srctel" value="${company.servicePhone }">
    				</div>
    				<div class="layui-input-inline">
    					<input type="text" id="telephone" name="telephone" 
    					<c:if test="${position.telType eq 0 }">
    					value="${company.servicePhone }"
    					</c:if>
    					<c:if test="${position.telType eq 1 }">
    					value="${position.telephone }"
    					</c:if>
    					class="layui-input">
    				</div>
				</div>
    				
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:11%;">投递反馈</label>
					<div class="layui-input-inline">
    					<input type="checkbox" name="isFeedback" value="1" 
    					<c:if test="${position.isFeedback eq 1 }">checked="checked"</c:if>
    					 title="短信反馈">
    				</div>
				</div>
				</fieldset>
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
					</div>
				</div>
			</form>
		<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson.js"></script>
		<script type="text/javascript" src="<%=basePath %>/static/recruitment/position/edit.js"></script>
		<script type="text/javascript">
		init('${position.jobCity}','${position.highLight}');
		</script>
	</body>
</html>