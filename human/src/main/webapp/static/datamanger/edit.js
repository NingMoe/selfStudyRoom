layui.use(['form','laydate'], function(){
	var form = layui.form(),laydate = layui.laydate;
  //监听提交
  form.on('submit(demo1)', function(data){
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/data/manger/edit.html",$("#addForm").serializeArray(),function(data,status){
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
  var start = {
		    max: laydate.now(),
		    istime: true,
		    format: 'YYYY-MM-DD'
		    ,choose: function(datas){
		    }
		  };
		  
		  document.getElementById('applyDate').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		  $("#track").hide();
		  $("#sendReason").removeAttr("lay-verify");
		  form.on('radio(sendReason)', function(data){
				if(data.value=="其他"){
					$("#sendReason").attr("lay-verify","required")
					$("#track").show();
				}else{
					$("#sendReason").removeAttr("lay-verify");
					$("#track").hide();
				}
				
			});
});	