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
<link rel="stylesheet" href="<%=basePath%>/static/studentpc/css/less.css">
<script type="text/javascript">
var audioBlob;
</script>
<style>
.test {
	margin: 5% 0 0 38%;
	position: relative;
}

.wraph, .circleh, .percenth {
	position: absolute;
	width: 200px;
	height: 200px;
	border-radius: 50%;
}

.wraph {
	top: 0;
	left: 0;
	background-color: #64dcdc;
	border: 1px solid #64dcdc;
}

.circleh {
	box-sizing: border-box;
	border: 20px solid #64dcdc;
	clip: rect(0, 200px, 200px, 100px);
}

.clip-autoh {
	clip: rect(auto, auto, auto, auto);
}

.percenth {
	box-sizing: border-box;
	top: -20px;
	left: -20px;
}

.lefth {
	transition: transform ease;
	border: 20px solid #fafafa;
	clip: rect(0, 100px, 200px, 0);
}

.righth {
	border: 20px solid #fafafa;
	clip: rect(0, 200px, 200px, 100px);
}

.wth0h {
	width: 0;
}

.mytest-main img {
	position: absolute;
	box-sizing: border-box;
	width: 160px !important;
	height: 160px;
	line-height: 160px;
	text-align: center;
	font-size: 40px;
	left: 20px;
	top: 20px;
	border-radius: 50%;
	z-index: 1;
}
</style>
</head>
<body>
	<div class="public-mytest">
		<div class="mytest-top">
			<div class="testtop-left">
				<img src="<%=basePath %>/static/studentpc/images/14X_03.jpg" alt="">
				<p id="thisth"></p>
			</div>
			<div class="testtop-right">
				<p onclick="tonextquestion();" style="cursor:pointer" id="nextquestion">下一题</p>
				<img onclick="tonextquestion();" style="cursor:pointer" src="<%=basePath %>/static/studentpc/images/14X_06.jpg" alt="">
				<img onclick="goback();" style="cursor:pointer" src="<%=basePath %>/static/studentpc/images/14X_09.png" alt="">
				<p onclick="goback();" style="cursor:pointer" style="cursor:pointer">返回</p>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="mytest-main" style="padding-bottom: 240px">
			<p id="zdmessage"><!-- 你将有1分钟的时间熟悉屏幕上的短文。在听到开始录音的提示后，请将短文朗读一遍，时间为1分半钟 --></p>
			<p id="topic">
				<!-- An article called “How to Limit Screen Time and Get Moving” gives
				these ideas:<br> ep away from the screen. Doctors call watching
				TV or playing video games screen time. You should have no more than
				two hours of screen time a day. You will enjoy life more if you live
				it instead of watching it!Start slowly. An hour a day can seem like
				a long time. Start with 10 minutes of new activities every day. Add
				more as you get stronger. Think about walking. Take the stairs
				instead of the lift. You will feel healthier.<br> article
				called “How to Limit Screen Time and Get Moving” gives these ideas:<br>
				ep away from the screen. Doctors call watching TV or playing video
				games screen time. You should have no more than two hours of screen
				time a day. You will enjoy life more if you live it instead of
				watching it!Start slowly. An hour a day can seem like a long time.
				Start with 10 minutes of new activities every day. Add more as you
				get stronger. Think about walking. Take the stairs instead of the
				lift. You will feel healthier. -->
			</p>
			<p class="timu" id="content"><!-- 1,An article called “How to Limit Screen Time and
				Get Moving” gives these ideas: -->
			</p>
			<div class="test">
				<div class="wraph">
					<div class="circleh">
						<div class="percenth lefth"></div>
						<div class="percenth righth wth0h"></div>
					</div>
					<img onclick="recording();" id="recordimg" class="numh" src="<%=basePath %>/static/studentpc/images/14X_03.png" alt="">
				</div>
			</div>
		</div>
		<div class="mytesting-bottom">
			<input type="button" id="recordbtn" value="听题时间，不录音......">
		</div>
		<div id="answerDiv"></div>
		<div style="display: none;">
			<audio id="zd_audio" controls="controls" src="" ></audio>
			<audio id="content_audio" controls="controls" src="" ></audio>
		</div>
	</div>
	<input id="sampleRate" type="hidden">
	<div id="answerDiv"></div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/studentpc/js/initRecorder.js"></script>
<script src="<%=basePath%>/static/HZRecorder.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/studentpc/js/zuoye.js"></script>
<script type="text/javascript"> 
var bufferdSendSize=0;
var currentTime =0;
var currentIndex = 0;
var getBufferLoading;
var tjindex; 
var contentNum=0;
var is_last = -1;
var parseResult;
</script> 
</html>