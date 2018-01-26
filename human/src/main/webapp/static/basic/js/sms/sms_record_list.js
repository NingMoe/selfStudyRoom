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
		sendTel:$("#sendTel").val(),
		company:$("#companyId").val(),
		deptIds:$("#deptId").next().find("input[class='idinput']").val(),
		state:$("#state").val()
	};
}


$(function() {
	initTable();
});  