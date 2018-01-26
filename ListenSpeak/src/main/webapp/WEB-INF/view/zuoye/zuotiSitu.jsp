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
		<input id="question_id" type="hidden" value="${lz.id}"> 
		<input id="class_code" type="hidden" value="${lz.class_code}"> 
		<input id="zuoye_id" type="hidden" value="${lz.zuoye_id}"> 
		<div style ="font-weight:900">
			<span>总人数：${total} &nbsp;&nbsp;已批阅：${pg} &nbsp;&nbsp;未提交：${ntj}</span><span style=" position: absolute;right: 23%;">平均得分率：${avg.avgScore }%</span>
		</div>
		<div id="report" style="width:80%;height:300px ;"></div>
	</div>
</body>
<script src="<%=basePath%>/static/echarts/echarts.3.5.min.js"></script>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById('report'));
$(function(){
	initReport();
});
		function initReport(){
			var question_id=$("#question_id").val();
			var class_code=$("#class_code").val();
			var zuoye_id=$("#zuoye_id").val();
			myChart.showLoading()
			$.post(jsBasePath + "/classZuoyeReport/reportClass.html", {
				"class_code" : class_code,
				"zuoye_id" : zuoye_id,
				"id":question_id
			}, function(data) {
				var twopointfive=0 ;
				var five=0;
				var sevenpointfive=0;
				var ten=0;
					if(data.twoPoint!=''&&data.twoPoint!=null){
						twopointfive=data.twopoint;
					}else if(data.five!=''&&data.five!=null){
						five=data.five;
					}else if(data.sevenpointfive!=''&&data.sevenpointfive!=null){
						sevenpointfive=data.sevenpointfive;
					}else if(data.ten!=''&&data.ten!=null){
						ten=data.ten;
					}
				var zeroStu='';	
				var twopfiveStu='';
				var fivepSevenStu ='';
				var ten='';
				$.each(data.sScore,function(i ,info){
					if(info.stage=="twoPoint"){
						zeroStu+=info.name+":得分率"+info.overall_teacher+"%"+'</br>'
					}else if(info.stage=='five'){
						twopfiveStu+=info.name+":得分率"+info.overall_teacher+"%"+'</br>';
					}else if(info.stage=='sevenpointfive'){
						fivepSevenStu+=info.name+":得分率"+info.overall_teacher+"%"+'</br>';
					}else if(info.stage=='seven'){
						ten+=info.name+":得分率"+info.overall_teacher+"%"+'</br>';
					}
				});	
				myChart.hideLoading() // 填入数据
				myChart.setOption({
					 tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        },
					        formatter:function(params){
					        	var data='';
								 var res =   params[0].name;
						            		if('<25%'==res){
						            			data+=zeroStu;
						            		}else if('25%~50%'==res){
						            			data+=twopfiveStu;
						            		}else if('50%~75%'==res){
						            			data+=fivepSevenStu;
						            		}else if('75%~100%'==res){
						            			data+=ten;
						            		}
						            return data;
							}
					    },
					    legend: {
					        data:['<25%','25%~50%','50%~75%','75%~100%']
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
					            data :['<25%','25%~50%','50%~75%','75%~100%']
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
				    series: [
								{
					            name:'<25%',
					            type:'bar',
					            stack: 'score',
					            data:[twopointfive,0,0,0],
					            itemStyle : {
									normal : {
										color : '#0066CC'
									}
								}
					        },
					        {
					            name:'25%~50%',
					            type:'bar',
					            stack: 'score',
					            data:[0,five,0,0],
					            itemStyle : {
									normal : {
										color : '#FF0000'
									}
								}
					        },
					        {
					            name:'50%~75%',
					            type:'bar',
					            stack: 'score',
					            data:[0,0,sevenpointfive,0],
					            itemStyle : {
									normal : {
										color : '#FFFF00'
									}
								}
					        },{
					            name:'75%~100%',
					            type:'bar',
					            stack: 'score',
					            data:[0,0,0,ten],
					            itemStyle : {
									normal : {
										color : '#FF9900'
									}
								}
					        }
				       
				    ]
			});
			},"JSON");
		}
</script>
</html>