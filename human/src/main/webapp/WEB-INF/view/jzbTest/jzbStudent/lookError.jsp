<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
<style type="text/css">
	input::-webkit-input-placeholder {text-align: left; } 
	input:-moz-placeholder { text-align: left; } 
	input:-ms-input-placeholder { text-align: left; } 
	input{ border:none!important; }
</style>
</head>
<body>
    <c:if  test="${!empty jpqList}">
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post" >
			   <c:forEach var="jpq" items="${jpqList }" varStatus="status">	
			   		<c:if  test="${jpq.qType eq '1'}">	
						<div class="layui-form-item">
							<label class="layui-form-label">题干:</label>
							<div class="layui-input-block">
								${jpq.qContent }
							</div>
						</div>	
						<div class="layui-form-item">
							<label class="layui-form-label">题目编码:</label>
							<div class="layui-input-block">
								 <input type="text" class="layui-input" value="${jpq.qCode }">
							</div>
						</div>	
									
						<div class="layui-form-item">
						<label class="layui-form-label">正确答案:</label>
							<div class="layui-input-block">
							    <input type="text" class="layui-input" value="${jpq.xh1 }.${jpq.aContent1 }">
							    <c:if test="${!empty jpq.aImg1 }">
									<img src="${fileurl }${jpq.aImg1}">
								</c:if>						
							</div>
						</div>
						
						<div class="layui-form-item">
						<label class="layui-form-label">你的答案:</label>
							<div class="layui-input-block">
							   <input type="text" class="layui-input" style="color:red;" value="${jpq.xh2 }.${jpq.aContent2 }">
							   <c:if test="${!empty jpq.aImg2 }">
									<img src="${fileurl }${jpq.aImg2}">
								</c:if>					
							</div>
						</div>
					  </c:if>
					  <c:if  test="${jpq.qType eq '2'}">
					       <div class="layui-form-item">
							<label class="layui-form-label">短文:</label>
								<div class="layui-input-block">
									${jpq.qMainDesc }
								</div>
							</div>
					    <c:forEach var="jpqError" items="${jpq.errorList }" varStatus="status">
							<div class="layui-form-item">
							<label class="layui-form-label">题干:</label>
								<div class="layui-input-block">
									${jpqError.qContent }
								</div>
							</div>	
										
							<div class="layui-form-item">
							<label class="layui-form-label">正确答案:</label>
								<div class="layui-input-block">
								    <input type="text" class="layui-input" value="${jpqError.xh1 }.${jpqError.aContent1 }">						
								</div>
							</div>
							
							<div class="layui-form-item">
							<label class="layui-form-label">错误答案:</label>
								<div class="layui-input-block">
								   <input type="text" class="layui-input" style="color:red;" value="${jpqError.xh2 }.${jpqError.aContent2 }">					
								</div>
							</div>
						</c:forEach>
					  </c:if>
				</c:forEach>			
			</form>
		</div>	
	</c:if>	
	<c:if  test="${empty jpqList}">
	  <p style="text-align:center;margin-top:25%">无错题</p>
	</c:if>
</body>
</html>