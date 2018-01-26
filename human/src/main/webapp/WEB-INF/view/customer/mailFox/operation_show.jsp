<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>简历详情</title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	   <script src="<%=basePath%>/static/common/bootstrap/js/bootstrap.min.js"></script>
	   <link href="<%=basePath%>/static/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
	   <link rel="stylesheet" href="<%=basePath%>/static/viewer/viewer.min.css"/>
<script src="<%=basePath%>/static/viewer/viewer.min.js"></script>
<style type="text/css">
.layui-layer-content{
	overflow: auto !important;
}
.layui-layer-imgprev{
	position: fixed !important;
	left:21%;
}
.layui-layer-imgnext{
	position: fixed !important;
	right:21%;
}
.layui-layer-imgbar{
	position: fixed !important;
	bottom: 5%;
}

.input-disabled {
	background: #eee;
}
.layui-select-disabled .layui-disabled {
	color: black !important;
	background: #eee !important;
}
.row {
	margin:5px 0px ;
}
.layui-form-select {
	width: 182px !important;
}
.layui-input-inline {
	margin-bottom: 5px;
	width: 182px;
}
.layui-form-item{
	margin-bottom: 5px;
}
.layui-form-label {
	width: 110px !important;
}
.titleDiv {
	color: white;
	padding-left: 10px;
	height: 30px;
	line-height: 30px;
	background: #1AA094;
	border-bottom: 1px solid #eee;
}
.sortSelect >.layui-form-select{
	width: 80px !important;
}
.sortSelect{
width: 80px !important;
}


.tjspan {
	margin: 0;
	font-size: 12px;
	color: #999;
	line-height: 22px;
}

