layui.use(['form'], function(){
    var form = layui.form();
    //监听提交
    form.on('submit(demo1)', function(data){
  	  var index =layer.load(3, {shade: [0.3]});
  	  var activityId=$("#activityId").val();
  	  var telephone=$.trim($("#telephone").val());
  	  if(telephone==""){
  		 layer.close(index); 
  		 layer.alert("手机号或者身份证号必填!",{icon:2});
  	  }else{		
  		$.ajax({
			url : jsBasePath+"/sign/activity/fastSign.html",
			data : {activityId:activityId,telephone:telephone},
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
  	  }  	   	 
  	  return false;
    });
});