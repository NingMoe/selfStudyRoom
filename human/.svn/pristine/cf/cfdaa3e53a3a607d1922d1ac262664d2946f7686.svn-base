<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>面试</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
</head>
<body ontouchstart>
	<div class="page__bd">
        <!-- <div class="weui-cells__title">带跳转的列表项</div> -->
        <div class="weui-cells" style="margin-top:0px;">
          <a class="weui-cell weui-cell_access" href="javascript:;" onclick="toTdjl();" style="height:40px;">
            <div class="weui-cell__bd">
              <p>投递记录</p>
            </div>
            <div class="weui-cell__ft">
   <%--          <c:if test="${tdCnt gt 0 }">
            	<span class="weui-badge"
			style="position: absolute; top: -0.75em; right:2em;">${tdCnt }
			</span></c:if> --%>
            </div>
          </a>
          <a class="weui-cell weui-cell_access" href="javascript:;" onclick="toMessjl();" style="height:40px;">
            <div class="weui-cell__bd">
              <p>消息通知</p>
            </div>
            <div class="weui-cell__ft">
            	<%-- <c:if test="${smsCnt gt 0 }">
            	<span
			class="weui-badge"
			style="position: absolute; top: -0.75em; right:2em;">${smsCnt }
			</span></c:if> --%>
            </div>
          </a>
        </div>
      </div>
 <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
</body>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/jquery-weui.js"></script>
<script type="text/javascript">
	function toTdjl(){
		location.href = "<%=basePath%>/front/interview/toTdjl.html?cutype=2";
	}
	function toMessjl(){
		location.href = "<%=basePath%>/front/interview/toMessjl.html?cutype=2";
	}
</script>
</html>