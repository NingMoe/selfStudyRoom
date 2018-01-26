var report_1 = echarts.init(document.getElementById('report_1'));
var report_2 = echarts.init(document.getElementById('report_2'));
var report_3 = "" ;
var report_4 = "";
var report_5 = "";
var report_6 = echarts.init(document.getElementById('report_6'));
var report_7 = echarts.init(document.getElementById('report_7'));
var report_8 = echarts.init(document.getElementById('report_8'));
var report_9 = echarts.init(document.getElementById('report_9'));
var report_10 = echarts.init(document.getElementById('report_10'));
//初始化
$(function() {
	initreport_1();//雅思高分人数占比
	initreport_2();//TKT高分人数占比
	initreport_3();//TKT达标率
	initreport_4();//Celtaz证书人数
	initreport_5();//教师资格证人数
	initreport_6();//教师功底积分
	initreport_7();//教研成果积分
	initreport_8();//教学成果积分
	initreport_9();//教学服务积分
	initreport_10();//教师总积分
	buttonclick();
});
layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
	var laydate = layui.laydate;
	  var start = {
	      istoday : false
	    , istime : true
	    , format : 'YYYY-MM-DD  hh:mm:ss'
	    ,choose: function(datas){
	      end.min = datas; //开始日选好后，重置结束日的最小日期
	      end.start = datas //将结束日的初始值设定为开始日
	    }
	  };
	  var end = {
	      istoday: false
	    , istime: true
	    , format: 'YYYY-MM-DD 00:00:00'
	    ,choose: function(datas){
	      start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	  };
	  document.getElementById('left_integral_time').onclick = function(){
	       start.elem = this;
	       laydate(start);
	  };
	  document.getElementById('right_integral_time').onclick = function(){
	       end.elem = this
	       laydate(end);
	  };
	  var lefttime = "";
	  var righttime = "";
	  var nowtime = new Date();
	  var nowMonth = nowtime.getMonth(); //当前月
      var nowYear = nowtime.getFullYear(); //当前年
      var starttime = "";
	  var endtime = "";
      if(nowMonth < 6){
    	  lefttime = (nowYear-1)+"-"+"06-01 00:00:00";
      }else{
    	  lefttime = nowYear+"-"+"06-01 00:00:00";
      }
      
      if(nowMonth < 6){
    	  righttime = nowYear+"-"+"05-31 23:59:59";
      }else{
    	  righttime = (nowYear+1)+"-"+"05-31 23:59:59";
      }
	 
	  $("#left_integral_time").val(lefttime);
	  $("#right_integral_time").val(righttime);
});

function buttonclick(){

}

function serchstudentinfo(){
	initreport_1();//雅思高分人数占比
	initreport_2();//TKT高分人数占比
	initreport_3();//TKT达标率
	initreport_4();//Celtaz证书人数
	initreport_5();//教师资格证人数
	initreport_6();//达分提分学员统计
	initreport_7();//年级细分提分统计
	initreport_8();//教学成果积分
	initreport_9();//教学服务积分
	initreport_10();//教师总积分积分
}

//显示标题，图例和空的坐标轴
//异步加载数据
function initreport_1(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selectieltsteachersource.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				report_1.setOption({
					tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data:['其它','7分','7.5分','8分']
				    },
				    color:['#868686','#1DCBCD', '#F6BA55','#F78266'],
				    series: [
				        {
				            name:'人数',
				            type:'pie',
				            radius: ['60%', '80%'],
				            avoidLabelOverlap: false,
				            label: {
				                normal: {
				                    show: true,
				                    position: 'left'
				                },
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '30',
				                        fontWeight: 'bold'
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: true
				                }
				            },
				            data:[
				                {value : data.t_count - data.x - data.y - data.z, name:'其它'},
				                {value : data.x, name:'7分'},
				                {value : data.y, name:'7.5分'},
				                {value : data.z, name:'8分'}
				            ]
				        }
				    ]
				});
			}else{
				layer.msg("雅思高分人数占比加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
			report_1.showLoading();
		},
		complete : function(data){
			report_1.hideLoading();
		}
	});
}

