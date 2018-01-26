<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
<link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<script src="<%=basePath %>/static/raty/lib/jquery.raty.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>/static/viewer/viewer.min.css"/>
<script src="<%=basePath%>/static/viewer/viewer.min.js"></script>
<style type="text/css">
.layui-form-item {
	margin-bottom: 0px;
	margin-right: 20px;
}

.inputRow {
	padding: 0px 10px;
}

.row {
	margin: 0px 10px 5px 10px;
}

.tjspan {
	margin: 0;
	font-size: 12px;
	color: #999;
	line-height: 22px;
}

.div_left {
	width: 80%;
	border: 1px solid #b0dbc0;
	border-radius: 5px;
	padding: 10px;
	margin: 5px;
	font-size: 16px;
	border-radius: 5px;
	padding: 10px;
	margin: 5px;
}

.div_right {
	width: 80%;
	border: 1px solid #eee;
	border-radius: 5px;
	padding: 10px;
	margin: 5px;
	float: right;
	font-size: 16px;
}

li {
	list-style-type: none;
}

.layer-photos-demo img {
	position: relative;
	display: inline-block;
	width: 77px;
	height: 77px;
	margin: 5px 5px 5px 5px;
	border: 1px solid #D9D9D9;
	background: #fff no-repeat center;
	background-size: cover;
}
</style>
<body >
	<div class="appForm">
		<input type="hidden"  name="baseId" value="${fb.id}"/>
		<div class="row" style="border-bottom: 1px solid #F0F0F0;">
			<strong>${fb.title}</strong>
		<p class="text-left tjspan" >提交于:<fmt:formatDate value='${fb.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
		</div>
		<c:forEach var="detail" items="${fb.fbdetail}">
		 <c:if test="${detail.type==0}">
		<div class="row">
		<div class="div_left">
		${detail.desc}
		<c:if test="${detail.fbpPhoto !=null or fn:length(detail.fbpPhoto)>0}">
			<div class="img-list layer-photos-demo"  id="imglist_${detail.id}" style="margin-bottom: 0px;" >
			<c:forEach var="photo" items="${detail.fbpPhoto}" varStatus="st">
				<img layer-pid="photo.id" onclick="showImg(this,${st.index});" layer-src="${filePath}${photo.url}" src="${filePath}${photo.url}" alt="">
			</c:forEach>
			</div>
		</c:if>
		<p class="text-left tjspan"><c:if test="${fb.isHide==1}">匿名</c:if>
							<c:if test="${fb.isHide!=1}">${detail.operUser}</c:if>&nbsp;反馈时间:<fmt:formatDate value='${fb.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
		</div>
		</div>
		</c:if>
		<c:if test="${detail.type==1}">
		<div class="row" style="text-align: right;">
		<div class="div_right">
		<p class="text-left"  style="margin: 0;">${detail.desc}</p>
		<p class="text-right tjspan" >${detail.operUser}&nbsp;回复时间:<fmt:formatDate value='${fb.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
		</div>
		</div>
		</c:if>
		</c:forEach>
		<c:if test="${fb.closeType==1}">
		<div class="row" style="text-align: right;">
		<div class="div_right">
		<p class="text-left"  style="margin: 0;">${fb.closeDesc}</p>
		<p class="text-right tjspan" >${fb.userName}&nbsp;关闭于:<fmt:formatDate value='${fb.closeTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
		</div>
		</div>
		</c:if>
		<c:if test="${fb.closeType==0}">
		<div class="row">
		<div class="div_left">
		<div class="layui-inline"  style="width: 100%;margin-bottom: 10px;">
		<font style="font-size: 12px;line-height: 22px;color: #999;">评分:</font>
		<div class="layui-input-inline"  id="star" data-score="${fb.score}">
		</div>
		</div>
			<p class="text-left"> ${fb.closeDesc}</p>
		<p class="text-left tjspan"><c:if test="${fb.isHide==1}">匿名</c:if>
							<c:if test="${fb.isHide!=1}">${fb.userName}</c:if>&nbsp;关闭于:<fmt:formatDate value='${fb.closeTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
		</div>
		</div>
		</c:if>
	</div>
	<script type="text/javascript">
	function  showImg(obj,index){
		$(obj).parent().viewer();
	}
		$('#star').raty({
			readOnly : true,
			score : function() {
				return $(this).attr('data-score');
			}
		});
    </script>
</body>
</html>