var companyId;
layui.use(['laydate','form'], function() {
	var form = layui.form();
	form.verify({
		isTeach:isTeach
	});
	
	 form.on('submit(demo1)', function(data){
		  var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/basic/employee/saveUserTeach.html",$("#subForm").serializeArray(),function(data,status){
				layer.close(index); 
				if(data.flag==false){
					layer.alert(data.msg,{icon:2});
				}else{
					$("#emailAddr").val("");
					$("#tableList").bootstrapTable('refresh');
				}
			},"json");
			return false;
	  });
	
});


$(function() {
	initTable();
});  

function isTeach(value){
	var v=value.split("@");
	var rows=$("#tableList").bootstrapTable("getData");
	var isS=false;
	for(var i=0;i<rows.length;i++){
		if(rows[i].emailAddr==v[0]){
			return "该人员已是用户导师，请勿重复添加!";
		}
	}
}

function delSuper(teachId){
	 var index = layer.load(3, {shade: [0.3]});
	$.post(jsBasePath+"/basic/employee/delTeach.html",{empId:$("#empId").val(),teachId:teachId},function(data,status){
		layer.close(index);
		if(data!=null){
			layer.msg(data.msg);
			if(data.flag==true){
				$("#tableList").bootstrapTable("removeByUniqueId",teachId);
			}
		}
	},"json");
}