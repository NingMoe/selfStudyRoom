<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>简历详情</title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	   <script src="<%=basePath%>/static/common/bootstrap/js/bootstrap.min.js"></script>
	   <link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
	   <link rel="stylesheet" href="<%=basePath%>/static/viewer/viewer.min.css"/>
<script src="<%=basePath%>/static/viewer/viewer.min.js"></script>
<style type="text/css">
</style>
<body >
	<div class="appForm container">
		<div class="layui-tab layui-tab-card row" lay-filter="tabChange">
				<ul class="layui-tab-title" style="position: inherit;"> 
					<li class="layui-this">简历详情</li>
					<li>面试反馈</li>
					<!-- <li>笔试情况</li> -->
				</ul>
				<div style="color:#4BB2FF;margin-bottom:10px;width:100%" >
					<div 
					<c:if test="${!empty node }">
						title="${node.positionName}>>${node.nodeName }&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${node.isMsgDesc eq '1'}">面试官：${node.assigneeStr }</c:if>"
					</c:if>
					 style="text-align:right;width:60%;height:20px;margin-top:-37px;margin-right:20px;float: right;overflow:hidden;">
					 <c:if test="${!empty node }">
					 	${node.positionName}>>${node.nodeName }&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${node.isMsgDesc eq '1'}">面试官：${node.assigneeStr }</c:if>
					 </c:if>
					 </div>
				</div>
			<div class="layui-tab-content">
			<input type="hidden"  id="userName" value="${userName}"/>
			<input type="hidden"  id="flowCode" value="${flowCode}"/>
			<input id="filepath" value="${filepath}" type="hidden">
				<div class="layui-tab-item layui-show">
				<div id="fixDiv" style="position: fixed;width: 100px;">
					<div id="fixButton">
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary" href="#jldesc">
								<li class="fa fa-user-o"></li> &nbsp;个人信息
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${empty rb.resumeInterntion.id}">inlineHidden isinit</c:if>" href="#jobIntensionDiv">
								<li class="fa fa-star-o"></li> &nbsp;求职意向
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${empty rb.evaluation}">inlineHidden isinit</c:if>" href="#mydiv">
								<li class="fa fa-newspaper-o"></li> &nbsp;自我评价
							</button>
							<%-- <button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${!empty rb.resumeLink or enclosure }">inlineHidden isinit</c:if> " href="#jltable">
								<li class="fa fa-envelope-o"></li> &nbsp;原始简历
							</button> --%>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary" href="#jltable">
								<li class="fa fa-envelope-o"></li> &nbsp;原始简历
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${rb.resumeMajorList == null || fn:length(rb.resumeMajorList) == 0}">inlineHidden isinit</c:if>" href="#majordiv">
								<li class="fa fa-book"></li> &nbsp;专业技能
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${rb.languageList == null || fn:length(rb.languageList) == 0}">inlineHidden isinit</c:if>" href="#languageDiv">
								<li class="fa fa-language"></li> &nbsp;语言能力
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${fn:length(rb.resumeSnapshot) == 0 && fn:length(rb.resumePhoto) == 0}">inlineHidden isinit</c:if>" href="#imgTable">
								<li class="fa fa-picture-o"></li> &nbsp;图片简历
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${fn:length(rb.resumeWorkList) == 0}">inlineHidden isinit</c:if>" href="#workRecordDiv">
								<li class="fa fa-suitcase"></li> &nbsp;工作经历
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${fn:length(rb.resumeEduList) == 0}">inlineHidden isinit</c:if>" href="#schoolRecordDiv">
								<li class="fa fa-graduation-cap"></li> &nbsp;教育经历
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${fn:length(rb.schoolPostList) == 0}">inlineHidden isinit</c:if>" href="#schoolDutyDiv">
								<li class="fa fa-trophy"></li> &nbsp;校内职务
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${fn:length(rb.trainList) == 0}">inlineHidden isinit</c:if>" href="#pxRecordDiv">
								<li class="fa fa-university"></li> &nbsp;培训经历
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${fn:length(rb.projectList) == 0}">inlineHidden isinit</c:if>" href="#projectRecordDiv">
								<li class="fa fa-th-large"></li> &nbsp;项目经验
							</button>
							<button type="button" class="layui-btn layui-btn-small layui-btn-primary <c:if test="${fn:length(rb.practiceList) == 0}">inlineHidden isinit</c:if>" href="#practicalRecordDiv">
								<li class="fa fa-coffee"></li> &nbsp;实践经验
							</button>
							</div>
							<button type="button" id="reEdit" class="layui-btn layui-btn-small layui-btn-warm inlineHidden"
								onclick="cancelFrom(event);"">
								<li class="fa fa-reply"></li> &nbsp; 取消编辑
							</button>
							<button type="button" id="edit" 
								class="layui-btn layui-btn-small layui-btn-warm"
								onclick="editFrom(event);">
								<li class="fa fa-pencil"></li> &nbsp;编 &nbsp; &nbsp; &nbsp; &nbsp;辑
							</button>
							<button type="button" id="sub" style="border:1px solid #C9C9C9"
								class="layui-btn layui-btn-small layui-btn-normal inlineHidden" >
								<li class="fa fa-check"></li> &nbsp;保 &nbsp; &nbsp; &nbsp; &nbsp;存
							</button>
							<button type="button"  onclick="toSendMsgNoLogin('${rb. telephone}','${rb.resumeSeekerId}','${rb.id}');"
								class="layui-btn layui-btn-small layui-btn-normal" style="border:1px solid #C9C9C9">
								<li class="fa fa-commenting-o"></li> &nbsp;消息通知
							</button>
						<c:if test="${flowCode !=null && flowCode !=''}">
							<c:if test="${ifQx}">
								<button type="button" onclick="feedback();"
									class="layui-btn layui-btn-small layui-btn-normal">
									<li class="fa fa-pencil-square-o"></li> &nbsp;面试反馈
								</button>
							</c:if>
						</c:if>
					</div>
				<form class="layui-form" id="editForm" action="" method="post">
					<div class="" style="" id="jldesc">
						<div data-toggle="collapse" class="titleDiv " data-parent="jldesc"
							href="#collapseOne">
							个人信息&nbsp;
							<li class="fa fa-angle-double-down" ></li>
						</div>
						<div class="container collapse in" id="collapseOne">
								<input type="hidden"  id="id" name="id" value="${rb.id}"> 
								<input type="hidden"  name="resumeSeekerId" value="${rb.resumeSeekerId}">
								<input type="hidden"  id="headUrl" name="headUrl" value="${rb.headUrl}">
								<div class="row">
									<div class="col-md-2" style="padding: 10px;text-align: center;">
										<div class="row">
											<img style="width: 120px;height: 168px;margin: 0 auto;"
												id="file1Img"
												<c:if  test="${empty rb.headUrl}">
						 src="<%=basePath%>/static/common/images/hxr_photo.jpg"</c:if>
												<c:if  test="${!empty rb.headUrl}">
						 <c:if test="${fn:startsWith(rb.headUrl, 'http')}"> src="${rb.headUrl }"</c:if>
												<c:if test="${!fn:startsWith(rb.headUrl, 'http')}"> src="${filepath }${rb.headUrl }"</c:if>
											</c:if>
											/>
										</div>
										<div class="row">
											<div class="layui-input-inline ">
											<div class="inlineHidden isinit">
												<input type="file" lay-type="images" id="file1" name="file1"
													class="layui-upload-file  " lay-title="上传头像"
													style="width:50px;" disabled>
													</div>
													<div class="noinlineHidden">
												<button type="button"  onclick="showQrCode('<%=basePath%>/free/resume/quickUploadResumPhoto.html?resumeId=${rb.id}','简历头像更新')"
													class="layui-btn layui-btn-mini layui-btn-normal">
													<li class="fa fa-cloud-upload"></li> &nbsp;快传
												</button></div>
											</div>
										</div>
									</div>
									<div class="col-md-10">
										<div class="row">
											<div
												class="layui-inline <c:if test="${empty rb.name}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">姓名:</label>
												<div class="layui-input-inline">
													<input type="text" name="name" lay-verify="required"
														class="layui-input"  autocomplete="off"
														value="${rb.name}" disabled>
												</div>
											</div>
											<div class="layui-inline <c:if test="${empty rb.sex}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">性别:</label>
												<div class="layui-form layui-input-inline">
													<select name="sex" disabled>
														<option value="">选择性别</option>
														<option value="F"
															<c:if test="${rb.sex eq 'F'}">selected</c:if>>女</option>
														<option value="M"
															<c:if test="${rb.sex eq 'M'}">selected</c:if>>男</option>
													</select>
												</div>
											</div>
											<div class="layui-inline <c:if test="${empty rb.birthDate}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">出生日期:</label>
												<div class="layui-form layui-input-inline">
													<input type="text" name="birthDate" autocomplete="off"
														class="layui-input" value="${rb. birthDate}" disabled>
												</div>
											</div>
											
											<div
												class="layui-inline <c:if test="${empty rb. nationality}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">国籍:</label>
												<div class="layui-input-inline">
													<input type="text" name="nationality" autocomplete="off"
														class="layui-input" value="${rb. nationality}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.telephone}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">手机号:</label>
												<div class="layui-input-inline">
													<input type="text" name="telephone"
														lay-verify="required|phone" class="layui-input"
														value="${rb. telephone}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.email}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">邮箱:</label>
												<div class="layui-input-inline">
													<input type="text" name="email" lay-verify="email"
														class="layui-input" value="${rb. email}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb. nation}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">民族:</label>
												<div class="layui-input-inline">
													<input type="text" name="nation" autocomplete="off"
														class="layui-input" value="${rb. nation}" disabled>
												</div>
											</div>
											<!-- </div>
						<div class="row"> -->
											<div
												class="layui-inline  <c:if test="${empty rb. graSchool}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">学校:</label>
												<div class="layui-input-inline">
													<input type="text" name="graSchool" class="layui-input"
														value="${rb. graSchool}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb. major}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">专业:</label>
												<div class="layui-input-inline">
													<input type="text" name="major" class="layui-input"
														value="${rb. major}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.highEdu}">inlineHidden isinit</c:if>">
												<label class="layui-form-label" style="width: 90px;">学历:</label>
												<div class="layui-form layui-input-inline">
													<select name="highEdu" disabled>
														<option value="">选择学历</option>
														<c:forEach var="de" items="${degrees }">
															<option value="${de.name }"
																<c:if test="${de.name eq rb.highEdu}">selected="selected"</c:if>>${de.name }</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.graduationDate}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">毕业时间:</label>
												<div class="layui-input-inline">
													<input type="text" name="graduationDate" lay-verify="date"
														 autocomplete="off"
														class="layui-input" onclick="layui.laydate({elem: this})"
														class="layui-input" value="${rb. graduationDate}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.phoneBack}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">备用号码:</label>
												<div class="layui-input-inline">
													<input type="text" name="phoneBack" lay-verify="phone"
														class="layui-input" value="${rb. phoneBack}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.source}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">简历来源:</label>
												<div class="layui-input-inline">
													<input type="text" name="source" class="layui-input"
														value="${rb. source}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.type}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">简历类型:</label>
												<div class="layui-input-inline">
													<input type="text" name="type" class="layui-input"
														value="${rb.type}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.locationCity}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">居住城市:</label>
												<div class="layui-form layui-input-inline">
													<select name="locationCity" id="locationCity" disabled
														lay-search>
														<option value="">选择城市</option>
														<c:forEach var="area" items="${areaInfo }">
															<option value="${area.areaName }"
																<c:if test="${rb.locationCity eq area.areaName}">selected</c:if>>${area.areaName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.householdRegister}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">户口:</label>
												<div class="layui-form layui-input-inline">
													<select name="householdRegister" id="householdRegister"
														disabled lay-search>
														<option value="">选择城市</option>
														<c:forEach var="area" items="${areaInfo }">
															<option value="${area.areaName }"
																<c:if test="${rb.householdRegister eq area.areaName}">selected</c:if>>${area.areaName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.politicalStatus}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">政治面貌:</label>
												<div class="layui-input-inline">
													<input type="text" name="politicalStatus"
														class="layui-input" value="${rb.politicalStatus}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.certificatesType}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">证件类型:</label>
												<div class="layui-input-inline">
													<input type="text" name="certificatesType"
														class="layui-input" value="${rb.certificatesType}"
														disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.certificatesNumber}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">证件号码:</label>
												<div class="layui-input-inline">
													<input type="text" name="certificatesNumber"
														class="layui-input" value="${rb.certificatesNumber}"
														disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.postAdjustment}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">是否调剂:</label>
												<div class="layui-input-inline">
												<select name="postAdjustment" disabled>
														<option value="">选择是否调剂</option>
														<option value="1"
															<c:if test="${rb.postAdjustment==0}">selected</c:if>>是</option>
														<option value="0"
															<c:if test="${rb.postAdjustment==0}">selected</c:if>>否</option>
													</select>
													
												</div>
											</div>

											<div
												class="layui-inline <c:if test="${empty rb.deliveryDate}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">投递时间:</label>
												<div class="layui-input-inline">
													<input type="text" name="deliveryDate" lay-verify="datetime"
														 autocomplete="off"
														class="layui-input"
														onclick="layui.laydate({elem: this,istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
														class="layui-input" value="${rb.deliveryDate}" disabled>
												</div>
											</div>

											<div
												class="layui-inline <c:if test="${empty rb.workingTime}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">工作年限:</label>
												<div class="layui-input-inline">
													<input type="text" name="workingTime" class="layui-input"
														value="${rb.workingTime}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.insideRecommend}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">推荐人:</label>
												<div class="layui-input-inline">
													<input type="text" name="insideRecommend"
														class="layui-input" value="${rb.insideRecommend}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.insideRelation}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">推荐人关系:</label>
												<div class="layui-input-inline">
													<input type="text" name="insideRelation"
														class="layui-input" value="${rb.insideRelation}" disabled>
												</div>
												<button type="button" class="layui-btn" lay-submit="" lay-filter="editApp"  id="editButton" style="display: none;" >编辑</button>
											</div>
										</div>
									</div>
								</div>
						</div>
					</div>
						<!-- 求职意向 -->
					<div class="<c:if test="${empty rb.resumeInterntion.id}">inlineHidden isinit</c:if>" style="" id="jobIntensionDiv">
						<div data-toggle="collapse" class=" titleDiv" data-parent="jobIntensionDiv" href="#collapseJobIntension">
							求职意向&nbsp;
							<li class="fa fa-angle-double-down" ></li>
						</div>
						<div class="collapse in" id="collapseJobIntension">
							<div class="row">
							<input type="hidden" name="resumeInterntion#id" value="${rb.resumeInterntion.id}">
									<div class="layui-inline <c:if test="${empty rb.resumeInterntion.expectWorkPlace}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">工作地区:</label>
												<div class="layui-input-inline">
													<input type="text" name="resumeInterntion#expectWorkPlace" class="layui-input"
														value="${rb.resumeInterntion.expectWorkPlace}" disabled>
												</div>
											</div>
									<div
												class="layui-inline <c:if test="${empty rb.resumeInterntion.expectWorkProperty}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">工作性质:</label>
												<div class="layui-input-inline">
													<input type="text" name="resumeInterntion#expectWorkProperty" class="layui-input"
														value="${rb.resumeInterntion.expectWorkProperty}" disabled>
												</div>
											</div>
									<div
												class="layui-inline <c:if test="${empty rb.resumeInterntion.expectWorkJob}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">从事职业:</label>
												<div class="layui-input-inline" style="width: 400px;">
													<input type="text" name="resumeInterntion#expectWorkJob" class="layui-input"
														value="${rb.resumeInterntion.expectWorkJob}" disabled>
												</div>
											</div>
									<div
												class="layui-inline <c:if test="${empty rb.resumeInterntion.expectWorkSalary}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">期望薪资:</label>
												<div class="layui-input-inline">
													<input type="text" name="resumeInterntion#expectWorkSalary" class="layui-input"
														value="${rb.resumeInterntion.expectWorkSalary}" disabled>
												</div>
											</div>
									<div
												class="layui-inline <c:if test="${empty rb.resumeInterntion.currentStatus}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">目前状况:</label>
												<div class="layui-input-inline">
													<input type="text" name="resumeInterntion#currentStatus" class="layui-input"
														value="${rb.resumeInterntion.currentStatus}" disabled>
												</div>
											</div>
									<div
												class="layui-inline <c:if test="${empty rb.resumeInterntion.expectArrivalTime}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">到岗时间:</label>
												<div class="layui-input-inline">
													<input type="text" name="resumeInterntion#expectArrivalTime" class="layui-input"
														value="${rb.resumeInterntion.expectArrivalTime}" disabled>
												</div>
											</div>
											<div
												class="layui-inline <c:if test="${empty rb.resumeInterntion.expectWorkIndustry}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">从事行业:</label>
												<div class="layui-input-inline" style="width: 400px;">
													<input type="text" name="resumeInterntion#expectWorkIndustry" class="layui-input"
														value="${rb.resumeInterntion.expectWorkIndustry}" disabled>
												</div>
											</div>
							</div>
						</div>
					</div>
					<!-- 求职意向 -->
					
					<!-- 自我评价 -->
					<div class="<c:if test="${empty rb.evaluation}">inlineHidden isinit</c:if>" style="" id="mydiv">
						<div data-toggle="collapse" class=" titleDiv" data-parent="mydiv" href="#collapseMy">
							自我评价&nbsp;
							<li class="fa fa-angle-double-down" ></li>
						</div>
						<div class="collapse in" id="collapseMy">
							<div class="row ">
									<textarea name="evaluation" disabled="disabled" 
										 class="layui-textarea">${rb.evaluation }</textarea>
							</div>
						</div>
					</div>
					<!-- 自我评价 -->
					<!-- 原始简历 -->
				<%-- 	<div class="<c:if test="${!rb.resumeLink==null or enclosure }">inlineHidden isinit</c:if>" style="" id="jltable"> --%>
					<div id="jltable">
						<div data-toggle="collapse" class="titleDiv"
							data-parent="jltable" href="#collapsejl">
							原始简历&nbsp;
							<li class="fa fa-angle-double-down" id="jl"></li>
						</div>
						<div class="collapse in" id="collapsejl">
							<div class="row"  style="padding-left: 10px;">
							<c:if test="${!empty rb.resumeLink or enclosure }">
								<c:if test="${!empty rb.resumeLink}"><button type="button" class="layui-btn  layui-btn-normal layui-btn-small" onclick="dowFile('${filepath }${rb.resumeLink}')"><li class="fa fa-link"></li> &nbsp;简历链接</button></c:if>
								<c:if test="${enclosure}"><button type="button" class="layui-btn layui-btn-normal layui-btn-small" onclick="dowEnclosure();"><li class="fa fa-paperclip"></li> &nbsp;简历附件</button></c:if>
							</c:if>
							<c:if test="${empty rb.resumeLink and !enclosure }">
								暂无原始简历信息
							</c:if>
							</div>
						</div>
					</div>
					<!-- 原始简历 -->
					<!-- 专业技能 -->
					<div class="<c:if test="${rb.resumeMajorList == null || fn:length(rb.resumeMajorList) == 0}">inlineHidden isinit</c:if>"   id="majordiv">
						<div data-toggle="collapse" class=" titleDiv" data-parent="majordiv" href="#collapsemajor">
							专业技能&nbsp;
							<li class="fa fa-angle-double-down" ></li>
							<button type="button"  onclick="addRecord(this,event);"
								class="inlineHidden layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn isinit"
								style="margin-right: 10px;">
								<li class="fa fa-plus"></li> &nbsp;新增
							</button>
						</div>
						<div class="collapse in" id="collapsemajor">
						<c:forEach var="resumeMajor" items="${rb.resumeMajorList}">
							<div class="row">
									<input type="hidden" value="${resumeMajor.id}" name="resumeMajorList.id">
									<div class="layui-inline">
										<label class="layui-form-label">技能名称:</label>
										<div class="layui-input-inline">
											<input type="text" name="resumeMajorList.skillName" class="layui-input"
												 value="${resumeMajor.skillName}" disabled>
										</div>
									</div>
									<div class="layui-inline <c:if test="${resumeMajor.masterDegree == null }">inlineHidden isinit</c:if>">
										<label class="layui-form-label">熟练程度:</label>
										<div class="layui-input-inline">
											<input type="text" name="resumeMajorList.masterDegree" class="layui-input"
												 value="${resumeMajor.masterDegree}" disabled>
										</div>
									</div>
									<div class="layui-inline <c:if test="${resumeMajor.useTime == null }">inlineHidden isinit</c:if>">
										<label class="layui-form-label">使用时间:</label>
										<div class="layui-input-inline">
											<input type="text" name="resumeMajorList.useTime" class="layui-input"
												 value="${resumeMajor.useTime}" disabled>
										</div>
									</div>
									<div class="layui-inline  inlineHidden"  style="margin-left: 10px;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除
							</button></div>
								</div>
						</c:forEach>
						</div>
					</div>
					<!-- 专业技能 -->
					<!-- 语言能力  -->
					<div class="<c:if test="${rb.languageList == null || fn:length(rb.languageList) == 0}">inlineHidden isinit</c:if>" style="" id="languageDiv">
						<div data-toggle="collapse" class=" titleDiv"
							data-parent="languageDiv" href="#language">
							语言能力&nbsp;
							<li class="fa fa-angle-double-down"></li>
							<button type="button"  onclick="addRecord(this,event);"
								class="inlineHidden layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn isinit"
								style="margin-right: 10px;">
								<li class="fa fa-plus"></li> &nbsp;新增
							</button>
						</div>
						<div class="collapse in" id="language">
							<c:forEach var="language" items="${rb.languageList}">
								<div class="row">
									<input type="hidden" value="${language.id}" name="languageList.id">
									<div class="layui-inline">
										<label class="layui-form-label">语言类型:</label>
										<div class="layui-input-inline">
											<input type="text" name="languageList.name" class="layui-input"
												 value="${language.name}" disabled>
										</div>
									</div>
									<div class="layui-inline <c:if test="${language.describes == null }">inlineHidden isinit</c:if>">
										<label class="layui-form-label">熟练程度:</label>
										<div class="layui-input-inline">
											<input type="text" name="languageList.describes"
												class="layui-input" 
												value="${language.describes}" disabled>
										</div>
									</div>
									<div class="layui-inline  inlineHidden"  style="margin-left: 10px;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除
							</button></div>
									<%-- 	<div class="layui-inline <c:if test="${empty resumeMajor.useTime}">inlineHidden isinit</c:if>">
										<label class="layui-form-label">等级证书:</label>
										<div class="layui-input-inline">
											<a href="#" target="_self">点击查看</a>
										</div>
									</div> --%>
								</div>
							</c:forEach>
						</div>
					</div>
					<!--语言能力 -->
					
					
					<!-- 图片简历 -->
					<div class="<c:if test="${fn:length(rb.resumeSnapshot) == 0 && fn:length(rb.resumePhoto) == 0}">inlineHidden isinit</c:if>" style="" id="imgTable">
						<div data-toggle="collapse" class=" titleDiv"
							data-parent="imgTable" href="#collapseImg">
							图片简历&nbsp;
							<li class="fa fa-angle-double-down"></li>
							<button type="button" 
								class="layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn"
								style="margin-right: 10px;">
								<li class="fa fa-cloud-upload"></li> &nbsp;快传
							</button>
							<button type="button"  id="uploadPhotoButton" onclick="uploadPhoto(event);"
								class="layui-btn layui-btn-mini layui-btn-danger titleDiv_Btn"
								 style="margin-right: 10px;">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除
							</button>
							<button type="button" id="cancelPhotoBtton" onclick="cancelPhoto(event);"
								class="layui-btn layui-btn-mini layui-btn-normal titleDiv_Btn"
								 style="margin-right: 10px;display: none;">
								<li class="fa fa-check"></li> &nbsp;完成
							</button>
						</div>
						<div class="collapse in" id="collapseImg">
							<div class="row imgs" id="layer-photos">
							<c:forEach var="snapshot" items="${rb.resumeSnapshot}" varStatus="st">
							<img  onclick="showImg();"
									layer-src="${filepath }${snapshot.pathName }"
									src="${filepath }${snapshot.pathName }"
									alt="简历快照_${st.index}">
							</c:forEach>
							<c:forEach var="photo" items="${rb.resumePhoto}">
							<div style="position:relative;display:inline-block;" id="photo_${photo.id}">
							<p style="position:absolute;z-index:1;background:#000000;width:100%;height:100%;opacity:0.5;display: none;"></p>
							<p style="position:absolute;right:5px;top:0;color: #ffffff;font-size:20px;z-index:2;display:none;" onclick="delResumePhoto(${photo.id},'${photo.path}')"><i class="fa fa-trash" ></i></p>
							<img  onclick="showImg();"
									layer-src="${filepath }${photo.path}"
									src="${filepath }${photo.path}"
									alt="${photo.name }"></div>
							</c:forEach>
								<input type="file" lay-type="images" id="file2" name="file2"
													 lay-title="上传图片" class="layui-upload-file">
							</div>
						</div>
					</div>
					<!-- 图片简历 -->
					
					<!-- 工作经历  -->
					<div class="<c:if test="${fn:length(rb.resumeWorkList) == 0}">inlineHidden isinit</c:if>"  id="workRecordDiv">
						<div data-toggle="collapse" class=" titleDiv"
							data-parent="workRecordDiv" href="#workRecord">
							工作经历&nbsp;
							<li class="fa fa-angle-double-down"></li>
							<button type="button"  onclick="addRecord(this,event);"
								class="inlineHidden layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn isinit"
								style="margin-right: 10px;">
								<li class="fa fa-plus"></li> &nbsp;新增
							</button>
						</div>
						<div class="collapse in" id="workRecord">
							<c:forEach var="resumeWork" items="${rb.resumeWorkList}">
								<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
								<input type="hidden" name="resumeWorkList.id" value="${resumeWork.id}">
									<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
									<div class="row">
										<div class="layui-form-item" >
										<div class="layui-inline">
												<label class="layui-form-label">工作时间:</label>
												<div class="layui-input-inline" style="width: 100px;">
        												<input type="text"  autocomplete="off"
													class="layui-input" name="resumeWorkList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<fmt:formatDate value='${resumeWork.startTime}' pattern='yyyy年MM月'/>"
													disabled>
      											</div>
      											 <div class="layui-form-mid">~</div>
      											 <div class="layui-input-inline" style="width: 100px;">
        													<input type="text"  autocomplete="off"
													class="layui-input"  name="resumeWorkList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<c:if test="${empty resumeWork.endTime}">至今</c:if><c:if test="${!empty resumeWork.endTime}"><fmt:formatDate value='${resumeWork.endTime}' pattern='yyyy年MM月'/></c:if>"
													disabled>
      											</div>
										</div>
										</div>
										</div>
										<div class="row">
											<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">所在公司</label>
												<div class="layui-input-inline" style="width: 400px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.companyName"
														value="${resumeWork.companyName }" disabled>
												</div>
											</div>
											<div class="layui-inline <c:if test="${empty resumeWork.companyScale}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">公司类型</label>
												<div class="layui-input-inline" >
														<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.companyScale"
														value="${resumeWork.companyScale }" disabled>
												</div>
											</div>
											</div>
										</div>
									<div class="row  <c:if test="${empty resumeWork.department and empty resumeWork.position and empty resumeWork.salary }">inlineHidden isinit</c:if>">
										<div class="layui-form-item">
											<div class="layui-inline  <c:if test="${empty resumeWork.department}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">部门:</label>
												<div class="layui-input-inline" style="width: 200px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.department"
														value="${resumeWork.department }" disabled>
												</div>
											</div>
											<div class="layui-inline <c:if test="${empty resumeWork.position}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">职位</label>
												<div class="layui-input-inline" style="width: 200px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.position"
														value="${resumeWork.position }" disabled>
												</div>
											</div>
											<div class="layui-inline <c:if test="${empty resumeWork.salary}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">薪资待遇:</label>
												<div class="layui-input-inline"  style="width: 200px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.salary"
														value="${resumeWork.salary }" disabled>
												</div>
											</div>
										</div>
									</div>
									<div class="row <c:if test="${empty resumeWork.describes}">inlineHidden isinit</c:if>" >
										<div class="layui-form-item"  style="margin-bottom:  2px;">
											<label class="layui-form-label">工作描述:</label>
											<div class="layui-input-block">
												<textarea   name="resumeWorkList.describes" placeholder="请输入工作描述"
													class="layui-textarea" disabled>${resumeWork.describes }</textarea>
											</div>
										</div>
									</div>
									<div class="row <c:if test="${empty resumeWork.leaveReason}">inlineHidden isinit</c:if>">
										<div class="layui-form-item">
											<label class="layui-form-label">离职原因:</label>
											<div class="layui-input-block">
												<textarea  name="resumeWorkList.leaveReason"  placeholder="请输入离职原因"
													class="layui-textarea" disabled>${resumeWork.leaveReason }</textarea>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							</div>
						</div>
					<!-- 工作经历 -->
					
					<!-- 教育经历  -->
					<div class="<c:if test="${fn:length(rb.resumeEduList) == 0}">inlineHidden isinit</c:if>" style="" id="schoolRecordDiv">
						<div data-toggle="collapse" class=" titleDiv" data-parent="schoolRecordDiv" href="#schoolRecord">
							教育经历&nbsp;
							<li class="fa fa-angle-double-down" ></li>
							<button type="button"   onclick="addRecord(this,event);"
								class="inlineHidden layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn isinit"
								onclick="" style="margin-right: 10px;">
								<li class="fa fa-plus"></li> &nbsp;新增
							</button>
						</div>
						<div class="collapse in" id="schoolRecord">
						<c:forEach var="resumeEdu" items="${rb.resumeEduList}">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="resumeEduList.id" value="${resumeEdu.id}">
									<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
									<div class="row">
										<div class="layui-form-item" >
										<div class="layui-inline">
													<label class="layui-form-label">起至时间:</label>
											<div class="layui-input-inline">
												<input type="text"  autocomplete="off"
													class="layui-input" name="resumeEduList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<fmt:formatDate value='${resumeEdu.startTime}' pattern='yyyy年MM月'/>"
													disabled>
											</div>
											<div class="layui-form-mid">~</div>
											<div class="layui-input-inline">
												<input type="text"  autocomplete="off"
													class="layui-input" name="resumeEduList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<c:if test="${empty resumeEdu.endTime}">至今</c:if><c:if test="${!empty resumeEdu.endTime}"><fmt:formatDate value='${resumeEdu.endTime}' pattern='yyyy年MM月'/></c:if>"
													disabled>
											</div>
										</div>
										</div>
									</div>
							<div class="row" >
											<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">学校</label>
												<div class="layui-input-inline" style="max-width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeEduList.schoolName"
														value="${resumeEdu.schoolName }" disabled>
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">专业</label>
												<div class="layui-input-inline" style="max-width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeEduList.major"
														value="${resumeEdu.major }" disabled>
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">学历</label>
												<div class="layui-input-inline" style="max-width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeEduList.education"
														value="${resumeEdu.education }" disabled>
												</div>
											</div>
											</div>
									</div>
										<div class="row <c:if test="${empty resumeEdu.describes}">inlineHidden isinit</c:if>" >
												<div class="layui-form-item">
											<label class="layui-form-label">学历描述:</label>
											<div class="layui-input-block">
												<textarea  placeholder="请简单描述学习课程和掌握情况"
													class="layui-textarea" disabled>${resumeEdu.describes }</textarea>
											</div>
										</div>
									</div>
							</div>
							</c:forEach>
							</div>
						</div>
					<!-- 教育经历 -->
					
					<!-- 校内职务  -->
					<div class="<c:if test="${fn:length(rb.schoolPostList) == 0}">inlineHidden isinit</c:if>" style="" id="schoolDutyDiv">
						<div data-toggle="collapse" class=" titleDiv" data-parent="schoolDutyDiv" href="#schoolDuty">
							校内职务&nbsp;
							<li class="fa fa-angle-double-down" ></li>
							<button type="button"  onclick="addRecord(this,event);"
								class="layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn inlineHidden isinit"
								 style="margin-right: 10px;">
								<li class="fa fa-plus"></li> &nbsp;新增
							</button>
						</div>
						<div class="collapse in" id="schoolDuty">
							<c:forEach var="schoolPost" items="${rb.schoolPostList}">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
							<input name="schoolPostList.id" value="${schoolPost.id}" type="hidden">
							<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
							<div class="row" >
							<div class="layui-form-item" >
										<div class="layui-inline">
													<label class="layui-form-label">起至时间:</label>
											<div class="layui-input-inline">
												<input type="text"  autocomplete="off"
													class="layui-input" name="schoolPostList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<fmt:formatDate value='${schoolPost.startTime}' pattern='yyyy年MM月'/>"
													disabled>
											</div>
											<div class="layui-form-mid">~</div>
											<div class="layui-input-inline">
												<input type="text" autocomplete="off"
													class="layui-input"  name="schoolPostList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<c:if test="${empty schoolPost.endTime}">至今</c:if><c:if test="${!empty schoolPost.endTime}"><fmt:formatDate value='${schoolPost.endTime}' pattern='yyyy年MM月'/></c:if>"
													disabled>
											</div>
										</div>
										</div>
							</div>
							<div class="row" >
											<div class="layui-form-item">
												<label class="layui-form-label">职务名称:</label>
												<div class="layui-input-block" >
													<input type="text" autocomplete="off" class="layui-input"  name="schoolPostList.postName"
														value="${schoolPost.postName }" disabled>
												</div>
											</div>
									</div>
									<div class="row <c:if test="${empty schoolPost.describes}">inlineHidden isinit</c:if>" >
												<div class="layui-form-item">
											<label class="layui-form-label">职务描述:</label>
											<div class="layui-input-block">
												<textarea   name="schoolPostList.describes "
													class="layui-textarea" disabled>${schoolPost.describes }</textarea>
											</div>
										</div>
									</div>
							</div>
							</c:forEach>
							
						</div>
					</div>
					<!-- 校内职务 -->
						<!-- 培训经历  -->
					<div class="<c:if test="${fn:length(rb.trainList) == 0}">inlineHidden isinit</c:if>" style="" id="pxRecordDiv">
						<div data-toggle="collapse" class=" titleDiv" data-parent="pxRecordDiv" href="#pxRecord">
							培训经历&nbsp;
							<li class="fa fa-angle-double-down" ></li>
							<button type="button"  onclick="addRecord(this,event);"
								class="layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn inlineHidden isinit"
								onclick="" style="margin-right: 10px;">
								<li class="fa fa-plus"></li> &nbsp;新增
							</button>
						</div>
						<div class="collapse in" id="pxRecord">
						<c:forEach var="resumeTrain" items="${rb.trainList}">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
							<input type="hidden" value="${resumeTrain.id}" name="trainList.id">
							<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
							<div class="row" >
							<div class="layui-form-item" >
										<div class="layui-inline">
													<label class="layui-form-label">起至时间:</label>
											<div class="layui-input-inline">
												<input type="text"  autocomplete="off"
													class="layui-input"  name="trainList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<fmt:formatDate value='${resumeTrain.startTime}' pattern='yyyy年MM月'/>"
													disabled>
											</div>
											<div class="layui-form-mid">~</div>
											<div class="layui-input-inline">
												<input type="text"  autocomplete="off"
													class="layui-input" name="trainList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<c:if test="${empty resumeTrain.endTime}">至今</c:if><c:if test="${!empty resumeTrain.endTime}"><fmt:formatDate value='${resumeTrain.endTime}' pattern='yyyy年MM月'/></c:if>"
													disabled>
											</div>
										</div>
										</div>
							</div>
							<div class="row" >
											<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">培训机构:</label>
												<div class="layui-input-inline" style="max-width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="trainList.trainCompany"
														value="${resumeTrain.trainCompany }" disabled>
												</div>
											</div>
											<div class="layui-inline <c:if test="${empty resumeTrain.place}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">培训地点:</label>
												<div class="layui-input-inline" style="width: 400px;">
													<input type="text" autocomplete="off" class="layui-input" name="trainList.place"
														value="${resumeTrain.place }" disabled>
												</div>
											</div>
											</div>
									</div>
									<div class="row" >
											<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">培训课程:</label>
												<div class="layui-input-inline" >
													<input type="text" autocomplete="off" class="layui-input" name="trainList.trainName"
														value="${resumeTrain.trainName }" disabled>
												</div>
											</div>
											<div class="layui-inline <c:if test="${empty resumeTrain.certificate}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">所获证书:</label>
												<div class="layui-input-inline" style="width: 400px;">
													<input type="text" autocomplete="off" class="layui-input" name="trainList.certificate"
														value="${resumeTrain.certificate }" disabled>
												</div>
											</div>
											</div>
									</div>
									<div class="row <c:if test="${empty resumeTrain.describes}">inlineHidden isinit</c:if>" >
												<div class="layui-form-item">
											<label class="layui-form-label">培训描述:</label> 
											<div class="layui-input-block">
												<textarea  name="trainList.describes"
													class="layui-textarea" disabled>${resumeTrain.describes }</textarea>
											</div>
										</div>
									</div>
							</div>
							</c:forEach>
						</div>
						
					</div>
					<!-- 培训经历 -->
					
					<!-- 项目经验  -->
					<div class="<c:if test="${fn:length(rb.projectList) == 0}">inlineHidden isinit</c:if>" style="" id="projectRecordDiv">
						<div data-toggle="collapse" class=" titleDiv" data-parent="projectRecordDiv" href="#projectRecord">
							项目经验&nbsp;
							<li class="fa fa-angle-double-down" ></li>
							<button type="button"  onclick="addRecord(this,event);"
								class="layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn inlineHidden isinit"
								onclick="" style="margin-right: 10px;">
								<li class="fa fa-plus"></li> &nbsp;新增
							</button>
						</div>
						<div class="collapse in" id="projectRecord">
							<c:forEach var="project" items="${rb.projectList}">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
							<input type="hidden" value="${project.id}" name="projectList.id">
							<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
							<div class="row" >
							<div class="layui-form-item" >
										<div class="layui-inline">
													<label class="layui-form-label">起至时间:</label>
											<div class="layui-input-inline">
												<input type="text"  autocomplete="off"
													class="layui-input"  name="projectList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<fmt:formatDate value='${project.startTime}' pattern='yyyy年MM月'/>"
													disabled>
											</div>
											<div class="layui-form-mid">~</div>
											<div class="layui-input-inline">
												<input type="text" autocomplete="off"
													class="layui-input"  name="projectList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<c:if test="${empty project.endTime}">至今</c:if><c:if test="${!empty project.endTime}"><fmt:formatDate value='${project.endTime}' pattern='yyyy年MM月'/></c:if>"
													disabled>
											</div>
										</div>
										</div>
							</div>
							<div class="row" >
							<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">项目名称:</label>
												<div class="layui-input-inline"  style="width: 400px;">
													<input type="text" autocomplete="off" class="layui-input"  name="projectList.projectName"
														value="${project.projectName }" disabled>
												</div>
											</div>
											<div class="layui-inline <c:if test="${empty project.companyName}">inlineHidden isinit</c:if>">
												<label class="layui-form-label">所属公司:</label>
												<div class="layui-input-inline" style="width: 300px;">
													<input type="text" autocomplete="off" class="layui-input"  name="projectList.companyName"
														value="${project.companyName }" disabled>
												</div>
											</div>
											</div>
									</div>
									<div class="row <c:if test="${empty project.projectDescribe}">inlineHidden isinit</c:if>" >
												<div class="layui-form-item" style="margin-bottom:  2px;">
											<label class="layui-form-label">项目描述:</label>
											<div class="layui-input-block">
												<textarea    name="projectList.projectDescribe"
													class="layui-textarea" disabled>${project.projectDescribe }</textarea>
											</div>
										</div>
									</div>
									<div class="row <c:if test="${empty project.responsibilityDescribe}">inlineHidden isinit</c:if>" >
												<div class="layui-form-item">
											<label class="layui-form-label">责任描述:</label>
											<div class="layui-input-block">
												<textarea   name="projectList.responsibilityDescribe"
													class="layui-textarea" disabled>${project.responsibilityDescribe }</textarea>
											</div>
										</div>
									</div>
							</div>
							</c:forEach>
							</div>
						</div>
					<!-- 项目经验 -->
					
					<!-- 实践经验  -->
					<div class="<c:if test="${fn:length(rb.practiceList) == 0}">inlineHidden isinit</c:if>" style="" id="practicalRecordDiv">
						<div data-toggle="collapse" class=" titleDiv" data-parent="practicalRecordDiv" href="#practical">
							实践经验&nbsp;
							<li class="fa fa-angle-double-down" ></li>
							<button type="button"  onclick="addRecord(this,event);"
								class="layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn inlineHidden isinit"
								 style="margin-right: 10px;">
								<li class="fa fa-plus"></li> &nbsp;新增
							</button>
						</div>
						<div class="collapse in" id="practical">
							<c:forEach var="practice" items="${rb.practiceList}">
							<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
							<input type="hidden" value="${practice.id}" name="practiceList.id">
							<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
							<div class="row" >
							<div class="layui-form-item" >
										<div class="layui-inline">
													<label class="layui-form-label">起至时间:</label>
											<div class="layui-input-inline">
												<input type="text" autocomplete="off"
													class="layui-input" name="practiceList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<fmt:formatDate value='${practice.startTime}' pattern='yyyy年MM月'/>"
													disabled>
											</div>
											<div class="layui-form-mid">~</div>
											<div class="layui-input-inline">
												<input type="text"  autocomplete="off"
													class="layui-input" name="practiceList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})"
													value="<c:if test="${empty practice.endTime}">至今</c:if><c:if test="${!empty practice.endTime}"><fmt:formatDate value='${practice.endTime}' pattern='yyyy年MM月'/></c:if>"
													disabled>
											</div>
										</div>
										</div>
							</div>
							<div class="row" >
							<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">实践名称:</label>
												<div class="layui-input-inline"  style="width: 400px;">
													<input type="text" autocomplete="off" class="layui-input" name="practiceList.practiceName"
														value="${practice.practiceName }" disabled>
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">实践单位:</label>
												<div class="layui-input-inline" style="width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="practiceList.practiceCompany"
														value="${practice.practiceCompany }" disabled>
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">实践岗位:</label>
												<div class="layui-input-inline" style="width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="practiceList.practicePosition"
														value="${practice.practicePosition }" disabled>
												</div>
											</div>
											</div>
									</div>
									<div class="row <c:if test="${empty practice.practiceDescribe}">inlineHidden isinit</c:if>" >
												<div class="layui-form-item">
											<label class="layui-form-label">实践描述:</label>
											<div class="layui-input-block">
												<textarea   name="practiceList.practiceDescribe"
													class="layui-textarea" disabled>${practice.practiceDescribe }</textarea>
											</div>
										</div>
									</div>
							</div>
							</c:forEach>
						</div>
					</div>
					<!-- 实践经验 -->
					</form>
				</div>
				<div class="layui-tab-item" id="gtNR">
				<!-- 	<div class="titleDiv" ><div style="float: left;">第一轮面试:</div><div style="float: right;margin-right: 20px;">得分：4.4</div></div>
					<table class="gtTable">
					<tr><td>面试官:</td><td>xiazhenyou</td></tr>
					<tr><td>面试时间:</td><td>xiazhenyou</td></tr>
					<tr><td>面试记录:</td><td >dddddd</td></tr>
					<tr><td>面试结论:</td><td>dddddd</td></tr>
					<tr><td>面试打分:</td><td>dddddd</td></tr>
					</table> -->
					</div>
				</div>
			</div>
		</div>
		</div>
	<script type="text/javascript" src="<%=basePath %>/static/nologin/jl_desc.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson2.js"></script>
	<link href="<%=basePath%>/static/recruitment/acceptance/css/jl_desc.css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.qrcode.min.js"></script> 
    <script type="text/javascript">
    </script>
