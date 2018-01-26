<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
</head>
<body>
	<div class="alertFrom">
		<div class="layui-form Div2" id="Div2">
			<input type="hidden" id="code" name="code" value='${code}'>
			<input type="hidden" id="paperId" name="paperId" value='${paperId}'>
			<input type="hidden" id="fileUrl" name="fileUrl" value='${fileurl}'>
			<input type="hidden" id="questionType" name="questionType" value='${questionType}'>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">题型</label>
				<div class="layui-input-inline" style="width: 20%;">
					<input class="layui-input" placeholder="" id="type" value="${questionTypeName}">
				</div>
				<label class="layui-form-label" style="width: 5%;">难度</label>
				<div class="layui-input-inline"  style="width: 20%;">
					<input class="layui-input" placeholder="" name="difficulty" id="difficulty" value="${difficultyName}">
				</div>
				<label class="layui-form-label" style="width: 5%;">年级</label>
				<div class="layui-input-inline"  style="width: 20%;">
					<input class="layui-input" placeholder="" name="grade" id="grade" value="${GradeName}" >
				</div>
			</div>
			<div class="layui-form-item append"></div>
			<div class="layui-form-item answerAppend"></div>
			<div class="layui-form-item topicAppend"></div>
		</div>
	</div>

	<div id="div1" style="display: none">
		<div>
			<input type="hidden"  id="questionId">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">指导语</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea class="layui-textarea" name="message" id="message"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label topicTime" id="topicTime"
					style="width: 77%;">短文熟悉时间</label>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">题干</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea class="layui-textarea" name="content" id="content" cols="100" rows="8"
						style="width: 100%; height: 400px;"></textarea>
				</div>
			</div>
			<div class="layui-form-item aaa">
				<label class="layui-form-label " style="width: 10%;">排序</label>
				<div class="layui-input-inline" style="width: 12%;">
					<input type="text" id="xh" name="xh" placeholder="请输入题目顺序" value=""
						class="layui-input">
				</div>
				<label class="layui-form-label " style="width: 40%;">限定作答时间</label>
			</div>
			<div class="layui-form-item">
				<button type="button" class="layui-btn "
					onclick="addQuestion(this);" style="float: left; margin-left: 12%;">
					<li class="fa fa-plus-square"></li> &nbsp;添加试题
				</button>
				<label class="layui-form-label " style="width: 54%;">播放音频解析</label>
			</div>
		</div>
	</div>
	<div id="div2" style="display: none">
		<div>
		<input type="hidden"  id="questionId">
			<div class="layui-form-item ">
				<label class="layui-form-label zdmessage" style="width: 10%;">指导语</label>
				<div class="layui-input-inline zdmessage" style="width: 65%;">
					<textarea class="layui-textarea" name="message" id="message"></textarea>
				</div>
				<div class="layui-form-item topicTime">
					<label class="layui-form-label " id="topicTime"
						style="width: 77%;">短文熟悉时间</label>
				</div>
				<div class="layui-form-item topicTime">
					<label class="layui-form-label" style="width: 10%;">大题干</label>
					<div class="layui-input-inline" style="width: 65%;">
						<textarea class="layui-textarea" name="topic" id="topic" cols="100" rows="8"
							style="width: 100%; height: 100px;"></textarea>
					</div>
				</div>
				<div class="layui-form-item sonTitle"  >
				</div>
