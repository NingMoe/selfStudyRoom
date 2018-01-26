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
				<label class="layui-form-label" style="width:100px;">标题:</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" id="title" 
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
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="paramTable">
            </table>
                 <div id="toolbar">
            	<button onClick="add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;添加
			</button>
			<button onClick="bath_delForm()" type="button"
				class="layui-btn layui-btn-danger"><li class="fa fa-remove"></li>
				&nbsp;批量删除
			</button>
            </div>
    </div>
    <script type="text/javascript">
    	function initTable() {
    		//初始化Table 不 
    		$('#paramTable').bootstrapTable('destroy');
    		$("#paramTable").bootstrapTable({
    			url : jsBasePath + '/questionnaire/collect/query.html', //请求后台的URL（*）
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
    			showRefresh : true, //是否显示刷新按钮
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
    				field : 'id',
    				title : '编号',
    				align : 'center'
    			},{
    				field : 'title',
    				title : '标题',
    				align : 'center'
    			}, {
    				field : 'userName',
    				title : '添加人',
    				align : 'center' 
    			}, {
    				field : 'startTime',
    				title : '生效时间',
    				align : 'center' 
    			}, {
    				field : 'endTime',
    				title : '失效时间',
    				align : 'center' 
    			}, {
    				field : 'createTime',
    				title : '添加时间',
    				align : 'center' 
    			}, {
    				field : 'collectDesc',
    				title : ' 收集参数',
    				align : 'center'
    			}, {
    				field : 'collectCount',
    				title : ' 条数',
    				align : 'center'
    			},{
    				field : '',
    				align : 'center',
    				title : '操作',
    				switchable : false,
    				formatter : function(value, row, index) {
    					var op = "";
    						op += "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
    						op += "<button  class='layui-btn layui-btn-mini' onclick='return showCode(\"" + row.id + "\");'><i class='fa fa-file-code-o'></i>&nbsp;代码</button >&nbsp;";
    						op += "<button  class='layui-btn layui-btn-mini' onclick='return queryResult(\"" + row.id + "\");'><i class='fa fa-search'></i>&nbsp;收集结果</button >&nbsp;";
    						op += "<button  class='layui-btn layui-btn-mini layui-btn-warm' onclick='return showIp(\"" + row.id+"\");'><i class='fa fa-wrench'></i>&nbsp;禁用IP管理</button >&nbsp;";
    						op += "<button  class='layui-btn layui-btn-mini layui-btn-normal' onclick='return exportResult(\"" + row.id + "\",\""+row.title+"\");'><i class='fa fa-download'></i>&nbsp;导出结果</button >&nbsp;";
    						op += "<button  class='layui-btn layui-btn-mini layui-btn-danger' onclick='return delForm(\"" + row.id + "\");'><i class='fa fa-remove'></i>&nbsp删除</button >&nbsp;";
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
    			title : $.trim($("#title").val())
    		};
    	}
    
    	
    	function add(){
    		 var url = jsBasePath+"/questionnaire/collect/toAdd.html";
    		  layer.open({
    	      type: 2,
    	      shade : [ 0.5, '#000' ],
    			offset : [ '5%' ],
    	      area: ['600px','80%'],
    	      //scrollbar: false,
    	      title: "新增", //不显示标题
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
    	

    	function 	showIp(id){
    		 var url = jsBasePath+"/questionnaire/collect/showIp.html?formId="+id;
    		  layer.open({
    	      type: 2,
    	      shade : [ 0.5, '#000' ],
    			offset : [ '5%' ],
    	      area: ['600px','80%'],
    	      //scrollbar: false,
    	      title: "禁用IP管理", //不显示标题
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
    		 var url = jsBasePath+"/questionnaire/collect/toEdit.html?id="+id;
    		  layer.open({
    	       type: 2,
    	       shade : [ 0.5, '#000' ],
    			offset : [ '5%' ],
    			  area: ['600px','80%'],
    	       //scrollbar: false,
    	       title: "编辑", //不显示标题
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
    	function showCode(id){
    		 var url = jsBasePath+"/questionnaire/collect/showCode.html?id="+id;
    		  layer.open({
    	       type: 2,
    	       shade : [ 0.5, '#000' ],
    			offset : [ '5%' ],
    			  area: ['80%','80%'],
    	       //scrollbar: false,
    	       title: "代码查看", //不显示标题
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
    	
    	function queryResult(id){
   		 var url = jsBasePath+"/questionnaire/collect/toQueryResult.html?id="+id;
   		  layer.open({
   	       type: 2,
   	       shade : [ 0.5, '#000' ],
   			offset : [ '5%' ],
   			  area: ['90%','80%'],
   	       //scrollbar: false,
   	       title: "收集结果", //不显示标题
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
    	导出收集结果
    	**/
    	function exportResult(id,name){
    		window.location.href = jsBasePath+"/questionnaire/collect/exportResult.html?id="+id+"&formName="+name;
    	}
    	
    	$(function() {
    		initTable();
    	});
    	
    	
    	function delForm(id){
    		layer.confirm("确认删除该报表?", {
    			  btn: ['是','否'] ,//按钮
    			  offset: '10%',
    			  btnAlign:'c'
    			}, function(index){
    				$.post(jsBasePath+"/questionnaire/collect/deleteForm.html",{deleteIds:id},function(data,status){
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
    	
    	function bath_delForm(){
    		var ids=getSelectId("paramTable");
    		if(ids==""){
    			layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
    			return;
    		}
    		layer.confirm("确认删除所选报表?", {
    			  btn: ['是','否'] ,//按钮
    			  offset: '10%',
    			  btnAlign:'c'
    			}, function(index){
    				$.post(jsBasePath+"/questionnaire/collect/deleteForm.html",{deleteIds:ids},function(data,status){
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