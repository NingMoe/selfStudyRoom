<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>搜索</title>
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
				<input class="iconfont" style="width:55%;" id="search" placeholder="职位搜索"> 
				<span style="width: 10%;margin-left: 10px;">
					<a href="javascript:;" id="btn_search" style="color:white;">搜索</a>
				</span> 
			</div>
			<c:forEach var="item" items="${positionMap}"> 
				<div class="weui-flex" style="margin-top:12px;">
					<div class="weui-flex__item" >
						<div class="page preview js_show">
							<div style="padding-left: 12px;font-size: 14px;">热门${item.key }</div>
							<div class="page__bd" style="margin: 12px 8px 12px 12px;">
							<c:forEach items="${item.value}" var="position">  
							<div style="width: 33%;float: left;margin-top: 8px;">
								<a href="javascript:;" onclick="todetail(${position.id})" style="width:90%;padding:1px;font-size:11px;color: #888;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" class="weui-btn weui-btn_mini weui-btn_default">${position.name }</a>
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
</body>
<script src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript">
function todetail(positionId){
	location.href = "<%=basePath%>/front/home/toPositionDetail.html?positionId="+positionId;
}

$("#areaspan").select({
	title: "选择城市",
	items: ["合肥", "武汉"]
});

$("#areaspan").bind("change",function(){
	var areaName = this.value;
	location.href = "<%=basePath%>/front/home/index.html?areaName="+areaName;
});

$("#btn_search").bind("click",function(){
	var search = $("#search").val();
	if(!search){
		return false;
	}
	location.href="<%=basePath%>/front/home/toResult.html?search="+search;
});
</script>
</html>