layui.use('form', function(){
  var form = layui.form();
  //监听
  form.on('select(comid)', function(data){
  	$("#schoolAreaName").html("<option value=''>请选择</option>");
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/school_area/getSchoolArea.html",{hrCompanyId:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.name +"'>" +org.name + "</option>";
			})
			$("#schoolAreaName").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
  //监听提交
  form.on('submit(demo1)', function(data){
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/continued_class/importData/add.html",$("#addForm").serializeArray(),function(data,status){
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
});	