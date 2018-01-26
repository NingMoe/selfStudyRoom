<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>首页</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
<style type="text/css">
.selector{ 
	font-family:"Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu; 
	letter-spacing:1px;
} 
</style>
</head>
<body ontouchstart>
	
		<input id="openId" type="hidden" value="${openId }">
		<input id="requestUri" type="hidden" value="${requestUri }">
		<input id="code" type="hidden" >
		<div class="selector" style="font-size:20px;margin: 42px auto 20px;text-align: center;width: 85%;font-weight:500;">
			请先输入以下真实信息方便您接收面试通知等信息
		</div>
	<div style="width:82%;margin: 0 auto;">
		<div class="weui-cells weui-cells_form" style="border: none;overflow: visible;">
			<div class="weui-cell" style="border: 1px solid #ddd;margin-top:25px;border-radius:8px;">
	    		<div class="weui-cell__hd" style="width:9%;border-right: 1px solid #ddd;">
	    			<label class="weui-label selector"><img src="<%=basePath%>/static/front/image/name.png" style="width:20px;height: auto;"></label>
	    		</div>
	    		<div class="weui-cell__bd" style="padding-left: 10px;">
	      			<input class="weui-input selector" id="seekerName" name="seekerName" type="text" placeholder="真实姓名">
	    		</div>
	  		</div>
	  		
	  		<div class="weui-cell" style="border: 1px solid #ddd;margin-top:20px;border-radius:8px;">
	    		<div class="weui-cell__hd" style="width:9%;border-right: 1px solid #ddd;">
	    			<label class="weui-label selector"><img src="<%=basePath%>/static/front/image/phone.png" style="width:20px; height: auto;"></label>
	    		</div>
	    		<div class="weui-cell__bd" style="padding-left: 10px;">
	      			<input class="weui-input selector" type="tel" id="telephone" name="telephone" placeholder="手机号">
	    		</div>
	  		</div>
	  		<div class="weui-cell weui-cell_vcode" style="border: 1px solid #ddd;margin-top:20px;border-radius:8px;">
			    <div class="weui-cell__bd">
			    	<input class="weui-input selector" id="jym" name="jym" type="text" placeholder="请输入验证码">
			    </div>
			    <div class="weui-cell__ft" style="padding:2px;">
			    	<button class="weui-vcode-btn selector" id="fs" style="background-color:#32a995;border-radius:6px;color:#fff;font-size: 16px;">发送验证码</button>
			    </div>
	  		</div>
	  		<a href="javascript:;" id="bnt_bind" class="weui-btn weui-btn_primary" style="border-radius:20px;width: 85%;margin-top:25px;background-color:#32a995; ">确&nbsp;&nbsp;认</a>
	  	</div>
	</div>
  	<script type="text/javascript">
  	var clock;//全局定时器事件
  	var nums = 60;//全局60秒倒计时 
  	
	$("#bnt_bind").bind("click",function(){
		var telephone = $("#telephone").val();
		var seekerName = $("#seekerName").val();
		var code = $("#code").val();
		var jym = $("#jym").val();
		if(!seekerName){
			layer.alert("请输入真实姓名");
			return false;
		}
		if(!telephone){
			layer.alert("请输入手机号码");
			return false;
		}
		if(!jym){
			layer.alert("请输入验证码");
			return false;
		}
		
		if($.trim(jym)!=code){
			layer.alert("验证码输入不正确");
			return false;
		}
		
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : jsBasePath+"/front/home/bindOpenId.html",
			data : {
				name : seekerName,
				phone : telephone
			},
			success : function(data){
				if(data.flag){
					layer.alert(data.message,{icon:1},function(index){
						location.href = $("#requestUri").val();
					});
				}
			},
			error : function(data){
				layer.alert("ajax请求错误！");
			}
		});
		
	});
  		
	$("#fs").bind("click",function(){
		var phone = $("#telephone").val();
	    if($.trim(phone) == "" || phone == null){
	    	layer.alert("手机号不能为空！",{icon:2});
	    }else if(!(/^1[34578]\d{9}$/.test(phone))){ 
	    	layer.alert("手机号码有误!",{icon:2},function(index){
	    		$("#telephone").html("").focus();
	    		layer.close(index);
	    	});
	    }else{
	    	$("#fs").attr("disabled",true);
	    	clock = setInterval('doLoop()', 1000);//每秒执行1次
	    	sendMsg(phone);
	    } 
	});
	
	function doLoop(){
		nums--;
		if(nums > 0){
			$("#fs").html(nums+"秒后可重新发送");
		}else{
			clearInterval(clock); //清除js定时器
			$("#fs").attr("disabled",false);
			$("#fs").html("获取验证码");
			nums = 60; //重置时间
		}
	}
	
	function sendMsg(phone){
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : jsBasePath+"/front/home/sendWxBindMsg.html",
			data : {
				telephone:phone
			},
			success : function(data){
				if(data.flag){
					layer.alert(data.message,{icon:1},function(index){
						$("#code").val(data.code);
						layer.close(index);
					});
				}else{
					layer.alert(data.message,{icon:2},function(index){
						layer.close(index);
					});
				}
			},
			error : function(data){
				layer.alert("ajax请求错误！");
			}
		});
	}
  	</script>
</body>
</html>