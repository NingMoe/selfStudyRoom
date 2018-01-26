<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	   <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
	   <link href="<%=basePath%>/static/common/main.css" rel="stylesheet">
<style type="text/css">
.titleDiv {
	color: white;
	padding-left: 10px;
	height: 30px;
	line-height: 30px;
	background: #99CCCC;
	border-bottom: 1px solid #eee;
}

.row {
	margin-top: 5px;
	margin-bottom: 5px;
}

.layui-form-label {
	width: 110px !important;
}

.layui-form-select {
	width: 165px !important;
}

#collapseOne input{
	width:165px;
}

.layui-input-inline {
	margin-bottom: 5px;
}

.linktitle {
	margin-bottom: 0px;
	border-bottom: 1px solid #34A8FF;
	color: #34A8FF;
	background: #F2F2F2;
	padding-top: 5px;
	padding-left: 5px;
}

.linkdesc {
	padding-bottom: 5px;
	padding-left: 5px;
	margin-bottom: 5px;
	background: #F2F2F2;
}

.layui-disabled {
	color: black !important;
	background: #eee;
}

.input-disabled {
	background: #eee;
}

.inlineHidden {
	display: none;
}

.disnone {
	display: none;
}

.titleDiv_Btn {
	margin-top: 4px;
	float: right;
}

.layui-upload-button {
	font-size: 12px;
	line-height: 22px;
	height: 22px;
	background-color: #1E9FFF;
	border: 0px;
	color: white;
}

.layui-upload-icon {
	margin: 0 5px;
}

.layui-upload-icon i {
	color: white;
	font-size: 14px;
}

.layui-upload-button:hover {
	opacity: 0.8;
	border: 0px;
	color: white;
}

