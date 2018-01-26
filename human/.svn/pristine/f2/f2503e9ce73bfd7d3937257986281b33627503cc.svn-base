<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>简历投递</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
<script type="text/javascript">
	var  deviceWidth = document.documentElement.clientWidth;
	if(deviceWidth >750) deviceWidth =750;
	document.documentElement.style.fontSize = deviceWidth /7.5+'px';
</script>
<style type="text/css">
body{background:#f2f2f2;}
.weui-select{padding-left:0;}
.ulbstyle{
   height: 25%;
   width:30%;
   margin-left:35%

}
input{
	-webkit-appearance:none;
	outline:none
}
.submit1{
	background:#1eb9aa!important;
	width:66%!important;
	height:40px!important;
	line-height:40px!important;
	margin-left:17%;
	border-radius:60px;
	box-shadow: 1px 1px 8px 1px #afdeda;
}
.icon{
	width:30px;
	margin-right:5px;
	margin-top:-2px;
}
.padin{width:100%}
.titleDiv {
	color: #000000;
	padding-left: 20px;
	line-height:40px;
	background: #ffffff;
	border-bottom: 1px solid #eee;
	margin-top: 10px;
}
.sc-main{
	background:#f2f2f2;
}
.fa{float:right;
line-height:40px;
margin-right:3%;
color:#b2b2b2;

}
.add1{
	border:0;
	width:40%;
	margin-left:30%;
	border-radius:60px;
	background:#1eb9aa;
	color:#ffffff;
	height:30px;
	margin-top:5px
}
.titleDiv_Btn {
    float: right;
    margin-top: 4px;
}
input::-webkit-input-placeholder { /* WebKit browsers */ text-align: left; } 
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */ text-align: left; } 
input:-ms-input-placeholder { /* Internet Explorer 10 */ text-align: left; } 
input::placeholder { text-align: left; }
</style>
</head>
<body ontouchstart>
	<form id="editForm" action="" method="post" onSubmit = "return false;">
	  <div class="weui-cells_form">
	  <!-- 隐藏域开始 -->
	   <input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${rs.id }">
	   <input id="matchingPosition" type="hidden" name="matchingPosition" value="${positionId }">
	   <input type="hidden" id="headUrl" name="headUrl" value="${rb.headUrl}">
	   <input type="hidden" id="hasHeadPhotoFlag" name="hasHeadPhotoFlag" value="${hasHeadPhotoFlag}">
	  <!-- 隐藏域结束 -->
	             <!-- 简历头像开始 -->
	                 <div class="sc-main">
				    		<div class="sc-imgbox">
				            	<c:if  test="${empty rb.headUrl}">
								  <img id="photoImg" style="width:120px;height:120px;" src="<%=basePath%>/static/front/image/photo.jpg"/>
								  <input class="ulbstyle" type="file" name="photo" id="photo" />
								 </c:if>			
								<c:if  test="${!empty rb.headUrl}">
								   <c:if test="${fn:startsWith(rb.headUrl, 'http')}">
								      <img id="photoImg" style="width:120px;height:120px;" src="${rb.headUrl }"/>
								      <input class="ulbstyle" type="file" name="photo" id="photo" />
								   </c:if>
								   <c:if test="${!fn:startsWith(rb.headUrl, 'http')}">
								      <img id="photoImg" style="width:120px;height:120px;" src="${filepath }${rb.headUrl }"/>
								      <input class="ulbstyle" type="file" name="photo" id="photo" />
								   </c:if>		
								</c:if>         
				            </div>                
				       </div>
				 <!-- 简历头像结束 -->
				 <!-- 个人信息开始 -->
				   <div class='padin'>
					<div id="basicDiv">
						<div data-toggle="collapse" id="wwc1" class="titleDiv" data-parent="basicDiv" href="#basicRecord">
							<img class='icon' src="<%=basePath%>/static/front/image/jltd_03.jpg">个人信息&nbsp;<font color="red">*</font>
							<li class="fa fa-angle-double-right" ></li>
						</div>
						<div class="collapse in" id="basicRecord">						
						      <div class="weui-cell">
								<div class="weui-cell__hd">
								<label class="weui-label">姓名</label>
								</div>
								<div class="weui-cell__bd">
								<input class="weui-input"  style="text-align:left;" name="name" id="name" placeholder="请输入姓名" type="text" value="${rs.name }" />
							   </div>
							 </div>
							
							<div class="weui-cell">
								<div class="weui-cell__hd">
								<label class="weui-label">手机号</label>
								</div>
								<div class="weui-cell__bd">
								<input class="weui-input"  style="text-align:left;" type="tel" name="telephone" id="telephone" placeholder="请输入手机号"  value="${rs.phone }" />
							   </div>
							</div>
					
					        <div class="weui-cell">
								<div class="weui-cell__hd">
								<label class="weui-label">性别</label>
								</div>
								<div class="weui-cell__bd">
									<select class="weui-select" style="text-align:left;" name="sex" id="sex">
										<option value="">请选择</option>
										<option value="M" <c:if test="${rb.sex eq 'M'}">selected="selected"</c:if>>男</option>
										<option value="F" <c:if test="${rb.sex eq 'F'}">selected="selected"</c:if>>女</option>
									</select>
							   </div>
							</div>
					
							<div class="weui-cell">
								<div class="weui-cell__hd">
								<label class="weui-label" for="">出生年月</label>
								</div>
								<div class="weui-cell__bd">
								<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"  name="birthDate"  id="birthDate" value="${rb.birthDate }">
								</div>
							</div>
					
						   <div class="weui-cell">
								<div class="weui-cell__hd">
								<label class="weui-label">婚姻状况</label>
								</div>
								<div class="weui-cell__bd">
									<select class="weui-select" style="text-align:left;" name="marriage" id="marriage">
									    <option value="">请选择</option>
										<option value="1"<c:if test="${rb.marriage eq '1'}">selected="selected"</c:if>>未婚</option>
										<option value="2"<c:if test="${rb.marriage eq '2'}">selected="selected"</c:if>>已婚</option>
									</select>
							   </div>
							</div>
									
							<div class="weui-cell">
								<div class="weui-cell__hd">
								<label class="weui-label">所在城市</label>
								</div>
								<div class="weui-cell__bd">
								 <input class="weui-input"  style="text-align:left;" name="locationCity"  id="locationCity" placeholder="请输入城市" type="text" value="${rb.locationCity }">
							    </div>
							</div>
													
							<div class="weui-cell">
								<div class="weui-cell__hd">
								<label class="weui-label">联系邮箱</label>
								</div>
								<div class="weui-cell__bd">
								<input class="weui-input"  style="text-align:left;" name="email" id="email" placeholder="请输入邮箱" type="text" value="${rb.email }" >
							   </div>
							</div> 
						</div>
					</div>			
                  </div>
				 <!-- 个人信息结束 -->
				 <!-- 求职意向开始 -->
					 <div class='padin'>
						<div id="jobIntensionDiv">
							<div data-toggle="collapse" id="wwc2" class="titleDiv" data-parent="jobIntensionDiv" href="#collapseJobIntension">
								<img class='icon' src="<%=basePath%>/static/front/image/jltd_06.jpg">求职意向&nbsp;&nbsp;
								<li class="fa fa-angle-double-right" ></li>
							</div>
							<div class="collapse in" id="collapseJobIntension" style="display: none;">
								<div class="weui-cell">
									<div class="weui-cell__hd">
									<label class="weui-label">期望工作地点</label>
									</div>
									<div class="weui-cell__bd">
										<input class="weui-input" style="text-align:left;" type="text" placeholder="请输入期望工作地点" name="resumeInterntion#expectWorkPlace" id="expectWorkPlace" value="${ri.expectWorkPlace }"/>
								   </div>
								</div>		
								<div class="weui-cell">
									<div class="weui-cell__hd">
									<label class="weui-label">期望薪金</label>
									</div>
									<div class="weui-cell__bd">
									<input class="weui-input"  name="resumeInterntion#minSalary" id="minSalary"  pattern="[0-9]*" type="number" style="width:15%;text-align:left;" value="${ri.minSalary }">K &nbsp;&nbsp;-
									<input class="weui-input"  name="resumeInterntion#maxSalary" id="maxSalary"  pattern="[0-9]*" type="number" style="width:15%;text-align:left;" value="${ri.maxSalary }">K
								    </div>
								</div>
					        </div>
					     </div>
					 </div>
				 <!-- 求职意向结束 --> 
			    <!-- 教育经历 开始 -->
                <div class='padin'>               
					<div id="schoolRecordDiv">
						<div data-toggle="collapse" id="wwc3" class="titleDiv" data-parent="schoolRecordDiv" href="#schoolRecord">
						<img class='icon' src="<%=basePath%>/static/front/image/jltd_08.jpg">教育经历&nbsp;<font color="red">*</font>
							 <li class="fa fa-angle-double-right"></li>						
						</div>
						<div class="collapse in" id="schoolRecord" style="display: none;">
					    <c:if  test="${!empty rehList}">
						 <c:forEach var="resumeEdu" items="${rehList }" varStatus="status">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="resumeEduList.id" value="${resumeEdu.id}">
									<div class="row  inlineHidden"   style="text-align: right;">
										<button type="button"  class="layui-btn layui-btn-mini layui-btn-danger"
									      style="margin-right: 10px;"  onclick="removeRecord(this);">
									       &nbsp;删除
								        </button>
							        </div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">学校</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="resumeEduList.schoolName" id="schoolName" placeholder="请输入学校" type="text"  value="${resumeEdu.schoolName }">
									   </div>
									</div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">学历</label>
										</div>
										<div class="weui-cell__bd">
											<select class="weui-select" style="text-align:left;" name="resumeEduList.education"  id="education">
											    <option value="">请选择</option>
												<c:forEach items="${eduList }" var="edu">
								    					<option value="${edu.name }"<c:if test="${resumeEdu.education eq edu.name}">selected="selected"</c:if>>${edu.name }</option>
								    		    </c:forEach>
											</select>
									   </div>
									</div>							
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">专业</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="resumeEduList.major" placeholder="请输入专业"  id="major"  type="text"  value="${resumeEdu.major }" >
									   </div>
									</div>
							
								   <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">入学时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="text-align:left;"
												name="resumeEduList.startTime" id="startTime"
												value="<fmt:formatDate value='${resumeEdu.startTime}' pattern='yyyy-MM-dd'/>"/>
										</div>
									</div>
									
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">毕业时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"
											 name="resumeEduList.endTime"
											value="<c:if test="${empty resumeEdu.endTime}"></c:if><c:if test="${!empty resumeEdu.endTime}"><fmt:formatDate value='${resumeEdu.endTime}' pattern='yyyy-MM-dd'/></c:if>"/>
										</div>
									</div>
							     </div>
							    </c:forEach>
							  </c:if>
							  <input class='add1' type="button" id="schoolRecord1" onclick="addRecord(this,event);" value='添加'>								
							</div>							
						</div>			
                  </div>
            <!-- 教育经历结束 -->
            <!-- 工作经历开始 -->
                 <div class='padin'>               
					<div id="workRecordDiv">
						<div data-toggle="collapse"  id="wwc4"class="titleDiv" data-parent="workRecordDiv" href="#workRecord">
							<img class='icon' src="<%=basePath%>/static/front/image/jltd_10.jpg">工作经历&nbsp;&nbsp;
							<li class="fa fa-angle-double-right"></li>
						
						</div>
						<div class="collapse in" id="workRecord" style="display: none;">
					    <c:if  test="${!empty rwhList}">
						 <c:forEach var="rwh" items="${rwhList }" varStatus="status">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="resumeWorkList.id" value="${rwh.id}">
									<div class="row  inlineHidden"   style="text-align: right;">
										<button type="button"  class="layui-btn layui-btn-mini layui-btn-danger"
									      style="margin-right: 10px;"  onclick="removeRecord(this);">
									       &nbsp;删除
								        </button>
							        </div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">公司名称</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="resumeWorkList.companyName" id="companyName" placeholder="请输入公司名称" type="text"  value="${rwh.companyName }">
									   </div>
									</div>
									  <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">岗位名称</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="resumeWorkList.position" id="position" placeholder="请输入岗位名称" type="text"  value="${rwh.position }">
									   </div>
									</div>
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">工作性质</label>
										</div>
										<div class="weui-cell__bd">
											<select class="weui-select" style="text-align:left;" name="resumeWorkList.workType" id="workType">
												<option value="0" <c:if test="${rwh.workType eq '0'}">selected="selected"</c:if>>全职</option>
												<option value="1" <c:if test="${rwh.workType eq '1'}">selected="selected"</c:if>>兼职</option>
												<option value="2" <c:if test="${rwh.workType eq '2'}">selected="selected"</c:if>>实习</option>
											</select>
									   </div>
									 </div>							
								   <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">开始时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="text-align:left;"
												name="resumeWorkList.startTime" id="startTime"
												value="<fmt:formatDate value='${rwh.startTime}' pattern='yyyy-MM-dd'/>"/>
										</div>
									</div>
									
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">结束时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"
											   name="resumeWorkList.endTime"
											value="<c:if test="${empty rwh.endTime}"></c:if><c:if test="${!empty rwh.endTime}"><fmt:formatDate value='${rwh.endTime}' pattern='yyyy-MM-dd'/></c:if>"/>
										</div>
									</div>
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">职位薪金</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  name="resumeWorkList.minSalary" id="minSalary" pattern="[0-9]*" type="number" style="width:15%;text-align:left;" value="${rwh.minSalary }">K &nbsp;&nbsp;-
										<input class="weui-input"  name="resumeWorkList.maxSalary" id="maxSalary" pattern="[0-9]*" type="number" style="width:15%;text-align:left;" value="${rwh.maxSalary }">K
									    </div>
									</div>
		
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">工作描述</label>
										</div>
										<div class="weui-cell__bd">
										 <textarea class="weui-textarea" style="text-align:left;" placeholder="请输入工作描述" rows="5" name="resumeWorkList.describes" id="describes">${rwh.describes }</textarea>
									    </div>
									</div>
							     </div>
							    </c:forEach>
							  </c:if>
							  <input class='add1' type="button" id="workRecord1" onclick="addRecord(this,event);" value='添加'>
							</div>
						</div>			
                  </div>
            <!-- 工作经历结束 -->  
            <!-- 项目经验开始 -->
                   <div class='padin'>               
					<div id="projectRecordDiv">
						<div data-toggle="collapse"  id="wwc5" class="titleDiv" data-parent="projectRecordDiv" href="#projectRecord">
							<img class='icon' src="<%=basePath%>/static/front/image/jltd_12.jpg">项目经验&nbsp;&nbsp;
							  <li class="fa fa-angle-double-right"></li>							
						
						</div>
						<div class="collapse in" id="projectRecord" style="display: none;">
					    <c:if  test="${!empty rpeList}">
						 <c:forEach var="rpe" items="${rpeList }" varStatus="status">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="projectList.id" value="${rpe.id}">
									<div class="row  inlineHidden"   style="text-align: right;">
										<button type="button"  class="layui-btn layui-btn-mini layui-btn-danger"
									      style="margin-right: 10px;"  onclick="removeRecord(this);">
									       &nbsp;删除
								        </button>
							        </div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">项目名称</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="projectList.projectName" id="projectName" placeholder="请输入项目名称" type="text"  value="${rpe.projectName }">
									   </div>
									</div>
									
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">项目职责</label>
										</div>
										<div class="weui-cell__bd">
										 <textarea class="weui-textarea" style="text-align:left;" placeholder="请输入项目职责" rows="5" name="projectList.responsibilityDescribe" id="responsibilityDescribe">${rpe.responsibilityDescribe }</textarea>
									    </div>
									</div>
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">所属公司</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="projectList.companyName" id="companyName" placeholder="请输入所属公司" type="text"  value="${rpe.companyName }">
									   </div>
									</div>							
								   <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">开始时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"
												name="projectList.startTime" id="startTime"
												value="<fmt:formatDate value='${rpe.startTime}' pattern='yyyy-MM-dd'/>"/>
										</div>
									</div>
									
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">结束时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"
											  name="projectList.endTime"
											value="<c:if test="${empty rpe.endTime}"></c:if><c:if test="${!empty rpe.endTime}"><fmt:formatDate value='${rpe.endTime}' pattern='yyyy-MM-dd'/></c:if>"/>
										</div>
									</div>
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">项目描述</label>
										</div>
										<div class="weui-cell__bd">
										 <textarea class="weui-textarea" style="text-align:left;" placeholder="请输入项目描述" rows="5" name="projectList.projectDescribe" id="projectDescribe">${rpe.projectDescribe }</textarea>
									    </div>
									</div>
							     </div>
							    </c:forEach>
							  </c:if>
							  <input class='add1' type="button" id="projectRecord1" onclick="addRecord(this,event);" value='添加'>
							</div>
						</div>			
                  </div>
            <!-- 项目经验结束 --> 
            <!-- 语言能力开始 --> 
            	 <div class='padin'>               
					<div id="languageDiv">
						<div data-toggle="collapse"  id="wwc6" class="titleDiv" data-parent="languageDiv" href="#language">
							<img class='icon' src="<%=basePath%>/static/front/image/jltd_14.jpg">语言能力&nbsp;&nbsp;
							<li class="fa fa-angle-double-right"></li>
					
						</div>
						<div class="collapse in" id="language" style="display: none;">
					    <c:if  test="${!empty rlList}">
						 <c:forEach var="rl" items="${rlList }" varStatus="status">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="languageList.id" value="${rl.id}">
									<div class="row  inlineHidden"   style="text-align: right;">
										<button type="button"  class="layui-btn layui-btn-mini layui-btn-danger"
									      style="margin-right: 10px;"  onclick="removeRecord(this);">
									       &nbsp;删除
								        </button>
							        </div>
							         <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">认证名称</label>
										</div>
										<div class="weui-cell__bd">
											<select class="weui-select" style="text-align:left;" name="languageList.name">
											    <option value="">请选择</option>
												<c:forEach items="${resumeLanguageList }" var="language">
								    					<option value="${language.name }"<c:if test="${rl.name eq language.name}">selected="selected"</c:if>>${language.name }</option>
								    		    </c:forEach>
											</select>
									   </div>
									</div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">认证成绩</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="languageList.describes" id="describes" placeholder="请输入成绩或描述" type="text"  value="${rl.describes }">
									   </div>
									</div>
							     </div>
							    </c:forEach>
							  </c:if>
							  <input class='add1' type="button" id="language1" onclick="addRecord(this,event);" value='添加'>
							</div>
						</div>			
                  </div>
            <!-- 语言能力结束 -->
            <!-- 内部推荐开始 -->
                <div class='padin'>
						<div id="insideRecommendDiv">
							<div data-toggle="collapse" id="wwc7" class="titleDiv" data-parent="insideRecommendDiv" href="#collapseInsideRecommend">
								<img class='icon' src="<%=basePath%>/static/front/image/jltd_16.png">内部推荐&nbsp;&nbsp;
								<li class="fa fa-angle-double-right" ></li>
							</div>
							<div class="collapse in" id="collapseInsideRecommend" style="display: none;">								
								<div class="weui-cell">
									<div class="weui-cell__hd">
									<label class="weui-label">推荐人邮箱</label>
									</div>
									<div class="weui-cell__bd">
									  <c:if  test="${empty insideRecommend}">
									   <input class="weui-input"  name="insideRecommend" id="insideRecommend" placeholder="请输入邮箱" type="text" style="text-align:left;" value="${rb.insideRecommend }">
									  </c:if>
									 <c:if test="${!empty insideRecommend}">
									   <input class="weui-input"  name="insideRecommend" id="insideRecommend" placeholder="请输入邮箱" type="text" style="text-align:left;" value="${insideRecommend }" readonly>
									 </c:if>
								    </div>
								</div>
								<div class="weui-cell">
									<div class="weui-cell__hd">
									<label class="weui-label">与推荐人关系</label>
									</div>
									<div class="weui-cell__bd">
									<input class="weui-input"  name="insideRelation" id="insideRelation"  placeholder="请输入关系" type="text" style="text-align:left;" value="${rb.insideRelation }">
								   </div>
								</div>
					        </div>
					     </div>
					 </div>                
            <!-- 内部推荐结束 -->                  				       
	  </div>
	   <div class="weui-btn-area">       
         <button id="showTooltips"  class="weui-btn weui-btn_primary submit1" onClick="save();">确认投递</button>
       </div>	              
	</form>
