layui.use('form', function(){
  var form = layui.form;  
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
		var name=$("#name").val().trim();
		var loginName=$("#loginName").val().trim();
		var phone=$("#phone").val().trim();
		var password=$("#userPassword").val().trim();
		var sex=$("#sex").val();			
	  	var data = {	  			
	  			name : name,
	  			loginName : loginName,
	  			phone : phone,
	  			password : password,
	  			sex : sex	  			
		  }; 
	    var index = layer.load(3, {shade: [0.3]});
	 	$.ajax({
			url : "/manager/user/add",
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


			