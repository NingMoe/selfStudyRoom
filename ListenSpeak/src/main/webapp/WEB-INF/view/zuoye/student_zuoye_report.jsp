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
	width: 12%
}

th {
	text-align: center !important;
}
.float{
	float:left;
	height:300px;
/* 	border:1px solid red ; */
	width:49.7%
}
</style>
<body>
	<div class="alertFrom">
		<div style="width :90%; height:300px;">
			 <input
			id="class_code" type="hidden" value="${lz.class_code}"> <input
			id="zuoye_id" type="hidden" value="${lz.zuoye_id}"><input
			id="student_id" type="hidden" value="${lz.student_id}">
			<input id="totalNumClass" type="hidden" value="${totalNumClass}">
			<input id="beatNumClass" type="hidden" value="${beatNumclass}">
			<input id="wbeatNumClass" type="hidden" value="${wBeatNumClass}">
			<input id="beatPercent" type="hidden" value="${beatPercent}">
			
			<input id="totalNumTest" type="hidden" value="${totalNumTest}">
			<input id="beatNumTest" type="hidden" value="${beatNumTest}">
			<input id="wbeatNumTest" type="hidden" value="${wBeatNumTest}">
			<input id="beatPercentTest" type="hidden" value="${beatPercentTest}">
			<div class="float">
				<blockquote class="layui-elem-quote layui-text" style="width: 90%">
				<span></span> <span style="margin-left: 5%;">作业概况</span>
				</blockquote>
				<div style="text-indent:2em; margin :5% 6%" >本次作业由${zuoyeInfo.teacherName}老师布置，共${zuoyeInfo.count}题，作业
				<c:if test="${ 2 eq zuoyeInfo.tj_status }">准时提交</c:if>
				<c:if test="${ 3 eq zuoyeInfo.tj_status }">延时提交</c:if></div>
				<div style="margin :5% 6%"><span style="font-weight:900;">作业名称：</span>${zuoyeInfo.testName}</div>
				<div style="margin :5% 6%"><span style="font-weight:900;">完成时间：</span><fmt:formatDate value="${zuoyeInfo.tj_tme}" pattern="yyyy年MM月dd日 HH:mm"/></div>
			</div>
			<div class="float" >
				<blockquote class="layui-elem-quote layui-text" style="width: 90%">
				<span></span> <span style="margin-left: 5%;">作业反馈</span>
				</blockquote>
				<div style="position:relative">
					<div style="margin :3% 6%"><span style="font-weight:900;margin :5% 6%">原始得分：${dflMap.dfl}/${dflMap.totalScore }</span></div>
					<div style="margin :3% 6%"><span style="font-weight:900;margin :5% 6%">转换模考得分：${dflMap.zhdfl}/10</span></div>
					<div style="margin :3% 6%"><span style="font-weight:900;margin :5% 6%">得分率：${dflMap.dflPercent}%</span></div>
					<div style="width:50% ;height: 130px ; position:absolute;  top:-9px;right:0px">
							<div id="beatClass" style="float:left;width:50% ;height: 71px ;" ></div>
							<div id="beatClass" style="float:left;width:47% ;height: 71px ;line-height:23px" >
								<p style="margin-top:11%">打败全班</p>
								<p>${beatPercent}%学生</p>
							</div>
							<div class="clear" style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
							<div id="beatTest" style=" float:left; width:50% ;height: 71px ;">
							</div>
							<div id="beatTest" style="width:47% ;float:left;line-height:23px; height: 71px ;">
								<p   style="margin-top:11%">打败系统</p>
								<p>${beatPercentTest}%的学生</p>
							</div>
						<div class="clear" style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
					</div>
				</div>
			<div class="layui-pr " style="margin-top: 6%;">
				<label style="    position: absolute;margin-left: 3%;">准确度</label>
				<div class="layui-progress layui-progress-big"  lay-showpercent="true" lay-filter="demo" style="    margin-top: 3%;margin-left: 22%;">
  					<div class="layui-progress-bar "  id="fluency" lay-percent="${dflMap.accuracy }%" ></div>
				</div>
				<label style="    position: absolute;margin-left: 3%;margin-top:1%">流利度</label>
				<div class="layui-progress layui-progress-big"  lay-showpercent="true" lay-filter="demo" style="    margin-top: 3%;margin-left: 22%;">
  					<div class="layui-progress-bar layui-bg-orange"  id="fluency" lay-percent="${dflMap.fluency }%" ></div>
				</div>
				<label style="    position: absolute;margin-left: 3%; margin-top:1%">完整度</label>
				<div class="layui-progress layui-progress-big"  lay-showpercent="true" lay-filter="demo" style="    margin-top: 3%;margin-left: 22%;">
  					<div class="layui-progress-bar layui-bg-blue"  id="fluency" lay-percent="${dflMap.integrity}%" ></div>
				</div>
			</div>
			</div>
			<div class="clear" style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
		</div>
		<blockquote class="layui-elem-quote layui-text" style="width: 90%">
				<span></span> <span style="margin-left: 5%;">完成情况</span>
		</blockquote>
		<div style="width :90%;">
			<table class="layui-table" style="width: 100%" lay-size="lg">
			<colgroup>
				<col width="100">
				<col width="100">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<td style="text-align: center" width="11%">题号</td>
					<td style="text-align: center" width="11%">题型</td>
					<td style="text-align: center" width="11%">难度</td>
					<td style="text-align: center" width="11%">总分</td>
					<td style="text-align: center" width="11%">准确</td>
					<td style="text-align: center" width="11%">完整</td>
					<td style="text-align: center" width="11%">流利</td>
					<td style="text-align: center" width="23%">平均得分率</td>
				</tr>
				<c:forEach items="${zuoyeSitu}" var="situ" varStatus="i">
					<tr>
						<td style="text-align: center">${i.index+1 }</td>
						<td style="text-align: center">${situ.questionName }</td>
						<td style="text-align: center">${situ.difficulty}</td>
						<td style="text-align: center">${situ.overall}</td>
						<td style="text-align: center">${situ.accuracy}</td>
						<td style="text-align: center">${situ.integrity}</td>
						<td style="text-align: center">${situ.fluency}</td>
						<td style="text-align: center">${situ.overallPercent}%</td>
					</tr>
				</c:forEach>
				<tr>
						<td style="text-align: center">总分</td>
						<td style="text-align: center"></td>
						<td style="text-align: center"></td>
						<td style="text-align: center">${Scores.overall}</td>
						<td style="text-align: center">${Scores.accuracy}</td>
						<td style="text-align: center">${Scores.integrity}</td>
						<td style="text-align: center">${Scores.fluency}</td>
						<td style="text-align: center">${Scores.overallPercent}%</td>
					</tr>
			</tbody>
		</table>
		</div>
		<blockquote class="layui-elem-quote layui-text" style="width: 90%">
				<span></span> <span style="margin-left: 5%;">成长轨迹</span>
		</blockquote>
		<div id="growth" style="width :90%;  height:250px;">
		
		</div>
		<blockquote class="layui-elem-quote layui-text" style="width: 90%">
				<span></span> <span style="margin-left: 5%;">作业详情</span>
		</blockquote>
		<div class="layui-collapse" style="width: 93%"
			index="${ques.question_code}">
			<c:forEach items="${questions}" var="ques" varStatus="i">
				<div class="layui-colla-item " id="${ques.id}">
					<h2 class="layui-colla-title">第${i.index+1 }题</h2>
					<c:if test="${ 0 eq i.index }">
					<div class="layui-colla-content layui-show foreach">
					</c:if>
					<c:if test="${ 0 ne i.index }">
					<div class="layui-colla-content foreach">
					</c:if>
						<c:if test="${1 eq ques.type }">
							<c:forEach items="${ques.list}" var="li">
								<div class="layui-col-md12 bt " style="    margin-left: 1%;">
									<span>题型：${ques.questionName}&nbsp;&nbsp;&nbsp;
										难度：${ques.difficulty}&nbsp;&nbsp;&nbsp; 得分：${ques.overall }&nbsp;&nbsp;&nbsp;
										准确：${ques.accuracy }&nbsp;&nbsp;&nbsp; 完整：${ques.fluency }&nbsp;&nbsp;&nbsp;
										流利：${ques.integrity }&nbsp;&nbsp;&nbsp; 得分率：${ques.overallPercent }%
									</span>
								</div>
							</c:forEach>
						<div class="layui-form-item">
							<label class="layui-form-label" style="margin-left: -4%;">评语</label>
							<div class="layui-input-inline" style="width: 85%;">
								<textarea class="layui-textarea" name="studentComment" id="studentComment"
									cols="100" rows="8" style="width: 100%; height: 100px;">${ques.answer_comment}</textarea>
							</div>
						</div>
						</c:if>
						<div class="layui-form-item">
							<label class="layui-form-label" style="margin-left: -4%;">提示语</label>
							<div class="layui-input-inline" style="width: 85%;">
								<textarea class="layui-textarea" name="zdmessage" id="zdmessage"
									cols="100" rows="8" style="width: 100%; height: 100px;">${ques.zdmessage}</textarea>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label" style="margin-left: -4%;">题干</label>
							<div class="layui-input-inline" style="width: 85%;">
								<textarea class="layui-textarea" name="content" id="content"
									cols="100" rows="8" style="width: 100%; height: 100px;">${ques.content}</textarea>
							</div>
						</div>
						<c:if test="${!empty ques.parse_text }">
							<div class="layui-form-item">
								<label class="layui-form-label" style="margin-left: -4%;">解析</label>
								<div class="layui-input-inline" style="width: 85%;">
									<textarea class="layui-textarea" name="content" id="content"
										cols="100" rows="8" style="width: 100%; height: 100px;">${ques.content}</textarea>
								</div>
							</div>
						</c:if>
						<c:if test="${!empty ques.parse_audio and 1 eq ques.type }">
						<div class="layui-form-item" style="margin-bottom: 10px;margin-left:2%">
												<label class="layui-form-label" style="margin-left: -5%;">学生答案</label>
												<div id="audioDiv" class="detailAudio" class="layui-input-inline" style="width:46%;float:left; margin-left:-10px;margin-top: 3px;">
													<audio id="audio" src="${fileurl}${ques.student_answer}" controls="controls"></audio>
												</div>
												<label class="layui-form-label" style="margin-left: -5%;">音频解析</label>
												<div class="layui-input-inline" id="recordDiv" style="margin-top:-1%">
													<div id="audioDiv" class="detailAudio" class="layui-input-inline" style="width:46%;float:left; margin-left:-10px;margin-top: 14px;">
															<audio id="audio" src="${fileurl}${ques.parse_audio}" controls="controls"></audio>
													</div>
												</div>
											</div>
						</c:if>
						
						<c:if test="${2 eq ques.type }">
							<c:forEach items="${ques.list}" var="lis" varStatus="j">
								<div class="layui-collapse" style="width: 85%; margin-left: 5%;">
									<div class="layui-colla-item" id="${lis.id}">
										<h2 class="layui-colla-title">问题${j.index+1 }</h2>
										<div class="layui-colla-content layui-show">
											<div class="layui-col-md12 bt " style="    margin-left: 3%;">
											<span>题型：${lis.questionName}&nbsp;&nbsp;&nbsp;
												难度：${lis.name }&nbsp;&nbsp;&nbsp; 得分：${lis.overall }&nbsp;&nbsp;&nbsp;
												准确：${lis.accuracy }&nbsp;&nbsp;&nbsp; 完整：${lis.fluency }&nbsp;&nbsp;&nbsp;
												流利：${lis.integrity }&nbsp;&nbsp;&nbsp; 得分率：${lis.overallPercent }%
											</span>
										</div>
										<div class="layui-form-item">
												<label class="layui-form-label" style="margin-left: -3%;">评语</label>
												<div class="layui-input-inline" style="width: 85%;">
													<textarea class="layui-textarea" name="content"
														id="topic" cols="100" rows="8"
														style="width: 100%; height: 100px;">${lis.answer_comment}</textarea>
												</div>
											</div>
											<div class="layui-form-item">
												<label class="layui-form-label" style="margin-left: -3%;">小题干</label>
												<div class="layui-input-inline" style="width: 85%;">
													<textarea class="layui-textarea" name="content"
														id="topic" cols="100" rows="8"
														style="width: 100%; height: 100px;">${lis.topic}</textarea>
												</div>
											</div>
											<div class="layui-form-item">
											<label class="layui-form-label" style="margin-left: -3%;">解析</label>
												<div class="layui-input-inline" style="width: 85%;">
													<textarea class="layui-textarea" name="content"
														id="parse_text" cols="100" rows="8" 
														style="width: 100%; height: 100px;">${lis.parse_text}</textarea>
												</div>
											</div>	
											<div class="layui-form-item" style="margin-bottom: 10px;margin-left:2%">
												<label class="layui-form-label" style="margin-left: -5%;">学生答案</label>
												<div id="audioDiv" class="detailAudio" class="layui-input-inline" style="width:46%;float:left; margin-left:-10px;margin-top: 3px;">
													<audio id="audio" src="${fileurl}${lis.student_answer}" controls="controls"></audio>
												</div>
												<label class="layui-form-label" style="margin-left: -5%;">音频解析</label>
												<div class="layui-input-inline" id="recordDiv" style="margin-top:-1%">
													<div id="audioDiv" class="detailAudio" class="layui-input-inline" style="width:46%;float:left; margin-left:-10px;margin-top: 14px;">
															<audio id="audio" src="${fileurl}${ques.parse_audio}" controls="controls"></audio>
													</div>
												</div>
											
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
<script src="<%=basePath%>/static/echarts/echarts.3.5.min.js"></script>
<script type="text/javascript">
		var myChartGrowth = echarts.init(document.getElementById('growth'));
		var myChartClass=echarts.init(document.getElementById("beatClass"));
		var myChartTest=echarts.init(document.getElementById("beatTest"));
	layui.use('element', function() {
		var element = layui.element;
		myChartTjFunction();
		myChartClassFunction();
		myChartTestFunction();
	});
	
	function myChartTjFunction() {
		var zuoye_id = $("#zuoye_id").val();
		var class_code = $("#class_code").val();
		var student_id=$("#student_id").val();
		myChartGrowth.showLoading()
		$.post(jsBasePath + "/classZuoyeReport/getGrowth.html", {
			"class_code" : class_code,
			"zuoye_id" : zuoye_id,
			"student_id":student_id
		}, function(data) {
			var time =new Array();
			var score=new Array();
			var times=new Array();
			var overall=new Array();
			var dati_num =new Array(); 
			var totalNum=new Array();
			$.each(data,function(i,info){
				time[time.length]=info.release_time;
				score[score.length]=info.totalScore;
				times[times.length]="第"+(i+1)+"次";
				overall[overall.length]=info.overall;
				dati_num[dati_num.length]=info.dati_num;
				totalNum[totalNum.length]=info.totalNum;
			});
			myChartGrowth.hideLoading() // 填入数据
			myChartGrowth.setOption({
			 title: {
			     text: '成长轨迹'
			    },
			    tooltip: {
			        trigger: 'axis',
			        formatter:function(params){
						 var res =  params[0].name;
						 var i=parseInt(res.substr(0,2).substr(1,2))-1;
				            		res += '<br/> '+"单次作业日期"+":"+time[i]
				            				+'<br/>' + "单次做题量" + ' : ' +dati_num[i]
				            				+'<br/> '+"累计做题量"+":"+totalNum[i]
				            				+'<br/> '+"累计做题得分率"+":"+totalNum[i]
				            return res;
					}
			    },
			    legend: {
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: times
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'',
			            type:'line',
			            stack: '总量',
			            data:score
			        },
			       
			    ]
		});
		},"JSON");
	}
	function myChartClassFunction(){
		var zuoye_id = $("#zuoye_id").val();
		var class_code = $("#class_code").val();
		var student_id=$("#student_id").val();
		var beatNumClass=$("#beatNumClass").val();
		var wbeatNumClass=$("#wbeatNumClass").val();
		myChartClass.showLoading();
			myChartClass.hideLoading() // 填入数据
			myChartClass.setOption({
// 				 tooltip: {
// 				        trigger: 'item',
// 				        formatter: "{b}: {d}%"
// 				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				    },
				    series: [
				        {
				            name:'',
				            type:'pie',
				            radius: ['50%', '70%'],
// 				            avoidLabelOverlap: false,
				            label: {
				                normal: {
				                    show: false,
				                    position: 'center'
				                },
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '10',
				                        fontWeight: 'bold'
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: false
				                }
				            },
				            data:[
				                {value:beatNumClass, name:'击败'},
				                {value:wbeatNumClass, name:'未击败'},
				               
				            ]
				        }
				    ]
		});
	}
	function myChartTestFunction(){
		var beatNumTest=$("#beatNumTest").val();
		var wbeatNumTest=$("#wbeatNumTest").val();
		myChartTest.showLoading();
		myChartTest.hideLoading() // 填入数据
			myChartTest.setOption({
// 				 tooltip: {
// 				        trigger: 'item',
// 				        formatter: "{b}: {d}%"
// 				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				    },
				    series: [
				        {
				            name:'',
				            type:'pie',
				            radius: ['50%', '70%'],
				            avoidLabelOverlap: false,
				            label: {
				                normal: {
				                    show: false,
				                    position: 'center'
				                },
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '10',
				                        fontWeight: 'bold'
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: false
				                }
				            },
				            data:[
				                {value:beatNumTest, name:'击败'},
				                {value:wbeatNumTest, name:'未击败'},
				               
				            ]
				        }
				    ]
		});
	}
</script>

</html>