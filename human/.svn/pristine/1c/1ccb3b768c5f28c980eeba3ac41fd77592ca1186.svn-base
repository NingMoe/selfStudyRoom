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
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
.layui-form-item {
	margin-bottom: 0px ;
}
.layui-form-label {
	width: 110px !important;
}

.layui-form-select {
	width: 190px !important;
}
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<form class="layui-form" >
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-form-item">
				<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">提交日期:</label>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="开始日期" id="exam_start">
						</div>
						<div class="layui-form-mid">-</div>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="截止日期" id="exam_end">
						</div>
					</div>
					<div class="layui-inline">
				<label class="layui-form-label" >部门:</label>
				<div class="layui-input-inline" >
					<select id="dept"   >
					<option value="">请选择</option>
					<c:forEach var="d" items="${deptList}">
					<option value="${d.id }">${d.name}</option>
					</c:forEach>
					</select>
				</div>
				</div>
					<div class="layui-inline">
				<label class="layui-form-label" >校区:</label>
				<div class="layui-input-inline" >
					<select id="campus"   >
					<option value="">请选择</option>
					<c:forEach var="d" items="${campusList}">
					<option value="${d.id }">${d.name}</option>
					</c:forEach>
					</select>
				</div>
				</div>
					<div class="layui-inline">
				<label class="layui-form-label" >年级:</label>
				<div class="layui-input-inline" >
					<select id="grade"   >
					<option value="">请选择</option>
					<c:forEach var="d" items="${gradeList}">
					<option value="${d.id }">${d.name}</option>
					</c:forEach>
					</select>
				</div>
				</div>
				<div class="layui-inline">
				<label class="layui-form-label" >接入方式:</label>
				<div class="layui-input-inline" >
					<select id="acctype"   >
					<option value="">请选择</option>
					<c:forEach var="d" items="${acctype}">
					<option value="${d.id }">${d.name}</option>
					</c:forEach>
					</select>
				</div>
				</div>
				<div class="layui-inline">
				<label class="layui-form-label" >当前状态:</label>
				<div class="layui-input-inline" >
					<select id="state"   >
					<option value="">请选择</option>
					<option value="3">跟进中</option>
					<option value="4">已跟进</option>
					<option value="5">已处理</option>
					</select>
				</div>
				</div>
			<div class="layui-inline">
				<label class="layui-form-label">联系方式:</label>
				<div class="layui-input-inline">
					<input type="text" id="telphone" autocomplete="off"  placeholder="输入联系方式检索"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<button onClick="initTable()" type="button"
				class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			</button>
			</div>
			</div>
		</div>
		</form>
		</fieldset>
		<div class="y-role">
            <table class="tableList"  id="tableList">
            </table>
            <div id="toolbar">
			 </div>
        </div>
    </div>
</body>
<script type="text/javascript">
layui.use(['laydate','form'], function() {
	var form = layui.form();
	var laydate = layui.laydate;
	var start = {
		istoday : true,
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
		}
	};

	var end = {
		istoday : true,
		choose : function(datas) {
			start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};
	$('#exam_start').bind("click", function() {
		start.elem = this;
		laydate(start);
	});
	$("#exam_end").bind("click", function() {
		end.elem = this
		laydate(end);
	});
	initTable();
});


function initTable(){
	 var columns = [{
			checkbox : true,
			fieId : 'id'
		}, {
			field : 'number',
			title : '序号',
			align : 'center',
			formatter : function(value, row, index) {
				var page = $("#tableList").bootstrapTable("getPage");
				return page.pageSize * (page.pageNumber - 1) + index + 1;
			}
		},{
			field : 'dept',
			title : '部门',
			align : 'center'
		},{
			field : 'campus',
			title : '校区',
			align : 'center'
		},{
			field : 'title',
			title : '主题',
			align : 'center'
		},{
			field : 'name',
			title : '提交人',
			align : 'center'
		},{
			field : 'telPhone',
			title : '联系方式',
			align : 'center'
		},{
			field : 'createTime',
			title : '提交时间',
			align : 'center'
		}, {
			field : 'state',
			title : '当前状态',
			align: 'center',
			formatter : function(value, row, index) {
					if(value==3){
						return "<font color=\"#1AA094\">跟进中</font>";
					}
					if(value==4){
						return "<font color=\"#1AA094\">已跟进</font>";
					}
					if(value==5){
						return "<font color=\"##80D540\">已处理</font>";
					}
			}
		}, {
			field : 'score',
			title : '评分',
			align : 'center' 
		},{
			field : 'solUser',
			title :'处理人',
			align : 'center'
		}];
		 columns.push({
			field : '',
			align : 'center',
			title : '操作',
			switchable : false,
			formatter : function(value, row, index) {
				var op = "";
				if(row.state==4||row.state==3){
					op += "<button  class='layui-btn layui-btn-mini layui-btn-normal' onclick='return edit(\"" + row.id + "\");'><i class='fa fa-caret-square-o-right'></i>&nbsp;处理</button >&nbsp;";
					//op += "<button  class='layui-btn layui-btn-warm layui-btn-mini' onclick='return del(\"" + row.id + "\",-1);'><i class='fa fa-minus-square'></i>&nbsp;关闭反馈</button >";
				}
				if(row.state==5){
					op += "<button  class='layui-btn layui-btn-mini' onclick='return showDetail(\"" + row.id + "\");'><i class='fa fa-search'></i>&nbsp;查看详情</button >&nbsp;";
				}
				
				return op;
			}
		});
		 showTable(columns);
}
	function showTable(columns) {
		//初始化Table 不 
		$('#tableList').bootstrapTable('destroy');
		$("#tableList").bootstrapTable({
			url : jsBasePath + '/customer/mailFox/queryTracer.html', //请求后台的URL（*）
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
		/* 	showColumns : true, //是否显示所有的列 */
			showRefresh : false, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : false, //是否启用点击选中行
			height:$(window).height()-$("#serachFrom").height()-52,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "id", //每一行的唯一标识，一般为主键列
			showToggle : false, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			smartDisplay : true, // 智能显示 pagination 和 cardview 等
			toolbar : '#toolbar', //工具按钮用哪个容器
			toolbarAlign : 'left',
			columns : columns,
			onLoadSuccess : function(dataAll) {
			},
			onLoadError : function() {
			}
		});
	};
	
	
	//传递的参数
	function queryParams(params) {
		return {
			pageSize : params.limit,
			pageNow : params.offset / params.limit + 1,
			dept : $.trim($("#dept").val()),
			acctype : $.trim($("#acctype").val()),
			campus : $.trim($("#campus").val()),
			grade : $.trim($("#grade").val()),
			telphone: $.trim($("#telphone").val()),
			startDate :  $.trim($("#exam_start").val()),
			endDate :  $.trim($("#exam_end").val()),
			state:$.trim($("#state").val())
		};
	}
	
	function edit(id){
		layer.open({
			   type: 2,
			   title: '处理反馈',
			   shadeClose: false,
			   shade: 0.8,
			   offset : ['10%'],
			   area: ['80%', '80%'],
			   content: jsBasePath+'/customer/mailFox/tracerEdit.html?id='+id
			}); 
	}
	
	function showDetail(id){
		layer.open({
			   type: 2,
			   title: '查看详情',
			   shadeClose: false,
			   shade: 0.8,
			   offset : ['10%'],
			   area: ['80%', '80%'],
			   content: jsBasePath+'/customer/mailFox/show.html?id='+id
			}); 
	}

	
</script>
</html>