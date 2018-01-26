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
var transcendCount = ${zuoye_info.transcendCount == null ? 0 : zuoye_info.transcendCount}
</script>
</head>
<body>
<div class="public-lookwork">
	<div class="lookwork-top">
		<p>学生姓名：<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null }">${zuoye_info.studentZuoye.student_name }</c:if></p>
		<p class="public-callback" onclick="goback();" style="cursor:pointer"><img src="<%=basePath %>/static/studentpc/images/17_04_03.png" alt="">返回</p>
		<div class="clearfix"></div>
		<div class="worktop2-main">
			<div class="wtmain-left">
				<p>·&nbsp作业概况</p>
				<p>
					<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null  && zuoye_info.studentZuoye.tj_status == 2}">本次作业由${zuoye_info.studentZuoye.teacher_name }老师布置，共${zuoye_info.studentZuoye.zuoye_num }题，作业准时提交</c:if>
					<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null  && zuoye_info.studentZuoye.tj_status == 3}">本次作业由${zuoye_info.studentZuoye.teacher_name }老师布置，共${zuoye_info.studentZuoye.zuoye_num }题，作业未准时提交</c:if>
				</p>
				<p>作业名称：<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null }">${zuoye_info.studentZuoye.zuoye_name }</c:if></p>
				<p>完成时间：<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null }"><fmt:formatDate value="${zuoye_info.studentZuoye.tj_tme }" pattern="yyyy年MM月dd日HH:mm"/></c:if></p>
			</div>
			<div class="wtmain-right">
				<p class="fankui">·&nbsp作业反馈</p>
				<div class="wtmain-bottom">
					<div class="wtmb-top">
						<div class="wtmbt-left">
							<p>原始得分：
								<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null }">
									<fmt:formatNumber type="number" value="${zuoye_info.studentZuoye.overall_teacher ne null ? zuoye_info.studentZuoye.zuoye_num * zuoye_info.studentZuoye.overall_teacher : zuoye_info.studentZuoye.zuoye_num * zuoye_info.studentZuoye.overall_sogou}" maxFractionDigits="0"/>/${zuoye_info.studentZuoye.zuoye_num * 10 }
								</c:if>
							</p>
							<p>预测模考得分：
								<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null }">
									<fmt:formatNumber type="number" value="${zuoye_info.studentZuoye.overall_teacher ne null ? zuoye_info.studentZuoye.overall_teacher : zuoye_info.studentZuoye.overall_sogou }" maxFractionDigits="0"/>/10
								</c:if>
							</p>
							<p>得分率：
								<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null }">
									<fmt:formatNumber type="number" value="${zuoye_info.studentZuoye.overall_teacher ne null ? zuoye_info.studentZuoye.overall_teacher * 10 : zuoye_info.studentZuoye.overall_sogou * 10}" maxFractionDigits="2"/>%
								</c:if>
							</p>
						</div>
						<div class="wtmbt-right">
							<div class="wtmbt-beat">
								<div class="wrap"> 
								    <div class="circle"> 
								        <div class="percent left"></div> 
								        <div class="percent right wth0">80%</div> 
								    </div> 
								    <div class="num"></div>
								</div>
								<p class="wtmbt-beat2">班内超过<br><fmt:formatNumber type="number" value="${zuoye_info.transcendCount == null ? 0 : zuoye_info.transcendCount * 100}" maxFractionDigits="2"/>%的学生</p>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="wtmbt-bottom">
						<div class="wtmbtb-z">
							<p>准确度</p>
							<div class="wtmbtb-jd1">
								<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null}">
									<p style="width:<fmt:formatNumber type="number" value="${zuoye_info.studentZuoye.accuracy_teacher ne null ? zuoye_info.studentZuoye.accuracy_teacher * 10 : zuoye_info.studentZuoye.accuracy_sogou * 10}" maxFractionDigits="2"/>%; background-image: url(<%=basePath %>/static/studentpc/images/17_03.jpg);">
										<fmt:formatNumber type="number" value="${zuoye_info.studentZuoye.accuracy_teacher ne null ? zuoye_info.studentZuoye.accuracy_teacher * 10 : zuoye_info.studentZuoye.accuracy_sogou * 10}" maxFractionDigits="2"/>%
									</p>
								</c:if>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="wtmbtb-l">
							<p>流利度</p>
							<div class="wtmbtb-jd2">
								<c:if test="${zuoye_info.flag && zuoye_info.studentZuoye ne null }">
									<p style="width:<fmt:formatNumber type="number" value="${zuoye_info.studentZuoye.fluency_teacher ne null ? zuoye_info.studentZuoye.fluency_teacher * 10 : zuoye_info.studentZuoye.fluency_sogou * 10}" maxFractionDigits="2"/>%; background-image: url(<%=basePath %>/static/studentpc/images/17_06.jpg);">
										<fmt:formatNumber type="number" value="${zuoye_info.studentZuoye.fluency_teacher ne null ? zuoye_info.studentZuoye.fluency_teacher * 10 : zuoye_info.studentZuoye.fluency_sogou * 10}" maxFractionDigits="2"/>%
									</p>
								</c:if>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="wtmbtb-w">
							<p>完整度</p>
							<div class="wtmbtb-jd3">
								<p style="width:<fmt:formatNumber type="number" value="${zuoye_info.studentZuoye.integrity_teacher ne null ? zuoye_info.studentZuoye.integrity_teacher * 10 : zuoye_info.studentZuoye.integrity_sogou * 10}" maxFractionDigits="2"/>%; background-image: url(<%=basePath %>/static/studentpc/images/17_08.png);">
									<fmt:formatNumber type="number" value="${zuoye_info.studentZuoye.integrity_teacher ne null ? zuoye_info.studentZuoye.integrity_teacher * 10 : zuoye_info.studentZuoye.integrity_sogou * 10}" maxFractionDigits="2"/>%
								</p>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="lookwork-main">
		<p>·&nbsp完成情况</p>
		<table class="layui-table" lay-even="" lay-skin="nob" style="margin:0;">
			<thead>
				<th>题号</th>
				<th>题型</th>
				<th>难度</th>
				<th>总分</th>
				<th>准确</th>
				<th>完整</th>
				<th>流利</th>
				<th>得分率</th>
			</thead>
			<tbody>
				<c:if test="${zuoye_info.flag && zuoye_info.answerlist ne null }">
					<c:forEach items="${zuoye_info.answerlist }" var="answer">
						<tr>
							<td>${answer.xh }</td>
							<td>${answer.name }</td>
							<td>
								${answer.difficulty == 'A' ? "简单" : (answer.difficulty == 'B' ? "中等" : "困难") }
							</td>
							<td>
								<fmt:formatNumber type="number" value="${answer.overall_teacher ne null ? answer.overall_teacher  : answer.overall_sogou }" maxFractionDigits="1"/>
							</td>
							<td>
								<fmt:formatNumber type="number" value="${answer.accuracy_teacher ne null ? answer.accuracy_teacher  : answer.accuracy_sogou }" maxFractionDigits="1"/>
							</td>
							<td>
								<fmt:formatNumber type="number" value="${answer.integrity_teacher ne null ? answer.integrity_teacher  : answer.integrity_sogou }" maxFractionDigits="1"/>
							</td>
							<td>
								<fmt:formatNumber type="number" value="${answer.fluency_teacher ne null ? answer.fluency_teacher  : answer.fluency_sogou }" maxFractionDigits="1"/>
							</td>
							<td><fmt:formatNumber type="number" value="${answer.overall_teacher ne null ? answer.overall_teacher * 10  : answer.overall_sogou * 10 }" maxFractionDigits="2"/>%</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="clearfix"></div>
	</div>
	<div class="lookwork-particular">
		<div class="particular-top">
			<p>·&nbsp作业详情</p>
		</div>
		<div>
			<c:if test="${zuoye_info.flag && zuoye_info.questionlist ne null}">
				<c:forEach items="${zuoye_info.questionlist }" var="qlist">
					<div class="lookwork-one">
						<div class="lwo-top">
							<p>第一题</p>
						</div>
						<div class="lwo-main">
							<div class="lwom-top">
								<p>题型：<span class="colorred">${qlist.tname }</span></p>
								<p>难度：<span class="colorred">${qlist.difficulty == 'A' ? "简单" : (qlist.difficulty == 'B' ? "中等" : "困难") }</span></p>
								<p>总分：
									<span class="colorred">
										<c:set value="0" var="sum" />
										<c:set value="0" var="count" />
										<c:forEach items="${qlist.answer_list }" var="alist">
											<c:set value="${sum + (alist.overall_teacher ne null ? alist.overall_teacher : alist.overall_sogou)}" var="sum" />
											<c:set value="${count + 1}" var="count" />
										</c:forEach>
										<fmt:formatNumber type="number" value="${sum/count }" maxFractionDigits="1"/>
									</span>
								</p>
								<p>准确：
									<span class="colorred">
										<c:set value="0" var="sum" />
										<c:set value="0" var="count" />
										<c:forEach items="${qlist.answer_list }" var="alist">
											<c:set value="${sum + (alist.accuracy_teacher ne null ? alist.accuracy_teacher : alist.accuracy_sogou)}" var="sum" />
											<c:set value="${count + 1}" var="count" />
										</c:forEach>
										<fmt:formatNumber type="number" value="${sum/count }" maxFractionDigits="1"/>
									</span>
								</p>
								<p>完整：
									<span class="colorred">
										<c:set value="0" var="sum" />
										<c:set value="0" var="count" />
										<c:forEach items="${qlist.answer_list }" var="alist">
											<c:set value="${sum + (alist.integrity_teacher ne null ? alist.integrity_teacher : alist.integrity_sogou)}" var="sum" />
											<c:set value="${count + 1}" var="count" />
										</c:forEach>
										<fmt:formatNumber type="number" value="${sum/count }" maxFractionDigits="1"/>
									</span>
								</p>
								<p>流利：
									<span class="colorred">
										<c:set value="0" var="sum" />
										<c:set value="0" var="count" />
										<c:forEach items="${qlist.answer_list }" var="alist">
											<c:set value="${sum + (alist.fluency_teacher ne null ? alist.fluency_teacher : alist.fluency_sogou)}" var="sum" />
											<c:set value="${count + 1}" var="count" />
										</c:forEach>
										<fmt:formatNumber type="number" value="${sum/count }" maxFractionDigits="1"/>
									</span>
								</p>
								<p>平均得分率：
									<span class="colorred">
										<c:set value="0" var="sum" />
										<c:set value="0" var="count" />
										<c:forEach items="${qlist.answer_list }" var="alist">
											<c:set value="${sum + (alist.overall_teacher ne null ? alist.overall_teacher : alist.overall_sogou)}" var="sum" />
											<c:set value="${count + 1}" var="count" />
										</c:forEach>
										<fmt:formatNumber type="number" value="${sum/count*10 }" maxFractionDigits="2"/>%
									</span>
								</p>
								<div class="clearfix"></div>
							</div>
							<c:if test="${qlist.type == '2'}">
								<p>
									${qlist.topic}题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干题干
								</p>
							</c:if>
							<c:forEach items="${qlist.answer_list }" var="alist">
								<div class="lwom-main">
									<p>
										评语：${alist.answer_comment }
									</p>
									<p>
										${alist.content }
									</p>
									<div class="lwom-bofang">
										<c:if test="${alist.student_answer ne null && alist.student_answer != ''}">
											<img onclick="bofang(this);" src="<%=basePath %>/static/studentpc/images/17_03.png" alt="">
											<audio controls="controls" src="<%=osspath %>${alist.student_answer }" style="display:none;"></audio>
											<p>播放你的答案</p>
											<div class="clearfix"></div>
										</c:if>
										<c:if test="${alist.student_answer eq null || alist.student_answer == ''}">
											<p>该题没有你的答案</p>
											<div class="clearfix"></div>
										</c:if>
									</div>
									<div class="lwom-bofang">
										<c:if test="${alist.parse_audio ne null && alist.parse_audio != ''}">
											<img onclick="bofang(this);" src="<%=basePath %>/static/studentpc/images/17_03.png" alt="">
											<audio controls="controls" src="<%=osspath %>${alist.parse_audio }" style="display:none;"></audio>
											<p>播放解析</p>
											<div class="clearfix"></div>
										</c:if>
										<c:if test="${alist.parse_audio eq null || alist.parse_audio == ''}">
											<p>该题没有解析</p>
											<div class="clearfix"></div>
										</c:if>
									</div>
									<div class="clearfix"></div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/studentpc/js/studentzuoyebaogao.js"></script>
</html>