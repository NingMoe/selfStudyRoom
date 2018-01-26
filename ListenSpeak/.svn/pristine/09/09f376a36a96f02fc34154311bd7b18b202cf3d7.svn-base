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
	<div class="alertFrom">
		<div class="layui-form Div2" id="Div2">
			<input type="hidden" id="paperId" name="paperId" value='${lst.id}'>
			<input type="hidden" id="speakEssay" name="speakEssay"
				value='${lbp.speakEssay}'> <input type="hidden"
				id="situaAnswer" name="situaAnswer" value='${lbp.situaAnswer}'>
			<input type="hidden" id="topicBrief" name="topicBrief"
				value='${lbp.topicBrief}'>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">题型</label>
				<div class="layui-input-inline" style="width: 40%;">
					<input class="layui-input" placeholder="" id="type" value="短文朗读"
						index='15'>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">难度</label>
				<div class="layui-input-inline" style="width: 60%;">
					<select name="difficulty" id="difficulty">
						<option value="">请选择</option>
						<c:forEach var="diff" items="${diffLevels }">
							<option value="${diff.dataValue }">${diff.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			 <div class="layui-form-item">
    			<label class="layui-form-label style=" width: 10% !important;    margin-left: 7%;">年级</label>
    			<div class="layui-input-block style="width: 60% !important;" >
    				<c:forEach var="grade" items="${grades }">
     				<input type="checkbox" name="${grade.dataValue }" title="${grade.name } "  lay-filter="grade" >
      				</c:forEach>
   			 	</div>
 			</div>
<!-- 			<div class="layui-form-item"> -->
<!-- 				<label class="layui-form-label" style="width: 10%;">年级</label> -->
<!-- 				<div class="layui-input-inline" style="width: 60%;"> -->
<!-- 					<select name="grade" id="grade" lay-filter="grade"> -->
<!-- 						<option value="">请选择</option> -->
<%-- 						<c:forEach var="grade" items="${grades }"> --%>
<%-- 							<option value="${grade.dataValue }">${grade.name }</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="layui-form-item ">
				<label class="layui-form-label progress" index="0"
					style="width: 20%;">录入进度（0/${lbp.speakEssay}）</label>
			</div>
			<div class="layui-form-item append"></div>
			<div class="layui-form-item answerAppend"></div>
			<div class="layui-form-item topicAppend"></div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="" class="layui-btn upQuestion" index="1"
						lay-submit="" lay-filter="demo1">上一题</button>
					<button id="" class="layui-btn nextQuestion" index="0"
						lay-submit="" lay-filter="demo1">下一题</button>	
				</div>
			</div>
		</div>
	</div>

	<div id="div1" style="display: none">
		<div>
			<input type="hidden" id="id" name="id" value='${lst.id}'>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">指导语</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea class="layui-textarea" name="message" id="message"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label topicTime" id="topicTime"
					style="width: 77%;">短文熟悉时间</label>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 11%;">题干</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea class="layui-textarea" name="content" id="content" cols="100" rows="8"
						style="width: 100%; height: 400px;"></textarea>
				</div>
			</div>
			<div class="layui-form-item aaa">
				<label class="layui-form-label " style="width: 10%;">排序</label>
				<div class="layui-input-inline" style="width: 12%;">
					<input type="text" id="xh" name="xh" placeholder="请输入题目顺序" value=""
						class="layui-input">
				</div>
				<label class="layui-form-label " style="width: 40%;">限定作答时间</label>
			</div>
			<div class="layui-form-item">
				<button type="button" class="layui-btn "
					onclick="addQuestion(this);" style="float: left; margin-left: 12%;">
					<li class="fa fa-plus-square"></li> &nbsp;添加试题
				</button>
				<label class="layui-form-label " style="width: 54%;">播放音频解析</label>
			</div>
			<div class="layui-form-item">
				<div class="layui-progress">
					<div class="layui-progress-bar" lay-percent="0%"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="div2" style="display: none">
		<div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">指导语</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea class="layui-textarea" name="message" id="message"></textarea>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label topicTime" id="topicTime"
						style="width: 77%;">短文熟悉时间</label>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 11%;">题干</label>
					<div class="layui-input-inline" style="width: 65%;">
						<textarea class="layui-textarea" name="topic" id="topic" cols="100" rows="8"
							style="width: 100%; height: 300px;"></textarea>
					</div>
				</div>
				<div class="layui-form-item sonTitle">
				</div>
				<div class="layui-form-item aaa">
					<label class="layui-form-label " style="width: 10%;">排序</label>
					<div class="layui-input-inline" style="width: 12%;">
						<input type="text" id="xh" name="xh" placeholder="请输入题目顺序"
							value="" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<button type="button" class="layui-btn " 
						onclick="addQjQuestion(this);"
						style="float: left; margin-left: 12%;">
						<li class="fa fa-plus-square"></li> &nbsp;添加试题
					</button>
				</div>
				<div class="layui-form-item">
					<div class="layui-progress">
						<div class="layui-progress-bar" lay-percent="0%"></div>
					</div>
			</div>
			</div>
		</div>
	</div>


	<div id="dryAnswer" style="display: none">
		<div class="layui-form-item son-item">
			<input type="hidden"  id="questionId">
			<label class="layui-form-label" style="width: 11%;">子题干</label>
			<div class="layui-input-inline" style="width: 65%;">
				<textarea class="layui-textarea" name="content" id="content" cols="100" rows="8"
					style="width: 100%; height: 100px;"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">解析</label>
			<div class="layui-input-inline" style="width: 65%;">
				<textarea class="layui-textarea" name="parseText" id="parseText" cols="100" rows="8"
					style="width: 100%; height: 100px;"></textarea>
			</div>
		</div>
		<div class="layui-form-item ">
			<label class="layui-form-label answerTime" style="width: 77%;">限定作答时间</label>
		</div>
	</div>
	<div class="div3" id="div3" style="display: none">
		<div>
			<input type="hidden"  id="questionId">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">指导语</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea class="layui-textarea" name="message" id="message"></textarea>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label topicTime" id="topicTime"
						style="width: 77%;">短文熟悉时间</label>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 11%;">题干</label>
					<div class="layui-input-inline" style="width: 65%;">
						<textarea class="layui-textarea" name="topic" id="topic" cols="100" rows="8"
							style="width: 100%; height: 300px;"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label topicTime" id="limitTime"
						style="width: 77%;"></label>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 11%;">解析</label>
					<div class="layui-input-inline" style="width: 65%;">
						<textarea class="layui-textarea" name="parseText" id="parseText" cols="100" rows="8"
							style="width: 100%; height: 100px;"></textarea>
					</div>
				</div>
				<div class="layui-form-item aaa">
					<label class="layui-form-label " style="width: 10%;">排序</label>
					<div class="layui-input-inline" style="width: 12%;">
						<input type="text" id="xh" name="xh" placeholder="请输入题目顺序"
							value="" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<button type="button" class="layui-btn " 
						onclick="addQuestion(this);"
						style="float: left; margin-left: 12%;">
						<li class="fa fa-plus-square"></li> &nbsp;添加试题
					</button>
				</div>
				<div class="layui-form-item">
					<div class="layui-progress">
						<div class="layui-progress-bar" lay-percent="0%"></div>
					</div>
			</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>/static/lstbasepaper/insertTest.js"></script>
</body>
</html>