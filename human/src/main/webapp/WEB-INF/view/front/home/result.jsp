<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>筛选结果</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
<style type="text/css">
	.xz{
		background-color: #31A894;
		color:#ffffff !important;
	}
</style>
</head>
<body ontouchstart>
	<div class="page flex js_show">
		<div class="page__bd page__bd_spacing">
			<div class="weui-flex" id="topar">
				<span id="areaspan" style="width: 20%; color: white; margin-left: 15px;">${selectedArea.areaName }
				<img alt="" style="width:15px;height:15px;" src="<%=basePath%>/static/front/image/arrows_down.png">
				</span>
				<input type="hidden" id="areaId" value="${selectedArea.id }">
				<input class="iconfont" style="width:55%;" id="search" placeholder="职位搜索" value="${search }"> 
				<span style="width: 10%;margin-left: 10px;">
					<a href="javascript:;" id="btn_search" style="color:white;">筛选</a>
				</span> 
			</div>
			<div class="weui-form-preview">
			<div style="height: 10px;"></div>
			<c:if test="${empty positions }">
				<div style="margin-left: 15px;">暂无搜索结果</div>
			</c:if>
			<c:if test="${!empty positions }">
				<c:forEach items="${positions}" var="position">  
					<div class="weui-form-preview__hd posi" style="line-height: 1.5em;">
						<input type="hidden" name="positionId" value="${position.id }">
						<div class="weui-form-preview__item" style="text-align: left;">
							<div>
								<span style="font-size: 14px;">${position.name }</span> <span class="page__desc1 gx">${position.postionClassification }</span> 
								<span class="page__desc2" style="color: #FFC7B9;">${position.salaryDesc }</span>
							</div>
						</div>
						<div class="weui-form-preview__item" style="text-align: left;margin-top: 3px;">
							<span class="page__desc">${position.companyName }</span> 
							<span class="page__desc2" style="color:#888;">招聘人数：${position.recruitmentNumber }</span>
						</div>
						<div class="weui-form-preview__item" style="text-align: left;margin-top: 3px;">
							<span class="page__desc3"><img style="width:16px;height:auto;margin-right: 5px;" alt="" src="<%=basePath%>/static/front/image/location.png">${position.workCitys }</span>
							<span class="page__desc3" style="margin-left: 10px;"><img style="width:16px;height:auto;margin-right: 5px;" alt="" src="<%=basePath%>/static/front/image/xueli.png">${position.requireDegree }</span>
							<span class="page__desc3 gj" style="margin-left: 10px;"><img style="width:16px;height:auto;margin-right: 5px;" alt="" src="<%=basePath%>/static/front/image/jingyan.png">${position.workingMonth }</span>
						</div>
					</div>
				</c:forEach>   
			</c:if>
			</div>
		</div>
	</div>
	<div id="zwsx" style="display:none;">
		<div class="weui-flex">
			<span style="margin-left: 10px;margin-top:10px;font-size: 14px;">工作经验</span>
		</div>
		<div class="weui-flex" style="margin-top: -5px;">
			<div class="weui-flex__item" style="margin:0px 15px;">
				<a href="javascript:;" 
				style=" width:23%;padding:1px;font-size:11px;color: #888;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" 
				class="weui-btn weui-btn_mini weui-btn_default gzjy">应届生</a>
				<a href="javascript:;" 
				style="width:23%;padding:1px;font-size:11px;color: #888;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" 
				class="weui-btn weui-btn_mini weui-btn_default gzjy">一年以上</a>
				<a href="javascript:;" 
				style="width:23%;padding:1px;font-size:11px;color: #888;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" 
				class="weui-btn weui-btn_mini weui-btn_default gzjy">2年以上相关经验</a>
				<a href="javascript:;" 
				style="width:23%;padding:1px;font-size:11px;color: #888;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" 
				class="weui-btn weui-btn_mini weui-btn_default gzjy">五年以上</a>
			</div>
		</div>
		<div class="weui-flex">
			<span style="margin-left: 10px;margin-top:5px;font-size: 14px;">工作性质</span>
		</div>
		<div class="weui-flex" style="margin-top: -5px;">
			<div class="weui-flex__item" style="margin:2px 15px;">
				<a href="javascript:;" 
				style=" width:23%;padding:1px;font-size:11px;color: #888;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" 
				class="weui-btn weui-btn_mini weui-btn_default gzxz">应届生</a>
				<a href="javascript:;" 
				style="width:23%;padding:1px;font-size:11px;color: #888;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" 
				class="weui-btn weui-btn_mini weui-btn_default gzxz">一年以上</a>
				<a href="javascript:;" 
				style="width:23%;padding:1px;font-size:11px;color: #888;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" 
				class="weui-btn weui-btn_mini weui-btn_default gzxz">2年以上相关经验</a>
				<a href="javascript:;" 
				style="width:23%;padding:1px;font-size:11px;color: #888;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" 
				class="weui-btn weui-btn_mini weui-btn_default gzxz">五年以上</a>
			</div>
		</div>
	</div>
	<input id="gzjy" type="hidden" >
	<input id="gzxz" type="hidden" >
	<div style="height:60px;"></div>
</body>
<%@include file="/WEB-INF/view/front/home/foot.jsp" %>
<script src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript">
$("#areaspan").select({
	title: "选择城市",
	items: ["合肥","武汉"]
});

$("#areaspan").bind("change",function(){
	var areaName = this.value;
	location.href = "<%=basePath%>/front/home/index.html?areaName="+areaName;
});

$("#search").bind("focus",function(){
	location.href="<%=basePath%>/front/home/toSearch.html";
});

$(".posi").bind("click",function(){
	var positionId = $(this).find("input[name='positionId']").val();
	location.href = "<%=basePath%>/front/home/toPositionDetail.html?positionId="+positionId;
});

$("#btn_search").bind("click",function(){
	layer.open({
		title:false,
		type: 1,
		closeBtn: 0,
		content: $("#zwsx"),
		area: ['100%', 'auto'],
		offset: '0px',
		btn: ['确定', '取消'],
		yes:function(index,layero){
			var gzjy = $("#gzjy").val();
			var gzxz = $("#gzxz").val();
			if(gzjy=="" && gzxz==""){
				return;
			}
			$(".posi").show();
			$(".posi").each(function(){
				if(!!gzjy){
					if($(this).find(".gj").text()!=gzjy){
						$(this).hide();
					}
				}
				if(!!gzxz){
					if($(this).find(".gx").text()!=gzxz){
						$(this).hide();
					}
				}
			});
			layer.close(index);
		}
	});
});

$(".gzjy").bind("click",function(){
	var isXz = $(this).hasClass("xz");
	if(isXz){
		$(this).removeClass("xz");
		$("#gzjy").val("");
	}
	if(!isXz){
		$(this).siblings().removeClass("xz");
		$(this).addClass("xz");
		$("#gzjy").val($(this).text());
	}
});

$(".gzxz").bind("click",function(){
	var isXz = $(this).hasClass("xz");
	if(isXz){
		$(this).removeClass("xz");
		$("#gzxz").val("");
	}
	if(!isXz){
		$(this).siblings().removeClass("xz");
		$(this).addClass("xz");
		$("#gzxz").val($(this).text());
	}
});
</script>
</html>