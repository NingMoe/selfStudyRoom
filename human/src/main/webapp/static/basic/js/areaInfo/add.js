layui.use('form', function(){
  var form = layui.form();
  //监听提交
  form.on('submit(reMail)', function(data){
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/basic/areaInfo/addArea.html",$("#addForm").serializeArray(),function(data,status){
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
  
  form.on('select(province)', function(data){
	  var parentAreaCode=data.value;
	  if(""==parentAreaCode){
		  return;
	  }
	  var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/areaInfo/getArea.html",{parentAreaCode:parentAreaCode,areaLevel:2},function(data,status){
			layer.close(index); 
			var html ="<option value=''>请选择</option>";
			$.each(data,function(i,item){
				html+='<option value="'+item.id+'">'+item.areaName+'</option>'
			})
			 $("#parentAreaCode").html(html);
	         form.render();
		},"json");
		return false;
	});
  
});	