<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>投递详情</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<style type="text/css">
	.sptb{
		/* border: 1px solid #F0F1F5; */
		height:24px;
		line-height: 24px;
		font-size: 15px;
		text-align: center;
		color: #B5B5B5;
	}
</style>
</head>
<body ontouchstart  style="background-color: #f0f1f5;">
	<div class="page__bd">
        <!-- <div class="weui-cells__title">带跳转的列表项</div> -->
        <div class="weui-cells" style="margin-top:0px;border-bottom:8px solid #f0f1f5; ">
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
        
        <div class="weui-cells__title" style="font-size: 17px;color: #000000;background-color: white;margin-top:0px;margin-bottom:0px;padding-top: 12px;">简历状态</div>
        <div class="weui-cells" style="margin-top:0px;border-bottom:8px solid #f0f1f5; ">
        	<c:if test="${!empty nodes }">
        		<c:forEach items="${nodes }" var="node">
	        	<div class="weui-flex"  style="margin-top:12px;margin-bottom: 10px;">
	          		<div class="weui-flex__item sptb" style="color:#51B5A3;">${node.nodeName }</div>
	          		<div class="weui-flex__item sptb">
	          		<c:if test="${node.status eq '1'}">已办理</c:if>
	          		<c:if test="${node.status eq '2'}">进行中</c:if>
	          		</div>
	          		<div class="weui-flex__item sptb">
	          			<c:if test="${empty node.vtask}">无</c:if>
	          			<c:if test="${!empty node.vtask}">${node.vtask}</c:if>
	          		</div>
	          		<div class="weui-flex__item sptb">
		          		<c:if test="${empty node.task}">无</c:if>
		          		<c:if test="${!empty node.task}">
		           			<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary msxz" style="background-color:#51B5A3; height:22px;line-height: 22px;width: 90%;font-size: 12px;">
		           				面试须知
		           			</a>
		           		</c:if>
		           		<div style="display:none;">${node.task}</div>
	          		</div>
	          	</div>
	        	</c:forEach>
        	</c:if>
        	<c:if test="${empty nodes }">
        		<div style="margin-top: 10px;margin-left: 15px;margin-bottom: 10px;">暂未受理</div>
        	</c:if>
        </div>
      <c:if test="${fn:length(nodes) eq 1 or empty nodes}">
      <div class="weui-cells" style="margin-top:0px;">
          <a class="weui-cell weui-cell_access" href="javascript:;" onclick="wanshanJianli('${resumeBase.id}');"  style="height:36px;">
            <div class="weui-cell__bd" style="margin-top: 8px;margin-bottom: 8px;">
              <p>可继续去完善简历</p>
            </div>
          </a>
        </div>
      </div>
      </c:if>
       <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
</body>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/jquery-weui.js"></script>
<script type="text/javascript">
	function wanshanJianli(resumeId){
		location.href = "<%=basePath%>/front/resume/index.html?cutype=3&resumeId="+resumeId;
	}
	
	$(".msxz").on("click mouseout",function(event){
		  if(event.type == "click"){
			  var cause = $(this).next().html();
			  index1 = layer.tips(cause,$(this),{tips:[3,'#009688'],time:20000});
		  }else if(event.type == "mouseout"){
			  layer.close(index1);
		  }
		});
</script>
</html>