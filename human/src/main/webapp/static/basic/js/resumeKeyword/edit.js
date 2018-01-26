layui.use(['form','laydate'], function(){
    var form = layui.form();
    //监听下拉框事件
    form.on('select(website)', function(data){
    	if(data.value==""){
    		return ;
    	}
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/resumeModular/getModularByCondition.html",{website:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
			})
			$("#modularId").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
    //监听提交
    form.on('submit(demo1)', function(data){
  	  var index =layer.load(3, {shade: [0.3]});
  	  $.post(jsBasePath+"/basic/resumeKeyword/edit.html",$("#addForm").serializeArray(),function(data,status){
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