<!-- 				<div class="layui-form-item aaa"> -->
<!-- 					<label class="layui-form-label " style="width: 10%;">排序</label> -->
<!-- 					<div class="layui-input-inline" style="width: 12%;"> -->
<!-- 						<input type="text" id="xh" name="xh" placeholder="请输入题目顺序" -->
<!-- 							value="" class="layui-input"> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="layui-form-item"> -->
<!-- 					<button type="button" class="layui-btn "  -->
<!-- 						onclick="addQjQuestion(this);" -->
<!-- 						style="float: left; margin-left: 12%;"> -->
<!-- 						<li class="fa fa-plus-square"></li> &nbsp;添加试题 -->
<!-- 					</button> -->
<!-- 				</div> -->
			</div>
		</div>
	</div>


	<div id="dryAnswer" style="display: none">
		<div class="layui-collapse" lay-accordion="" style="width:70%;margin-left: 11%;">
                	<div class="layui-colla-item">
                		<h2 class="layui-colla-title icon-title" index='1'></h2>
                		<div class="layui-colla-content detail">
		<div class="layui-form-item son-item">
			<input type="hidden"  id="questionId">
			<label class="layui-form-label" style="text-align: left;padding: 9px 6px;">题干</label>
			<div class="layui-input-inline"    style=" width: 98%;margin-left: 1%;">
				<textarea class="layui-textarea" name="content" id="content" cols="100" rows="8"
					style="width: 100%; height: 100px;"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="text-align: left;padding: 9px 6px;">解析</label>
			<div class="layui-input-inline" style=" width: 98%;margin-left: 1%;">
				<textarea class="layui-textarea" name="parseText" id="parseText" cols="100" rows="8"
					style="width: 100%; height: 100px;"></textarea>
			</div>
		</div>
		<div class="layui-form-item ">
			<label class="layui-form-label answerTime" style="width: 97%;">限定作答时间</label>
		</div>
				<div class="layui-form-item" style="margin-bottom: 10px;margin-left:2%">
					<label class="layui-form-label" style="width:49%;text-align: left;padding:9px 0px;">音频解析：点击播放音频解析&nbsp;&nbsp;</label>
				</div>
				<div class="layui-form-item" style="margin-bottom: 10px;margin-left:2%">
					<div class="layui-input-inline" id="recordDiv" style="width:90%;height:71px;padding:8px 10px;margin-right: 0px;">
						<div id="audioDiv" class="detailAudio" class="layui-input-inline" style="width:46%;float:left; margin-left:-10px;margin-top: 14px;">
								<audio id="audio" src="" controls="controls"></audio>
						</div>
					</div>
				</div></div></div></div>
	</div>
	<div class="div3" id="div3" style="display: none">
		<div>
			<input type="hidden"  id="questionId">
			<div class="layui-form-item">
				<label class="layui-form-label messageDiv" style="width: 10%;">指导语</label>
				<div class="layui-input-inline messageDiv" style="width: 65%; ">
					<textarea class="layui-textarea" name="message" id="message"></textarea>
				</div>
				<div class="layui-form-item topicDiv">
					<label class="layui-form-label topicTime" id="topicTime"
						style="width: 77%;">短文熟悉时间</label>
				</div>
				<div class="layui-form-item topicDiv">
					<label class="layui-form-label" style="width: 10%;">题干</label>
					<div class="layui-input-inline" style="width: 65%;">
						<textarea class="layui-textarea" name="topic" id="topic" cols="100" rows="8"
							style="width: 100%; height: 100px;"></textarea>
					</div>
				</div>
				<div class="layui-form-item topicTimeDiv">
					<label class="layui-form-label contentTime" id="contentTime"
						style="width: 77%;">题干熟悉时间</label>
				</div>
				<div class="layui-form-item contentDiv">
					<label class="layui-form-label" style="width: 10%;">题干</label>
					<div class="layui-input-inline" style="width: 65%;">
						<textarea class="layui-textarea" name="content" id="content" cols="100" rows="8"
							style="width: 100%; height: 100px;"></textarea>
					</div>
				</div>
				<div class="layui-form-item parseTextDiv">
					<label class="layui-form-label limitTime" id="limitTime"
						style="width: 77%;"></label>
				</div>
				<div class="layui-form-item parseTextDiv">
					<label class="layui-form-label " style="width: 10%;">解析</label>
					<div class="layui-input-inline" style="width: 65%;">
						<textarea class="layui-textarea" name="parseText" id="parseText" cols="100" rows="8"
							style="width: 100%; height: 100px;"></textarea>
					</div>
				</div>
				<div class="layui-form-item parseTextDiv" style="margin-bottom: 10px; margin-left:14%">
					<label class="layui-form-label" style="width:49%;text-align: left;padding:9px 0px;">音频解析：点击播放音频解析&nbsp;&nbsp;</label>
				</div>
				<div class="layui-form-item parseTextDiv" style="margin-bottom: 10px;margin-left:9%">
					<div class="layui-input-inline" id="recordDiv" style="width:90%;height:71px;padding:8px 10px;margin-right: 0px;">
					<div id="audioDiv" class="layui-input-inline" style="width:30%;margin-left:40px;margin-top: 14px;">
						<audio id="audio"  src="" controls="controls"></audio>
					</div>
					</div>
				</div>
