<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
</head>
<body>
	<div class="main-wrap">
		<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
			<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
			<div style="display:none;">
				<input type="hidden" id="urlParams" value="">
				<input type="button" id="hiddenAddBtn">
			</div>
			<div class="layui-form" >
			<div class="layui-form-item collapse in" id="collapseOne">
				<div class="layui-form-item" style="margin-bottom: 5px;">
					<label class="layui-form-label" style="width:8%;">年级:</label>
					<div class="layui-input-inline" style="width:14%;">
						<select name="grade" id="grade">
							<option value="">请选择</option>
							<c:forEach items="${grades }" var="grade">
								<option value="${grade.dataValue }">${grade.name }</option>
							</c:forEach>
						</select>
					</div>
					<label class="layui-form-label" style="width:8%;">难度:</label>
					<div class="layui-input-inline" style="width:14%;">
						<select name="difficulty" id="difficulty">
							<option value="">请选择</option>
							<option value="A">简单</option>
							<option value="B">中等</option>
							<option value="C">较难</option>
						</select>
					</div>
					<label class="layui-form-label" style="width:8%;">题型:</label>
					<div class="layui-input-inline" style="width:14%;">
						<select name="questionType" id="questionType">
							<option value="">请选择</option>
							<c:forEach items="${questionTypes }" var="questionType">
								<option value="${questionType.id }">${questionType.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="layui-input-inline" style="width:14%;">
						<button id="searchBtn" class="layui-btn">搜索</button>
					</div>
				</div>
					
			</div>
			</div>
		</fieldset>
		
		<div class="y-role">
			<div class="layui-btn-group" style="margin-top: 20px;">
				<button class="layui-btn" id="selectButton" >新增题目</button>
			</div>
			<table class="layui-table" id="questionTable" lay-filter="question" style="width: 100%">
		</div>
	</div>
</body>
<script type="text/html" id="diffTpl">
 {{ d.difficulty == 'A' ? '简单' : (d.difficulty == 'B' ? '中等' : '较难') }}
</script>
<sec:authorize ifAnyGranted='ROLE_QUESTION_DEL'>
<script type="text/html" id="questionbar">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delQuestion">删除</a>
</script>
</sec:authorize>

<sec:authorize ifNotGranted='ROLE_QUESTION_DEL'>
<script type="text/html" id="questionbar">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>
</sec:authorize>



<script type="text/javascript">
layui.use(['table','laypage'], function(){
	var table = layui.table,laypage = layui.laypage;
	var tableIns = table.render({
		elem: '#questionTable',
		url: jsBasePath + '/question/query.html',
		page: {
			first: false,
			last: false,
			curr: location.hash.replace('#!fenye=', ''),
			hash: 'fenye'
		},
		cols: [[
		        {field:'code', width:"15%", title: '题目编码',sort:true,fixed: 'left',align:"center"},
		        {field:'questionTypeName', width:"10%", title: '题型',align:"center"},
		        {field:'gradeName', width:"9%", title: '年级',align:"center"},
		        {field:'difficulty', title: '难度', width: '11%', minWidth: 100,align:"center",templet:"#diffTpl"},
		        {field:'createUserName',width:"15%", title: '创建人',align:"center"},
		        {field:'createTime',width:"15%", title: '创建时间',align:"center",sort:true},
		        {fixed: 'right', width:"25%", title: '操作', align:'center', toolbar: '#questionbar'}
		]]
	});
	
	table.on('tool(question)', function(obj){
		var data = obj.data;
		if(obj.event === 'edit'){
			toEdit(data.code,data.type);
		}
		if(obj.event === 'delQuestion'){
			layer.confirm('确定删除该题目？', function(index){
				layer.close(index);
				delQuestion(data.code);
			});
		}
	});
			  
	$('#searchBtn').bind('click', function(){
		reloadTable();
	});
	
	$("#selectButton").bind('click', function(){
		toSelect();
	});
	
	$("#hiddenAddBtn").bind('click', function(){
		toAdd();
	});
	
	function delQuestion(code){
		var index = layer.load(3, {shade: [0.3]});
		 $.ajax({
			 type: "post",
			 url: jsBasePath+"/question/delQuestion.html?questionCode="+code,
			 dataType: "json",
			 async:false,
			 success: function(data){
				 layer.close(index);
				 if(data.flag==false){
					  layer.alert(data.message,{icon:2});
				  }else{
					  layer.alert(data.message,{icon:1},function(index){
						  layer.close(index);
						  reloadTable();
					  });
				  }
			 }
		 });
	}
	
	function reloadTable(){
		var grade = $("#grade");
		var difficulty = $("#difficulty");
		var questionType = $("#questionType");
		tableIns.reload({
			where: {
				grade: grade.val(),
				difficulty : difficulty.val(),
				questionType : questionType.val()
			}
		});
	}
	
	function toEdit(code,type) {
		var url = jsBasePath + "/question/toEdit.html?code="+code+"&type="+type;
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "编辑题目",
			offset : ['10px'],
			area : ['80%','88%'],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {
				reloadTable();
			}
		});
	}

	function toSelect() {
		var url = jsBasePath + "/question/toSelect.html";
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "选择题型", 
			offset : ['10%'],
			area : ['40%','50%'],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {
				reloadTable();
			}
		});
	}

	//新增
	function toAdd() {
		layer.closeAll();
		var urlParams = $("#urlParams").val();
		var url = jsBasePath + "/question/toAdd.html?"+urlParams;
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "新增题目",
			offset : ['10px'],
			area : ['80%','88%'],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {
				reloadTable();
			}
		});
	}
});
	</script>
</html>