<!-- 教育经历复制html专用区域 -->
<div id="copy_schoolRecord" style="display: none;">
	<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
		<input type="hidden" name="resumeEduList.id" value="">
			<div class="row  inlineHidden"   style="text-align: right;">
					<button type="button"  
							 class="layui-btn layui-btn-mini layui-btn-danger"
							 style="margin-right: 10px;"  onclick="removeRecord(this);">
							 &nbsp;删除
					</button>
		   </div>
		   
		 <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">学校</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="resumeEduList.schoolName" id="schoolName" placeholder="请输入学校" type="text">
		   </div>
		</div>

        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">学历</label>
			</div>
			<div class="weui-cell__bd">
				<select class="weui-select" style="text-align:left;" name="resumeEduList.education"  id="education">
				    <option value="">请选择</option>
					<c:forEach items="${eduList }" var="edu">
	    					<option value="${edu.name }">${edu.name }</option>
	    		    </c:forEach>
				</select>
		   </div>
		</div>

        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">专业</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="resumeEduList.major" placeholder="请输入专业"  id="major"  type="text">
		   </div>
		</div>

	   <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label" for="">入学时间</label>
			</div>
			<div class="weui-cell__bd">
			<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"  name="resumeEduList.startTime"  id="startTime">
			</div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label" for="">毕业时间</label>
			</div>
			<div class="weui-cell__bd">
			<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="text-align:left;"  name="resumeEduList.endTime"  id="endTime">
			</div>
		</div>												
	</div>
