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
    
 String.prototype.endWith=function(endStr){
     var d=this.length-endStr.length;
     return (d>=0&&this.lastIndexOf(endStr)==d)
   } 

    	function showTable(columns) {
    		// 初始化Table 不
    		$('#tableList').bootstrapTable('destroy');
    		$("#tableList").bootstrapTable({
    			url : jsBasePath + '/northamerica/mention/queryInfoPage.html', // 请求后台的URL（*）
    			// method: 'get', //请求方式（*）
    			method : 'post',
    			contentType : "application/x-www-form-urlencoded", // 必须的,post
    			striped : true, // 是否显示行间隔色
    			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    			pagination : true, // 是否显示分页（*）
    			sortable : false, // 是否启用排序
    			// sortOrder : "asc", //排序方式
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
    	
    	//导入成绩
    	function import_score(){
    		layer.open({
    			   type: 2,
    			   title: '导入考试成绩',
    			   shadeClose: false,
    			   shade: 0.8,
    			   offset : ['10%'],
    			   area: ['450px', '320px'],
    			   content: jsBasePath+'/northamerica/mention/importScoreView.html'
    			}); 
    	}
    	
    	//传递的参数
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			examType : $.trim($("#examType").val()),
    			studentCode: $.trim($("#studentCode").val()),		
    			studentName: $.trim($("#name").val()),
    			examStartDate :  $.trim($("#exam_start").val()),
    			examEndDate :  $.trim($("#exam_end").val()),
    			managerTeach:$.trim($("#managerTeach").val()),
    			queryClassCode:$.trim($("#queryClassCode").val()),
    			onlyOne:$("#onlyOne").is(':checked'),
    			teachName:$("#teachName").val()
    		};
    	}
    	
    	function add_score(){
    		layer.open({
 			   type: 2,
 			   title: '录入成绩',
 			   shadeClose: false,
 			   shade: 0.8,
 			   offset : ['10%'],
 			   area: ['470px', '80%'],
 			   content: jsBasePath+'/northamerica/mention/addScoreView.html'
 			}); 
    	}
    	function edit(id){
    		layer.open({
 			   type: 2,
 			   title: '更新成绩',
 			   shadeClose: false,
 			   shade: 0.8,
 			   offset : ['10%'],
 			   area: ['470px', '80%'],
 			   content: jsBasePath+'/northamerica/mention/editScoreView.html?id='+id
 			}); 
    	}
  
    	
    	function del(id){
    		layer.confirm("确定删除所选成绩么?删除后不可恢复!", {
    			  btn: ['是','否'] ,//按钮
    			  offset: '10%',
    			  btnAlign:'c'
    			}, function(index){
    				 var index1 = layer.load(3, {shade: [0.3]});
    				$.post(jsBasePath+"/northamerica/mention/delScore.html",{deleteIds:id},function(data,status){
    					layer.close(index);
    					layer.close(index1);
    					if(data!=null){
    						layer.msg(data.msg);
    						if(data.flag==true){
    							    $("#tableList").bootstrapTable('refresh');
    						}
    					}
    				},"json");
    			}, function(index){
    				layer.close(index);
    		});
    	}
    	//批量删除大学
    	function bath_del(){
    		var ids=getSelectId("tableList");
    		if(ids==""){
    			layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
    			return;
    		}
    		del(ids);
    	}	
    	
    	/**
    	 * 更新学员班级和老师信息
    	 */
    	function refreshClassInfo(){
    				 var index1 = layer.load(3, {shade: [0.3]});
    				$.post(jsBasePath+"/northamerica/mention/refreshClassInfo.html",{},function(data,status){
    					layer.close(index1);
    					if(data.length==0){
    						layer.alert("更新成功!",{icon:1});
    						$("#tableList").bootstrapTable('refresh');
    					}else{
    						var h="更新有记录错误:<br>";
    						$.each(data,function(i,item){
    							h+=data+"<br>";
    						})
    						layer.alert(h,{icon:2});
    						$("#tableList").bootstrapTable('refresh');
    					}
    				},"json");
    	}
    	
    	//导出全部数据
    	function export_list(){
    		window.location.href = jsBasePath+"/northamerica/mention/exportMentionInfo.html?examType="+$("#examType").val()+"&studentCode="+$.trim($("#studentCode").val())
    		+"&studentName="+$.trim($("#name").val())+"&examStartDate="+$.trim($("#exam_start").val())+"&examEndDate="+$.trim($("#exam_end").val())+"&onlyOne="+$("#onlyOne").is(':checked')+"&teachName="+$.trim($("#teachName").val())+"&queryClassCode="+$.trim($("#queryClassCode").val())+"&managerTeach="+$.trim($("#managerTeach").val());
    	}