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
	<form class="layui-form"  id="editForm" action="" method="post">
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
		<p class="text-left tjspan">
		<c:if test="${fb.isHide==1}">匿名</c:if>
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
	<div class="row inputRow" style="border-top: 1px solid #F0F0F0;padding-top: 10px;"><textarea  lay-verify="required"  name="desc" placeholder="请输入反馈内容" class="layui-textarea" ></textarea></div>						
			<div class="row"  style="text-align: center;"><button class="layui-btn" lay-submit  lay-filter="sub">回复反馈</button>
					</div>		
			</form>
	</div>
	<script type="text/javascript">
	function  showImg(obj,index){
		$(obj).parent().viewer();
	}
	
	 layui.use(['form'], function(){
	    	var form = layui.form()
	    	  ,layer = layui.layer;
						form.on('submit(sub)', function(data,event){
				    		 var index = layer.load(3, {shade: [0.3]});
				  			$.post(jsBasePath+'/manager/lookFeedbackConfig/fbFeedBack.html',$("#editForm").serializeArray(),function(data,status){
				  				layer.close(index); 
				  			  if(!data.flag){
				  				  layer.alert(data.msg,{icon:2});
				  			  }else{
				  				  layer.alert(data.msg,{icon:1},function(){
				  					parent.initTable();
				  					  closeFrame();
				  				  });
				  			  }
				  			},"json");
				  			return false;
					});
			});
    </script>
</body>
</html>