//显示标题，图例和空的坐标轴
//异步加载数据
function initreport_2(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selecttktteacher.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				report_2.setOption({
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data:['其它','10分','11分','12分']
				    },
				    label: {
		                normal: {
		                    show: true,
		                    position: 'left'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: true
		                }
		            },
				    color:['#868686','#1DCBCD', '#F6BA55','#F78266'],
				    series: [
				        {
				            name:'人数',
				            type:'pie',
				            radius: ['60%', '80%'],

				            data:[
				                {value : data.t_count - data.x - data.y - data.z, name : '其它'},
				                {value : data.x, name : '10分'},
				                {value : data.y, name : '11分'},
				                {value : data.z, name : '12分'}
				            ]
				        }
				    ]
				});
				$("#ielts_tkt_count").html(((data.x + data.y + data.z) * 100 / data.t_count).toFixed(0));
			}else{
				layer.msg("TKT高分人数占比加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
			report_2.showLoading();
		},
		complete : function(data){
			report_2.hideLoading();
		}
	});
}

//异步加载数据
function initreport_3(){
/*	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selecttktteacher.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				$("#ielts_tkt_count").html(((data.x + data.y + data.z) * 100 / data.t_count).toFixed(0));
			}else{
				layer.msg("平均成绩分析加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
		},
		complete : function(data){
		}
	});*/
}

//异步加载数据
function initreport_4(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selectceltazteachercount.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				$("#ielts_celtaz_count").html(data.ielts_celtaz_count);
			}else{
				layer.msg("Celtaz证书人数加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
		},
		complete : function(data){
		}
	});
}

//异步加载数据
function initreport_5(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selectcertificatecount.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				$("#ielts_certificate_count").html(data.ielts_certificate_count);
			}else{
				layer.msg("教师资格证人数加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
		},
		complete : function(data){
		}
	});
}

//异步加载数据
function initreport_6(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selectallteacherintegral.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				report_6.setOption({
					tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    grid : {
				    	left : "15%"
				    },
				    label: {
		                normal: {
		                    show: true,
		                    position: 'left'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: true
		                }
		            },
		            itemStyle: {
		            	normal: {
		            		//好，这里就是重头戏了，定义一个list，然后根据所以取得不同的值，这样就实现了，
		            		color: function(params) {
		            			// build a color map as your need.
		            			var colorList = [
		            			                 '#1DCBCD', '#F78266'
		            			];
		            			return colorList[params.dataIndex]
		            		},
		            			//以下为是否显示，显示位置和显示格式的设置了
		            		label: {
		                                    show: true,
		                                    position: 'top',
		                                    formatter: '{b}\n{c}'
		                                }
		                            }
		            },
		            xAxis : [
		                     {
		                         type : 'category',
		                         data : ['平均分', '最高分'],
		                         axisTick: {
		                             alignWithLabel: true
		                         }
		                     }
		            ],
		           yAxis : [
		                     {
		                         type : 'value',
		                         name : '分数'
		                     }
		           ],
		           series : [
		                     {
		                         name:'分数',
		                         type:'bar',
		                         barWidth: '30%',
		                         data:[data.avg_integer,data.max_integral]
		                     }
		           ]
				});
				
				$("#teacher_basics_integral_name").html(data.max_teacher_name);
				$("#teacher_basics_integral_email").html(data.max_email_addr+"@xdf.cn");
			}else{
				layer.msg("教师功底加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
			report_6.showLoading();
		},
		complete : function(data){
			report_6.hideLoading();
		}
	});
}
//异步加载数据
function initreport_7(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selectallteacherintegralresearch.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				report_7.setOption({
					tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    grid : {
				    	left : "15%"
				    },
				    label: {
		                normal: {
		                    show: true,
		                    position: 'left'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: true
		                }
		            },
		            itemStyle: {
		            	normal: {
		            		//好，这里就是重头戏了，定义一个list，然后根据所以取得不同的值，这样就实现了，
		            		color: function(params) {
		            			// build a color map as your need.
		            			var colorList = [
		            			                 '#1DCBCD', '#F78266'
		            			];
		            			return colorList[params.dataIndex]
		            		},
		            			//以下为是否显示，显示位置和显示格式的设置了
		            		label: {
		                                    show: true,
		                                    position: 'top',
		                                    formatter: '{b}\n{c}'
		                                }
		                            }
		            },
		            xAxis : [
		                     {
		                         type : 'category',
		                         data : ['平均分', '最高分'],
		                         axisTick: {
		                             alignWithLabel: true
		                         }
		                     }
		            ],
		           yAxis : [
		                     {
		                         type : 'value',
		                         name : '分数'
		                     }
		           ],
		           series : [
		                     {
		                         name:'分数',
		                         type:'bar',
		                         barWidth: '30%',
		                         data:[data.avg_integer,data.max_integral]
		                     }
		           ]
				});
				$("#teacher_research_integral_name").html(data.max_teacher_name);
				$("#teacher_research_integral_email").html(data.max_email_addr+"@xdf.cn");
			}else{
				layer.msg("教研成果加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
			report_7.showLoading();
		},
		complete : function(data){
			report_7.hideLoading();
		}
	});
}

