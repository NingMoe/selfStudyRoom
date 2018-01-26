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
<%@include file="/WEB-INF/view/common/bootstrapTable.jsp"%></head>
<style type="text/css">
.ui-center {
	text-align: center;
	font-size: 28px;
	border: none;
	margin-top: 10%;
}

.ui-title {
	text-align: center;
	font-size: 44px;
	font-family: monospace;
	border-bottom: 3px solid #ddd;
	margin: 4% 5%;
	padding-bottom: 2%;
}

.ui-input {
	width: 116px;
	height: 0%;
	border-bottom-color: black;
	border-top: none;
	border-left: none;
	border-right: none;
	font-size: 28px;
}

.ui-start {
	text-align: center;
	font-size: 42px;
	font-family: monospace;
	margin-top: 20%;
}

.ui-time {
	text-align: center;
	font-size: 35px;
	font-family: monospace;
}

.ui-tearcher {
	font-family: -webkit-body
}

.ui-dis {
	margin-top: 0;
	font-family: cursive;
}

.dash {
	border-bottom: dashed;
}

.bottom {
	margin-bottom: 2%;
}

.ui-top {
	margin-top: 0px
}

.ui-left {
	margin-left: 5%;
	font-size: 26px;
}

.ui-left-top {
	margin-top: 2%;
}

.ui-stage {
	border: none;
	text-align: left;
	margin-top: 14%;
}

.ui-cesi {
	margin: 8% 5%;
	font-size: 27px;
}

.ui-toffl {
	margin-left: 5%;
	font-size: 41px;
}

.ui-top {
	margin-top: 10%
}

.ui-tab-page {
/* 	height: 1250px */
}

.ui-tab-page2{
/* height:1350px */
} 
.ui-font {
	font-size: 16px;
	text-indent: 2em
}

.ui-top-lar {
	margin-top: 25%;
}
 .NextPage{
 
 page-break-after: always;
 }
 body{
 width:100%;}
</style>
<style media="print">
    @page {
      size: auto;  /* auto is the initial value */
      margin: 0mm; /* this affects the margin in the printer settings */
    }
    @media print { 
 .noprint { display: none } 
}
</style>

