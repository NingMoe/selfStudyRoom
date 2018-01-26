var companyId;

layui.use('form', function(){
  var form = layui.form();
  
  form.verify({
		pass: [/(.+){6,12}$/, '密码必须6到12位'],
		subPassword:function(value) {
			var pa=$.trim($("input[name='userPassword']").val());
			if(value!=pa) {
				return '两次输入的密码不一致';
			}
		}
	});
  //监听提交
  form.on('submit(demo1)', function(data){
	  $("input[name='deptName']").val($("#deptId option:selected").text());
	  $("input[name='companyName']").val($("#companyId option:selected").text());
	  var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/manager/user/add.html",$("#addUserForm").serializeArray(),function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert(data.message,{icon:1},function(){
					parent.location.reload(); 
					closeFrame();
				});
			}
		},"json");
		return false;
  });
  
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


			