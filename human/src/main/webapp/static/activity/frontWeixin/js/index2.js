var clock;//全局定时器事件
var nums = 60;//全局60秒倒计时 
//验证手机号格式
function checkPhone(){
	var pno = $.trim($("#telephone").val());
	if(pno==""){
		return;
	}
	var reg = /^1[3|4|5|7|8][0-9]{9}$/; //验证手机号规则
	var flag = reg.test(pno);
	if(!flag){
	    alert("手机号格式不正确");
	    $("#telephone").val("").focus();	
	}
}
//发送验证码
function validate(){  	
	var phone = $("#telephone").val();
    if($.trim(phone) == ""){
    	alert("手机号不能为空!");
    	return false;
    }else{ //输入正确时  
         var reg = /^1[3|4|5|7|8][0-9]{9}$/; //验证手机号规则
	     var flag = reg.test(phone);
	     if(!flag){
	       alert("手机号格式不正确");
	       return false;
	     }else{
		     $("#get_msg_btn").attr("disabled",true);
	    	 $("#get_msg_btn").css("background","gray");
	    	 clock = setInterval('doLoop()', 1000);//每秒执行1次
	    	 getMsg(phone);
	     }	
    }                       
}
//倒计时
function doLoop(){
	nums--;
	if(nums > 0){
		$("#get_msg_btn").text(nums+"秒后可重新获取");
	}else{
		clearInterval(clock); //清除js定时器
		$("#get_msg_btn").attr("disabled",false);
		$("#get_msg_btn").text("获取验证码");
		$("#get_msg_btn").css("background","#fb12a9");
		nums = 60; //重置时间
	}
}
//发送验证码
function getMsg(phone){
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : jsBasePath+"/activity/weixinPay/sendCode.html",
		data : {
			telNumber:phone,
			type: "1"
		},
		success : function(data){
		    alert(data.message);		
		},
		error : function(data){
			alert("网络错误,请联系管理员!");
		}
	});
}
//立即抢购
function confirmBuy(){
	var index = layer.load(1, {shade: [0.8, '#393D49']});
//	//判断是否微信浏览器中打开
//	var ua = window.navigator.userAgent.toLowerCase();
//	if(ua.match(/MicroMessenger/i) != 'micromessenger'){		
//		alert("请在微信浏览器中打开!");
//		layer.close(index);
//		return false;
//	}
    //第一步：判断信息是否完善
	var name=$.trim($("#name").val());
	if(name==""){
		alert("姓名必填!");
		layer.close(index);
		return false;
	}	
	var recover=$("input:radio").is(":checked");
	if(!recover){
        alert("优惠卷类型必选!");
        layer.close(index);
	    return false;		
    }
	var text1=$("#gradeId").val();
	if(text1==""){
		alert("年级必选!");
		layer.close(index);
		return false;
	}
	var subject=$("input:checkbox").is(":checked");
	if(!subject){
        alert("预约科目必选!");
        layer.close(index);
	    return false;		
    }
	var buySum=parseInt($("#buySum").val());
	if(buySum==0){
	   alert("购买数量必选!");
	   layer.close(index);
	   return false;
	}    
    var telephone=$.trim($("#telephone").val());
	if(telephone ==""){
		alert("手机号必填!");
		layer.close(index);
		return false;
	}	
    var code=$.trim($("#msg").val());
	if(code ==""){
		alert("手机号验证码必填!");
		layer.close(index);
		return false;
	}
    //第二步：后台校验
    var productId=$('input:radio:checked').val();
    var activityId=$("#activityId").val();
	var text2="";
	$('input:checkbox:checked').each(function (index, item) {
		text2+=$(this).val()+",";
	});
	text2=text2.substring(0,text2.length-1);
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : jsBasePath+"/activity/orderInfo/checkOrderInfo.html",
		data : {
			activityId : activityId,
			productId : productId,
			name : name,
			buySum : buySum,
			telephone : telephone,
			code : code
		},
		success : function(data){
			if(data.flag){				
				window.location.href = jsBasePath+"/activity/orderInfo/insertOrderInfo.html?activityId="+activityId+"&productId="+productId+"&name="+name+"&telephone="+telephone+"&buySum="+buySum+"&text1="+text1+"&text2="+text2;				
			}else{
				layer.close(index);
				alert(data.message);
			}
		},
		error : function(data){
			alert("网络错误,请联系管理员!");
		}
	});
}
//我的卡卷
function myCard(){
	window.location.href = jsBasePath+"/activity/weixinPay/toMyCardInfo.html";
}