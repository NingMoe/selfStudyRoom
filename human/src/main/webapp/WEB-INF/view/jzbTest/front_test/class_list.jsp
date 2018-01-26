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
    <link href="<%=basePath %>/static/jzbTest/question/main.css" rel="stylesheet" media="screen">
    <link href="<%=basePath %>/static/jzbTest/question/style.css" rel="stylesheet" media="screen">
	<link href="<%=basePath %>/static/jzbTest/question/style2.css" rel="stylesheet" media="screen">
    <style>
    .main{margin-bottom:0;border-bottom:0}
	.main p{width:90%;margin-left:5%;margin-top:5%}
	input {-webkit-appearance:none; /*去除input默认样式*/}
    </style>
</head>
<body>
<body>
	<div style="display: none;"><img src="<%=basePath %>/static/jzbTest/question/wechat_ldx_info/images/wxtp.jpg"></div>
	<input type="hidden" id="paperId" value="${param.paperId }">
	<c:if test="${empty classList}">
		没有可选班级
	</c:if>
	<c:if test="${!empty classList}">
		<div class="banner-box"></div>
		<div class="main-box" >
			<img src="<%=basePath %>/static/jzbTest/question/wechat_ldx_info/images/activityname.png"><p class="activityname">上课地点</p>
			<div></div>
		</div>
		<div class="main" id="school" style="margin-top: 2rem;">
		<div class="main-btn" id="school_area">
				<c:forEach items="${areas }" var="area">
					<input type="button" onclick="getClass('${area.dataValue}',this);" value="${area.name }">
				</c:forEach>
    	</div>
		</div>
		<div class="main-box">
			<img src="<%=basePath %>/static/jzbTest/question/wechat_ldx_info/images/activityname.png"><p class="activityname">课程内容</p>
			<div class="clearfix"></div>
		</div>
		
		<div class="main">
		    <div id="classinfo" style="margin-top: 2rem;">
		    	<c:forEach items="${classList }" var="classInfo">
		    		<div class='lesson' style="margin-left:3%; display:none;">
		    			<input type="hidden" name="areaFlag" value="${classInfo.sAreaCode }">
		    			<table><tbody>
		    			<tr><td><p class="classname" style="width:100%;padding-top:0.3rem;">${classInfo.sClassName }</p></td></tr>
		    			<tr>
		    				<td>
			    				<div class='a'> 
			    					<div class='classcode fl' style='background-image:url(<%=basePath %>/static/jzbTest/question/wechat_ldx_info/image/logo.png)'></div>
			    					<p>${classInfo.sClassCode }</p>
			    				</div>
			    				<div class='b'>
			    					<div class='price fl' style='background-image:url(<%=basePath %>/static/jzbTest/question/wechat_ldx_info/image/logo.png)'></div>
			    					<p>${classInfo.dFee }</p>
			    				</div>
			    				<c:if test="${!empty classInfo.sAllTeacherName}">
			    					<c:if test="${fn:length(classInfo.sAllTeacherName) gt 5}">
			    						<div class='b' style="width: 70%;float: none;">
			    							<div class='teacher fl' 
			    							style="background-image:url(<%=basePath %>/static/jzbTest/question/wechat_ldx_info/image/logo.png)"></div>
			    							<p>${classInfo.sAllTeacherName }</p>
			    						</div>
			    					</c:if>
			    					<c:if test="${fn:length(classInfo.sAllTeacherName) <= 5}">
			    						<div class='b'>
			    							<div class='teacher fl' 
			    							style="background-image:url(<%=basePath %>/static/jzbTest/question/wechat_ldx_info/image/logo.png)"></div>
			    							<p>${classInfo.sAllTeacherName }</p>
			    						</div>
			    					</c:if>
			    				</c:if>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>
			    				<div class='classdate fl' 
			    				style='background-image:url(<%=basePath %>/static/jzbTest/question/wechat_ldx_info/image/logo.png)'></div>
			    				<p>${classInfo.sPrintTime }</p>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>
                			<div class='classlocation fl' style='background-image:url(<%=basePath %>/static/jzbTest/question/wechat_ldx_info/image/logo.png)'></div>
                			<p>${classInfo.sPrintAddress }</p>
                			</td>
                		</tr>
	                	</tbody>
	                	</table>
	                	 <div class='bottomdiv'>
	                		<button class="clselect ok" onclick="addClassCode('${classInfo.sClassCode }','${classInfo.dFee }','${classInfo.sClassName }',this);" >加入购物车</button>
	                	</div>
	                </div>
	               
	                
		    	</c:forEach>
		    </div>
		</div>
		<div style="height: 40px;"></div>
		<div class="btm" id="#BtnRegisterksbb">
			<p id="classprice" style="font-size: 13px;" class="cost">尚未选中任何课程</p>
			<p id="buy" class="qrbutton" onclick="buy()" value="0">去结算</p>
		</div>
	</c:if>
</body>
<script type="text/javascript">

Array.prototype.remove = function(val) {
	for (i in this) {
		if (this[i].sClassCode == val.sClassCode){
			this.splice(i,1);
			break;
		}
	}
};

var classList = [];

function getClass(areaCode,obj){
	$("#school_area").children("input").css("background-color","#3cc5be");
	$(obj).css("background-color","#bdbdbd");
	
	$(".lesson").hide();
	$(".lesson").each(function(){
		var areaFlag = $(this).find("input[name='areaFlag']").val();
		if(areaFlag==areaCode){
			$(this).show();
		}
	});
}

function addClassCode(sClassCode,dFee,sClassName,obj){
	var type = $(obj).html()=="加入购物车"?"1":"2";
	if(type=="1"){
		$(obj).html("已选中");	
		$(obj).css("background-color","#d6d3d3");
		classList.push({"sClassCode":sClassCode,"dFee":dFee,"sClassName":sClassName});
	}else{
		$(obj).html("加入购物车");	
		$(obj).css("background-color","#fff");
		classList.remove({"sClassCode":sClassCode,"dFee":dFee,"sClassName":sClassName});
	}
	getPrice();
}

function getPrice(){
	if(classList.length==0){
		$("#classprice").html("尚未选中任何课程");
	}else{
		var total = eval($(classList).map(function(){
			return this.dFee;
		}).get().join("+"));
		$("#classprice").html("已选中课程数:"+classList.length+",总价：￥"+total);
	}
}


function buy(){
	if(classList.length==0){
		alert("尚未选中任何课程");
	}else{
		var classCodes = JSON.stringify(classList);
		var paperId= $("#paperId").val();
		location.href = jsBasePath+"/jzbTest/weixin/toPzSbq.html?classCodes="+classCodes+"&paperId="+paperId;
	}
	
}
</script>
</html>
