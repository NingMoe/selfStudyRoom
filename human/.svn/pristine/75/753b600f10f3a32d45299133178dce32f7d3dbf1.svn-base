var myChart = echarts.init(document.getElementById('report'));// 预备阶段随堂测
var myChart_1 = echarts.init(document.getElementById('report_1'));// 预备阶段词测
var myChart_Js = echarts.init(document.getElementById('report_Js'));// 基础阶段随堂测
var myChart_Jc = echarts.init(document.getElementById('report_Jc'));// 基础阶段词测
var myChart_Qs = echarts.init(document.getElementById('report_Qs'));// 强化阶段随堂测试成绩
var myChart_Qc = echarts.init(document.getElementById('report_Qc'));// 强化阶段词测
var myChart_Cc = echarts.init(document.getElementById('report_Cs'));
var myChart_JHBas = echarts.init(document.getElementById('report_JHBas'));
var myChart_JHStr = echarts.init(document.getElementById('report_JHStr'));
var myChart_JHPre = echarts.init(document.getElementById('report_JHPre'));
var myChart_JH = echarts.init(document.getElementById('report_JH'));// 阶段成绩汇总
var myChart_MK = echarts.init(document.getElementById('report_MK'));// toffl模考柱状图
var myChart_MK_Z = echarts.init(document.getElementById('report_MK_Z'));// 托福模考折线图
var myChart_SK = echarts.init(document.getElementById('report_SK'));// 基础阶段toffl实考柱状图
var myChart_SK_r = echarts.init(document.getElementById('report_SK_r'));// 基础阶段toffl实考阅读成绩汇总
var myChart_SK_l = echarts.init(document.getElementById('report_SK_l'));// 基础阶段toffl实考听力成绩汇总
var myChart_SK_S = echarts.init(document.getElementById('report_SK_S'));// 基础阶段toffl实考口語成绩汇总
var myChart_SK_W = echarts.init(document.getElementById('report_SK_W'));// 基础阶段toffl实考写作成绩汇总
var skGrade = new Array();
var mkGrade = new Array();
layui.use([ 'laydate', 'form' ], function() {
	var form = layui.form();
	var laydate = layui.laydate;
	initTable();
});
function initreport() {
	myChart.showLoading()
	$.post(jsBasePath + "/Examinee/List/queryDetailReport.html", {},
			function(data) {
				var legend = new Array();
				var yfTearcher = new Array();
				var tkTearcher = new Array();
				var dTearcher = new Array();
				$.each(data, function(i, info) {
					legend[legend.length] = info.frequency
					yfTearcher[yfTearcher.length] = info.yfTearcher;
					tkTearcher[tkTearcher.length] = info.tkTearcher;
					dTearcher[dTearcher.length] = info.dTearcher;
				});
				myChart.hideLoading() // 填入数据
				myChart.setOption({
					title : {
						text : ''/* ,subtext:subtext */
					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : [ '语法', '听口', '阅读' ]
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						containLabel : true
					},
					toolbox : {
						feature : {
							saveAsImage : {}
						}
					},
					xAxis : {
						type : 'category',
						oundaryGap : false,
						data : legend
					},
					yAxis : {
						type : [ {
							type : 'value',
							min : 0,
							max : 100
						} ],
					},
					series : [ {
						name : '语法',
						type : 'line',
						stack : '语法',
						data : yfTearcher,
						itemStyle : {
							normal : {
								color : '#DE7815'
							}
						}
					}, {
						name : '听口',
						type : 'line',
						stack : '听口',
						data : tkTearcher,
						itemStyle : {
							normal : {
								color : '#55A72D'
							}
						}
					}, {
						name : '阅读',
						type : 'line',
						stack : '阅读',
						data : dTearcher,
						itemStyle : {
							normal : {
								color : '#3476C4'
							}
						}
					} ]
				});
			}, 'JSON').done();
}
// 预备班随堂测折线图
function initreport() {
	myChart.showLoading()
	$.post(jsBasePath + "/Examinee/List/queryDetailReport.html", {
		type : "预备"
	}, function(data) {
		var legend = new Array();
		var yfTearcher = new Array();
		var tkTearcher = new Array();
		var dTearcher = new Array();
		$.each(data, function(i, info) {
			legend[legend.length] = info.frequency
			yfTearcher[yfTearcher.length] = info.yfTearcher;
			tkTearcher[tkTearcher.length] = info.tkTearcher;
			dTearcher[dTearcher.length] = info.dTearcher;
		});
		myChart.hideLoading() // 填入数据
		myChart.setOption({
			title : {
				text : ''/* ,subtext:subtext */
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '词汇语法', '听口', '读写' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				oundaryGap : false,
				data : legend
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				name : '词汇语法',
				type : 'line',
				stack : '词汇语法',
				data : yfTearcher,
				itemStyle : {
					normal : {
						color : '#DE7815'
					}
				}
			}, {
				name : '听口',
				type : 'line',
				stack : '听口',
				data : tkTearcher,
				itemStyle : {
					normal : {
						color : '#55A72D'
					}
				}
			}, {
				name : '读写',
				type : 'line',
				stack : '阅读',
				data : dTearcher,
				itemStyle : {
					normal : {
						color : '#3476C4'
					}
				}
			} ]
		});
	}, 'JSON').done();
}
function initreport_1() {
	myChart_1.showLoading()
	$.post(jsBasePath + "/Examinee/List/report_1.html", {
		type : "预备"
	}, function(data) {
		if (data.length == '0') {
			$(".yubei").hide();
		}
		var legend = new Array();
		var cice = new Array();
		var average = 0;
		$.each(data, function(i, info) {
			legend[legend.length] = info.frequency
			cice[cice.length] = info.cice;
			average += (parseInt(info.cice));
		});
		if (legend.length != 0 && legend.length != null) {
			cice.push(fomatFloat(average / (legend.length), 1))
		}
		if (legend.length) {
			legend.push("平均分");
		}
		myChart_1.hideLoading() // 填入数据
		myChart_1.setOption({
			color : [ '#3398DB' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : legend,
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '直接访问',
				type : 'bar',
				barWidth : '60%',
				label : {
					normal : {
						show : true,
					}
				},
				data : cice
			} ]
		});
	}, 'JSON').done();
}
// 基础班随堂测折线图
function initreport_Js() {
	myChart_Js.showLoading()
	$.post(jsBasePath + "/Examinee/List/queryDetailReport.html", {
		type : "基础"
	}, function(data) {
		if (data.length == '0') {
			$(".jichu").hide();
		}
		var legend = new Array();
		var rTearcher = new Array();
		var lTearcher = new Array();
		var sTearcher = new Array();
		var wTearcher = new Array();

		$.each(data, function(i, info) {
			legend[legend.length] = info.frequency
			rTearcher[rTearcher.length] = info.rTearcher;
			lTearcher[lTearcher.length] = info.lTearcher;
			sTearcher[sTearcher.length] = info.sTearcher;
			wTearcher[wTearcher.length] = info.wTearcher;
		});
		myChart_Js.hideLoading() // 填入数据
		myChart_Js.setOption({
			title : {
				text : ''/* ,subtext:subtext */
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '阅读', '听力', '口语', '写作' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				oundaryGap : false,
				data : legend
			},
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 100
			} ],
			series : [ {
				name : '阅读',
				type : 'line',
				stack : '阅读',
				data : rTearcher,
				itemStyle : {
					normal : {
						color : '#3476C4'
					}
				}
			}, {
				name : '听力',
				type : 'line',
				stack : '听力',
				data : lTearcher,
				itemStyle : {
					normal : {
						color : '#55A72D'
					}
				}
			}, {
				name : '口语',
				type : 'line',
				stack : '口语',
				data : sTearcher,
				itemStyle : {
					normal : {
						color : '#E2B20D'
					}
				}
			}, {
				name : '写作',
				type : 'line',
				stack : '写作',
				data : wTearcher,
				itemStyle : {
					normal : {
						color : '#DE7815'
					}
				}
			} ]
		});
	}, 'JSON').done();
}
// 基础班词测
function initreport_Jc() {
	myChart_Jc.showLoading()
	$.post(jsBasePath + "/Examinee/List/report_1.html", {
		type : "基础"
	}, function(data) {
		var legend = new Array();
		var cice = new Array();
		var average = 0;
		$.each(data, function(i, info) {
			legend[legend.length] = info.frequency
			cice[cice.length] = info.cice;
			average += (parseInt(info.cice));
		});
		if (legend.length != 0 && legend.length != null) {

			cice.push(fomatFloat(average / (legend.length), 1))
		}
		if (legend.length) {
			legend.push("平均分");
		}
		myChart_Jc.hideLoading() // 填入数据
		myChart_Jc.setOption({
			color : [ '#3398DB' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : legend,
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 100
			} ],
			series : [ {
				name : '直接访问',
				type : 'bar',
				barWidth : '60%',
				label : {
					normal : {
						show : true,
					}
				},
				data : cice
			} ]
		});
	}, 'JSON').done();
}
// 强化班随堂测折线图
function initreport_Qs() {
	myChart_Qs.showLoading()
	$.post(jsBasePath + "/Examinee/List/queryDetailReport.html", {
		type : "强化"
	}, function(data) {
		if (data.length == '0') {
			$(".qianghua").hide();
		}
		var legend = new Array();
		var rTearcher = new Array();
		var lTearcher = new Array();
		var sTearcher = new Array();
		var wTearcher = new Array();

		$.each(data, function(i, info) {
			legend[legend.length] = info.frequency
			rTearcher[rTearcher.length] = info.rTearcher;
			lTearcher[lTearcher.length] = info.lTearcher;
			sTearcher[sTearcher.length] = info.sTearcher;
			wTearcher[wTearcher.length] = info.wTearcher;
		});
		myChart_Qs.hideLoading() // 填入数据
		myChart_Qs.setOption({
			title : {
				text : ''/* ,subtext:subtext */
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '阅读', '听力', '口语', '写作' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				oundaryGap : false,
				data : legend
			},
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 100
			} ],
			series : [ {
				name : '阅读',
				type : 'line',
				stack : '阅读',
				data : rTearcher,
				itemStyle : {
					normal : {
						color : '#3476C4'
					}
				}
			}, {
				name : '听力',
				type : 'line',
				stack : '听力',
				data : lTearcher,
				itemStyle : {
					normal : {
						color : '#55A72D'
					}
				}
			}, {
				name : '口语',
				type : 'line',
				stack : '口语',
				data : sTearcher,
				itemStyle : {
					normal : {
						color : '#E2B20D'
					}
				}
			}, {
				name : '写作',
				type : 'line',
				stack : '写作',
				data : wTearcher,
				itemStyle : {
					normal : {
						color : '#DE7815'
					}
				}
			} ]
		});
	}, 'JSON').done();
}
// 强化班词测
function initreport_Qc() {
	myChart_Qc.showLoading()
	$.post(jsBasePath + "/Examinee/List/report_1.html", {
		type : "强化"
	}, function(data) {
		var legend = new Array();
		var cice = new Array();
		var average = 0;
		$.each(data, function(i, info) {
			legend[legend.length] = info.frequency
			cice[cice.length] = info.cice;
			average += (parseInt(info.cice));
		});
		if (legend.length != 0 && legend.length != null) {
			cice.push(fomatFloat(average / (legend.length), 1))
		}
		if (legend.length) {
			legend.push("平均分");
		}
		myChart_Qc.hideLoading() // 填入数据
		myChart_Qc.setOption({
			color : [ '#3398DB' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : legend,
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 100
			} ],
			series : [ {
				name : '直接访问',
				type : 'bar',
				barWidth : '60%',
				label : {
					normal : {
						show : true,
					}
				},
				data : cice
			} ]
		});
	}, 'JSON').done();
}
// 冲刺班随堂测折线图
function initreport_Cs() {
	myChart_Cc.showLoading()
	$.post(jsBasePath + "/Examinee/List/queryDetailReport.html", {
		type : "冲刺"
	}, function(data) {
		if (data.length == '0') {
			$(".chongci").hide();
		}
		var legend = new Array();
		var rTearcher = new Array();
		var lTearcher = new Array();
		var sTearcher = new Array();
		var wTearcher = new Array();

		$.each(data, function(i, info) {
			legend[legend.length] = info.frequency
			rTearcher[rTearcher.length] = info.rTearcher;
			lTearcher[lTearcher.length] = info.lTearcher;
			sTearcher[sTearcher.length] = info.sTearcher;
			wTearcher[wTearcher.length] = info.wTearcher;
		});
		myChart_Cc.hideLoading() // 填入数据
		myChart_Cc.setOption({
			title : {
				text : ''/* ,subtext:subtext */
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '阅读', '听力', '口语', '写作' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				oundaryGap : false,
				data : legend
			},
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 100
			} ],
			series : [ {
				name : '阅读',
				type : 'line',
				stack : '阅读',
				data : rTearcher,
				itemStyle : {
					normal : {
						color : '#3476C4'
					}
				}
			}, {
				name : '听力',
				type : 'line',
				stack : '听力',
				data : lTearcher,
				itemStyle : {
					normal : {
						color : '#55A72D'
					}
				}
			}, {
				name : '口语',
				type : 'line',
				stack : '口语',
				data : sTearcher,
				itemStyle : {
					normal : {
						color : '#E2B20D'
					}
				}
			}, {
				name : '写作',
				type : 'line',
				stack : '写作',
				data : wTearcher,
				itemStyle : {
					normal : {
						color : '#DE7815'
					}
				}
			} ]
		});
	}, 'JSON').done();
}
// 阶段测试柱状图
function initreport_JH() {
	myChart_JH.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "阶段测试"
	}, function(data) {
		if(data.length=='0'){
			$(".stageCeshi").hide();
		}
		// 预备听、读、写
		var yfTearcher = new Array();
		var tkTearcher = new Array();
		var dTearcher = new Array();
		// 基础听说读写
		var rTearcher = new Array();
		var lTearcher = new Array();
		var sTearcher = new Array();
		var wTearcher = new Array();
		// 强化
		var rTearcher_q = new Array();
		var lTearcher_q = new Array();
		var sTearcher_q = new Array();
		var wTearcher_q = new Array();
		$.each(data, function(i, info) {

			if (info.stage == '预备') {
				dTearcher[dTearcher.length] = info.dTearcher;
				tkTearcher[tkTearcher.length] = info.tkTearcher;
				yfTearcher[yfTearcher.length] = info.yfTearcher;
				wTearcher[wTearcher.length] = 0;
				rTearcher[rTearcher.length] = 0;
				sTearcher[sTearcher.length] = 0;
				lTearcher[lTearcher.length] = 0;
			} else if (info.stage == '基础') {
				dTearcher[dTearcher.length] = 0;
				tkTearcher[tkTearcher.length] = 0;
				yfTearcher[yfTearcher.length] = 0;
				rTearcher[rTearcher.length] = info.rTearcher;
				lTearcher[lTearcher.length] = info.lTearcher;
				sTearcher[sTearcher.length] = info.sTearcher;
				wTearcher[wTearcher.length] = info.wTearcher;
			} else if (info.stage == '强化') {
				dTearcher[dTearcher.length] = 0;
				tkTearcher[tkTearcher.length] = 0;
				yfTearcher[yfTearcher.length] = 0;

				rTearcher[rTearcher.length] = info.rTearcher;
				lTearcher[lTearcher.length] = info.lTearcher;
				sTearcher[sTearcher.length] = info.sTearcher;
				wTearcher[wTearcher.length] = info.wTearcher;
			}
		});
		myChart_JH.hideLoading() // 填入数据
		myChart_JH.setOption({
			tooltip : {
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '读写', '听口', '语法词汇', '阅读', '听力', '写作', '口语' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : [ '预备', '基础', '强化' ]
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 100
			} ],
			series : [ {
				name : '读写',
				type : 'bar',
				data : dTearcher,
				stack:'阅读',
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {
						color : '#FF0080'
					}
				}
			}, {
				name : '听口',
				type : 'bar',
				data : tkTearcher,
				stack : '听力',
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {

						color : '#AAFFFF'
					}
				}
			}, {
				name : '语法词汇',
				type : 'bar',
				data : yfTearcher,
				stack : '语法',
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {

						color : '#8CEA00'
					}
				}
			}, {
				name : '阅读',
				type : 'bar',
				data : rTearcher,
				stack:'阅读',
				label : {
					normal : {
						show : true,
						formatter:function(params){
						    if(params.value=="0"){
	                        return '';
	                            }else
	                            {
	                            	return params.value;
	                            }
						}

					}
				},
				itemStyle : {
					normal : {

						color : '#2F6FBE'
					}
				}
			},

			{
				name : '听力',
				type : 'bar',
				stack : '听力',
				data : lTearcher,
				label : {
					normal : {
						show : true,
						formatter:function(params){
						    if(params.value=="0"){
	                        return '';
	                            }else
	                            {
	                            	return params.value;
	                            }
						}
					}
				},
				itemStyle : {
					normal : {
						color : '#54A72D'
					}
				}
			}, {
				name : '写作',
				type : 'bar',
				stack : '语法',
				label : {
					normal : {
						show : true,
						formatter:function(params){
						    if(params.value=="0"){
	                        return '';
	                            }else
	                            {
	                            	return params.value;
	                            }
						}
					}
				},
				data : wTearcher,
				itemStyle : {
					normal : {
						color : '#DB7615'
					}
				}
			}, {
				name : '口语',
				type : 'bar',
				data : sTearcher,
				label : {
					normal : {
						show : true,
						formatter:function(params){
						    if(params.value=="0"){
	                        return '';
	                            }else
	                            {
	                            	return params.value;
	                            }
						}
					}
				},
				itemStyle : {
					normal : {
						color : '#DDA20E'
					}
				},
				markLine : {
					lineStyle : {
						normal : {
							type : 'dashed',

						}
					},
				}
			},

			]
		});
	}, 'JSON').done();
}
// 托福模考柱状图
function initreport_MK() {
	myChart_MK.showLoading()
	$.post(
			jsBasePath + "/Examinee/List/stageReport.html",
			{
				type : "模考"
			},
			function(data) {
				if (data.length == '0') {
					$(".report_MK_0").hide();
				}
				var rTearcher = new Array();
				var lTearcher = new Array();
				var sTearcher = new Array();
				var wTearcher = new Array();
				var time = new Array();
				var frequency = new Array();
				// 强化
				var strengthen = new Array();
				$.each(data, function(i, info) {
					if (info.stage == "基础") {
						rTearcher[rTearcher.length] = info.rTearcher;
						lTearcher[lTearcher.length] = info.lTearcher;
						sTearcher[sTearcher.length] = info.sTearcher;
						wTearcher[wTearcher.length] = info.wTearcher;
						time[time.length] = info.time;
						frequency[frequency.length] = info.type + info.stage
								+ info.frequency;
					}
					if (info.stage == "强化") {
						rTearcher[rTearcher.length] = info.rTearcher;
						lTearcher[lTearcher.length] = info.lTearcher;
						sTearcher[sTearcher.length] = info.sTearcher;
						wTearcher[wTearcher.length] = info.wTearcher;
						time[time.length] = info.time;
						frequency[frequency.length] = info.type + info.stage
								+ info.frequency;
					}
					if (info.stage == "冲刺") {
						rTearcher[rTearcher.length] = info.rTearcher;
						lTearcher[lTearcher.length] = info.lTearcher;
						sTearcher[sTearcher.length] = info.sTearcher;
						wTearcher[wTearcher.length] = info.wTearcher;
						time[time.length] = info.time;
						frequency[frequency.length] = info.type + info.stage
								+ info.frequency;

					}

				});
				myChart_MK.hideLoading() // 填入数据
				myChart_MK.setOption({
					tooltip : {
						trigger : 'axis',
						axisPointer : { // 坐标轴指示器，坐标轴触发有效
							type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						},
						formatter:function(params){
							 var res =  params[0].name;
							 var total=0;
					            for (var i = 0, l = params.length; i < l; i++) {
					            	if( params[i].value!=null){
					            		res += '<br/>' + params[i].seriesName + ' : ' + params[i].value;
					            	}
					            	total=parseInt(total)+parseInt(params[i].value);
					            }
					            res +='<br/>'+'总分'+":"+total;
					            return res;
						}
					},
					legend : {
						data : [ '阅读', '听力', '口语', '写作' ]
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						containLabel : true
					},
					xAxis : [ {
						type : 'value',
						min : 0,
						max : 120
					} ],
					yAxis : {
						type : 'category',
						data : frequency
					},
					series : [ {
						name : '阅读',
						type : 'bar',
						stack : '成绩',
						label : {
							normal : {
								show : true,
								position : 'insideRight'
							}
						},
						data : rTearcher,
						itemStyle : {
							normal : {
								color : '#4794D4'
							}
						}
					}, {
						name : '听力',
						type : 'bar',
						stack : '成绩',
						label : {
							normal : {
								show : true,
								position : 'insideRight'
							}
						},
						data : lTearcher,
						itemStyle : {
							normal : {
								color : '#5DB632'
							}
						}
					}, {
						name : '口语',
						type : 'bar',
						stack : '成绩',
						label : {
							normal : {
								show : true,
								position : 'insideRight'
							}
						},
						data : sTearcher,
						itemStyle : {
							normal : {
								color : '#DBA50D'
							}
						}
					}, {
						name : '写作',
						type : 'bar',
						stack : '成绩',
						label : {
							normal : {
								show : true,
								position : 'insideRight'
							}
						},
						data : wTearcher,
						itemStyle : {
							normal : {
								color : '#DB7615'
							}
						}
					}, ]
				});
			}, 'JSON').done();
}
// 强化阶段托福模考柱状图
function initreport_MK_1() {
	myChart_MK_1.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "模考",
		stage : "强化"
	}, function(data) {
		
		var rTearcher = new Array();
		var lTearcher = new Array();
		var sTearcher = new Array();
		var wTearcher = new Array();
		var time = new Array();
		// 强化
		var strengthen = new Array();
		$.each(data, function(i, info) {
			rTearcher[rTearcher.length] = info.rTearcher;
			lTearcher[lTearcher.length] = info.lTearcher;
			sTearcher[sTearcher.length] = info.sTearcher;
			wTearcher[wTearcher.length] = info.wTearcher;
			time[time.length] = info.time;
		});
		myChart_MK_1.hideLoading() // 填入数据
		myChart_MK_1.setOption({
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '阅读', '听力', '口语', '写作' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : {
				type : 'value'
			},
			yAxis : {
				type : 'category',
				data : time
			},
			series : [ {
				name : '阅读',
				type : 'bar',
				stack : '成绩',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : rTearcher,
				itemStyle : {
					normal : {
						color : '#4794D4'
					}
				}
			}, {
				name : '听力',
				type : 'bar',
				stack : '成绩',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : lTearcher,
				itemStyle : {
					normal : {
						color : '#5DB632'
					}
				}
			}, {
				name : '口语',
				type : 'bar',
				stack : '成绩',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : sTearcher,
				itemStyle : {
					normal : {
						color : '#DBA50D'
					}
				}
			}, {
				name : '写作',
				type : 'bar',
				stack : '成绩',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : wTearcher,
				itemStyle : {
					normal : {
						color : '#DB7615'
					}
				}
			}, ]
		});
	}, 'JSON').done();
}
// 托福模考柱状图
function initreport_MK_2() {
	myChart_MK_2.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "模考",
		stage : "冲刺"
	}, function(data) {
		var rTearcher = new Array();
		var lTearcher = new Array();
		var sTearcher = new Array();
		var wTearcher = new Array();
		var time = new Array();
		// 强化
		var strengthen = new Array();
		$.each(data, function(i, info) {
			rTearcher[rTearcher.length] = info.rTearcher;
			lTearcher[lTearcher.length] = info.lTearcher;
			sTearcher[sTearcher.length] = info.sTearcher;
			wTearcher[wTearcher.length] = info.wTearcher;
			time[time.length] = info.time;
		});
		myChart_MK_2.hideLoading() // 填入数据
		myChart_MK_2.setOption({
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '阅读', '听力', '口语', '写作' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : {
				type : 'value'
			},
			yAxis : {
				type : 'category',
				data : time
			},
			series : [ {
				name : '阅读',
				type : 'bar',
				stack : '成绩',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : rTearcher,
				itemStyle : {
					normal : {
						color : '#4794D4'
					}
				}
			}, {
				name : '听力',
				type : 'bar',
				stack : '成绩',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : lTearcher,
				itemStyle : {
					normal : {
						color : '#5DB632'
					}
				}
			}, {
				name : '口语',
				type : 'bar',
				stack : '成绩',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : sTearcher,
				itemStyle : {
					normal : {
						color : '#DBA50D'
					}
				}
			}, {
				name : '写作',
				type : 'bar',
				stack : '成绩',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : wTearcher,
				itemStyle : {
					normal : {
						color : '#DB7615'
					}
				}
			}, ]
		});
	}, 'JSON').done();
}
// toffl实考
function initreport_SK() {
	myChart_SK.showLoading()
	$.post(
			jsBasePath + "/Examinee/List/stageReport.html",
			{
				type : "实考"
			},
			function(data) {
				if(data.length=='0'){
					$(".toffleSk").hide();
				}
				var totalGrade = new Array();
				var time = new Array();
				var strengthen = new Array();
				$.each(data, function(i, info) {
					totalGrade[totalGrade.length] = parseInt(info.rTearcher)
							+ parseInt(info.lTearcher)
							+ parseInt(info.sTearcher)
							+ parseInt(info.wTearcher);
					time[time.length] = info.stage + info.frequency;
					skGrade[skGrade.length] = parseInt(info.rTearcher)
							+ parseInt(info.lTearcher)
							+ parseInt(info.sTearcher)
							+ parseInt(info.wTearcher);
				});
				initreport_MK_Z();// 托福模考折线图
				myChart_SK.hideLoading() // 填入数据
				myChart_SK.setOption({
					color : [ '#863411' ],
					tooltip : {
						trigger : 'axis',
						axisPointer : { // 坐标轴指示器，坐标轴触发有效
							type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					legend : {
						data : [ '总分' ]
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						containLabel : true
					},
					xAxis : [ {
						type : 'category',
						data : time,
						axisTick : {
							alignWithLabel : true
						}
					} ],
					yAxis : [ {
						type : 'value',
						min : 0,
						max : 120
					} ],
					series : [ {
						name : '总分',
						type : 'bar',
						barWidth : '60%',
						label : {
							normal : {
								show : true,
							}
						},
						data : totalGrade
					} ]
				});
			}, 'JSON').done();
}
// toffl实考阅读成绩
function initreport_SK_r() {
	myChart_SK_r.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "实考"
	}, function(data) {
		var reading = new Array();
		var time = new Array();
		$.each(data, function(i, info) {
			reading[reading.length] = info.rTearcher;
			time[time.length] = info.stage + info.frequency;
		});
		myChart_SK_r.hideLoading() // 填入数据
		myChart_SK_r.setOption({
			color : [ '#3174C3' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '阅读' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : time,
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 30
			} ],
			series : [ {
				name : '阅读',
				type : 'bar',
				// barWidth: '60%',
				label : {
					normal : {
						show : true,
					}
				},
				data : reading
			} ]
		});
	}, 'JSON').done();
}
// toffl实考听力成绩成绩
function initreport_SK_l() {
	myChart_SK_l.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "实考"
	}, function(data) {
		var listening = new Array();
		var time = new Array();
		$.each(data, function(i, info) {
			listening[listening.length] = info.lTearcher;
			time[time.length] = info.stage + info.frequency;
		});
		myChart_SK_l.hideLoading() // 填入数据
		myChart_SK_l.setOption({
			color : [ '#3174C3' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '听力' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : time,
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 30
			} ],
			series : [ {
				name : '听力',
				type : 'bar',
				label : {
					normal : {
						show : true,
					}
				},
				data : listening
			} ]
		});
	}, 'JSON').done();
}
// toffl实考口语成绩
function initreport_SK_S() {
	myChart_SK_S.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "实考"
	}, function(data) {
		var speaking = new Array();
		var time = new Array();
		$.each(data, function(i, info) {
			speaking[speaking.length] = info.sTearcher;
			time[time.length] = info.stage + info.frequency;
		});
		myChart_SK_S.hideLoading() // 填入数据
		myChart_SK_S.setOption({
			color : [ '#3174C3' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '口语' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : time,
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 30
			} ],
			series : [ {
				name : '口语',
				type : 'bar',
				label : {
					normal : {
						show : true,
					}
				},
				data : speaking
			} ]
		});
	}, 'JSON').done();
}
// toffl实考写作成绩
function initreport_SK_W() {
	myChart_SK_W.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "实考"
	}, function(data) {
		var writing = new Array();
		var time = new Array();
		$.each(data, function(i, info) {
			writing[writing.length] = info.wTearcher;
			time[time.length] = info.stage + info.frequency;
		});
		myChart_SK_W.hideLoading() // 填入数据
		myChart_SK_W.setOption({
			color : [ '#3174C3' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '写作' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : time,
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 30
			} ],
			series : [ {
				name : '写作',
				type : 'bar',
				label : {
					normal : {
						show : true,
					}
				},
				data : writing
			} ]
		});
	}, 'JSON').done();
}
// 基础阶段测试
function initreport_JHBas() {
	myChart_JHBas.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "阶段测试"
	}, function(data) {
		// 预备听、读、写
		var yfTearcher = new Array();
		var tkTearcher = new Array();
		var dTearcher = new Array();
		var wTearcher = new Array();
		$.each(data, function(i, info) {

			if (info.stage == '基础') {
				dTearcher[dTearcher.length] = info.rTearcher;
				tkTearcher[tkTearcher.length] = info.lTearcher;
				yfTearcher[yfTearcher.length] = info.sTearcher;
				wTearcher[wTearcher.length] = info.wTearcher;
			}
		});
		myChart_JHBas.hideLoading() // 填入数据
		myChart_JHBas.setOption({
			tooltip : {
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '阅读', '听力', '写作', '口语' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : []
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 100
			} ],
			series : [ {
				name : '阅读',
				type : 'bar',
				data : dTearcher,
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {

						color : '#2F6FBE'
					}
				}
			},

			{
				name : '听力',
				type : 'bar',
				stack : '听力',
				data : tkTearcher,
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {
						color : '#54A72D'
					}
				}
			}, {
				name : '写作',
				type : 'bar',
				stack : '写作',
				label : {
					normal : {
						show : true,
					}
				},
				data : yfTearcher,
				itemStyle : {
					normal : {
						color : '#DB7615'
					}
				}
			}, {
				name : '口语',
				type : 'bar',
				data : wTearcher,
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {
						color : '#DDA20E'
					}
				},
				markLine : {
					lineStyle : {
						normal : {
							type : 'dashed',

						}
					},
				}
			},

			]
		});
	}, 'JSON').done();
}
// 强化阶段
// 基础阶段测试
function initreport_JHStr() {
	myChart_JHBas.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "阶段测试"
	}, function(data) {
		// 预备听、读、写
		var yfTearcher = new Array();
		var tkTearcher = new Array();
		var dTearcher = new Array();
		var wTearcher = new Array();
		$.each(data, function(i, info) {

			if (info.stage == '强化') {
				dTearcher[dTearcher.length] = info.rTearcher;
				tkTearcher[tkTearcher.length] = info.lTearcher;
				yfTearcher[yfTearcher.length] = info.sTearcher;
				wTearcher[wTearcher.length] = info.wTearcher;
			}
		});
		myChart_JHStr.hideLoading() // 填入数据
		myChart_JHStr.setOption({
			tooltip : {
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '阅读', '听力', '写作', '口语' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : []
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 100
			} ],
			series : [ {
				name : '阅读',
				type : 'bar',
				data : dTearcher,
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {

						color : '#2F6FBE'
					}
				}
			},

			{
				name : '听力',
				type : 'bar',
				stack : '听力',
				data : tkTearcher,
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {
						color : '#54A72D'
					}
				}
			}, {
				name : '写作',
				type : 'bar',
				stack : '写作',
				label : {
					normal : {
						show : true,
					}
				},
				data : yfTearcher,
				itemStyle : {
					normal : {
						color : '#DB7615'
					}
				}
			}, {
				name : '口语',
				type : 'bar',
				data : wTearcher,
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {
						color : '#DDA20E'
					}
				},
				markLine : {
					lineStyle : {
						normal : {
							type : 'dashed',

						}
					},
				}
			},

			]
		});
	}, 'JSON').done();
}
// 预备
function initreport_JHPre() {
	myChart_JHPre.showLoading()
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
		type : "阶段测试"
	}, function(data) {
		// 预备听、读、写
		var yfTearcher = new Array();
		var tkTearcher = new Array();
		var dTearcher = new Array();
		var wTearcher = new Array();
		$.each(data, function(i, info) {

			if (info.stage == '预备') {
				dTearcher[dTearcher.length] = info.dTearcher;
				tkTearcher[tkTearcher.length] = info.tkTearcher;
				yfTearcher[yfTearcher.length] = info.yfTearcher;
			}
		});
		myChart_JHPre.hideLoading() // 填入数据
		myChart_JHPre.setOption({
			tooltip : {
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '读写', '听口', '语法词汇' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : []
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				max : 100
			} ],
			series : [ {
				name : '读写',
				type : 'bar',
				stack : '读写',
				data : dTearcher,
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {

						color : '#2F6FBE'
					}
				}
			},

			{
				name : '听口',
				type : 'bar',
				stack : '听口',
				data : tkTearcher,
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {
						color : '#54A72D'
					}
				}
			}, {
				name : '语法词汇',
				type : 'bar',
				stack : '语法词汇',
				label : {
					normal : {
						show : true,
					}
				},
				data : yfTearcher,
				itemStyle : {
					normal : {
						color : '#DB7615'
					}
				}
			}

			]
		});
	}, 'JSON').done();
}
function add() {
	window.print();
}
// 托福模考折线图
function initreport_MK_Z() {
	myChart_MK_Z.showLoading();
	$.post(
			jsBasePath + "/Examinee/List/queryDetailReportTotal.html",
			{},
			function(data) {
				if(data.length=='0'){
					$(".toffleGrade").hide();
				}
				var rTearcher = new Array();
				var lTearcher = new Array();
				var sTearcher = new Array();
				var wTearcher = new Array();
				
				var rTearcher1 = new Array();
				var lTearcher1 = new Array();
				var sTearcher1 = new Array();
				var wTearcher1 = new Array();
				var time = new Array();
				var series = [];
				$.each(data, function(i, info) {
					console.log(info.type.substring(0,2));
					var type = info.type.substring(0,2);
					if(type=="模考"){
						rTearcher[i] = info.rTearcher;
						lTearcher[i] = info.lTearcher;
						sTearcher[i] = info.sTearcher;
						wTearcher[i] = info.wTearcher;
					}else{
						rTearcher1[i] = info.rTearcher;
						lTearcher1[i] = info.lTearcher;
						sTearcher1[i] = info.sTearcher;
						wTearcher1[i] = info.wTearcher;
					}
					time[time.length]=info.type
				});
				
				myChart_MK_Z.hideLoading() // 填入数据
				myChart_MK_Z.setOption({
					title : {
						text : ''/* ,subtext:subtext */
					},
					tooltip : {
						trigger : 'axis',
						axisPointer : { // 坐标轴指示器，坐标轴触发有效
							type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						},
						formatter:function(params){
							 var res =  params[0].name;
							 var total=0;
					            for (var i = 0, l = params.length; i < l; i++) {
					            	if( params[i].value!=null){
					            		res += '<br/>' + params[i].seriesName + ' : ' + params[i].value;
					            		total=parseInt(total)+parseInt(params[i].value);
					            	}
					            }
					            res += '<br/>' +"总分"+":"+total;
					            return res;
						}
					},
					legend : {
						data : []
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						containLabel : true
					},
					xAxis : [ {
						type : 'category',
						data : time
					} ],
					yAxis : [ {
						type : 'value'
					} ],
					series : [
					{
						name : '模考阅读',
						type : 'bar',
						stack : '模考',
						data : rTearcher,
						itemStyle : {
							normal : {
								color : '#5DB632'
							}
						},
						label : {
							normal : {
								show : true,
								formatter:function(params){
								    if(params.value=="0"){
			                        return '';
			                            }else
			                            {
			                            	return params.value;
			                            }
								}
							}
						},
					}, {
						name : '模考听力',
						type : 'bar',
						stack : '模考',
						data : lTearcher,
						itemStyle : {
							normal : {
								color : '#5DB632'
							}
						},label: {
    		                normal: {
    		                    show: true,
    		                    formatter:function(params){
    							    if(params.value=="0"){
    		                        return '';
    		                            }else
    		                            {
    		                            	return params.value;
    		                            }
    							}
    		                }
    		            },
					},
					 {
						name : '模考口语',
						type : 'bar',
						stack : '模考',
						data : rTearcher,
						itemStyle : {
							normal : {
								color : '#5DB632'
							}
						},
						label : {
							normal : {
								show : true,
								formatter:function(params){
								    if(params.value=="0"){
			                        return '';
			                            }else
			                            {
			                            	return params.value;
			                            }
								}
							}
						},

					},

					{
						name : '模考写作',
						type : 'bar',
						stack : '模考',
						data : wTearcher,
						itemStyle : {
							normal : {
								color : '#5DB632'
							}
						},
						label : {
							normal : {
								show : true,
								formatter:function(params){
								    if(params.value=="0"){
			                        return '';
			                            }else
			                            {
			                            	return params.value;
			                            }
								}
							}
						},
					},
					{
						name : '实考阅读',
						type : 'bar',
						stack : '模考',
						data : rTearcher1,
						itemStyle : {
							normal : {
								color : '#DBA50D'
							}
						},
						label : {
							normal : {
								show : true,
								formatter:function(params){
								    if(params.value=="0"){
			                        return '';
			                            }else
			                            {
			                            	return params.value;
			                            }
								}
							}
						},
					}, {
						name : '实考听力',
						type : 'bar',
						stack : '模考',
						data : lTearcher1,
						itemStyle : {
							normal : {
								color : '#DBA50D'
							}
						},label: {
    		                normal: {
    		                    show: true,
    		                    formatter:function(params){
    							    if(params.value=="0"){
    		                        return '';
    		                            }else
    		                            {
    		                            	return params.value;
    		                            }
    							}
    		                }
    		            },
					}, {
						name : '实考口语',
						type : 'bar',
						stack : '模考',
						data : rTearcher1,
						itemStyle : {
							normal : {
								color : '#DBA50D'
							}
						},
						label : {
							normal : {
								show : true,
								formatter:function(params){
								    if(params.value=="0"){
			                        return '';
			                            }else
			                            {
			                            	return params.value;
			                            }
								}
							}
						},

					},

					{
						name : '实考写作',
						type : 'bar',
						stack : '模考',
						data : wTearcher1,
						itemStyle : {
							normal : {
								color : '#DBA50D'
							}
						},
						label : {
							normal : {
								show : true,
								formatter:function(params){
								    if(params.value=="0"){
			                        return '';
			                            }else
			                            {
			                            	return params.value;
			                            }
								}
							}
						},
					}

					]
				});
			}, 'JSON').done();
};
// 保留小数点后一位小数 src 传入数据，pos 需要保留几位小数
function fomatFloat(src, pos) {
	return Math.round(src * Math.pow(10, pos)) / Math.pow(10, pos);
}
//提示是否有成绩
function initShow(){
	$.post(jsBasePath + "/Examinee/List/stageReport.html", {
	}, function(data) {
		if(data=='[]'){
			alert("该学生暂时无成绩信息");
			return;
		}
	});
}
function initTable() {
	initShow();
	initreport();// 预备阶段随堂测
	initreport_1();// 预备阶段词测
	initreport_Js();// 基础阶段随堂测
	initreport_Jc();// 基础阶段词测
	initreport_Qs();// 强化阶段随堂测
	initreport_Qc();// 强化阶段词测
	initreport_Cs();// 冲刺阶段随堂测
	initreport_JHBas();// 阶段测试汇总
	initreport_JHStr();// 阶段测试汇总
	initreport_JHPre();// 预备阶段测试
	initreport_JH();// 阶段测试汇总
	initreport_MK();// toffl模考成绩
	initreport_SK();// toffl实考成绩
	initreport_SK_r();// toffl实考阅读成绩
	initreport_SK_l();// toffl实考阅读成绩
	initreport_SK_S();// toffl实考阅读成绩
	initreport_SK_W();// toffl实考写作成绩
	// alert(navigator.userAgent);
};
