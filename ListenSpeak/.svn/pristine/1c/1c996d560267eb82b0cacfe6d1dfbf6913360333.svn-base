layui.use(['form','laydate'], function(){
	var form = layui.form,laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	//监听提交
  form.on('submit(demo1)', function(data){
	 	var classCodes="";
		$("#class").find(".layui-form-checked").prev().each(function(){
			classCodes+=$(this).attr("name")+",";
		})
		if(classCodes==""){
			layer.alert("请选择发布班级",{icon:2});
			return  false;
		}
		var name =$("#name").val();
		var continueTime =$("#continueTime").val();
		var endTime =$("#endTime").val();
		  var index =layer.load(3, {shade: [0.3]});
		  $.post(jsBasePath+"/lstClassTest/add.html",{"classCodes":classCodes,"name":name,"endTime":endTime,"continueTime":continueTime},function(data,status){
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
	    elem: '#endTime', //指定元素
	    format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
	  });
});	

