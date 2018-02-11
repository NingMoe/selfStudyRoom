layui.use('form', function(){
  var form = layui.form;
  //监听提交
  form.on('submit(demo1)', function(data){
	    var id=$("#userId").val();
	    var name=$("#name").val();	
	    var phone=$("#phone").val();
		var sex=$("#sex").val();
	  	var data = {
	  			id:id,
	  			name : name,
	  			phone: phone,
	  			sex : sex
		  }; 
	    var index = layer.load(3, {shade: [0.3]});
	    $.ajax({
			url : "/manager/user/update",
			data : data,
			async:false,
			dataType : "json",
			type : "post",
			success:function(res){
				layer.close(index);
				if(!res.flag){
					layer.alert(res.message,{icon:2});
				}else{
					layer.alert(res.message,{icon:1},function(){						
						parent.location.reload();	
						closeFrame();
					});
				}
			}
		});
	  return false;
  });
  
});	


			