.div_left {
	width: 80%;
	border: 1px solid #b0dbc0;
	border-radius: 5px;
	padding: 10px;
	margin: 5px;
	font-size: 16px;
	border-radius: 5px;
	padding: 10px;
	margin: 5px;
}
.div_right {
	width: 80%;
	border: 1px solid #eee;
	border-radius: 5px;
	padding: 10px;
	margin: 5px;
	float: right;
	font-size: 16px;
}
.layer-photos-demo img {
	position: relative;
	display: inline-block;
	width: 77px;
	height: 77px;
	margin: 5px 5px 5px 5px;
	border: 1px solid #D9D9D9;
	background: #fff no-repeat center;
	background-size: cover;
}
.layui-form-item .layui-inline {
	 margin-bottom: 0px; 
}
</style>
<body >
	<div class="appForm"  style="width: 98%;margin: 0 auto;">
	<div class="layui-tab layui-tab-card row">
				<ul class="layui-tab-title" style="position: inherit;"> 
					<li class="layui-this">反馈详情</li>
					<li>沟通记录</li>
				</ul>
			<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<form class="layui-form" id="editForm" >
						<div class="titleDiv" >基本信息</div>
								<div class="row">
									<div class="layui-inline">
										<label class="layui-form-label">接入时间:</label>
										<div class="layui-input-inline">
											<input type="text"  value="<fmt:formatDate value='${cm.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly class="layui-input"  autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">解决时间:</label>
										<div class="layui-input-inline">
											<input type="text"  value="<fmt:formatDate value='${cm.solTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">接入渠道:</label>
										<div class="layui-form layui-input-inline">
											<input type="text" readonly value="${cm.acctype}" class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">学员年级:</label>
										<div class="layui-form layui-input-inline">
											<input type="text" readonly value="${cm.grade}" class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">学员姓名:</label>
										<div class="layui-input-inline">
											<input type="text"  value="${cm.name}" class="layui-input"  readonly autocomplete="off">
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">联系方式:</label>
										<div class="layui-input-inline">
											<input type="text" readonly value="${cm.telPhone}" class="layui-input" autocomplete="off" lay-verify="phone">
										</div>
									</div>
									<c:if test="${cm.acctype=='校长信箱'}">
									<div class="layui-inline">
										<label class="layui-form-label">涉及方面:</label>
										<div class="layui-input-inline">
											<input type="text" name="type" value="${cm.type}" readonly class="layui-input" autocomplete="off" >
										</div>
									</div>
									</c:if>
									<div class="layui-inline">
										<label class="layui-form-label">对应校区:</label>
										<div class="layui-form layui-input-inline">
											<input type="text" readonly value="${cm.campus}" class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">对应部门:</label>
										<div class="layui-form layui-input-inline">
												<input type="text" readonly value="${cm.dept}" class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">一级类别:</label>
										<div class="layui-form layui-input-inline">
											<input type="text" readonly value="${cm.type1}" class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">二级类别:</label>
										<div class="layui-form layui-input-inline">
											<input type="text" readonly value="${cm.type2}" class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">三级类别:</label>
										<div class="layui-form layui-input-inline">
											<input type="text" readonly value="${cm.type3}" class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">四级类别:</label>
										<div class="layui-form layui-input-inline">
											<input type="text" readonly value="${cm.type4}" class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">完善人员:</label>
										<div class="layui-input-inline">
											<input type="text"  value="${cm.completeUser}" readonly class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">完善时间:</label>
										<div class="layui-input-inline">
											<input type="text"  value="<fmt:formatDate value='${cm.completeTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">处理人员:</label>
										<div class="layui-input-inline">
											<input type="text"  value="${cm.solUser}" readonly class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">开始处理:</label>
										<div class="layui-input-inline">
											<input type="text"  value="<fmt:formatDate value='${cm.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly class="layui-input" autocomplete="off" >
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">解决时间:</label>
										<div class="layui-input-inline">
											<input type="text"  value="${cm.solTime}" readonly class="layui-input" autocomplete="off" >
										</div>
									</div>
								</div>
						<div class=" titleDiv" >
							家长原始描述
						</div>
							<div class="row ">
							<div class="layui-form-item">
										<label class="layui-form-label">反馈主题:</label>
										<div class="layui-input-block">
											<input type="text" readonly value="${cm.title}"   class="layui-input" autocomplete="off"   >
										</div>
									</div>
							<div class="layui-form-item" >
							<label class="layui-form-label">原始描述:</label>
							<div class="layui-input-block">
									<textarea name="desc"  readonly  class="layui-textarea"
										 >${cm.desc}</textarea></div>
										 </div>
							</div>
						<div class=" titleDiv" >
							处理信息
						</div>
						<div class="row "  style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 5px;">
							<div class="layui-form-item">
										<label class="layui-form-label">补充说明:</label>
										<div class="layui-input-block">
												<textarea  readonly  class="layui-textarea">${cm.comment}</textarea>
										 </div>
							</div>
							</div>
							<c:if test="${re.cmp !=null or fn:length(cm.cmd)>0}">
							<div class="row "  style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom:5px;" id="jaxq">
							<div class="layui-form-item">
										<label class="layui-form-label" >家长需求</label>
								</div>
								<c:forEach var="d" items="${cm.cmd}" varStatus="st">
								<div class="layui-form-item">
								<div class="layui-inline">
										<label class="layui-form-label">需求${st.index+1}:</label>
										<div class="layui-input-inline"  style="width:500px;">
											<input type="text" readonly  value="${d.demandDesc}" class="layui-input" autocomplete="off">
										</div>
									</div>  
									<div class="layui-inline"><label class="layui-form-label">是否满足:</label>
										<div class="layui-form layui-input-inline sortSelect">
											<input type="text" readonly value="<c:if test="${!d.isMeet}">否</c:if><c:if test="${d.isMeet}">是</c:if>" class="layui-input" autocomplete="off">
										</div>
									</div>
							</div>
								</c:forEach>
							</div>
							</c:if>
							<div class="row"  style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 5px;">
							<div class="layui-form-item">
										<label class="layui-form-label">解决方案:</label>
										<div class="layui-input-block">
												<textarea name="solution" readonly  class="layui-textarea"
										 				>${cm.comment}</textarea>
										 </div>
							</div>
							</div>
							<div class="row "  style="border-bottom: 1px solid #1AA094;margin-top: 5px;padding-bottom: 5px;">
						<%-- 	<div class="layui-form-item">
										<label class="layui-form-label"  style="width: 203px !important">是否需要相关人员跟进？</label>
										<div class="layui-form layui-input-inline sortSelect">
										<input type="text" readonly value="<c:if test="${!cm.isTracet}">否</c:if><c:if test="${cm.isTracet}">是</c:if>" class="layui-input" autocomplete="off">
										</div>
									</div> --%>
									<div class="layui-inline">
										<label class="layui-form-label">跟进人:</label>
										<div class="layui-input-inline">
											<input type="text" readonly value="${cm.tracerName}" class="layui-input" autocomplete="off">
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">开始跟进:</label>
										<div class="layui-input-inline">
											<input type="text" readonly value="<fmt:formatDate value='${cm.tracerStartTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" class="layui-input" autocomplete="off">
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">跟进结束:</label>
										<div class="layui-input-inline">
											<input type="text" readonly  value="<fmt:formatDate value='${cm.tracerEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"  class="layui-input" autocomplete="off">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">跟进情况:</label>
										<div class="layui-input-block">
												<textarea name="traceDesc" readonly   class="layui-textarea"
										 				 >${cm.traceDesc}</textarea>
										 </div>
							</div>
							</div>
							<div class="row "  >
							<div class="layui-form-item">
										<label class="layui-form-label"  style="width: 315px !important">是否发送处理已完结，邀请家长评价短信？</label>
										<div class="layui-input-block sortSelect" style="width: 50px;float: left;margin-left: 0px">
											<input type="text" readonly value="<c:if test="${!cm.isComSms}">否</c:if><c:if test="${cm.isComSms}">是</c:if>" class="layui-input" autocomplete="off">
										 </div>
							</div>
								<div class="layui-form-item">
										<div class="layui-input-block" style="text-align: center;">
												<input type="button"  class="layui-btn layui-btn-primary"  onclick ="return closeFrame();" value="返回">
										</div>
							</div>
							</div>
					</form></div>
					<div class="layui-tab-item">
					<c:forEach var="re" items="${cm.cmr}">
		 <c:if test="${re.type==0}">
		<div class="row">
		<div class="div_left">
		${re.desc}
		<c:if test="${re.cmp !=null or fn:length(re.cmp)>0}">
			<div class="img-list layer-photos-demo"  id="imglist_${re.id}" style="margin-bottom: 0px;" >
			<c:forEach var="photo" items="${re.cmp}" varStatus="st">
				<img layer-pid="photo.id" onclick="showImg(this,${st.index});" layer-src="${filePath}${photo.url}" src="${filePath}${photo.url}" alt="">
			</c:forEach>
			</div>
		</c:if>
		<p class="text-left tjspan">用户&nbsp;反馈时间:<fmt:formatDate value='${re.operTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
		</div>
		</div>
		</c:if>
		<c:if test="${re.type==1}">
		<div class="row" style="text-align: right;">
		<div class="div_right">
		<p class="text-left"  style="margin: 0;">${re.desc}<c:if test="${re.cmp !=null or fn:length(re.cmp)>0}">
			<div class="img-list layer-photos-demo"  id="imglist_${re.id}" style="margin-bottom: 0px;" >
			<c:forEach var="photo" items="${re.cmp}" varStatus="st">
				<img layer-pid="photo.id" onclick="showImg(this,${st.index});" layer-src="${filePath}${photo.url}" src="${filePath}${photo.url}" alt="">
			</c:forEach>
			</div>
		</c:if></p>
		<p class="text-right tjspan" >${re.operUser}&nbsp;回复时间:<fmt:formatDate value='${re.operTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
		</div>
		</div>
		</c:if>
		</c:forEach></div>
				</div>
		</div>
</body>
    <script type="text/javascript">
    layui.use(['form', 'layedit', 'laydate','upload','element'], function(){
  	  var form = layui.form()
  	  ,layer = layui.layer
  	  ,layedit = layui.layedit
  	  ,laydate = layui.laydate
  	  ,element = layui.element();
	});
	
    $(function() {
		$(":text[readonly]").addClass("input-disabled");
		$("textarea[readonly]").addClass("input-disabled");
	});


function  showImg(obj,index){
		$(obj).parent().viewer();
	}
    </script>
</html>