</body>
<!-- 语言能力复制html专用区域 -->
<div id="copy_language" style="display: none;">
	<div class="row">
		<input type="hidden" value="" name="languageList.id">
		<div class="layui-inline">
			<label class="layui-form-label">语言类型:</label>
			<div class="layui-input-inline">
				<input type="text" name="languageList.name" class="layui-input"
					placeholder="语言类型" >
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">熟练程度:</label>
			<div class="layui-input-inline">
				<input type="text" name="languageList.describes" class="layui-input"
					placeholder="语言熟练度" >
			</div>
		</div>
		<div class="layui-inline  inlineHidden"  style="margin-left: 10px;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除
							</button></div>
		<%-- 	<div class="layui-inline <c:if test="${empty resumeMajor.useTime}">inlineHidden isinit</c:if>">
										<label class="layui-form-label">等级证书:</label>
										<div class="layui-input-inline">
											<a href="#" target="_self">点击查看</a>
										</div>
									</div> --%>
	</div>
</div>

<!-- 专业技能复制html专用区域 -->
<div id="copy_collapsemajor" style="display: none;">
							<div class="row">
									<input type="hidden" value="" name="resumeMajorList.id">
									<div class="layui-inline">
										<label class="layui-form-label">技能名称:</label>
										<div class="layui-input-inline">
											<input type="text" name="resumeMajorList.skillName" class="layui-input"
												placeholder="请输入技能名称" value="" >
										</div>
									</div>
									<div class="layui-inline ">
										<label class="layui-form-label">熟练程度:</label>
										<div class="layui-input-inline">
											<input type="text" name="resumeMajorList.masterDegree" class="layui-input"
												placeholder="请输入熟练程度" value="" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">使用时间:</label>
										<div class="layui-input-inline">
											<input type="text" name="resumeMajorList.useTime" class="layui-input"
												placeholder="请输入使用时间" value="" >
										</div>
									</div>
									<div class="layui-inline  inlineHidden"  style="margin-left: 10px;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除
							</button></div>
								</div>
