<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="width: 100%;background-color: #F0F0F0;">
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="format-detection" content="telephone=no"/>
	<title>新东方入班水平测试</title>
	<link rel="stylesheet" href="<%=basePath %>/static/jzbTest/weixin/css/view6.css">
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
	<input id="stuAnswer" type="hidden" value="">
	<input id="isTk" type="hidden" value="${question.isTk }">
	<input id="questionId" type="hidden" value="${question.id }">
	<input id="sort" type="hidden" value="${paper.sort }">
		<div style="padding-left: 15px; padding-right: 15px; margin-top:15px; overflow: hidden; background-color: #fff;">
			<div style="width: 100%;white-space:normal;min-height:5rem; word-break:break-all; word-wrap:break-word; padding-top:5px; padding-bottom: 15px;letter-spacing:1px;">
				${question.qCode }<br>题目${paper.sort }<BR>${question.qContent }
			</div>
		</div>
		<div>
			<c:if test="${question.isTk eq '1'}">
				<c:forEach items="${question.answers }" var="answer">
					<c:if test="${!empty answer.aImg or !empty answer.aContent }">
					<div style="margin-top:15px; padding-left: 15px; padding-right: 15px; white-space:normal; word-break:break-all; word-wrap:break-word">
						<div style="width: 100%;">
							<div style="padding-left: 5px; padding-right: 5px;">
								<div id="answer${answer.id }" class="main_body_ans" onclick="removeactivity('${answer.id}',this)">
									${answer.xh }. ${answer.aContent }
									<c:if test="${!empty answer.aImg }">
										<img src="${fileurl }${answer.aImg}"> 
									</c:if>
								</div>
							</div>
						</div>
					</div>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${question.isTk eq '2'}">
				<div style="margin-top:15px; padding-left: 15px; padding-right: 15px; white-space:normal; word-break:break-all; word-wrap:break-word">
				  <c:forEach varStatus="status" begin="1" end="${question.tkNum}">
					<input class="tk" id="answer${status.index+1 }" placeholder="填空${status.index+1 }">
				</c:forEach>
				</div>
			</c:if>
		</div>
		<div style="height: 3.5rem;"></div>
		<div style="width:100%; position: fixed; bottom:0px; background-color: #fff; height: 3rem; overflow: hidden;">
		<c:if test="${paper.sort ne '1' }">
		<div style="float: left;width: 25%;"><img style="height: 3rem;" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/last.png" id="lastImg"></div>
		</c:if>
		<c:if test="${paper.sort eq '1' }">
		<div style="float: left;width: 25%;"><img style="height: 3rem;" alt="" src=""></div>
		</c:if>
			<div style="float: left;width: 50%; text-align: center;">
				<div><img style="" class="" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/clock.png"></div>
				<div>
					<div style="float: left; width: 50%; text-align: right;">剩余时间：</div>
					<div style="float: left; width: 25%; text-align: right;" id="sysj"></div>
				</div>
			</div>
			<c:if test="${paper.sort ne paper.totalNum }">
				<div style="float: left;width: 25%;"><img style="height: 3rem;" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/next.png" id="nextImg"></div>			
			</c:if>
			<c:if test="${paper.sort eq paper.totalNum }">
				<div style="float: left;width: 25%;"><img style="height: 3rem;" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/tjsj.png" id="tjsj"></div>			
			</c:if>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>/static/jzbTest/cookie_util.js"></script>
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
		
		function removeactivity(a,thisdiv){
			var divhtml = $(thisdiv).parent().parent().parent().parent();
			divhtml.find(".main_body_ans").removeClass("activity");
			$("#stuAnswer").val(a);
			$(thisdiv).addClass("activity");
		}
		
		$("#lastImg").click(function(){
			setAutoAnswer(isTk);
			location.href = jsBasePath+"/jzbTest/weixin/toExam.html?paperId=${paper.paperId}&sort=${paper.sort-1}&totalNum=${paper.totalNum}&monthConfigId=${paper.monthConfigId}";
		});
		
		$("#nextImg").click(function(){
			if(setAnswer(isTk)){
				location.href = jsBasePath+"/jzbTest/weixin/toExam.html?paperId=${paper.paperId}&sort=${paper.sort+1}&totalNum=${paper.totalNum}&monthConfigId=${paper.monthConfigId}";
			}
		});
		
		$("#tjsj").click(function(){
			if(setAnswer(isTk)){
				location.href = jsBasePath+"/jzbTest/weixin/toExamResult.html?paperId=${paper.paperId}&monthConfigId=${paper.monthConfigId}";
			}
		});
		
		function setAnswer(isTk){
			var questionId = $("#questionId").val();
			if(isTk=="1"){
				var stuAnswer = $("#stuAnswer").val();
				if(!!stuAnswer){
					setCookie("JZBAS"+questionId,stuAnswer);
					return true;
				}else{
					layer.alert("请选择答案");
					return false;
				}
			}
			if(isTk=="2"){
				var flag = true;
				$(".tk").each(function(index){
					if(!this.value){
						layer.alert("请填写完整");
						flag = false;
						return false;
					}
				});
				if(flag){
					var stuAnswer = $(".tk").map(function(){return this.value}).get().join();
					setCookie("JZBAS"+questionId,stuAnswer);
					return true;
					
				}
			}
		}
		
		function setAutoAnswer(){
			var questionId = $("#questionId").val();
			if(isTk=="1"){
				var stuAnswer = $("#stuAnswer").val();
				if(!!stuAnswer){
					setCookie("JZBAS"+questionId,stuAnswer);
					return true;
				}
			}
			if(isTk=="2"){
				var stuAnswer = $(".tk").map(function(){return this.value}).get().join();
				setCookie("JZBAS"+questionId,stuAnswer);
				return true;
			}
		}
		
		function initAnswer(isTk){
			var questionId = $("#questionId").val();
			var answer = getCookie("JZBAS"+questionId);
			if(!!answerId){
				if(isTk=="1"){
					$("#answer"+answerId).click();	
				}
				if(isTk=="2"){
					var anArr = answer.split(",");
					for(var i=1;i<=anArr.length;i++){
						(!!anArr[i]) && $("#answer"+i).val(anArr[i]);
					}
				}
			}
			
		}
		
	    function addZero(i){
	        if(i<10){
	            i = "0" + i;
	        }return i;
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
	        document.getElementById("sysj").innerHTML = h+ ":" + m + ":" + s;
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