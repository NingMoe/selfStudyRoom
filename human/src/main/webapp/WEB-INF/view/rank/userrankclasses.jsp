<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>${rankInfo.rank_name}</title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %>
	<script type="text/javascript" src="<%=basePath %>/static/rank/userrankclasses.js"></script>
	<style type="text/css">
	.show_shuzi{
		width: 10%;
		float: left;
	}
	.show_class_code{
		width: 20%;
		float: left;
	}
	.show_class_name{
		width: 30%;
		float: left;
	}
	.show_teacher_name{
		width: 25%;
		float: left;
	}
	
	.show_surplus{
		width: 15%;
		float: left;
	}
	</style>
</head>
<body style="margin: 0; background-color: #${rankInfo.b_color_code};">
	<input id="id" type="hidden" value="${rankInfo.id }"/>
	<input id="rank_num" type="hidden" value="${rankInfo.ranke_num }"/>
	<input id="rank_lastnum" type="hidden" value="${rankInfo.ranke_lastnum }"/>
	<input id="refresh_time" type="hidden" value="${rankInfo.refresh_time }"/>
	<div style="width: 100%;">
		<div style="width: 100%;">
			<c:if test="${!empty rankInfo.head_img and rankInfo.head_img ne ''}">
				<img style="width: 100%; height: 200px;" alt="" src="http://hrms-img.oss-cn-shanghai.aliyuncs.com/${rankInfo.head_img }">
			</c:if>
			<c:if test="${empty rankInfo.head_img or rankInfo.head_img eq ''}">
				<img style="width: 100%; height: 200px;" alt="" src="<%=basePath%>/static/teacherservice/book/images/image-02.jpg">
			</c:if>
		</div>
		<div style="width: 100%; height: 4rem;">
			<div style="padding: 1rem 10px;">
				<div style="width: 40%; float: left;">
					<div style=" width:13rem; height: 2rem; line-height: 2rem; text-align: center; color:#${rankInfo.font_color}; font-size: 1.8rem;">续班排行榜</div>
				</div>
				<div style="width: 60%; float: left;">
					<div style="">
						<a style="float:right; display:block; text-align:center; text-decoration:none; color:#${rankInfo.font_color}; background-color: #ff0000; width: 15rem; border-radius:25px;"  href="${rankInfo.link_access}">${rankInfo.link_name }</a>
					</div>
				</div>
			</div>
		</div>
		<div id="classtable" style="min-height:220px; font-size: 14px; padding:0 5px; color:#${rankInfo.font_color}; text-align: center; overflow:hidden;">
			<!-- <div style="width: 100%;"><div class="show_shuzi">1</div><div class="show_class_code">PM1815041</div><div class="show_class_name">五年级暑假数学</div><div class="show_teacher_name">王燕的2</div><div class="show_surplus">剩余50</div></div>
			<div style="width: 100%;"><div class="show_shuzi">2</div><div class="show_class_code">PM1815041</div><div class="show_class_name">五年级暑假数学</div><div class="show_teacher_name">王燕的2</div><div class="show_surplus">剩余50</div></div>
			<div style="width: 100%;"><div class="show_shuzi">3</div><div class="show_class_code">PM1815041</div><div class="show_class_name">五年级暑假数学</div><div class="show_teacher_name">王燕的2</div><div class="show_surplus">剩余50</div></div>
			<div style="width: 100%;"><div class="show_shuzi">4</div><div class="show_class_code">PM1815041</div><div class="show_class_name">五年级暑假数学</div><div class="show_teacher_name">王燕的2</div><div class="show_surplus">剩余50</div></div>
			<div style="width: 100%;"><div class="show_shuzi">5</div><div class="show_class_code">PM1815041</div><div class="show_class_name">五年级暑假数学</div><div class="show_teacher_name">王燕的2</div><div class="show_surplus">剩余50</div></div>
			<div style="width: 100%;"><div class="show_shuzi">6</div><div class="show_class_code">PM1815041</div><div class="show_class_name">五年级暑假数学</div><div class="show_teacher_name">王燕的2</div><div class="show_surplus">剩余50</div></div> -->
		</div>
		<div>
			<c:if test="${!empty rankInfo.foot_img and rankInfo.foot_img ne ''}">
				<img style="width: 100%; height: 180px;" alt="" src="http://hrms-img.oss-cn-shanghai.aliyuncs.com/${rankInfo.foot_img }">
			</c:if>
			<c:if test="${empty rankInfo.foot_img or rankInfo.foot_img eq ''}">
				<img style="width: 100%; height: 180px;" alt="" src="<%=basePath%>/static/teacherservice/book/images/image-02.jpg">
			</c:if>
		</div>
	</div>
</body>
</html>