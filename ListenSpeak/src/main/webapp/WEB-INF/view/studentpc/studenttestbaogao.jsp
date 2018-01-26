<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<link rel="stylesheet" href="<%=basePath %>/static/studentpc/css/less.css">
<script type="text/javascript" src="<%=basePath %>/static/studentpc/js/studenttestbaogao.js"></script>
<script type="text/javascript">
var transcendCount = ${(student_test.overall_teacher != null ? student_test.overall_teacher : student_test.overall_sogou) * 10};
var a = ${(student_test.accuracy_teacher != null ? student_test.accuracy_teacher : student_test.accuracy_sogou) > 10 ? 100 : (student_test.accuracy_teacher != null ? student_test.accuracy_teacher : student_test.accuracy_sogou) * 10};
var b = ${(student_test.fluency_teacher != null ? student_test.fluency_teacher : student_test.fluency_sogou) > 10 ? 100 : (student_test.fluency_teacher != null ? student_test.fluency_teacher : student_test.fluency_sogou) * 10};
var c = ${(student_test.integrity_teacher != null ? student_test.integrity_teacher : student_test.integrity_sogou) > 10 ? 100 : (student_test.integrity_teacher != null ? student_test.integrity_teacher : student_test.integrity_sogou) * 10};
var d = ${(class_test.accuracy_teacher != null ? class_test.accuracy_teacher : class_test.accuracy_sogou) > 10 ? 100 : (class_test.accuracy_teacher != null ? class_test.accuracy_teacher : class_test.accuracy_sogou) * 10};
var e = ${(class_test.fluency_teacher != null ? class_test.fluency_teacher : class_test.fluency_sogou) > 10 ? 100 : (class_test.fluency_teacher != null ? class_test.fluency_teacher : class_test.fluency_sogou) * 10};
var f = ${(class_test.integrity_teacher != null ? class_test.integrity_teacher : class_test.integrity_sogou) > 10 ? 100 : (class_test.integrity_teacher != null ? class_test.integrity_teacher : class_test.integrity_sogou) * 10};
</script>
</head>
<body>
<div class="public-lookwork">
	<div class="lookwork-top">
		<p>测试日期：<fmt:formatDate value="${student_test.open_time }" pattern="yyyy.MM.dd"/></p>
		<p class="public-callback" onclick="goback();" style="cursor:pointer"><img src="<%=basePath %>/static/studentpc/images/17_04_03.png" alt="">返回</p>
		<div class="clearfix"></div>
			<div class="ceshi-main">
				<p class="fankui">·&nbsp成绩概况</p>
				<div class="ceshi-left">
					<div class="wrap"> 
						<div class="circle"> 
						<div class="percent left"></div> 
						<%-- <div class="percent right wth0">
						<fmt:formatNumber type="number" value="${(student_test.overall_teacher ne null ? student_test.overall_teacher : student_test.overall_sogou) / 10}" maxFractionDigits="0"/>%</div> 
						--%>
						<div class="percent right wth0">80%</div> 
						</div>
						<div class="num">
						<fmt:formatNumber type="number" value="${student_test.overall_teacher ne null ? student_test.overall_teacher : student_test.overall_sogou}" maxFractionDigits="0"/>/10
						<br>得分</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="ceshi-right">
					<div class="wtmbtb-z">
						<p>准确度</p>
						<div class="wtmbtb-jd1">
							<p style="width: <fmt:formatNumber type="number" value="${student_test.accuracy_teacher ne null ? student_test.accuracy_teacher * 10 : student_test.accuracy_sogou * 10}" maxFractionDigits="2"/>%; background-image: url(<%=basePath %>/static/studentpc/images/17_03.jpg);">
							<fmt:formatNumber type="number" value="${student_test.accuracy_teacher ne null ? student_test.accuracy_teacher * 10 : student_test.accuracy_sogou * 10}" maxFractionDigits="2"/>%</p>
						</div>
						<div class="clearfix"></div>
						<p class="aver"><img src="<%=basePath %>/static/studentpc/images/21_04_03.png" alt="">平均分
						<fmt:formatNumber type="number" value="${class_test.accuracy_teacher ne null ? class_test.accuracy_teacher * 10 : class_test.accuracy_sogou * 10}" maxFractionDigits="2"/>%</p>
					</div>
					<div class="wtmbtb-l">
						<p>流利度</p>
						<div class="wtmbtb-jd2">
							<p style="width: <fmt:formatNumber type="number" value="${student_test.fluency_teacher ne null ? student_test.fluency_teacher * 10 : student_test.fluency_sogou * 10}" maxFractionDigits="2"/>%; background-image: url(<%=basePath %>/static/studentpc/images/17_06.jpg);">
							<fmt:formatNumber type="number" value="${student_test.fluency_teacher ne null ? student_test.fluency_teacher * 10 : student_test.fluency_sogou * 10}" maxFractionDigits="2"/>%</p>
						</div>
						<div class="clearfix"></div>
						<p class="aver"><img src="<%=basePath %>/static/studentpc/images/21_04_03.png" alt="">平均分
						<fmt:formatNumber type="number" value="${class_test.fluency_teacher ne null ? class_test.fluency_teacher * 10 : class_test.fluency_sogou * 10}" maxFractionDigits="2"/>%</p>
					</div>
					<div class="wtmbtb-w">
						<p>完成度</p>
						<div class="wtmbtb-jd3">
							<p style="width: <fmt:formatNumber type="number" value="${student_test.integrity_teacher ne null ? student_test.integrity_teacher * 10 : student_test.integrity_sogou * 10}" maxFractionDigits="2"/>%; background-image: url(<%=basePath %>/static/studentpc/images/17_08.png);">
							<fmt:formatNumber type="number" value="${student_test.integrity_teacher ne null ? student_test.integrity_teacher * 10 : student_test.integrity_sogou * 10}" maxFractionDigits="2"/>%</p>
						</div>
						<div class="clearfix"></div>
						<p class="aver"><img src="<%=basePath %>/static/studentpc/images/21_04_03.png" alt="">平均分
						<fmt:formatNumber type="number" value="${class_test.integrity_teacher ne null ? class_test.integrity_teacher * 10 : class_test.integrity_sogou * 10}" maxFractionDigits="2"/>%</p>						
					</div>
					<p>${student_test.student_name }同学,你本次听口模考总成绩为<fmt:formatNumber type="number" value="${(student_test.overall_teacher ne null ? student_test.overall_teacher : student_test.overall_sogou) * student_test.test_num}" maxFractionDigits="0"/>分，超过了<fmt:formatNumber type="number" value="${transcend_count * 100 }" maxFractionDigits="0"/>%的学生，在模考中排名第<fmt:formatNumber type="number" value="${than_count + 1 }" maxFractionDigits="0"/>名</p>
					<p>
					<c:set value="0" var="da" />
					<c:set value="0" var="df" />
					<c:set value="0" var="di" />
					<c:set value="0" var="ga" />
					<c:set value="0" var="gf" />
					<c:set value="0" var="gi" />
					<c:if test="${(student_test.accuracy_teacher ne null ? student_test.accuracy_teacher : student_test.accuracy_sogou) < (class_test.accuracy_teacher ne null ? class_test.accuracy_teacher : class_test.accuracy_sogou)}">
						<c:set value="1" var="da" />
					</c:if>
					<c:if test="${(student_test.fluency_teacher ne null ? student_test.fluency_teacher : student_test.fluency_sogou) < (class_test.fluency_teacher ne null ? class_test.fluency_teacher : class_test.fluency_sogou)}">
						<c:set value="1" var="df" />
					</c:if>
					<c:if test="${(student_test.integrity_teacher ne null ? student_test.integrity_teacher : student_test.integrity_sogou) < (class_test.integrity_teacher ne null ? class_test.integrity_teacher : class_test.integrity_sogou)}">
						<c:set value="1" var="di" />
					</c:if>
					<c:if test="${(student_test.accuracy_teacher ne null ? student_test.accuracy_teacher : student_test.accuracy_sogou) > (class_test.accuracy_teacher ne null ? class_test.accuracy_teacher : class_test.accuracy_sogou)}">
						<c:set value="1" var="ga" />
					</c:if>
					<c:if test="${(student_test.fluency_teacher ne null ? student_test.fluency_teacher : student_test.fluency_sogou) > (class_test.fluency_teacher ne null ? class_test.fluency_teacher : class_test.fluency_sogou)}">
						<c:set value="1" var="gf" />
					</c:if>
					<c:if test="${(student_test.integrity_teacher ne null ? student_test.integrity_teacher : student_test.integrity_sogou) > (class_test.integrity_teacher ne null ? class_test.integrity_teacher : class_test.integrity_sogou)}">
						<c:set value="1" var="gi" />
					</c:if>
					
					<!-- 都不相等的情况 -->
					<c:if test="${da eq '1' && df eq '1' && di eq '1' && ga eq '0' && gf eq '0' && gi eq '0'}">
						其中，准确度、流利度和完成度均低于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '0' && ga eq '1' && gf eq '1' && gi eq '1'}">
						其中，准确度、流利度和完成度均高于平均值。
					</c:if>
					<c:if test="${da eq '1' && df eq '1' && di eq '0' && ga eq '0' && gf eq '0' && gi eq '1'}">
						其中，准确度和流利度低于平均值，完成度高于平均值。
					</c:if>
					<c:if test="${da eq '1' && df eq '0' && di eq '1' && ga eq '0' && gf eq '1' && gi eq '0'}">
						其中，准确度和完成度低于平均值，流利度高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '1' && di eq '1' && ga eq '1' && gf eq '0' && gi eq '0'}">
						其中，流利度和完成度低于平均值，准确度高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '1' && ga eq '1' && gf eq '1' && gi eq '0'}">
						其中，完成度低于平均值，准确度和流利度高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '1' && di eq '0' && ga eq '1' && gf eq '0' && gi eq '1'}">
						其中，流利度低于平均值，准确度和完成度高于平均值。
					</c:if>
					<c:if test="${da eq '1' && df eq '0' && di eq '0' && ga eq '0' && gf eq '1' && gi eq '1'}">
						其中，准确度低于平均值，流利度和完成度高于平均值。
					</c:if>
					
					<!-- 一个相等的情况 -->
					<c:if test="${da eq '1' && df eq '1' && di eq '0' && ga eq '0' && gf eq '0' && gi eq '0'}">
						其中，准确度和流利度均低于平均值。
					</c:if>
					<c:if test="${da eq '1' && df eq '0' && di eq '1' && ga eq '0' && gf eq '0' && gi eq '0'}">
						其中，准确度和完成度均低于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '1' && di eq '1' && ga eq '0' && gf eq '0' && gi eq '0'}">
						其中，流利度和完成度均低于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '0' && ga eq '1' && gf eq '1' && gi eq '0'}">
						其中，准确度和流利度均高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '0' && ga eq '1' && gf eq '0' && gi eq '1'}">
						其中，准确度和完成度均高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '0' && ga eq '0' && gf eq '1' && gi eq '1'}">
						其中，流利度和完成度均高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '1' && di eq '0' && ga eq '0' && gf eq '0' && gi eq '1'}">
						其中，流利度低于平均值，完成度高于平均值。
					</c:if>
					<c:if test="${da eq '1' && df eq '0' && di eq '0' && ga eq '0' && gf eq '0' && gi eq '1'}">
						其中，准确度低于平均值，完成度高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '1' && ga eq '0' && gf eq '1' && gi eq '0'}">
						其中，完成度低于平均值，流利度高于平均值。
					</c:if>
					<c:if test="${da eq '1' && df eq '0' && di eq '0' && ga eq '0' && gf eq '1' && gi eq '0'}">
						其中，准确度低于平均值，流利度高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '1' && ga eq '1' && gf eq '0' && gi eq '0'}">
						其中，完成度低于平均值，准确度高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '1' && di eq '0' && ga eq '1' && gf eq '0' && gi eq '0'}">
						其中，流利度低于平均值，准确度高于平均值。
					</c:if>
					
					<!-- 两个相等的情况 -->
					<c:if test="${da eq '1' && df eq '0' && di eq '0' && ga eq '0' && gf eq '0' && gi eq '0'}">
						其中，准确度低于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '1' && di eq '0' && ga eq '0' && gf eq '0' && gi eq '0'}">
						其中，流利度低于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '1' && ga eq '0' && gf eq '0' && gi eq '0'}">
						其中，完成度低于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '0' && ga eq '1' && gf eq '0' && gi eq '0'}">
						其中，准确度高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '0' && ga eq '0' && gf eq '1' && gi eq '0'}">
						其中，流利度高于平均值。
					</c:if>
					<c:if test="${da eq '0' && df eq '0' && di eq '0' && ga eq '0' && gf eq '0' && gi eq '1'}">
						其中，完成度高于平均值。
					</c:if>
					
					请继续努力，希望你下次考出更好的成绩，不断超越自己</p>
				</div>
		</div>
	</div>
	<div class="lookwork-curve">
		<div class="curve-top">
			<p>·&nbsp成长轨迹</p>
		</div>
		<div id="report" style="width: 100%; height: 100%;"></div>
	</div>
	<div class="lookwork-main" style="margin-top:0.8rem!important; margin-bottom:0.3rem;">
		<p>·&nbsp逐题分析</p>
		<table class="layui-table" lay-even="" lay-skin="nob" style="margin:0">
			<thead>
				<th>题号</th>
				<th>题型</th>
				<th>难度</th>
				<th>得分率</th>
				<th>正确答案</th>
				<th>学生回答</th>
			</thead>
			<tbody>
				<c:if test="${qlist ne null }">
					<c:forEach items="${qlist }" var="squestion">
						<c:forEach items="${squestion.answer_list }" var="alist" varStatus="status">
							<tr id="zhutifenxi">
								<c:if test="${status.first}">
									<td>${squestion.xh }</td>
									<td>
										<c:if test="${squestion.tname eq '情景问答' }">
											${squestion.tname }(${status.index + 1})
										</c:if>
										<c:if test="${squestion.tname ne '情景问答' }">
											${squestion.tname }
										</c:if>
									</td>
									<td>${squestion.difficulty eq 'A' ? "简单" : (squestion.difficulty eq 'B' ? "中等" : "困难")}</td>
									<td>
										<c:set value="0" var="sum" />
										<c:set value="0" var="count" />
										<c:forEach items="${squestion.answer_list }" var="blist">
											<c:set value="${sum + (blist.overall_teacher ne null ? blist.overall_teacher : blist.overall_sogou)}" var="sum" />
											<c:set value="${count + 1}" var="count" />
										</c:forEach>
										<fmt:formatNumber type="number" value="${sum/count * 10 }" maxFractionDigits="0"/>%
									</td>
									<td>
										<img onclick="bofang(this);" src="<%=basePath %>/static/studentpc/images/21_03.png" alt="" style="vertical-align: middle;">
										<audio controls="controls" src="<%=osspath %>${alist.parse_audio }" style="vertical-align: middle; display:none;"></audio>
									</td>
									<td>
										<img onclick="bofang(this);" src="<%=basePath %>/static/studentpc/images/21_03.png" alt="" style="vertical-align: middle;">
										<audio st controls="controls" src="<%=osspath %>${alist.student_answer }" style="vertical-align: middle; display:none;"></audio>
									</td>
								</c:if>
								<c:if test="${!status.first}">
									<td></td>
									<td>
										<c:if test="${squestion.tname eq '情景问答' }">
											${squestion.tname }(${status.index + 1})
										</c:if>
										<c:if test="${squestion.tname ne '情景问答' }">
											${squestion.tname }
										</c:if>
									</td>
									<td></td>
									<td></td>
									<td>
										<img onclick="bofang(this);" src="<%=basePath %>/static/studentpc/images/21_03.png" alt="" style="vertical-align: middle;">
										<audio controls="controls" src="<%=osspath %>${alist.parse_audio }" style="vertical-align: middle; display:none;"></audio>
									</td>
									<td>
										<img onclick="bofang(this);" src="<%=basePath %>/static/studentpc/images/21_03.png" alt="" style="vertical-align: middle;">
										<audio st controls="controls" src="<%=osspath %>${alist.student_answer }" style="vertical-align: middle; display:none;"></audio>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="clearfix"></div>
	</div>
