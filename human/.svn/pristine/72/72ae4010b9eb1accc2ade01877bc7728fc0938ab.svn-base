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
	
	<link rel="stylesheet" href="<%=basePath %>/static/jzbTest/weixin/css/view3.css">
	<style type="text/css">
	.divselected{
		background-color: #0db09b;
	}
	</style>
</head>
<body >

	<div class="main_body">
	<input type="hidden" id="monthConfigId">
	<input type="hidden" id="mainConfigId">
	<input type="hidden" id="paperTime">
	<input type="hidden" id="month">
	<input type="hidden" id="examName">
	<input type="hidden" id="dayTimes">
	<input type="hidden" id="monthTimes">
	<input type="hidden" id="totalTimes">
		<div style="height:100%; padding-left: 30px; padding-right: 30px;">
			<div style="width:100%; padding-top: 5%">
				<div style="text-align: center;">
					<img class="main_head_logo_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/logo.png">
				</div>
			</div>
			<div style="width: 100%; padding-top: 5%; overflow: hidden;">
				<div class="main_head_title" style="width: 23%;">
					<img class="main_head_l_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/left.png">
				</div>
				<div class="main_head_title" style="width: 54%; text-align: center; line-height: 2rem; font-size: 1rem;">
					<div id="examNameDiv">
						
					</div>
				</div>
				<div class="main_head_title" style="width: 23%;">
					<img class="main_head_l_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/right.png">
				</div>
			</div>
			<div style="margin-top: 10px; padding-left: 15px; padding-right: 15px; overflow: hidden;">
				<div class="main_body_div">
					<div class="main_body_div_left">
						<img class="main_body_div_left_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/input_one.png">
					</div>
					<div class="main_body_div_center">
						<span>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级</span>
						<span>:</span>
					</div>
					<div class="main_body_div_right">
						<span>${gradeName }</span>
						<input id="grade" type="hidden" value="${gradeId }">
					</div>
				</div>
				<div class="main_body_div">
					<div class="main_body_div_left">
						<img class="main_body_div_left_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/input_two.png">
					</div>
					<div class="main_body_div_center">
						<span>科&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目</span>
						<span>:</span>
					</div>
					<div class="main_body_div_right">
						<span></span>
						<input id="subject" type="hidden">
					</div>
				</div>
				<div class="main_body_div">
					<div class="main_body_div_left">
						<img class="main_body_div_left_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/input_three.png">
					</div>
					<div class="main_body_div_center">
						<span>考试时间</span>
						<span>:</span>
					</div>
					<div class="main_body_div_right">
						<span id="paperTimeDesc"></span>
					</div>
				</div>
				<div class="main_body_div">
					<div class="main_body_div_left">
						<img class="main_body_div_left_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/input_four.png">
					</div>
					<div class="main_body_div_center">
						<span>考试题数</span>
						<span>:</span>
					</div>
					<div class="main_body_div_right">
						<span id="totalQNum"></span>
					</div>
				</div>
				<div class="main_body_div" style="cursor:pointer; background-color: #0db09b; border-radius:25px; text-align: center; color: #e4e4e4; font-size: 1.4rem;" id="ksStartButton">
					开始考试
				</div>
			</div>
			
			<div style="margin-top: 15%; overflow: hidden;">
				<div style="width: 38%; float: left;height: 2rem;line-height: 2rem;"><img class="main_head_l_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/l_foot.jpg"></div>
				<div style="width: 24%; float: left; text-align: center;height: 2rem;line-height: 2rem; font-size: 1rem;">温馨提示</div>
				<div style="width: 38%; float: left;height: 2rem;line-height: 2rem;"><img class="main_head_l_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/l_foot.jpg"></div>
			</div>
			<div style="margin-top: 10px; overflow: hidden;">
				<div style="font-size: 0.8rem; text-align: center;" id="headMessage"></div>
			</div>
		</div>
	</div>
	<div id="cantfind" style="display:none">
		<div style="padding:1rem 10%;">
			<div style="text-align: center; font-size: 1.1rem; font-weight: bold;">请选择类型</div>
			<div style="padding: 10px 5% 20px 5%;">
				<div style="overflow: hidden;">
					<!-- <div class="main_tap_select" style="background-color: #0db09b;">一年级</div>
					<div class="main_tap_select">二年级</div>
					<div class="main_tap_select">三年级</div>
					<div class="main_tap_select">四年级</div> -->
					<c:forEach items="${classTypes }" var="classType">
						<div class="main_tap_select ct">${classType.name }
							<input name="classType" type="hidden" value="${classType.dataValue }">
						</div>
					</c:forEach>
				</div>
			</div>
			<div style="text-align: center; font-size: 1.1rem; font-weight: bold;">请选择科目</div>
			<div style="padding: 10px 5% 20px 5%;">
				<div style="overflow: hidden;" id="subjectDiv">
					<%-- <c:forEach items="${subjects }" var="subject">
						<div class="main_tap_select">${subject.name }
							<div style="display:none;">
								<input name="subject" type="radio" value="${subject.dataValue }">
							</div>
						</div>
					</c:forEach> --%>
					
					
				</div>
			</div>
			<div style="text-align: center; padding-bottom: 10px;">
				<div style="cursor:pointer; height: 2rem; line-height:2rem; color: #e4e4e4; font-size:1.2rem; background-color: #0db09b; border-radius:9px;" onclick="closefrom();">确认</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var showopen;
	
	showcontent();
	
	$(".ct").bind("click",function(){
		$(this).siblings().removeClass("divselected");
		$(this).addClass("divselected");
		var grade = $("#grade").val();
		var classType = $(this).find("input").val();
		setSubject(classType,grade);
		
	});
	
	function setSubject(classType,grade){
		$.post(jsBasePath+"/jzbTest/weixin/getValidSubject.html",{
			"classType" :classType,
			"gradeIds" :grade
		  },function(data,status){
				if(data.flag==false){
					layer.alert(data.message,{icon:2});
				}else{
					if(data.subjects.length==0){
						layer.alert("该班型下无配置科目",{icon:2});
					}else{
						$("#subjectDiv").html("");
						$(data.subjects).each(function(){
							$("#subjectDiv").append('<div class="main_tap_select sub">'+this.name+'<input name="subject" type="hidden" value="'+this.dataValue+'"></div>');
						});
						$(".sub").bind("click",function(){
							$(this).siblings().removeClass("divselected");
							$(this).addClass("divselected");
						});
					}
				}
			},"json");
			return false;
	}
	
	$("#ksStartButton").click(function(){
		var monthConfigId = $("#monthConfigId").val();
		var mainConfigId = $("#mainConfigId").val();
		var grade = $("#grade").val();
		var classType = $(".divselected").find("input[name='classType']").val();
		var subject = $(".divselected").find("input[name='subject']").val();
		var examName = $("#examName").val();
		var paperTime = $("#paperTime").val();
		var totalTimes = $("#totalTimes").val();
		var dayTimes = $("#dayTimes").val();
		var monthTimes = $("#monthTimes").val();
		if(!monthConfigId){
			layer.alert("配置错误，请联系老师",{icon:2});
		}else{
			location.href = jsBasePath+"/jzbTest/weixin/toStudentMsg.html?monthConfigId="+monthConfigId+"&gradeId="
					+grade+"&classType="+classType+"&subject="+subject+"&mainConfigId="+mainConfigId+"&examName="+examName
					+"&paperTime="+paperTime+"&dayTimes="+dayTimes+"&monthTimes="+monthTimes+"&totalTimes="+totalTimes;
		}
	});
	
	
	function showcontent(){
		showopen = layer.open({
			title: false,
		  	type: 1,
			skin:'layer-box',
			area:'80%',
			closeBtn:0,
			offset: '20%',
		 	content: $('#cantfind') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
	}
	
	function closefrom(){
		var classType = $(".divselected").find("input[name='classType']").val();
		if(!classType){
			layer.alert("请选择班型",{icon:2});
			return false;
		}
		var subject = $(".divselected").find("input[name='subject']").val();
		if(!subject){
			layer.alert("请选择科目",{icon:2});
			return false;
		}
		var grade = $("#grade").val();
		setPaperConfig(classType,subject,grade);
	}
	
	
	function setPaperConfig(classType,subject,grade){
		var index =layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/jzbTest/weixin/getPaperConfig.html",{
			"classType" :classType,
			"subject" :subject,
			"grade" :grade
		  },function(data,status){
				layer.close(index); 
				if(data.flag==false){
					layer.alert(data.message,{icon:2});
				}else{
					var paperConfig = data.Data;
					$("#subject").val(paperConfig.mainConfig.subject);
					$("#subject").prev().html(paperConfig.mainConfig.subjectName);
					$("#paperTimeDesc").text(paperConfig.paperTime+"分钟");
					$("#totalQNum").text(paperConfig.totalQNum);
					$("#headMessage").html(paperConfig.mainConfig.headMessage);
					$("#monthConfigId").val(paperConfig.id);
					$("#mainConfigId").val(paperConfig.mainConfig.id);
					$("#month").val(paperConfig.month);
					$("#paperTime").val(paperConfig.paperTime);
					$("#examNameDiv").html(paperConfig.examName);
					$("#examName").val(paperConfig.examName);
					$("#dayTimes").val(paperConfig.mainConfig.dayTimes);
					$("#monthTimes").val(paperConfig.mainConfig.monthTimes);
					$("#totalTimes").val(paperConfig.mainConfig.totalTimes);
					layer.close(showopen);
				}
			},"json");
			return false;
	}
	
	
	
	</script>
</body>
</html>