<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>一对六班级管理</title>
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
						<label class="layui-form-label" style="width: 10%;">班号</label>
						<div class="layui-input-inline" style="width: 11%;">
							<input type="text" id="sClassCode" autocomplete="off"
								class="layui-input">
						</div>

						<label class="layui-form-label" style="width: 10%;">授课老师</label>
						<div class="layui-input-inline" style="width: 11%;">
							<input type="text" id="sAllTeacherName" autocomplete="off"
								class="layui-input">
						</div>

						<label class="layui-form-label" style="width: 10%;">教研组</label>
						<div class="layui-input-inline" style="width: 11%;">
							<select name="sClassTypeName" id="sClassTypeName">
								<option value="">请选择</option>
								<option value="初中数学">初中数学</option>
								<option value="初中英语">初中英语</option>
								<option value="初中生化">初中生化</option>
								<option value="初中物理">初中物理</option>
								<option value="高中数学">高中数学</option>
								<option value="高中生化">高中生化</option>
								<option value="高中英语">高中英语</option>
								<option value="高中物理">高中物理</option>
								<option value="文综">文综</option>
							</select>
						</div>

						<label class="layui-form-label" style="width: 10%;">区域</label>
						<div class="layui-input-inline" style="width: 11%;">
							<select name="sAreaAddress" id="sAreaAddress">
								<option value="">请选择</option>
								<c:forEach items="${jyz }" var="jyz">
									<option value="${jyz.name }">${jyz.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="width: 10%;">上课校区</label>
						<div class="layui-input-inline" style="width: 11%;">
							<select name="sPrintAddress" id="sPrintAddress">
								<option value="">请选择</option>
								<c:forEach items="${xq}" var="xq">
									<option value="${xq.name}">${xq.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="layui-form-label" style="width: 10%;">上课时间</label>
						<div class="layui-input-inline" style="width: 11%;">
							<input type="text" id="sPrintTime" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label" style="width: 10%;">年级</label>
						<div class="layui-input-inline" style="width: 11%;">
							<select name="sProjectCode" id="sProjectCode">
								<option value="">请选择</option>
								<c:forEach items="${jibGrade}" var="grade">
									<option value="${grade.dataValue}">${grade.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="layui-form-label" style="width: 10%;">季度</label>
						<div class="layui-input-inline" style="width: 11%;">
							<select name="sQuarter" id="sQuarter">
								<option value="">请选择</option>
								<option value="1">春季</option>
								<option value="2">夏季</option>
								<option value="3">秋季</option>
								<option value="4">冬季</option>
							</select>
						</div>
					</div>
				<div class="layui-form-item">
						<label class="layui-form-label " style="width: 10%;">班级水平</label>
						<div class="layui-input-inline beginGrade" style="width: 11%;">
							<select name="beginGrade" id="beginGrade" lay-filter="beginGrade">
								<option value="">请选择</option>
								<option value="40">40分以下</option>
								<option value="45">45分</option>
								<option value="50">50分</option>
								<option value="55">55分</option>
								<option value="60">60分</option>
								<option value="65">65分</option>
								<option value="70">70分</option>
								<option value="75">75分</option>
								<option value="80">80分</option>
								<option value="85">85分</option>
								<option value="90">90分</option>
								<option value="95">95分</option>
								<option value="100">100分</option>
							</select>
						</div>
						
						<div class="layui-input-inline endGrade" style="width: 11%;">
							<select name="endGrade" id="endGrade"  lay-filter="endGrade">
								<option value="">请选择</option>
								<option value="40">40分以下</option>
								<option value="45">45分</option>
								<option value="50">50分</option>
								<option value="55">55分</option>
								<option value="60">60分</option>
								<option value="65">65分</option>
								<option value="70">70分</option>
								<option value="75">75分</option>
								<option value="80">80分</option>
								<option value="85">85分</option>
								<option value="90">90分</option>
								<option value="95">95分</option>
								<option value="100">100分</option>
							</select>
						</div>
						<label class="layui-form-label " style="width: 10%;">当前人数</label>
						<div class="layui-input-inline nCurrentCount" style="width: 11%;">
							<select name="nCurrentCount" id="nCurrentCount" >
								<option value="">请选择</option>
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
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
			<sec:authorize ifAnyGranted='ROLE_one6_edit'>
				<div id="toolbar">
				<button type="button" id="init" onclick="init()" class="layui-btn"><li class="fa fa-add"></li>&nbsp;初始化班级</button>
				</div>
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
	<%--     <script src="<%=basePath %>/static/xdfclassinfo/list.js" type="text/javascript"></script> --%>
	<script type="text/javascript">
		layui.use('form', function() {
			var form = layui.form();
			initTable();
			
			  form.on('select(beginGrade)', function(data){
				  if(data.value=='40'){
					  $(".endGrade").hide();
					  $("[name=endGrade]").val('');
					  layui.form().render();
				  }else{
					  $(".endGrade").show();
				  }
				  
			  });
			  form.on('select(endGrade)', function(data){
				  if(data.value=='40'){
					  $(".beginGrade").hide();
					  $("[name=beginGrade]").val('');
					  layui.form().render();
				  }else{
					  $(".beginGrade").show();
				  }
				  
			  });
			
			
		});

		function initTable() {
			var beginGrade=$.trim($("#beginGrade").val());
			var endGrade  =$.trim($("#endGrade").val());
			
			if(parseInt(endGrade)<parseInt(beginGrade)){
				layer.alert("最大分数不能小于最小分数",{icon:2});
				return false;
			}else if(parseInt(endGrade)-parseInt(beginGrade)>20){
				layer.alert("分差不能超过20",{icon:2});
				return false;
			}else if(beginGrade!=''||endGrade!=''){
				if(beginGrade!=''&&parseInt(beginGrade)!=40&&endGrade==''){
					layer.alert("请输入最高分数",{icon:2});
					return false;
				}else if(beginGrade==''&&parseInt(endGrade)!=40&&endGrade!=''){
					layer.alert("请输入最底分数",{icon:2});
					return false;
				}
			}
			var columns = [
					{
						checkbox : true,
						fieId : 'sClassCode',
					},
					{
						field : 'sClassCode',
						title : '班号',
						align : 'center',
						formatter : function(value, row, index) {
							return "<a onclick='goStudentInfo(\""
									+ row.sClassCode + "\")'>" + value + "</a>";
						}
					},
					{
						field : 'sClassName',
						title : '班级名称',
						align : 'center',
					},
					{
						field : 'sAllTeacherName',
						title : '授课老师',
						align : 'center'
					},
					//     		{
					//     			field : 'sClassTypeName',
					//     			title : '教研组',
					//     			align : 'center',
					//     			formatter:function(value, row, index){
					//     				var op;
					//     				 if("高中数学".indexOf(row.area)==-1&&"高中英语".indexOf(row.area)==-1){
					//                          op=row.area;
					//                      }else if("高中数学".indexOf(row.area)!=-1){
					//                     	 op="高中数学";
					//                      }else if("高中英语".indexOf(row.area)!=-1){
					//                     	 op="高中英语";
					//                      }
					//     				return op;
					//     			}

					//     		}
					//     		,
					{
						field : 'area',
						title : '区域',
						align : 'center'
					},
					{
						field : 'sPrintAddress',
						title : '上课校区',
						align : 'center'
					},
					{
						field : 'sPrintTime',
						title : '上课时间',
						align : 'center'
					},
					{
						field : 'nCurrentCount',
						title : '当前系统人数',
						align : 'center'
					},
					{
						field : 'sLevel',
						title : '班级水平',
						align : 'center',
						formatter:function(value, row, index){
							var op;
							if(row.sLevel=='39'){
								op='40分以下';
							}else {
								op=row.sLevel;
							}
							return op;
						}
					},
					//     		{
					//     			field : 'sClassSubject',
					//     			title : '已上课次',
					//     			align : 'center',
					//     			formatter:function(value, row, index){
					//     				var op;
					//     				op=row.sClassSubject+"/"+row.nLesson;
					//     				return op;
					//     			}
					//     		}, 
					{
						field : 'remark',
						title : '备注',
						align : 'center',
					},
					{
						field : 'bCanRegister',
						title : '是否允许新生报名',
						align : 'center',
						formatter : function(value, row, index) {
							var op;
							if (row.bCanRegister == 'true') {
								op = '是';
							} else {
								op = '否';
							}
							return op;
						}
					},
					{
						field : '',
						title : '操作',
						align : 'center',
						formatter : function(value, row, index) {
							var re = "";
							re += "<sec:authorize ifAnyGranted='ROLE_one6_edit'><button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return edit(\""
									+ row.sClassCode
									+ "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button></sec:authorize>";
							return re;
						}
					} ];
			//初始化Table 不 
			$('#ccrTable').bootstrapTable('destroy');
			$("#ccrTable").bootstrapTable({
				url : jsBasePath + '/jw/xdf/class/query.html', //请求后台的URL（*）
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
				sAllTeacherName : $.trim($("#sAllTeacherName").val()),
				sClassTypeName : $.trim($("#sClassTypeName").val()),
				sAreaAddress : $.trim($("#sAreaAddress").val()),
				sPrintAddress : $.trim($("#sPrintAddress").val()),
				sPrintAddress : $.trim($("#sPrintAddress").val()),
				sPrintTime : $.trim($("#sPrintTime").val()),
				nCurrentCount : $.trim($("#nCurrentCount").val()),
				subject : $.trim($("#subject").val()),
				sLevel : $.trim($("#sLevel").val()),
				sQuarter : $.trim($("#sQuarter").val()),
				beginGrade:$.trim($("#beginGrade").val()),
				endGrade:$.trim($("#endGrade").val()),
				sProjectCode:$.trim($("#sProjectCode").val()),
				nCurrentCount:$.trim($("#nCurrentCount").val())
			};
		}
		//跳转一对6
		function goStudentInfo(sClassCode) {
			var url = jsBasePath + "/stu/admin/list.html?sClassCode="
					+ sClassCode;
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

		//编辑
		function edit(sClassCode) {
			var url = jsBasePath + "/jw/xdf/class/toEdit.html?sClassCode="
					+ sClassCode;
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
		
		function init(){
			var url = jsBasePath + "/jw/xdf/class/toinit.html"
			layer.open({
				type : 2,
				shade : [ 0.5, '#000' ],
				title : "编辑数据", //
				offset : [ '4%' ],
				area : [ '50%', '50%' ],
				content : url, //捕获的元素
				cancel : function(index) {
					layer.close(index);
				},
				end : function() {
		
				}
	});
		}
	</script>
</body>
</html>