//异步加载数据
function initreport_8(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selectallteacherintegralteaching.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				report_8.setOption({
					tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    grid : {
				    	left : "15%"
				    },
				    label: {
		                normal: {
		                    show: true,
		                    position: 'left'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: true
		                }
		            },
		            itemStyle: {
		            	normal: {
		            		//好，这里就是重头戏了，定义一个list，然后根据所以取得不同的值，这样就实现了，
		            		color: function(params) {
		            			// build a color map as your need.
		            			var colorList = [
		            			                '#1DCBCD', '#F78266'
		            			];
		            			return colorList[params.dataIndex]
		            		},
		            			//以下为是否显示，显示位置和显示格式的设置了
		            		label: {
		                                    show: true,
		                                    position: 'top',
		                                    formatter: '{b}\n{c}'
		                                }
		                            }
		            },
		            xAxis : [
		                     {
		                         type : 'category',
		                         data : ['平均分', '最高分'],
		                         axisTick: {
		                             alignWithLabel: true
		                         }
		                     }
		            ],
		           yAxis : [
		                     {
		                         type : 'value',
		                         name : '分数'
		                     }
		           ],
		           series : [
		                     {
		                         name:'分数',
		                         type:'bar',
		                         barWidth: '30%',
		                         data:[data.avg_integer,data.max_integral]
		                     }
		           ]
				});
				$("#teacher_teachering_integral_name").html(data.max_teacher_name);
				$("#teacher_teachering_integral_email").html(data.max_email_addr+"@xdf.cn");
			}else{
				layer.msg("教学成果积分加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
			report_8.showLoading();
		},
		complete : function(data){
			report_8.hideLoading();
		}
	});
}

