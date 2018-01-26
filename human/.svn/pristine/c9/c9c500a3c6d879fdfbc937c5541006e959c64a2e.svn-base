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
	<title class="fenlei"></title>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/jquery.js"></script>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/bootstrap/js/bootstrap.js"></script>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/main.js"></script>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/layer/layer.js"></script>
	<link rel="stylesheet" href="<%=basePath %>/static/teacherservice/weixin/book/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath %>/static/teacherservice/weixin/book/css/index.css">
      <style>
    	.searchshu{width:100%;padding-top:10px;background:#ffffff;padding-bottom:10px;}
    	.search-1{width:70%;margin-left:2.5%;float:left;border-radiu s:3px;border:1px solid #f1f1f1 !important;height:30px!important;line-height:30px!important;margin-bottom:0!important; box-sizing:border-box;}
    	.search-2{width:25% !important;float:left;border-radius:3px;background:#009688;color:#ffffff;line-height:30px; box-sizing:border-box;}
    	.anniu2 input{
    		width:30%;
    		margin-right:3%;
    		background:#ffffff;
    		margin-left:0!important;
    		}
    </style>
</head>
<body>
<script type="text/javascript">
var jsBasePath = '<%=basePath %>';
</script>
<input id="openid" type="hidden" value="${openid }"/>
<div class="top-box">
	<img class="top-img" src="<%=basePath %>/static/teacherservice/weixin/book/images/li-logo.png">
    <p class="top-word fenlei"></p>
    <div class="clearfix"></div>
</div>
<div class="borrow-box">
<div class="searchshu">
	<input class="search-1" type="text" id="search_text" placeholder="请输入搜索图书"><input class="search-2" id="search_btn" type="button" value="搜索">
	<div class="clearfix"></div>
</div>
	<table class="table table-striped borrow">
    	<thead>
        	<th style="width:40%">图书名称</th>
            <th style="width:25%">图书编码</th>
            <th style="width:35%">图书操作</th>
        </thead>
        <tbody id="list">
        </tbody>
    </table>
</div>
<div id="cantfind" style="display:none">

</div>
<div id="huanshu" style="display:none">
	 <div class="pop">
     	<p>图书名称：智能时代</p>
        <p>摆放位置：富广6楼图书馆A-14书柜</p>
     </div>
     	<input type="button" value="确认摆放到位"><input type="button" style="margin-left:20%" value="取消">
        <div class="clearfix"></div>
     </div>
</div>
<div id="jieshu" style="display:none">

</div>
<script type="text/javascript">
var id = getQueryString("id");
var book_name = getQueryString("book_name");
$(function(){
	list();
	$("#search_btn").bind("click",function(){
		var search_text = $("#search_text").val();
		if(search_text == ''){
			layer.msg("请输入书籍名称关键词");
			return false;
		}
		window.location.href=jsBasePath+"/wechat/binding/library/categoryview.html?book_name="+search_text;
	});
});

/* 获取管理列表 */
function list(){
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/getbooktype.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
			book_name : book_name
		},
		success : function(data){
			if(data.flag){
				var html = '';
				var nhtml ='';
				$.each(data.booklist, function(index, book_info){
					if(book_info.num >= book_info.book_count){
						nhtml+='<tr><td>'+book_info.book_name+'</td><td>'+book_info.book_code+'</td>';
						nhtml+='<td><input style="width:80%" class="borrow-no" type="button" value="已被借阅"></tr>';
					}else{
						html+='<tr><td>'+book_info.book_name+'</td><td>'+book_info.book_code+'</td>';
						html +='<td><input style="width:80%" class="borrow-huan" onClick="jieshu('+book_info.id+',\''+book_info.book_type_name+'\')"type="button" value="我要借书"></td></tr>';
					}
				});
				$('#list').html(html+nhtml);
			}else{
				layer.msg(data.message);
			}
			
		},
		error : function(data){
			alert("请联系信管老师");
		}
	});
}

//异常书籍
function errorbook(id, book_name){
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/errorbook.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
			book_name : book_name
		},
		success : function(data){
			layer.msg(data.message);
			layer.close(jieshuopen);
		},
		error : function(data){
			alert("请联系信管老师");
		}
	});
}

//借书
function borrowbook(id, book_name,type_name){
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/borrowbook.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id,
			book_name : book_name,
			book_type_name : type_name
		},
		success : function(data){
			layer.msg(data.message);
			if(data.flag){
				list();
			}
			layer.close(jieshuopen);
		},
		error : function(data){
			alert("请联系信管老师");
		}
	});
}


//JavaScript Document
function getQueryString(key){
  var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
  var result = window.location.search.substr(1).match(reg);
  return result?decodeURIComponent(result[2]):null;
}

function closeFrame(){
	  var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	  parent.layer.close(index); //执行关闭
}
</script>
</body>
</html>