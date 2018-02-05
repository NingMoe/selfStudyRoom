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
			<form class="layui-form" id="editForm" action="" method="post" >
			  <input type="hidden" name="id" value="${info.id }">
				<div class="layui-form-item">
				   <label class="layui-form-label" style="width: 100px;">活动名称:</label>
					<div class="layui-input-inline" >
						<input type="text" id="activityName" name="activityName" autocomplete="off"  placeholder="请输入活动名称" lay-verify="required" class="layui-input" value="${info.activityName}">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"style="width: 100px;">所属部门:</label>
					<div class="layui-input-inline" >
						<input type="text" id="deptName" name="deptName" autocomplete="off"  placeholder="请输入所属部门" lay-verify="required" class="layui-input" value="${info.deptName}">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
				   <label class="layui-form-label" style="width: 100px;">开始时间:</label>
					<div class="layui-input-inline">
						<input  id="startTime" name="startTime"  placeholder="请输入开始时间" lay-verify="required" class="layui-input" value="${startTime}">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label"style="width: 100px;">结束时间:</label>
					<div class="layui-input-inline" >
						<input  id="endTime" name="endTime"   placeholder="请输入结束时间" lay-verify="required" class="layui-input" value="${endTime}">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
							
				<div class="layui-form-item">
				    <label class="layui-form-label"style="width: 100px;">文本1:</label>
					<div class="layui-input-inline">
						<input  id="text1" name="text1" autocomplete="off"  placeholder="请输入文本1"  class="layui-input" value="${info.text1}">												
					</div>	
					<div class="layui-input-inline">
						<input type="checkbox" name="text1Isneed" value="1" lay-filter="Isneed1" title="必填" <c:if test="${info.text1Isneed eq '1' }">checked="checked"</c:if>>											
					</div>							
				</div>
				
				<div class="layui-form-item">										
					<label class="layui-form-label"style="width: 100px;">文本2:</label>
					<div class="layui-input-inline" >
						<input  id="text2" name="text2" autocomplete="off"  placeholder="请输入文本2"  class="layui-input" value="${info.text2}">											
					</div>		
					<div class="layui-input-inline" >
						<input type="checkbox" name="text2Isneed" value="1" lay-filter="Isneed2" title="必填" <c:if test="${info.text2Isneed eq '1' }">checked="checked"</c:if>>											
					</div>							
				</div>
				
				
				<div class="layui-form-item">
				    <label class="layui-form-label"style="width: 100px;">文本3:</label>
					<div class="layui-input-inline" >
						<input  id="text3" name="text3" autocomplete="off"  placeholder="请输入文本3"  class="layui-input" value="${info.text3}">												
					</div>	
					<div class="layui-input-inline" >
						<input type="checkbox" name="text3Isneed" value="1" lay-filter="Isneed3" title="必填" <c:if test="${info.text3Isneed eq '1' }">checked="checked"</c:if>>											
					</div>						
				</div>
				
				<div class="layui-form-item">										
					<label class="layui-form-label"style="width: 100px;">文本4:</label>
					<div class="layui-input-inline" >
						<input  id="text4" name="text4" autocomplete="off"  placeholder="请输入文本4"  class="layui-input" value="${info.text4}">											
					</div>		
					<div class="layui-input-inline" >
						<input type="checkbox" name="text4Isneed" value="1" lay-filter="Isneed4" title="必填" <c:if test="${info.text4Isneed eq '1' }">checked="checked"</c:if>>											
					</div>							
				</div>
				
				<div class="layui-form-item">
				    <label class="layui-form-label"style="width: 100px;">收费限制:</label>					
					<div class="layui-input-inline">
						<input type="checkbox" name="tollLimit" value="1" lay-filter="tollLimit" title="单个手机号只能购买一次" <c:if test="${info.tollLimit eq '1' }">checked="checked"</c:if>>											
					</div>							
				</div>
				
				<div id="productRecord">
				  <c:forEach var="product" items="${prodList }" varStatus="status">
						<div class="layui-form-item" >
						    <input type="hidden" name="productList.id" value="${product.id}">										
							<label class="layui-form-label"style="width: 100px;">商品名称:</label>
							<div class="layui-input-inline" >
								<input   name="productList.name"  autocomplete="off"  placeholder="请输入商品名称"  class="layui-input" value="${product.name}">											
							</div>
							<label class="layui-form-label" style="width: 60px;">商品单价:</label>									
							<div class="layui-input-inline" style="width: 80px;">
								<input  type="number"  name="productList.price" autocomplete="off"  placeholder="单价/元"  class="layui-input" lay-verify="number" value="${product.price}" <c:if test="${product.saleTotal ne 0 }">readonly="readonly"</c:if>>											
							</div>
							<label class="layui-form-label" style="width: 60px;">商品数量:</label>	
							<div class="layui-input-inline" style="width: 80px;">
								<input  type="number"  name="productList.total" autocomplete="off"  placeholder="物品数量"  onchange="checkNumber('${product.id}',this.value);"  class="layui-input" lay-verify="number" value="${product.total}">											
							</div>
							<c:if test="${status.index eq 0}">
								<div class="layui-input-inline">
								   <input type="button"  class="layui-btn" onclick="addRecord(this,event);" value="新增">
								</div>
							</c:if>					
						</div>
					</c:forEach>
				</div>
																											
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>
<!-- 商品html专用区域 -->
<div id="copy_productRecord" style="display: none;">
              <div class="layui-form-item">
                    <label class="layui-form-label"style="width: 100px;">商品名称:</label>
					<div class="layui-input-inline" >
						<input  name="productList.name"  autocomplete="off"  placeholder="请输入商品名称"  class="layui-input">											
					</div>
					<label class="layui-form-label" style="width: 60px;">商品单价:</label>		
					<div class="layui-input-inline" style="width: 80px;">
						<input  type="number" name="productList.price" autocomplete="off"  placeholder="单价/元"  class="layui-input" lay-verify="number">											
					</div>	
					<label class="layui-form-label" style="width: 60px;">商品数量:</label>
					<div class="layui-input-inline" style="width: 80px;">
						<input  type="number" name="productList.total" autocomplete="off"  placeholder="物品数量"  class="layui-input" lay-verify="number">											
					</div>
					<div class="layui-input-inline">
					   <input type="button"  class="layui-btn" onclick="removeRecord(this);"  value="删除">
					</div>	
		      </div>
</div>		
<!-- 商品html专用区域 -->					
</body>
<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.serializeJson2.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/activity/edit.js"></script>
</html>