</div>

<!-- 实践经验复制html专用区域 -->
<div id="copy_practical" style="display: none;">
	<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
		<input type="hidden" value="" name="practiceList.id">
		<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
		<div class="row">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">起至时间:</label>
					<div class="layui-input-inline">
						<input type="text" placeholder="yyyy年mm月" autocomplete="off"
							class="layui-input" name="practiceList.startTime"
							onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
					</div>
					<div class="layui-form-mid">~</div>
					<div class="layui-input-inline">
						<input type="text" placeholder="yyyy年mm月" autocomplete="off"
							class="layui-input" name="practiceList.endTime"
							onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">实践名称:</label>
					<div class="layui-input-inline" style="width: 400px;">
						<input type="text" autocomplete="off" class="layui-input"
							name="practiceList.practiceName" >
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">实践单位:</label>
					<div class="layui-input-inline" style="width: 300px;">
						<input type="text" autocomplete="off" class="layui-input"
							name="practiceList.practiceCompany">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">实践岗位:</label>
					<div class="layui-input-inline" style="width: 300px;">
						<input type="text" autocomplete="off" class="layui-input"
							name="practiceList.practicePosition">
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="layui-form-item">
				<label class="layui-form-label">实践描述:</label>
				<div class="layui-input-block">
					<textarea  placeholder="实践描述"
						name="practiceList.practiceDescribe" class="layui-textarea" ></textarea>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 项目经验复制html专用区域 -->
