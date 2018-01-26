<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline">
				<label class="layui-form-label" style="width:100px;">参数Key:</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" id="key" 
						autocomplete="off" class="layui-input">
				</div>
				</div>
			<div class="layui-inline">
				<label class="layui-form-label">参数名:</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" id="name" autocomplete="off"
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
		</fieldset>
		
		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="paramTable">
            </table>
            <div id="toolbar">
            	<button onClick="add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;添加参数
			</button>
			<button onClick="bath_Disable(0)" type="button"
				class="layui-btn"><li class="fa fa-check-square"></li>
				&nbsp;批量启用
			</button>
			<button onClick="bath_Disable(1)" type="button"
				class="layui-btn layui-btn-warm"><li class="fa fa-minus-square"></li>
				&nbsp;批量禁用
			</button>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$('#paramTable').bootstrapTable('destroy');
    		$("#paramTable").bootstrapTable({
    			url : jsBasePath + '/questionnaire/param/query.html', //请求后台的URL（*）
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
    			showColumns : false, //是否显示所有的列
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
    			},{
    				field : 'key',
    				title : '参数KEY',
    				align : 'center'
    			}, {
    				field : 'name',
    				title : '参数名称',
    				align : 'center' 
    			}, {
    				field : 'sort',
    				title : '排序',
    				align : 'center' 
    			}, {
    				field : 'status',
    				title : ' 状态',
    				align : 'center',
    				formatter : function(value, row, index) {
    					if (value == 0) {
    						return "<font class='normal'>正常</font>";
    					}
    					if (value == 1) {
    						return "<font class='disable'>禁用</font>";
    					}
    				}
    			}, {
    				field : '',
    				align : 'center',
    				title : '操作',
    				switchable : false,
    				formatter : function(value, row, index) {
    					var op = "";
    						op += "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
    					if (row.status == 0) {
    						op += "<button  class='layui-btn layui-btn-warm layui-btn-mini' onclick='return paramDisable(\"" + row.id + "\",1);'><i class='fa fa-minus-square'></i>&nbsp;禁用</button >&nbsp;";
    					}
    					if (row.status == 1) {
    						op += "<button  class='layui-btn layui-btn-mini' onclick='return paramDisable(\"" + row.id + "\",0);'><i class='fa fa-check-square'></i>&nbsp;启用</button >&nbsp;";
    					}
    					return op;
    				}
    			} ],
    			onLoadSuccess : function(dataAll) {},
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
    			key : $.trim($("#key").val()),
    			name : $.trim($("#name").val())
    		};
    	}
    
    	
    	function add(){
    		 var url = jsBasePath+"/questionnaire/param/toAdd.html";
    		  layer.open({
    	      type: 2,
    	      shade : [ 0.5, '#000' ],
    			offset : [ '5%' ],
    	      area: ['520px','300px'],
    	      //scrollbar: false,
    	      title: "新增参数", //不显示标题
    	      content:url, //捕获的元素
    	      cancel: function(index){
    	          layer.close(index);
    	      },
    	      end:function(){
    	      	//query();
    	      }
    	  });
    		  return false;
    	}
    	
    	/**
    	 * 编辑
    	 */
    	function edit(id){
    		 var url = jsBasePath+"/questionnaire/param/toEdit.html?id="+id;
    		  layer.open({
    	       type: 2,
    	       shade : [ 0.5, '#000' ],
    			offset : [ '5%' ],
    	       area: ['520px','300px'],
    	       //scrollbar: false,
    	       title: "编辑参数", //不显示标题
    	       content:url, //捕获的元素
    	       cancel: function(index){
    	           layer.close(index);
    	       },
    	       end:function(){
    	       	//query();
    	       }
    	   });
    		  return false;
    	}
    	
    	$(function() {
    		initTable();
    	});
    	
    	/**
    	 * 禁用、删除用户
    	 */
    	function paramDisable(id,status){
    		var m="";
    		if(status==1){
    			m="确认要禁用参数?";
    		}
    		if(status==0){
    			m="确认要启用参数?";
    		}
    		layer.confirm(m, {
    			  btn: ['是','否'] ,//按钮
    			  offset: '10%',
    			  btnAlign:'c'
    			}, function(index){
    				$.post(jsBasePath+"/questionnaire/param/updateStatus.html",{deleteIds:id,status:status},function(data,status){
    					layer.close(index);
    					if(data!=null){
    						layer.msg(data.message);
    						if(data.flag==true){
    							    $("#paramTable").bootstrapTable('refresh');
    						}
    					}
    				},"json");
    			}, function(index){
    				layer.close(index);
    			});
    	}

    	/**
    	 * 禁用、删除用户(批量)
    	 */
    	function bath_Disable(status){
    		var ids=getSelectId("paramTable");
    		if(ids==""){
    			layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
    			return;
    		}
    		var m="";
    		if(status==1){
    			m="确认要禁用所选参数?";
    		}
    		if(status==0){
    			m="确认要启用所选参数?";
    		}
    		layer.confirm(m, {
    			  btn: ['是','否'] ,//按钮
    			  offset: '10%',
    			  btnAlign:'c'
    			}, function(index){
    				$.post(jsBasePath+"/questionnaire/param/updateStatus.html",{deleteIds:ids,status:status},function(data,status){
    					layer.close(index);
    					if(data!=null){
    						layer.msg(data.message);
    						if(data.flag==true){
    							    $("#paramTable").bootstrapTable('refresh');
    						}
    					}
    				},"json");
    			}, function(index){
    				layer.close(index);
    			});
    	}
    </script>
</body>
</html>