//异步加载数据
function initreport_9(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selectallteacherintegralservice.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				report_9.setOption({
					tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    grid : {
				    	left : "15%"
				    },
				    label: {
		                normal: {
		                    show: true,
		                    position: 'left'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: true
		                }
		            },
		            itemStyle: {
		            	normal: {
		            		//好，这里就是重头戏了，定义一个list，然后根据所以取得不同的值，这样就实现了，
		            		color: function(params) {
		            			// build a color map as your need.
		            			var colorList = [
		            			                 '#1DCBCD', '#F78266'
		            			];
		            			return colorList[params.dataIndex]
		            		},
		            			//以下为是否显示，显示位置和显示格式的设置了
		            		label: {
		                                    show: true,
		                                    position: 'top',
		                                    formatter: '{b}\n{c}'
		                                }
		                            }
		            },
		            xAxis : [
		                     {
		                         type : 'category',
		                         data : ['平均分', '最高分'],
		                         axisTick: {
		                             alignWithLabel: true
		                         }
		                     }
		            ],
		           yAxis : [
		                     {
		                         type : 'value',
		                         name : '分数'
		                     }
		           ],
		           series : [
		                     {
		                         name:'分数',
		                         type:'bar',
		                         barWidth: '30%',
		                         data:[data.avg_integer,data.max_integral]
		                     }
		           ]
				});
				$("#teacher_service_integral_name").html(data.max_teacher_name);
				$("#teacher_service_integral_email").html(data.max_email_addr+"@xdf.cn");
			}else{
				layer.msg("教学服务积分加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
			report_9.showLoading();
		},
		complete : function(data){
			report_9.hideLoading();
		}
	});
}

//异步加载数据
function initreport_10(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerteacherinfo/selectallteacherintegraltotal.html",
		type : "POST",
		dataType : "json",
		data : {
			left_integral_time : integral_time.left_integral_time,
			right_integral_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				report_10.setOption({
					tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    grid : {
				    	left : "15%"
				    },
				    label: {
		                normal: {
		                    show: true,
		                    position: 'left'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: true
		                }
		            },
		            itemStyle: {
		            	normal: {
		            		//好，这里就是重头戏了，定义一个list，然后根据所以取得不同的值，这样就实现了，
		            		color: function(params) {
		            			// build a color map as your need.
		            			var colorList = [
		            			                 '#F6BA55','#1DCBCD', '#F78266'
		            			];
		            			return colorList[params.dataIndex]
		            		},
		            			//以下为是否显示，显示位置和显示格式的设置了
		            		label: {
		                                    show: true,
		                                    position: 'top',
		                                    formatter: '{b}\n{c}'
		                                }
		                            }
		            },
		            xAxis : [
		                     {
		                         type : 'category',
		                         data : ['最低分', '平均分', '最高分'],
		                         axisTick: {
		                             alignWithLabel: true
		                         }
		                     }
		            ],
		           yAxis : [
		                     {
		                         type : 'value',
		                         name : '分数'
		                     }
		           ],
		           series : [
		                     {
		                         name:'分数',
		                         type:'bar',
		                         barWidth: '30%',
		                         data:[data.min_integral,data.avg_integer,data.max_integral]
		                     }
		           ]
				});
				$("#teacher_all_integral_name").html(data.max_teacher_name);
				$("#teacher_all_integral_email").html(data.max_email_addr+"@xdf.cn");
			}else{
				layer.msg("教师总积分加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
			report_10.showLoading();
		},
		complete : function(data){
			report_10.hideLoading();
		}
	});
}

function getintegralime(){
	var left_integral_time = $("#left_integral_time").val();
	var right_integral_time = $("#right_integral_time").val();
	var lefttime = "";
	var righttime = "";
	var nowtime = new Date();
	var nowMonth = nowtime.getMonth(); //当前月
    var nowYear = nowtime.getFullYear(); //当前年
    var starttime = "";
	var endtime = "";
    if(nowMonth < 6){
    	lefttime = (nowYear-1)+"-"+"06-01 00:00:00";
    }else{
    	lefttime = nowYear+"-"+"06-01 00:00:00";
    }
      
    if(nowMonth < 6){
    	righttime = nowYear+"-"+"05-31 23:59:59";
    }else{
    	righttime = (nowYear+1)+"-"+"05-31 23:59:59";
    }
    if(left_integral_time == null || left_integral_time == ''){
    	left_integral_time = lefttime;
    }
    if(right_integral_time == null || right_integral_time == ''){
    	right_integral_time = righttime;
    }
    return {
		left_integral_time : left_integral_time,
		right_integral_time : right_integral_time
	};
}