<div id="copy_projectRecord" style="display: none;">
	<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
							<input type="hidden" value="" name="projectList.id">
							<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
							<div class="row" >
							<div class="layui-form-item" >
										<div class="layui-inline">
													<label class="layui-form-label">起至时间:</label>
											<div class="layui-input-inline">
												<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input"  name="projectList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
											</div>
											<div class="layui-form-mid">~</div>
											<div class="layui-input-inline">
												<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input"  name="projectList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
											</div>
										</div>
										</div>
							</div>
							<div class="row" >
							<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">项目名称:</label>
												<div class="layui-input-inline"  style="width: 400px;">
													<input type="text" autocomplete="off" class="layui-input"  name="projectList.projectName">
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">所属公司:</label>
												<div class="layui-input-inline" style="width: 300px;">
													<input type="text" autocomplete="off" class="layui-input"  name="projectList.companyName">
												</div>
											</div>
											</div>
									</div>
									<div class="row" >
												<div class="layui-form-item" style="margin-bottom:  2px;">
											<label class="layui-form-label">项目描述:</label>
											<div class="layui-input-block">
												<textarea  placeholder="项目描述"  name="projectList.projectDescribe"
													class="layui-textarea" ></textarea>
											</div>
										</div>
									</div>
									<div class="row" >
												<div class="layui-form-item">
											<label class="layui-form-label">责任描述:</label>
											<div class="layui-input-block">
												<textarea  placeholder="责任描述"  name="projectList.responsibilityDescribe"
													class="layui-textarea" ></textarea>
											</div>
										</div>
									</div>
							</div>