.selected {
	color: red;
}
.container{
width: 100%;
}
</style>
<body >
	<div class="appForm">
		<div class="" style="" id="serachFrom">
			<div data-toggle="collapse" class=" container titleDiv " data-parent="serachFrom"
				href="#collapseOne">
				个人信息&nbsp; 
				<li class="fa fa-angle-double-down" id="ic"></li>
				<button type="button" id="reEdit" 
								class="layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn" onclick="cancelFrom(event);" style="margin-right: 15px;display: none;" >
								<li class="fa fa-reply"></li> &nbsp; 取消编辑
							</button>
				<button type="button" id="edit"
								class="layui-btn layui-btn-mini layui-btn-primary titleDiv_Btn" onclick="editFrom(event);" style="margin-right: 10px;">
								<li class="fa fa-pencil"></li> &nbsp;编辑
							</button>
								<button type="button" id="sub" 
								class="layui-btn layui-btn-mini layui-btn-normal titleDiv_Btn"  style="margin-right: 10px;display: none;">
								<li class="fa fa-check"></li> &nbsp;保存
							</button>
			</div>
			<div class="container collapse in" id="collapseOne">
			<form class="layui-form" id="editForm" action="" method="post" >
			<input type="hidden" id="apId" value="${rs.id}">
			<input type="hidden" id="totel" value="${rs. phone}">
			<input type="hidden" id="headUrl" value="${rs.headUrl}">
				<div class="row">
					<div class="col-md-2" style="padding: 10px;text-align: center;">
						<div class="row">
						<img style="width: 120px;height: 168px;margin: 0 auto;"  id="file1Img"  
						<c:if  test="${empty rs.headUrl}">
						 src="<%=basePath%>/static/common/images/hxr_photo.jpg"</c:if><c:if  test="${!empty rs.headUrl}">
						 <c:if test="${fn:startsWith(rs.headUrl, 'http')}"> src="${rs.headUrl }"</c:if>
						  <c:if test="${!fn:startsWith(rs.headUrl, 'http')}"> src="${filepath }${rs.headUrl }"</c:if>
						</c:if>/>
						</div>
						<div class="row">
						<div class="layui-input-inline  inlineHidden isinit" >
							<input type="file"  lay-type="images" id="file1" name="file1" 
						class="layui-upload-file" lay-title="上传头像" style="width:50px;"  disabled>
						<!-- 	<button type="button"
								class="layui-btn layui-btn-mini layui-btn-normal"
								>
								<li class="fa fa-cloud-upload"></li> &nbsp;快传
							</button> -->
							</div>
						</div>
					</div>
					<div class="col-md-10">
						<div class="row">
							<div class="layui-inline <c:if test="${empty rs.name}">inlineHidden isinit</c:if>" >
								<label class="layui-form-label">姓名:</label>
								<div class="layui-input-inline">
									<input type="text" name="name" lay-verify="required" class="layui-input"  placeholder="请输入姓名" autocomplete="off" value="${rs.name}"  disabled>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs.sex}">inlineHidden isinit</c:if>"  >
								<label class="layui-form-label">性别:</label>
								<div class="layui-form layui-input-inline" >
									<select name="sex" disabled>
											<option value="">选择性别</option>
											<option value="F" <c:if test="${rs.sex eq 'F'}">selected</c:if>>女</option>
											<option value="M" <c:if test="${rs.sex eq 'M'}">selected</c:if>>男</option>
										</select>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs. birthDate}">inlineHidden isinit</c:if>"  >
								<label class="layui-form-label">出生日期:</label>
								<div class="layui-input-inline">
									<input type="text" name="birthDate" lay-verify="date"
										placeholder="yyyy-mm-dd" autocomplete="off"
										class="layui-input" onclick="layui.laydate({elem: this})" value="${rs. birthDate}"  disabled>
								</div>
							</div>
						<!-- </div>
						<div class="row"> -->
							<div class="layui-inline  <c:if test="${empty rs. graSchool}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">学校:</label>
								<div class="layui-input-inline">
									<input type="text" name="graSchool" class="layui-input"  value="${rs. graSchool}" disabled>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs. major}">inlineHidden isinit</c:if>"  >
								<label class="layui-form-label">专业:</label>
								<div class="layui-input-inline">
									<input type="text" name="major" class="layui-input"  value="${rs. major}" disabled>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs.highEdu}">inlineHidden isinit</c:if>">
								<label class="layui-form-label" style="width: 90px;">学历:</label>
								<div class="layui-form layui-input-inline">
									<select name="highEdu" disabled>
										<option value="">选择学历</option>
										<c:forEach var="de" items="${degrees }">
											<option value="${de.name }"  <c:if test="${de.name eq rs.highEdu}">selected="selected"</c:if>>${de.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						<!-- </div>

						<div class="row"> -->
							<div class="layui-inline <c:if test="${empty rs.graduationDate}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">毕业时间:</label>
								<div class="layui-input-inline">
									<input type="text" name="graduationDate" lay-verify="date"
										placeholder="yyyy-mm-dd" autocomplete="off"
										class="layui-input" onclick="layui.laydate({elem: this})"
										class="layui-input" value="${rs. graduationDate}" disabled>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs.phone}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">手机号:</label>
								<div class="layui-input-inline">
									<input type="text" name="phone" lay-verify="required|phone"
										class="layui-input" value="${rs. phone}" disabled>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs.standbyPhone}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">备用号码:</label>
								<div class="layui-input-inline">
									<input type="text" name="standbyPhone" lay-verify="phone"
										class="layui-input" value="${rs. standbyPhone}" disabled>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs.email}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">邮箱:</label>
								<div class="layui-input-inline">
									<input type="text" name="email" lay-verify="email"
										class="layui-input"  value="${rs. email}" disabled>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs.idCardNo}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">身份证号:</label>
								<div class="layui-input-inline">
									<input type="text" name="idCardNo" lay-verify="identity|"
										class="layui-input" value="${rs. idCardNo}" disabled>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs.deliveryDate}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">投递日期:</label>
								<div class="layui-input-inline">
									<input type="text" name="deliveryDate" lay-verify="date"
										placeholder="yyyy-mm-dd  hh:mm:ss" autocomplete="off"
										class="layui-input" onclick="layui.laydate({elem: this,istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
										class="layui-input"  value="${rs. deliveryDate}" disabled>
								</div>
							</div>
						
							<div class="layui-inline <c:if test="${empty rs.locationCity}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">城市:</label>
								<div class="layui-form layui-input-inline">
									<select name="locationCity" id="locationCity" disabled lay-search>
										<option value="">选择城市</option>
										<c:forEach var="area" items="${areaInfo }">
											<option value="${area.areaName }"  <c:if test="${rs.locationCity eq area.areaName}">selected</c:if>>${area.areaName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs.workTime}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">工作年限:</label>
								<div class="layui-input-inline">
									<input type="text" name="workTime" class="layui-input" value="${rs. workTime}" disabled>
								</div>
							</div>
								<div class="layui-inline <c:if test="${empty rs.eduType}">inlineHidden isinit</c:if>">
									<label class="layui-form-label" style="width: 90px;">学历类型:</label>
									<div class="layui-form layui-input-inline">
										<select name="eduType" disabled>
											<option value="">学历类型</option>
											<c:forEach var="eduType" items="${eduTypeList }">
												<option value="${eduType.name }" <c:if test="${rs.eduType eq eduType.name}">selected</c:if>>${eduType.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
						<%-- 	<div class="layui-inline <c:if test="${empty rs.recom}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">推荐人:</label>
								<div class="layui-input-inline">
									<input type="text" name="recom" class="layui-input" value="${rs. recom}" disabled>
								</div>
							</div>
							<div class="layui-inline <c:if test="${empty rs.recomRelation}">inlineHidden isinit</c:if>">
								<label class="layui-form-label">推荐人关系:</label>
								<div class="layui-input-inline">
									<input type="text"  name="recomRelation" class="layui-input" value="${rs. recomRelation}" disabled>
								</div>
							</div> --%>
						</div>
					</div>
				</div>
			</div>
			<button type="button" class="layui-btn " lay-submit="" lay-filter="editApp"  id="editButton" style="display: none;">编辑</button>
			</form>
		</div>
	</div>
<!-- 简历投递记录 -->	
	<div class="" style="" id="jltable">
			<div data-toggle="collapse" class=" container titleDiv" data-parent="jltable"
				href="#collapsejl">
				简历投递记录&nbsp;
				<li class="fa fa-angle-double-down" id="jl"></li>
			</div>
			<div class="container collapse in" id="collapsejl">
				<div class="row">
				<table class="tableList" id="jlList">
				</table>
			</div>
			</div>
		</div>
	</div>
	<!-- 沟通记录 -->
	<div class="" style="" id="jlinktable">
			<div data-toggle="collapse" class="container titleDiv" data-parent="jlinktable"
				href="#collapsejlink">
				沟通记录&nbsp;
				<li class="fa fa-angle-double-down" id="jlink"></li>
				<button type="button"   onclick="sendMsgGT(event);"
								class="layui-btn layui-btn-mini layui-btn-normal titleDiv_Btn"  style="margin-right: 10px;">
								<li class="fa fa-comments-o"></li>&nbsp;发送短信
							</button>
			</div>
			<div class="container collapse in" id="collapsejlink">
			<div class="layui-tab layui-tab-card row">
				<ul class="layui-tab-title">
					<li class="layui-this">沟通主题</li>
					<li>短信记录<font style="color: red;" id="smsCount"></font></li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<table class="tableList" id="linkList">
						</table>
						<div class="titleDiv"  style="margin: 10px 0px;">快速新增:
						</div>
						<div class="layui-form-item">
						<form class="layui-form"   id="quickForm" action="" method="post" >
						<input type="hidden" id="recordId" value="">
						<div class="layui-inline">
								<label class="layui-form-label">沟通主题:</label>
								<div class="layui-input-inline">
									<input type="text" name="addTopIc"  placeholder="请输入沟通主题" autocomplete="off"
										class="layui-input"  lay-verify="required">
								</div>
							</div>
						<div class="layui-inline">
								<label class="layui-form-label" style="width:120px !important;">下次跟进时间:</label>
								<div class="layui-input-inline">
									<input type="text" name="addNextTime"  placeholder="yyyy-mm-dd  hh:mm" autocomplete="off"
										class="layui-input" onclick="layui.laydate({elem: this,istime: true,min: laydate.now(),format:'YYYY-MM-DD hh:mm'})" class="layui-input" >
								</div>
							</div>
							<div class="layui-inline" style="">
							<button type="button"  lay-submit="" lay-filter="quickAdd" class="layui-btn  layui-btn-normal layui-btn-small">
								<li class="fa fa-plus-circle"></li> &nbsp;保存
							</button>
						</div>
						<div class="layui-form-item layui-form-text">
								<label class="layui-form-label" style="">沟通内容:</label>
								<div class="layui-input-block">
									<textarea placeholder="请输入内容" class="layui-textarea" lay-verify="required" id="addComment"></textarea>
								</div>
						</div>
						</form>
						</div>
						<div class="layui-form-item"  id="linkDesc" style="margin-top: 10px; "></div>
					</div>
					<div class="layui-tab-item" id="smsDesc">
					</div>
				</div>
			</div>
			<!-- <div class="row">
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">内容1</div>
					<div class="layui-tab-item">
						<table class="tableList" id="linkList">
						</table>
					</div>
				</div>
			</div> -->
		</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>/static/recruitment/acceptance/applicant_desc.js"></script>
    <script type="text/javascript">
    </script>
</body>
</html>