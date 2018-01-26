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
#score{
 float:left;
}
#percent{
width: 54%;
height: 180px;
margin-left: 26%;
}
.layui-pr{
    margin-top: 4%;

}
    *{  
        margin: 0;  
        padding: 0;  
    }  
    .wrap,.circle,.percent{  
        position: absolute;  
        width: 140px;  
        height: 140px;  
        border-radius: 50%;  
    }  
    .wrap{  
/*         top:50px;   */
/*         left:50px;   */
        background-color: #ccc; 
        margin-left:4%; 
    }  
    .circle{  
        box-sizing: border-box;  
        border:20px solid #ccc;  
        clip:rect(0,200px,200px,70px)!important;  
    }  
    .clip-auto{  
        clip:rect(auto, auto, auto, auto);  
    }  
    .percent{  
        box-sizing: border-box;  
        top:-20px;  
        left:-20px;  
    }  
    .left{  
        transition:transform ease;  
        border:20px solid #009999;  
        clip: rect(0,70px,200px,0);  
    }  
    .right{  
        border:20px solid #009999;  
        clip: rect(0,200px,200px,70px);  
    }  
    .wth0{  
        width:0;  
    }  
    .num{  
        position: absolute;  
        box-sizing: border-box;  
        width: 100px;  
        height: 100px;  
        line-height: 100px;  
        text-align: center;  
        font-size: 40px;  
        left: 20px;  
        top: 20px;  
        border-radius: 50%;  
        background-color: #fff;  
        z-index: 1; 
    } 
</style>
<body>
	<div class="alertFrom">
		<input id="studentId" type="hidden" value="${lsta.studentId}">
		<input id="testId" type="hidden" value="${lsta.testId}">
		<input id="name" type="hidden"  value="${lsta.testName}">
		<input id="percent" type="hidden"  value="${answer.percent}">
		<input id="testNum" type="hidden"  value="${answer.testNum}">
		<input id="accuracyPercent" type="hidden"  value="${la.accuracyPercent}">
		<input id="fluencyPercent" type="hidden"  value="${la.fluencyPercent}">
		<input id="integrityPercent" type="hidden"  value="${la.integrityPercent}">
		<input id="accuracyAvg" type="hidden"  value="${accuracyAvg}">
		<input id="fluencyAvg" type="hidden"  value="${fluencyAvg}">
		<input id="inegrityAvg" type="hidden"  value="${inegrityAvg}">
		<input id="overallTeacher" type="hidden"  value="${la.overallTeacher}">
		<input id="paiming" type="hidden"  value="${paiming}">
		<input id="overPercent" type="hidden"  value="${overPercent}">
		<blockquote class="layui-elem-quote layui-text" >
			<span></span> <span style="margin-left: 5%;">成绩概况</span>
		</blockquote>
		
<div id="score" style="padding-left: 9%;"> 		
  <div class="wrap " >  
    <div class="circle">  
        <div class="percent left"></div>  
        <div class="percent right wth0"></div>  
    </div>  
    <div class="num"><span></span></div>  
</div> 
<div class="clear"
					style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
</div >					
		<div id="percent" style="height:150px!important;padding-left: 12%;">
			<div class="layui-pr">
				<label style="    position: absolute;margin-left: 3%;">准确度</label>
				<div class="layui-progress layui-progress-big"  lay-showpercent="true" lay-filter="demo" style="    margin-top: 3%;margin-left: 22%;">
  					<div class="layui-progress-bar"  id="accuracy" lay-percent="${la.accuracyPercent }%" ></div>
				</div>
				<label style="position: absolute;right:11%">平均${accuracyAvg}%</label>
			</div>	
			<div class="layui-pr">
				<label style="    position: absolute;margin-left: 3%;">流利度</label>
				<div class="layui-progress layui-progress-big"  lay-showpercent="true" lay-filter="demo" style="    margin-top: 3%;margin-left: 22%;">
  					<div class="layui-progress-bar "  id="fluency" lay-percent="${la.fluencyPercent }%" ></div>
				</div>
				<label style="position: absolute;right:11%">平均${fluencyAvg}%</label>
			</div>
			<div class="layui-pr">
				<label style="    position: absolute;margin-left: 3%;">完成度</label>
				<div class="layui-progress layui-progress-big"  lay-showpercent="true" lay-filter="demo" style="    margin-top: 3%;margin-left: 22%;">
  					<div class="layui-progress-bar " id="integrity" lay-percent="${la.integrityPercent}%" ></div>
				</div>
				<label style="position: absolute;right:11%;">平均${inegrityAvg}%</label>
			</div>
		</div>
		<div class="clear" style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
		<div style=" ">
		<p id="text" style="text-indent:2em">
		</p>
		</div>
		<blockquote class="layui-elem-quote layui-text" style="margin-top:2%">
			<span></span> <span style="margin-left: 5%;">题型分析</span>
		</blockquote>
		<div id="questionAnalyse" style="height:250px;"></div>
		<blockquote class="layui-elem-quote layui-text" style="">
			<span></span> <span style="margin-left: 5%;">逐题分析</span>
		</blockquote>
			<table class="layui-table" style="">
			<colgroup>
				<col width="150">
				<col width="200">
				<col>
			</colgroup>
			<tbody>	
					<tr>
						<td style="text-align:center">题号</td>
						<td style="text-align:center">题型</td>
						<td style="text-align:center">难度</td>
						<td style="text-align:center">考试得分</td>
						<td style="text-align:center">正确答案</td>
						<td style="text-align:center">学生答案</td>
					</tr>
					<c:forEach items="${AllQues}" var="ques" varStatus="i">
					<tr>
						<td style="text-align:center">${i.index+1 }</td>
						<td style="text-align:center">${ques.name }</td>
						<td style="text-align:center">${ques.difficulty}</td>
						<td style="text-align:center">${ques.overallTeacher}</td>
						<td style="text-align:center">
						<div id="audioDiv" class="layui-input-inline" ">
						<audio src="${fileurl }${ques.parseAudio}" controls="controls" style="width:200px"></audio>
						</div>
						</td>
						<td style="text-align:center">
						<div id="audioDiv" class="layui-input-inline" ">
							<audio src="${fileurl }${ques.studentAnswer}" controls="controls" style="width:200px"></audio>
						</div></td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/lstclass/edit.js"></script>
<script src="<%=basePath%>/static/echarts/echarts.3.5.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/static/lstclasscorrect/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/lstclasscorrect/stu_score_report.js">
</script>

</html>