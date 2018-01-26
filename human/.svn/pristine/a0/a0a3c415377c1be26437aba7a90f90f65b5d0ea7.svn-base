<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head style="font-size: 100px;">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;width=device-width; initial-scale=1.0;">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<title>新东方华仑校区</title>
<meta charset="UTF-8">
<meta name="keywords" content="新东方合肥学校">
<%@include file="/WEB-INF/view/activity/frontWeixin/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/activity/frontWeixin/css/style2.css">
</head>
<body >
<input type="hidden"  id="appid" name="appid" value="${appid}"/>
<input type="hidden"  id="redirect_uri" name="redirect_uri" value="${redirect_uri}"/>
<input type="hidden"  id="orderInfoId" name="orderInfoId" value="${orderInfo.id}"/>
<input type="hidden"  id="flagId" name="flagId" value="${flag}"/>
<input type="hidden"  id="indexId" name="indexId" value="${index}"/>
<input type="hidden"  id="messageId" name="messageId" value="${message}"/>
<input type="hidden"  id="activityId" name="activityId" value="${activityId}"/>
<div class="banner">
</div>
<div class="mycard">
	<img class="loc" src="<%=basePath%>/static/activity/frontWeixin/img/loc.png">
	<p class="adr">经开区始信路183号小168学校旁</p>
	<button class="card_button"><img src="<%=basePath%>/static/activity/frontWeixin/img/mycard.png"><p>我的卡券</p></button>
</div>
<div class="quan">
	<p class="big big1">200</p><p class="small">抵</p><p class="big">500</p><p class="small">抵用券</p>
</div>
<div class="form">
   <c:if test="${flag eq true }">
	 <ul>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/type.png"><p class="type_p">订单名称：</p>
		     <span>${orderInfo.orderName }</span>
		</li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/price.png"><p>支付金额：</p><span>￥${orderInfo.orderCost }元</span></li>
		<li class="buy_li"><button class="buy" onclick="return weixinPay();">微信支付</button></li>
	 </ul>
   </c:if>
   <c:if test="${flag eq false }">
     <span>${message }</span>
   </c:if>
</div>
<div class="gonglue">
	<img src="<%=basePath%>/static/activity/frontWeixin/img/gonglue2.png">
</div>
<div class="shuoming">
	<p class="shuoming_title">使用说明</p>
	<p class="shuoming_content">即日起至1月20日，预交200元定金，在1.20-1.21两天可抵500元使用。</br>
① 适用范围：在华仑广场校区前台报名，且所报课程为优能高中部、优能一对一部设置在华仑广场校区的寒春班级；</br>
② 每人仅限使用一张定金劵；</br>
③ 定金劵仅限1.20-1.21两天使用，逾期不能使用；</br>
④ 若所交定金需要退款，在1.20-1.21日至华仑广场校区前台办理，逾期不再受理。</p>
</div>
<div class="xiaoqu">
	<div class="kecheng">
		<p class="kecheng_h1">开设课程</p>
		<p class="kecheng_p">新东方合肥学校华仑广场校区开业之际，针对优能中学教育推出系列优惠课程，1.20-1.21日所有课程均可享受优惠卷抵免（仅此两天），1月20日，新校区不见不散！</p>
		<p class="kecheng_p2">关于新东方</p>
		<p class="kecheng_p3">新东方教育科技集团</p>
		<p class="kecheng_p">新东方教育科技集团，由1993年11月16日成立的北京新东方学校发展壮大而来，目前集团以语言培训为核心，拥有短期培训系统、基础教育系统、文化传播系统、科技产业系统、咨询服务系统等多个发展平台，是一家集教育培训、教育产品研发、教育服务等于一体的大型综合性教育科技集团。新东方教育科技集团于2006年9月7日在美国纽约证券交易所成功上市，成为中国大陆首家海外上市的教育培训机构。</p>
		<p class="kecheng_p3">新东方合肥学校</p>
		<p class="kecheng_p">新东方合肥学校成立于2006年6月，是新东方教育科技集团旗下第28家直营分校。目前在合肥市拥有28家学习中心，开设有泡泡少儿（语文/数学/英语）、优能中学（全科）出国考试（托福/雅思/GRE/SAT/ACT）、大学考试（考研/四六级/口语）等培训课程及国际冬夏令营等业务。截止2016年5月，学校已累计培训学员近100万人次。</p>
	</div>
</div>
</body>
<script type="text/javascript">
	var  deviceWidth = document.documentElement.clientWidth;
	if(deviceWidth >750) deviceWidth =750;
	document.documentElement.style.fontSize = deviceWidth /7.5+'px';
</script>
<script type="text/javascript" src="<%=basePath%>/static/activity/frontWeixin/js/orderInfo.js"></script>
</html>