var companyId;
$(function() {
	initTable();
});
function initTable() {
	//初始化Table 不 
	$('#mailNoticeTypeTable').bootstrapTable('destroy');
	$("#mailNoticeTypeTable").bootstrapTable({
		url : jsBasePath + '/basic/mailNoticeType/queryTem.html', //请求后台的URL（*）
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
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : false, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		smartDisplay : true, // 智能显示 pagination 和 cardview 等
		toolbar : '#toolbar', //工具按钮用哪个容器
		toolbarAlign : 'left',
		columns : [{
			checkbox : true,
			fieId : 'id'
		}, {
			field : 'companyName',
			title : '机构',
			align: 'center'
		}, {
			field : 'deptName',
			title : '部门',
			align : 'center' 
		}, {
			field : 'name',
			title : '类型名称',
			switchable : false,
			align : 'center' 
		}, {
			field : 'isValid',
			title : ' 状态',
			align : 'center',
			formatter : function(value, row, index) {
				if (value == '0') {
					return "<font class='normal'>正常</font>";
				}
				if (value ==  '1') {
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
				if (row.isValid == 0) {
				    op += "<button  class='layui-btn layui-btn-mini' style='margin-right:10px;' onclick='return toEdit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >";
					op += "<button  class='layui-btn layui-btn-danger layui-btn-mini' style='margin-right:10px;' onclick='return delTem(\"" + row.id + "\",1);'><i class='fa fa-minus-square'></i>&nbsp;禁用</button >";
				}
				return op;
			}
		}],
		onLoadSuccess : function(dataAll) {},
		onLoadError : function() {
		}
	});
}
layui.use(['form'], function() {
	var form = layui.form();
	  form.on('select(companyId)', function(data){
		  if(companyId==data.value){
			  return;
		  }
		  companyId=data.value;
		  var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/manager/hrOrg/getUserOrg.html",{companyId:companyId},function(data,status){
				layer.close(index); 
				 var proHtml ="<option value=''>请选择</option>";
				$.each(data,function(i,org){
					  proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
				})
				 $("#deptId").html(proHtml);
				  form.render();
			},"json");
			return false;
		});   
	
});

//传递的参数
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNow : params.offset / params.limit + 1,
		name : $.trim($("#temName").val()),
		companyId:$("#companyId").val(),
		deptIds:$("#deptId").next().find("input[class='idinput']").val(),
		isValid:$("#isValid").val()
	};
}

/**
 * 新增
 */
function add(){
	 var url = jsBasePath+"/basic/mailNoticeType/toAdd.html";
	  layer.open({
      type: 2,
      shade : [ 0.5, '#000' ],
	  offset : [ '5%' ],
      area: ['400px','60%'],
      title: "新增邮件通知类型", //不显示标题
      content:url, //捕获的元素
      cancel: function(index){
          layer.close(index);
      },
      end:function(){
      }
  });
	 return false;
}

/**
 * 编辑
 */
function toEdit(id){
	 var url = jsBasePath+"/basic/mailNoticeType/toEdit.html?id="+id;
	  layer.open({
      type: 2,
      shade : [ 0.5, '#000' ],
	  offset : [ '5%' ],
      area: ['400px','60%'],
      title: "编辑邮件通知类型", //不显示标题
      content:url, //捕获的元素
      cancel: function(index){
          layer.close(index);
      },
      end:function(){
      }
  });
	  return false;
}


/**
 * 禁用、删除用户
 */
function delTem(id){
	layer.confirm("禁用后无法重新启用,确定禁用该通知类型?", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/basic/mailNoticeType/delTemp.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#mailNoticeTypeTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}

/**
 * 禁用、删除(批量)
 */
function bath_delTem(){
	var ids=getSelectId("mailNoticeTypeTable");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	layer.confirm("禁用后无法重新启用,确定禁用所选通知类型?", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/basic/mailNoticeType/delTemp.html",{deleteIds:ids},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#mailNoticeTypeTable").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}
  