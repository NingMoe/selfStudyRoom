<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>学员电话查询</title>
	<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
	<meta name="keywords" content="新东方合肥学校">
	<%@include file="/WEB-INF/view/sign/frontWeiXin/taglib.jsp" %>
	<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/base.css">
	<style>
.title{
	background: linear-gradient(to right,#00b3a3,#00b38a);
	height: 1.8rem;
	padding-top: .4rem;
	font-size: .20rem;
	color: white;
}
.title p{
	margin-top: .2rem;
	height: .4rem;
	line-height: .4rem;
	text-align: center;
	font-weight:700;
}
.title >div{
	position: relative;
	margin: 0  auto;
	width: 91%;
	margin-top: .1rem;
	color:#D7D7D7;
}
.title input{
	
	font-weight: 400;
	width: 80%;
	border-radius: .06rem;
	height: .36rem;
	margin: 0  auto;
	color: #D7D7D7;
	font-size: 0.2rem;
	margin-left: .1rem;
}
.title button{
	background: rgba(255,255,255,0);
	height: .36rem;
	/*width: 18%;*/
	border-radius: 0.04rem;
	position: relative;
	vertical-align: middle;
    color: white;
    text-align: center;
    font-weight: normal;
    font-size: .16rem
}
input[placeholder]{
   color:#D7D7D7 !important;
}
.list1{
	margin-top: .1rem;
}
.list1 li{
  padding: 0  0.16rem;
}
.border{
	padding-bottom: .1rem;
	/*border-bottom: 1px solid #ccc;*/
}
.list1 li .border{
	border-bottom: 1px solid   rgba(6,6,6,0.1);
}
.list1  .list1Left{
	color:#666;
	float: left;
   width: 60%;
}
.list1Left p{
	font-size:0.16rem;
	height: 0.2rem;
	line-height: 0.2rem;
}
.list1  a{
   float: right;
   color:white ;
   width:.4rem;
   height:.4rem;
}
.list1  a img{
height: 100%;
width: 100%;	
}
input::-webkit-input-placeholder { /* WebKit browsers */ text-align: left;color:#ccc; font-size: .14rem;line-height:.28rem; } 
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */ text-align:  left;color:#ccc;font-size: .14rem;line-height:.28rem;  } 
input:-ms-input-placeholder { /* Internet Explorer 10 */ text-align: left;color:#ccc; font-size: .14rem;line-height:.28rem; } 
input::placeholder { text-align: left; color:ccc; font-size: .14rem;line-height:.28rem;} 
</style>
</head>
<body>
  <div class="title">
   	<p>学员信息查询助手</p>
   	<div>
   	  <input id="studentCode" type="text" placeholder="请输入学员号查询,多个以逗号分隔">   	  
      <button onclick="serach();">搜索</button>
   	</div>
  </div>
  <div>
    <ul class="list1" id="phoneList">
        
    </ul>
  </div>	
</body>
<script type="text/javascript" src="<%=basePath%>/static/xdfStudent/wechat/js/searchPhone.js"></script>
</html>