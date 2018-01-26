<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<script type="text/javascript" src="<%=basePath %>/static/ielts/teacherself/integralchange.js"></script>
</head>
<body style="padding:10px;">
	<div class="main-wrap">
		<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
			<div class="layui-form">
				<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
				<div class="layui-form-item collapse in" id="collapseOne">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 120px;">统计开始时间</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input id="left_integral_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 120px;">统计结束时间</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input id="right_integral_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input">
						</div>
					</div>
					  
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<button id="search_teacherinfo" type="button" class="layui-btn">
						<li class="fa fa-search"></li>
							&nbsp;确认
					  	</button>
					</div>
				</div>
			</div>
		</fieldset>
    </div>
    
    <div style="width: 100%;">
	    <div class="alertFrom" style="width: 400px; width : 450px; float: left; text-align: center;">
	    
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 85px; font-size: 21px">教师功底</label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">项目</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">成果</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">模块分数</label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">TKT成绩</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 30%" id="tkt_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="tkt_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">雅思成绩</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 30%" id="ielts_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="ielts_integral">15分</label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">教师资格证</label>
					</div>
					<div id="is_teacher_certificate_div" class="layui-inline" style="margin: 0 auto; width: 32%;">
						<%-- <c:if test="${flag }">
							<c:if test="${ieltsTeacherCertificate.is_teacher_certificate eq 0 }">
								<label class="layui-form-label" style="width: 30%">无</label>
							</c:if>
							<c:if test="${ieltsTeacherCertificate.is_teacher_certificate eq 1 }">
								<label class="layui-form-label" style="width: 30%">有</label>
							</c:if>
						</c:if> --%>
					</div>
					<div id="is_teacher_certificate_fen" class="layui-inline" style="margin: 0 auto; width: 32%;">
						<%-- <c:if test="${flag }">
							<c:if test="${ieltsTeacherCertificate.is_teacher_certificate eq 0 }">
								<label class="layui-form-label">0分</label>
							</c:if>
							<c:if test="${ieltsTeacherCertificate.is_teacher_certificate eq 1 }">
								<label class="layui-form-label">5分</label>
							</c:if>
						</c:if> --%>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">Celta证书</label>
					</div>
					<div id="is_celta_div" class="layui-inline" style="margin: 0 auto; width: 32%;">
						<%-- <c:if test="${flag }">
							<c:if test="${ieltsTeacherCertificate.is_celta eq 0 }">
								<label class="layui-form-label" style="width: 30%">无</label>
							</c:if>
							<c:if test="${ieltsTeacherCertificate.is_celta eq 1 }">
								<label class="layui-form-label" style="width: 30%">有</label>
							</c:if>
						</c:if> --%>
					</div>
					<div  id="is_celta_fen"class="layui-inline" style="margin: 0 auto; width: 32%;">
						<%-- <c:if test="${flag }">
							<c:if test="${ieltsTeacherCertificate.is_celta eq 0 }">
								<label class="layui-form-label">0分</label>
							</c:if>
							<c:if test="${ieltsTeacherCertificate.is_celta eq 1 }">
								<label class="layui-form-label">10分</label>
							</c:if>
						</c:if> --%>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 64%;">
						<label class="layui-form-label" style="font-size: 21px; width: 100px;">模块积分</label>
					</div>
					
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="font-size: 21px;" id="model1">28分</label>
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="alertFrom" style="width: 400px; width : 450px; float: left; text-align: center;">
	    
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 85px; font-size: 21px">教学成果</label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">项目</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">成果</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">模块分数</label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">成绩回收率</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 30%" id="back_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="back_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">学生达分率</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 30%" id="goto_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="goto_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">高分学员数</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="text-align: center; width: 80%" id="hight_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="hight_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">赛课</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%; text-align: center;" id="matchclass_source">
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;" id="matchclass">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 64%;">
						<label class="layui-form-label" style="font-size: 21px; width: 100px;">模块积分</label>
					</div>
					
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="font-size: 21px;" id="model2">32分</label>
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="alertFrom" style="width: 400px; width : 450px; float: left; text-align: center;">
	    
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 85px; font-size: 21px">教研表现</label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">项目</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">成果</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">模块分数</label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">教研出勤率</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 33%" id="attendance_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="attendance_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">教研文章</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 33%" id="article_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="article_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">教学分享</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 33%" id="share_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="share_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 64%;">
						<label class="layui-form-label" style="font-size: 21px; width: 100px;">模块积分</label>
					</div>
					
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="font-size: 21px;" id="model3"></label>
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="alertFrom" style="width: 400px; width : 450px; float: left; text-align: center;">
	    
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 85px; font-size: 21px">教学服务</label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">项目</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">成果</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">模块分数</label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">运营支持</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 30%" id="operate_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="operate_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label">教学投诉</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 30%" id="complaint_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="complaint_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 50%">无教学反馈(超过2次)</label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="width: 30%" id="feedback_source"></label>
					</div>
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" id="feedback_integral"></label>
					</div>
				</div>
			</div>
			
			<div class="layui-form" style="width: 450px;">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto; width: 64%;">
						<label class="layui-form-label" style="font-size: 21px; width: 100px;">模块积分</label>
					</div>
					
					<div class="layui-inline" style="margin: 0 auto; width: 32%;">
						<label class="layui-form-label" style="font-size: 21px;" id="model4"></label>
					</div>
				</div>
			</div>
			
		</div>
		
	</div>
</body>
</html>