layui.use('form', function(){
  var form = layui.form();
  //监听提交
  form.on('submit(jyz)', function(data){
	  
	  var index =layer.load(3, {shade: [0.3]});
	  checkJyz() && $.post(jsBasePath+"/jw/addJyzUser.html",{
		   /* private Integer id;

		    private String email;

		    private String name;

		    private String jyz;
		    
		    private String dept;*/
		  email :$("#email").val(),
		  name : $("#name").val(),
		  jyz : $("#jyz").next().find(".idinput").val(),
		  dept:$("#dept").val()
			  
	  },function(data,status){
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
  
  $("#email").bind("blur",function(){
	  var email = this.value;
	  if(email){
		  $.ajax({
				url : jsBasePath+"/jw/checkUser.html",
				data : {email:email},
				dataType : "json",
				type : "post",
				async:false,
				success : function(res) {
					if (!res.flag) {
						layer.alert(res.message);
						$("#email").val("").focus();
					} else {
						var user = res.user;
						$("#name").val(user.name);
						$("#dept").val(user.deptName);
					}
				}
			});
	  }
	  
  });
});	

function checkJyz(){
	var flag = true;
	var jyzs = $("#jyz").next().find(".idinput").val();
	console.log(jyzs);
	if(!jyzs){
		layer.alert("请选择教研组",{icon:2},function(){
			flag = false;
		});
	}
	return flag;
}