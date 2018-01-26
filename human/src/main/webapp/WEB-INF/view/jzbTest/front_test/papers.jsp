<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="width: 100%; background-color: #F0F0F0;">
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
<!-- <meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" /> -->
<meta name="format-detection" content="telephone=no" />
<title>新东方入班水平测试</title>
	<link rel="stylesheet" href="<%=basePath %>/static/jzbTest/weixin/css/view2.css">
</head>
<body >
	<div class="main_body">
		<div style="padding-left: 10px; padding-right: 10px;">
			<div style="padding-top: 20px; font-weight: 700;">
				<div style="width: 100%;overflow: hidden;">
					<div class="main_table_head">姓名</div>
					<div class="main_table_head">年级</div>
					<div class="main_table_head">科目</div>
					<div class="main_table_head" style="width:20%;">测试结果</div>
					<div class="main_table_head">报名</div>
					<div class="main_table_head">错题</div>
				</div>
			</div>
			<div style="width: 100%;">
			<c:forEach items="${papers }" var="paper">
				<div style="width: 100%;overflow: hidden;">
					<div class="main_table_r">${paper.name }</div>
					<div class="main_table_r">${paper.gradeName }</div>
					<div class="main_table_r">${paper.subjectName }</div>
					<div class="main_table_r" style="width:20%;">
						<c:if test="${paper.status eq '2' }">
							超时
						</c:if>
						<c:if test="${paper.status ne '2' }">
							${paper.isPassName }
						</c:if>
					</div>
					<c:if test="${paper.isExistClass eq '0' }">
					<div class="main_table_r main_table_r_btn" onclick="bmClass('${paper.id }')">报名</div>
					</c:if>
					
					<c:if test="${paper.isExistClass eq '1' }">
					<div class="main_table_r">未配置</div>
					</c:if>
					
					<div class="main_table_r main_table_r_btn" onclick="viewError('${paper.id }')">错题</div>
				</div>
			</c:forEach>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function viewError(paperId){
	location.href = jsBasePath+ "/jzbTest/weixin/toErrorQusstion.html?paperId="+paperId;
}

function bmClass(paperId){
	location.href = jsBasePath+ "/jzbTest/weixin/toSelectClass.html?paperId="+paperId;
}
</script>
</html>