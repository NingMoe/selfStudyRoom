<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
.layui-form-item {
	margin-bottom: 0px ;
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
				<label class="layui-form-label" style="width: 100px;">机构:</label>
				<div class="layui-input-inline" >
					<select id="companyId"    lay-filter="companyId">
					<option value="">请选择</option>
					<c:forEach var="com" items="${companyList}">
								<option value="${com.companyId}">${com.companyName}</option>
					</c:forEach>
					</select>
				</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">部门:</label>
					<div class="layui-input-inline" >
					<!-- <select d="deptId" class="selectpicker bla bla bli" multiple
						data-live-search="true" > -->
								<select id="deptId" lay-search="">
									<option value="">请选择</option>
								</select>
							</div>
				</div>
				<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">AD状态:</label>
				<div class="layui-input-inline" >
					<select id="hrStatus"   >
					<option value="">请选择</option>
					<option value="A">有效</option>
					<option  value="I">无效</option>
					</select>
				</div>
				</div>
				<div class="layui-inline">
				<label class="layui-form-label">学校:</label>
				<div class="layui-input-inline" >
					<input type="text" id="graduageSch" 
						class="layui-input">
				</div>
			</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">工号:</label>
				<div class="layui-input-inline" >
					<input type="text" id="empId" autocomplete="off"
						class="layui-input">
				</div>
				</div>
			<div class="layui-inline">
				<label class="layui-form-label">姓名:</label>
				<div class="layui-input-inline">
					<input type="text" id="name" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">手机:</label>
				<div class="layui-input-inline" >
					<input type="text" id="empPhone" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">邮箱:</label>
				<div class="layui-input-inline" >
					<input type="text" id="emailAddr" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			</div>
			<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">入职日期:</label>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="开始日期" id="join_start">
						</div>
						<div class="layui-form-mid">-</div>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="截止日期" id="join_end">
						</div>
					</div>
				<div class="layui-inline">
				<label class="layui-form-label">生日:</label>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="开始日期" id="birth_start">
						</div>
						<div class="layui-form-mid">-</div>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="截止日期" id="birth_end">
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
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="tableList">
            </table>
            <div id="toolbar">
            <sec:authorize ifAnyGranted='ROLE_import_teach'>
            <button onClick="import_teach()" type="button"
				class="layui-btn"><li class="fa fa-cloud-upload"></li>
				&nbsp;导入导师配置
			</button></sec:authorize>
			 <sec:authorize ifAnyGranted='ROLE_import_super'>
			<button onClick="import_super()" type="button"
				class="layui-btn"><li class="fa fa-cloud-upload"></li>
				&nbsp;导入汇报关系
			</button></sec:authorize>
			<sec:authorize ifAnyGranted='ROLE_import_emp_phone'>
			<button onClick="import_phone()" type="button"
				class="layui-btn"><li class="fa fa-cloud-upload"></li>
				&nbsp;导入手机号
			</button>
			</sec:authorize>            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/basic/js/employee/emp_list.js"></script>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$('#tableList').bootstrapTable('destroy');
    		$("#tableList").bootstrapTable({
    			url : jsBasePath + '/basic/employee/queryEmp.html', //请求后台的URL（*）
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
    			height:$(window).height()-$("#serachFrom").height()-52,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    			uniqueId : "id", //每一行的唯一标识，一般为主键列
    			showToggle : false, //是否显示详细视图和列表视图的切换按钮
    			cardView : false, //是否显示详细视图
    			detailView : false, //是否显示父子表
    			smartDisplay : true, // 智能显示 pagination 和 cardview 等
    			toolbar : '#toolbar', //工具按钮用哪个容器
    			toolbarAlign : 'left',
    			columns : [ {
    				field : 'comName',
    				title : '机构',
    				align: 'center'
    			}, {
    				field : 'companyName',
    				title : 'AD机构',
    				align : 'center'
    			}, {
    				field : 'deptName',
    				title : 'AD部门',
    				align : 'center' 
    			}, {
    				field : 'empId',
    				title : '工号',
    				align : 'center'
    			}, {
    				field : 'name',
    				title : '姓名',
    				switchable : false,
    				align : 'center' 
    			},{
    				field : 'emailAddr',
    				title : '邮箱',
    				align : 'center' ,
    				formatter : function(value, row, index) {
    						if(value!=''){
    							return "<a href='Mailto:"+value+"@xdf.cn'>"+value+"@xdf.cn</a>";
    						}
    				}
    			}, {
    				field : 'joinDate',
    				title : '入职日期',
    				align : 'center'
    			}, {
    				field : 'birthDate',
    				title : '生日',
    				align : 'center'
    			}, {
    				field : 'graduageSch',
    				title : '学校',
    				align : 'center'
    			}, {
    				field : 'major',
    				title : '专业',
    				align : 'center'
    			}, {
    				field : 'highestEduc',
    				title : '学历',
    				align : 'center'
    			}/*, {
    				field : 'nationalId',
    				title : '身份证',
    				align : 'center'
    			} , {
    				field : 'empPhone',
    				title : '手机号',
    				align : 'center'
    			} */, {
    				field : 'synTime',
    				title : '同步时间',
    				align : 'center'
    			}, {
    				field : 'hrStatus',
    				title : ' AD状态',
    				align : 'center',
    				formatter : function(value, row, index) {
    					if (value == 'A') {
    						return "<font class='normal'>正常</font>";
    					}
    					if (value ==  'I') {
    						return "<font class='disable'>禁用</font>";
    					}
    				}
    			}, {
    				field : '',
    				title : ' 汇报人',
    				align : 'center',
    				formatter : function(value, row, index) {
    					var t = "<a href='javascript:;' <sec:authorize ifAnyGranted='ROLE_cfig_super'> onclick='toCfigSuper(\""+row.empId+"\",\""+row.name+"\")' </sec:authorize> title='";
    					var ti = "";
    					if (row.listSuper.length > 0) {
    						$.each(row.listSuper, function(i, empSuper) {
    							ti += empSuper.superName + "\n";
    						})
    					}else {
    						ti = "暂未配置";
    					}
    					t +=ti+"'>" + row.listSuper.length + "</a>";
    					return t;
    				}
    			},{
    				field : '',
    				title : ' 导师',
    				align : 'center',
    				formatter : function(value, row, index) {
    					var t = "<a href='javascript:;' <sec:authorize ifAnyGranted='ROLE_cfig_teach'> onclick='toCfigTeach(\""+row.empId+"\",\""+row.name+"\")' </sec:authorize>  title='";
    					var ti = "";
    					if(row.listTeach.length>0){
    						$.each(row.listTeach, function(i,empTeach){
    							ti+=empTeach.teachName+"\n";
    						})
    					}else{
    						ti = "暂未配置";
    					}
    					t +=ti+"'>" + row.listTeach.length + "</a>";
    					return t;
					}
    			}],
    			onLoadSuccess : function(dataAll) {},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	};
    	
    </script>
</body>
</html>