<!-- 				<div class="layui-form-item aaa"> -->
<!-- 					<label class="layui-form-label " style="width: 10%;">排序</label> -->
<!-- 					<div class="layui-input-inline" style="width: 12%;"> -->
<!-- 						<input type="text" id="xh" name="xh" placeholder="请输入题目顺序" -->
<!-- 							value="" class="layui-input"> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="layui-form-item"> -->
<!-- 					<button type="button" class="layui-btn "  -->
<!-- 						onclick="addQuestion(this);" -->
<!-- 						style="float: left; margin-left: 12%;"> -->
<!-- 						<li class="fa fa-plus-square"></li> &nbsp;添加试题 -->
<!-- 					</button> -->
<!-- 				</div> -->
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			layui.use(['form','laydate',"layer"], function(){
				var form = layui.form,laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
			 $(".layui-collapse").find(".icon-title").click(function(){
				  var index =$(this).attr("index");
				  if(index=="1"){
					  $(this).parent().find(".detail").addClass('layui-show');
					  $(this).parent().find(".icon-title").find(".layui-colla-icon").css("transform","none");
					  $(this).attr("index","2");
				  }else if(index=="2"){
					  $(this).parent().find(".detail").removeClass('layui-show');
					  $(this).parent().find(".icon-title").find(".layui-colla-icon").css("transform","rotate(0deg)");
					  $(this).attr("index","1");
				  }
			  });
			});
			 $.ajax({
				 type: "post",
				 url: jsBasePath+"/lstQuestion/getQuestion.html",
				 data: {code:$("#code").val()},
				 dataType: "json",
				 async:false,
				 success: function(data){
					 var fileUrl=$("#fileUrl").val();
						var Div=$("#div2").clone(true);
						$.each(data,function(i,info){
						 if(info.TYPE=='1'){
								$("#Div2").find(".topicAppend").empty();
								var speakDiv=$("#div3").clone(true);
								speakDiv.find("#message").text(info.ZDMESSAGE);
								speakDiv.find("#topicTime").text("题干熟悉时间"+info.CONTENT_TIME+"秒");
								speakDiv.find("#limitTime").text("限定作答时间"+info.ANSWER_TIME+"秒");
								speakDiv.find("#questionId").val(info.ID);
								speakDiv.find("#content").text(info.CONTENT);
								speakDiv.find("#parseText").text(info.PARSE_TEXT);
								speakDiv.find("#audio").attr("src",fileUrl+info.PARSE_AUDIO)
								if(info.isNeedGuide!='1'){
									speakDiv.find(".zdmessage").hide();
								}
								if(info.isNeedParse!='1'){
									speakDiv.find(".parseTextDiv").hide();
									speakDiv.find(".parseTextTimeDiv").hide();
								}
								if(info.isNeedEssay!="1"){
									speakDiv.find(".topicDiv").hide();
								}
								$("#Div2").find(".topicAppend").append(speakDiv.html());
//		 						form.render();
						}else if(info.TYPE=='2') {
										$("#Div2").find(".answerAppend").empty();
										Div.find("#topicTime").text("短文熟悉时间"+info.TOPIC_TIME+"秒");
										Div.find("#topic").text(info.TOPIC);
										Div.find("#message").text(info.ZDMESSAGE);
										var dryAnswerDiv=$("#dryAnswer").clone(true);
										dryAnswerDiv.find("#questionId").val(info.ID);
										dryAnswerDiv.find("#content").text(info.CONTENT);
										var num=parseInt(1)+parseInt(i);
										dryAnswerDiv.find(".icon-title").text("问题"+num);
										dryAnswerDiv.find(".layui-colla-title").append("<i class='layui-icon layui-colla-icon'></i>");
										dryAnswerDiv.find(".layui-colla-title").find(".layui-colla-icon").css("transform","rotate(-90deg)");
										if(info.PARSE_AUDIO==""){
											dryAnswerDiv.find("#audio").hide()
										}
										dryAnswerDiv.find("#audio").attr("src",fileUrl+info.PARSE_AUDIO)
										if(info.ANSWER_TIME==null||info.ANSWER_TIME==""){
										info.ANSWER_TIME=15
										}
										if(info.isNeedGuide!='1'){
											speakDiv.find(".zdmessage").hide();
										}
										if(info.isNeedParse!='1'){
											speakDiv.find(".sonTitle").hide();
										}
										if(info.isNeedEssay!="1"){
											speakDiv.find(".topicDiv").hide();
										}
										dryAnswerDiv.find(".answerTime").text("作答限定时间"+info.ANSWER_TIME+"秒");
										Div.find(".sonTitle").append(dryAnswerDiv.html());
										$("#Div2").find(".answerAppend").append(Div.html());
						}
					});
				 }});
// 			$.post(jsBasePath+"/lstQuestion/getQuestion.html",{code:$("#code").val()},function(data,status){
				
// 		 },"json");
		});
		function addQuestion(obj){
			var questionId= $("#questionId").val();
			var xh= $(obj).parent().parent().find("#xh").val();
			var paperId=$("#paperId").val();
			var questionType=$("#questionType").val();
			var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/lstQuestion/addTest.html",{"paperId":paperId,"questionId":questionId,"xh":xh,"type":"1","questionType":questionType},function(data,status){
				layer.close(index); 
				if(data.flag==true){
					layer.alert(data.message,{icon:1},function(){
						closeFrame();
					});
					}else{
						layer.alert(data.message,{icon:2});
						return false;
					}
			},"json");
			return false;
		}

		function addQjQuestion(obj){
			var paperId=$("#paperId").val();
			var questionId=""
			var questionType=$("#questionType").val();
			var xh= $(obj).parent().parent().find("#xh").val();
			$(obj).parent().parent().find(".sonTitle").find(".son-item").each(function(){
				questionId+=$(this).find("#questionId").val()+",";
			});
			var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/lstQuestion/addTest.html",{"paperId":paperId,"questionId":questionId,"xh":xh,"type":"2","questionType":questionType},function(data,status){
				layer.close(index); 
				if(data.flag==true){
					layer.alert(data.message,{icon:1},function(){
						closeFrame();
					});
					}else{
						layer.alert(data.message,{icon:2});
					}
			},"json");
			return false;
		}
// 		});
	</script>	
</body>
</html>