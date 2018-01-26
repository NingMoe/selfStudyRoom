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
<script src="<%=basePath %>/static/weui/js/jquery-weui.min.js"></script>
<link rel="stylesheet" href="<%=basePath %>/static/weui/css/weui.min.css">
<link rel="stylesheet" href="<%=basePath %>/static/weui/css/jquery-weui.min.css">
<link href="<%=basePath %>/static/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet" />
<style type="text/css">
 input[type="text"] {
-webkit-appearance: none;
}
textarea{
-webkit-appearance: none;
}
</style>
<script type="text/javascript">
var jsBasePath = '<%=basePath %>';


Date.prototype.Format = function (fmt) { //author: meizz   
    var o = {  
        "M+": this.getMonth() + 1, //月份   
        "d+": this.getDate(), //日   
        "H+": this.getHours(), //小时   
        "m+": this.getMinutes(), //分   
        "s+": this.getSeconds(), //秒   
        "q+": Math.floor((this.getMonth() + 3)/ 3), 
        "S": this.getMilliseconds() //毫秒   
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
}  
  
</script>
	


