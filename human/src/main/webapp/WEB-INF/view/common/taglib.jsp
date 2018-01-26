<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script src="<%=basePath %>/static/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/AjaxFileUploaderV2.1/ajaxfileupload.js"></script>
<script src="<%=basePath %>/static/layer/layer.js"></script>
<script src="<%=basePath %>/static/layui/layui.js"></script>
<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
<!-- 图标库 -->
<link href="<%=basePath %>/static/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet" />
<style type="text/css">
.main-wrap {
	height: 100%;
	background: rgba(255, 255, 255, .9);
	padding: 10px 10px 35px 10px;
}

.tableList thead {
	background: #F2F2F2;
}

.disable {
	color: red;
}

.normal {
	color: green;
}

.alertFrom {
	margin: 10px;
	border: 1px solid #e2e2e2;
	padding: 10px;
}
</style>
<script type="text/javascript">
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?c973a3f2cdcc00a0216aeb5df43114fc";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();

var jsBasePath = '<%=basePath %>';
var navtab;
function closeFrame(){
	  var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	  parent.layer.close(index); //执行关闭
}



/**
 * 下载文件模版
 */
function downTemp(newFileName,temFileName){
	  var url=jsBasePath+"/basic/employee/downTemp.html?newFileName="+newFileName+"&temFileName="+temFileName;
	   window.location.href = url;    
}

function toSendMsg(tel,seekerId,resumeId){
	var url =  jsBasePath + "/basic/sms/sendView.html?sendTel="+tel;
	if(!!seekerId){
		url += "&seekerId="+seekerId;
	}
	if(!!resumeId){
		url += "&resumeId="+resumeId;
	}
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
		area : [ '60%', '400px' ],
		title : "发送短信", //不显示标题
		content : url, //捕获的元素
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			location.reload()
		}
	});
}
function sendMsg20170428(){
	var temDesc=$.trim($("#temDesc20170428").val());
	if(temDesc==""){
		layer.alert("请输入短信内容!");
		return false;
	}
	 var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/sms/sendMsg.html",{
			sendTel:$("#sendTel20170428").val(),
			sendComment:temDesc,
			seekerId:$("#seekerId20170428").val(),
			resumeId:$("#resumeId20170428").val(),
			memo:$("#temName20170428").val()
		},function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert("发送失败!");
			}else{
				layer.alert("发送成功!",{icon:1},function(){
				location.reload();
					/* closeFrame(); */
				});
			}
		},"json");
	return true;
}

//二维码中文识别
function toUtf8(str) {    
    var out, i, len, c;    
    out = "";    
    len = str.length;    
    for(i = 0; i < len; i++) {    
        c = str.charCodeAt(i);    
        if ((c >= 0x0001) && (c <= 0x007F)) {    
            out += str.charAt(i);    
        } else if (c > 0x07FF) {    
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));    
            out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));    
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
        } else {    
            out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));    
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
        }    
    }    
    return out;    
} 
/**
 * 二维码展示
 */
function showQrCode(url,title){
	$('#qrcode').qrcode(toUtf8(url));
	$('#qrcodeInput').val(url);
	layer.open({
		  title:title,
		  type: 1,
		  content: $("#qrcodeDiv"), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  end:function(){
			  $("#qrcode").empty();
			  $("#qrcodeInput").val("");
		  }
		});
}
</script>
	
<div  id="qrcodeDiv" style="display: none;padding: 10px;text-align: center;">
				<div id="qrcode" style="margin: 0 auto;"></div>
			<!-- 	<div class="layui-form-item"  > -->
				<!-- <label class="layui-form-label" style="width: 62px !important ;">链接:</label> -->
				<!-- <div class="layui-input-inline"> --><input type="text" autocomplete="off" class="layui-input"  readonly="readonly" id="qrcodeInput">
				<!-- </div> --></div>
	</div>

