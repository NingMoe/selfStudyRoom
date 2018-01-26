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
</style>
<body>
	<div class="alertFrom">
		<input id="nzsPercent" type="hidden" value="${nzsPercent}"> <input
			id="zsPercent" type="hidden" value="${zsPercent}"> <input
			id="class_code" type="hidden" value="${lz.class_code}"> <input
			id="zuoye_id" type="hidden" value="${lz.zuoye_id}">
		<blockquote class="layui-elem-quote layui-text" style="width: 90%">
			<span></span> <span style="margin-left: 5%;">作业概况</span>
		</blockquote>
		<div>
			<div>作业名称：</div>
			<div style="width: 90%; height: 250px;">
				<div id="zuoye_tj" style="width: 49%; height: 250px; float: left;">
					
				</div>
				<div id="zuoye-dfl" style="width: 49%; height: 250px; float: left;">
				</div>
				<div class="clear"
					style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
				<div>
					<div style="width: 49%; float: left; margin-top: -3% !important; text-align: center !important;">作业提交情况</div>
					<div style="width: 49%;; float: left; margin-top: -3% !important; margin-left: 70% !important;">作业提交情况</div>
				</div>
				<div class="clear"
					style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
			</div>
		</div>
		<blockquote class="layui-elem-quote layui-text" style="width: 90%">
			<span></span> <span style="margin-left: 5%;">完成情况</span>
		</blockquote>
		<!-- 		<div style="width:90%;height:250px;border:1px solid red" > -->
		<table class="layui-table" style="width: 93%" lay-size="lg">
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
						<td style="text-align: center">${situ.name }</td>
						<td style="text-align: center">${situ.difficulty}</td>
						<td style="text-align: center">${situ.overall}</td>
						<td style="text-align: center">${situ.accuracy}</td>
						<td style="text-align: center">${situ.integrity}</td>
						<td style="text-align: center">${situ.fluency}</td>
						<td style="text-align: center">${situ.totalPercent}%</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<blockquote class="layui-elem-quote layui-text" style="width: 90%">
			<span></span> <span style="margin-left: 5%;">逐题分析</span>
		</blockquote>
		<div class="layui-collapse" style="width: 93%"
			index="${ques.question_code}">
			<c:forEach items="${questions}" var="ques" varStatus="i">
				<div class="layui-colla-item " >
					<h2 class="layui-colla-title">第${i.index+1 }题</h2>
					<c:if test="${ 0 eq i.index }">
					<div class="layui-colla-content layui-show foreach">
					</c:if>
					<c:if test="${ 0 ne i.index }">
					<div class="layui-colla-content foreach">
					</c:if>
					<div class="layui-colla-content layui-show  foreach" id="${ques.id}">
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
							<label class="layui-form-label" style="margin-left: -6%;">音频解析</label>
							<div class="layui-input-inline" id="recordDiv" style="width:90%;height:71px;padding:8px 10px;margin-right: 0px;">
								<div id="audioDiv" class="detailAudio" class="layui-input-inline" style="margin-top:-2%;width:46%;float:left; margin-left:-10px;margin-top: 0px;">
									<audio id="audio" src="${fileurl}${ques.parse_audio}" controls="controls"></audio>
								</div>
								<a class='layui-btn layui-btn-xs' onclick="zuotiSitu2(this);" style="    margin-left: 41%;">做题情况</a>
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
											<label class="layui-form-label" style="margin-left: -5%;">音频解析</label>
											<div class="layui-input-inline" id="recordDiv" style="width:90%;height:71px;padding:8px 10px;margin-right: 0px;margin-top:-2%">
											<div id="audioDiv" class="detailAudio" class="layui-input-inline" style="width:46%;float:left; margin-left:-10px;margin-top: 14px;">
													<audio id="audio" src="${fileurl}${ques.parse_audio}" controls="controls"></audio>
											</div>
											<a class='layui-btn layui-btn-xs' onclick="zuotiSitu(this);" style="    margin-left: 41%;    margin-top: 3%;">做题情况</a>
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
<script type="text/javascript"
	src="<%=basePath%>/static/lstclass/edit.js"></script>
