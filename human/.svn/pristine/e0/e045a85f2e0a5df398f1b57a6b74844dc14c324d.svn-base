<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>头像更新</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no, email=no" />
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/nologin/js/css/style.css" rel="stylesheet" type="text/css">
<!--[if IE]>
<script src="js/html5shiv.min.js"></script>
<![endif]-->
</head>
<body >
<!--头部-->
<!-- <div id="user_head">
        <a id="left_ico" href="javascript:history.go(-1);">
          <i class="icon iconfont">&#xe645;</i>
        </a>
        <span>个人头像</span>
        <a id="s_dpage" href="javascript:void(0);">
           <i class="icon iconfont">&#xe633;</i> 
        </a>
</div> -->
<div style="width: 100%;text-align: center;margin-top: 10%;" >
<input type="hidden"  id="id" name="id" value="${rb.id}"> 
    <img  id="headImg"  style="border: 2px solid #F0F0F0;width: 60%;"  <c:if  test="${empty rb.headUrl}">
						 src="<%=basePath%>/static/common/images/hxr_photo.jpg"</c:if>
												<c:if  test="${!empty rb.headUrl}">
						 <c:if test="${fn:startsWith(rb.headUrl, 'http')}"> src="${rb.headUrl }"</c:if>
												<c:if test="${!fn:startsWith(rb.headUrl, 'http')}"> src="${filepath }${rb.headUrl }"</c:if>
											</c:if>" />
</div>
<div class="htmleaf-container">
<div id="clipArea"></div>
<div id="view"></div>
</div>
<div class="btn-1"  id="logoBox">
<button>确认更换</button>
</div>
<div id="dpage">
 <a href="javascript:void(0);" >
     <input type="button" name="file" class="button" value="选择照片">
	  <input id="file" type="file"  accept="image/*"  id="testFile"   />
      </a>
 <a href="javascript:void(0);" ><button type="button"  id="clipBtn" >裁剪上传</button></a>
 <a href="javascript:reback();"><input type="button"  class="button" value="返回"></a>
</div>
<script>window.jQuery || document.write('<script src="<%=basePath %>/static/nologin/js/js/jquery-2.1.1.min.js"><\/script>')</script>
<script src="<%=basePath %>/static/nologin/js/js/iscroll-zoom.js"></script>
<script src="<%=basePath %>/static/nologin/js/js/hammer.js"></script>
<script src="<%=basePath%>/static/nologin/js/js/exif.js" type="text/javascript"></script>
<script src="<%=basePath %>/static/nologin/js/js/jquery.photoClip.js"></script>
<script>
function reback(){
	$(".htmleaf-container").hide();
	$("#dpage").removeClass("show");
}