<body>
	<div class="main-wrap">
		<div class="ui-tab-page" >
			<div class="ui-title">合肥新东方之旅</div>
			<div class="ui-center">
				<span class="ui-font">学生姓名</span> <span> <input
					class="ui-input" type="text" value="${cc.name }">
				</span> <span class="ui-font">学员号</span> <span class="ui-bottom"></span> <span>
					<input class="ui-input" type="text" value="${cc.code }">
				</span>
			</div>
			<div class="ui-start">预备班</div>
			<div class="ui-time">${cc.preStarttime }&nbsp;至
				&nbsp;${cc.preEndtime }</div>
			<div class="ui-start ui-tearcher">授课老师</div>
			<div class="ui-start ui-tearcher ui-dis">
				<span>听力口语</span> <span> <input class="ui-input dash" value="${stupre.tkTearcher}"
					type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis">
				<span>阅读写作</span> <span> <input class="ui-input dash" value="${stupre.dTearcher}"
					type="text">
				</span>
			</div>
			<div class="ui-start ui-tearcher ui-dis">
				<span>语法词汇</span> <span> <input class="ui-input dash" value="${stupre.yfTearcher}"
					type="text">
				</span>
			</div>
		</div>
		<div class="NextPage"></div>
		<!-- 			page2 -->
		<div class="ui-tab-page">
			<div class="ui-start bottom ">随堂测试成绩</div>
			<div style="width: 50%; height: 300px; margin: 1% 5%;" id="report">

			</div>
			<div class="ui-start bottom report_1">高中单词测试成绩</div>
			<div style="width: 70%; height: 300px; margin: 1% 5%;" id="report_1">

			</div>
		</div>
		<div class="NextPage"></div>
		<!-- 		page3 -->
		<div class="ui-tab-page">
			<div class="ui-start bottom ui-top">结课评语</div>
			<div class="ui-left">
				<%-- 			${cc.preyfComments } --%>
				语法词汇课:
				<div class="ui-font">${cc.preyfComments }</div>
			</div>
			<div class="ui-left ui-left-top">
				听力写作课:
				<div class="ui-font">${cc.predComments }</div>
			</div>
			<div class="ui-left ui-left-top">
				阅读口语课:
				<div class="ui-font">${cc.pretkComments }
					床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
					床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡</div>
			</div>
		</div>
	</div>
	<div class="NextPage"></div>
	<!-- 			page4 -->
	<!-- 基础班 -->
	<div class=" ui-tab-page ">
		<div class="ui-start ui-top ui-top-lar">基础班</div>
		<div class="ui-time">${cc.basStarttime }&nbsp;至&nbsp;${cc.basEndtime}
		</div>
		<div class="ui-start ui-tearcher ui-top ui-top-lar">授课老师</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>listening</span> <span> <input class="ui-input dash" value="${stubas.lTearcher}"
				type="text">
			</span>
		</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>Reading</span> <span> <input class="ui-input dash" value="${stubas.rTearcher}"
				type="text">
			</span>
		</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>speaking</span> <span> <input class="ui-input dash" value="${stubas.sTearcher}"
				type="text">
			</span>
		</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>Writing</span> <span> <input class="ui-input dash" value="${stubas.wTearcher}"
				type="text">
			</span>
		</div>
	</div>
	<div class="NextPage"></div>
	<!-- 		page5	 -->
	<div class="ui-tab-page">
		<div class="ui-start bottom report_Js">随堂测试成绩</div>
		<div style="width: 50%; height: 300px; margin: 1% 5%;" id="report_Js">

		</div>

		<div class="ui-start bottom report_Jc">高中单词测试成绩</div>
		<div style="width: 70%; height: 300px; margin: 1% 5%;" id="report_Jc">

		</div>
	</div>
	<div class="NextPage"></div>
	<!-- 	page6  		-->
	<div class="ui-tab-page " id="div1">
		<div class="ui-start bottom ui-top">结课评语</div>
		<div class="ui-left">
			阅读课:
			<div class="ui-font">床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡</div>
		</div>
		<div class="ui-left ui-left-top">
			听力课:
			<div class="ui-font">床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡</div>
		</div>
		<div class="ui-left ui-left-top">
			口语课:
			<div class="ui-font">床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡</div>
		</div>
		<div class="ui-left ui-left-top">
			写作课:
			<div class="ui-font">床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
				床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡床前明月光疑似地上霜举头望明月低头思故乡
			</div>
		</div>
	</div>
	<div class="NextPage"></div>
	<!-- 		page7 -->
	<!-- 强化班 -->
	<div class="ui-tab-page">
		<div class="ui-start">强化班</div>
		<div class="ui-time">${cc.strStarttime }&nbsp;至&nbsp;${cc.strEndtime }</div>
		<div class="ui-start ui-tearcher">授课老师</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>listening</span> <span> <input class="ui-input dash" value="${stustr.lTearcher}"
				type="text">
			</span>
		</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>Reading</span> <span> <input class="ui-input dash" value="${stustr.rTearcher}"
				type="text">
			</span>
		</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>speaking</span> <span> <input class="ui-input dash" value="${stustr.sTearcher}"
				type="text">
			</span>
		</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>Writing</span> <span> <input class="ui-input dash" value="${stustr.wTearcher}"
				type="text">
			</span>
		</div>
	</div>
	<div class="NextPage"></div>
	<!-- 		page7 -->
	<div class="ui-tab-page">
		<div class="ui-start bottom report_Qs">随堂测试成绩</div>
		<div style="width: 50%; height: 300px; margin: 1% 5%;" id="report_Qs">

		</div>
		<div class="ui-start bottom ui-top report_Qc">高中单词测试成绩</div>
		<div style="width: 70%; height: 300px; margin: 1% 5%;" id="report_Qc">

		</div>
	</div>
	<div class="NextPage"></div>
	<!-- 	page8	 -->
	<div class="ui-tab-page">
		<div class="ui-start bottom ui-top">结课评语</div>
		<div class="ui-left">
			阅读课:
			<div class="ui-font">${cc.strrComments }</div>
		</div>
		<div class="ui-left ui-left-top">
			听力课:
			<div class="ui-font">${cc.strlComments }</div>
		</div>
		<div class="ui-left ui-left-top">
			口语课:
			<div class="ui-font">${cc.strsComments }</div>
		</div>
		<div class="ui-left ui-left-top">
			写作课:
			<div class="ui-font">${cc.strwComments }</div>
		</div>
	</div>
	<div class="NextPage"></div>
	<!-- 冲刺班 -->
	<!-- 			page9 -->
	<div class="ui-tab-page">
		<div class="ui-start " style="margin-top:10%">冲刺班</div>
		<div class="ui-time">${cc.dasStarttime}&nbsp;至&nbsp;${cc.dasEndtime}</div>
		<div class="ui-start ui-tearcher">授课老师</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>listening</span> <span> <input class="ui-input dash" value="${studas.lTearcher }"
				type="text">
			</span>
		</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>Reading</span> <span> <input class="ui-input dash" value="${studas.rTearcher }"
				type="text">
			</span>
		</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>speaking</span> <span> <input class="ui-input dash" value="${studas.sTearcher }"
				type="text">
			</span>
		</div>
		<div class="ui-start ui-tearcher ui-dis">
			<span>Writing</span> <span> <input class="ui-input dash" value="${studas.wTearcher }"
				type="text">
			</span>
		</div>
		<div class="ui-start bottom report_Cs">随堂测试成绩</div>
		<div style="width: 50%; height: 300px; margin: 1% 5%;" id="report_Cs">

		</div>
