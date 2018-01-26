<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%@include file="/WEB-INF/view/common/bootstrapTable.jsp"%></head>
<link rel="stylesheet"
	href="<%=basePath%>/static/northamerica/examineelist/css/report_phone.css">
<style media="print">
@page {
	size: auto; /* auto is the initial value */
	margin: 0mm; /* this affects the margin in the printer settings */
}

@media print {
	.noprint {
		display: none
	}
}
</style>
<style type="text/css">
.ui-center {
	text-align: center
}
</style>
<body>
	<div class="main-wrap">
			<img class="ui-image" alt=""
				src="<%=basePath%>/static/northamerica/examineelist/image/logo.png">
			<div class="ui-title">合肥新东方之旅</div>
			<div class="ui-center">
				<div class="ui-top-t">
					<span class="ui-font">学生姓名：</span> <span> <input
						class="ui-input" type="text" value="${name }">
				</div>
				<div class="ui-top-t">
					</span> <span class="ui-font">学&nbsp;员&nbsp;号&nbsp;：</span> <span>
						<input class="ui-input" type="text" value="${code }">
					</span>
				</div>
			</div>
		<div class="yubei">
			<div class="ui-start font-s " style="margin-top: 8%;">预备班</div>
			<div class="ui-time font-s">${cc.preStarttime }&nbsp;至
				&nbsp;${cc.preEndtime }</div>
			<div class="ui-start ui-tearcher">授课老师</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>听力口语</span> <span> <input class="ui-input dash"
					value="${stupre.tkTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>阅读写作</span> <span> <input class="ui-input dash"
					value="${stupre.dTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>语法词汇</span> <span> <input class="ui-input dash"
					value="${stupre.yfTearcher}" type="text">
				</span>
			</div>
			<img  style="width: 101%;  alt=""
				src="<%=basePath%>/static/northamerica/examineelist/image/background.jpg">
		</div>	
		<!-- 			page2 -->
		<div class="ui-tab-page yubei">
			<div class="ui-start bottom "
				style="font-weight: 800; font-size: 31px;">随堂测试成绩</div>
			<div style="width: 100%; height: 300px; margin: 1% 2%;" id="report">

			</div>
			<div class="ui-start bottom  report_1"
				style="font-weight: 800; font-size: 31px;">高中单词测试成绩</div>
			<div style="width: 100%; height: 300px; margin: 1% 2%;" id="report_1">

			</div>
			<div class="ui-title ui-stage report_JHPre">阶段测试</div>
			<div style="width: 100%; height: 300px; margin: 1% 5%;" id="report_JHPre">

			</div>
			<img style="height: 350px; width: 100%" alt=""
				src="<%=basePath%>/static/northamerica/examineelist/image/picture1.jpg">
		</div>
		<!-- 		page3 -->
		<div class="ui-tab-page yubei">
			<form style="margin: 5%">
				<fieldset style="border: 1px solid red">
					<legend style="font-size: 48px; color: red !important;background:white!important">结课评语:</legend>
					<div class="ui-left " style="margin-top: 2%">
						词汇语法:
						<div class="ui-font comment-size">${cc.preyfComments }</div>
					</div>
					<div class="ui-left ui-left-top ">
						听力口语:
						<div class="ui-font comment-size">${cc.pretkComments }</div>
					</div>
					<div class="ui-left ui-left-top">
						阅读写作:
						<div class="ui-font comment-size">${cc.predComments }</div>
					</div>
				</fieldset>
			</form>
		</div>
		<!-- 			page4 -->
		<!-- 基础班 -->
		<div class=" ui-tab-page  jichu">
			<div class="ui-start font-s">基础班</div>
			<div class="ui-time  font-s">${cc.basStarttime }&nbsp;至&nbsp;${cc.basEndtime}
			</div>
			<div class="ui-start  ui-souke ui-tearcher">授课老师</div>
			<div class="ui-start ui-tearcher ui-dis">
				<span>听力</span> <span> <input class="ui-input dash"
					value="${stubas.lTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>阅读</span> <span> <input class="ui-input dash"
					value="${stubas.rTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>口语</span> <span> <input class="ui-input dash"
					value="${stubas.sTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>写作</span> <span> <input class="ui-input dash"
					value="${stubas.wTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start bottom report_Js"
				style="font-weight: 800; font-size: 31px;">随堂测试成绩</div>
			<div style="width: 100%; height: 300px; margin: 1% 5%;" id="report_Js">

			</div>
		</div>
		<!-- 		page5	 -->
		<div class="ui-tab-page jichu">

			<div class="ui-start bottom report_Jc title-font">四级单词测试成绩</div>
			<div style="width: 100%; height: 300px; margin: 1% 5%;" id="report_Jc">

			</div>
			<div class="ui-title ui-stage report_JHBas">阶段测试</div>
			<div style="width: 100%; height: 300px; margin: 1% 5%;" id="report_JHBas">

			</div>
			<img alt="" style="width: 100%; margin: 2%; padding: 4%;"
				src="<%=basePath%>/static/northamerica/examineelist/image/base.png">
		</div>
		<!-- 	page6  		-->
		<div class="ui-tab-page jichu" id="div1">
			<form style="margin: 5%">
				<fieldset style="border: 1px solid red">
					<legend style="font-size: 48px; color: red !important;background:white!important">结课评语:</legend>
					<div class="ui-left">
						阅读课:
						<div class="ui-font comment-size">${cc.basrComments }</div>
					</div>
					<div class="ui-left ui-left-top">
						听力课:
						<div class="ui-font comment-size">${cc.baslComments }</div>
					</div>
					<div class="ui-left ui-left-top">
						口语课:
						<div class="ui-font comment-size">${cc.bassComments }</div>
					</div>
					<div class="ui-left ui-left-top">
						写作课:
						<div class="ui-font comment-size">${cc.baswComments }</div>
					</div>
				</fieldset>
			</form>
		</div>
		<!-- 		page7 -->
		<!-- 强化班 -->
		<div class="ui-tab-page qianghua">
			<div class="ui-start font-s">强化班</div>
			<div class="ui-time font-s">${cc.strStarttime }&nbsp;至&nbsp;${cc.strEndtime }</div>
			<div class="ui-start ui-tearcher ui-souke">授课老师</div>
			<div class="ui-start ui-tearcher ui-dis">
				<span>听力</span> <span> <input class="ui-input dash"
					value="${stustr.lTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>阅读</span> <span> <input class="ui-input dash"
					value="${stustr.rTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>口语</span> <span> <input class="ui-input dash"
					value="${stustr.sTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>写作</span> <span> <input class="ui-input dash"
					value="${stustr.wTearcher}" type="text">
				</span>
			</div>
			<div class="ui-start bottom report_Qs title-font">随堂测试成绩</div>
			<div style="width: 100%; height: 300px; margin: 1% 5%;" id="report_Qs">

			</div>
		</div>
		<!-- 		page7 -->
		<div class="ui-tab-page qianghua">
			<div class="ui-start bottom ui-top report_Qc title-font">托福单词测试成绩</div>
			<div style="width: 100%; height: 300px; margin: 1% 5%;" id="report_Qc">

			</div>
			<div class="ui-title ui-stage report_JHStr">阶段测试</div>
			<div style="width: 100%; height: 300px; margin: 1% 5%;" id="report_JHStr">

			</div>
			<img alt="" style="width: 100%; margin: 2%; padding: 4%;"
				src="<%=basePath%>/static/northamerica/examineelist/image/90606557935891041.png">
		</div>
		<!-- 	page8	 -->
		<div class="ui-tab-page qianghua">
			<form style="margin: 5%">
				<fieldset style="border: 1px solid red">
					<legend style="font-size: 48px; color: red !important; background:white!important">结课评语:</legend>
					<div class="ui-left">
						阅读课:
						<div class="ui-font comment-size">${cc.strrComments }</div>
					</div>
					<div class="ui-left ui-left-top">
						听力课:
						<div class="ui-font comment-size">${cc.strlComments }</div>
					</div>
					<div class="ui-left ui-left-top">
						口语课:
						<div class="ui-font comment-size">${cc.strsComments }</div>
					</div>
					<div class="ui-left ui-left-top">
						写作课:
						<div class="ui-font comment-size">${cc.strwComments }</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="NextPage"></div>
		<!-- 冲刺班 -->
		<!-- page9 -->
		<div class="ui-tab-page chongci">
			<div class="ui-start title-font">冲刺班</div>
			<div class="ui-time title-font">${cc.dasStarttime}&nbsp;至&nbsp;${cc.dasEndtime}</div>
			<div class="ui-start ui-tearcher ui-souke">授课老师</div>
			<div class="ui-start ui-tearcher ui-dis">
				<span>听力</span> <span> <input class="ui-input dash"
					value="${studas.lTearcher }" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>阅读</span> <span> <input class="ui-input dash"
					value="${studas.rTearcher }" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>口语</span> <span> <input class="ui-input dash"
					value="${studas.sTearcher }" type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis ui-top-t">
				<span>写作</span> <span> <input class="ui-input dash"
					value="${studas.wTearcher }" type="text">
				</span>
			</div>
			<div class="ui-start bottom report_Cs title-font">随堂测试成绩</div>
			<div style="width: 100%; height: 300px; margin: 1% 5%;" id="report_Cs">
		</div>
		<!-- page10	 -->
		<div class="ui-tab-page chongci">
			<form style="margin: 5%">
				<fieldset style="border: 1px solid red">
					<legend style="font-size: 48px; color: red !important; background:white!important">结课评语:</legend>
					<div class="ui-left">
				阅读课:
				<div class="ui-font comment-size">${cc.dasrComments }</div>
			</div>
			<div class="ui-left ui-left-top">
				听力课:
				<div class="ui-font comment-size">${cc.daslComments }</div>
			</div>
			<div class="ui-left ui-left-top">
				口语课:
				<div class="ui-font comment-size">${cc.dassComments }</div>
			</div>
			<div class="ui-left ui-left-top">
				写作课:
				<div class="ui-font comment-size">${cc.daswComments }</div>
			</div>
				</fieldset>
			</form>
		</div>
		</div>
		<!-- page11	 -->
		<div class="ui-tab-page2 report_JHC stageCeshi">
			<div class="ui-title ui-stage report_JH">阶段测试汇总</div>
			<div style="width: 100%; height: 300px; margin: 1% 5%;" id="report_JH">

			</div>
			<div class="ui-cesi report_MK ">该测试主要考查学生上课讲义内容掌握情况</div>
			<div class="ui-toffl report_MK_0">托福模考记录</div>
			<div style="width: 100%; height: 400px; margin: 1% 5%;" id="report_MK"
				class="report_MK_0"></div>
		</div>
		<div class="ui-tab-page2 page_SK toffleSk">
			<div class="ui-toffl report_SK " style="margin-top: 5%;">托福实考记录</div>
			<div style="width: 100%; height: 400px; margin: 1% 5%;" id="report_SK">

			</div>
			<div style="width: 35%; height: 300px; padding:1%;margin: 3% 5%; float: left"
				id="report_SK_r"></div>
			<div style="width: 35%; height: 300px;  padding:1%;margin: 3% 5%; float: left;"
				id="report_SK_l"></div>
			<div style="width: 35%; height: 300px;  padding:1%;margin: 3% 5%; float: left"
				id="report_SK_S"></div>
			<div style="width: 35%; height: 300px;  padding:1%;margin: 3% 5%; float: left"
				id="report_SK_W"></div>
			<div class="clear"
				style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
		</div>
		<div class="clear"
			style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
			<div class="ui-start bottom toffleGrade"
				style="font-weight: 800; font-size: 31px;">托福成绩汇总</div>
			<div style="width: 100%; height: 300px; margin: 1% 2%;" id="report_MK_Z" class="toffleGrade">	
	</div>
	</div>
	<script src="<%=basePath%>/static/echarts/echarts.3.5.min.js"></script>
	<script
		src="<%=basePath%>/static/northamerica/examineelist/reportIndex.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/pdf/html2canvas.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/pdf/jsPdf.debug.js"></script>
</body>
</html>