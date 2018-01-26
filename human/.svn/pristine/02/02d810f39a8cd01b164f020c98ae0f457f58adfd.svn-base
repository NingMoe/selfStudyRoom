layui.use(['form','laydate'], function(){
	var form = layui.form(),laydate = layui.laydate;
	var titleNum =$("#titleNum").val();
	if("1"==titleNum){
		$(".title").css("display","none");
	}
  //监听提交
  form.on('submit(demo1)', function(data){
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/jzbTest/jkPoint/edit.html",$("#addForm").serializeArray(),function(data,status){
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
  form.on('select(mulit)', function(data){
	  if("2"==data.value){
		  $(".title").css("display","block");
		  $("#titleNum").val("");
	  }else{
		  $(".title").css("display","none");
		  $("#titleNum").val(1);
	  }
	}); 
});	