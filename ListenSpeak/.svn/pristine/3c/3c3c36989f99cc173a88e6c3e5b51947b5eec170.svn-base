layui.use('form', function(){
  var form = layui.form;  
//监听邮箱类型
  form.on('select(type)', function(data){
		if(data.value=="1"){
			$(".send").hide();
			$(".accept").show();
		}		
		if(data.value=="2"){
			$(".send").show();
			$(".accept").hide();
		}		
		if(data.value==""){
			$(".send").hide();
			$(".accept").hide();
		}
		
	});
  
//监听省份选择事件
  form.on('select(schoolProvinceId)', function(data){    	
  	$("#schoolCity").empty();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/areaInfo/getArea.html",{areaLevel:2,parentAreaCode:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.areaName + "</option>";
			})
			$("#schoolCity").html(proHtml);
			form.render();
		},"json");
		return false;
	});
  //监听城市选择事件
  form.on('select(schoolCityId)', function(data){
  	$("#schoolArea").empty();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/areaInfo/getArea.html",{areaLevel:3,parentAreaCode:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.areaName + "</option>";
			})
			$("#schoolArea").html(proHtml);
			form.render();
		},"json");
		return false;
	});
  
  //监听学校类型选择事件
  form.on('select(schoolType)', function(data){
	    var schoolProvince=$("#schoolProvince").val();
	    var schoolCity=$("#schoolCity").val();
	    var schoolArea=$("#schoolArea").val();
	    $("#schoolName").empty();
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/basic/school/getSchoolByParam.html",{schoolProvince:schoolProvince,schoolCity:schoolCity,schoolArea:schoolArea,schoolType:data.value},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.schoolName + "</option>";
			})
			$("#schoolName").html(proHtml);
			form.render();
		},"json");
		return false; 	    
  });
  
  //监听提交
  form.on('submit(demo1)', function(data){
	    var id=$("#userId").val();
	    var name=$("#name").val();		
		var sex=$("#sex").val();
		var subject=$("#subject").val();
		var grade=$("#grade").val();
		var type=$("#type").val();		
	  	var data = {
	  			id:id,
	  			name : name,
	  			sex : sex,
	  			subject : subject,
	  			grade : grade,
	  			type : type	    
		  }; 
	  	//新东方学校  	
	  	if(type==1){  		
	  		data.school = $("#school").val();  			  		
	  	}else if(type==2){//公立学校
	  		data.school = $("#schoolName").val();
	  	}
	  	if(data.school==""){
			layer.alert("学校名称必选！",{icon:2});
			return false;
		}	
	    var index = layer.load(3, {shade: [0.3]});
	    $.ajax({
			url : jsBasePath+"/manager/user/update.html",
			data : data,
			async:false,
			dataType : "json",
			type : "post",
			success:function(res){
				layer.close(index);
				if(!res.flag){
					layer.alert(res.message,{icon:2});
				}else{
					layer.alert(res.message,{icon:1},function(){						
						parent.location.reload();	
						closeFrame();
					});
				}
			}
		});
	  return false;
  });
  
});	


			