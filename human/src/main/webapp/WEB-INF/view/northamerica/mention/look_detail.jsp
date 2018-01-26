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
</style>
<body >
	<div class="main-wrap">
		<div style="width: 100%;margin-bottom: 10px;">
			<div style="width: 20%;height: 300px;float: left;">
					<div style="height: 200px;margin-top: 30px;font-size: 16px;line-height: 24px;text-align: center;">
					<table class="layui-table">
						<tr>
							<td style="font-weight: bolder;">学号</td>
							<td>${info.studentCode}</td>
						</tr>
						<tr>
							<td style="font-weight: bolder;">姓名</td>
							<td>${info.studentName}</td>
						</tr>
						<tr>
							<td style="font-weight: bolder;">班号</td>
							<td>${info.lastRoom }</td>
						</tr>
						<tr>
							<td style="font-weight: bolder;">授课老师</td>
							<td>${info.lastRoomTeach }</td>
						</tr>
					</table>
					 <button onClick="initTable()" type="button"
				class="layui-btn"><li class="fa fa-refresh"></li>
				&nbsp;刷新数据
			</button>
				</div>
			</div>
			<div style="width: 80%;height: 300px;float: left;"id="report">
			
			</div>
		</div>
		<div class="y-role">
            <table class="tableList"  id="tableList">
            </table>
            <!-- <div id="toolbar">
             <button onClick="add_score()" type="button"
				class="layui-btn"><li class="fa fa-plus"></li>
				&nbsp;新增
			</button>
            <button onClick="import_score()" type="button"
				class="layui-btn"><li class="fa fa-cloud-upload"></li>
				&nbsp;导入成绩
			</button>
				<button onClick="bath_del()" type="button"
				class="layui-btn layui-btn-danger"><li class="fa fa-remove"></li>
				&nbsp;批量删除
			</button>
			 </div> -->
        </div>
    </div>
    <script src="<%=basePath %>/static/echarts/echarts.3.5.min.js"></script>
    <%-- <script src="<%=basePath %>/static/echarts/echarts.3.5.js"></script> --%>
    <script type="text/javascript">
    var myChart = echarts.init(document.getElementById('report'));
    layui.use(['laydate','form'], function() {
    	var form = layui.form();
    	var laydate = layui.laydate;
    	initTable();
    });
    
    function initreport_1(){
    	myChart.showLoading()
    	$.post(jsBasePath+"/northamerica/mention/queryDetailReport.html",{examType:${info.examType},studentCode:"${info.studentCode}"},function (data) {
    		var legend=new Array();
    		var listenScore=new Array();
    		var speakScore=new Array();
    		var readScore=new Array();
    		var writeScore=new Array();
    		var totalScore=new Array();
    		$.each(data,function(i,info){
       		   legend[legend.length]=info.examDate;
       		   listenScore[listenScore.length]=info.listenScore;
       		   speakScore[speakScore.length]=info.speakScore;
       			readScore[readScore.length]=info.readScore;
       		  writeScore[writeScore.length]=info.writeScore;
       		totalScore[totalScore.length]=info.totalScore;
       	   });
    		myChart.hideLoading() // 填入数据
    	    	myChart.setOption({
    				title:{text:'提分情况'/* ,subtext:subtext */},
    			    tooltip : {
    			    	trigger: 'axis'
    			    },
    			    legend: {
    			        data:['阅读','听力','口语','写作','总分']
    			        //data:nodeArray
    			    },
    			    grid: {
    			        left: '3%',
    			        right: '4%',
    			        bottom: '3%',
    			        containLabel: true
    			    },
    			    toolbox: {
    			        feature: {
    			            saveAsImage: {}
    			        }
    			    },
    			    xAxis:  {
    			    	type: 'category',
    			    	oundaryGap: false,
    			    	data:legend
    			    },
    			    yAxis: {
    			        type: 'value'
    			    },
    			    series: [ {
		                 name:'阅读',
		                 type:'line',
		                 stack: '阅读',
		                 data:readScore
		             },
    			             {
    			                 name:'听力',
    			                 type:'line',
    			                 stack: '听力',
    			                 data:listenScore
    			             },
    			             {
    			                 name:'口语',
    			                 type:'line',
    			                 stack: '口语',
    			                 data:speakScore
    			             },
    			             {
    			                 name:'写作',
    			                 type:'line',
    			                 stack: '写作',
    			                 data:writeScore
    			             },
    			             {
    			                 name:'总分',
    			                 type:'line',
    			                 stack: '总分',
    			                 data:totalScore
    			             }
    			         ]
    			});
    	},'JSON').done();
    }
    
    
    
    function initTable(){
    	var type=${info.examType};
    	 var columns = [{
    		 	field : 'managerTeach',
    		 	title : '学管师',
				align: 'center'
    		},{
				field : 'examType',
				title : '考试类型',
				align: 'center',
				formatter : function(value, row, index) {
						if(value==1){
							return "雅思";
						}
						if(value==2){
							return "托福";
						}
						if(value==3){
							return "ACT";
						}
						if(value==4){
							return "GRE";
						}
						if(value==5){
							return "SAT";
						}
				}
			}, {
				field : 'examDate',
				title : '考试日期',
				align : 'center'
			}, {
				field : 'totalScore',
				title : '总分',
				align : 'center' 
			}];
    		if(type==1||type==2||type==""){
    			   columns.push(
    					   {
   		    				field : 'readScore',
   		    				title : '阅读',
   		    				align : 'center'
   		    			}, {
    		    				field : 'listenScore',
    		    				title : '听力',
    		    				align : 'center' 
    		    			}, {
    		    				field : 'speakScore',
    		    				title : '口语',
    		    				align : 'center'
    		    			},{
    		    				field : 'writeScore',
    		    				title : '写作',
    		    				align : 'center'
    		    			});
        	}
    		 columns.push({
				field : 'updateTime',
				title : '更新时间',
				align : 'center'
			}, {
				field : 'uploadUserName',
				title : '更新人员',
				align : 'center'
			}/* , {
				field : '',
				align : 'center',
				title : '操作',
				switchable : false,
				formatter : function(value, row, index) {
					var op = "";
					op += "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
					op += "<button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return del(\"" + row.id + "\",-1);'><i class='fa fa-remove'></i>&nbsp;删除</button >";
					return op;
				}
			} */);
    		 showTable(columns);
    }
    	function showTable(columns) {
    		//初始化Table 不 
    		$('#tableList').bootstrapTable('destroy');
    		$("#tableList").bootstrapTable({
    			url : jsBasePath + '/northamerica/mention/queryInfoPage.html', //请求后台的URL（*）
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
    			height:$(window).height()-35,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    			uniqueId : "id", //每一行的唯一标识，一般为主键列
    			showToggle : false, //是否显示详细视图和列表视图的切换按钮
    			cardView : false, //是否显示详细视图
    			detailView : false, //是否显示父子表
    			smartDisplay : true, // 智能显示 pagination 和 cardview 等
    			//toolbar : '#toolbar', //工具按钮用哪个容器
    			//toolbarAlign : 'left',
    			columns : columns,
    			onLoadSuccess : function(dataAll) {
    				initreport_1();
    			},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	};
    	
    	//导入成绩
    	function import_score(){
    		layer.open({
    			   type: 2,
    			   title: '导入考试成绩',
    			   shadeClose: false,
    			   shade: 0.8,
    			   offset : ['10%'],
    			   area: ['450px', '220px'],
    			   content: jsBasePath+'/northamerica/mention/importScoreView.html'
    			}); 
    	}
    	
    	//传递的参数
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			examType : ${info.examType},
    			studentCode : "${info.studentCode}"
    		};
    	}
    </script>
</body>
</html>