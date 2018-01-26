<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="width: 100%; background-color: #F0F0F0;">
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<title>入班测试题目预览</title>
<link rel="stylesheet"
	href="<%=basePath%>/static/jzbTest/weixin/css/view6.css">
</HEAD>
<style>
.choose{
background-color: #ff9800;color: #fff;
}
.layui-input-block {
    margin-left: 85px;
    min-height: 36px;
}
</style>
<BODY>
	<div class="main_body">
		<input id="sort" type="hidden" value="${jq.sort }">
		<input id="totalNum" type="hidden" value="${totalNum}">
		<input  type="hidden" id="qClasstype" value="${jq.qClasstype }"/>
		<input type="hidden" id="qMonth" value="${jq.qMonth}"/>
		<input type="hidden" id="qDept" value="${jq.qDept}"/>
		<input type="hidden" id="qGrade" value="${jq.qGrade }"/>
		<input type="hidden" id="qSubject"  value="${jq.qSubject }"/>
		<input type="hidden" id="qCode"  value="${question.qCode }"/>
		<c:if test="${error ne '1' }">
			<div
				style="padding-left: 15px; padding-right: 15px; margin-top: 15px; overflow: hidden; background-color: #fff;">
				<div
					style="width: 100%; color: #ff9800; font-size: 1.1rem; padding-top: 15px;">${question.qCode }<br>题目${jq.sort +1}
				</div>
				<div
					style="width: 100%; min-height: 5rem; white-space: normal; word-break: break-all; word-wrap: break-word; padding-top: 5px; padding-bottom: 15px; letter-spacing: 1px;">
					${question.qMainDesc }</div>
			</div>


			<div style="">
			<c:forEach items="${question.questions }" var="q"  varStatus='status'>
				<div
					style="margin-top: 20px; padding-left: 30px; padding-right: 30px;"
					class="blq">
					<div style="width: 100%; background-color: #fff;">
						<input class="stuAnswer" type="hidden" value=""> <input
							class="questionId" type="hidden" value="${q.id }">
						<div
							style="padding-top: 10px; padding-left: 5px; padding-right: 5px; white-space: normal; word-break: break-all; word-wrap: break-word;">
							${status.index+1}.${q.qContent }</div>
						<div
							style="padding-left: 15px; padding-right: 15px; white-space: normal; word-break: break-all; word-wrap: break-word;">
							<div style="width: 100%; margin-top: 5px;">
							<!-- 			是否是填空题 -->
							<c:if test="${q.isTk eq '2'}">
								<c:forEach items="${q.tkAnswerList }" var="tk" varStatus='s'>
								<div class="layui-form-item layui-form-text" style="padding-bottom:15px;">
									<label class="layui-form-label" style="width: 15%">答案${s.index+1}</label>
									<div class="layui-input-block">
										<textarea placeholder="" style="width: 100%;  min-height: 50px;"  readonly="readonly" name="qContent" class="layui-textarea">${tk}</textarea>
									</div>
								</div>	
								</c:forEach>
							</c:if>
							<c:if test="${q.isTk eq '1'}">
								<c:forEach items="${q.answers }" var="a">
								<c:if test="${!empty a.aContent }">
									<div
										style="padding-bottom: 10px; padding-left: 5px; padding-right: 5px;">
										<c:if test="${a.isCorrect eq '1' }">
											<div id="answer${a.id }" class="main_body_ans choose" 
											onclick="removeactivity('${a.id}',this)">${a.xh }.
											${a.aContent }</div>
										</c:if>	
										<c:if test="${a.isCorrect ne '1' }">
											<div id="answer${a.id }" class="main_body_ans " 
											onclick="removeactivity('${a.id}',this)">${a.xh }.
											${a.aContent }</div>
										</c:if>	
									</div>
								</c:if>
								</c:forEach>
							</c:if>	
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			</c:if>
		</div>
	<c:if test="${error eq '1' }">
		<p>此题不存在</p>
	</c:if>
		<div style="height: 3.5rem;"></div>
		<div
			style="width: 100%; position: fixed; bottom: 0px; background-color: #fff; height: 3rem; overflow: hidden;">
			<c:if test="${jq.sort ne '0' and look_total ne '1'}">
				<div style="float: left; width: 25%;">
					<img style="height: 3rem;" alt=""
						src="<%=basePath%>/static/jzbTest/weixin/img/last.png"
						id="lastImg">
				</div>
			</c:if>
			<c:if test="${jq.sort eq '0' or look_total eq '1'}">
				<div style="float: left; width: 25%;">
					<img style="height: 3rem;" alt="" src="">
				</div>
			</c:if>
			<div style="float: left; width: 50%; text-align: center;">
				 <div style="padding-top:0.7em;">
					<input id="code" style="height:2em;background-color: #fff;border:1px solid #ddd;box-shadow:none" value="${q_code }">
				</div>
			</div>
			<c:if test="${jq.sort+1 ne totalNum and look_total ne '1'}">
				<c:if test="${show ne '1' }">
				<div style="float: left; width: 25%;">
					<img style="height: 3rem;" alt=""
						src="<%=basePath%>/static/jzbTest/weixin/img/next.png"
						id="nextImg">
				</div>
				</c:if>
			</c:if>
			<%-- <c:if test="${look_total eq '1' }">
				 <div style="float: left; width: 25%;padding-top:1em;">
				 	<button style="height: 1.5rem;" class="layui-btn layui-btn-mini " id="panel" id="res">返回</button>
				</div> 
			</c:if> --%>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>/static/jzbTest/cookie_util.js"></script>
	<script type="text/javascript">
		$("#lastImg").click(function() {
			var qMonth=$("#qMonth").val();
			var qClasstype=$("#qClasstype").val();
			var qDept=$("#qDept").val();
			var qGrade=$("#qGrade").val();
			var qSubject=$("#qSubject").val();
			var dept=qDept.split(",");
			location.href = jsBasePath+"/jzbTest/jpConfig/view_phone.html?qMonth="+qMonth+"&qClasstype="+qClasstype
			+"&qDept="+qDept+"&qGrade="+qGrade+"&qSubject="+qSubject+"&sort=${jq.sort-1}"+"&qDept="+dept[0]+"&isPc=no";					
			
		});
		
		$("#nextImg").click(function() {
			var qMonth=$("#qMonth").val();
			var qClasstype=$("#qClasstype").val();
			var qDept=$("#qDept").val();
			var qGrade=$("#qGrade").val();
			var dept=qDept.split(",");
			var qSubject=$("#qSubject").val();
			var qCode=$("#qCode").val();
				location.href = jsBasePath+"/jzbTest/jpConfig/view_phone.html?qMonth="+qMonth+"&qClasstype="+qClasstype
				+"&qDept="+qDept+"&qGrade="+qGrade+"&qSubject="+qSubject+"&sort=${jq.sort+1}"+"&qDept="+dept[0]+"&isPc=no";					
		});
		$("#code").blur(function(){
			var qMonth=$("#qMonth").val();
			var qClasstype=$("#qClasstype").val();
			var qDept=$("#qDept").val();
			var qGrade=$("#qGrade").val();
			var qSubject=$("#qSubject").val();
			var qCode=$("#code").val();
			if(qCode==""){
				layer.alert("请输入题目编号",{icon:2});
				return false;	
						}
			var dept=qDept.split(",");
				location.href = jsBasePath+"/jzbTest/jpConfig/view_phone.html?qMonth="+qMonth+"&qClasstype="+qClasstype
				+"&qDept="+qDept+"&qGrade="+qGrade+"&qSubject="+qSubject+"&qDept="+dept[0]+"&qCode="+qCode+"&isSearch=2"+"&isGoBack=1&&sort=${jq.sort}&s_code=${question.qCode}";	
		})
		$("#panel").click(function() {
			var qMonth=$("#qMonth").val();
			var qClasstype=$("#qClasstype").val();
			var qDept=$("#qDept").val();
			var qGrade=$("#qGrade").val();
			var qSubject=$("#qSubject").val();
			var qDept=$("#qDept").val();
			var dept=qDept.split(",");
			location.href = jsBasePath + "/jzbTest/jpConfig/view_phone.html?"+
			"&qGrade="+qGrade+"&qSubject="+qSubject+"&qClasstype="+qClasstype+"&qMonth="+qMonth+"&qDept="+dept[0];					
		});
	</script>
</body>
</html>