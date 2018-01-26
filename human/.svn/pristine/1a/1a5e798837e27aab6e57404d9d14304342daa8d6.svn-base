<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<style type="text/css">
.col-md-6{padding-left: 5px !important;padding-right: 5px !important;}
</style>
<body>
	<div style="width: 98%;margin: 0 auto;">
		<div class="row" style="padding: 10px 10px 5px 10px;">
			<div class="layui-form-pane" style="margin-top: 15px;">
			<input type="hidden" id="searchType"  value="0">
				<div class="layui-form-item">
					<label class="layui-form-label">时间段:</label>
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="开始日" id="startDate">
					</div>
					 <div class="layui-form-mid">~</div>
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="截止日" id="endDate">
					</div>
					<div class="layui-btn-group">
						<button class="layui-btn"  searchType="0">搜索</button>
					</div>
					<div class="layui-btn-group" >
						<button class="layui-btn layui-btn-small layui-btn-primary" searchType="1">今天</button>
						<button class="layui-btn layui-btn-small layui-btn-primary" searchType="2">本周</button>
						<button class="layui-btn layui-btn-small layui-btn-primary" searchType="3">本月</button>
						<button class="layui-btn layui-btn-small layui-btn-primary" searchType="4">本季度</button>
						<button class="layui-btn layui-btn-small layui-btn-primary" searchType="5">本财年</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" style="margin-left: 5px;margin-right: 5px;">
				<div id="report_1" style="height: 600px;"></div>
			</div>
			<div class="col-md-6">
				<div id="report_2"></div>
			</div>
		</div>
	</div>
</body>
<script src="<%=basePath %>/static/echarts/echarts.3.5.min.js"></script>
<script type="text/javascript">
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  var start = {
	    min: laydate.now()
	    ,choose: function(datas){
	      end.min = datas; //开始日选好后，重置结束日的最小日期
	      end.start = datas //将结束日的初始值设定为开始日
	    }
	  };
	  
	  var end = {
	    min: laydate.now()
	    ,choose: function(datas){
	      start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	  };
	  
	  document.getElementById('startDate').onclick = function(){
	    start.elem = this;
	    laydate(start);
	  }
	  document.getElementById('endDate').onclick = function(){
	    end.elem = this
	    laydate(end);
	  }
	});

var myChart = echarts.init(document.getElementById('report_1'));
$(function(){ 
	console.log($(window).height());
	console.log($(document).height());
	console.log($("body").height());
	var charHeigh=$("body").height()-$(".row").eq(0).height();
	$("#report_1").height(charHeigh);
	initreport_1();
});
//显示标题，图例和空的坐标轴
//异步加载数据
function initreport_1(){
	myChart.showLoading()
	$.post(jsBasePath+"/home/getLinkDist.html",{type:$("#searchType").val(),startDate:$("#startDate").val() ,endDate:$("#endDate").val()},function (data) {
		myChart.hideLoading() // 填入数据
	    if (data.flag) {
	    	var subtext="";
	    	if(data.startDate==""&&data.endDate==""){
	    		subtext+="截至:"+data.curDate;
	    	}else if(data.startDate==""){
	    		subtext+="截至:"+data.endDate;
	    	}else if(data.endDate==""){
	    		subtext+=data.startDate+"~至今";
	    	}else{
	    		subtext+=data.startDate+"~"+data.endDate;
	    	}
	    	myChart.setOption({
				title:{text:'职位环节分布',subtext:subtext},
			    tooltip : {
			        trigger: 'item',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        },
			    },
			    legend: {
			        data:data.legendList
			        //data:nodeArray
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis:  {
			        type: 'value'
			    },
			    yAxis: {
			        type: 'category',
			        data: data.yAxisList
			        //data: postionArray
			    },
			    series: data.eList
			});
	    }
	},'JSON').done();
}

$(".layui-btn").click(function() {
	$(".layui-btn").addClass("layui-btn-primary");
	$(this).removeClass("layui-btn-primary");
	$("#searchType").val($(this).attr("searchType"));
	initreport_1();
});
/* function quickSearch(type){
	$("#quickDiv button").addClass("layui-btn-primary");
	$(this).removeClass("layui-btn-primary");
} */
</script>
</html>