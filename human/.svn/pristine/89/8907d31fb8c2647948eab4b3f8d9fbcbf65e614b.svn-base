<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
		<title>首页配置</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	</head>
	<body>
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post" >
			 <input type="hidden" name="id"  id="jzbMainConfigId" value="${jzbMainConfig.id}">
			 <input id="headImg" type="hidden" name="headImg" value="${jzbMainConfig.headImg}">
				<div class="layui-form-item">
				 <label class="layui-form-label">首页图:<font color="red">*</font></label>
					<div class="layui-input-block">
					    <c:if  test="${empty jzbMainConfig.headImg}">
						  <img id="photoImg" style="width:400px;height:180px;" src="<%=basePath%>/static/front/image/photo.jpg"/>
						  <input class="ulbstyle" type="file" name="photo" id="photo" />
				 		</c:if> 
				 		<c:if  test="${!empty jzbMainConfig.headImg}">
						   <img id="photoImg" style="width:400px;height:180px;" src="${filepath }${jzbMainConfig.headImg }"/>
						   <input class="ulbstyle" type="file" name="photo" id="photo" />
				 		</c:if>
					</div>
				</div>
							
				<div class="layui-form-item">
				<label class="layui-form-label">首页文字:<font color="red">*</font></label>
					<div class="layui-input-block">
						<textarea  id="content" name="content" placeholder="多行输入" class="layui-textarea" >${jzbMainConfig.content }</textarea>
					</div>
				</div>	
							
				<div class="layui-form-item">
				<label class="layui-form-label">底部备注:<font color="red">*</font></label>
					<div class="layui-input-block">
					    <input type="text" id="remark" name="remark" autocomplete="off"  placeholder="单行输入" lay-verify="required" class="layui-input" value="${jzbMainConfig.remark }">						
					</div>
				</div>
																				
				<div class="layui-form-item">
					<div class="layui-input-block">					
						<button  type="button"  class="layui-btn" style="margin:0 auto;text-align:cente;" onclick="save();">保存</button>
					</div>
				</div>
			</form>
		</div>		
	</body>
<script type="text/javascript" src="<%=basePath %>/static/jzbTest/jzbMainConfig/save.js"></script>
</html>