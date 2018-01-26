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
	<link rel="stylesheet" href="<%=basePath %>/static/jzbTest/weixin/css/view7.css">
</HEAD>
<!-- <BODY onload="StopBack();"
  onpageshow="if (event.persisted) StopBack();" onunload=""> -->
<BODY> 
	<div class="main_body">
		<div style="margin-top: 15px;">
			<div style="padding: 0 5rem;"><img style="width: 100%;" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/true.png"></div>
			<div style="padding: 0 1rem; margin-top: -20px;">
				<div style="width: 100%; background-color: #fef4db; box-shadow:2px 2px 2px #fbb143; border-radius: 6px;">
					<div style="padding: 10px;">
						<div style="border: 1px #ff9800 dashed; overflow: hidden;">
							<div style="margin: 2rem 0 0 2rem; width: 100%; font-size: 1rem;">本次考试结果为：</div>
							<div style="color: #ff9800; font-size: 4rem; text-align: center; font-weight: bold;">通过</div>
							<div style="margin-top:1rem; font-size: 0.8rem; padding: 0 2rem;"><div style="width: 100%;">${msg }</div> </div>
							
							<c:if test="${isExistClass eq '0' }">
								<div style="padding: 2rem 2rem;margin-bottom:1rem;">
									<div style="width:50%;float: left;">
										<div style="margin: 0 auto;width:85%;cursor:pointer; background-color: #ff9800; border-radius:15px; color: #fff; text-align: center; height:2rem; line-height:2rem; font-size: 1rem;" onclick="bmClass('${paper.id }')">报名课程</div>
									</div>
									<div style="width:50%;float: left;margin: 0 auto;">
										<div style="margin: 0 auto;width:85%;cursor:pointer; background-color: #ff9800; border-radius:15px; color: #fff; text-align: center; height:2rem; line-height:2rem; font-size: 1rem;" onclick="viewError('${paper.id }')">查看错题</div>
									</div>
								</div>
							</c:if>
							<c:if test="${isExistClass eq '1' }">
								<div style="padding: 2rem 2.3rem;">
									<div style="width: 100%;">
										<div style="cursor:pointer; background-color: #ff9800; border-radius: 25px; color: #fff; text-align: center; height: 3rem; line-height: 3rem; font-size: 1rem;" onclick="viewError('${paper.id }')">查看错题</div>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div style="margin-top: 15px;">
			<!-- <div style="padding: 0 1rem;">
				二维码展示
			</div> -->
		</div>
		
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jzbTest/cookie_util.js"></script>
<script type="text/javascript">
deleteCookieByPre("JZBAS");

function bmClass(paperId){
	location.href = jsBasePath+ "/jzbTest/weixin/toSelectClass.html?paperId="+paperId;
}

function viewError(paperId){
	location.href = jsBasePath+ "/jzbTest/weixin/toErrorQusstion.html?paperId="+paperId;
}
</script>
</html>