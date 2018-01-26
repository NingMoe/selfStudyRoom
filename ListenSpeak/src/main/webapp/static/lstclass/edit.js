layui.use(['form','laydate'], function(){
	var form = layui.form,laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	//监听提交
  form.on('submit(demo1)', function(data){
		  var index =layer.load(3, {shade: [0.3]});
		  $.post(jsBasePath+"/lstclass/edit.html",$("#addForm").serializeArray(),function(data,status){
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
  
	  laydate.render({
		    elem: '#validTime', //指定元素
		    format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
		  });
	  laydate.render({
		    elem: '#startTime', //指定元素
		    format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
		  });
});	

