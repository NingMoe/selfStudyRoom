layui.use('form', function(){
  var form = layui.form();
  //监听提交
  form.on('submit(dept)', function(data){
	  
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/jzbTest/dept/add.html",{
		  name :$("#name").val(),
		  company : $("#company").val(),
	  },function(data,status){
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