</div>
</body>
<script src="<%=basePath %>/static/echarts/echarts.3.5.min.js"></script>
<script type="text/javascript">
var report = echarts.init(document.getElementById('report'));
report.setOption({
	color: ['#ffffff','#ffffffb2'],
	backgroundColor: '#50D4C3',
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter: function (params,ticket,callback) {
            var res = params[0].name;
            $.each(params,function(index,p){
            	res += '<br/>'+p.seriesName+':'+p.value+"%";
            });
            return res;
        } 
    },
    legend: {
        data:['个人得分率', '平均得分率'],
        textStyle:{color:['#ffffff','#ffffffb2']},
    },
    //工具包
    /* toolbox: {
        feature: {
            saveAsImage: {}//保存为图片
        }
    }, */
    grid: {
        left: '4%',
        right: '3%',
        bottom: '15%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['朗读短文', '情景问答', '话题简述'],
            axisTick: {
                alignWithLabel: true
            },
            // 控制网格线是否显示
            splitLine: {
                show: false,
                //  改变轴线颜色
                lineStyle: {
                    // 使用深浅的间隔色
                    color: ['#ffffff']
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
                    fontSize:'14'
                },
            }
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel:{
                formatter: function (value) {
                    var texts = [];
                    if(value===0){
                        texts.push('0');
                    }else if(value<=20){
                        texts.push('20%');
                    }else if(value <= 40){
                        texts.push('40%');
                    }else if(value <= 60){
                        texts.push('60%');
                    }else if(value <= 80){
                        texts.push('80%');
                    }else if(value <= 100){
                        texts.push('100%');
                    }
                    return texts;
                }
            },
            max: 100,
            min: 0,
            boundaryGap: [0, 20, 40, 60, 80, 100],
            // 控制网格线是否显示
            splitLine: {
                show: true,
                //  改变轴线颜色
                lineStyle: {
                    // 使用深浅的间隔色
                    color: ['#ffffff7f']
                }
            },
            //改变y轴颜色
            axisLine:{
                lineStyle:{
                    color:'#ffffff'
                    
                }
            }
        }
    ],
    series : [
        {
            name:'个人得分率',
            type:'bar',
            barWidth: '10%',
            label: {
                normal: {
                    show: true,
                    position: 'top',
                    formatter: '{c}%'
                }
            },
            data:[a, b, c]
        },
        {
            name:'平均得分率',
            type:'bar',
            barWidth: '10%',
            label: {
                normal: {
                    show: true,
                    position: 'top',
                    formatter: '{c}%'
                }
            },
            data:[d, e, f]
        }
    ]
});
</script>
</html>