<script src="<%=basePath%>/static/echarts/echarts.3.5.min.js"></script>
<script type="text/javascript">
	var myChartTj = echarts.init(document.getElementById('zuoye_tj'));
	var myChartDfl = echarts.init(document.getElementById('zuoye-dfl'));
	$(function() {
		layui.use('element', function() {
			var element = layui.element;

		});
		myChartTjFunction();
		myChartDflFunction();
	})
	function myChartTjFunction() {
		var nzsPercent=$("#nzsPercent").val();
		var zsPercent=$("#zsPercent").val();
		myChartTj.showLoading();
		myChartTj.hideLoading(); // 填入数据
		myChartTj.setOption({
			title : {
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : [ '未按时', '准时' ]
			},
			series : [ {
				name : '是否按时提交作业',
				type : 'pie',
				radius : '55%',
				center : [ '45%', '45%' ],
				data : [ {
					value : nzsPercent,
					name : '未按时'
				}, {
					value : zsPercent,
					name : '准时'
				}, ],
				color : [ '#FF0000', '#2589BD' ],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			} ]
		});

	}

	function myChartDflFunction() {
		var zuoye_id = $("#zuoye_id").val();
		var class_code = $("#class_code").val();
		myChartDfl.showLoading()
		$.post(jsBasePath + "/classZuoyeReport/zuoyeDfl.html", {
			"class_code" : class_code,
			"zuoye_id" : zuoye_id
		}, function(data) {
			var twoPointFive = new Array();
			var five = new Array();
			var sevenPointFive = new Array();
			var zero = new Array();
			// 			if(data!=null){
			$.each(data, function(i, info) {
				if (info.scoreSec == 'zero') {
					zero[zero.length] = info.num;
				}
				if (info.scoreSec == 'twoPointFive') {
					twoPointFive[twoPointFive.length] = info.num;
				}
				if (info.scoreSec == 'five') {
					five[five.length] = info.num;
				}
				if (info.scoreSec == 'sevenPointFive') {
					sevenPointFive[sevenPointFive.length] = info.num;
				}
			});
			// 			}
			myChartDfl.hideLoading() // 填入数据
			myChartDfl.setOption({
				title : {
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : [ '<25%', '25%~50%', "50%~75%", "75%~100%" ]
				},
				series : [ {
					name : '是否按时提交作业',
					type : 'pie',
					radius : '55%',
					center : [ '45%', '45%' ],
					data : [ {
						value : zero,
						name : '<25%'
					}, {
						value : twoPointFive,
						name : '25%~50%'
					}, {
						value : five,
						name : '50%~75%'
					}, {
						value : sevenPointFive,
						name : '75%~100%'
					}, ],
					color : [  '#2589BD','#FF0000', '#FFFF00', '#FF9900' ],
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			});
		}, 'JSON').done();
	}
	
	function zuotiSitu(obj){
		var class_code=$("#class_code").val();
		var zuoye_id=$("#zuoye_id").val();
		var question_id =$(obj).parent().parent().parent().parent().attr("id");
		var url = jsBasePath + "/classZuoyeReport/zuotiSitu.html?zuoye_id="+zuoye_id+"&class_code="+class_code+"&id="+question_id;
			layer.open({
				  type : 2,
				  shade : [ 0.5, '#000' ],
				  title : "做题情况", //
				  offset : ['4%'],
				  area : ['85%','90%'],
				  content : url, //捕获的元素
				  cancel : function(index) {
					  layer.close(index);
				  },
				  end : function() {
					  reloadTable();
				  }
		});
	}
	function zuotiSitu2(obj){
		var class_code=$("#class_code").val();
		var zuoye_id=$("#zuoye_id").val();
		var question_id =$(obj).parent().parent().parent().attr("id");
		var url = jsBasePath + "/classZuoyeReport/zuotiSitu.html?zuoye_id="+zuoye_id+"&class_code="+class_code+"&id="+question_id;
			layer.open({
				  type : 2,
				  shade : [ 0.5, '#000' ],
				  title : "做题情况", //
				  offset : ['4%'],
				  area : ['85%','90%'],
				  content : url, //捕获的元素
				  cancel : function(index) {
					  layer.close(index);
				  },
				  end : function() {
					  reloadTable();
				  }
		});
	}
</script>

</html>