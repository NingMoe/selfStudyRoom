layui.use(['form','laydate'], function(){
	var form = layui.form(),laydate = layui.laydate;
  //监听提交
  form.on('submit(demo1)', function(data){
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/student/exam/edit.html",$("#addForm").serializeArray(),function(data,status){
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
		  
		  document.getElementById('time').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
});	