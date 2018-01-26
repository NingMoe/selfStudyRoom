<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>教师中心</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">

</head>
<body ontouchstart style="background-color: #eee">
	<div class="page flex js_show" style="background-color: #fff">
		<div class="page__bd page__bd_spacing">
			<div>
				<img src="<%=basePath%>/static/front/image/jszx.jpg" style="width: 100%; height: auto;">
			</div>
    		<c:forEach items="${menuMap }" var="entry">
    			<div class="weui-cells__title" style="background-color: #eee;margin-top: 0px;margin-bottom: 0px;padding-top:8px;">
    			${fn:substringBefore(entry.key, "-")} </div>
    			<div class="weui-grids whitebg" style="margin-top:0px; padding-top:0px;">
    				<c:forEach items="${entry.value }" var="menu">
    					<a href="${menu.url }" class="weui-grid js_grid" data-id="button" style="width: 25%;padding: 8px 6px;">
					        <div class="weui-grid__icon" style="width:60%;height:60%">
					            <img src="${fileurl }${menu.icon }" alt="">
					        </div>
					        <p class="weui-grid__label" style="font-size: 12px;">${menu.name }</p>
					    </a>   
    				</c:forEach>
    			</div>
    		</c:forEach>
		</div>
	</div>
	<div style="height: 20px;"></div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/jquery-weui.js"></script>
<script type="text/javascript">
	$("a[href='###']").bind("click",function(){
		$.confirm("确认解除微信绑定?", function() {
			  //点击确认后的回调函数
			var index = layer.load(1, {shade: [0.8, '#393D49']});
			wxUnbind(index);
		  }, function() {
			  //点击取消后的回调函数
		});
	});
	
	function wxUnbind(index){
		$.ajax({
			type : "post",
			dataType : "json",
			url : jsBasePath+"/wechat/binding/deletebindinginfo.html",
			success: function(data){
				if(data.flag){
					layer.close(index); 
					$.alert(data.message, function() {
						window.location.href=jsBasePath+"/wechat/binding/toTeacherCenter.html";
					});				
				}else{
				   layer.close(index); 
				   $.alert(data.message, function() {				   
				    });
				}
			},
			error : function(data, status, e) {	
				layer.close(index);
				$.alert("ajax请求出错"+e);			
			}
		});	
	}
</script>
</html>