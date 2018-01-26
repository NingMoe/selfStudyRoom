<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="background-color: #F0F0F0; width: 100%;">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="format-detection" content="telephone=no"/>
		<%@include file="/WEB-INF/view/jzbTest/jzbtaglib.jsp" %>
	<title>新东方在线测试首页</title>
	<link rel="stylesheet" href="<%=basePath %>/static/jzbTest/weixin/css/view1.css">
	<SCRIPT type="text/javascript">
	$(function() {
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function () {
				window.history.pushState('forward', null, '#');
				window.history.forward(1);
			});
		}
		window.history.pushState('forward', null, '#');
		window.history.forward(1);
	});
</SCRIPT>
</head>	
<body >
	<div class="main_body">
		<div class="main_head">
			<img class="main_head_background_img" alt="" src="${fileurl }${indexConfig.headImg }">
			<div style="padding-left: 5px; padding-right: 10px;">
				<div class="main_head_tig">
					<div class="main_head_tig_name">考试说明</div>
					<img class="main_head_tig_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/tig.png">
					<div class="main_head_tig_panel">${indexConfig.content }</div>
				</div>
			</div>
		</div>
		<div class="main_tap">
			<div style="padding-left: 10px; padding-right: 10px;">
				<div class="main_tap_table">
					<div style="padding: 20px 10px 0 10px">
						<div class="main_tap_tig">
							<img class="main_tap_tig_img" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/calendar.png">
							<div style="color: #ff9800; margin-left: 15px; font-size: 1.1rem; width: 80px; float: left;">选择年级</div>
							<div class="main_tap_tip_btn">历史成绩</div>
						</div>
						<div style="background-color: #dedede; height: 0.02rem;"></div>
					</div>
						<div style="padding: 10px 0 20px 0;">
							<div style="overflow: hidden;">
								<c:forEach items="${grades }" var="grade">
									<div class="main_tap_select">${grade.gradeName }
									<input type="hidden" name="ids" value="${grade.ids }">
									<input type="hidden" name="gradeName" value="${grade.gradeName }">
									</div>
								</c:forEach>
							</div>
						</div>
					
				</div>
			</div>
		</div>

		<div class="main_foot">
			<div style="padding-left: 10px; padding-right: 10px; padding-bottom: 15px;">
			${indexConfig.remark }
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(".main_tap_select").click(function(){
			var gradeId = $(this).find("input[name='ids']").val();
			var gradeName = $(this).find("input[name='gradeName']").val();
			location.href = jsBasePath+"/jzbTest/weixin/toPaperConfig.html?gradeId="+gradeId+"&gradeName="+gradeName;
		});
		
		$(".main_tap_tip_btn").click(function(){
			location.href = jsBasePath+"/jzbTest/weixin/toPapers.html";
		});
	</script>
</body>
</html>