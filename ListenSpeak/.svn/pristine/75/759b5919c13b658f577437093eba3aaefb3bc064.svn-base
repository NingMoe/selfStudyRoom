layui.use(['form','laydate'], function(){
	var form = layui.form,laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	//监听提交
  form.on('submit(demo1)', function(data){
		  var index =layer.load(3, {shade: [0.3]});
		var sourceType=$("#sourceType").val();
		var year=$("#year").val();
		var name =$("#name").val();
		var getId="";
		  $.post(jsBasePath+"/lstBasePaper/add.html",{"sourceType":sourceType,"year":year,"name":name},function(data,status){
			  layer.close(index); 
			  getId=data.id;
			  $(".questionNum").each(function(){
				  var num=$(this).find(".layui-input").val();
				  var typeId=$(this).find(".layui-input").attr("name");
				  $.post(jsBasePath+"/lstBasePaper/addToQtype.html",{"num":num,"typeId":typeId,"paperId":getId},function(data,status){
					  if(data.flag==false){
						  layer.alert(data.message,{icon:2});
					  }else{
						  layer.alert(data.message,{icon:1},function(){
							  parent.location.reload(); 
							  closeFrame();
						  });
					  }
				  },"json");
			  })
		  },"json");
	  return false;
  });
  form.on('select(provinceId)', function(data){    	
  	$("#city").empty();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/areaInfo/getArea.html",{areaLevel:2,parentAreaCode:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.areaName + "</option>";
			})
			$("#city").html(proHtml);
			form.render();
		},"json");
		return false;
	});
  
});	

