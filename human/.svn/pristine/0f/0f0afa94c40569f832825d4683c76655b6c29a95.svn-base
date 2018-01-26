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
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;"  id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label" style="white-space: nowrap;">姓名:</label>
					<div class="layui-input-inline" >
							<input class="layui-input"   placeholder="应聘者姓名模糊匹配" id="name"  >
					</div>
					</div>
					<div class="layui-inline">
				<label class="layui-form-label" style="white-space: nowrap;">手机号:</label>
					<div class="layui-input-inline" >
							<input class="layui-input"   placeholder="应聘者手机号精确匹配" id="tel" >
					</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">状态:</label>
						<div class="layui-form layui-input-inline">
							<select id="state">
								<option value="">请选择</option>
								<option value="1" selected>待跟进</option>
								<option value="2">跟进结束</option>
							</select>
						</div>
					</div>
					<div class="layui-inline">
							<button type="button" onclick="initTable();" class="layui-btn">
								<li class="fa fa-search"></li> &nbsp;查询
							</button>
						</div>
					</div>
					</div>
		</fieldset>
		<div class="y-role" style="margin-top: 10px;">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="tableList">
            </table>
            <div id="toolbar">
            </div>
        </div>
    </div>
   <script type="text/javascript">
   layui.use(['form'], function(){
 	  var form = layui.form();
   });
    	function initTable() {
    		$('#tableList').bootstrapTable('destroy');
    		//初始化Table 不 
    		$("#tableList").bootstrapTable({
    			url : jsBasePath + '/recruit/acceptance/toLinkQuery.html', //请求后台的URL（*）
    			method : 'post',
    			contentType : "application/x-www-form-urlencoded", //必须的,post
    			sidePagination : "server",
    			striped : true, //是否显示行间隔色
    			pagination : true, //是否显示分页（*）
    			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    			sortable : false, //是否启用排序
    			//sortOrder : "asc", //排序方式
    			pageNumber : 1, //初始化加载第一页，默认第一页
    			pageSize : 10, //每页的记录行数（*）
    			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
    			queryParams : queryParams, //传递参数（*）
    			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    			strictSearch : false,
    			showRefresh : false, //是否显示刷新按钮
    			minimumCountColumns : 2, //最少允许的列数
    			clickToSelect : false, //是否启用点击选中行
    			//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    			uniqueId : "id", //每一行的唯一标识，一般为主键列
    			showToggle : false, //是否显示详细视图和列表视图的切换按钮
    			cardView : false, //是否显示详细视图
    			detailView : false, //是否显示父子表
    			columns : [
    			   {
    				field : 'name',
    				title : '应聘者',
    				align : 'center'
    			}, {
    				field : 'sex',
    				title : '性别',
    				align : 'center',
    				formatter : function(value, row, index) {
    					if(value=="F"){
    						return "女";
    					}
    					if(value=="M"){
    						return "男";
    					}
    				}
    			},{
    				field : 'phone',
    				title : '手机',
    				align : 'center' 
    			},{
    				field : 'email',
    				title : '邮箱',
    				align : 'center' ,
    				formatter : function(value, row, index) {
						if(value!=''){
							return "<a href='Mailto:"+value+"'>"+value+"</a>";
						}
				}
    			}, {
    				field : 'graSchool',
    				title : '学校',
    				align : 'center'
    			}, {
    				field : 'highEdu',
    				title : '学历',
    				align : 'center'
    			}, {
    				field : 'workTime',
    				title : '工作时间',
    				align : 'center'
    			}, {
    				field : 'preTime',
    				title : '上次沟通时间',
    				align : 'center'
    			}, {
    				field : 'nextTime',
    				title : '下次沟通时间',
    				align : 'center'
    			}, {
    				field : '',
    				align : 'center',
    				title : '操作',
    				switchable : false,
    				formatter : function(value, row, index) {
    					var op = "";
    					op += "<button  class='layui-btn layui-btn-mini layui-btn-normal' onclick='return subResumel(\"" + row.id + "\",\"" + row.name + "\");'><i class='fa fa-check-square'></i>&nbsp;查看</button >&nbsp;";
    					return op;
    				}
    			}],
    			onLoadSuccess : function(dataAll) {},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	};
    	
    	//传递的参数
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			name:$("#name").val(),
    			phone:$("#tel").val(),
    			state:$("#state").val()
    		};
    	}
    	
    	$(function() {
    		initTable();
    	});
    	
    	//查看简历详情
    	function jdDesc(id,flowCode){
    		if(flowCode=="null"){
    			flowCode="";
    		}
    		var winObj=window.open(jsBasePath + "/recruit/acceptance/jdDesc.html?id="+id+"&flowCode="+flowCode,"","menubar=no,toolbar=no,location=no,scrollbars=yes,width="+window.screen.availWidth+",height="+window.screen.availHeight+"" );
    		 var loop = setInterval(function() {       
    		        if(winObj.closed) {      
    		            clearInterval(loop);      
    		            location.reload();   
    		        }      
    		    }, 100);    
    	}
    </script>
</body>
</html>