</div>

<!-- 培训经历复制html专用区域 -->
<div id="copy_pxRecord" style="display: none;">
<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
							<input type="hidden" value="" name="trainList.id">
							<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
							<div class="row" >
							<div class="layui-form-item" >
										<div class="layui-inline">
													<label class="layui-form-label">起至时间:</label>
											<div class="layui-input-inline">
												<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input"  name="trainList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
											</div>
											<div class="layui-form-mid">~</div>
											<div class="layui-input-inline">
												<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input" name="trainList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
											</div>
										</div>
										</div>
							</div>
							<div class="row" >
											<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">培训机构:</label>
												<div class="layui-input-inline" style="max-width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="trainList.trainCompany">
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">培训地点:</label>
												<div class="layui-input-inline" style="width: 400px;">
													<input type="text" autocomplete="off" class="layui-input" name="trainList.place">
												</div>
											</div>
											</div>
									</div>
									<div class="row" >
											<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">培训课程:</label>
												<div class="layui-input-inline" >
													<input type="text" autocomplete="off" class="layui-input" name="trainList.trainName">
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">所获证书:</label>
												<div class="layui-input-inline" style="width: 400px;">
													<input type="text" autocomplete="off" class="layui-input" name="trainList.certificate">
												</div>
											</div>
											</div>
									</div>
									<div class="row" >
												<div class="layui-form-item">
											<label class="layui-form-label">培训描述:</label> 
											<div class="layui-input-block">
												<textarea placeholder="培训描述" name="trainList.describes"
													class="layui-textarea" ></textarea>
											</div>
										</div>
									</div>
							</div>
