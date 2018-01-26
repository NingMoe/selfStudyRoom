layui.use(['form','laydate'], function(){
    var form = layui.form();
    //监听提交
    form.on('submit(demo1)', function(data){
  	  var index =layer.load(3, {shade: [0.3]});
  	  var buyInfoIds=$("#buyInfoIds").val();
  	  var refundMoney=$("#refundMoney").val();
  	  if(refundMoney==""){
  		 layer.close(index); 
  		 layer.alert("退款金额必填!",{icon:2});
  	  }else{		
  		$.ajax({
			url : jsBasePath+"/basic/activity/refundOrderInfo.html",
			data : {buyInfoIds:buyInfoIds,refundMoney:refundMoney},
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