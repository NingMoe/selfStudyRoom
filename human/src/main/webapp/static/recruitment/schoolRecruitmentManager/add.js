layui.use(['form','laydate'], function(){
    var form = layui.form();
    form.on('select(comid)', function(data){
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/manager/org/getOrgByCondition.html",{company:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
			})
			$("#deptId").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
    
    //监听提交
    form.on('submit(demo1)', function(data){
  	  var index =layer.load(3, {shade: [0.3]});
  	  $.post(jsBasePath+"/recruit/schoolRecruitmentManager/save.html",$("#addForm").serializeArray(),function(data,status){
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