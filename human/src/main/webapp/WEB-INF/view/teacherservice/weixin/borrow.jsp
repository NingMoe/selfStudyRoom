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
	<title>我的借阅</title>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/jquery.js"></script>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/bootstrap/js/bootstrap.js"></script>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/layer/layer.js"></script>
	<link rel="stylesheet" href="<%=basePath %>/static/teacherservice/weixin/book/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath %>/static/teacherservice/weixin/book/css/index.css">
    <style>
    	.anniu2 input{
    		width:45%!important;
    		margin-left:3%!important;
    		background:#ffffff;
    		box-sizing:border-box;
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
    <p class="top-word">借阅中心</p>
    <div class="clearfix"></div>
</div>
<div class="borrow-box">
	<table class="table table-striped borrow">
    	<thead>
        	<th style="width:40%">借书时间</th>
            <th style="width:30%">图书名称</th>
            <th style="width:30%">还书信息</th>
        </thead>
        <tbody id='mylist'>
        	
        </tbody>
    </table>
</div>
<div id="jieshu" style="display:none">

</div>
<script type="text/javascript">
var openid = $("#openid").val();
var id = getQueryString("id");
var huanshuo;
$(function (){
	shuaxin();
});

function shuaxin(){
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/getborrowbook.html",
		type : "POST",
		dataType : "json",
		data : {
			
		},
		success : function(data){
			if(data.flag){
				var html='';
				for(i=0;i<data.list.length;i++){
					html+='<tr><td>'+data.list[i].borrow_time.substring(0,10)+'</td><td>'+data.list[i].book_name+'</td>';
					if(!!data.list[i].return_time){
						html+='<td>'+data.list[i].return_time.substring(0,10)+'</td>'
					}else{
						html+='<td><input style="width:80%" onclick="huanshu('+data.list[i].borrow_id+','+data.list[i].book_id+')" class="borrow-huan" type="button" value="我要还书"></td>'
					}
				}
				$('#mylist').html(html);
			}else{
				layer.msg(data.message);
			}
		},
		error : function(data){
			alert("请联系信管老师");
		}
	});
}

//还书
function returnbook(id){
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/returnbook.html",
		type : "POST",
		dataType : "json",
		data : {
			borrow_id : id
		},
		success : function(data){
			layer.msg(data.message);
			if(data.flag){
				shuaxin();
			}
			layer.close(huanshuo);
		},
		error : function(date){
			alert("请联系信管老师");
		}
	});
}

function huanshu(a,b){
	var id=b;
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/queryById.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data){
			if(data.flag){
				var html='';
				html='<div class="pop"><p>图书名称：'+data.libBook.book_name+'</p><p>摆放位置：'+data.libBook.book_address+'</p> </div><div class="anniu2"><input type="button" onclick="returnbook('+a+')" value="确认还书"><input type="button"  onclick="layerclose()" value="取消"><div class="clearfix"></div></div>'
				$('#jieshu').html(html);
			}else{
				layer.msg(data.message);
			}
		},
		error : function(data){
			alert("网络出错，请重新发送。");
		}
	});	
	
	
	
huanshuo = layer.open({
	title: ['我要还书', 'background:#45b2a8;color:#ffffff;text-align:center;padding:0'],
  	type: 1,
	skin:'layer-box',
	area:'80%',
	offset: '25%',
 	content: $('#jieshu') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
});
}


//异常书籍
function errorbook(id){
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/errorbook.html",
		type : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(date){
			layer.msg(date.message);
			layer.close(huanshuo);
		},
		error : function(date){
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
</script>
</body>
</html>
