<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>一对六学员信息</title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
<%@include file="/WEB-INF/view/common/bootstrapTable.jsp"%></head>
<style type="text/css">
</style>
<body>
	<div class="main-wrap">
		<fieldset class="layui-elem-field" style="padding: 15px;"
			id="serachFrom">
			<legend data-toggle="collapse" data-parent="serachFrom"
				href="#collapseOne" style="color: #1AA094;">
				检索&nbsp;
				<li class="fa fa-angle-double-down" id="ic"></li>
			</legend>
			<form class="layui-form">
				<div class="layui-form-item collapse in" id="collapseOne">
					<div class="layui-form-item">
						<input type="text" hidden="true" id="sClassCode"
							value="${sClassCode}" autocomplete="off" class="layui-input">
						<label class="layui-form-label" style="width: 10%">学校</label>
						<div class="layui-input-inline" style="width: 11%;">
							<input type="text" id="schoolName" autocomplete="off" 
								class="layui-input">
						</div>
<!-- 						<div class="layui-input-inline" style="width: 11%;"> -->
<!-- 							<select name="schoolName" id="schoolName"> -->
<!-- 								<option value="">请选择</option> -->
<%-- 								<c:forEach items="${schoolName}" var="sn"> --%>
<%-- 									<option value="${sn.name }">${sn.name }</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->
						<label class="layui-form-label" style="width: 10%">规划师</label>
						<div class="layui-input-inline" style="width: 11%;">
							<input type="text" id="planner" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label"
							style="width: 10%; margin-left: 3%">学生来源</label>
						<div class="layui-input-inline" style="width: 11%;">
							<select name="stuOrigin" id="stuOrigin">
								<option value="">请选择</option>
								<c:forEach items="${stuOrigin}" var="so">
									<option value="${so.name }">${so.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="width: 10%;">学生家长</label>
						<div class="layui-input-inline" style="width: 11%;">
							<input type="text" id="stuPar" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label" style="width: 10%;">学生联系电话</label>
						<div class="layui-input-inline" style="width: 11%;">
							<input type="text" id="stuPhone" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label" style="width: 150px;">是否提交测试</label>
						<div class="layui-input-inline" style="width: 10.7%">
							<select name="isSub" id="isSub" lay-verify="required"
								style="width: 150px;">
								<option value="">请选择</option>
								<option value="是">是</option>
								<option value="否">否</option>
							</select>
						</div>
						<div class="layui-inline">
							<button onClick="initTable()" type="button" class="layui-btn">
								<li class="fa fa-search"></li> &nbsp;搜索
							</button>
						</div>
					</div>
				</div>
			</form>
		</fieldset>
		<div class="y-role">
			<sec:authorize ifAnyGranted='ROLE_one6_manger_edit'>
				<div id="toolbar"></div>
			</sec:authorize>
			<table class="tableList" id="ccrTable">
			</table>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>/static/common/bootstrap/js/FileSaver.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/common/bootstrap/js/tableExport.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/common/bootstrap/js/bootstrap-table-export.js"></script>
	<script type="text/javascript">
		layui.use('form', function() {
			var form = layui.form();
		});
		$(function() {
			initTable();

		});

		function initTable() {
			var columns = [
					{
						checkbox : true,
						fieId : 'id'
					},
					{
						field : 'code',
						title : '学生学号',
						align : 'center',
					},
					{
						field : 'stuName',
						title : '学生姓名',
						align : 'center',
					},
					{
						field : 'schoolName',
						title : '学校（公立）',
						align : 'center',
					},
					{
						field : 'planner',
						title : '规划师',
						align : 'center'
					},
					{
						field : 'stuOrigin',
						title : '学生来源',
						align : 'center'
					},
					{
						field : 'stuPar',
						title : '学生家长',
						align : 'center'
					},
					{
						field : 'stuPhone',
						title : '学生联系电话',
						align : 'center'
					},
					{
						field : 'marAcc',
						title : '阅卷账号及密码',
						align : 'center'
					},
					{
						field : 'isSub',
						title : '是否提交测试',
						align : 'center'
					},
					{
						field : 'testAcce',
						title : '测试附件',
						align : 'center'
					},
					{
						field : 'remark',
						title : '备注',
						align : 'center'
					},
					{
						field : '',
						title : '操作',
						align : 'center',
						formatter : function(value, row, index) {
							var re = "";
							re += "<sec:authorize  ifAnyGranted='ROLE_one6_stu_bj'><button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\""
									+ row.id
									+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button></sec:authorize>";
							if(row.testAcce!=""&&row.testAcce!=null){
							re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return download(\""
							   + row.testAcce+ "\",\""
							   + row.stuName+ "\",\""
							   + row.sClassCode+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;下载附件</button>";
								re += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return lookfile(\""
								  + row.id
								  + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;查看附件</button>";   
							}		
							return re;
						}
					} ];
			//初始化Table 不 
			$('#ccrTable').bootstrapTable('destroy');
			$("#ccrTable").bootstrapTable({
				url : jsBasePath + '/stu/admin/query.html', //请求后台的URL（*）
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
				uniqueId : "sClassCode", //每一行的唯一标识，一般为主键列
				showToggle : false, //是否显示详细视图和列表视图的切换按钮
				cardView : false, //是否显示详细视图
				detailView : false, //是否显示父子表
				smartDisplay : true, // 智能显示 pagination 和 cardview 等
				toolbar : '#toolbar', //工具按钮用哪个容器
				toolbarAlign : 'left',
				showExport : true,
				showType : "all",
				fileName : "一对六班级管理",
				columns : columns,
				onLoadSuccess : function(dataAll) {
				},
				onLoadError : function() {
					//mif.showErrorMessageBox("数据加载失败！");
				}
			});
		}
		//传递的参数
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNow : params.offset / params.limit + 1,
				sClassCode : $.trim($("#sClassCode").val()),
				schoolName : $.trim($("#schoolName").val()),
				planner : $.trim($("#planner").val()),
				stuOrigin : $.trim($("#stuOrigin").val()),
				stuPar : $.trim($("#stuPar").val()),
				stuPhone : $.trim($("#stuPhone").val()),
				isSub : $.trim($("#isSub").val()),
			};
		}

		function goStudentInfo(sClassCode) {
			var url = jsBasePath + "/xdf/class/goStuInfo.html?sClassCode="
					+ sClassCode;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '80%', '70%' ],
				content : url, //捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {

				}
			});
		}

		function addTest() {
			$.post(jsBasePath + "/free/entry/test.html", {}, function(data,
					status) {
			}, "json");
			return false;
		}

		//编辑
		function edit(id) {
			var url = jsBasePath + "/stu/admin/toEdit.html?id=" + id;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '70%', '60%' ],
				content : url, //捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {

				}
			});
		}
		function lookfile(id){
			var url = jsBasePath + "/stu/admin/tolookfile.html?id=" + id;
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '70%', '60%' ],
				content : url, //捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {

				}
			});
		}
		
		
		function download(testAcce,sClassCode,stuName){
			window.location.href=jsBasePath+"/stu/admin/download.html?stuName="+stuName+"&sClassCode="+sClassCode+"&testAcce="+testAcce;
		}
	</script>
</body>
</html>