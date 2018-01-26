<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>合肥新东方学校</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
</head>
<body ontouchstart>
	<div class="page__bd">
        <!-- <div class="weui-cells__title">带跳转的列表项</div> -->
        <div class="weui-cells" style="margin-top:0px;border-bottom:1px solid #f0f1f5; ">
          <a class="weui-cell weui-cell_access" href="javascript:;" style="height:65px;"> 
            <div class="weui-cell__bd" style="margin-top:12px;margin-bottom:12px;">
              <p>${resumeBase.positionName }</p>
              <p style="font-size: 15px;color:#B5B5B5;margin-top:10px;">${resumeBase.companyName }</p>
            </div>
            <div class="weui-cell__ft">
            	<span style="font-size:15px;color:#B5B5B5;margin-right:3px;">${resumeBase.resumeStatus }</span>
            </div>
          </a>
        </div>
        
        <c:if test="${!empty currRecord}">
        <div style="margin-top:10px;border: none;">
          <div style="width:18%;float: left;border: none;">
	          <img src="<%=basePath%>/static/front/image/school_head0.png" alt="" 
	          style="width:40px;height:40px;margin-left:15px;margin-right:15px;display:block;border-radius:20px;margin-top: 10px;">
          </div>
          <div style="margin-top: 10px;background-color: #5AB9A9;font-size:14px;color:#ffffff;float: left;width:70%;padding:15px 12px;border-radius:8px;">
          	${fn:split(currRecord.sendTime,'.')[0]}<br>${currRecord.sendComment }
          </div>
          <div style="float:left;width:5%;"></div>
        </div>
        <div>
	     	<a href="javascript:;" id="liBtn" class="weui-btn weui-btn_mini weui-btn_default" style="float: right;margin-top:5px; margin-right: 5%;">查看历史消息</a>
	     </div>
     </div>
     </c:if>
     <c:if test="${empty currRecord}">
     	<div style="margin-top: 10px;margin-left: 15px;">暂无消息</div>
     </c:if>
     <div class="page__bd" id="lsms" style="display:none;">
     		<c:forEach items="${records }" var="record" varStatus="status">
     			<div style="border: none;">
		          <div style="width:18%;float: left;border: none;">
			          <img src="<%=basePath%>/static/front/image/school_head0.png" alt="" 
			          style="width:40px;height:40px;margin-left:15px;margin-right:15px;display:block;border-radius:20px;
			          <c:if test="${status.index eq 0 }">margin-top:48px;</c:if>
			          <c:if test="${status.index gt 0 }">margin-top:12px;</c:if>
			          ">
		          </div>
		          <div style="margin-top: 10px;background-color: #5AB9A9;font-size:14px;color:#ffffff;float: left;width:70%;padding:15px 12px;border-radius:8px;">
		          	${fn:split(record.sendTime,'.')[0]}<br>${record.sendComment }
		          <%-- 	<c:if test="${fn:length(records) eq status.index+1 }">
		          		<div style="height: 50px;"></div>
		          	</c:if> --%>
		          </div>
		        </div>
     		</c:forEach>
     		<div style="clear: both;height:80px;"></div>
     </div>
     <div id="foot" style="position: fixed;">
     	<%@include file="/WEB-INF/view/front/home/foot.jsp" %>
     </div>
      
</body>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/jquery-weui.js"></script>
<script type="text/javascript">
$("#liBtn").bind("click",function(){
	var te = $(this).text();
	if(te=="查看历史消息"){
		$("#lsms").show();
		$(this).text("隐藏历史消息");
	}
	if(te=="隐藏历史消息"){
		$("#lsms").hide();
		$(this).text("查看历史消息");
	}
});

</script>
</html>