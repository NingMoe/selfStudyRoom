var companyId;

layui.use(['form'], function() {
	var form = layui.form();
	  form.on('select(temCompany)', function(data){
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
	  
	  //监听提交
	  form.on('submit(add)', function(data){
		  var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/basic/mailNoticeType/add.html",$("#addForm").serializeArray(),function(data,status){
				layer.close(index); 
				if(data.flag==false){
					layer.alert(data.msg,{icon:2});
				}else{
					layer.alert(data.msg,{icon:1},function(){
						parent.location.reload(); 
						closeFrame();
					});
				}
			},"json");
			return false;
	  });
	
});