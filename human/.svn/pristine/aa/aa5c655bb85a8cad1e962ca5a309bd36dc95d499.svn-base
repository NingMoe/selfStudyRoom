<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
</head>
<body ontouchstart>
	<div class="weui-tabbar" style="position: fixed;">
		<a href="javascript:;" onclick="toIndex();" 
			<c:if test="${param.cutype eq '1' or empty param.cutype}">class="weui-tabbar__item weui-bar__item--on"</c:if>
			<c:if test="${param.cutype ne '1' }">class="weui-tabbar__item"</c:if>
		>
			<div class="weui-tabbar__icon">
				<img 
				<c:if test="${param.cutype eq '1' or empty param.cutype}">src="<%=basePath%>/static/front/image/sy1.png"</c:if>
				<c:if test="${param.cutype ne '1' }">src="<%=basePath%>/static/front/image/sy0.png"</c:if>
					alt="">
			</div>
			<p class="weui-tabbar__label">首页</p>
		</a> 
		<a href="javascript:;" onclick="toInterview();" 
			<c:if test="${param.cutype eq '2' }">class="weui-tabbar__item weui-bar__item--on"</c:if>
			<c:if test="${param.cutype ne '2' }">class="weui-tabbar__item"</c:if>
		> 
			<div class="weui-tabbar__icon">
				<img 
				<c:if test="${param.cutype eq '2' }">src="<%=basePath%>/static/front/image/ms1.png"</c:if>
				<c:if test="${param.cutype ne '2' }">src="<%=basePath%>/static/front/image/ms0.png"</c:if>
					alt="">
			</div>
			<p class="weui-tabbar__label">面试</p>
		</a> 
		<a href="javascript:;" onclick="toResume();" 
			<c:if test="${param.cutype eq '3' }">class="weui-tabbar__item weui-bar__item--on"</c:if>
			<c:if test="${param.cutype ne '3' }">class="weui-tabbar__item"</c:if>
		> 
			<div class="weui-tabbar__icon">
				<img 
				<c:if test="${param.cutype eq '3' }">src="<%=basePath%>/static/front/image/jl1.png"</c:if>
				<c:if test="${param.cutype ne '3' }">src="<%=basePath%>/static/front/image/jl0.png"</c:if>
					alt="">
			</div>
			<p class="weui-tabbar__label">简历</p>
		</a> 
		<a href="javascript:;" onclick="toMy();"
			<c:if test="${param.cutype eq '4' }">class="weui-tabbar__item weui-bar__item--on"</c:if>
			<c:if test="${param.cutype ne '4' }">class="weui-tabbar__item"</c:if>
		> 
			<div class="weui-tabbar__icon">
				<img 
				<c:if test="${param.cutype eq '4' }">src="<%=basePath%>/static/front/image/wd0.png"</c:if>
				<c:if test="${param.cutype ne '4' }">src="<%=basePath%>/static/front/image/wd1.png"</c:if>
					alt="">
			</div>
			<p class="weui-tabbar__label">更多</p>
		</a>
	</div>

</body>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/city-picker.js"></script>
<script type="text/javascript">
function toIndex(){
	location.href = "<%=basePath%>/front/home/index.html?cutype=1";
}

function toInterview(){
	location.href = "<%=basePath%>/front/interview/toInterview.html?cutype=2";
}
//简历按钮事件
function toResume(){
	location.href = "<%=basePath%>/front/resume/index.html?cutype=3&resumeId=${param.resumeId }&positionId=${param.positionId }";
}
//我的事件
function toMy(){
	location.href = "<%=basePath%>/front/mine/tomain.html?cutype=4";
}
</script>
</html>