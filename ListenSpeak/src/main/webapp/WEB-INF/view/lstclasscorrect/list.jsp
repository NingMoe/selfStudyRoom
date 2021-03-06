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
<link rel="stylesheet" href="<%=basePath %>/static/zuoye/question.css">
<style>
 .jxzzy{
 	height:90px; 
 	text-align: center; 
 	color: #222; 
  	font-size:15px; 
   	border: 1px solid #eee; 
  	padding: 20px 0px;
}	
 .jxzzy .xslb{
 	vertical-align: middle;
} 

 .jxzzy .bj{ 
 	height: 90px; 
 	line-height: 90px; 
 } 
 .bt{ 
     height:40px; 
     line-height:18px; 
 	padding-left:40px; 
 	margin: 10px 0px;
 } 
 .bt span{ 
 color: #319b5e; 
 margin-right: 20px; 
 font-size:16px;
 } 
</style>
</head>
<body>
<div class="layui-form">
	<div class="layui-collapse" lay-accordion="" style="width: 90%;margin: 10px auto;">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">批改考试 </h2>
			<div class="layui-colla-content layui-show">
				<c:forEach items="${list }" var="li">
					<div style="background:#f9f9f9;box-shadow:1px 1px  1px 1px #ccc;    margin-bottom: 10px;">
						<div class="layui-row" >
							<div class="layui-col-md12 bt">
								<span>考试名称：${li.testName }
								布置时间：<fmt:formatDate value="${li.createTime }" pattern="yyyy年MM月dd日"/>
								截止时间：<fmt:formatDate value="${li.endTime }" pattern="yyyy年MM月dd日"/></span>
							</div>
						</div>
						
						<div class="layui-row jxzzy">
							<div class="layui-col-xs4">
								<p class="bj">
									${li.className }
								</p>
							</div>
							<div class="layui-col-xs3">
								<c:if test="${empty li.overallDfl }">
									<p class="bj">
										暂未批改
									</p>
								</c:if>
								<c:if test="${!empty li.overallDfl }">
									<p>总分得分率：${li.overallDfl }%</p>
									<p>准确：${li.accuracyDfl }%</p>
									<p>完整：${li.integrityDfl }%</p>
									<p>流利：${li.fluencyDfl }%</p>
								</c:if>
							</div>
							<div class="layui-col-xs5 xslb">
								<p class="bj">
									<a class="layui-btn" onclick="viewxs('${li.classCode}','${li.testId }','${li.className}','${li.testName}')">学生列表</a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div class="layui-collapse" lay-accordion="" style="width: 90%;margin: 10px auto;">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">历史考试</h2>
			<div class="layui-colla-content layui-show">
			  	<div style="width: 90%;margin: 0px auto;">
			  		<table class="layui-table" id="ccrTable" lay-filter="paper"></table>
			  	</div>
		  	</div>
		</div>
	</div>
</div>
</body>
<script type="text/html" id="paperBar">
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="view">进入考试</a>
</script>

<script type="text/javascript">
layui.use([ 'table', 'laypage','element' ], function() {
// 	var table = layui.table, laypage = layui.laypage, layer = layui.layer;
	var table = layui.table, laypage = layui.laypage, element = layui.element, laypage = layui.laypage;
	var tableIns = table.render({
		elem : '#ccrTable',
		url : jsBasePath + '/lstClassTestCorrect/query.html',
		cellMinWidth : 80,
		page : { //详细参数可参考 laypage 组件文档
			layout : [ 'count', 'prev', 'page', 'next', 'skip', 'limit' ],
			groups : 3, //只显示 1 个连续页码
			first : false, //不显示首页
			last : false
		//不显示尾页
		},

		cols : [ [ {
			field : 'testName',
			width : "11%",
			title : '测试名称',
			align : "center"
		}, {
			field : 'className',
			width : "11%",
			title : '班级名称',
			align : "center"
		}, {
			field : 'status',
			width : "11%",
			title : '提交状态',
			align : "center"
		},  {
			field : 'createTime',
			width : "11%",
			title : '发布时间',
			align : "center"
		}, {
			field : 'overallDfl',
			width : "11%",
			title : '总分得分率',
			align : "center"
		}, {
			field : 'accuracyTeacher',
			width : "11%",
			title : '准确',
			align : "center"
		}, {
			field : 'integrityTeacher',
			width : "11%",
			title : '完整',
			align : "center"
		}, {
			field : 'fluencyTeacher',
			width : "11%",
			title : '流利',
			align : "center"
		}, {
			fixed : 'right',
			width : "12%",
			title : '操作',
			align : 'center',
			toolbar: '#paperBar'
		} ] ]
	});
	table.on('tool(paper)', function(obj){
	    var data = obj.data;
		if (obj.event == 'view') {
			view(data.classCode,data.testId,data.className,data.testName);
			} 
	 });
	$('#searchBtn').bind('click', function() {
		reloadTable();
	});

	function reloadTable() {
		var status = $("#status");
		tableIns.reload({
			where : {
				status : status.val(),
			}
		});
	}
	function view(classCode,testId,className,testName){
		var url = jsBasePath + "/lstClassTestCorrect/toStudentScore.html?classCode="+classCode+"&testId="+testId+"&className="+className+"&testName="+testName+"&status=3";
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "学生列表", //
			offset : [ '4%' ],
			area : [  '80%', '95%' ],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {

			}
		});
	}
});
function viewxs(classCode,testId,className,testName){
	var url =jsBasePath + "/lstClassTestCorrect/toStudentScore.html?classCode="+classCode+"&testId="+testId+"&className="+className+"&testName="+testName+"&type=2"+"&status=2";
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		title : "学生列表", 
		offset : ['5%'],
		area : [ '80%', '95%'],
		content : url, //捕获的元素
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
		}
	});
}
</script>
</html>