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
<style>
.bt{
	padding-left:40px;
	margin:2% 0px;
}
.bt span{
color: #319b5e;
margin-right: 20px;
font-size:16px;
}
</style>
</head>
<body>
<input type="hidden" id="zuoye_id" value="${param.zuoye_id }">
<input type="hidden" id="class_code" value="${param.class_code }">
<input type="hidden" id="zuoye_name" value="${param.zuoye_name }">
	<div class="main-wrap">
		<div class="bt">
			<span>作业名：${param.zuoye_name }</span><span>班级名：${param.class_name }</span>
		</div>
		<hr class="layui-bg-green">
		<div class="y-role">
			<div class="layui-btn-group" style="margin-top: 20px;">
				<button class="layui-btn" id="selectButton" >查看班级报告</button>
			</div>
			<table class="layui-table" id="studentTable" lay-filter="studentTable" style="width: 100%">
		</div>
	</div>
</body>
<script type="text/html" id="studentbar">
  <a class="layui-btn layui-btn-xs" lay-event="pgzy">批改作业</a>
  <a class="layui-btn layui-btn-xs" lay-event="detail">个人报告</a>
</script>
<script type="text/javascript">
layui.use(['table','laypage'], function(){
	var zuoye_id = $("#zuoye_id").val();
	var class_code = $("#class_code").val();
	var table = layui.table,laypage = layui.laypage;
	var tableIns = table.render({
		elem: '#studentTable',
		url: jsBasePath + '/zuoyeScore/getStudentList.html?zuoye_id='+zuoye_id+"&class_code="+class_code,
		page: {
			first: false,
			last: false,
			curr: location.hash.replace('#!fenye=', ''),
			hash: 'fenye',
			limits:[10,20]
		},
		cols: [[
		        {field:'student_name', width:"15%", title: '学生姓名',sort:true,fixed: 'left',align:"center"},
		        {field:'overall_sogou', width:"8%", title: '总分(系统)',align:"center"},
		        {field:'accuracy_sogou', width:"8%", title: '准确(系统)',align:"center"},
		        {field:'integrity_sogou', width: '8%',title: '完整(系统)',align:"center"},
		        {field:'fluency_sogou',width:"8%", title: '流利(系统)',align:"center"},
		        {field:'overall_teacher', width:"8%", title: '总分(人工)',align:"center"},
		        {field:'accuracy_teacher', width:"8%", title: '准确(人工)',align:"center"},
		        {field:'integrity_teacher', width: '8%',title: '完整(人工)',align:"center"},
		        {field:'fluency_teacher',width:"8%", title: '流利(人工)',align:"center"},
		        {fixed: 'right', width:"21%", title: '操作', align:'center', toolbar: '#studentbar'}
		]]
	});
	
	table.on('tool(studentTable)', function(obj){
		var data = obj.data;
		if(obj.event === 'pgzy'){
			topgzy(data);
		}
		if(obj.event === 'detail'){
			toStudentReport(data);
		}
	});
	
	function topgzy(data){
		  var student_id = data.student_id;
		  var zuoye_id = $("#zuoye_id").val();
		  var class_code = $("#class_code").val();
		  var zuoye_name = $("#zuoye_name").val();
		  
		  var url = jsBasePath + "/zuoyeScore/topgzy.html?student_id="+student_id
				  +"&zuoye_id="+zuoye_id+"&class_code="+class_code+"&zuoye_name="+zuoye_name;
		  layer.open({
			  type : 2,
			  shade : [ 0.5, '#000' ],
			  title : "批改作业", //
			  offset : ['4%'],
			  area : ['85%','90%'],
			  content : url, //捕获的元素
			  cancel : function(index) {
				  layer.close(index);
			  },
			  end : function() {
				  reloadTable();
			  }
		  });
	  }
	function toStudentReport(data){
		  var student_id = data.student_id;
		  var zuoye_id = $("#zuoye_id").val();
		  var class_code = $("#class_code").val();
		  var zuoye_name = $("#zuoye_name").val();
		  var url = jsBasePath + "/classZuoyeReport/toStudentReport.html?student_id="+student_id
				  +"&zuoye_id="+zuoye_id+"&class_code="+class_code+"&zuoye_name="+zuoye_name;
		  layer.open({
			  type : 2,
			  shade : [ 0.5, '#000' ],
			  title : "个人报告", //
			  offset : ['4%'],
			  area : ['90%','95%'],
			  content : url, //捕获的元素
			  cancel : function(index) {
				  layer.close(index);
			  },
			  end : function() {
				  reloadTable();
			  }
		  });
	  }
	$("#selectButton").click(function(){
		  var zuoye_id = $("#zuoye_id").val();
		  var class_code = $("#class_code").val();
		  var zuoye_name = $("#zuoye_name").val();
		  var url = jsBasePath + "/classZuoyeReport/zuoyeReport.html?zuoye_id="+zuoye_id+"&class_code="+class_code+"&zuoye_name="+zuoye_name;
  			layer.open({
	  		type : 2,
	  		shade : [ 0.5, '#000' ],
	  		title : "班级成绩报表", //
	  		offset : ['4%'],
	  		area : ['85%','90%'],
	  		content : url, //捕获的元素
	  		cancel : function(index) {
		  		layer.close(index);
	  		},
	  		end : function() {
		 	 reloadTable();
	  		}
  		});	
	})
});
	</script>
</html>