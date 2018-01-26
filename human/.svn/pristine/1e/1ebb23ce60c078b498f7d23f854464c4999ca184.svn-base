<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<script type="text/javascript">
var userId=${userId};

</script>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline">
				<label class="layui-form-label">角色名:</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" id=roleName 
						autocomplete="off" class="layui-input">
				</div>
				</div>
			<div class="layui-inline">
				<button onClick="initTable()" type="button"
				class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			</button>
			</div>
		</div>
		</fieldset>
		<div class="y-role">
            <table class="tableList"  id="roleTable">
            </table>
              <div id="toolbar" style="color:#1AA194; ">
            	<button onClick="cfgUserRole()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;确认角色配置
			</button>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    var otherPageRoleIds=[];
    var allRoleIds=${roles};
    	function initTable() {
    		//初始化Table 不 
    		$('#roleTable').bootstrapTable('destroy');
    		$("#roleTable").bootstrapTable({
    			url : jsBasePath + '/manager/role/getRoles.html', //请求后台的URL（*）
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
    			//showColumns : true, //是否显示所有的列
    			showRefresh : false, //是否显示刷新按钮
    			minimumCountColumns : 2, //最少允许的列数
    			clickToSelect : false, //是否启用点击选中行
    			//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    			uniqueId : "id", //每一行的唯一标识，一般为主键列
    			showToggle : false, //是否显示详细视图和列表视图的切换按钮
    			cardView : false, //是否显示详细视图
    			detailView : false, //是否显示父子表
    			smartDisplay : true, // 智能显示 pagination 和 cardview 等
    			toolbar : '#toolbar', //工具按钮用哪个容器
    			toolbarAlign : 'left',
    			columns : [ {
    				checkbox : true,
    				fieId : 'id'
    			}, {
    				field : 'roleName',
    				title : '角色名',
    				align : 'center'
    			}, {
    				field : 'roleDesc',
    				title : '角色说明',
    				align : 'center' /* ,
        			formatter: function (value, row, index) {
            	    	if(value==1){
            	    		return  "准考证通知";
            	    	}
            	    	if(value==0){
            	    		return  "查分通知";
            	    	}
            	    } */
    			}, {
    				field : 'createUserName',
    				title : '创建人',
    				align : 'center'
    			}, {
    				field : 'createTime',
    				title : '创建时间',
    				align : 'center'
    			}, {
    				field : 'updateUserName',
    				title : '修改人',
    				align : 'center'
    			}, {
    				field : 'updateTime',
    				title : '修改时间',
    				align : 'center'
    			}],
    			onLoadSuccess : function(dataAll) {
   				 	$("#roleTable").bootstrapTable("checkBy", {field:"id", values:allRoleIds}) ;
    				var curPageRoleIds = $("tr.selected").map(function(){return $(this).attr("data-uniqueid")+""});
    				otherPageRoleIds.length=0;
    				for(var i=0;i<allRoleIds.length;i++){
    					if($.inArray(allRoleIds[i]+"", curPageRoleIds)==-1 &&$.inArray(allRoleIds[i]+"", otherPageRoleIds)==-1){
    						otherPageRoleIds.push(allRoleIds[i]+"");
    					}
    				}
    				
    			},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	}
    	;
    	//传递的参数
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			roleName : $.trim($("#roleName").val())
    		};
    	}
    
    	$(function() {
    		initTable();
    	});
    	
    	/**
    	 * 确认配置角色
    	 */
    	function cfgUserRole(){
    		var currSelectIds = $($("#roleTable").bootstrapTable('getSelections')).map(function(){return this.id+""}).get();
    		var idsArr = currSelectIds;
    		if(otherPageRoleIds.length){
    			idsArr = otherPageRoleIds.concat(currSelectIds);
    		}
    		var ids = idsArr.join(",");
    		var m ="";
    		if(idsArr.length==0){
    			m="当前用户未配置任何角色，是否继续?";
    		}else{
    			m="确定为当前用户设置所选角色?";
    		}
    		layer.confirm(m, {
    			  btn: ['是','否'] ,//按钮
    			  offset: '10%',
    			  btnAlign:'c'
    			}, function(index){
    				 var index1 = layer.load(3, {shade: [0.3]});
    				$.post(jsBasePath+"/manager/user/cfgUserRole.html",{roleIds:ids,userId:userId},function(data,status){
    					layer.close(index1);
    					layer.close(index);
    					if(data!=null){
    						layer.alert(data.message,function(index2){
    							layer.close(index2);
    							location.href=jsBasePath+"/manager/user/toCfgUserRole.html?userId=${userId}";
    						});
    						
    					}
    				},"json");
    			}, function(index){
    				layer.close(index);
    			});
    	}
    </script>
</body>
</html>