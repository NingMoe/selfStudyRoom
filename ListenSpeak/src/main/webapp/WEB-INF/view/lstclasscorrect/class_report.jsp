<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
<style>
td {
	width: 20%
}

th {
	text-align: center !important;
}

</style>
<body>
	<div class="alertFrom">
		<input id="testId" type="hidden" value="${lsta.testId}">
		<input id="paperId" type="hidden" value="${lsta.paperId}">
		<input id="classCode" type="hidden" value="${lsta.classCode}">
		<blockquote class="layui-elem-quote layui-text" >
			  <span></span> <span style="margin-left:5%;">成绩概况</span>
			</blockquote>
		<table class="layui-table" >
			<colgroup>
				<col width="150">
				<col width="200">
				<col>
			</colgroup>
			<tbody>
					<tr>
						<td style="text-align:center">${score.count }</td>
						<td style="text-align:center">${score.maxScore}</td>
						<td style="text-align:center">${score.minScore}</td>
						<td style="text-align:center">${score.avgScore}</td>
					</tr>
					<tr>
						<td style="text-align:center">班级考试人数</td>
						<td style="text-align:center">最高分</td>
						<td style="text-align:center">最低分</td>
						<td style="text-align:center">平均分</td>
					</tr>
			</tbody>
		</table>
		<table class="layui-table" >
			<colgroup>
				<col width="150">
				<col width="200">
				<col>
			</colgroup>
			<thead>
				<tr>
					<th colspan="2" style="text-align: center !important;">班级前五名</th>
					<th colspan="2" style="text-align: center !important;">班级后五名</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="ls" >
					<tr>
						<td><a>${ls.topName}</a></td>
						<td><a>${ls.topOver}</a></td>
						<td><a>${ls.backName}</a></td>
						<td><a>${ls.backOver}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<blockquote class="layui-elem-quote layui-text" >
			  <span></span> <span style="margin-left:5%;">班级报表</span>
		</blockquote>
		<div id="report" style="height: 300px;"></div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/lstclass/edit.js"></script>
	<script src="<%=basePath%>/static/echarts/echarts.3.5.min.js"></script>
	<script type="text/javascript">
	var myChart = echarts.init(document.getElementById('report'));
	$(function(){
		initreport();
	})
	function initreport() {
		var classCode=$("#classCode").val();
		var testId=$("#testId").val();
		$.post(jsBasePath + "/lstTestStudentAnswer/report.html", {"classCode":classCode,"testId":testId},
				function(data) {
					var nameAvg=new Array();
					var nameTotal=new Array();
					var scoreAvg=new Array();
					var scoreTotal=new Array();
					$.each(data.classAvg,function(i,info){
						nameAvg[nameAvg.length]=info.name;
						scoreAvg[scoreAvg.length]=info.avgScore;
					});
					$.each(data.classTotal,function(i,info){
						nameTotal[name.length]=info.name;
						scoreTotal[scoreTotal.length]=info.avgScore
					});
					myChart.showLoading()
					myChart.hideLoading() // 填入数据
					myChart.setOption({
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        },
					        formatter:function(params){
								 var res =  params[0].name;
								 var total=0;
						            for (var i = 0, l = params.length; i < l; i++) {
						            		res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+"%";
						            		total=parseInt(total)+parseInt(params[i].value);
						            }
						            return res;
							}
					    },
					    legend: {
					        data:['本班得分率','平均得分率']
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : nameAvg
					        }
					    ],
					    yAxis :  [{
					    	type: 'value',
					    	min: 0,
					    	max: 100,
					    	splitArea: {
					    	show: true
					    	},
					    	}],
					    series : [
					        {
					            name:'本班得分率',
					            type:'bar',
					            data:scoreAvg,
					            itemStyle : {
									normal : {
										color : '#009999'
									}
								},
					            label : {
									normal : {
										show : true,
										formatter:function(params){
											var re =params.value+'%';
					                        return re;
										}
									}
								},
					        },
					        {
					            name:'平均得分率',
					            type:'bar',
					            stack: '广告',
					            data:scoreTotal,
					            itemStyle : {
									normal : {
										color : '#FF9900'
									}
								},
					            label : {
									normal : {
										show : true,
										formatter:function(params){
											var re =params.value+'%';
					                        return re;
										}
									}
								},
					        }
					    ]
					});
			
		}, 'JSON').done();
		

	}
	</script>
	
</html>