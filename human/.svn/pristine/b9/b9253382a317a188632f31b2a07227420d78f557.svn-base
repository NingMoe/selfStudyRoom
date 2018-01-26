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
		<div style="margin: 1% 5%; background-color: #ffffff; width: 90%; height: 375px;">
			<div style="font-size: 18px; text-align: center; padding-top: 30px; padding-bottom: 30px;">考试与成绩管理</div>
			<div>
				<div id="report_1" style="width: 50%; height: 220px;float: left;">
			
				</div>
				<div id="report_2" style="width: 50%; height: 220px;float: left;">
			
				</div>
			</div>
			<div>
				<div style="width: 50%; float: left;font-size: 16px;color: #A1A1A1;text-align: center;margin-top: 15px;">报考率</div>
				<div style="width: 50%; float: left;font-size: 16px;color: #A1A1A1;text-align: center;margin-top: 15px;">成绩回收率</div>
			</div>
		</div>
		<div style="margin: 1% 5%; background-color: #ffffff; width: 90%;">
			<div style="font-size: 18px; text-align: center; padding-top: 30px; padding-bottom: 10px;">平均成绩分析</div>
			<div>
				<div id="report_3" style="height: 300px;">
			
				</div>
			</div>
		</div>
		<div style="margin: 1% 5%; background-color: #ffffff; width: 90%;">
			<div style="font-size: 18px; text-align: center; padding-top: 30px; padding-bottom: 10px;">分数段分布</div>
			<div>
				<div id="report_4" style="height: 300px;">
			
				</div>
			</div>
		</div>
		<div style="margin: 1% 5%; background-color: #ffffff; width: 90%;">
			<div style="font-size: 18px; text-align: center; padding-top: 30px; padding-bottom: 20px;">高分学员统计</div>
			<div>
				<div id="report_5" style="height: 260px; text-align: center;">
					<div style="background-color: #F6BA55; float: left; width: 100px; height: 130px;margin: 10px 5%; color: white;">
						<div style="font-size: 1.5rem; padding-top: 20%; font-weight: bold;">雅思</div>
						<div style="font-size: 1.5rem; font-weight: bold;">高分学员数</div>
						<div id="ielts_count_ielts" style="font-size: 3rem; float: left; margin-top: 11px; width: 60%; font-weight: bold; text-align: right;">24</div>
						<div style="font-size: 1.4rem; float: left; margin-top: 27px; width: 30%;">人</div>
					</div>
					<div style="background-color: #0FB6A0; float: left; width: 100px; height: 130px;margin: 10px 5%; color: white;">
						<div style="font-size: 1.5rem; padding-top: 20%; font-weight: bold;">精品班</div>
						<div style="font-size: 1.5rem; font-weight: bold;">高分学员数</div>
						<div id="ielts_count_boutique" style="font-size: 3rem; float: left; margin-top: 11px; width: 60%; font-weight: bold; text-align: right;">24</div>
						<div style="font-size: 1.4rem; float: left; margin-top: 27px; width: 30%;">人</div>
					</div>
					<div style="background-color: #F97752; float: left; width: 100px; height: 130px;margin: 10px 5%; color: white;">
						<div style="font-size: 1.5rem; padding-top: 20%; font-weight: bold;">魔鬼营</div>
						<div style="font-size: 1.5rem; font-weight: bold;">高分学员数</div>
						<div id="ielts_count_devil" style="font-size: 3rem; float: left; margin-top: 11px; width: 60%; font-weight: bold; text-align: right;">24</div>
						<div style="font-size: 1.4rem; float: left; margin-top: 27px; width: 30%;">人</div>
					</div>
					<div style="background-color: #62D9FF; float: left; width: 100px; height: 130px;margin: 10px 5%; color: white;">
						<div style="font-size: 1.5rem; padding-top: 20%; font-weight: bold;">1对1</div>
						<div style="font-size: 1.5rem; font-weight: bold;">高分学员数</div>
						<div id="ielts_count_onevsone" style="font-size: 3rem; float: left; margin-top: 11px; width: 60%; font-weight: bold; text-align: right;">24</div>
						<div style="font-size: 1.4rem; float: left; margin-top: 27px; width: 30%;">人</div>
					</div>
					<div style="background-color: #F6BA55; float: left; width: 100px; height: 130px;margin: 10px 5%; color: white;">
						<div style="font-size: 1.5rem; padding-top: 20%; font-weight: bold;">1对6</div>
						<div style="font-size: 1.5rem; font-weight: bold;">高分学员数</div>
						<div id="ielts_count_onevssix" style="font-size: 3rem; float: left; margin-top: 11px; width: 60%; font-weight: bold; text-align: right;">24</div>
						<div style="font-size: 1.4rem; float: left; margin-top: 27px; width: 30%;">人</div>
					</div>
				</div>
			</div>
		</div>
		<div style="margin: 1% 5%; background-color: #ffffff; width: 90%;">
			<div style="font-size: 18px; text-align: center; padding-top: 30px; padding-bottom: 20px;">达分提分学员分析</div>
			<div>
				<div id="report_6" style="height: 260px; text-align: center;">
					<div style="width: 50%; float: left;">
						<div style="background-color: #62D9FF; width: 180px; height: 100px; color: white; margin-left: 32%;">
							<div style="float: left; width: 50%; padding-top: 30px; padding-left: 10px;">
								<div style="font-size: 1.5rem; font-weight: bold;">雅思学员</div>
								<div style="font-size: 1.5rem; font-weight: bold;">达分提分率</div>
							</div>
							<div style="float: left; width: 50%; padding-top: 30px; padding-left: 10px;">
								<div id="ielts_source_ielts" style="font-size: 3rem; float: left; margin-top: 6px; width: 60%; font-weight: bold; text-align: right;">0</div>
								<div style="font-size: 1.4rem; float: left; margin-top: 22px; width: 30%;">%</div>
							</div>
						</div>
					</div>
					<div style="width: 50%; float: left;">
						<div style="background-color: #F97752; width: 180px; height: 100px; color: white; margin-left: 32%;">
							<div style="float: left; width: 50%; padding-top: 30px; padding-left: 10px;">
								<div style="font-size: 1.5rem; font-weight: bold;">计划类学员</div>
								<div style="font-size: 1.5rem; font-weight: bold;">达分提分率</div>
							</div>
							<div style="float: left; width: 50%; padding-top: 30px; padding-left: 10px;">
								<div id="ielts_source_boutique" style="font-size: 3rem; float: left; margin-top: 6px; width: 60%; font-weight: bold; text-align: right;">0</div>
								<div style="font-size: 1.4rem; float: left; margin-top: 22px; width: 30%;">%</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="margin: 1% 5%; background-color: #ffffff; width: 90%;">
			<div style="font-size: 18px; text-align: center; padding-top: 30px; padding-bottom: 10px;">达分提分学员年级细分统计</div>
			<div>
				<div id="report_7" style="height: 300px;">
			
				</div>
			</div>
		</div>
	</div>
	<script src="<%=basePath %>/static/echarts/echarts.3.5.min.js"></script>
	<script src="<%=basePath %>/static/echarts/ieltsshine.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/ielts/manager/studentinfo.js"></script>
</body>
</html>