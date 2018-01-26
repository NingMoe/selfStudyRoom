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
		<fieldset class="layui-elem-field" style="padding: 15px;"
			id="serachFrom">
			<legend data-toggle="collapse" data-parent="serachFrom"
				href="#collapseOne" style="color: #1AA094;">
				班级列表&nbsp;
				<li class="fa fa-angle-double-down" id="ic"></li>
			</legend>
			<div class="layui-form">
				<div class="layui-form-item collapse in" id="collapseOne">
					<div class="layui-form-item" hidden="true">
						<input type="text" id="search" hidden="true" class="layui-input"
							value="search">
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="width: 10%;">是否有效</label>
						<div class="layui-input-inline" style="width: 11%;">
							<select name="status" id="status">
								<option value="1">有效</option>
								<option value="2">关闭</option>
								<option value="">全部</option>
							</select>
						</div>
						<div class="layui-inline">
							<button  id="searchBtn"  class="layui-btn"/>
								<li class="fa fa-search"></li> &nbsp;搜索
							</button>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		<div class="y-role">
			<!--工具栏-->
			<div id="toolbar"
				style="width: 98%; margin: 0 auto; margin-bottom: 10px;">
				<button onClick="return add()" type="button" class="layui-btn">
					<li class="fa fa-plus-square"></li> &nbsp;新增班级
				</button>
			</div>
			<!--/工具栏-->
			<!--文字列表-->
			<table class="tableList" id="ccrTable" lay-filter="paper">
			</table>
		</div>
	</div>
</body>
<script type="text/html" id="paperStatusTpl">
			{{#  if(d.status == '1'){ }}
  			有效
			{{#  } }}
			{{#  if(d.status == '2'){ }}
  			关闭
			{{#  } }}
		</script>

<script type="text/html" id="paperBar">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			{{#  if(d.status == '1'){ }}
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">禁用</a>
			{{#  } }}
			{{#  if(d.status == '2'){ }}
  			<a class="layui-btn layui-btn-xs" lay-event="use">启用</a>
			{{#  } }}
    		<a class="layui-btn layui-btn-xs" lay-event="goStudentInfo">学生列表</a>
		</script>
<script type="text/javascript">
	layui.use([ 'table', 'laypage' ], function() {
		var table = layui.table, laypage = layui.laypage;
		var status=$("#status").val();
		var tableIns = table.render({
			elem : '#ccrTable',
			url : jsBasePath + '/lstclass/query.html',
			where :{"status":status},
			page : {
				layout : [ 'count', 'prev', 'page', 'next', 'skip', 'limit' ],
				groups : 3, //只显示 1 个连续页码
				first : false, //不显示首页
				last : false
			},
			cols : [ [ {
				field : 'className',
				width : "10%",
				title : '班级名称',
				align : "center"
			}, {
				field : 'grade',
				width : "10%",
				title : '年级',
				align : "center"
			}, {
				field : 'subject',
				width : "10%",
				title : '科目',
				align : "center"
			}, {
				field : 'count',
				width : "10%",
				title : '班级人数',
				align : "center"
			}, {
				field : 'createTime',
				width : "10%",
				title : '创建时间',
				align : "center"
			}, {
				field : 'invitationCode',
				width : "10%",
				title : '邀请码',
				align : "center"
			}, {
				field : 'status',
				width : "10%",
				title : '状态',
				align : "center",
				templet : "#paperStatusTpl"
			}, {
				fixed : 'right',
				width : "30%",
				title : '操作',
				align : 'center',
				toolbar : '#paperBar'
			} ] ]
		});

		table.on('tool(paper)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				edit(data.id);
			} else if (obj.event === 'del') {
				del(data.id);
			} else if (obj.event === 'goStudentInfo') {
				goStudentInfo(data.classCode, data.invitationCode);
			} else if (obj.event == 'use') {
				goUse(data.id);
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

		//		编辑
		function edit(id) {
			var url = jsBasePath + "/lstclass/toEdit.html?id=" + id;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '800px', '400px' ],
				content : url, //捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {

				}
			});
		}

		function goStudentInfo(classCode, invitationCode) {
			var url = jsBasePath
					+ "/studentclass/toStudentInfo.html?classCode=" + classCode
					+ "&invitationCode=" + invitationCode;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '90%', '90%' ],
				content : url, //捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {

				}
			});
		}

		//删除
		function del(id) {
			layer.confirm("确定禁用该条数据么?", {
				btn : [ '是', '否' ],//按钮
				offset : '10%',
				btnAlign : 'c'
			}, function(index) {
				$.post(jsBasePath + "/lstclass/delete.html", {
					ids : id
				}, function(data, status) {
					layer.close(index);
					if (data != null) {
						layer.msg(data.message);
						if (data.flag == true) {
							reloadTable();
						}
					}
				}, "json");
			}, function(index) {
				layer.close(index);
			});
		}
		//删除
		function goUse(id) {
			layer.confirm("确定启用该条数据么?", {
				btn : [ '是', '否' ],//按钮
				offset : '10%',
				btnAlign : 'c'
			}, function(index) {
				$.post(jsBasePath + "/lstclass/goUse.html", {
					id : id
				}, function(data, status) {
					layer.close(index);
					if (data != null) {
						layer.msg(data.message);
						if (data.flag == true) {
							reloadTable();
						}
					}
				}, "json");
			}, function(index) {
				layer.close(index);
			});
		}

	});
	//	新增
	function add() {
		var url = jsBasePath + "/lstclass/toAdd.html";
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "新增数据", //
			offset : [ '4%' ],
			area : [ '800px', '400px' ],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {

			}
		});
	}
	//配置试卷
	function paperView(paperId) {
		if (paperId == null || paperId == '') {
			layer.alert('您还未配置试卷', {
				icon : 2,
				offset : '10%'
			});
			return false;
		}
		var url = jsBasePath + "/lstQuestion/index.html?id=" + paperId;
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "试卷预览", //
			offset : [ '4%' ],
			area : [ '90%', '95%' ],
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