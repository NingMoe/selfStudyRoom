<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
		<title></title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	</head>
	<body style="padding:20px;">
			<div class="layui-form">
			<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">基本信息</legend>
				<input type="hidden" id="companyId" name="companyId" value="${company.companyId }">
				<div class="layui-form-item">
					<label class="layui-form-label">学校名称</label>
					<div class="layui-input-inline">
						<input type="text" id="companyName" name="companyName" lay-verify="required" value="${company.companyName }" class="layui-input">
					</div>
					
					<label class="layui-form-label">学校官网</label>
					<div class="layui-input-inline">
						<input type="text" id="schoolSite" name="schoolSite" value="${company.schoolSite }" class="layui-input">
					</div>
					
					<label class="layui-form-label">招聘官网</label>
					<div class="layui-input-inline">
						<input type="text" id="recruitSite" name="recruitSite" value="${company.recruitSite }" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">咨询电话</label>
					<div class="layui-input-inline">
						<input type="text" id="servicePhone" name="servicePhone" value="${company.servicePhone }" class="layui-input">
					</div>
					
					<label class="layui-form-label">招聘微信</label>
					<div class="layui-input-inline">
						<input type="text" id="wechatNo" name="wechatNo" value="${company.wechatNo }" class="layui-input">
					</div>
					
					<label class="layui-form-label">招聘邮箱</label>
					<div class="layui-input-inline">
						<input type="text" id="recruitEmail" name="recruitEmail" value="${company.recruitEmail }" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">生日祝福</label>
					<div class="layui-input-inline">
    					<select name="isBirthWish" id="isBirthWish" style="width: 150px;">
    						<option value="1"
    						<c:if test="${company.isBirthWish eq 1 }">selected="selected"</c:if>
    						>是</option>
    						<option value="0"
    						<c:if test="${company.isBirthWish eq 0}">selected="selected"</c:if>
    						>否</option>
      					</select>
    				</div>
					
					<label class="layui-form-label">入职祝福</label>
					<div class="layui-input-inline">
    					<select name="isJoinWish" id="isJoinWish" style="width: 150px;">
    						<option value="1"
    						<c:if test="${company.isJoinWish eq 1 }">selected="selected"</c:if>
    						>是</option>
    						<option value="0"
    						<c:if test="${company.isJoinWish eq 0}">selected="selected"</c:if>
    						>否</option>
      					</select>
    				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">学校所在地</label>
					<div class="layui-input-inline">
						<select name="provinceCode" id="provinceCode" lay-filter="provinceCode" style="width:150px;" lay-verify="required">
		    				<option value="">选择省份</option>
		    				<c:forEach items="${provinces }" var="pro">
		    					<option value="${pro.id }" 
		    					<c:if test="${company.provinceCode eq pro.id}">
		    					selected="selected"
		    					</c:if>
		    					>${pro.areaName }</option>
		    				</c:forEach>
	      				</select>
	      			</div>
	      			<div class="layui-input-inline">	
	      				<input type="hidden" id="cityId" value="${company.cityCode }">
	      				<select name="cityCode" id="cityCode" style="width:150px;" lay-verify="required">
		    				<option value="">选择城市</option>
	      				</select>
	      			</div>
	      			<div class="layui-input-inline">	
	      				<input type="text" id="addr" name="addr" value="${company.addr }" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">学校简介</label>
				    <div class="layui-input-block">
				      <textarea class="layui-textarea layui-hide" id="profile" name="profile" lay-verify="content" id="profile">
				      ${company.profile }
				      </textarea>
				    </div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">视频介绍</label>
					<div class="layui-input-inline">
						<input type="text" id="schoolVideo" name="schoolVideo" value="${company.schoolVideo }" class="layui-input">
					</div>
					 <div class="layui-form-mid layui-word-aux">请复制腾讯视频链接地址</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">图片上传</label>
					
					<div class="layui-input-inline" style="width:265px">
						<input type="file" id="file1" name="file1" lay-type="file" lay-type="images" 
						class="layui-upload-file" lay-title="选择学校形象气质图(PC)" style="width:200px"> 
						<button id="file1Button" <c:if test="${empty company.temperamentPcImage}">style="display:none;"</c:if>
						 class="layui-btn layui-btn-small delPic"><i class="layui-icon"></i></button>
						<img <c:if test="${empty company.temperamentPcImage}">style="display:none;"</c:if>
						 width=255px height=200px id="file1Img"  
						 <c:if test="${!empty company.temperamentPcImage}">src="${fileurl }${company.temperamentPcImage }"</c:if>/>
					</div>
					<div class="layui-input-inline" style="width:265px">
						<input type="file" id="file2" name="file2" lay-type="file" lay-type="images"  
						class="layui-upload-file" lay-title="选择学校形象气质图(移动)" style="width:200px"> 
						<button id="file2Button" <c:if test="${empty company.temperamentMobileImage}">style="display:none;"</c:if>
						 class="layui-btn layui-btn-small delPic"><i class="layui-icon"></i></button>
						<img <c:if test="${empty company.temperamentMobileImage}">style="display:none;"</c:if> 
						width=262px height=200px id="file2Img" 
						<c:if test="${!empty company.temperamentMobileImage}">src="${fileurl }${company.temperamentMobileImage }"</c:if>/>
					</div>
					<div class="layui-input-inline" style="width:265px">
						<input type="file" id="file3" name="file3" lay-type="file" lay-type="images" 
						class="layui-upload-file" lay-title="选择招聘微信二维码图片" style="width:200px"> 
						<button id="file3Button" <c:if test="${empty company.wechatImage}">style="display:none;"</c:if>
						 class="layui-btn layui-btn-small delPic"><i class="layui-icon"></i></button>
						<img <c:if test="${empty company.wechatImage}">style="display:none;"</c:if>  
						width=255px height=200px id="file3Img" 
						<c:if test="${!empty company.wechatImage}">src="${fileurl }${company.wechatImage }"</c:if>/>
					</div>
				</div>
				</fieldset>
				<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">高级接口</legend>
				<div class="layui-form-item">
					<label class="layui-form-label">appId</label>
					<div class="layui-input-inline">
						<input type="text" id="appid" name="appid" value="${company.appid }" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">appSecret</label>
					<div class="layui-input-inline">
						<input type="text" id="password" name="password" value="${company.password }" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="ced">保存</button>
					</div>
				</div>
				</fieldset>
			</div>
		<script type="text/javascript" src="<%=basePath %>/static/manager/js/company_editDetail.js"></script>
		<script>
		</script>
	</body>
</html>