var obUrl = ''
var index;
//document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
$("#clipArea").photoClip({
	width: 240,
	height: 336,
	file: "#file",
	view: "#view",
	ok: "#clipBtn",
	outputType:"png",
	strictSize:true,
	loadStart: function() {
		index=layer.load(3);
		console.log("照片读取中");
	},
	loadComplete: function() {
		console.log("照片读取完成");
		layer.close(index);
	},
	clipFinish: function(dataURL) {
		$('#headImg').attr('src',imgsource);
		$(".htmleaf-container").hide();
		$("#dpage").removeClass("show");
		var index=layer.load(3);
		var form = new FormData();
		var file = dataURL.substring(22);;
		form.append("base64String ", file);
		form.append("fileType ", "png");
		form.append("resumeId ", $("#id").val());
		$.ajax({
			url:  jsBasePath+'/free/resume/saveQuickPhoto.html',
			type: "POST",
			data: form,
			async: true, //异步
			processData: false, //很重要，告诉jquery不要对form进行处理
			contentType: false, //很重要，指定为false才能形成正确的Content-Type
			success: function(data) {
				layer.close(index);
				layer.msg('上传成功', {
				    time: 1000
				  });
		}});
	}
});
</script>
<script>
$(function(){
$("#logoBox").click(function(){
$(".htmleaf-container").fadeIn(300);
$("#dpage").addClass("show");
})
	/* $("#clipBtn").click(function(){
		
	}) */
});
</script>
<!-- <script type="text/javascript">
$(function(){
	jQuery.divselect = function(divselectid,inputselectid) {
		var inputselect = $(inputselectid);
		$(divselectid+" small").click(function(){
			$("#divselect ul").toggle();
			$(".mask").show();
		});
		$(divselectid+" ul li a").click(function(){
			var txt = $(this).text();
			$(divselectid+" small").html(txt);
			var value = $(this).attr("selectid");
			inputselect.val(value);
			$(divselectid+" ul").hide();
			$(".mask").hide();
			$("#divselect small").css("color","#333")
		});
	};
	$.divselect("#divselect","#inputselect");
});
</script>
<script type="text/javascript">
$(function(){
	jQuery.divselectx = function(divselectxid,inputselectxid) {
		var inputselectx = $(inputselectxid);
		$(divselectxid+" small").click(function(){
			$("#divselectx ul").toggle();
			$(".mask").show();
		});
		$(divselectxid+" ul li a").click(function(){
			var txt = $(this).text();
			$(divselectxid+" small").html(txt);
			var value = $(this).attr("selectidx");
			inputselectx.val(value);
			$(divselectxid+" ul").hide();
			$(".mask").hide();
			$("#divselectx small").css("color","#333")
		});
	};
	$.divselectx("#divselectx","#inputselectx");
});
</script>
<script type="text/javascript">
$(function(){
	jQuery.divselecty = function(divselectyid,inputselectyid) {
		var inputselecty = $(inputselectyid);
		$(divselectyid+" small").click(function(){
			$("#divselecty ul").toggle();
			$(".mask").show();
		});
		$(divselectyid+" ul li a").click(function(){
			var txt = $(this).text();
			$(divselectyid+" small").html(txt);
			var value = $(this).attr("selectyid");
			inputselecty.val(value);
			$(divselectyid+" ul").hide();
			$(".mask").hide();
			$("#divselecty small").css("color","#333")
		});
	};
	$.divselecty("#divselecty","#inputselecty");
});
</script>
<script type="text/javascript">
$(function(){
   $(".mask").click(function(){
	   $(".mask").hide();
	   $(".all").hide();
   })
	$(".right input").blur(function () {
		if ($.trim($(this).val()) == '') {
			$(this).addClass("place").html();
		}
		else {
			$(this).removeClass("place");
		}
	})
});
</script>
<script>
$("#file0").change(function(){
	var objUrl = getObjectURL(this.files[0]) ;
	 obUrl = objUrl;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img0").attr("src", objUrl).show();
	}
	else{
		$("#img0").hide();
	}
}) ;
function qd(){
   var objUrl = getObjectURL(this.files[0]) ;
   obUrl = objUrl;
   console.log("objUrl = "+objUrl) ;
   if (objUrl) {
	   $("#img0").attr("src", objUrl).show();
   }
   else{
	   $("#img0").hide();
   }
}
function getObjectURL(file) {
	var url = null ;
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}
</script>
<script type="text/javascript">
var subUrl = "";
$(function (){
	$(".file-3").bind('change',function(){
		subUrl = $(this).val()
		$(".yulan").show();
		$(".file-3").val("");
	});

	$(".file-3").each(function(){
		if($(this).val()==""){
			$(this).parents(".uploader").find(".filename").val("营业执照");
		}
	});
$(".btn-3").click(function(){
$("#img-1").attr("src", obUrl);
$(".yulan").hide();
$(".file-3").parents(".uploader").find(".filename").val(subUrl);
})
	$(".btn-2").click(function(){
		$(".yulan").hide();
	})

});
</script>
<script type="text/javascript">
function setImagePreview() {
	var preview, img_txt, localImag, file_head = document.getElementById("headImg"),
			picture = file_head.value;
	if (!picture.match(/.jpg|.gif|.png|.bmp/i)) return alert("您上传的图片格式不正确，请重新选择！"),
			!1;
	if (preview = document.getElementById("preview"), file_head.files && file_head.files[0]) preview.style.display = "block",
			preview.style.width = "100px",
			preview.style.height = "100px",
			preview.src = window.navigator.userAgent.indexOf("Chrome") >= 1 || window.navigator.userAgent.indexOf("Safari") >= 1 ? window.webkitURL.createObjectURL(file_head.files[0]) : window.URL.createObjectURL(file_head.files[0]);
	else {
		file_head.select(),
				file_head.blur(),
				img_txt = document.selection.createRange().text,
				localImag = document.getElementById("localImag"),
				localImag.style.width = "100px",
				localImag.style.height = "100px";
		try {
			localImag.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)",
					localImag.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = img_txt
		} catch(f) {
			return alert("您上传的图片格式不正确，请重新选择！"),
					!1
		}
		preview.style.display = "none",
				document.selection.empty()
	}
	return document.getElementById("DivUp").style.display = "block",
			!0
}
</script> -->

</body>
</html>