</div>
<!-- 教育经历复制html专用区域  -->
<!-- 工作经历复制html专用区域 -->
<div  id="copy_workRecord" style="display: none;">
                          <div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="resumeWorkList.id" value="">
									<div class="row  inlineHidden"   style="text-align: right;">
										<button type="button"  class="layui-btn layui-btn-mini layui-btn-danger"
									      style="margin-right: 10px;"  onclick="removeRecord(this);">
									       &nbsp;删除
								        </button>
							        </div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">公司名称</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="resumeWorkList.companyName" id="companyName" placeholder="请输入公司名称" type="text">
									   </div>
									</div>
									  <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">岗位名称</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="resumeWorkList.position" id="position" placeholder="请输入岗位名称" type="text" >
									   </div>
									</div>
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">工作性质</label>
										</div>
										<div class="weui-cell__bd">
											<select class="weui-select" style="text-align:left;" name="resumeWorkList.workType" id="workType">
												<option value="0">全职</option>
												<option value="1">兼职</option>
												<option value="2">实习</option>
											</select>
									   </div>
									 </div>							
								   <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">开始时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="text-align:left;"  name="resumeWorkList.startTime" id="startTime"/>
										</div>
									</div>
									
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">结束时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;" name="resumeWorkList.endTime" id="endTime"/>
										</div>
									</div>
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">职位薪金</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  name="resumeWorkList.minSalary" id="minSalary" pattern="[0-9]*" type="number" style="width:15%;text-align:left;" >K &nbsp;&nbsp;-
										<input class="weui-input"  name="resumeWorkList.maxSalary" id="maxSalary" pattern="[0-9]*" type="number" style="width:15%;text-align:left;" >K
									    </div>
									</div>
		
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">工作描述</label>
										</div>
										<div class="weui-cell__bd">
										 <textarea class="weui-textarea" style="text-align:left;" placeholder="请输入工作描述" rows="5" name="resumeWorkList.describes" id="describes"></textarea>
									    </div>
									</div>
							     </div>									
