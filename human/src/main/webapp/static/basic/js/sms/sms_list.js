var companyId;

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
		temName : $.trim($("#temName").val()),
		temType:$("#temType").val(),
		temCompany:$("#companyId").val(),
		deptIds:$("#deptId").next().find("input[class='idinput']").val(),
		state:$("#state").val()
	};
}

/**
 * 新增
 */
function add(){
	 var url = jsBasePath+"/basic/sms/toAdd.html";
	  layer.open({
      type: 2,
      shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
      area: ['670px','80%'],
      //scrollbar: false,
      title: "新增模版", //不显示标题
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
function toEdit(id){
	 var url = jsBasePath+"/basic/sms/toEdit.html?id="+id;
	  layer.open({
      type: 2,
      shade : [ 0.5, '#000' ],
		offset : [ '5%' ],
      area: ['670px','80%'],
      //scrollbar: false,
      title: "编辑模版", //不显示标题
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
 * 禁用、删除用户
 */
function delTem(id){
	layer.confirm("禁用后无法重新启用,确定禁用该模版?", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/basic/sms/delTemp.html",{deleteIds:id},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#tableList").bootstrapTable('refresh');
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
	var ids=getSelectId("tableList");
	if(ids==""){
		layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
		return;
	}
	layer.confirm("禁用后无法重新启用,确定禁用所选模版?", {
		  btn: ['是','否'] ,//按钮
		  offset: '10%',
		  btnAlign:'c'
		}, function(index){
			$.post(jsBasePath+"/basic/sms/delTemp.html",{deleteIds:ids},function(data,status){
				layer.close(index);
				if(data!=null){
					layer.msg(data.message);
					if(data.flag==true){
						    $("#tableList").bootstrapTable('refresh');
					}
				}
			},"json");
		}, function(index){
			layer.close(index);
		});
}

$(function() {
	initTable();
});  