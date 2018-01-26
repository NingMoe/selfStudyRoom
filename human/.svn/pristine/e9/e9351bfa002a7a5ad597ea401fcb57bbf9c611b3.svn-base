var report_1 = echarts.init(document.getElementById('report_1'));
var report_2 = echarts.init(document.getElementById('report_2'));
var report_3 = echarts.init(document.getElementById('report_3'));
var report_4 = echarts.init(document.getElementById('report_4'));
var report_5 = "";
var report_6 = "";
var report_7 = echarts.init(document.getElementById('report_7'));
//初始化
$(function() {
	initreport_1();//报考率
	initreport_2();//成绩回收率
	initreport_3();//雅思学员平均成绩分析
	initreport_4();//分数段分布
	initreport_5();//高分学员统计
	initreport_6();//达分提分学员统计
	initreport_7();//年级细分提分统计
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
	initreport_1();//报考率
	initreport_2();//成绩回收率
	initreport_3();//雅思学员平均成绩分析
	initreport_4();//分数段分布
	initreport_5();//高分学员统计
	initreport_6();//达分提分学员统计
	initreport_7();//年级细分提分统计
}

//显示标题，图例和空的坐标轴
//异步加载数据
function initreport_1(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerstudentinfo/selectstudentapply.html",
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
				        data:['报考人数','剩余人数']
				    },
				    color:['#F78266', '#868686'],
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
				                {value : data.enrollcount, name:'报考人数'},
				                {value : data.studentcount - data.enrollcount, name:'剩余人数'}
				            ]
				        }
				    ]
				});
			}else{
				layer.msg("报考率加载失败:"+data.message);
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
		url : jsBasePath + "/ielts/managerstudentinfo/selectstudentrecovery.html",
		type : "POST",
		dataType : "json",
		data : {
			left_test_time : integral_time.left_integral_time,
			right_test_time : integral_time.right_integral_time
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
				        data:['有成绩人数','无成绩人数']
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
				    color:['#1DCBCD', '#868686'],
				    series: [
				        {
				            name:'人数',
				            type:'pie',
				            radius: ['60%', '80%'],

				            data:[
				                {value : data.hassourcecount, name : '有成绩人数'},
				                {value : data.enrollcount - data.hassourcecount, name : '无成绩人数'}
				            ]
				        }
				    ]
				});
			}else{
				layer.msg("报考率加载失败:"+data.message);
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
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerstudentinfo/selectstudentaverage.html",
		type : "POST",
		dataType : "json",
		data : {
			left_test_time : integral_time.left_integral_time,
			right_test_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				report_3.setOption({
					tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
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
		            				'#F97752','#0FB6A0','#F97752','#62D9FF','#F6BA55'
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
		                         data : ['听力平均分', '阅读平均分', '写作平均分', '口语平均分', '总成绩平均分'],
		                         axisTick: {
		                             alignWithLabel: true
		                         }
		                     }
		            ],
		           yAxis : [
		                     {
		                         type : 'value'
		                     }
		           ],
		           series : [
		                     {
		                         name:'平均分',
		                         type:'bar',
		                         barWidth: '60%',
		                         data:[data.listenavg, data.readavg.toFixed(2), data.readavg.toFixed(2), data.talkavg.toFixed(2), data.totalcount.toFixed(2)]
		                     }
		           ]
				});
			}else{
				layer.msg("平均成绩分析加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
			report_3.showLoading();
		},
		complete : function(data){
			report_3.hideLoading();
		}
	});
}

//异步加载数据
function initreport_4(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerstudentinfo/selectstudentmaxtotal.html",
		type : "POST",
		dataType : "json",
		data : {
			left_test_time : integral_time.left_integral_time,
			right_test_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				report_4.setOption({
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data:['7-9分','6-6.5分','5-5.5分','5分以下']
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
				    color:['#0FB6A0', '#F6BA55','#62D9FF','#F97752'],
				    series: [
				        {
				            name:'人数',
				            type:'pie',
				            radius: ['40%', '60%'],

				            data:[
				                {value : data.a1, name : '7-9分'},
				                {value : data.a2, name : '6-6.5分'},
				                {value : data.a3, name : '5-5.5分'},
				                {value : data.a4, name : '5分以下'}
				            ]
				        }
				    ]
				});
			}else{
				layer.msg("分数段分布加载失败:"+data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		},
		beforeSend : function(data){
			report_4.showLoading();
		},
		complete : function(data){
			report_4.hideLoading();
		}
	});
}

