<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head style="font-size: 100px;">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;width=device-width; initial-scale=1.0;">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<title>新东方经开区华仑校区1月20日盛大开业，预售课程优惠券充200抵500！</title>
<meta charset="UTF-8">
<meta name="keywords" content="新东方合肥学校">
<%@include file="/WEB-INF/view/activity/frontWeixin/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/activity/frontWeixin/css/style2.css">
</head>
<body >
 <input type="hidden" name="activityId" id="activityId" value="${info.id }">
 <input type="hidden" name="privce" id="privce" value="${price}">
<div class="banner">
</div>
<div class="mycard">
	<img class="loc" src="<%=basePath%>/static/activity/frontWeixin/img/loc.png">
	<p class="adr">经开区始信路183号小168学校旁</p>
	<button class="card_button" onclick="myCard();"><img src="<%=basePath%>/static/activity/frontWeixin/img/mycard.png"><p>我的卡券</p></button>
</div>
<div class="quan">
	<p class="big big1">200</p><p class="small">抵</p><p class="big">500</p><p class="small">抵用券</p>
</div>
<div class="form">
	<ul>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/name.png"><p>姓名：</p></p><input  name="姓名" id="name" class="name_input"></li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/type.png"><p class="type_p">类别：</p>
		  <c:forEach var="product" items="${prodList }" varStatus="status">		    
		     <input type="radio" name="productId" id="checkbox_b${status.count }"  value="${product.id }" /><label for="checkbox_b${status.count }">${product.name }</label>
		  </c:forEach>
		</li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/type.png"><p class="type_g">年级：</p >
			<select class="grade_option" id="gradeId">
			   <option value="">请选择</option>
		       <option value="高一">高一</option>
		       <option value="高二">高二</option>
		       <option value="高三">高三</option>
	  		</select>
  		</li>
  		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/type.png"><p class="type_g">预约科目：</p >
			 <input type="checkbox"  value="数学" /><label>数学</label>
			 <input type="checkbox"  value="英语" /><label>英语</label>
			 <input type="checkbox"  value="物理" /><label>物理</label>
			 <input type="checkbox"  value="化学" /><label>化学</label>
  		</li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/num.png"><p>购买数量：</p><input  type="hidden" value="1"  name="buySum" id="buySum" class="num_input" readonly="readonly"><p>每人限购1张</p></li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/price.png"><p>支付金额：</p><input class="prc_input"  name="" value="200" readonly="readonly"></li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/phone.png"><p>手机号码：</p><input  class="tel_input" name="telephone" id="telephone" onblur="checkPhone();"><button id="get_msg_btn" class="tel_button" onclick="return validate();">获取验证码</button></li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/code.png"><p>验证码：</p><input  id="msg" name="code" class="yan_input"></li>
		<li class="buy_li"><button class="buy" onclick="return confirmBuy();">立即抢券</button></li>
	</ul>
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
<script type="text/javascript" src="<%=basePath%>/static/activity/frontWeixin/js/index2.js"></script>
</html>