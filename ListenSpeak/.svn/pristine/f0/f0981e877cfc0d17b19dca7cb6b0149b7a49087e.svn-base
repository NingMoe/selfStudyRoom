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
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<link rel="stylesheet" href="<%=basePath %>/static/studentpc/css/less.css">
</head>
<body>
<style>
.workover{
text-align:left;width:100%;
}
tbody>tr{
height:40px;
line-height:40px;
}
</style>
<div class="public-homework">
	<div class="homework-top">
		<p><span>进行中的考试</span></p>
		<c:if test="${not_end_test.flag && not_end_test.list ne null }">
			<c:forEach items="${not_end_test.list }" var="not_end_test">
				<div class="worktop-main">
					<div class="main-titlename">
						<img src="<%=basePath %>/static/studentpc/images/13_03.jpg" alt="">
						<p>${not_end_test.test_name }</p>
						<p class="main-man">发布人：${not_end_test.create_user }</p>
						<div class="clearfix"></div>
					</div>
					<div class="main-work">
						<table class='table table2'>
							<tr>
								<td>题量</td>
								<td>截止时间</td>
							</tr>
							<tr>
								<td>
									<img src="<%=basePath %>/static/studentpc/images/13_03.png" alt="">
								</td>
								<td>
									<img src="<%=basePath %>/static/studentpc/images/13_03.png" alt="">
								</td>
								<td>
									<input type="button" class="startKs" value="开始考试">
									<input type="hidden" name="student_id" value="${not_end_test.student_id }">
									<input type="hidden" name="class_code" value="${not_end_test.class_code }">
									<input type="hidden" name="test_id" value="${not_end_test.test_id }">
									<input type="hidden" name="paper_id" value="${not_end_test.paper_id }">
									<input type="hidden" name="student_test_id" value="${not_end_test.id }">
									<input type="hidden" name="test_end_num" value="${not_end_test.test_end_num }">
								</td>
							</tr>
							<tr>
								<td>${not_end_test.dati_num }</td>
								<td><fmt:formatDate value="${not_end_test.end_time }" pattern="yyyy年MM月dd日"/></td>
							</tr>
						</table>
						<div class="clearfix"></div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<div class="homework-table">
		<p class="workover"><span>已完成考试</span></p>
		<table class="layui-table" lay-even="" lay-skin="nob" style="margin-top:20px">
			<thead>
				<th>考试名称</th>
				<th>提交时间</th> 
				<th>总分</th>
				<th>准确</th>
				<th>完整</th>
				<th>流利</th>
				<th>查看</th>
			</thead>
			<tbody>
				<c:if test="${end_test.flag && end_test.list ne null }">
					<c:forEach items="${end_test.list }" var="ent_test">
						<tr>						
							<td>${ent_test.test_name }</td>
							<td><fmt:formatDate value="${ent_test.submission_time }" pattern="yyyy年MM月dd日"/></td>
							<c:if test="${ent_test.overall_teacher ne null }">
								<td>${ent_test.overall_teacher }</td>
							</c:if>
							<c:if test="${ent_test.overall_teacher eq null }">
								<td>${ent_test.overall_sogou }</td>
							</c:if>
							
							<c:if test="${ent_test.accuracy_teacher ne null }">
								<td>${ent_test.accuracy_teacher }</td>
							</c:if>
							<c:if test="${ent_test.accuracy_teacher eq null }">
								<td>${ent_test.accuracy_sogou }</td>
							</c:if>
							
							<c:if test="${ent_test.integrity_teacher ne null }">
								<td>${ent_test.integrity_teacher }</td>
							</c:if>
							<c:if test="${ent_test.integrity_teacher eq null }">
								<td>${ent_test.integrity_sogou }</td>
							</c:if>
							
							<c:if test="${ent_test.fluency_teacher ne null }">
								<td>${ent_test.fluency_teacher }</td>
							</c:if>
							<c:if test="${ent_test.fluency_teacher eq null }">
								<td>${ent_test.fluency_sogou }</td>
							</c:if>
							<td style="cursor:pointer" onclick="toTestBaoGao('${ent_test.test_id }','${ent_test.class_code }')">查看</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript" src="<%=basePath %>/static/studentpc/js/studenttest.js"></script>
</body>
</html>