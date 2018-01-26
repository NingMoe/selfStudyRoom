<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="format-detection" content="telephone=no"/>
	<title>图书馆守则</title>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/jquery.js"></script> 
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/bootstrap/js/bootstrap.js"></script>
	<link rel="stylesheet" href="<%=basePath %>/static/teacherservice/weixin/book/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath %>/static/teacherservice/weixin/book/css/index.css">
    <style>
    	.searchshu{width:100%;padding-top:10px;background:#ffffff;}
    	.search-1{width:70%;margin-left:2.5%;float:left;border-radius:3px;border:1px solid #f1f1f1 !important;height:30px!important;line-height:30px!important;margin-bottom:0!important; box-sizing:border-box;}
    	.search-2{width:25% !important;float:left;border-radius:3px;background:#009688;color:#ffffff;line-height:30px; box-sizing:border-box;}
    	.tushuxvzhi p{ text-align:center;line-height:30px;}
    	.tushuxvzhi{background:#ffffff;padding-top:15px;padding-bottom:15px;}
    </style>
</head>

<body>
<input id="openid" type="hidden" value="${openid }"/>
<script type="text/javascript">
var jsBasePath = '<%=basePath %>';
</script>
<div class="top-box">
	<img class="top-img" src="<%=basePath %>/static/teacherservice/weixin/book/images/li-logo.png">
    <p class="top-word">微图书馆</p>
    <div class="clearfix"></div>
</div>
<div class="banner-box">
	<img src="<%=basePath %>/static/teacherservice/weixin/book/images/li-banner.png">
</div>
<div class="classify-box"style="width:100%;overflow-x:hidden">
	<div class="classify-p"  style="width:100%;overflow-x:hidden">
    	<p style="width:40%">图书馆使用须知</p>
    </div>
    <div class='tushuxvzhi'>
    	<p>1,仅限新东方员工使用，so需要刷员工卡进入</p>
    	<p>2,每周一和法定节假日闭关，其他时间任性开放</p>
    	<p>3,借书期限一个月，每次最多2本</p>
    	<p>4,遗失或损毁，请买本新书补上</p>
    	<p>5,借书、还书请在图书馆线上借阅系统操作</p>
    </div>
</div>


<script type="text/javascript">
var openid = $("#openid").val();
$(function(){
	manbook();
	type();
	$("#search_btn").bind("click",function(){
		var search_text = $("#search_text").val();
		if(search_text == ''){
			layer.msg("请输入书籍名称关键词");
			return false;
		}
		window.location.href=jsBasePath+"/wechat/binding/library/categoryview.html?book_name="+search_text;
	});
});

function boxshow(a,b){
	$('.own-box table').hide();
	$(a).siblings().removeClass('own-active');
	$(a).addClass('own-active');
	if(b == 1){
		$('#bookbox').hide();
		$('#manbox').show();
	}else{
		$('#manbox').hide();
		$('#bookbox').show();
	}
}
/*获取最爱看的书及最热门的书列表*/
function manbook(){
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/getbookinfo.html",
		type : "POST",
		dataType : "json",
		data : {
			
		},
		success : function(data){
			if(data.flag){
				var html='';
				for(i=0;i<data.peoplelist.length;i++){
					var num=i+1
					html+='<tr><td>'+num+'</td><td>'+data.peoplelist[i].name+'</td><td>'+data.peoplelist[i].dept_name+'</td><td>'+data.peoplelist[i].num+'</td></tr>'
					$('#man').html(html);
				}
				var bhtml='';
				for(i=0;i<data.booklist.length;i++){
					var num=i+1
					bhtml+='<tr><td>'+num+'</td><td>'+data.booklist[i].book_name+'</td><td>'+data.booklist[i].type_name+'</td><td>'+data.booklist[i].num+'</td></tr>'
					$('#book').html(bhtml);
				}
				//获取最爱看书的人和最热门的书成功
			}else{
				alert(date.message);
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}
function type(){

	$.ajax({
		url : jsBasePath+"/wechat/binding/library/gettype.html",
		type : "POST",
		dataType : "json",
		data : {
			
		},
		success : function(data){
			var html='';
			if(data.flag){
				for(i=0;i<data.list.length;i++){
					var num=i+1
					html+='<div class="classify-imgbox" onclick=\"fenlei('+data.list[i].id+');\"><img src="http://hrms-img.oss-cn-shanghai.aliyuncs.com/'+data.list[i].type_cover_url+'"></div>'
				}
				html+='<div class="clearfix"></div>'
				$('#fenlei').html(html)
				$('#manbox').show()
				//获取分类成功
			}else{
				alert(date.message);
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}

 /*跳转借阅中心*/
 function borrow(){
	 window.location.href=jsBasePath+"/wechat/binding/library/borrowview.html"
 }
 /*跳转分类中心*/
 function fenlei(a){
	 window.location.href=jsBasePath+"/wechat/binding/library/categoryview.html?id="+a
 }
</script>
</body>
</html>