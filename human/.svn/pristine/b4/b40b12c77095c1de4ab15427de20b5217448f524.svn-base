<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="width: 100%; background-color: #F0F0F0;">
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<title>新东方入班水平测试</title>
<link rel="stylesheet"
	href="<%=basePath%>/static/jzbTest/weixin/css/view6.css">
<SCRIPT type="text/javascript">
$(function() {
	if (window.history && window.history.pushState) {
		$(window).on('popstate', function () {
			window.history.pushState('forward', null, '#');
			window.history.forward(1);
		});
	}
	window.history.pushState('forward', null, '#');
	window.history.forward(1);
});
</SCRIPT>
</HEAD>
<BODY>
	<div class="main_body">
		<input id="isTk" type="hidden" value="${question.isTk }">
		<input id="sort" type="hidden" value="${paper.sort }">
		<div style="padding-left: 15px; padding-right: 15px; margin-top:15px;overflow: hidden; background-color: #fff;">
			<div
				style="width: 100%; color: #ff9800; font-size: 1.1rem; padding-top: 15px;">${question.qCode }<br>题目${paper.sort }</div>
			<div
				style="width: 100%; min-height:5rem; white-space: normal; word-break: break-all; word-wrap: break-word; padding-top:5px; padding-bottom: 15px; letter-spacing: 1px;">
				${question.qMainDesc }</div>
		</div>


		<div style="">
			<c:forEach items="${question.questions }" var="q" varStatus="status">
				<c:if test="${question.isTk eq '1' }">
					<div style="margin-top: 20px; padding-left: 30px; padding-right: 30px;" class="blq">
						<div style="width: 100%; background-color: #fff;">
							<input class="stuAnswer" type="hidden" value=""> <input
								class="questionId" type="hidden" value="${q.id }">
							<div
								style="padding-top: 10px; padding-left: 5px; padding-right: 5px; white-space: normal; word-break: break-all; word-wrap: break-word;">
								${q.qContent }</div>
							<div
								style="padding-left: 15px; padding-right: 15px; white-space: normal; word-break: break-all; word-wrap: break-word;">
								<div style="width: 100%; margin-top: 5px;">
									<c:forEach items="${q.answers }" var="a">
									<c:if test="${!empty a.aContent }">
										<div
											style="padding-bottom: 10px; padding-left: 5px; padding-right: 5px;">
											<div id="answer${a.id }" class="main_body_ans"
												onclick="removeactivity('${a.id}',this)">${a.xh }.
												${a.aContent }</div>
										</div>
									</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${question.isTk eq '2' }">
					<div style="margin-top: 20px; padding-left: 30px; padding-right: 30px;">
						<div style="width: 100%; background-color: #fff;" class="tkDiv">
							<input class="questionId" type="hidden" value="${q.id }">
							<div
								style="padding-top: 10px; padding-left: 5px; padding-right: 5px; white-space: normal; word-break: break-all; word-wrap: break-word;">
								${q.qContent }</div>
							<div style="padding-left: 15px; padding-right: 15px; white-space: normal; word-break: break-all; word-wrap: break-word;">
								<div style="width: 100%; margin-top: 5px;">
									<input class="tk" id="answer${q.id }" placeholder="填空${status.index+1 }">
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>

		<div style="height: 3.5rem;"></div>
		<div
			style="width: 100%; position: fixed; bottom: 0px; background-color: #fff; height: 3rem; overflow: hidden;">
			<c:if test="${paper.sort ne '1' }">
				<div style="float: left; width: 25%;">
					<img style="height: 3rem;" alt=""
						src="<%=basePath%>/static/jzbTest/weixin/img/last.png"
						id="lastImg">
				</div>
			</c:if>
			<c:if test="${paper.sort eq '1' }">
				<div style="float: left; width: 25%;">
					<img style="height: 3rem;" alt="" src="">
				</div>
			</c:if>
			<div style="float: left; width: 50%; text-align: center;">
				<div>
					<img style="" class="" alt=""
						src="<%=basePath%>/static/jzbTest/weixin/img/clock.png">
				</div>
				<div>
					<div style="float: left; width: 50%; text-align: right;">剩余时间：</div>
					<div style="float: left; width: 25%; text-align: right;" id="sysj"></div>
				</div>
			</div>
			<c:if test="${paper.sort ne paper.totalNum }">
				<div style="float: left; width: 25%;">
					<img style="height: 3rem;" alt=""
						src="<%=basePath%>/static/jzbTest/weixin/img/next.png"
						id="nextImg">
				</div>
			</c:if>
			<c:if test="${paper.sort eq paper.totalNum }">
				<div style="float: left; width: 25%;">
					<img style="height: 3rem;" alt=""
						src="<%=basePath%>/static/jzbTest/weixin/img/tjsj.png" id="tjsj">
				</div>
			</c:if>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>/static/jzbTest/cookie_util.js"></script>
	<script type="text/javascript">
	var finishTime= '${finishTime}';
	var currentTime = '${currentTime}';
	var isTk = $("#isTk").val();
	finishTime = finishTime.replace(/-/g,"/");
	currentTime = currentTime.replace(/-/g,"/");
	var nowtime = new Date(currentTime);
	var endtime = new Date(finishTime);
	showtime();
	initAnswer(isTk);

		function removeactivity(a, thisdiv) {
			var divhtml = $(thisdiv).parent().parent().parent().parent();
			divhtml.find(".main_body_ans").removeClass("activity");
			divhtml.find(".stuAnswer").val(a);
			$(thisdiv).addClass("activity");
		}

		$("#lastImg").click(function() {
			setAutoAnswer(isTk);
			location.href = jsBasePath+ "/jzbTest/weixin/toExam.html?paperId=${paper.paperId}&sort=${paper.sort-1}&totalNum=${paper.totalNum}&monthConfigId=${paper.monthConfigId}";					
			
		});
		
		$("#nextImg").click(function() {
			if(setAnswer(isTk)){
				location.href = jsBasePath+ "/jzbTest/weixin/toExam.html?paperId=${paper.paperId}&sort=${paper.sort+1}&totalNum=${paper.totalNum}&monthConfigId=${paper.monthConfigId}";					
			}
		});
		
		$("#tjsj").click(function() {
			if(setAnswer(isTk)){
				location.href = jsBasePath+ "/jzbTest/weixin/toExamResult.html?paperId=${paper.paperId}&monthConfigId=${paper.monthConfigId}";					
			}
		});

		function setAnswer(isTk) {
			var result = true;
			(isTk=="1") && $(".blq").each(function() {
				var questionId = $(this).find(".questionId").val();
				var stuAnswer = $(this).find(".stuAnswer").val();
				if (!!stuAnswer) {
					setCookie("JZBAS" + questionId, stuAnswer);
				}else{
					layer.alert("题目尚未完成");
					result = false;
					return false;
				}
			});
			
			(isTk=="2") && $(".tkDiv").each(function() {
				var questionId = $(this).find(".questionId").val();
				var stuAnswer = $(this).find(".tk").val();
				if (!!stuAnswer) {
					setCookie("JZBAS" + questionId, stuAnswer);
				}else{
					layer.alert("题目尚未完成");
					result = false;
					return false;
				}
			});
			
			return result;
		}
		
		function setAutoAnswer(isTk) {
			(isTk=="1") && $(".blq").each(function() {
				var questionId = $(this).find(".questionId").val();
				var stuAnswer = $(this).find(".stuAnswer").val();
				if (!!stuAnswer) {
					setCookie("JZBAS" + questionId, stuAnswer);
				}
			});
			
			(isTk=="2") && $(".tkDiv").each(function() {
				var questionId = $(this).find(".questionId").val();
				var stuAnswer = $(this).find(".tk").val();
				if (!!stuAnswer) {
					setCookie("JZBAS" + questionId, stuAnswer);
				}
			});
			
		}

		function initAnswer(isTk) {
			(isTk=="1") && $(".blq").each(function() {
				var questionId = $(this).find(".questionId").val();
				var answerId = getCookie("JZBAS" + questionId);
				$("#answer" + answerId).click();
			});
			(isTk=="2") && $(".tkDiv").each(function() {
				var questionId = $(this).find(".questionId").val();
				var answer = getCookie("JZBAS" + questionId);
				$("#answer" + questionId).val(answer);
			});
		}

		function addZero(i) {
			if (i < 10) {
				i = "0" + i;
			}
			return i;
		}
		function showtime() {
	        var lefttime = parseInt((endtime.getTime() - nowtime.getTime()) / 1000);
	        console.log(lefttime);
	        console.log(nowtime);
	        nowtime = new Date(nowtime.getTime() + 1000); 
	        var h = parseInt(lefttime / 60 / 60 % 60);
	        var m = parseInt(lefttime / 60 % 60);
	        var s = parseInt(lefttime % 60);
	        m = addZero(m);
	        s = addZero(s);
	        document.getElementById("sysj").innerHTML = h + ":" +m + ":" + s;
	        if(lefttime<=0){
	        	setAutoAnswer();
	        	location.href = jsBasePath+"/jzbTest/weixin/toExamResult.html?paperId=${paper.paperId}&monthConfigId=${paper.monthConfigId}";
	        }else{
	        	setTimeout(showtime,1000);	        	
	        }
	    }
	</script>
</body>
</html>