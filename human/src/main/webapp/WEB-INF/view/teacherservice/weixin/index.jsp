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
	<title>图书馆首页</title>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/jquery.js"></script> 
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/bootstrap/js/bootstrap.js"></script>
	<link rel="stylesheet" href="<%=basePath %>/static/teacherservice/weixin/book/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath %>/static/teacherservice/weixin/book/css/index.css">
    <style>
    	.searchshu{width:100%;padding-top:10px;background:#ffffff;}
    	.search-1{width:70%;margin-left:2.5%;float:left;border-radius:3px;border:1px solid #f1f1f1 !important;height:30px!important;line-height:30px!important;margin-bottom:0!important; box-sizing:border-box;}
    	.search-2{width:25% !important;float:left;border-radius:3px;background:#009688;color:#ffffff;line-height:30px; box-sizing:border-box;}
    </style>
</head>

<body>
<input id="openid" type="hidden" value="${openid }"/>
<script type="text/javascript">
var jsBasePath = '<%=basePath %>';
</script>
<div class="top-box">
	<img class="top-img" src="<%=basePath %>/static/teacherservice/weixin/book/images/li-logo.png">
    <p class="top-word">图书馆</p>
    <div class="clearfix"></div>
</div> 
<div class="banner-box">
	<img src="<%=basePath %>/static/teacherservice/weixin/book/images/li-banner.png">
</div>
<div class="classify-box"style="width:100%;overflow-x:hidden">
	<div class="classify-p"  style="width:100%;overflow-x:hidden">
    	<p style="width:40%">图书分类</p>
    </div>
<div class="searchshu">
	<input class="search-1" type="text" id="search_text" placeholder="请输入搜索图书"><input class="search-2" id="search_btn" type="button" value="搜索">
	<div class="clearfix"></div>
</div>
    <div class="classify-img" id='fenlei'>
       
    </div>
</div>

<div class="menu-box">
    	<table class="table menu-list">
        	<tr>
            	<td class="menu-icon1"><img src="<%=basePath %>/static/teacherservice/weixin/book/images/li-list1.png"></td>
                <td class="menu-icon2" onclick='borrow();'><p class="menu-jy">我的借阅</p><p class="menu-zs">终身学习</p></td>
                <td class="menu-icon3"><img src="<%=basePath %>/static/teacherservice/weixin/book/images/liberary_40.png"></td>
            </tr>
        </table>
        <table class="table menu-list">
        	<tr>
            	<td class="menu-icon1"><img src="<%=basePath %>/static/teacherservice/weixin/book/images/li-list2.png"></td>
                <td class="menu-icon2" onclick='lawview();'><p class="menu-jy">图书馆须知</p><p class="menu-zs">有规有矩</p></td>
                <td class="menu-icon3"><img src="<%=basePath %>/static/teacherservice/weixin/book/images/liberary_40.png"></td>
            </tr>
        </table>
        <table class="table menu-list">
        	<tr>
            	<td class="menu-icon1"><img src="<%=basePath %>/static/teacherservice/weixin/book/images/li-list1.png"></td>
                <td class="menu-icon2" onclick='myFeedBack();'><p class="menu-jy">我要推荐</p><p class="menu-zs">好书共享</p></td>
                <td class="menu-icon3"><img src="<%=basePath %>/static/teacherservice/weixin/book/images/liberary_40.png"></td>
            </tr>
        </table>
        <table class="table menu-list">
        	<tr>
            	<td class="menu-icon1"><img src="<%=basePath %>/static/teacherservice/weixin/book/images/li-list1.png"></td>
                <td class="menu-icon2" onclick='myFeedBack2();'><p class="menu-jy">意见反馈</p><p class="menu-zs">有啥说啥</p></td>
                <td class="menu-icon3"><img src="<%=basePath %>/static/teacherservice/weixin/book/images/liberary_40.png"></td>
            </tr>
        </table>
</div>
<div class="own-box">
	<div class="own-menu">
    	<p id="p1" class="own-active" onclick='boxshow(this,1)'>最爱看书的人</p>
        <p id="p" onclick='boxshow(this,2)'>最热门的书</p>
        <div class="clearfix"></div>
    </div>
    <table id='manbox' class="table table-striped own-list hide">
    	<thead>
        	<th>排名</th>
            <th>借阅人</th>
            <th>部门</th>
            <th>阅读量</th>
        </thead>
        <tbody id='man'>
        	<c:if test="${flag }">
        		<c:forEach items="${peoplelist }" var="pp" step="1">
	        		<tr>
		        		<td>${step}</td>
		        		<td>${pp.name }</td>
		        		<td>${pp.dept_name }</td>
		        		<td>${pp.num }</td>
		        	</tr>
        		</c:forEach>
        	</c:if>
        	
        	
        </tbody>
    </table>
    <table id='bookbox' class="table table-striped own-list hide">
    	<thead>
        	<th style="width:15%">排名</th>
            <th style="width:35%">书籍名</th>
            <th style="width:25%">所属类别</th>
            <th style="width:25%">借阅次数</th>
        </thead>
        <tbody id='book'>
        	<c:if test="${flag }">
        		<c:forEach items="${booklist }" var="book" step="1">
        			<tr>
        				<td>${step}</td>
        				<td>${ book.book_name}</td>
        				<td>${ book.type_name}</td>
        				<td>${ book.num}</td>
        			</tr>
        		</c:forEach>
        	</c:if>
        </tbody>
    </table>
</div>
<script type="text/javascript">
var openid = $("#openid").val();
$(function(){
	//manbook();
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
				}
				$('#man').html(html);
				var bhtml='';
				for(i=0;i<data.booklist.length;i++){
					var num=i+1
					bhtml+='<tr><td>'+num+'</td><td>'+data.booklist[i].book_name+'</td><td>'+data.booklist[i].type_name+'</td><td>'+data.booklist[i].num+'</td></tr>'
				}
				$('#book').html(bhtml);
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
 
 /*图书馆须知*/
 function lawview(){
	 window.location.href=jsBasePath+"/wechat/binding/library/lawview.html"
 }
 
 /*我的反馈*/
 function myFeedBack(){
	 window.location.href=jsBasePath+"/wechat/binding/library/ToMyFeedBack.html"
 }
 
 /*意见反馈*/
 function myFeedBack2(){
	 window.location.href=jsBasePath+"/wechat/binding/library/ToMyFeedBack2.html"
 }
</script>
</body>
</html>