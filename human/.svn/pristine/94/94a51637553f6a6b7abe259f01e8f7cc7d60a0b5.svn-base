layui.use(['form','laydate'], function(){
	var form = layui.form(),laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	//监听提交
  form.on('submit(demo1)', function(data){
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/data/manger/add.html",$("#addForm").serializeArray(),function(data,status){
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
	  $(".layui-icon").bind("click", function() {
		  alert(1);
		});
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
		  $("#sendReason").removeAttr("lay-verify")
		  form.on('radio(sendReason)', function(data){
				if(data.value=="其他"){
					$("#track").show();
					$("#sendReason").attr("lay-verify","required")
				}else{
					$("#sendReason").removeAttr("lay-verify")
					$("#track").hide();
				}
				
			});
});	

