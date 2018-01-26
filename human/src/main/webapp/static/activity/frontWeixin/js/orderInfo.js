var appId;				
var nonceStr;
var packageStr;
var paySign;
var timeStamp;
$(function() {
	confirm();
});
function confirm(){
	var index=$("#indexId").val();
	if(index=="1"){
		confirmOrder();
	}else if(index=="2"){
		getWxPayParam();
	}		
}
//提示订单语句
function confirmOrder(){
	var flag=$("#flagId").val();
	if(flag){
		alert("支付订单已经生成,请点击微信支付按钮发起支付");
	}else{
		var message=$("#messageId").val();
		alert(message);
		location.href = jsBasePath+"/activity/weixinPay/index.html?activityId="+$("#activityId").val();	
	}			
}
//微信支付
function weixinPay(){
	var index=$("#indexId").val();
	if(index=="1"){
		//第一步：判断活动有效期是否已经截止
		var activityId=$("#activityId").val();
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : jsBasePath+"/activity/weixinPay/getTime.html",
			data : {
				activityId : activityId
			},
			success : function(data){						
				if(data.flag){
					oauth();
				}else{
				   alert(data.message);
				}
			},
			error : function(data){
				alert("网络错误,请联系管理员!");
			}
		});					
	}else if(index=="2"){
		getWxPayParam();
	}	
 }

//第二步：引导用户授权
function oauth(){
	var ua = window.navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){		
		var appid=$("#appid").val();
		var redirect_uri=$("#redirect_uri").val();
		window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=123456789#wechat_redirect";			
	}else{ 
		alert("请在微信浏览器中打开!");		 
	}	    	
}
 
//第三步：获取微信支付参数信息
function getWxPayParam(){
	var flag=$("#flagId").val();
	if(flag){
		var orderInfoId=$("#orderInfoId").val();
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : jsBasePath+"/activity/orderInfo/getWxPayParamById.html",
			data : {
				orderInfoId : orderInfoId
			},
			success : function(data){
				if(data.flag){
					appId=data.appId;
					nonceStr=data.nonceStr;
					packageStr=data.packageStr;
					paySign=data.paySign;
					timeStamp=data.timeStamp;
					pay();
				}else{
					alert(data.message);
				}
			},
			error : function(data){
				alert(data);
			}
		});	
	}else{
		var message=$("#messageId").val();
		alert(message);
	}	
}
//第四步：调用微信接口发起H5支付
function pay(){
	if(typeof WeixinJSBridge == "undefined"){
	    if( document.addEventListener ){
	        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	    }else if (document.attachEvent){
	        document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	    }
	 }else{
		 onBridgeReady();
	} 	
}
//H5支付
function onBridgeReady(){
	 WeixinJSBridge.invoke(
		        'getBrandWCPayRequest', {
		            "appId": appId,     //公众号名称，由商户传入     
		            "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数     
		            "nonceStr": nonceStr, //随机串     
		            "package": packageStr ,     
		            "signType" :   "MD5",         //微信签名方式：     
		            "paySign" : paySign     //微信签名 
		        },
		        function(res){
		            if(res.err_msg == "get_brand_wcpay_request:ok" ){
		            	setTimeout(queryOrderInfo,1000);
		            }else if(res.err_msg == "get_brand_wcpay_request:fail"){
		            	payFaild();
		            }else{
		            	payCancle();
		            }    
		        }
		 );	
}

//支付成功
function queryOrderInfo(){
	var orderInfoId=$("#orderInfoId").val();
	$.ajax({
		type:"POST",
		url:jsBasePath+"/activity/orderInfo/queryOrderInfo.html?orderInfoId="+orderInfoId,
		async: false,
		dataType:"json",
		success:function(data){
			if(data.flag){
				var activityId=$("#activityId").val();
				if(activityId=="2"){
					alert("订单支付成功!");
				}else{
					alert(data.message);
				}				
				window.location.href =jsBasePath+"/activity/weixinPay/index.html?activityId="+activityId;
			}else{
				alert(data.message);
			}
		 },
		error:function(data){
		  alert("error" + data);
		}
	});	
}
//支付取消
function payCancle(){
	alert("抢购的人特别多,请亲尽早支付噢!");
}
//支付失败关闭原订单
function  payFaild(){
	var orderInfoId=$("#orderInfoId").val();
	$.ajax({
		type:"POST",
		url:jsBasePath+"/activity/orderInfo/closeOrderInfo.html?orderInfoId="+orderInfoId,
		async: false,
		dataType:"json",
		success:function(data){			
			alert(data.message);
			window.location.href=jsBasePath+"/activity/weixinPay/index.html?activityId="+$("#activityId").val();
		 },
		error:function(data){
		   alert("支付失败后关闭原订单失败!");
		}
	});	
}

