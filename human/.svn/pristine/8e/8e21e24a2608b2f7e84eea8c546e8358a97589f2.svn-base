<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>投递记录</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
</head>
<body ontouchstart>
	<div class="page__bd">
        <!-- <div class="weui-cells__title">带跳转的列表项</div> -->
        <c:if test="${empty tdjl }">
        	<div style="margin-top:20px;margin-left:20px;">暂无投递记录</div>
        </c:if>
        <c:if test="${!empty tdjl }">
        <div class="weui-cells" style="margin-top:0px;">
        	
        	<c:forEach items="${tdjl }" var="jl">
        		 <a class="weui-cell weui-cell_access" href="javascript:;" style="height:65px;"  onclick="toTddetail('${jl.id}','${jl.companyName }','${jl.positionName }','${jl.resumeStatus }');">
		            <div class="weui-cell__bd" style="margin-top: 12px;margin-bottom: 12px;">
		              <p>${jl.positionName }</p>
		              <p style="font-size: 15px;color:#B5B5B5;margin-top:5px;">${jl.companyName }</p>
		            </div>
		            <div class="weui-cell__ft">
		            	<span style="font-size:15px;color:#B5B5B5">${jl.resumeStatus }</span>
		            </div>
		          </a>
        	</c:forEach>
        </div>
        </c:if>
      </div>
       <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
</body>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/jquery-weui.js"></script>
<script type="text/javascript">
function toTddetail(id,companyName,positionName,resumeStatus){
	location.href = "<%=basePath%>/front/interview/toTddetail.html?id="+id+"&companyName="+companyName+"&positionName="+positionName+"&resumeStatus="+resumeStatus+"&cutype=2";
}
</script>
</html>