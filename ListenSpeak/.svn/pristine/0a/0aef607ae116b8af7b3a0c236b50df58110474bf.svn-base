<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="height: 100%;">
<head>
<meta charset="UTF-8">
<title>我的测试(测试中)</title>
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta charset="UTF-8">
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<link rel="stylesheet" href="<%=basePath %>/static/studentpc/css/less.css">
	    <style> 
     .test{
    	margin: 5% 0 0 38%;
		position: relative;
    }
    .wraph,.circleh,.percenth{ 
        position: absolute; 
        width: 200px; 
        height: 200px; 
        border-radius: 50%; 
    }

    .wraph{ 
        top:0; 
        left:0; 
        background-color: #64dcdc; 
        border: 1px solid #64dcdc;
    } 

    .circleh{ 
        box-sizing: border-box; 
        border:20px solid #64dcdc; 
        clip:rect(0,200px,200px,100px); 
    } 
    .clip-autoh{ 
        clip:rect(auto, auto, auto, auto); 
    } 
    .percenth{ 
        box-sizing: border-box; 
        top:-20px; 
        left:-20px; 
    } 
    .lefth{ 
        transition:transform ease; 
        border:20px solid #fafafa; 
        clip: rect(0,100px,200px,0); 
    } 
    .righth{ 
        border:20px solid #fafafa; 
        clip: rect(0,200px,200px,100px); 
    } 
    .wth0h{ 
        width:0; 
    } 
     .mytest-main img { 
        position: absolute; 
        box-sizing: border-box; 
        width: 160px!important; 
        height: 160px; 
        line-height: 160px; 
        text-align: center; 
        font-size: 40px; 
        left: 20px; 
        top: 20px; 
        border-radius: 50%; 
        z-index: 1; 
    } 
     #father div{
		margin-top: 5px;
		height: 4px;
		background: #64dcdc;
	}
    </style>
</head>
<body>
<div class="public-mytest"> 
<input id="sampleRate" type="hidden">
<input type="hidden" id="student_id" name="student_id" value="${param.studentId }">
<input type="hidden" id="class_code" name="class_code" value="${param.classCode }">
<input type="hidden" id="test_id" name="test_id" value="${param.testId }">
<input type="hidden" id="student_test_id" name="student_test_id" value="${param.studentTestId }">

<input type="hidden" id="isEnd" name="isEnd" value="${isEnd }">
<input type="hidden" id="tmNum" name="tmNum" value="${question.tmNum }">
<input type="hidden" id="xh" name="xh" value="${question.xh }">
<input type="hidden" id="bigXh" name="bigXh" value="${question.bigXh }">
<input type="hidden" id="smallXh" name="smallXh" value="${question.smallXh }">

<input type="hidden" id="question_id" name="question_id" value="${question.questionId }">
<input type="hidden" id="scoreType" name="scoreType" value="${question.scoreType }">
<input type="hidden" id="parseText" name="parseText" value="${question.parseText }">
<input type="hidden" id="content" name="content" value="${question.content }">
<input type="hidden" id="tgTime" name="tgTime" value="${question.contentTime }">
<input type="hidden" id="answerTime" name="tgTime" value="${question.answerTime }">

<div style="display: none;">
<audio id="zdAudio" controls="controls" src="${fileurl }${question.zdAudio }"></audio>
<audio id="contentAudio" controls="controls" src="${fileurl }${question.contentAudio }"></audio>
</div>

<div class="mytest-top">
	<div class="testtop-left">
		<img src="<%=basePath %>/static/studentpc/images/14X_03.jpg" alt="">
		<p>第${question.bigXh }题</p>
	</div>
	<div class="testtop-right">
		<!-- <p>下一题</p> -->
		<img src="<%=basePath %>/static/studentpc/images/14X_06.jpg" alt="">
	</div>
	<div class="clearfix"></div>
</div>
<div class="mytest-main" style="padding-bottom:240px">
	<p>${question.zdmessage }</p>
	<p>${question.content }</p>
	<div class="test">
		<div class="wraph"> 
		    <div id="circle" class="circleh"> 
		        <div class="percenth lefth"></div> 
		        <div class="percenth righth wth0h"></div> 
		    </div> 
		    <img class="numh" style="position:relative" src="<%=basePath %>/static/studentpc/images/15X_04_03.png" alt="">
		     <div id="father"  style="display:none; height:50px; width:26px; background: #F5F5F5; position:absolute; right: 37px;top: 82px;z-index: 99999;">
			   <div id="son1" style=" width: 90%;"></div>
			   <div id="son2" style=" width: 75%;"></div>
			   <div id="son3" style=" width: 60%;"></div>
			   <div id="son4" style=" width: 45%;"></div>
			   <div id="son5" style=" width: 30%;"></div>
			   <div id="son6" style=" width: 15%;"></div>
			</div>
		</div> 
	</div>
	</div>
	<div class="mytesting-bottom">
		<input id="tsmess" type="button" value="指导语时间">
	</div>
	<div id="answerDiv"></div>
</div>


<script type="text/javascript"> 
/*勿删，用于rem样式*/
var  deviceWidth = document.documentElement.clientWidth;
if(deviceWidth >750) deviceWidth =750;
document.documentElement.style.fontSize = deviceWidth /7.5+'px';
/*用于圆环,实现百分比可以直接改下边percent*/
</script> 
<script type="text/javascript" src="<%=basePath%>/static/studentpc/js/initRecorder.js"></script>
<script src="<%=basePath%>/static/HZRecorder.js"></script>
<script src="<%=basePath%>/static/studentpc/js/simple_ks.js"></script>
<script type="text/javascript"> 
var zdAudio = $("#zdAudio").get(0),contentAudio = $("#contentAudio").get(0);
var zdSj,zd_status,audioBlob;
var tjindex; 
var bufferdSendSize=0;
var currentTime =0;
var currentIndex = 0;
var getBufferLoading;
var tgTime = $("#tgTime").val();
var answerTime = $("#answerTime").val();
var isHasTopic = $("#isHasTopic").val();
zdAudio.addEventListener("canplay", function(){
	zdSj=parseInt(zdAudio.duration);
	bfZdy();
});
</script> 
</body>
</html>