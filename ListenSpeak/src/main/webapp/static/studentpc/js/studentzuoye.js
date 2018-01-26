$(function(){
	report(datalist,datatimelist,sourcelist,sourcealllist, zuoyenumalllist);
	btn_click();
})

//按钮初始化
function btn_click(){
	
}

function toZuoyeView(zuoye_id, class_code){
	if(zuoye_id == null || zuoye_id == ''){
		layer.msg("作业ID为空");
		return false;
	}
	if(class_code == null || class_code == ''){
		layer.msg("班号为空");
		return false;
	}
	
	window.location.href = jsBasePath+"/studentpc/studentzuoye/zuoyeview.html?zuoye_id="+zuoye_id+"&class_code="+class_code;
}

//打开作业报告页面
function toZuoyeBaoGao(zuoye_id,class_code){
	if(zuoye_id == null || zuoye_id == ''){
		layer.msg("作业ID为空")
		return false;
	}
	if(class_code == null || class_code == ''){
		layer.msg("班级编码为空")
		return false;
	}
	window.location.href = jsBasePath+"/studentpc/studentzuoye/studentzuoyebaogaoview.html?zuoye_id="+zuoye_id+"&class_code="+class_code;
}

//成绩
function report(datalist,datatimelist,sourcelist,sourcealllist,zuoyenumalllist){
	var report = echarts.init(document.getElementById('report'));
	var cs = [];
	for(var i = 1; i <= sourcelist.length; i++){
		cs.push(i);
	}
	report.setOption({
		color: ['#ffffff7f'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        },
	        formatter: function (params,ticket,callback) {
	            console.log(params);
	            var res = "第"+params[0].name+"次作业："
	            		  + '<br/>作业日期:'+datatimelist[params[0].dataIndex]
	            		  + '<br/>当次作业得分:'+params[0].value
	            		  + '<br/>累计得分率:'+sourcealllist[params[0].dataIndex]
	            		  + '<br/>累计做题量:'+zuoyenumalllist[params[0].dataIndex];
	            return res;
	        } 
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '13%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : cs,
	            boundaryGap: false,
	            axisTick: {
	                alignWithLabel: true
	            },
	            // 控制网格线是否显示
	            splitLine: {
	                show: true,
	                //  改变轴线颜色
	                lineStyle: {
	                    // 使用深浅的间隔色
	                    color: ['#ffffff3f']
	                }
	            },
	            //改变x轴颜色
	            axisLine:{
	                lineStyle:{
	                    color:'#ffffff'
	                }
	            },
	            //  改变x轴字体颜色和大小
	            axisLabel: {
	                textStyle: {
	                    color: '#ffffff',
	                    fontSize:'16'
	                },
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            // 控制网格线是否显示
	            splitLine: {
	                show: true,
	                //  改变轴线颜色
	                lineStyle: {
	                    // 使用深浅的间隔色
	                    color: ['#ffffff3f']
	                }
	            },
	            //改变y轴颜色
	            axisLine:{
	                lineStyle:{
	                    color:'#ffffff'
	                    
	                }
	            },
	        //  改变x轴字体颜色和大小
	            axisLabel: {
	                textStyle: {
	                    color: '#ffffff',
	                    fontSize:'16'
	                },
	            }
	        }
	    ],
	    series : [
	        {
	        	name:'成绩',
	            type:'line',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'top',
	                    textStyle: {
	                    	fontSize: 18,
		                    color:"#fff"
	                    }
	                }
	            },
	            itemStyle:{
	            	normal:{
	            		lineStyle:{
	            			color:"#fff",
	            			width:3,
	            		},
	            	},
	            },
	            areaStyle: {normal: {}},
	            data: sourcelist
	        }
	    ]
	});
}
