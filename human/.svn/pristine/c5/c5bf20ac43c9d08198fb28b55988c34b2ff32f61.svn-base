<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script src="<%=basePath %>/static/jquery/jquery-1.11.1.min.js"></script>
<meta charset="utf-8">
		<title>提示</title>
		<style type="text/css">
/* 注意、警告框 */
.attention {
	background: #FFFBCC;
	border: 1px #E6DB55 solid;
	color: #333;
	margin: 10px;
	padding: 8px 8px 8px 35px;
	line-height: 22px;
	font-size: 12px;
	text-align: center;
}
/* 圆角，CSS3支持 */
</style>
	</head>
	<body>
		<DIV class="attention">
			提示:${msg}
			<br><c:if test="${ not empty url }">页面将在<span id="s" style="color: red;">5</span>秒后跳转!</p></c:if>
		</div>
		<script type="text/javascript">
/* 		function dianji(){
			var num=5;
			 setInterval(function(){
			            $("#s").html(num);// 你倒计时显示的地方元素
			              num--;
			           if(num==0){          
			        	   window.location.href =${url};
			        }
			}, 1000);
		}   */ 
		function dianji(url){
			var num=4;
			var hs=setInterval(function() {
				 $("#s").html(num);
				 num--;
				  if(num==0){
					  clearInterval(hs)
		        	   window.location.href =url;
		        }
			}, 1000)
		}
			
			$(function() {
				var url="${url}";
				if(url!=""){
					dianji(url);
				}
	    	});
		</script>
	</body>
</html>
