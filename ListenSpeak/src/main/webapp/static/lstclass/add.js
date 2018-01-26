layui.use(['form','laydate'], function(){
	var form = layui.form,laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	//监听提交
  form.on('submit(demo1)', function(data){
	  var flag=true;
	  if($("#className").val()==''){
		  layer.alert("请输入班级名称",{icon:2});
		  flag=false;
	  }else if($("#validTime").val()==''){
		  layer.alert("请输入失效时间",{icon:2});
		  flag=false;
	  }
	  if(flag){
		  var index =layer.load(3, {shade: [0.3]});
		  $.post(jsBasePath+"/lstclass/add.html",$("#addForm").serializeArray(),function(data,status){
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
	  }
	  return false;
  });
  
  laydate.render({
	    elem: '#validTime', //指定元素
	    format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
	  });
  laydate.render({
	    elem: '#startTime' ,//指定元素
	    format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
	  });
});	

