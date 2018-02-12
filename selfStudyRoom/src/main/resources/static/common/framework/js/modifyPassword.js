$(function(){
	setTimeout(function() {
		$("input[name='oldPassword']").val("");
	},100);	
});
layui.use('form', function(){
  var form = layui.form;
  form.verify({
		pass: [/(.+){6,12}$/, '密码必须6到12位'],
		subPassword:function(value) {
			var pa=$.trim($("input[name='passWord']").val());
			if(value!=pa) {
				return '两次输入的密码不一致';
			}
		}
	});
  //监听提交
  form.on('submit(demo1)', function(data){
	  var index = layer.load(3, {shade: [0.3]});
		$.post("/modifyPwd",$("#editForm").serializeArray(),function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert(data.message,{icon:1},function(){
					closeFrame();
				});
			}
		},"json");
		return false;
  });
});	