<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %>
	 </head>
<style type="text/css">
.layui-inline{
margin-bottom: 0px !important;;
}
.layui-form-item {
	margin-bottom: 5px  ;
}

.layui-form-label{
	width: 100px;
}
/*
.layui-form-label {
	width: 110px !important;
}

.layui-form-select {
	width: 190px !important;
} */
</style>
<body >
	<div class="main-wrap">
		<form class="layui-form"  id="subForm">
		 	<input type="hidden" value="${info.id }" name="id"/>
		 	<div class="layui-form-item"  >
				<div class="layui-inline">
				<label class="layui-form-label" >学管师:</label>
				<div class="layui-input-inline" >
					<input type="text"  name="managerTeach" autocomplete="off"    value="${info.managerTeach}"
						class="layui-input">
				</div>
				</div>
				</div>
			<div class="layui-form-item">
				<div class="layui-inline">
				<label class="layui-form-label" >考试日期:</label>
						<div class="layui-input-inline">
							<input class="layui-input"  value="${info.examDate}" placeholder="请选择考试日期"  name="examDate"  lay-verify="required|date"    autocomplete="off"  onclick="layui.laydate({elem: this})" readonly="readonly">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label" >考试类型:</label>
						<div class="layui-input-inline" >
							<select name="examType"   lay-verify="required"  lay-filter="type">
								<option value="1" <c:if test="${info.examType==1}">selected</c:if>>托福</option>
								<option value="2" <c:if test="${info.examType==2}">selected</c:if>>ACT</option>
								<option value="3" <c:if test="${info.examType==3}">selected</c:if>>SAT</option>
							</select>
						</div>
					</div>
				</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">学号:</label>
					<div class="layui-input-inline">
						<input type="text" name="studentCode" value="${info.studentCode}"
							autocomplete="off" lay-verify="required" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">姓名:</label>
					<div class="layui-input-inline">
						<input type="text" name="studentName" value="${info.studentName}"
							autocomplete="off"   class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item iftf" <c:if test="${info.examType !=1}">style="display: none;"</c:if>>
				<div class="layui-inline">
					<label class="layui-form-label">指导老师:</label>
					<div class="layui-input-inline">
						<input type="text" name="guideTeach" autocomplete="off" value="${info.guideTeach}"
							 class="layui-input" >
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">是否大学生:</label>
					<div class="layui-input-inline">
						<input type="checkbox" name="isCollege" lay-skin="switch"  <c:if test="${info.isCollege}">checked=""</c:if> 
							lay-filter="isCollege" value="0" lay-text="是|否">
					</div>
				</div>
			</div>
				<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">学校名:</label>
					<div class="layui-input-inline">
						<input type="text" name="schoolName" autocomplete="off" value="${info.schoolName}"
							lay-verify="required" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">是否首考:</label>
					<div class="layui-input-inline">
						<input type="checkbox" name="isFirst" lay-skin="switch" value="0" <c:if test="${info.isFirst}">checked=""</c:if> 
							lay-filter="isFirst" lay-text="是|否">
					</div>
				</div>
			</div>
			<div class="layui-form-item joinTofl" <c:if test="${info.examType ==1}">style="display: none;"</c:if>>
						<div class="layui-inline">
							<label class="layui-form-label">入班托福成绩:</label>
							<div class="layui-input-inline">
								<input type="text" name="joinToefl" autocomplete="off" value="${info.joinToefl}"
									class="layui-input">
							</div>
						</div>
			</div>
			
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">总分:</label>
					<div class="layui-input-inline">
						<input type="text" name="totalScore" autocomplete="off"
							lay-verify="required|isScore" value="${info.totalScore}"
							class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item iftf ifact ifsat" >
			<div class="layui-inline">
				<label class="layui-form-label">阅读:</label>
				<div class="layui-input-inline">
					<input type="text"  name="readScore" autocomplete="off" lay-verify="required|isScore" value="${info.readScore}" 
						class="layui-input">
				</div>
			</div>
			</div>
			<div class="layui-form-item iftf"    <c:if test="${info.examType !=1}">style="display: none;"</c:if>>
			<div class="layui-inline">
				<label class="layui-form-label">听力:</label>
				<div class="layui-input-inline">
					<input type="text"  name="listenScore" autocomplete="off" lay-verify="isScore" value="${info.listenScore}" 
						class="layui-input">
				</div>
			</div>
			</div>
			<div class="layui-form-item iftf" <c:if test="${info.examType!=1}">style="display: none;"</c:if>>
			<div class="layui-inline">
				<label class="layui-form-label">口语:</label>
				<div class="layui-input-inline">
					<input type="text"  name="speakScore" autocomplete="off" lay-verify="isScore" value="${info.speakScore}" 
						class="layui-input">
				</div>
			</div>
			</div>
			
			<div class="layui-form-item ifact ifsat" <c:if test="${info.examType==1}">style="display: none;"</c:if>>
				<div class="layui-inline">
					<label class="layui-form-label">文法:</label>
					<div class="layui-input-inline">
						<input type="text" name="grammarScore" autocomplete="off" value="${info.grammarScore}" 
							lay-verify="isScore" class="layui-input">
					</div>
				</div>
			</div>
			
			<div class="layui-form-item ifact ifsat" <c:if test="${info.examType==1}">style="display: none;"</c:if>>
				<div class="layui-inline">
					<label class="layui-form-label">数学:</label>
					<div class="layui-input-inline">
						<input type="text" name="matheScore" autocomplete="off" value="${info.matheScore}" 
							lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item ifact" <c:if test="${info.examType !=2}">style="display: none;"</c:if>>
				<div class="layui-inline">
					<label class="layui-form-label">科推:</label>
					<div class="layui-input-inline">
						<input type="text" name="reasonScore" autocomplete="off" value="${info.reasonScore}" 
							lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			
			<div class="layui-form-item iftf  ifact ifsat" >
			<div class="layui-inline">
				<label class="layui-form-label">写作:</label>
				<div class="layui-input-inline">
					<input type="text"  name="writeScore" autocomplete="off"  lay-verify="isScore" value="${info.writeScore}" 
						class="layui-input">
				</div>
			</div>
			</div>
			<div class="layui-form-item" style="text-align: center;">
				<button lay-submit="" lay-filter="sub" type="button"
				class="layui-btn"><li class="fa fa-check-square-o"></li>
				&nbsp;更新
			</button>
		</div>
		</form>
		</div>
    <script type="text/javascript">
    layui.use(['laydate','form'], function() {
    	var form = layui.form();
    	var laydate = layui.laydate;
    	var start = {
    		istoday : true,
    	};
    	$('#exam_start').bind("click", function() {
    		start.elem = this;
    		laydate(start);
    	});
    	
    	form.on('switch(isCollege)', function(data) {
			if (data.elem.checked) {
				$(data.elem).val("1");
			} else {
				$(data.elem).val("0");
			}
		});
    	
    	form.on('switch(isFirst)', function(data) {
			if (data.elem.checked) {
				//如果是选中就判断是否ACT和SAT，是否需要录入首考托福成绩
				var type = ($("select[name='examType']").val());
				if (type == 2 || type == 3) {
					$(".joinTofl input").attr("lay-verify","required|isScore");
					$(".joinTofl").show();
				} else {
					$(".joinTofl input").attr("lay-verify", "");
					$(".joinTofl").hide();
				}
				$(data.elem).val("1");
			} else {
				//如果取消，则隐藏托福首考，并取消必填
				$(".joinTofl input").attr("lay-verify", "");
				$(".joinTofl").hide();
				$(data.elem).val("0");
			}
			form.render();
		});
    	
    	form.on('select(type)', function(data) {
			$(".iftf").hide();
			$(".iftf input").val("");
			$(".iftf input").attr("lay-verify", "isScore");
			$(".ifact").hide();
			$(".ifact input").val("");
			$(".ifact input").attr("lay-verify", "isScore");
			$(".ifsat").hide();
			$(".ifsat input").val("");
			$(".ifsat input").attr("lay-verify", "isScore");
			//选择托福
			if (data.value ==1) {
				$(".iftf").show();
				$(".iftf input[name !='guideTeach']").attr("lay-verify", "required|isScore");
			}
			//选择ACT
			if (data.value ==2) {
				$(".ifact").show();
				$(".ifact input[name !='writeScore']").attr("lay-verify", "required|isScore");
			}
			//选择SAT
			if (data.value ==3) {
				$(".ifsat").show();
				$(".ifsat input[name !='writeScore']").attr("lay-verify", "required|isScore");
			}
			form.render();
		});
    	
    	  form.on('submit(sub)', function(data){
    		   var index = layer.load(3, {shade: [0.3]});
  			$.post(jsBasePath+"/northamerica/mention/editMention.html",$("#subForm").serializeArray(),function(data,status){
  				layer.close(index); 
  				if(data.flag==false){
  					layer.alert(data.msg,{icon:2});
  				}else{
  					parent.initTable();
  					layer.alert(data.msg, {icon:1},function(){
  						closeFrame();
  					});
  				}
  			},"json");
  			return false; 
    	  });
    });
    </script>
</body>
</html>