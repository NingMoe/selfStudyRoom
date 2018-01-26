layui.use(['form'], function(){
	var form = layui.form(),layer = layui.layer;
	form.on('submit(resume-add)', function(data){
		var name=$.trim($("input[name='name']").val());
		var phone=$.trim($("input[name='phone']").val());
		if(name==""||phone==""){
			layer.alert("姓名和手机号不能为空!",{icon:2});
			return;
		}
		$.post(jsBasePath+"/recruit/resume/add.html",{name:name,phone:phone},function(data,status){
			closeFrame();
		},"json");
		return false;
	});
});