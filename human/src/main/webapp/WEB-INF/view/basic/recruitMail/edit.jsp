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
	<body>
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post">
			<input type="hidden" name="id" value="${recruitMail.id }" id="recruitMailId">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">机构名称</label>
					<div class="layui-input-inline">
						<select name="hrCompanyId" id="hrCompanyId" lay-verify="required" style="width: 150px;">
		    				<option value="">请选择</option>
		    				<c:forEach items="${companys }" var="company">
		    					<option value="${company.companyId }" <c:if test="${company.companyId eq recruitMail.hrCompanyId}">selected="selected"</c:if>>${company.companyName }</option>
		    				</c:forEach>
      					</select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">邮箱类型</label>
					<div class="layui-input-inline">
						<select name="mailType" id="mailType" lay-filter="mailType" style="width: 150px;" lay-verify="required">
		    				<option value="">请选择</option>
		    				<option value="1" <c:if test="${recruitMail.mailType eq '1'}">selected="selected"</c:if>>接收</option>
		    				<option value="2" <c:if test="${recruitMail.mailType eq '2'}">selected="selected"</c:if>>发送</option>
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
						
																			
				 <c:if test="${recruitMail.mailType eq '1'}">   
				　　  <div class="layui-form-item accept" style="display:inline;"> 
				　</c:if>
				  <c:if test="${recruitMail.mailType eq '2'}">   
				　　  <div class="layui-form-item accept" style="display:none;"> 
				　</c:if>
					<label class="layui-form-label" style="width: 150px;">Exchange服务器地址</label>
					<div class="layui-input-inline">
						<input type="text" name="exchangeMailServerHost" id="exchangeMailServerHost"  placeholder="例如:mailbj.xdf.cn" autocomplete="off" class="layui-input" value="${recruitMail.exchangeMailServerHost }">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>	
						
				 <c:if test="${recruitMail.mailType eq '1'}">   
				　　  <div class="layui-form-item send" style="display:none;"> 
				　</c:if>
			     <c:if test="${recruitMail.mailType eq '2'}">   
				　　  <div class="layui-form-item send" style="display:inline;"> 
				　</c:if>				  
				   <label class="layui-form-label" style="width: 150px;">发件服务器地址</label>
					<div class="layui-input-inline">
						<input type="text" name="sendMailServerHost" id="sendMailServerHost"   placeholder="例如:mailbj.xdf.cn" autocomplete="off" class="layui-input" value="${recruitMail.sendMailServerHost }">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
							
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">用户账号</label>
					<div class="layui-input-inline">
					<input type="text" id="mailUser" name="mailUser" lay-verify="required" placeholder="例如:liuwei63" autocomplete="off" class="layui-input" value="${recruitMail.mailUser }">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">用户密码</label>
					<div   class="layui-input-inline">
					<input type="password" id="mailPassword" name="mailPassword" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${recruitMail.mailPassword }">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">域名</label>
					<div class="layui-input-inline">
					<input type="text" id="domain"  name="domain"  lay-verify="required" placeholder="请输入域名" autocomplete="off" class="layui-input" value="${recruitMail.domain }">					
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button type="button" class="layui-btn" id="tj">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>		
	</body>
<script type="text/javascript" src="<%=basePath %>/static/basic/js/recruitMail/edit.js"></script>
</html>