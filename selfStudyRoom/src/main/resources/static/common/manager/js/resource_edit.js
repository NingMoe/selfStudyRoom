layui.use('form', function(){
  var form = layui.form;
  form.verify();
  //监听提交
  form.on('submit(demo1)', function(data){
	  var index = layer.load(3, {shade: [0.3]});
		$.post("/manager/resource/update",$("#editForm").serializeArray(),function(data,status){
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
});	


			