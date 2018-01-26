<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<title>我的学员列表</title>
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
    <link rel="stylesheet" href="<%=basePath %>/static/common/font/iconfont.css"  />
	<link rel="stylesheet" href="<%=basePath %>/static/xdfStudent/wechat/style/less.css">
	<link rel="stylesheet" href="<%=basePath %>/static/bootstrap/css/bootstrap.css">
	<style>
		p{margin:0!important}
	</style>	
</head>
<body>
		<c:if  test="${!empty xsfList}">
				<div class="main2">
					<table class="table">
						<thead>
							<th style="width:50%">姓名/学校</th>  	
							<th style="width:50%">手机号</th>
						</thead>
						<tbody>
						  <c:forEach var="xsf" items="${xsfList }" varStatus="status">
								<tr>
									<td>
										<p>${xsf.studentName }</p>
										<c:if  test="${!empty xsf.schoolName}">
											<p>(${xsf.schoolName })</p>
										</c:if>
									</td>
									<td>
										<p>${xsf.telephone1 }</p>
										<p>${xsf.telephone2 }</p>
									</td>
								</tr>
						  </c:forEach>
						</tbody>
					</table>
				</div>
		</c:if>
		<c:if  test="${empty xsfList}">
			<p style="text-align:center;margin-top:25%">该班下暂无学员</p>
		</c:if>
</body>
 <script type="text/javascript">
		var  deviceWidth = document.documentElement.clientWidth;
		if(deviceWidth >750) deviceWidth =750;
		document.documentElement.style.fontSize = deviceWidth /7.5+'px';
 </script>
</html>