<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head style="font-size: 100px;">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;width=device-width; initial-scale=1.0;">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<title>合肥新东方H5支付</title>
<meta charset="UTF-8">
<meta name="keywords" content="新东方合肥学校">
<%@include file="/WEB-INF/view/activity/frontWeixin/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/activity/frontWeixin/css/style.css">
</head>
<body >
 <input type="hidden" name="activityId" id="activityId" value="${info.id }">
 <input type="hidden" name="privce" id="privce" value="${price}">
<div class="banner">
</div>
<div class="mycard">
	<img class="loc" src="<%=basePath%>/static/activity/frontWeixin/img/loc.png">
	<p class="adr">杭州路3333号保利拉菲公馆</p>
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
		       <option value="一年级">一年级</option>
		       <option value="二年级">二年级</option>
		       <option value="三年级">三年级</option>
		       <option value="四年级">四年级</option>
		       <option value="五年级">五年级</option>
		       <option value="六年级">六年级</option>
		       <option value="初一">初一</option>
		       <option value="初二">初二</option>
		       <option value="中考">中考</option>
	  		</select>
  		</li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/num.png"><p>购买数量：</p><button class="add"></button><input  value="1"  name="buySum" id="buySum" class="num_input" readonly="readonly"><button class="sub"></button></li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/price.png"><p>支付金额：</p><input class="prc_input"  name="" value="1" readonly="readonly"></li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/phone.png"><p>手机号码：</p><input  class="tel_input" name="telephone" id="telephone" onblur="checkPhone();"><button id="get_msg_btn" class="tel_button" onclick="return validate();">获取验证码</button></li>
		<li><img src="<%=basePath%>/static/activity/frontWeixin/img/code.png"><p>验证码：</p><input  id="msg" name="code" class="yan_input"></li>
		<li class="buy_li"><button class="buy" onclick="return confirmBuy();">立即抢券</button></li>
	</ul>
</div>
<div class="gonglue">
	<img src="<%=basePath%>/static/activity/frontWeixin/img/gonglue.png">
</div>
<div class="shuoming">
	<p class="shuoming_title">使用说明</p>
	<p class="shuoming_content">即日起至12月24日零点，预交200元定金抢500元抵用券，在开业当天可抵500元使用。</br>
① 适用范围：在滨湖保利校区前台报名，且所报课程为泡泡少儿部、优能初中部、优能一对一部设置在滨湖保利校区的寒春班级；</br>
② 每个班级仅限使用一张抵用劵；</br>
③ 定金劵仅限开业当天使用，逾期不能使用；</br>
④ 若所交定金需要退款，在开业当天至滨湖保利校区前台办理，逾期不再受理。</p>
</div>
<div class="xiaoqu">
	<div class="kecheng">
		<p class="kecheng_h1">开设课程</p>
		<p class="kecheng_p">新东方合肥学校滨湖保利校区开业之际，特针对泡泡少儿教育，优能中学教育推出系列优惠课程，开业当天所有课程均可享受优惠券抵免（仅此一天），12月24日，新校区不见不散！</p>
		<p class="kecheng_p2">关于新东方</p>
		<p class="kecheng_p3">新东方教育科技集团</p>
		<p class="kecheng_p">新东方教育科技集团，由1993年11月16日成立的北京新东方学校发展壮大而来，目前集团以语言培训为核心，拥有短期培训系统、基础教育系统、文化传播系统、科技产业系统、咨询服务系统等多个发展平台，是一家集教育培训、教育产品研发、教育服务等于一体的大型综合性教育科技集团。新东方教育科技集团于2006年9月7日在美国纽约证券交易所成功上市，成为中国大陆首家海外上市的教育培训机构。</p>
		<p class="kecheng_p3">新东方合肥学校</p>
		<p class="kecheng_p">新东方合肥学校成立于2006年6月，是新东方教育科技集团旗下第28家直营分校。目前在合肥市拥有28家学习中心，开设有泡泡少儿（语文/数学/英语）、优能中学（全科）出国考试（托福/雅思/GRE/SAT/ACT）、大学考试（考研/四六级/口语）等培训课程及国际冬夏令营等业务。截止2016年5月，学校已累计培训学员55万人次。</p>
	</div>
</div>
</body>
<script type="text/javascript">
	var  deviceWidth = document.documentElement.clientWidth;
	if(deviceWidth >750) deviceWidth =750;
	document.documentElement.style.fontSize = deviceWidth /7.5+'px';
</script>	
<script type="text/javascript" src="<%=basePath%>/static/activity/frontWeixin/js/index3.js"></script>
</html>