//异步加载数据
function initreport_5(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerstudentinfo/selectclasstypecount.html",
		type : "POST",
		dataType : "json",
		data : {
			left_test_time : integral_time.left_integral_time,
			right_test_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				var i = 0;
				$.each(data.classtypelist, function(index, classtypeinfo){
					if(classtypeinfo.class_type_id == 1){
						$("#ielts_count_onevsone").html(classtypeinfo.student_count);
					}
					
					if(classtypeinfo.class_type_id == 2){
						$("#ielts_count_onevssix").html(classtypeinfo.student_count);
					}
					
					if(classtypeinfo.class_type_id == 3){
						$("#ielts_count_boutique").html(classtypeinfo.student_count);
					}
					
					if(classtypeinfo.class_type_id == 4){
						$("#ielts_count_devil").html(classtypeinfo.student_count);
					}
					i += classtypeinfo.student_count;
				});
				$("#ielts_count_ielts").html(i);
			}else{
				layer.msg("高分学员统计加载失败:"+data.message);
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
		url : jsBasePath + "/ielts/managerstudentinfo/selectachievesource.html",
		type : "POST",
		dataType : "json",
		data : {
			left_test_time : integral_time.left_integral_time,
			right_test_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				if(data.ieltsallstudentcount != null && data.ieltsallstudentcount != 0){
					$("#ielts_source_ielts").html(parseInt(data.ieltsincreasestudentcount * 100 / data.ieltsallstudentcount));
				}
				if(data.planallcount != null && data.planallcount != 0){
					$("#ielts_source_boutique").html(parseInt(data.planincreasecount * 100 / data.planallcount));
				}
			}else{
				layer.msg("达分提分学员加载失败:"+data.message);
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
function initreport_7(){
	var integral_time = getintegralime();
	$.ajax({
		url : jsBasePath + "/ielts/managerstudentinfo/selectgradeaverage.html",
		type : "POST",
		dataType : "json",
		data : {
			left_test_time : integral_time.left_integral_time,
			right_test_time : integral_time.right_integral_time
		},
		success : function(data){
			if(data.flag){
				var chuyi = 0;
				var chuer  = 0;
				var chusan  = 0;
				var gaoyi  = 0;
				var gaoer  = 0;
				var gaosan  = 0;
				var dayi  = 0;
				var daer  = 0;
				var dasan  = 0;
				var dasi  = 0;
				var yanjiusheng  = 0;
				if(data.chuyi_count != null && data.chuyi_count != 0){
					chuyi = (data.chuyi_i_count * 100 /  data.chuyi_count).toFixed(0);
				}
				if(data.chuer_count != null && data.chuer_count != 0){
					chuer = (data.chuer_i_count * 100 /  data.chuer_count).toFixed(0);
				}
				if(data.chusan_count != null && data.chusan_count != 0){
					chusan = (data.chusan_i_count * 100 /  data.chusan_count).toFixed(0);
				}
				if(data.gaoyi_count != null && data.gaoyi_count != 0){
					gaoyi = (data.gaoyi_i_count * 100 /  data.gaoyi_count).toFixed(0);
				}
				if(data.gaoer_count != null && data.gaoer_count != 0){
					gaoer = (data.gaoer_i_count * 100 /  data.gaoer_count).toFixed(0);
				}
				if(data.gaosan_count != null && data.gaosan_count != 0){
					gaosan = (data.gaosan_i_count * 100 /  data.gaosan_count).toFixed(0);
				}
				if(data.dayi_count != null && data.dayi_count != 0){
					dayi = (data.dayi_i_count * 100 /  data.dayi_count).toFixed(0);
				}
				if(data.daer_count != null && data.daer_count != 0){
					daer = (data.daer_i_count * 100 /  data.daer_count).toFixed(0);
				}
				if(data.dasan_count != null && data.dasan_count != 0){
					dasan = (data.dasan_i_count * 100 /  data.dasan_count).toFixed(0);
				}
				if(data.dasi_count != null && data.dasi_count != 0){
					dasi = (data.dasi_i_count * 100 /  data.dasi_count).toFixed(0);
				}
				if(data.yanjiusheng_count != null && data.yanjiusheng_count != 0){
					yanjiusheng = (data.yanjiusheng_i_count * 100 /  data.yanjiusheng_count).toFixed(0);
				}
				
				report_7.setOption({
					tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
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
		            				'#F97752','#0FB6A0','#F97752','#62D9FF','#F6BA55',
		            				'#F97752','#0FB6A0','#F97752','#62D9FF'
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
		                         data : ['初一', '初二', '初三', '高一', '高二', '高三', '大一', '大二', '大三', '大四', '研究生'],
		                         axisTick: {
		                             alignWithLabel: true
		                         }
		                     }
		            ],
		           yAxis : [
		                     {
		                         type : 'value'
		                     }
		           ],
		           series : [
		                     {
		                         name:'平均分',
		                         type:'bar',
		                         barWidth: '60%',
		                         data:[chuyi, chuer,chusan, gaoyi, gaoer, gaosan, dayi, daer, dasan, dasi, yanjiusheng]
		                     }
		           ]
				});
			}else{
				layer.msg("平均成绩分析加载失败:"+data.message);
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