</div>

<!-- 校内职务复制html专用区域 -->
<div  id="copy_schoolDuty" style="display: none;">
<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
							<input name="schoolPostList.id" value="" type="hidden">
							<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
							<div class="row" >
							<div class="layui-form-item" >
										<div class="layui-inline">
													<label class="layui-form-label">起至时间:</label>
											<div class="layui-input-inline">
												<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input" name="schoolPostList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
											</div>
											<div class="layui-form-mid">~</div>
											<div class="layui-input-inline">
												<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input"  name="schoolPostList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
											</div>
										</div>
										</div>
							</div>
							<div class="row" >
											<div class="layui-form-item">
												<label class="layui-form-label">职务名称:</label>
												<div class="layui-input-block" >
													<input type="text" autocomplete="off" class="layui-input"  name="schoolPostList.postName">
												</div>
											</div>
									</div>
									<div class="row" >
												<div class="layui-form-item">
											<label class="layui-form-label">职务描述:</label>
											<div class="layui-input-block">
												<textarea  placeholder="职务描述" name="schoolPostList.describes "
													class="layui-textarea" ></textarea>
											</div>
										</div>
									</div>
							</div>
</div>
<!-- 教育经历复制html专用区域 -->
<div id="copy_schoolRecord" style="display: none;">
	<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
									<input type="hidden" name="resumeEduList.id" value="">
									<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
									<div class="row">
										<div class="layui-form-item" >
										<div class="layui-inline">
													<label class="layui-form-label">起至时间:</label>
											<div class="layui-input-inline">
												<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input" name="resumeEduList.startTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
											</div>
											<div class="layui-form-mid">~</div>
											<div class="layui-input-inline">
												<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input" name="resumeEduList.endTime"
													onclick="layui.laydate({elem: this,istime: true, format: 'YYYY年MM月'})">
											</div>
										</div>
										</div>
									</div>
							<div class="row" >
											<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">学校</label>
												<div class="layui-input-inline" style="max-width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeEduList.schoolName">
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">专业</label>
												<div class="layui-input-inline" style="max-width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeEduList.major">
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">学历</label>
												<div class="layui-input-inline" style="max-width: 300px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeEduList.education">
												</div>
											</div>
											</div>
									</div>
										<div class="row" >
												<div class="layui-form-item">
											<label class="layui-form-label">学历描述:</label>
											<div class="layui-input-block">
												<textarea  placeholder="学历描述"
													class="layui-textarea" ></textarea>
											</div>
										</div>
									</div>
							</div>
