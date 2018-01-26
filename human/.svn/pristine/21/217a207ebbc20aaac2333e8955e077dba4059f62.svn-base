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
	<link rel="stylesheet" href="<%=basePath %>/static/jzbTest/weixin/css/view5.css">
	 <style>
	input {-webkit-appearance:none; /*去除input默认样式*/}
    </style>
</head>
<body >
	<div class="main_body">
	<input type="hidden" id="oldSchool" 
	<c:if test="${!empty paper}">
						value="${paper.school }"
						</c:if>
	>
	
	<input type="hidden" id="oldArea" 
	<c:if test="${!empty paper}">
						value="${paper.area }"
						</c:if>
	>
	<form action="<%=basePath %>/jzbTest/weixin/toStartKs.html" id="startForm" method="post">
	<input type="hidden" id="monthConfigId" name="monthConfigId" value="${param.monthConfigId }">
	<input type="hidden" id="mainConfigId" name="mainConfigId" value="${param.mainConfigId }">
	<input type="hidden" id="classtype" name="classtype" value="${param.classType }">
	<input type="hidden" id="subject" name="subject" value="${param.subject }">
	<input type="hidden" id="paperTime" name="paperTime" value="${param.paperTime }">
	<input type="hidden" id="grade" name="grade" value="${param.gradeId }">
	<input type="hidden" id="examName" name="examName" value="${param.examName }">
	<input type="hidden" id="dayTimes" name="dayTimes" value="${param.dayTimes }">
	<input type="hidden" id="monthTimes" name="monthTimes" value="${param.monthTimes }">
	<input type="hidden" id="totalTimes" name="totalTimes" value="${param.totalTimes }">
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
					<div>
						${param.examName }
					</div>
				</div>
				<div class="main_head_title" style="width: 23%;">
					<img class="main_head_l_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/right.png">
				</div>
			</div>
			<div style="margin-top: 10px; padding-left: 15px; padding-right: 15px; overflow: hidden;">
				<div class="main_body_div">
					<div class="main_body_div_left">
						<span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span>
						<span>:</span>
					</div>
					<div class="main_body_div_right">
						<input class="main_body_input" id="name" name="name" type="text" placeholder="请输入姓名" 
						<c:if test="${!empty paper}">
						value="${paper.name }"
						</c:if>
						/>
					</div>
				</div>
				<div class="main_body_div">
					<div class="main_body_div_left">
						<span>手&nbsp;机&nbsp;号</span>
						<span>:</span>
					</div>
					<div class="main_body_div_right">
						<input class="main_body_input" id="phone" name="phone" type="text" placeholder="请输入手机号"
						<c:if test="${!empty paper }">
						value="${paper.phone }"
						</c:if>
						/>
					</div>
				</div>
				
				<div class="main_body_div">
					<div class="main_body_div_left">
						<span>验&nbsp;证&nbsp;码</span>
						<span>:</span>
					</div>
					<div class="main_body_div_right" style="position:relative;width: 72%;">
						<input class="main_body_input" style="width:65%;" id="jym" name="jym" type="text" placeholder="请输入验证码"/>
						<input id="fs" class="main_body_input" style="border-radius:7px;position:absolute;height: 2.3rem;margin-top:0.15rem;
						width:5rem;color:#ffffff;background-color:#32a995;right: 0;" type = "button" value="获取验证码"/>
					</div>
				</div>
				
				
				<div class="main_body_div">
					<div class="main_body_div_left">
						<span>行&nbsp;政&nbsp;校</span>
						<span>:</span>
					</div>
					<div class="main_body_div_right">
						<select class="main_body_select" id="area" name="area">
							<option value="">请选择</option>
							<c:forEach items="${areas }" var="area">
								<option value="${area.id }"
								<c:if test="${!empty paper and paper.area eq area.id}">
								selected="selected"
								</c:if>
								>${area.areaName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="main_body_div">
					<div class="main_body_div_left">
						<span>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校</span>
						<span>:</span>
					</div>
					<div class="main_body_div_right">
						<select class="main_body_select" id="school" name="school">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				<div class="main_body_div" id="startKs" style="cursor:pointer; background-color: #0db09b; border-radius:25px; text-align: center; color: #e4e4e4; font-size: 1.4rem;">
					开始答题
				</div>
			</div>
		</div>
		</form>
	</div>
	<script type="text/javascript">
	var clock;//全局定时器事件
  	var nums = 60;//全局60秒倒计时 
	initSchool();
  	
  	
	function checkKsTimes(index){
		var classtype = $("#classtype").val();
		var subject = $("#subject").val();
		var grade = $("#grade").val();
		var dayTimes = $("#dayTimes").val();
		var monthTimes = $("#monthTimes").val();
		var totalTimes = $("#totalTimes").val();
		var phone = $("#phone").val();
		var jym = $("#jym").val();
		$.ajax({
		type : "POST",
		dataType : "json",
		async:false,
		url : jsBasePath+"/jzbTest/weixin/checkJymAndKsTimes.html",
		data : {
			classtype : classtype,
			subject : subject,
			grade : grade,
			dayTimes : dayTimes,
			monthTimes : monthTimes,
			totalTimes : totalTimes,
			phone:phone,
			jym:jym
		},
		success : function(data){
			layer.close(index);
			if(!data.flag){		
				layer.alert(data.message,{icon:2},function(index){
					layer.close(index);
					if(!!data.companyId){
						location.href = jsBasePath+"/jzbTest/weixin/index.html?companyId="+data.companyId;						
					}
				});
			}else{
				$("#startForm").submit();
			}
		},
		error : function(data){
			alert(JSON.stringify(data));
			alert("网络错误,请联系管理员!");
		}
	});
		
	}
	
		function initSchool(){
			var areaId = $("#oldArea").val();
			if(!!areaId){
				$.post(jsBasePath+"/jzbTest/weixin/getSchoolByArea.html",{
					"areaId" :areaId,
					"gradeId" :$("#grade").val()
				  },function(data,status){
						if(data.flag==false){
							layer.alert(data.message,{icon:2});
						}else{
							var schools = data.Data;
							$("#school").get(0).options.length =1;
							$(schools).each(function(){
								 var option = "<option value='"+this.id +"' ";
								if(this.id==$("#oldSchool").val()){
									option += "selected='selected' ";
								}
								option += ">"+this.schoolName+"</option>";
								$("#school").append(option); 
							});
						}
					},"json");
			}
		}
	
		$("#area").bind("change",function(){
			var areaId = this.value;
			var index =layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/jzbTest/weixin/getSchoolByArea.html",{
				"areaId" :areaId,
				"gradeId" :$("#grade").val()
			  },function(data,status){
					layer.close(index); 
					if(data.flag==false){
						layer.alert(data.message,{icon:2});
					}else{
						var schools = data.Data;
						$("#school").get(0).options.length =1;
						$(schools).each(function(){
							$("#school").append("<option value='"+this.id+"'>"+this.schoolName+"</option>");
						});
					}
				},"json");
				return false;
		});
		
		$("#startKs").click(function(){
			var index =layer.load(3, {shade: [0.3]});
			var name = $("#name").val();
			var phone = $("#phone").val();
			var school = $("#school").val();
			var jym = $("#jym").val();
			console.log(school);
			if(name==""||phone==""||school==""){
				layer.alert("请填写完整再进行考试");
				layer.close(index);
			}else if($.trim(jym)==""){
				layer.alert("请填写验证码");
				layer.close(index);
			}else{
				checkKsTimes(index);
			}
		});
		
		$("#fs").bind("click",function(){
			var phone = $("#phone").val();
		    if($.trim(phone) == "" || phone == null){
		    	layer.alert("手机号不能为空！",{icon:2});
		    }else if(!(/^1[34578]\d{9}$/.test(phone))){ 
		    	layer.alert("手机号码有误!",{icon:2},function(index){
		    		$("#phone").html("").focus();
		    		layer.close(index);
		    	});
		    }else{
		    	$("#fs").attr("disabled",true);
		    	clock = setInterval('doLoop()', 1000);//每秒执行1次
		    	sendMsg(phone);
		    } 
		});
		
		function doLoop(){
			nums--;
			if(nums > 0){
				$("#fs").val("倒计时"+nums);
			}else{
				clearInterval(clock); //清除js定时器
				$("#fs").attr("disabled",false);
				$("#fs").val("获取验证码");
				nums = 60; //重置时间
			}
		}
		
		function sendMsg(phone){
			$.ajax({
				type : "POST",
				dataType : "JSON",
				url : jsBasePath+"/jzbTest/weixin/sendWxBindMsg.html",
				data : {
					telephone:phone
				},
				success : function(data){
					if(data.flag){
						layer.alert(data.message,{icon:1},function(index){
							layer.close(index);
						});
					}else{
						layer.alert(data.message,{icon:2},function(index){
							layer.close(index);
						});
					}
				},
				error : function(data){
					layer.alert("ajax请求错误！");
				}
			});
		}
	</script>
</body>
</html>