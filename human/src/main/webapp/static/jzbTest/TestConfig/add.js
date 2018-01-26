layui.use(['form','laydate'], function(){
	var form = layui.form(),laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	//监听提交
  form.on('submit(demo1)', function(data){
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/jzbTest/jkPoint/add.html",$("#addForm").serializeArray(),function(data,status){
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
//监听change事件
  form.on('select(subjects)', function(data){
	  if("英语"==data.value){
		  $(".title").show();
	  }else{
		  $(".title").hide();
		  $(".titleNum").val(parseInt("1"));
		  $(".titleNum").removeAttr()
	  }
	}); 
});	