</div>
<!-- 工作经历复制html专用区域 -->
<div  id="copy_workRecord" style="display: none;">
		<div class="row" style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 10px;">
								<input type="hidden" name="resumeWorkList.id" value="">
									<div class="row  inlineHidden"   style="text-align: right;"><button type="button"  
								class="layui-btn layui-btn-mini layui-btn-danger"
								 style="margin-right: 10px;"  onclick="removeRecord(this);">
								<li class="fa fa-times-circle-o"></li> &nbsp;删除记录
							</button></div>
									<div class="row">
										<div class="layui-form-item" >
										<div class="layui-inline">
												<label class="layui-form-label">工作时间:</label>
												<div class="layui-input-inline" style="width: 100px;">
        												<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input" name="resumeWorkList.startTime"
													onclick="layui.laydate({elem: this, format: 'YYYY年MM月'})">
      											</div>
      											 <div class="layui-form-mid">~</div>
      											 <div class="layui-input-inline" style="width: 100px;">
        													<input type="text" placeholder="yyyy年mm月" autocomplete="off"
													class="layui-input"  name="resumeWorkList.endTime"
													onclick="layui.laydate({elem: this,format: 'YYYY年MM月'})">
      											</div>
										</div>
										</div>
										</div>
										<div class="row">
											<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">所在公司</label>
												<div class="layui-input-inline" style="width: 400px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.companyName">
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">公司类型</label>
												<div class="layui-input-inline" >
														<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.companyScale">
												</div>
											</div>
											</div>
										</div>
									<div class="row">
										<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">部门:</label>
												<div class="layui-input-inline" style="width: 200px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.department">
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">职位</label>
												<div class="layui-input-inline" style="width: 200px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.position">
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">薪资待遇:</label>
												<div class="layui-input-inline"  style="width: 200px;">
													<input type="text" autocomplete="off" class="layui-input" name="resumeWorkList.salary">
												</div>
											</div>
										</div>
									</div>
									<div class="row" >
										<div class="layui-form-item"  style="margin-bottom:  2px;">
											<label class="layui-form-label">工作描述:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入工作描述" name="resumeWorkList.describes"
													class="layui-textarea" ></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="layui-form-item">
											<label class="layui-form-label">离职原因:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入离职原因" name="resumeWorkList.leaveReason"
													class="layui-textarea" ></textarea>
											</div>
										</div>
									</div>
								</div>
	</div>
</html>