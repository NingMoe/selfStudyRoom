<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="height: 100%;">
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<link rel="stylesheet" href="<%=basePath %>/static/studentpc/css/less.css">
<script type="text/javascript">
var jsonarray = ${student_growth.list};
var datalist = [];
var datatimelist = [];
var sourcelist = [];
var sourcealllist = [];
var zuoyenumalllist = [];
$.each(jsonarray,function(index, sg){
	//alert(sg.rtime +"###" + formatDate(sg.rtime) + "###" + sg.overall + "###" + sg.overall_all + "###" + sg.zuoye_num_all);
	datalist.push(sg.rtime);
	datatimelist.push(formatDate(sg.rtime));
	sourcelist.push(sg.overall);
	sourcealllist.push(sg.overall_all);
	zuoyenumalllist.push(sg.zuoye_num_all);
});

function formatDate(timestp) { 
	var time = new Date(timestp * 1000);
	var year=time.getFullYear(); 
	var month=time.getMonth()+1; 
	var date=time.getDate(); 
	/* var hour=time.getHours(); 
	var minute=time.getMinutes(); 
	var second=time.getSeconds();  */
	return year+"."+month+"."+date; 
} 
</script>
</head>
<body style="height: 100%;">
<div class="public-homework">
	<div class="homework-top">
		<p><span>进行中的作业</span></p>
		<c:if test="${not_end_zuoye.flag && not_end_zuoye.list ne null }">
			<c:forEach items="${not_end_zuoye.list }" var="zuoye">
				<div class="worktop-main">
					<div class="main-titlename">
						<img src="<%=basePath %>/static/studentpc/images/13_03.jpg" alt="">
						<p>${zuoye.zuoye_name }</p>
						<div class="clearfix"></div>
					</div>
					<div class="main-work">
						<table class='table'>
							<tr>
								<td>班级</td>
								<td>作业状态</td>
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
									<img src="<%=basePath %>/static/studentpc/images/13_03.png" alt="">
								</td>
								<td><input style="cursor:pointer" type="button" value="完成作业" onclick="toZuoyeView('${zuoye.zuoye_id}','${zuoye.class_code }')"></td>
							</tr>
							<tr>
								<td>${zuoye.class_name }</td>
								<td>完成度：${zuoye.zuoye_end_num }/${zuoye.dati_num }</td>
								<td><fmt:formatDate value="${zuoye.end_time }" pattern="yyyy年MM月dd日"/></td>
							</tr>
						</table>
						<div class="clearfix"></div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
		<div class="homework-table">
			<p class="workover"><span>进行中的作业</span></p>
			<table class="layui-table" lay-even="" lay-skin="nob" style="margin-top:20px">
				<thead>
					<th>作业名称</th>
					<th>提交时间</th>
					<th>总分</th>
					<th>准确</th>
					<th>完整</th>
					<th>流利</th>
					<th>作业查看</th>
				</thead>
				<tbody>
					<c:if test="${end_zuoye.flag && end_zuoye.list ne null }">
						<c:forEach items="${end_zuoye.list }" var="zuoye">
							<tr>
								<td>${zuoye.zuoye_name }</td>
								<td><fmt:formatDate value="${zuoye.end_time }" pattern="yyyy年MM月dd日"/></td>
								<c:if test="${zuoye.overall_teacher ne null }">
									<td>${zuoye.overall_teacher }</td>
								</c:if>
								<c:if test="${zuoye.overall_teacher eq null }">
									<td>${zuoye.overall_sogou }</td>
								</c:if>
								
								<c:if test="${zuoye.accuracy_teacher ne null }">
									<td>${zuoye.accuracy_teacher }</td>
								</c:if>
								<c:if test="${zuoye.accuracy_teacher eq null }">
									<td>${zuoye.accuracy_sogou }</td>
								</c:if>
								
								<c:if test="${zuoye.integrity_teacher ne null }">
									<td>${zuoye.integrity_teacher }</td>
								</c:if>
								<c:if test="${zuoye.integrity_teacher eq null }">
									<td>${zuoye.integrity_sogou }</td>
								</c:if>
								
								<c:if test="${zuoye.fluency_teacher ne null }">
									<td>${zuoye.fluency_teacher }</td>
								</c:if>
								<c:if test="${zuoye.fluency_teacher eq null }">
									<td>${zuoye.fluency_sogou }</td>
								</c:if>
								<td style="cursor:pointer" onclick="toZuoyeBaoGao('${zuoye.zuoye_id }','${zuoye.class_code }')">查看</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="lookwork-curve">
			<div class="curve-top">
				<p>·&nbsp成长轨迹</p>
			</div>
			<div id="report" style="width: 100%; height: 100%;"></div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/echarts/echarts.3.5.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/studentpc/js/studentzuoye.js"></script>
</html>