</div>
<!-- 项目经验复制html专用区域 -->
<div id="copy_projectRecord" style="display: none;">
   <div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="projectList.id" value="">
									<div class="row  inlineHidden"   style="text-align: right;">
										<button type="button"  class="layui-btn layui-btn-mini layui-btn-danger"
									      style="margin-right: 10px;"  onclick="removeRecord(this);">
									       &nbsp;删除
								        </button>
							        </div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">项目名称</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="projectList.projectName" id="projectName" placeholder="请输入项目名称" type="text">
									   </div>
									</div>
									
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">项目职责</label>
										</div>
										<div class="weui-cell__bd">
										 <textarea class="weui-textarea" style="text-align:left;" placeholder="请输入项目职责" rows="5" name="projectList.responsibilityDescribe" id="responsibilityDescribe"></textarea>
									    </div>
									</div>
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">所属公司</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="projectList.companyName" id="companyName" placeholder="请输入所属公司" type="text">
									   </div>
									</div>							
								   <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">开始时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="text-align:left;" name="projectList.startTime" id="startTime"/>
										</div>
									</div>
									
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label" for="">结束时间</label>
										</div>
										<div class="weui-cell__bd">
										<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;" name="projectList.endTime" id="endTime"/>
										</div>
									</div>
									<div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">项目描述</label>
										</div>
										<div class="weui-cell__bd">
										 <textarea class="weui-textarea" style="text-align:left;" placeholder="请输入项目描述" rows="5" name="projectList.projectDescribe" id="projectDescribe"></textarea>
									    </div>
									</div>
							     </div>
