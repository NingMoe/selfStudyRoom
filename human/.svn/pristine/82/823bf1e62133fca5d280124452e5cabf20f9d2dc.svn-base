<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>消息通知</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
</head>
<body ontouchstart>
	<div class="page__bd">
	 <c:if test="${empty tdjl }">
        	<div style="margin-top:20px;margin-left:20px;">暂无消息记录</div>
        </c:if>
     <c:if test="${!empty tdjl }">
        <div class="weui-cells" style="margin-top:0px;">
        <c:forEach items="${tdjl }" var="jl">
        	 <a class="weui-cell weui-cell_access" href="javascript:;" style="height:65px;"  onclick="toMxdetail('${jl.id}','${jl.companyName }','${jl.positionName }','${jl.resumeStatus }');">
	          	<div class="weui-cell__hd">
	          		<img src="<%=basePath%>/static/front/image/school_head1.png" alt="" style="width:50px;height:50px;margin-right:5px;display:block;border-radius:25px;">
	          	</div>
	            <div class="weui-cell__bd" style="margin-left:10px; margin-top: 12px;margin-bottom: 12px;">
	              <p>${jl.positionName }</p>
	              <p style="font-size: 15px;color:#B5B5B5;margin-top:5px;">${jl.companyName }</p>
	            </div>
	            <div class="weui-cell__ft">
	            	
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
function toMxdetail(id,companyName,positionName,resumeStatus){
	location.href = "<%=basePath%>/front/interview/toMxdetail.html?id="+id+"&companyName="+companyName+"&positionName="+positionName+"&resumeStatus="+resumeStatus+"&cutype=2";
}
</script>
</html>