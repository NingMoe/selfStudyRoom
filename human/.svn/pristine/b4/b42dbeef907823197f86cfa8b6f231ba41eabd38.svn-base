<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %>
</head>
<body style="background-color: #F1F1F1">
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
						<button onClick="serchstudentinfo();" type="button"	class="layui-btn">
						<li class="fa fa-search"></li>
						&nbsp;搜索
					  	</button>
					</div>
				</div>
			</div>
		</fieldset>
    </div>
    
    <div style="width: 100%;">
		<div style="margin: 1% 5%; background-color: #ffffff; width: 90%; height: 550px;">
			<div style="font-size: 18px; text-align: center; padding-top: 30px; padding-bottom: 30px;">教师功底统计</div>
			<div style="height: 220px;">
				<div id="report_1" style="width: 50%;float: left;height: 220px;">
			
				</div>
				<div id="report_2" style="width: 50%;float: left;height: 220px;">
			
				</div>
			</div>
			<div style="height: 50px; margin-top: 15px;">
				<div style="width: 50%; float: left;font-size: 16px;color: #A1A1A1;text-align: center;">雅思高分人数占比</div>
				<div style="width: 50%; float: left;font-size: 16px;color: #A1A1A1;text-align: center;">TKT高分人数占比</div>
			</div>
			<div style="margin-top: 15px;">
				<div style="height: 260px; text-align: center; color: white;">
					<div id="report_3" style="width: 33%; float: left;">
						<div style="background-color: #F6BA55;  width: 120px; height: 130px; margin-left: 30%;">
							<div style="font-size: 1.5rem; padding-top: 20%; font-weight: bold;">TKT达标率</div>
							<div style="font-size: 1.5rem; font-weight: bold;"><br></div>
							<div id="ielts_tkt_count" style="font-size: 3rem; float: left; margin-top: 11px; width: 60%; font-weight: bold; text-align: right;"></div>
							<div style="font-size: 1.4rem; float: left; margin-top: 27px; width: 30%;">%</div>
						</div>
					</div>
					<div id="report_4" style="width: 33%; float: left;">
						<div style="background-color: #0FB6A0; width: 120px; height: 130px; margin-left: 30%;">
							<div style="font-size: 1.5rem; padding-top: 20%; font-weight: bold;">Celtaz证书</div>
							<div style="font-size: 1.5rem; font-weight: bold;">持有人数</div>
							<div id="ielts_celtaz_count" style="font-size: 3rem; float: left; margin-top: 11px; width: 60%; font-weight: bold; text-align: right;"></div>
							<div style="font-size: 1.4rem; float: left; margin-top: 27px; width: 30%;">人</div>
						</div>
					</div>
					<div id="report_5" style="width: 33%; float: left;">
						<div style="background-color: #F97752; width: 120px; height: 130px; margin-left: 30%;">
							<div style="font-size: 1.5rem; padding-top: 20%; font-weight: bold;">教师资格证</div>
							<div style="font-size: 1.5rem; font-weight: bold;">持有人数</div>
							<div id="ielts_certificate_count" style="font-size: 3rem; float: left; margin-top: 11px; width: 60%; font-weight: bold; text-align: right;"></div>
							<div style="font-size: 1.4rem; float: left; margin-top: 27px; width: 30%;">人</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="margin: 1% 5%; background-color: #ffffff; width: 90%; height: 1200px;">
			<div style="font-size: 18px; text-align: center; padding-top: 30px; padding-bottom: 10px;">教师积分统计</div>
			<div>
				<div style="height: 300px;">
					<div id="report_6" style="height: 300px; width: 33%; float: left;">
				
					</div>
					<div id="report_7" style="height: 300px; width: 33%; float: left;">
				
					</div>
					<div id="report_8" style="height: 300px; width: 33%; float: left;">
				
					</div>
				</div>
				<div style="height: 50px;">
					<div style="width: 33%; float: left;font-size: 16px;color: #A1A1A1;text-align: center;">教师功底</div>
					<div style="width: 33%; float: left;font-size: 16px;color: #A1A1A1;text-align: center;">教研成果</div>
					<div style="width: 33%; float: left;font-size: 16px;color: #A1A1A1;text-align: center;">教学成果</div>
				</div>
				<div style="color: #ffffff; height: 171px;">
					<div style="width: 33%; float: left;">
						<div style="background-color: #F6BA55;  width: 180px; height: 130px; margin-left: 26%;">
							<div style="font-size: 1.5rem; padding-top: 5%; font-weight: bold;text-align: center;">教师功底</div>
							<div style="font-size: 1.5rem; font-weight: bold; text-align: center;">积分最高教师</div>
							<div id="teacher_basics_integral_name" style="font-size: 2rem; margin-top: 20px; width: 95%; font-weight: bold; text-align: right;"></div>
							<div id="teacher_basics_integral_email" style="font-size: 1.5rem; width: 95%; text-align: right;"></div>
						</div>
					</div>
					<div style="width: 33%; float: left;">
						<div style="background-color: #F6BA55;  width: 180px; height: 130px; margin-left: 26%;">
							<div style="font-size: 1.5rem; padding-top: 5%; font-weight: bold;text-align: center;">教研成果</div>
							<div style="font-size: 1.5rem; font-weight: bold; text-align: center;">积分最高教师</div>
							<div id="teacher_research_integral_name" style="font-size: 2rem; margin-top: 20px; width: 95%; font-weight: bold; text-align: right;"></div>
							<div id="teacher_research_integral_email" style="font-size: 1.5rem; width: 95%; text-align: right;"></div>
						</div>
					</div>
					<div style="width: 33%; float: left;">
						<div style="background-color: #F6BA55;  width: 180px; height: 130px; margin-left: 26%;">
							<div style="font-size: 1.5rem; padding-top: 5%; font-weight: bold;text-align: center;">教学成果</div>
							<div style="font-size: 1.5rem; font-weight: bold; text-align: center;">积分最高教师</div>
							<div id="teacher_teachering_integral_name" style="font-size: 2rem; margin-top: 20px; width: 95%; font-weight: bold; text-align: right;"></div>
							<div id="teacher_teachering_integral_email" style="font-size: 1.5rem; width: 95%; text-align: right;"></div>
						</div>
					</div>
				</div>
				
				<div style="height: 300px;">
					<div id="report_9" style="height: 300px; width: 33%; float: left;">
				
					</div>
					<div id="report_10" style="height: 300px; width: 33%; float: left;">
				
					</div>
				</div>
				
				<div style="height: 50px;">
					<div style="width: 33%; float: left;font-size: 16px;color: #A1A1A1;text-align: center;">教学服务</div>
					<div style="width: 33%; float: left;font-size: 16px;color: #A1A1A1;text-align: center;">教师总积分</div>
				</div>
				
				<div style="color: #ffffff; height: 171px;">
					<div style="width: 33%; float: left;">
						<div style="background-color: #F6BA55;  width: 180px; height: 130px; margin-left: 26%;">
							<div style="font-size: 1.5rem; padding-top: 5%; font-weight: bold;text-align: center;">教学服务</div>
							<div style="font-size: 1.5rem; font-weight: bold; text-align: center;">积分最高教师</div>
							<div id="teacher_service_integral_name" style="font-size: 2rem; margin-top: 20px; width: 95%; font-weight: bold; text-align: right;"></div>
							<div id="teacher_service_integral_email" style="font-size: 1.5rem; width: 95%; text-align: right;"></div>
						</div>
					</div>
					<div style="width: 33%; float: left;">
						<div style="background-color: #F6BA55;  width: 180px; height: 130px; margin-left: 26%;">
							<div style="font-size: 1.5rem; padding-top: 5%; font-weight: bold;text-align: center;">教师总积分</div>
							<div style="font-size: 1.5rem; font-weight: bold; text-align: center;">积分最高教师</div>
							<div id="teacher_all_integral_name" style="font-size: 2rem; margin-top: 20px; width: 95%; font-weight: bold; text-align: right;"></div>
							<div id="teacher_all_integral_email" style="font-size: 1.5rem; width: 95%; text-align: right;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<script src="<%=basePath %>/static/echarts/echarts.3.5.min.js"></script>
	<script src="<%=basePath %>/static/echarts/ieltsshine.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/ielts/manager/teacherinfo.js"></script>
</body>
</html>