</div>
<!-- 语言能力复制html专用区域 -->
<div id="copy_language" style="display: none;">
    <div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="languageList.id" value="">
									<div class="row  inlineHidden"   style="text-align: right;">
										<button type="button"  class="layui-btn layui-btn-mini layui-btn-danger"
									      style="margin-right: 10px;"  onclick="removeRecord(this);">
									       &nbsp;删除
								        </button>
							        </div>
							         <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">认证名称</label>
										</div>
										<div class="weui-cell__bd">
											<select class="weui-select" style="text-align:left;" name="languageList.name">
											    <option value="">请选择</option>
												<c:forEach items="${resumeLanguageList }" var="language">
								    					<option value="${language.name }">${language.name }</option>
								    		    </c:forEach>
											</select>
									   </div>
									</div>
							        <div class="weui-cell">
										<div class="weui-cell__hd">
										<label class="weui-label">认证成绩</label>
										</div>
										<div class="weui-cell__bd">
										<input class="weui-input"  style="text-align:left;" name="languageList.describes" id="describes" placeholder="请输入成绩或描述" type="text">
									   </div>
									</div>
							     </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson2.js"></script>
<script type="application/javascript" src="<%=basePath%>/static/front/js/fastDelivery/fastclick.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resumeDelivery/resumeDelivery.js"></script>
</html>