<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>新东方优惠券</title>
<link rel="stylesheet" href="http://sw.hf.xdf.cn/static/layui/css/layui.css">
<script src="http://sw.hf.xdf.cn/static/jquery/jquery-1.11.1.min.js"></script>
<script src="http://sw.hf.xdf.cn/static/layer/layer.js"></script>
<style>
.mycourses_top{height:1.5rem; padding:0.5rem 0.5rem; background-color:#53cac3; position:relative;}
.mycourses_top article span{ font-size:0.36rem;  color:#FFF;}
.top_right_button{ width:1.2rem; height:1rem; position:absolute; top:33%; left:75%;}
.bding{ width:1.5rem; height:1rem; line-height:1rem; border:1px solid #FFF; border-radius:0.2rem; background-color:#53cac3; font-size:0.35rem; color:#FFF;}
.top{
		width: 100%;
		float: left;
	}
	.a1{
		background-color: white;
	}
	.a2,.a3{
    	background-color: #dbdbdb;
	}
	.top a{
		    width: 50%;
    		float: left;
    		font-size: 0.3rem;
    		text-align: center;
    		height: 0.7rem;
    		line-height: 0.7rem;
    		text-decoration: none;
    		color: black;
	}
	.list{
		float: left;
		width: 94%;
		margin:10px 3%;
		background-color: white;
		border: 1px solid rgba(128, 128, 128, 0.15);
	}
	.roof{
		width: 100%;
	}
	.mark{
		position: relative;
	    width: 50px;
	    right: 0;
	    top: -14px;
	    z-index: 2;
	    float: right;
	}
	#cut{
		float: left;
    	font-size: 0.35rem;
    	margin: 0.1rem 0.2rem;
    	color: #31a894;
    	font-weight: lighter;
	}
	#money{
		font-size: 1rem;
    	margin: 0;
    	line-height: 0.9rem;
    	float: left;
    	color: #31a894;
	}
	.list > span {
	    width: 100%;
	    float: left;
	   /*  height: 1rem; */
	}
	#money_icon{
	    margin: 0.2rem 0.1rem 0 1rem;
	    font-size: 0.6rem;
	    float: left;
	    vertical-align: bottom;
	    color: #31a894;
	}
	#sp1,#sp2{
		width: 50%;
		height: 1.2rem;
	}
	#sp2 p{
		font-size: 0.32rem;
		/* float: right; */
		line-height: 0.45rem;
	}
	#sp2 a{
		float: right;
	    width: 0.4rem;
	    font-size: 12px;
	    margin: 0 0.2rem;
	    background-color: #31a894;
	    line-height: 0.45rem;
	    text-align: center;
	    border-radius: 0.2rem;
	    color: white;
	}
	#sp3{
		border-top: rgba(128, 128, 128, 0.28) dashed 1px;
		line-height: 0.8rem;
	}
	#sp3 p{
		font-size: 0.3rem;
		line-height: 0.8rem;
		color: grey;
		text-align: center;
	}
	p{
		margin:0;
		padding: 0;
	}
	.yiguoqi,.yishiyong{
		display: none;
	}
	.yiguoqi p ,.yishiyong p{
		color: grey!important;
	}
	.yiguoqi a ,.yishiyong a{
		background-color: grey!important;
		color: white!important;
	}
	#p_guoqi,#p_shiyong{
		width: 3rem;
    	top: -1.5rem;
    	position: relative;
    	right: -0.5rem;
	}
	.banner{
		width: 100%;
    	height: 1rem;
    	font-size: 0.4rem;
    	background-color: #19decc;
    	color: white;
	}
	.banner p{
		float: left;
    	line-height: 1rem;
    	margin-left: 0.1rem;
	}
	.banner a{
		float: right;
		display: block;
		background-color: #19decc;
		border: 1px solid white;
		border-radius: 10px;
		text-decoration: none;
		color: white;
		width: 1.5rem;
		text-align: center;
		line-height: 0.4rem;
		margin:0.19rem;
	}
	.copy-tips{position:fixed;z-index:999;bottom:50%;left:50%;margin:0 0 -20px -80px;background-color:rgba(0, 0, 0, 0.2);filter:progid:DXImageTransform.Microsoft.Gradient(startColorstr=#30000000, endColorstr=#30000000);padding:6px;}
	.copy-tips-wrap{padding:10px 20px;text-align:center;border:1px solid #F4D9A6;background-color:#FFFDEE;font-size:14px;}
</style>
</head>
<body>
<!-- 通用绑定头部开始 -->
<div class="mycourses_top">
    <article><span>姓名：<span id ="student_name">${studentRes.studentname }</span></span></article>
    <article><span>手机号：<span id="student_moblie">${studentRes.studentmoblie }</span></span></article>
    <article><span>学员号：<span id="student_code">${studentRes.studentcode }</span></span></article>
    <article class="top_right_button"><input type="button" style="-webkit-appearance: none;" id="unbind" class="bding" value="解绑"></article>
</div>
<!-- 通用绑定头部结束 -->
<input id="openid" type="hidden" value="${param.openid }">
<input id="schoolid" type="hidden" value="${param.schoolid }">
	<div class="top">
		<a class="a1">未使用</a>
		<a class="a2">已使用</a>
	</div>
	
	
	<div class="weishiyong">
		<c:if test="${empty wsyList }">
	    	<div style="margin-top:60px;margin-left:30px;font-size:12px;">暂无未使用优惠券</div>
	    </c:if>
	    <c:if test="${!empty wsyList }">
		<c:forEach items="${wsyList }" var="coupon">  
			<div class="list" id="l1">
				<span>
					<img src="http://sw.hf.xdf.cn/static/coupon/img/1.png" class="roof">
					<img <c:if test="${coupon.couponType eq '1'}">src="http://sw.hf.xdf.cn/static/coupon/img/2.png"</c:if>
						 <c:if test="${coupon.couponType eq '2'}">src="http://sw.hf.xdf.cn/static/coupon/img/3.png"</c:if>
					  class="mark">
					<p id="cut">直减</p>
				</span>
				<span id="sp1">
					<p id="money_icon">¥</p><p id="money">${coupon.voucherValue }</p>
				</span>
				<span id="sp2">
				<div style="display: none;">
					<input type="text" id="${coupon.couponCode }" value="${coupon.couponCode }">
				</div>
					<p>优惠券编号</p>
					<p >${coupon.couponCode }</p>
				</span>
				<span id="sp3"> 
					<p>有效日期：${fn:substring(coupon.startDate, 0, 10)}&nbsp;&nbsp;至&nbsp;&nbsp;${fn:substring(coupon.endDate, 0, 10)}</p>
					<p>备注：${coupon.couponName }</p>
				</span>
			</div>
		</c:forEach>
		</c:if>
	</div>
	
	
	<div class="yishiyong">
		<c:if test="${empty ysyList }">
	    	<div style="margin-top:60px;margin-left:30px;font-size:12px;">暂无已使用优惠券</div>
	    </c:if>
	    <c:if test="${!empty ysyList }">
		<c:forEach items="${ysyList }" var="coupon">
		<div class="list" id="l1">
			<span>
				<img src="http://sw.hf.xdf.cn/static/coupon/img/4.png" class="roof">
				<img <c:if test="${coupon.couponType eq '1'}">src="http://sw.hf.xdf.cn/static/coupon/img/7.png"</c:if>
					 <c:if test="${coupon.couponType eq '2'}">src="http://sw.hf.xdf.cn/static/coupon/img/8.png"</c:if>
			 	  class="mark">
				<p id="cut">直减</p>
			</span>
			<span id="sp1">
				<p id="money_icon"></p><p id="money">${coupon.voucherValue }</p>
			</span>
			<span id="sp2">
				<p>优惠券编号</p>
				<p>${coupon.couponCode }</p>
				<img id="p_shiyong" src="http://sw.hf.xdf.cn/static/coupon/img/5.png">
			</span>
			<span id="sp3">
				<p>使用班号：${coupon.useClassCode }(${coupon.useClassName })</p>
				<p>备注：${coupon.couponName }</p>
			</span>
		</div>
		</c:forEach>
		</c:if>
	</div>
	 <script src="http://sw.hf.xdf.cn/static/coupon/clipboard.js"></script>
	<script type="text/javascript">
	var deviceWidth = document.documentElement.clientWidth;
	if(deviceWidth >750) deviceWidth =750;
	document.documentElement.style.fontSize = deviceWidth /7.5+'px';
	
	if('${isBind}' =='0'){
		var schoolid = $("#schoolid").val();
		location.href = "http://wxpay.xdf.cn/silenceauthorize/view.do?schoolid="+schoolid+"&callid=4&parm=6";
	}
	
	
	$(".a1").click(function(){
		$(".weishiyong").css({
			display: 'block'
		});
		$(".yishiyong").css({
			display: 'none'
		});
		$(".a1").css("background-color","white");
		$(".a2").css("background-color"," #dbdbdb");
	});
	$(".a2").click(function(){
		$(".yishiyong").css({
			display: 'block'
		});
		$(".weishiyong").css({
			display: 'none'
		});
		$(".a2").css("background-color","white");
		$(".a1").css("background-color"," #dbdbdb");
	});
	
	$("#unbind").bind("click",function(){
		if(confirm('确定要解除微信绑定吗？')){
			var openid = $("#openid").val();
			var schoolid = $("#schoolid").val();
 			$.ajax({
				url : "http://wxpay.xdf.cn/wechat/hf/free/coupon/unbind.html",
				data : {openid:openid,schoolid:schoolid},
				dataType : "json",
				type : "post",
				async:false,
				success : function(res) {
					if (!res.flag) {
						alert(res.message);
					} else {
						alert("解绑成功");
						location.href = "http://wxpay.xdf.cn/silenceauthorize/view.do?schoolid="+schoolid+"&callid=4&parm=6";
					}
				},
				error:function(t1,t2,t3){
					console.log(11);
				}
			});
 	     }
	});
	</script>
</body>
</html>