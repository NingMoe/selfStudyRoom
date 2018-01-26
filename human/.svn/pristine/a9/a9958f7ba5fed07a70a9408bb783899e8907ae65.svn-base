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
</head>
<style>
p {
	margin-left: 2%;
}

.choose {
	width: 13%;
	margin-left: 2%;
}

.month {
	width: 13%;
	margin-left: 2%;
}

.inline {
	width: 74% !important;
}

.konwledge {
	/* 	width:25%; */
	
}

body {
	
}
.layer {
	display: none;
	border: 1px solid #ddd;
	margin-top: 1%;
	position: fixed;
	width: 50%;
	height: 450px;
	z-index: 11;
	background: white;
	top: 0%;
	left: 19%;
}

.difficultnum {
	height: 26px;
	width: 24%;
	margin-left: 11px;
	margin-top: 11px;
}

.diflevel {
	margin-left: 13%
}
.panel{
	background-color: #000;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 2;
    opacity: 0.3;
    filter: alpha(opacity=30);
    width: 100%;
    height: 100%;
    display:none;
}
</style>
<body>
	<div class="panel"></div>
	<div class="alertFrom">
		<!-- 		<form class="layui-form" id="editForm" action="" method="post"> -->
		<input type="hidden" id="monthId" name="monthId" value="${monthId }">
		<input type="hidden" id="id" name="id" value="${jzb.mainConfigId}">
		<input type="hidden" id="month" name="paperMonth" value="${jzb.month}">
		<input type="hidden" id="subject" name="subject" value="${subject}">
		<input type="hidden" id="tkMonth" name="tkMonth" value="${jzb.tkMonth}">
		<input type="hidden" id="classType" name="classType" value="${classType}"> 
		<input type="hidden" id="grade" name="grade" value="${grade}">
		<input type="hidden" id="deptId" name="deptId" value="${deptId}">
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 7%; margin-left: 4%">试卷配置说明:</label>
			<div class="layui-input-inline inline">
				<p class="ui-p">抽选题库指的是此份试卷将会从几月份的题库中抽取题目，如1月份试卷可以抽取11，12两个月份的题库。</p>
				<p class="ui-p">答题时间限制整张试卷答题的时间。题目数量是此试卷的总题数，通过线为需答对多少题方可通过测试。</p>
				<p class="ui-p">题目数量可以根据需求自定义，例如总共10题，可以分配给语法5题，完型3题，阅读2题，其中语法5题可以分成3星2题，4星3题。</p>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 7%; margin-left: 4%">抽选题库:</label>
			<div class="layui-input-inline inline" style="margin-top: 1%;">
				<button class="layui-btn layui-btn-normal month " index='0'
					value='1'>1月</button>
				<button class="layui-btn layui-btn-normal month " index='0'
					value='2'>2月</button>
				<button class="layui-btn layui-btn-normal month " index='0'
					value='3'>3月</button>
				<button class="layui-btn layui-btn-normal month " index='0'
					value='4'>4月</button>
				<button class="layui-btn layui-btn-normal month " index='0'
					value='5'>5月</button>
				<button class="layui-btn layui-btn-normal month " index='0'
					value='6'>6月</button>
			</div>
			<label class="layui-form-label" style="width: 7%; margin-left: 4%"></label>
			<div class="layui-input-inline inline" style="margin-top: 1%;">
				<button class="layui-btn layui-btn-normal month " index='0' value='7'>7月</button>
				<button class="layui-btn layui-btn-normal month " index='0' value='8'>8月</button>
				<button class="layui-btn layui-btn-normal month " index='0' value='9'>9月</button>
				<button class="layui-btn layui-btn-normal month " index='0' value='A'>10月</button>
				<button class="layui-btn layui-btn-normal month " index='0' value='B'>11月</button>
				<button class="layui-btn layui-btn-normal month " index='0' value='C'>12月</button>
			</div>
		</div>
		<div id="config_content" style="padding-left: 5%;padding-right:11%;">
			<div class="layui-form-item"  >
					<label class="layui-form-label" style="width:8%" >答题时间:</label>
					<div class="layui-input-inline" style="width:12%">
						<input type="text" id="paperTime" name="paperTime" id="paperTime"  lay-verify="number|required" 
							value="${jzb.paperTime}" class="layui-input" placeholder="(分钟)">
					</div>
					<label class="layui-form-label" style="width:8%"style="width:8%">题目数量:</label>
					<div class="layui-input-inline" style="width:12%">
						<input type="text" id="totalQNum"  name="totalQNum" id="totalQNum" lay-verify="number|required"
							value="${jzb.totalQNum}" class="layui-input" placeholder="(题)">
					</div>
					<label class="layui-form-label" style="width:8%">最少答题时间:</label>
					<div class="layui-input-inline" style="width:12%">
						<input type="text" id="minTime" name="minTime"  
							lay-verify="number" value="${jzb.minTime}"  class="layui-input" placeholder="(分钟)" lay-verify="number|required">
					</div>
				</div>
				<div class="layui-form-item"  >
					<c:forEach items="${levels}" var="level">
						<label class="layui-form-label " style="width:8%" >${level.name}:</label>
						<div class="layui-input-inline level" style="width:12%">
							<input type="text" id="level" name="${level.dataValue}" <c:if test="${level.name eq '不通过' }">index="1"</c:if> lay-verify="number|required" 
								value="${level.num}" class="layui-input" placeholder="(题)">
						</div>
					</c:forEach>
				</div>
			</div>
				<div class="layui-form-item" style="    position: absolute; right: 2%;top: 20%">
				<div class="layui-input-block">
					<button id="view" class="layui-btn layui-btn-mini" >查看全部试题</button>
				</div>
				<div class="layui-input-block">
					<button id="view_phone" class="layui-btn layui-btn-mini" >可配试题二维码</button>
				</div>
			</div>
			 <div id="qr" style="display:none;">
			    <span id="qrspan"></span>
			    <div style="margin-left:70px;">
				   <image id="qrCode" src="" style="width:180px;height:180px;"/><br>
				 </div>
			</div> 
		<div index='key'
			style="border: 1px solid #ddd; padding: 1%; margin-top: 1%; margin-left: 5%; width: 76%">
			<div class="layui-form-item knowledge" style="margin-left: 5%;">
				<c:forEach items="${zsdList}" var="zsd">
					<div class='config-paper'>
						<p class='info-kl' style='margin: 1%;'>
							<span class='knowledgeName' style='' index='${zsd.ID}'>${zsd.knowledge}</span>
							<span class='maxNum' index='${zsd.count}'>(${zsd.count})</span>
							<span style='margin-left: 1%;'><span class='zsd'
								type='text' style='width: 6%; margin-left: 6%; height: 20px;'
								value='${zsd.totalNum}' name='title_num'>${zsd.totalNum}</span></span>
							<button class='layui-btn layui-btn-mini a configEdit'
								style='margin-left: 6%;' onclick='configEdit(this)'>配置</button>
						</p>
						<div class='layer' id="layer"></div>
						</span>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top: 1%;">
			<div class="layui-input-block">
				<button id="data-manger" class="layui-btn" lay-submit=""
					onclick="save(this);">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
			</div>
		</div>

	</div>
	<!-- 	</form> -->
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/jzbTest/TestConfig/jpConfig/paperedit.js">
	
</script>
<script type="text/javascript"
	src="<%=basePath%>/static/jquery/jquery.serializeJson2.js"></script>
</html>