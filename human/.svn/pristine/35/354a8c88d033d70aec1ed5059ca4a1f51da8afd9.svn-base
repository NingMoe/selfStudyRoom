function save(){
	saveData();
}
function delCurrentRow(id){
	if(id>=100000000){
		$("#dicDataTable").bootstrapTable("removeByUniqueId",id);
		layer.alert("删除成功",{icon:1},function(){
				
			    location.reload(); 
		})
		return false ;
	}
	layer.confirm("确定删除该条数据么?删除后不可恢复!", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/Examinee/List/delete.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.alert(data.message,{icon:1},function(){
						if(data.flag==true){
						    $("#dicDataTable").bootstrapTable('refresh');
						    location.reload(); 
					}
					});
					
					return false;
				}
			},"json");
		}, function(index){
			layer.close(index);
	});
	}
function add(){
	var table = $("#dicDataTable");  
	var nodata = table.find(".no-records-found").size();
	if(nodata==1){
		table.find(".no-records-found").remove();
	}
	var index = table.find("tr").size();
	var currId = addIndex;
	var ids =  table.find("tr");
	var newRow = {
			"id":currId,
			"name":"随堂测",
			"tkTearcher":"",
			"dTearcher":"",
			"yfTearcher":"",
			"lTearcher":"",
			"sTearcher":"",
			"wTearcher":"",
			"rTearcher":"",
			"frequency":"请选择",
			"stage":"预备",
			"cice":"",
			};
	 $("#dicDataTable").bootstrapTable("insertRow", {index:index,row:newRow });
	 addIndex++;
}

