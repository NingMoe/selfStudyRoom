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
<%@include file="/WEB-INF/view/common/bootstrapTable.jsp"%>
<script src="<%=basePath%>/static/common/bootstrap/js/bootstrap-table-editable.js"></script>
<link rel="stylesheet"
	src="<%=basePath%>/static/kinder-eitor/themes/default/default.css" />
<link rel="stylesheet"
	src="<%=basePath%>/static/kinder-eitor/plugins/code/prettify.css" />
</head>
<style type="text/css">
table {
	table-layout: fixed;
}

table tr td {
	text-overflow: ellipsis; /* for IE */
	-moz-text-overflow: ellipsis; /* for Firefox,mozilla */
	overflow: hidden;
	white-space: nowrap;
}
.fixed-table-pagination{
position: absolute !important;
bottom: -35px;
</style>
</head>
<body>
	<div class="main-wrap">
		<fieldset class="layui-elem-field" style="padding: 15px;"
			id="serachFrom">
			<legend  data-parent="serachFrom"
				href="#collapseOne" style="color: #1AA094;">
				试卷包含题型&nbsp;
				<li class="fa fa-angle-double-down" ></li>
			</legend>
			<form class="layui-form">
				<div class="layui-form-item collapse in" >
						<input type="hidden"  id="paperId" value="${lst.id}">
						<c:forEach var="lbpNum" items="${lbpNums }">
						<label class="layui-form-label"  style="width:11%">${lbpNum.name}(${lbpNum.num})</label>
		   			 	</c:forEach>
				</div>
			</form>
		</fieldset>
		<div class="y-role2">
				<div id="toolbar2">
				</div>
				<table class="tableList2" id="ccrTable2">
					
				</table>
			</div>
	</div>
	<div class="main-wrap" style="    margin-top: 2%;">
		<fieldset class="layui-elem-field" style="padding: 15px;"
			id="serachFrom">
			<legend data-toggle="collapse" data-parent="serachFrom"
				href="#collapseOne" style="color: #1AA094;">
				试题检索&nbsp;
				<li class="fa fa-angle-double-down" ></li>
			</legend>
			<form class="layui-form">
				<div class="layui-form-item collapse in" >
						<input type="hidden"  id="paperId" value="${lst.id}">
						<label class="layui-form-label " style=" width: 7% !important;">年级</label>
		    			<div class="layui-input-block grade" style="width: 60% !important;" >
		    				<c:forEach var="grade" items="${grades }">
		     				<input type="checkbox" name="${grade.dataValue }" title="${grade.name } "  lay-filter="grade" >
		      				</c:forEach>
		   			 	</div>
				</div>
				<div class="layui-form-item collapse in">
					<label class="layui-form-label" style="width: 7%;">题型</label>
					<div class="layui-input-inline" style="width: 20%;">
						<select name="questionType" id="questionType">
							<option value="">请选择</option>
							<option value="15">短文朗读</option>
							<option value="16">情景对话</option>
							<option value="17">话题简述</option>
						</select>
					</div>
					<label class="layui-form-label" style="width:7%;">难度</label>
					<div class="layui-input-inline" style="width: 20%;">
						<select name="difficulty" id="difficulty">
							<option value="">请选择</option>
							<c:forEach var="diff" items="${diffLevels }">
								<option value="${diff.dataValue }">${diff.name }</option>
							</c:forEach>
						</select>
					</div>
					<button onClick="initTable()" type="button"
			class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
				</div>
			</form>
		<div class="y-role">
			<div id="toolbar">
			</div>
			<table class="tableList" id="ccrTable">

			</table>
		</div>
	</fieldset>	
	</div>
</body>
<script type="text/javascript">
	layui.use('form', function() {
		var form = layui.form;
		initTable2();
		initTable();
	});
	function add(){
		var table = $("#ccrTable2");  
		var nodata = table.find(".no-records-found").size();
		if(nodata==1){
			table.find(".no-records-found").remove();
		}
		var index = table.find("tr").size();
		var currId = addIndex;
		var ids =  table.find("tr");
		var newRow = {
				"id":currId,
				"name":"xh",
				};
		 $("#dicDataTable").bootstrapTable("insertRow", {index:index,row:newRow });
		 addIndex++;
	}
	function initTable2(){
		var columns = [
// 						{
// 							checkbox : true,
// 							fieId : 'id',
// 						},
						{
							field : 'CODE',
							title : '题目编码',
							align : 'center',
						},
						{
							field : 'NAME',
							title : '所属题型',
							align : 'center',
						},
						{
							field : 'gradeName',
							title : '年级',
							align : 'center',
						},
						{
							field : 'userName',
							title : '创建人',
							align : 'center',
						},{
							field : 'xh',
							title : '排序',
							align : 'center',
							formatter: function(value, row, index){
								var re = "";
								if(row.xh==null||row.xh==""){
									re+="--";
								}else {
									re+=row.xh;
								}
								return re;
							},
							editable: {
				                    type: 'text',
				                    title: '排序',
				                    emptytext:"--",
				                    validate: function (v) {
				                        if (!v) return '排序不能为空';
				                        var reg = new RegExp("^[0-9]*$");
										if(!reg.test(v)){
											return '请填写数字';
											}
										}
				                    }
							}
					        ,
						{
							field : '',
							title : '操作',
							align : 'center',
							formatter : function(value, row, index) {
								var re = "";
								re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return deleteTest(\""
										+ row.CODE
										+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;删除试题</button>";
										return re;
							}
						} ];
				//初始化Table 不 
				$('#ccrTable2').bootstrapTable('destroy');
				$("#ccrTable2").bootstrapTable({
					url : jsBasePath + '/lstPaperQuestion/query.html', //请求后台的URL（*）
					//method: 'get',      //请求方式（*）
					method : 'post',
					contentType : "application/x-www-form-urlencoded", //必须的,post
					striped : true, //是否显示行间隔色
					cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					pagination : true, //是否显示分页（*）
					sortable : false, //是否启用排序
					//sortOrder : "asc", //排序方式
					queryParams : queryParams2, //传递参数（*）
					sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
					pageNumber : 1, //初始化加载第一页，默认第一页
					pageSize : 10, //每页的记录行数（*）
					pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
					search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
					strictSearch : false,
					showColumns : true, //是否显示所有的列
					showRefresh : false, //是否显示刷新按钮
					minimumCountColumns : 2, //最少允许的列数
					clickToSelect : false, //是否启用点击选中行
					//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
					uniqueId : "id", //每一行的唯一标识，一般为主键列
					showToggle : false, //是否显示详细视图和列表视图的切换按钮
					cardView : false, //是否显示详细视图
					detailView : false, //是否显示父子表
					smartDisplay : true, // 智能显示 pagination 和 cardview 等
					toolbar : '#toolbar2', //工具按钮用哪个容器
					toolbarAlign : 'left',
					showExport : true,
					showType : "all",
					fileName : "已选择试题",
					columns : columns,
					onLoadSuccess : function(dataAll) {
					},
					onLoadError : function() {
						//mif.showErrorMessageBox("数据加载失败！");
					},onPageChange : function(number, size) {
						$("html,body").animate({scrollTop:0},1000);
					},
					onEditableSave: function (field, row, oldValue, $el) {
						var code=row.CODE;
						var paperId=$("#paperId").val();
						var xh=row.xh;
						$.post(jsBasePath + "/lstQuestion/updateXh.html", {"paperId":paperId,"code" : code,"xh":xh}, function(data, status) {
							if (data != null) {
								layer.msg(data.message);
									$("#ccrTable").bootstrapTable('refresh');
									$("#ccrTable2").bootstrapTable('refresh');
							}
						}, "json");
					}
				});
	}
	function initTable() {
		var columns = [
// 				{
// 					checkbox : true,
// 					fieId : 'id',
// 				},
				{
					field : 'code',
					title : '题目编码',
					align : 'center',
				},
				{
					field : 'name',
					title : '所属题型',
					align : 'center',
				},
				{
					field : 'gradeName',
					title : '年级',
					align : 'center',
				},
				{
					field : 'userName',
					title : '创建人',
					align : 'center',
				},
				{
					field : '',
					title : '操作',
					align : 'center',
					formatter : function(value, row, index) {
						var re = "";
						re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return testView(\""
								+ row.code
								+ "\",\""
								+ row.questionType
								+ "\",\""
								+ row.gradeName
								+ "\",\""
								+ row.difficultyName
								+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看试题</button>";
					   re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return add(\""
						   		+ row.code
								+ "\",\""
								+ row.questionType
								+ "\",\""
								+ row.type
							   + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;添加试题</button>";		
								return re;
					}
				} ];
		//初始化Table 不 
		$('#ccrTable').bootstrapTable('destroy');
		$("#ccrTable").bootstrapTable({
			url : jsBasePath + '/lstQuestion/query.html', //请求后台的URL（*）
			//method: 'get',      //请求方式（*）
			method : 'post',
			contentType : "application/x-www-form-urlencoded", //必须的,post
			striped : true, //是否显示行间隔色
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, //是否显示分页（*）
			sortable : false, //是否启用排序
			//sortOrder : "asc", //排序方式
			queryParams : queryParams, //传递参数（*）
			sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 10, //每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : false,
			showColumns : true, //是否显示所有的列
			showRefresh : false, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : false, //是否启用点击选中行
			//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "code", //每一行的唯一标识，一般为主键列
			showToggle : false, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			smartDisplay : true, // 智能显示 pagination 和 cardview 等
			toolbar : '#toolbar', //工具按钮用哪个容器
			toolbarAlign : 'left',
			showExport : true,
			showType : "all",
			fileName : "试题列表",
			columns : columns,
			onLoadSuccess : function(dataAll) {
			},
			onLoadError : function() {
				//mif.showErrorMessageBox("数据加载失败！");
			},onPageChange : function(number, size) {
				$("html,body").animate({scrollTop:0},1000);
			}
		});
	}
	function queryParams2(params){
		return {
			pageSize : params.limit,
			pageNow : params.offset / params.limit + 1,
			paperId:$.trim($("#paperId").val())
		};
	}
	//传递的参数
	function queryParams(params) {
		var grade="";
		$(".grade").find(".layui-form-checked").prev().each(function(){
			grade+=$(this).attr("name")+",";
		})
		return {
			pageSize : params.limit,
			pageNow : params.offset / params.limit + 1,
			difficulty:$.trim($("#difficulty").val()),
			questionType:$.trim($("#questionType").val()),
			grade:grade,
			paperId:$.trim($("#paperId").val()),
		};
	}
	// 查看试题
	function testView(code,questionType,gradeName,difficultyName) {
		var paperId=$("#paperId").val();
		var url = jsBasePath + "/lstQuestion/testView.html?code=" + code+"&questionType="+questionType+"&gradeName="+gradeName+"&difficultyName="+difficultyName+"&paperId="+paperId;
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "查看试题", //
			offset : [ '4%' ],
			area : [ '1000px', '80%' ],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {

			}
		});
	}
	function add(code,questionType,type){
		var paperId=$("#paperId").val();
		$.post(jsBasePath + "/lstQuestion/addQues.html", {"paperId":paperId,"code" : code,"questionType":questionType,"type":type}, function(data, status) {
			if (data != null) {
				layer.msg(data.message);
				if (data.flag == true) {
					$("#ccrTable").bootstrapTable('refresh');
					$("#ccrTable2").bootstrapTable('refresh');
				}
			}
		}, "json");
	}
	//删除
	function del(id) {
		layer.confirm("确定删除该条数据么?删除后不可恢复!", {
			btn : [ '是', '否' ],//按钮
			offset : '10%',
			btnAlign : 'c'
		}, function(index) {
			$.post(jsBasePath + "/lstBasePaper/delete.html", {
				ids : id
			}, function(data, status) {
				layer.close(index);
				if (data != null) {
					layer.msg(data.message);
					if (data.flag == true) {
						$("#ccrTable").bootstrapTable('refresh');
					}
				}
			}, "json");
		}, function(index) {
			layer.close(index);
		});
	}
	//批量新增
	function bath_add(){
		var checks=$("#ccrTable").bootstrapTable('getSelections');
		var json={};
		var dataStr="";
		var data=[];
		var selectIds="";
		var paperId=$("#paperId").val();
		for(var i = 0; i < checks.length; i++)
		{		var info = {};
				info.code=selectIds+checks[i].code;
				info.questionType=selectIds+checks[i].questionType;
				info.type=selectIds+checks[i].type;
				info.paperId=$("#paperId").val();
				data.push(info);
		};
		dataStr=JSON.stringify(data);
		json.Data=dataStr;
		$.post(jsBasePath + "/lstQuestion/addQuesBath.html", {"jstr":JSON.stringify(json),"paperId":paperId}, function(data, status) {
			if (data != null) {
				layer.msg(data.message);
				if (data.flag == true) {
					$("#ccrTable").bootstrapTable('refresh');
					$("#ccrTable2").bootstrapTable('refresh');
				}
			}
		}, "json");
	}
	//批量删除
	function bath_del() {
		var checks=$("#ccrTable2").bootstrapTable('getSelections');
		var selectIds="";
		for(var i = 0; i < checks.length; i++)
		{
				selectIds=selectIds+checks[i].CODE+",";
		};
		var codes=selectIds;
		if (codes == "") {
			layer.alert('您还未选择任何记录!', {
				icon : 2,
				offset : '10%'
			});
			return;
		}
		deleteTest(codes);
	}
	//删除试卷配置
	function deleteTest(code){
		var paperId=$("#paperId").val();
		layer.confirm("确定删除该条数据么?删除后不可恢复!", {
			btn : [ '是', '否' ],//按钮
			offset : '10%',
			btnAlign : 'c'
		}, function(index) {
			$.post(jsBasePath + "/lstPaperQuestion/delete.html", {
				paperId : paperId,questionCode:code
			}, function(data, status) {
				layer.close(index);
				if (data != null) {
					layer.msg(data.message);
					if (data.flag == true) {
						$("#ccrTable2").bootstrapTable('refresh');
						$("#ccrTable").bootstrapTable('refresh');
					}
				}
			}, "json");
		}, function(index) {
			layer.close(index);
		});
	}
	//录入试卷
	function enterPaper() {
		var url = jsBasePath + "/lstBasePaper/toAdd.html"
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "编辑数据", //
			offset : [ '4%' ],
			area : [ '40%', '60%' ],
			content : url, //捕获的元素
			cancel : function(index) {
				layer.close(index);
			},
			end : function() {

			}
		});
	}

	//添加试题
	function toInsertTest(id) {
		var url = jsBasePath + "/lstBasePaper/toInsertTest.html?" + "id=" + id;
		layer.open({
			type : 2,
			shade : [ 0.5, '#000' ],
			title : "编辑数据", //
			offset : [ '4%' ],
			area : [ '1000px', '80%' ],
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