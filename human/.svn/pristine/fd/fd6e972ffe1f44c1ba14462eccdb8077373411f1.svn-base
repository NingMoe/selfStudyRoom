<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="width: 100%;background-color: #F0F0F0;">
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="format-detection" content="telephone=no"/>
	<title>入班测试题目预览</title>
</HEAD>
<style>
.choose{
background-color: #ff9800;color: #fff;
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
<%-- 		<c:if test="${show ne '1' }"> --%>
		<div style="padding-left: 15px; padding-right: 15px; margin-top:15px; overflow: hidden; background-color: #fff;">
			<div style="width: 100%;white-space:normal;min-height:5rem; word-break:break-all; word-wrap:break-word; padding-top:5px; padding-bottom: 15px;letter-spacing:1px;">
				${question.qCode }<br>题目${jq.sort+1 }</br>${question.qMainDesc }
			</div>
		</div>
		<div>
		
			 <c:if test="${question.isTk eq '2'}">
				<c:forEach items="${q.tkAnswerList }" var="tk">
				    <label class="layui-form-label" style="width: 15%">答案${s.index+1}</label>
					<div class="layui-input-block">
						<textarea placeholder="" style="width: 80%;height:25px"  name="qContent" readonly="readonly" class="layui-textarea">${tk}</textarea>
					</div>
				</c:forEach>
			</c:if> 
			<c:if test="${question.isTk eq '1'}">				
			<c:forEach items="${question.answers }" var="a">
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
		<div style="height: 3.5rem;"></div>
		<div style="width:100%; position: fixed; bottom:0px; background-color: #fff; height: 3rem; overflow: hidden;">
		<c:if test="${jq.sort ne '0' }">
		<div style="float: left;width: 25%;"><img style="height: 3rem;" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/last.png" id="lastImg"></div>
		</c:if>
		<c:if test="${jq.sort eq '0' }">
		<div style="float: left;width: 25%;"><img style="height: 3rem;" alt="" src=""></div>
		</c:if>
			<div style="float: left;width: 50%; text-align: center;">
				 <div style="padding-top:0.7em;">
					<input id="code" style="height:2em;background-color: #fff;border:1px solid #ddd;box-shadow:none" value="${q_code}">
				</div>
			</div>
			<c:if test="${jq.sort+1 ne totalNum }">
				<div style="float: left;width: 25%;"><img style="height: 3rem;" alt="" src="<%=basePath %>/static/jzbTest/weixin/img/next.png" id="nextImg"></div>			
			</c:if>
				<c:if test="${look_total eq '1' }">
				 <div style="float: left; width: 25%;padding-top:1em; padding-left:10px">
				 	<button style="height: 1.5rem;" class="layui-btn layui-btn-mini " id="panel" id="res">返回</button>
				</div> 
			</c:if>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>/static/jzbTest/cookie_util.js"></script>
	<script type="text/javascript">
		$("#lastImg").click(function(){
			var qMonth=$("#qMonth").val();
			var qClasstype=$("#qClasstype").val();
			var qDept=$("#qDept").val();
			alert(qDept)
			var qGrade=$("#qGrade").val();
			var qSubject=$("#qSubject").val();
			var qCode=$("#qCode").val();
			location.href = jsBasePath+"/jzbTest/jpConfig/view_phone.html?qMonth="+qMonth+"&qClasstype="+qClasstype
			+"&qDept="+qDept+"&qGrade="+qGrade+"&qSubject="+qSubject+"&sort=${jq.sort-1}"+"&qDept="+qDept+"&isPc=no";
		});
		
		$("#nextImg").click(function(){
			var qMonth=$("#qMonth").val();
			var qClasstype=$("#qClasstype").val();
			var qDept=$("#qDept").val();
			var qGrade=$("#qGrade").val();
			alert(qDept)
			var qSubject=$("#qSubject").val();
			var qCode=$("#qCode").val();
			location.href = jsBasePath+"/jzbTest/jpConfig/view_phone.html?qMonth="+qMonth+"&qClasstype="+qClasstype
			+"&qDept="+qDept+"&qGrade="+qGrade+"&qSubject="+qSubject+"&qCode="+qCode+"&sort=${jq.sort+1}"+"&qDept="+qDept+"&isPc=no";
		});
		$("#code").blur(function(){
			var qMonth=$("#qMonth").val();
			var qClasstype=$("#qClasstype").val();
			var qDept=$("#qDept").val();
			var qGrade=$("#qGrade").val();
			var qSubject=$("#qSubject").val();
			var qCode=$("#code").val();
				location.href = jsBasePath+"/jzbTest/jpConfig/view_phone.html?qMonth="+qMonth+"&qClasstype="+qClasstype
				+"&qDept="+qDept+"&qGrade="+qGrade+"&qSubject="+qSubject+"&qDept="+qDept+"&qCode="+qCode+"&isSearch=2";	
		})
		$("#panel").click(function() {
			var qMonth=$("#qMonth").val();
			var qClasstype=$("#qClasstype").val();
			var qDept=$("#qDept").val();
			var qGrade=$("#qGrade").val();
			var qSubject=$("#qSubject").val();
			var qDept=$("#qDept").val();
			location.href = jsBasePath + "/jzbTest/jpConfig/view_phone.html?"+
			"&qGrade="+qGrade+"&qSubject="+qSubject+"&qClasstype="+qClasstype+"&qMonth="+qMonth+"&qDept="+qDept;					
		});
	</script>
</body>
</html>