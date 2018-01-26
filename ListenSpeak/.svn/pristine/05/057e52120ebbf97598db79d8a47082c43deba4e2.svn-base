layui.use('element', function(){
	  var element = layui.element;
	});
		var myChart = echarts.init(document.getElementById('questionAnalyse'));
	$(function() {
		Create();
		var overallTeacher=$("#overallTeacher").val();
		var name=$("#name").val();
		var accuracyPercent=$("#accuracyPercent").val();
		var fluencyPercent=$("#fluencyPercent").val();
		var integrityPercent=$("#integrityPercent").val();
		var accuracyAvg=$("#accuracyAvg").val();
		var fluencyAvg=$("#fluencyAvg").val();
		var inegrityAvg=$("#inegrityAvg").val();
		var overPercent=$("#overPercent").val();
		var paiming=$("#paiming").val();
		var passName=",";
		var nopassName=",";
		var text=name+"同学您本次听口模考的总成绩为"+overallTeacher+"分，超过了参加模考的"+overPercent+"%的学生，在模考中排名第"+paiming+"名，其中";
		if(parseInt(accuracyPercent)<parseInt(accuracyAvg)){
			$("#accuracy").addClass("layui-bg-red");
			if(nopassName==','){
				nopassName+="准确度";
				}else{
				nopassName+="和准确度";
				}
		}else{
			if(passName==','){
			passName+="准确度";
			}else{
			passName+="和准确度";
			}
		}
		if(parseInt(fluencyPercent)<parseInt(fluencyAvg)){
			$("#fluency").addClass("layui-bg-red");
			if(nopassName==','){
				nopassName+="流利度";
				}else{
				nopassName+="和流利度";
				}
		}else{
			if(passName==','){
				passName+="流利度";
				}else{
				passName+="和流利度";
				}
		}
		if(parseInt(integrityPercent)<parseInt(inegrityAvg)){
			$("#integrity").addClass("layui-bg-red");
			if(passName==','){
				nopassName+="完整度";
				}else{
				nopassName+="和完整度";
				}
		}else{
			if(passName==','){
				passName+="完整度";
				}else{
				passName+="和完整度";
				}
		}
		if(nopassName==','&&passName!=','){
			text+=passName+"高于平均水平，希望你下次获取更高的水平，不断超越自己。";
		}else if(passName!=','&&nopassName==','){
			text+=nopassName+"低于平均水平";
		}else {
			text+=nopassName+"低于平均水平"+passName+"高于平均水平，希望你下次获取更高的水平，不断超越自己。";
		}
		$("#text").text(text);
		stuQuesTypeReport();
	})
	function Create(){
			 var percent=$("#percent").val(); 
			 var overallTeacher= fomatFloat($("#overallTeacher").val(),0);
			 var testNum=$("#testNum").val();
		    var loading=setInterval(function(){  
		        if(percent>100){  
		            percent=0;  
		            $('.circle').removeClass('clip-auto');  
		            $('.right').addClass('wth0');  
		        }else if(percent>50){  
		            $('.circle').addClass('clip-auto');  
		            $('.right').removeClass('wth0');  
		        }  
		        $('.left').css("-webkit-transform","rotate("+(18/5)*percent+"deg)");  
		        $('.num>span').text(overallTeacher+"/"+testNum);  
		    },200);  
	}
	//题型分析报表
	function stuQuesTypeReport() {
		var studentId=$("#studentId").val();
		var testId=$("#testId").val();
		$.post(jsBasePath + "/lstTestStudentAnswer/stuQuesTypeReport.html", {"studentId":studentId,"testId":testId},
				function(data) {
					var nameAvg=new Array();
					var nameTotal=new Array();
					var scoreAvg=new Array();
					var scoreTotal=new Array();
					$.each(data.studentAvg,function(i,info){
						nameAvg[nameAvg.length]=info.name;
						scoreAvg[scoreAvg.length]=info.avgScore;
					});
					$.each(data.total,function(i,info){
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
					        data:['个人得分率','平均得分率']
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
					            name:'个人得分率',
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
	function fomatFloat(src, pos) {
		return Math.round(src * Math.pow(10, pos)) / Math.pow(10, pos);
	}