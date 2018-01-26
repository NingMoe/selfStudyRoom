<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>首页</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
</head>
<body ontouchstart>
	<div class="page flex js_show">
		<div class="page__bd page__bd_spacing">
			<div class="weui-flex" id="topar">
				<span id="areaspan" style="width: 20%; color: white; margin-left: 15px;">${selectedArea.areaName }
				<img alt="" style="width:15px;height:15px;" src="<%=basePath%>/static/front/image/arrows_down.png">
				</span>
				<input type="hidden" id="areaId" value="${selectedArea.id }">
				<input class="iconfont" style="width:60%;" id="search" placeholder="职位搜索"> 
				<!-- <span style="width: 10%;margin-left: 10px;">
					<a href="javascript:;" style="color:white;">搜索</a>
				</span> -->
			</div>
			<div>
				<img src="<%=basePath%>/static/front/image/index_bg.png" style="width: 100%; height: auto;">
			</div>
			<div class="weui-flex patitle">
				<div class="weui-flex__item"  style="width:25%">
					<div class="placeholder zwsx" style="text-align: center;">
						<img style="width:60%;height: auto;" alt="" src="<%=basePath%>/static/front/image/sh.png">
						<div style="color: #888;font-size: 14px;text-align: center;margin-top: 8px;">社会招聘</div>
					</div>
				</div>
				<div class="weui-flex__item" style="width:25%">
					<div class="placeholder zwsx" style="text-align: center;">
						<img style="width:60%;height: auto;" alt="" src="<%=basePath%>/static/front/image/xy.png">
						<div style="color: #888;font-size: 14px;text-align: center;margin-top: 8px;">校园招聘</div>
					</div>
				</div>
				<div class="weui-flex__item" style="width:25%">
					<div class="placeholder zwsx" style="text-align: center;">
						<img style="width:60%;height: auto;" alt="" src="<%=basePath%>/static/front/image/gp.png">
						<div style="color: #888;font-size: 14px;text-align: center;margin-top: 8px;">管培生招聘</div>
					</div>
				</div>
				<div class="weui-flex__item" style="width:25%;margin-bottom: 15px;">
					<div class="placeholder zwsx" style="text-align: center;">
						<img style="width:60%;height: auto;" alt="" src="<%=basePath%>/static/front/image/sx.png">
						<div style="color: #888;font-size: 14px;text-align: center;margin-top: 8px;">实习生招聘</div>
					</div>
				</div>
			</div>
			
			<c:forEach var="item" items="${positionMap}"> 
				<div class="weui-flex patitle">
					<div class="weui-flex__item">
						<div class="page preview js_show">
							<div style="border-left: 2px solid #00BD99; padding-left: 5px;">${item.key }</div>
							<div class="page__bd">
							<c:forEach items="${item.value}" var="position">  
								 <div class="weui-form-preview posi" >
								 <input name="positionId" type="hidden" value="${position.id }">
									<div class="weui-form-preview__hd" style="line-height: 1.5em;">
										<div class="weui-form-preview__item" style="text-align: left;">
											<div>
												<span style="font-size: 14px;">${position.name }</span> <span class="page__desc1">${position.postionClassification }</span> 
												<span class="page__desc2" style="color: #FFC7B9;">${position.salaryDesc }</span>
											</div>
										</div>
										<div class="weui-form-preview__item" style="text-align: left;margin-top: 3px;">
											<span class="page__desc">${position.companyName }</span> 
											<span class="page__desc2" style="color:#888;">招聘人数：${position.recruitmentNumber }</span>
										</div>
										<div class="weui-form-preview__item" style="text-align: left;margin-top: 3px;">
											<span class="page__desc3"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/location.png">&nbsp;&nbsp;${position.workCitys }</span>
											<span class="page__desc3" style="margin-left: 10px;"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/xueli.png">&nbsp;&nbsp;${position.requireDegree }</span>
											<span class="page__desc3" style="margin-left: 10px;"><img style="width:16px;height:auto;" alt="" src="<%=basePath%>/static/front/image/jingyan.png">&nbsp;&nbsp;${position.workingMonth }</span>
										</div>
									</div>
								</div>
							</c:forEach>   
						    </div>
						</div>
					</div>
				</div>  
			</c:forEach>  
		</div>
		<div style="height:60px;"></div>
	</div>
	<%@include file="/WEB-INF/view/front/home/foot.jsp" %>

</body>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/city-picker.js"></script>
<script type="text/javascript">
$("#areaspan").select({
	title: "选择城市",
	items: ["合肥", "武汉"]
});

$("#areaspan").bind("change",function(){
	var areaName = this.value;
	location.href = "<%=basePath%>/front/home/index.html?areaName="+areaName;
});

$(".posi").bind("click",function(){
	var positionId = $(this).find("input[name='positionId']").val();
	location.href = "<%=basePath%>/front/home/toPositionDetail.html?positionId="+positionId;
});

$("#search").bind("focus",function(){
	location.href="<%=basePath%>/front/home/toSearch.html";
});

$(".zwsx").bind("click",function(){
	var classfication = $(this).find("div").html();
	location.href = "<%=basePath%>/front/home/toAttrResult.html?classfication="+classfication;
});
</script>
</html>