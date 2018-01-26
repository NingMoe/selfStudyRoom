layui.use(['form','laydate'], function(){
	var form = layui.form(),laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	//监听提交
  form.on('submit(demo1)', function(data){
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/student/exam/add.html",$("#addForm").serializeArray(),function(data,status){
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
		  
		  document.getElementById('time').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  };
		  $(".hide").hide();
		  $(".show").show();
		  form.on('select(stage)', function(data){
			 
			  $.post(jsBasePath+"/student/exam/lookforTearcher.html",$("#addForm").serializeArray(),function(data,status){
				  if(data.stage=='预备'){
					  $("#yfTearcher").val(data.yfTearcher);
					  $("#tkTearcher").val(data.tkTearcher);
					   $("#dTearcher").val(data.dTearcher);
					   $("#rTearcher").val("");
					   $("#lTearcher").val("");
					 $("#sTearcher").val("");
					 $("#wTearcher").val("");
				  }else{
					  $("#rTearcher").val(data.rTearcher);
					  $("#lTearcher").val(data.lTearcher);
					  $("#sTearcher").val(data.sTearcher);
					  $("#wTearcher").val(data.wTearcher);
					  $("#yfTearcher").val("");
					  $("#tkTearcher").val("");
					   $("#dTearcher").val("");
				  }
			  },"json");
			  if("预备"==data.value){
				  $(".hide").hide();
				  $(".show").show();
				  $("#rTearcher").val("");
				   $("#lTearcher").val("");
				 $("#sTearcher").val("");
				 $("#wTearcher").val("");
			  }else if(data.value==""){
				  $(".hide").hide();
				  $(".show").show();
			  }else{
				  $(".show").hide()
				  $(".hide").show();
				  $("#yfTearcher").val("");
				  $("#tkTearcher").val("");
				   $("#dTearcher").val("");
			  }
			});     
});	

