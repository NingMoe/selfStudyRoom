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
	   <script src="<%=basePath%>/static/common/bootstrap/js/bootstrap.min.js"></script>
	   <link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	   <link href="<%=basePath%>/static/common/main.css" rel="stylesheet">
<style type="text/css">
.titleDiv {
	margin-left: 0px;
	color: white;
	padding-left: 10px;
	height: 30px;
	line-height: 30px;
	background: #99CCCC;
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
#collapseOne input{
	width:165px;
}
</style>
<body >
	<div class="appForm">
		<div class="" style="" id="serachFrom">
			<div data-toggle="collapse" class="titleDiv" data-parent="serachFrom"
				href="#collapseOne">
				个人信息&nbsp;
				<li class="fa fa-angle-double-down" id="ic"></li>
			</div>
			<div class="container collapse in" id="collapseOne">
			<form class="layui-form" id="addForm" action="" method="post" >
			<input type="hidden" name="headUrl">
				<div class="row">
					<div class="col-md-2" style="padding: 10px;text-align: center;">
						<div class="row">
						<img style="width: 120px;height: 168px;margin: 0 auto;"  id="file1Img"  
						 src="<%=basePath%>/static/common/images/hxr_photo.jpg""/>
						</div>
						<div class="row">
						<div class="layui-input-inline" >
							<input type="file"  lay-type="images" id="file1" name="file1" 
						class="layui-upload-file" lay-title="上传头像" style="width:50px">
							</div>
						</div>
					</div>
						<div class="col-md-10">
							<div class="row">
								<div class="layui-inline">
									<label class="layui-form-label">姓名:</label>
									<div class="layui-input-inline">
										<input type="text" name="name" lay-verify="required"
											class="layui-input" placeholder="请输入姓名" autocomplete="off">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">性别:</label>
									<div class="layui-form layui-input-inline">
										<select name="sex">
											<option value="">选择性别</option>
											<option value="F">女</option>
											<option value="M">男</option>
										</select>
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">出生日期:</label>
									<div class="layui-input-inline">
										<input type="text" name="birthDate" lay-verify="date"
											placeholder="yyyy-mm-dd" autocomplete="off"
											class="layui-input" onclick="layui.laydate({elem: this})">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="layui-inline">
									<label class="layui-form-label">学校:</label>
									<div class="layui-input-inline">
										<input type="text" name="graSchool" class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">专业:</label>
									<div class="layui-input-inline">
										<input type="text" name="major" class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label" style="width: 90px;">学历:</label>
									<div class="layui-form layui-input-inline">
										<select name="highEdu">
											<option value="">选择学历</option>
											<c:forEach var="de" items="${degrees }">
												<option value="${de.name }">${de.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="layui-inline">
									<label class="layui-form-label">毕业时间:</label>
									<div class="layui-input-inline">
										<input type="text" name="graduationDate" lay-verify="date"
											placeholder="yyyy-mm-dd" autocomplete="off"
											class="layui-input" onclick="layui.laydate({elem: this})"
											class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">手机号:</label>
									<div class="layui-input-inline">
										<input type="text" name="phone" lay-verify="required|phone"
											class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">备用号码:</label>
									<div class="layui-input-inline">
										<input type="text" name="standbyPhone" lay-verify="phone"
											class="layui-input">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="layui-inline">
									<label class="layui-form-label">邮箱:</label>
									<div class="layui-input-inline">
										<input type="text" name="email" lay-verify="email"
											class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">身份证号:</label>
									<div class="layui-input-inline">
										<input type="text" name="idCardNo" lay-verify="identity"
											class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">投递日期:</label>
									<div class="layui-input-inline">
										<input type="text" name="deliveryDate" lay-verify="date"
											placeholder="yyyy-mm-dd" autocomplete="off"
											class="layui-input" onclick="layui.laydate({elem: this})"
											class="layui-input">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="layui-inline">
									<label class="layui-form-label">城市:</label>
									<div class="layui-form layui-input-inline">
										<select name="locationCity" id="locationCity" lay-search>
											<option value="">选择城市</option>
											<c:forEach var="area" items="${areaInfo }">
												<option value="${area.areaName }">${area.areaName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">工作年限:</label>
									<div class="layui-input-inline">
										<input type="text" name="workTime" class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label" style="width: 90px;">学历类型::</label>
									<div class="layui-form layui-input-inline">
										<select name="eduType">
											<option value="">学历类型</option>
											<c:forEach var="eduType" items="${eduTypeList }">
												<option value="${eduType.name }">${eduType.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="layui-inline">
								<label class="layui-form-label">推荐人:</label>
								<div class="layui-input-inline">
									<input type="text" name="recom"  placeholder="推荐人邮箱前缀" class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">推荐人关系:</label>
								<div class="layui-input-inline">
									<input type="text"  name="recomRelation" class="layui-input">
								</div>
							</div>
						</div>
						<div class="row" style="text-align: center;">
							<button type="button" class="layui-btn "  lay-submit="" lay-filter="demo1">
								<li class="fa fa-check"></li> &nbsp;提交
							</button>
						</div>
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>/static/recruitment/acceptance/new_applicant.js"></script>
    <script type="text/javascript">
  
    </script>
</body>
</html>