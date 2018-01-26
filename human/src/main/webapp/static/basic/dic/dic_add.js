layui.use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(compa)', function(data){
	  var index =layer.load(3, {shade: [0.3]});
	  var data = {"key":$("#key").val(),"type":$("#type").val(),"name":$("#name").val(),"isValueUnique":$("#isValueUnique").val()};
		$.post(jsBasePath+"/basic/dic/add.html",data,function(data,status){
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
