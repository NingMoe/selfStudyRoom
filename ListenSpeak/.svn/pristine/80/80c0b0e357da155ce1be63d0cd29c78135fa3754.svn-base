layui.use(['form'], function(){
    var form = layui.form;    
    //监听省份选择事件
    form.on('select(schoolProvinceId)', function(data){    	
    	$("#schoolCity").empty();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/areaInfo/getArea.html",{areaLevel:2,parentAreaCode:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.areaName + "</option>";
			})
			$("#schoolCity").html(proHtml);
			form.render();
		},"json");
		return false;
	});
    
    //监听城市选择事件
    form.on('select(schoolCityId)', function(data){
    	$("#schoolArea").empty();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/areaInfo/getArea.html",{areaLevel:3,parentAreaCode:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.areaName + "</option>";
			})
			$("#schoolArea").html(proHtml);
			form.render();
		},"json");
		return false;
	});
    
    //监听提交
    form.on('submit(demo1)', function(data){
  	  var index =layer.load(3, {shade: [0.3]});
  	  $.post(jsBasePath+"/basic/school/add.html",$("#addForm").serializeArray(),function(data,status){
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