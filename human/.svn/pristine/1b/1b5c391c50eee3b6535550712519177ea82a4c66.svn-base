<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<title>我的授课班级</title>
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
    <link rel="stylesheet" href="<%=basePath %>/static/common/font/iconfont.css"  />
	<link rel="stylesheet" href="<%=basePath %>/static/xdfStudent/wechat/style/less.css">
	<link rel="stylesheet" href="<%=basePath %>/static/bootstrap/css/bootstrap.css">
</head>
<body>
  <c:if  test="${!empty jwCourceList}">
		<div class="main">
			<table class="table">
				<thead>
					<th style="width:35%">班号</th>
					<th style="width:30%">校区</th>
					<th style="width:25%">操作</th>
				</thead>
				<tbody class='alltable'>
				    <c:forEach var="jwCource" items="${jwCourceList }" varStatus="status">
						<tr>
							<td>${jwCource.sClassCode }</td>
							<td>${jwCource.sPrintAddress }</td>
							<td><input type="button" value="查询学员" onclick="serachStudents('${jwCource.sClassCode }')"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>		
		</div>
   </c:if>
   <c:if test="${empty jwCourceList}">
		<img style="width:100%" src="<%=basePath%>/static/xdfStudent/wechat/images/zanwu.jpg" alt="">
   </c:if>
</body>
<script type="text/javascript" src="<%=basePath%>/static/xdfStudent/wechat/js/main.js"></script>
<script type="text/javascript">
		var  deviceWidth = document.documentElement.clientWidth;
		if(deviceWidth >750) deviceWidth =750;
		document.documentElement.style.fontSize = deviceWidth /7.5+'px';
</script>	
</html>