</div>	
<div class="NextPage"></div>
<!-- page10	 -->
<div class="ui-tab-page">
		<div class="ui-start bottom ui-top">结课评语</div>
		<div class="ui-left">阅读课:<div class="ui-font">${cc.dasrComments }</div></div>
		<div class="ui-left ui-left-top">听力课:<div class="ui-font">${cc.daslComments }</div></div>
		<div class="ui-left ui-left-top">口语课:<div class="ui-font">${cc.dassComments }</div></div>
		<div class="ui-left ui-left-top">写作课:<div class="ui-font">${cc.daswComments }</div></div>
</div>	
<div class="NextPage"></div>
<!-- page11	 -->
	<div class="ui-tab-page2">
		<div class="ui-title ui-stage report_JH">阶段测试汇总</div>
		<div style="width: 70%; height: 300px; margin: 1% 5%;" id="report_JH">

		</div>
		<div class="ui-cesi report_MK">该测试主要考查学生上课讲义内容掌握情况</div>
		<div class="ui-toffl">基础阶段托福模考记录</div>
		<div style="width: 70%; height: 400px; margin: 1% 5%;" id="report_MK">

		</div>
	</div>
	<div class="NextPage"></div>
<!-- 	page12 -->
	<div class="ui-tab-page2">
		<div class="ui-toffl report_MK_1">强化阶段托福模考记录</div>
		<div style="width: 70%; height: 400px; margin: 1% 5%;"
			id="report_MK_1"></div>
		<div class="ui-toffl report_MK_2">冲刺阶段托福模考记录</div>
		<div style="width: 70%; height: 400px; margin: 1% 5%;"
			id="report_MK_2"></div>
	</div>
	<div class="NextPage"></div>
	<div class="ui-tab-page2" style="height:1300px">
		<div class="ui-toffl report_SK">基础阶段托福实考记录</div>
		<div style="width: 70%; height: 400px; margin: 1% 5%;" id="report_SK">

		</div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left"
			id="report_SK_r"></div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left;"
			id="report_SK_l"></div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left"
			id="report_SK_S"></div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left"
			id="report_SK_W"></div>
		<div class="clear"
			style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
		<div class="ui-toffl report_SK">以上为基础阶段实考单项记录</div>
	</div>
	<div class="NextPage"></div>
	<div class="ui-tab-page2">
		<div class="ui-toffl report_SKQ">强化阶段托福实考记录</div>
		<div style="width: 70%; height: 400px; margin: 1% 5%;" id="report_SKQ">

		</div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left"
			id="report_SKQ_r"></div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left;"
			id="report_SKQ_l"></div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left"
			id="report_SKQ_S"></div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left"
			id="report_SKQ_W"></div>
		<div class="clear"
			style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
		<div class="ui-toffl report_SKQ">以上为强化阶段实考单项记录</div>
	</div>
	<div class="NextPage"></div>
	<div class="ui-tab-page2">
		<div class="ui-toffl report_SKC">冲刺阶段托福实考记录</div>
		<div style="width: 70%; height: 400px; margin: 1% 5%;" id="report_SKC">

		</div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left"
			id="report_SKC_r"></div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left;"
			id="report_SKC_l"></div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left"
			id="report_SKC_S"></div>
		<div style="width: 35%; height: 300px; margin: 3% 5%; float: left"
			id="report_SKC_W"></div>
		<div class="clear"
			style="clear: both; height: 0; line-height: 0; font-size: 0"></div>
		<div class="ui-toffl report_SKC" style="float:left ;">以上为冲刺阶段实考单项记录</div>
		<div style="float:left;    margin-left: 22%;margin-top: 1%;"></div> <button onClick="add()" type="button" class="layui-btn">打印或保存
		</button>
	</div>
	</div>
	</div>
	<script src="<%=basePath%>/static/echarts/echarts.3.5.min.js"></script>
	<script src="<%=basePath%>/static/northamerica/examineelist/reportIndex.js"></script>
		<script type="text/javascript" src="<%=basePath%>/static/pdf/html2canvas.js"></script>
   		 <script type="text/javascript" src="<%=basePath%>/static/pdf/jsPdf.debug.js"></script>
</body>
</html>