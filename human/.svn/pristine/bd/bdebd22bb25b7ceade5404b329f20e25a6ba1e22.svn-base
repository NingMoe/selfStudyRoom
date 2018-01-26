<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>学员中心</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
<style type="text/css">
.swiper-wrapper img{
width: 100%;
}

</style>
</head>
<body ontouchstart style="background-color: #eee">
	<div class="page flex js_show" style="background-color: #fff">
		<div class="page__bd page__bd_spacing">
			<div class="swiper-container" >
				<div class="swiper-wrapper">
					<c:if test="${ !empty allMenus}">
						<c:forEach items="${allMenus}" var="dept">
							<div class="swiper-slide">
							<c:if test="${dept.banerUrl==null or dept.banerUrl=='' }">
									<img src="<%=basePath%>/static/wxCustomer/mailFox/image/xyzx.jpg" alt="" >
							</c:if>
							<c:if test="${dept.banerUrl !=null and dept.banerUrl !='' }">
								<img src="${fileurl}${dept.banerUrl}" alt="">
							</c:if>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${empty allMenus}">
						<div class="swiper-slide">
							<img src="<%=basePath%>/static/wxCustomer/mailFox/image/xyzx.jpg" alt="">
						</div>
					</c:if>
				</div>
				 <div class="swiper-pagination" st></div>
			</div>
			<c:forEach items="${allMenus}" var="dept">
				<c:forEach items="${dept.cmList }" var="entry">
					<div class="weui-cells__title"
						style="background-color: #eee; margin-top: 0px; margin-bottom: 0px; padding-top: 8px;">
						${entry.modelName}</div>
					<div class="weui-grids whitebg"
						style="margin-top: 0px; padding-top: 0px;">
						<c:forEach items="${entry.ccmList }" var="menu">
							<a href="${menu.url }" class="weui-grid js_grid" data-id="button"
								style="width: 25%; padding: 8px 6px;">
								<div class="weui-grid__icon" style="width: 60%; height: 60%">
									<img src="${fileurl }${menu.icon }" alt="">
								</div>
								<p class="weui-grid__label" style="font-size: 12px;">${menu.name }</p>
							</a>
						</c:forEach>
					</div>
				</c:forEach>
			</c:forEach>
		</div>
	</div>
	<div style="height: 20px;"></div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/jquery-weui.js"></script>
<script type='text/javascript' src='<%=basePath%>/static/weui/js/swiper.js' charset='utf-8'></script>
<script type="text/javascript">
var coun=$(".swiper-wrapper").children().length;
if(coun>1){
	var mySwiper = new Swiper('.swiper-container',{
		pagination : '.swiper-pagination',
		autoplay : 2000,
		autoplayDisableOnInteraction : false,
		loop : true
		});
}else{
	var mySwiper = new Swiper('.swiper-container',{
		autoplay : 2000,
		autoplayDisableOnInteraction : false
		});
}


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
			url : jsBasePath+"/wxCustomer/CustomerCenter/unbind.html",
			success: function(data){
				if(data.flag){
					layer.close(index); 
					$.alert(data.message, function() {
						window.location.href=jsBasePath+"/wxCustomer/CustomerCenter/toStudentCenter.html";
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