function validateData(){
	var table = $("#dicDataTable"),result=true;  
	var type = $("#type").val();
	var isValueUnique = $("#isValueUnique").val();
	var nameArr = table.find("a[data-name='name']").map(function(){
		return this.text;
	}).get().sort();
	
	for(var i = 0; i < nameArr.length - 1; i++){
	   if (nameArr[i] == nameArr[i+1]){
		   layer.alert("名称存在重复",{icon:2});
		   result = false;
		   return result;
	    }
	}
	
	if(type=="1" && isValueUnique=="1"){
		var valueArr = table.find("a[data-name='dataValue']").map(function(){
			return this.text;
		}).get().sort();
		
		for(var i = 0; i < valueArr.length - 1; i++){
			if (valueArr[i] == valueArr[i+1]){
				layer.alert("字典值存在重复",{icon:2});
				result = false;
				return result;
			}
		}
	}
	
	
	table.find("a").each(function(){
		if($(this).text()=="点击编辑"){
			var index = $(this).parents("tr").attr("data-index");
			layer.alert("第"+(parseInt(index)+1)+"行填写未完成",{icon:2});
			result = false;
		}
	});
	return result;
}
function saveData(){
	var table = $("#dicDataTable");  
	var rows = table.bootstrapTable("getData",false);
 	$.ajax({
		url : jsBasePath + '/Examinee/List/editData.html',
		async : false,
		method : 'POST',
		data : {
			"jsonStr":JSON.stringify(rows),
			code:$("#code").val()
		},
		dataType : "json",
		success : function(data) {
			// 隐藏
			if (data.flag) {
				layer.alert(data.message, {icon: 1});
				$("#dicDataTable").bootstrapTable('refresh');
			} else {
				layer.alert(data.message, {icon: 2});
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			/* alert(2); */
		}
	});
}
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		type : $.trim($("#type").val()),
		stage : $.trim($("#stage").val())
	};
}
function initTable() {
	var options = [ {
		checkbox : true,
		fieId : 'id'
	}, {
		field : '',
		title : '序号',
		align : 'center',
		formatter : function(value, row, index) {
//			var page = $("#ccrTable").bootstrapTable("getPage");
			return  index + 1;
		}
	},{
		field : 'name',
		title : '考试类型',
		align : 'center',
		editable: {
            type: 'select',
            title: '考试类型',
            source:[{value:"随堂测",text:"随堂测"},{value:"词测",text:"词测"},{value:"阶段测试",text:"阶段测试"},{value:"模考",text:"模考"},{value:"实考",text:"实考"}]
        }
	},{
		field : 'stage',
		title : '阶段',
		align : 'center',
		editable: {
            type: 'select',
            title: '阶段',
            source:[{value:"预备",text:"预备"},{value:"基础",text:"基础"},{value:"强化",text:"强化"},{value:"冲刺",text:"冲刺"}]
        }
	},
	{
		field : 'frequency',
		title : '第几次',
		align : 'center',
		editable: {
            type: 'select',
            title: '第几次',
            source:[{value:"第1次",text:"第1次"},{value:"第2次",text:"第2次"},{value:"第3次",text:"第3次"},{value:"第4次",text:"第4次"},{value:"第5次",text:"第5次"}
            		,{value:"第6次",text:"第6次"},{value:"第7次",text:"第7次"},{value:"第8次",text:"第8次"},{value:"第9次",text:"第9次"},{value:"第10次",text:"第10次"}
            		,{value:"第11次",text:"第11次"},{value:"第12次",text:"第12次"},{value:"第13次",text:"第13次"},{value:"第14次",text:"第14次"},{value:"第15次",text:"第15次"},{value:"第16次",text:"第16次"}]
        }
	},{
		field : 'tkTearcher',
		title : '听口成绩',
		align : 'center',
		editable: {
            type: 'text',
            title: '听口',
            emptytext:"--",
//            validate: function (v) {
//                if (!v) return '名称不能为空';
//            }
	}
	},{
		field : 'dTearcher',
		title : '读写成绩',
		align : 'center',
		editable: {
            type: 'text',
            title: '读写',
			emptytext:"--",
	}
	},{
		field : 'yfTearcher',
		title : '语法成绩',
		align : 'center',
		editable: {
            type: 'text',
            title: '语法',
            emptytext:"--",
		}
	},{
		field : 'lTearcher',
		title : '听力',
		align : 'center',
		editable: {
            type: 'text',
            title: 'L',
            emptytext:"--",
          },
	},{
		field : 'rTearcher',
		title : '阅读',
		align : 'center',
		editable: {
            type: 'text',
            title: 'R',
//            isDel:{callback:isDel}
            emptytext:"--",
	}
	},{
		field : 'sTearcher',
		title : '口语',
		align : 'center',
		editable: {
            type: 'text',
            title: 'S',
            emptytext:"--",
	}
	},{
		field : 'wTearcher',
		title : '写作',
		align : 'center',
		editable: {
            type: 'text',
            title: 'W',
            emptytext:"--",
	}
	},{
		field : 'cice',
		title : '词测成绩',
		align : 'center',
		editable: {
            type: 'text',
            title: '词测成绩',
            emptytext:"--",
	}
	}];
	
	options.push({field : 'key',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var op = "<button  class='layui-btn layui-btn-danger layui-btn-mini' onclick='return delCurrentRow(\"" + row.id + "\");'><i class='fa fa-remove'></i>&nbsp;删除</button >";
				return op;
			}
		});
	//初始化Table 不 
	$('#dicDataTable').bootstrapTable('destroy');
	$("#dicDataTable").bootstrapTable({
		url : jsBasePath + '/Examinee/List/queryData.html', //请求后台的URL（*）
		//method: 'get',      //请求方式（*）
		dataField: "data",
		method : 'post',
		contentType : "application/x-www-form-urlencoded", //必须的,post
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : false, //是否显示分页（*）
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
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns :options,
		onLoadSuccess : function(dataAll) {},
		onLoadError : function() {
			//mif.showErrorMessageBox("数据加载失败！");
		},
		onEditableSave: function (field, row, oldValue, $el) {
		}
	});
}
function updateexcle(stage){
	var code=$("#code").val();
	layer.open({
		type: 2,
		title: '批量导入',
		shadeClose: false,
		shade: 0.8,
		offset : ['20%'],
		area: ['393px', '248px'],
		content: jsBasePath+'/Examinee/List/updateexcel.html?code='+code+"&stage="+stage,
		end:function(){
			$("#processDefTable").bootstrapTable('refresh');
			
				    location.reload(); 
		}
	});
}
//批量删除
function bath_del(){
	var ids=getSelectId("dicDataTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	delCurrentRow(ids);
}

