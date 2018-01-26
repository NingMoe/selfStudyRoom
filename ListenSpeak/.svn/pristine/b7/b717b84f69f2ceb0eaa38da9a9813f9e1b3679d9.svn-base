<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script src="<%=basePath %>/static/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/AjaxFileUploaderV2.1/ajaxfileupload.js"></script>
<script src="<%=basePath %>/static/layer/layer.js"></script>

<script type="text/javascript">
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?c973a3f2cdcc00a0216aeb5df43114fc";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();

var jsBasePath = '<%=basePath %>';

//获取id下去除前后空格的value
function getValue(id){
	return $.trim($("#"+id).val());
}

//验证是否手机号
function isNotPhone(phone){
	var phonezz = /^[1][3,4,5,7,8][0-9]{9}$/;
	return !phonezz.test(phone);
}
</script>