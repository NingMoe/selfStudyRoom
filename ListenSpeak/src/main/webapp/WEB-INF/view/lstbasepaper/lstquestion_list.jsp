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
</head>
<body>
	<div class="alertFrom " id="Div">
		<!-- 		<form class="layui-form" id="addForm" > -->
		<input type="hidden" id="paperId" name="paperId" value='${paperId}'>
		<input type="hidden" id="fileUrl" name="fileUrl" value='${fileurl}'>
		<div class="layui-form-item">
			<div class=""
				style="text-align: center; font-size: 29px; padding: 31px">${lbp.name}</div>
			<div class="layui-form-item">
				<div class="" style="text-align: center;">
					<c:forEach items="${sourceTypes}" var="sType">
						<c:if test="${sType.dataValue  eq lbp.sourceType  }">
							<label class="" style="width: 11%">试卷标签:${sType.name}(${ lbp.year})</label>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class=" topicAppend"></div>
		</div>
		<div id="div2" style="display: none">
			<div>
				<div class="layui-collapse" style="width: 74%; margin-left: 11%;"
					lay-accordion="">
					<div class="layui-colla-item">
						<h2 class="layui-colla-title icon-title" index="1"></h2>
						<div class="layui-colla-content detail div2-detail">
							<input type="hidden" id="questionId">
							<div class="layui-form-item">
								<label class="layui-form-label zdmessage" style="width: 8%;">指导语</label>
								<div class="layui-input-inline zdmessage" style="width: 72%;">
									<textarea class="layui-textarea" name="message" id="message"></textarea>
								</div>
							</div>
							<div class="layui-form-item topic">
								<label class="layui-form-label topicTime" id="topicTime"
									style="width: 77%;"></label>
							</div>
							<div class="layui-form-item topic">
								<label class="layui-form-label" style="width: 8%;">大题干</label>
								<div class="layui-input-inline" style="width: 72%;">
									<textarea class="layui-textarea" name="topic" id="topic"
										cols="100" rows="8" style="width: 100%; height: 100px;"></textarea>
								</div>
							</div>
							<div class="layui-form-item sonTitle parseText"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="dryAnswer" style="display: none">
			<div class="layui-collapse" style="width: 77%; margin-left: 10%;"
				lay-accordion="">
				<div class="layui-colla-item">
					<h2 class="layui-colla-title icon-title-dryAnswer" index="1"></h2>
					<div class="layui-colla-content detail dryAnswer-detail">
						<div class="layui-form-item son-item">
							<input type="hidden" id="questionId"> <label
								class="layui-form-label sonQuestion" style="width: 8%;">题干</label>
							<div class="layui-input-inline" style="width: 100%;">
								<textarea class="layui-textarea" name="content" id="content"
									cols="100" rows="8" style="width: 100%; height: 100px;"></textarea>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label" style="width: 8%;">解析</label>
							<div class="layui-input-inline" style="width: 100%;">
								<textarea class="layui-textarea" name="parseText" id="parseText"
									cols="100" rows="8" style="width: 100%; height: 100px;"></textarea>
							</div>
						</div>
						<div class="layui-form-item ">
							<label class="layui-form-label answerTime" style="width: 77%;">限定作答时间</label>
						</div>
						<div class="layui-form-item" style="margin-bottom: 10px;">
							<label class="layui-form-label"
								style="width: 49%; text-align: left; padding: 9px 0px;">音频解析：点击播放音频解析&nbsp;&nbsp;</label>
						</div>
						<div class="layui-form-item"
							style="margin-bottom: 10px; margin-left: 0%">
							<div id="audioDiv${status.index+1 }" class="detailAudio"
								class="layui-input-inline"
								style="width: 46%; float: left; margin-top: 14px;">
								<audio id="audio" src="" controls="controls"></audio>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="div3" id="div3" style="display: none">
			<div>
				<div class="layui-collapse" style="width: 74%; margin-left: 11%;"
					lay-accordion="">
					<div class="layui-colla-item">
						<h2 class="layui-colla-title icon-title " index="1">题目${status.index+1 }</h2>
						<div class="layui-colla-content detail div2-detail ">
							<input type="hidden" id="questionId"> <input
								type="hidden" id="questionId">
							<div class="layui-form-item ">
								<label class="layui-form-label zdmessage" style="width: 8%;">指导语</label>
								<div class="layui-input-inline zdmessage" style="width: 72%;">
									<textarea class="layui-textarea " name="message" id="message"></textarea>
								</div>
							</div>
							<div class="layui-form-item topicDiv">
								<label class="layui-form-label topicTime" id="topicTime"
									style="width: 77%;">短文熟悉时间</label>
							</div>
							<div class="layui-form-item topicDiv">
								<label class="layui-form-label" style="width: 8%;">大题干</label>
								<div class="layui-input-inline" style="width: 72%;">
									<textarea class="layui-textarea" name="topic" id="topic"
										cols="100" rows="8" style="width: 100%; height: 100px;"></textarea>
								</div>
							</div>
							<div class="layui-form-item content">
								<label class="layui-form-label content" id="contentTime"
									style="width: 77%;">短文熟悉时间</label>
							</div>
							<div class="layui-form-item content">
								<label class="layui-form-label" style="width: 8%;">题干</label>
								<div class="layui-input-inline" style="width: 72%;">
									<textarea class="layui-textarea" name="content" id="content"
										cols="100" rows="8" style="width: 100%; height: 100px;"></textarea>
								</div>
							</div>
							<div class="layui-form-item parseTextDiv">
								<label class="layui-form-label topicTime" id="limitTime"
									style="width: 77%;"></label>
							</div>
							<div class="layui-form-item parseTextDiv">
								<label class="layui-form-label" style="width: 8%;">解析</label>
								<div class="layui-input-inline" style="width: 72%;">
									<textarea class="layui-textarea" name="parseText"
										id="parseText" cols="100" rows="8"
										style="width: 100%; height: 100px;"></textarea>
								</div>
							</div>
							<div class="layui-form-item parseTextDiv"
								style="margin-bottom: 10px; margin-left: 7%">
								<label class="layui-form-label"
									style="width: 49%; text-align: left; padding: 9px 0px;">音频解析：点击播放音频解析&nbsp;&nbsp;</label>
							</div>
							<div class="layui-form-item parseTextDiv"
								style="margin-bottom: 10px; margin-left: 0%">
								<div class="layui-input-inline" id="recordDiv"
									style="width: 90%; height: 71px; padding: 8px 10px; margin-right: 0px;">
									<div id="audioDiv" class="layui-input-inline"
										style="width: 30%; margin-left: 40px; margin-top: 14px;">
										<audio id="audio" src="" controls="controls"></audio>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/lstbasepaper/edit.js"></script>
</script>
</html>