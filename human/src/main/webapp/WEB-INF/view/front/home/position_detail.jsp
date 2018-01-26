<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>职位详情页</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
</head>
<body ontouchstart>
	<div class="page flex js_show">
		<div class="page__bd page__bd_spacing">
			<div class="weui-form-preview">
				<div class="weui-form-preview__hd" style="line-height: 1.5em;">
					<div class="weui-form-preview__item" style="text-align: left;margin-top: 10px;">
						<div>
							<span style="font-size:18px;">${position.name }</span> 
							<span class="page__desc5">${position.postionClassification }</span> 
							<span class="page__desc2" style="color: #FFC7B9;font-size: 14px;">${position.salaryDesc }</span>
						</div>
					</div>
					<div class="weui-form-preview__item" style="text-align: left;margin-top:4px;">
						<span class="page__desc" style="font-size: 16px;">${position.companyName }</span> 
						<span class="page__desc2" style="color:#888;">招聘人数：${position.recruitmentNumber }</span>
					</div>
					<div class="weui-form-preview__item" style="text-align: left;margin-top:8px;border-bottom: 1px solid #bbb;padding-bottom: 10px;">
						<span class="page__desc4"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/location.png">&nbsp;&nbsp;${position.workCitys }</span>
						<span class="page__desc4" style="margin-left: 15px;"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/xueli.png">&nbsp;&nbsp;${position.requireDegree }</span>
						<span class="page__desc4" style="margin-left: 15px;"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/jingyan.png">&nbsp;&nbsp;${position.workingMonth }</span>
					</div>
					<div class="weui-form-preview__item" style="text-align: left;margin-top:6px;padding-top: 10px;">
						<span class="page__desc3" style="margin-top: 6px;font-size: 15px;"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/address.png">&nbsp;&nbsp;工作地点：${position.jobAddr }</span>
					</div>
				</div>
				
				<div class="weui-form-preview__hd" style="line-height: 1.5em;">
					<div class="weui-form-preview__item" style="text-align: left;margin-top:6px;">
						<div>
							<span style="font-size:16px;"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/liangdian.png">&nbsp;&nbsp;职位亮点</span> 
						</div>
					</div>
					<div class="weui-form-preview__item" style="text-align: left;margin-top:6px;">
						<c:forEach items="${position.highLight }" var="hl">
							<a href="javascript:;" style="width:23%;margin-top:0px; padding:1px;font-size:11px;color: #888;
							<c:if test="${hl.highLightVal eq '1' }">background-color:#8CF0DE;</c:if>
							text-overflow: ellipsis;overflow: hidden;white-space: nowrap;"
							 class="weui-btn weui-btn_mini weui-btn_default">${hl.highLight }</a>
						</c:forEach>
					</div>
				</div>
				
				<div class="weui-form-preview__hd" style="line-height: 1.5em;">
					<div class="weui-form-preview__item" style="text-align: left;">
						<div>
							<span style="font-size:16px;"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/zwms.png">&nbsp;&nbsp;职位描述</span> 
						</div>
					</div>
					<div class="weui-form-preview__item" style="text-align: left;margin-top: 3px;">
						<div class="page__desc" style="margin-left: 20px;">
						    ${position.obContent }
						</div>
					</div>
				</div>
				
				<div class="weui-form-preview__hd" style="line-height: 1.5em;">
					<div class="weui-form-preview__item" style="text-align: left;">
						<div>
							<span style="font-size:16px;"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/gsjs.png">&nbsp;&nbsp;任职资格</span> 
						</div>
					</div>
					<div class="weui-form-preview__item" style="text-align: left;margin-top: 3px;">
						<div class="page__desc" style="margin-left: 20px;">
							${position.qualifications }
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="height:60px;"></div>
	</div>
	
	<div class="weui-tabbar" style="position: fixed;border: none;height: 50px;">
		<div class="weui-tabbar__icon" onclick="fastDelivery(${position.id});" style="background-color: #6ec2b4;width:35%;height:50px;color: white;text-align: center;">
			<p style="padding:15px 0px;">快速投递</p>
		</div>
		<div class="weui-tabbar__icon" onclick="resumeDelivery(${position.id});" style="background-color: #31a894;width: 37%;height:50px;color: white;text-align: center;">
			<p style="padding:15px 0px;">简历投递</p>
		</div>
		<div class="weui-tabbar__icon" style="background-color: #fd5f39;width: 28%;height:50px;color: white;text-align: center;">
			<p style="padding:15px 0px;">
			<c:if test="${isHasTd}">已投递</c:if>
			<c:if test="${not isHasTd}">未投递</c:if>
			</p>
		</div>
	</div>
</body>
<script src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript">
$("#search").bind("focus",function(){
	location.href="<%=basePath%>/front/home/toSearch.html";
});
//快速投递
function fastDelivery(positionId){
	if('${isHasTd}'=="true"){
		$.alert("你已经投递过该岗位");
	}else{
		location.href="<%=basePath%>/front/fastDelivery/index.html?positionId="+positionId;	
	}
	
}
//简历投递
function resumeDelivery(positionId){
	if('${isHasTd}'=="true"){
		$.alert("你已经投递过该岗位");
	}else{
		location.href="<%=basePath%>/front/resumeDelivery/index.html?cutype=3&positionId="+positionId;
	}	
}

</script>
</html>