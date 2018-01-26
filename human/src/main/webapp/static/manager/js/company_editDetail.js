layui.use(['form', 'layedit','upload'], function(){
	  var form = layui.form()
	  ,layer = layui.layer
	  ,layedit = layui.layedit;
	  
		var getObjectURL = function(file) {
			var url = null;
			if (window.createObjectURL != undefined) {
				url = window.createObjectURL(file)
			} else if (window.URL != undefined) {
				url = window.URL.createObjectURL(file)
			} else if (window.webkitURL != undefined) {
				url = window.webkitURL.createObjectURL(file)
			}
			return url;
		};
	  
	  //创建一个编辑器
	  var editIndex = layedit.build('profile',{tool: []});
	  
	  var s = layui.upload({
		  url: jsBasePath+'/manager/company/editDetail.html',
		  isAuto:false,
		  change:function(file){
			  var id = $(file).attr("id");
			  $("#"+id+"Img").attr('src',getObjectURL(file.files[0])).show();
			  $("#"+id+"Button").show();
		  },
		  success: function(res){ //上传成功后的回调
			  if(!res.flag){
				  layer.alert(res.message,{icon:2});
			  }else{
				  layer.alert(res.message,{icon:1},function(){
					  closeFrame();
				  });
			  }
		  }
	   });
	  var provinceCode = $("#provinceCode").val();
	  if(!!provinceCode){
		  $.post(jsBasePath+"/free/entry/getArea.html",{areaLevel:2,parentAreaCode:provinceCode},function(data,status){
				var proHtml ="<option value=''>请选择</option>";
				$.each(data,function(i,area){
					proHtml += "<option value='" + area.id+"' ";
					if(area.id == $("#cityId").val()){
						proHtml += "selected='selected' ";
					}
					
					proHtml += ">" +area.areaName + "</option>";
				})
				$("#cityCode").html("").html(proHtml);
				form.render();
			},"json");
	  } 
	  
	  
	  form.on('select(provinceCode)', function(data){
			var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/free/entry/getArea.html",{areaLevel:2,parentAreaCode:data.value},function(data,status){
				layer.close(index); 
				var proHtml ="<option value=''>请选择</option>";
				$.each(data,function(i,area){
					proHtml += "<option value='" + area.id +"'>" +area.areaName + "</option>";
				})
				$("#cityCode").html("").html(proHtml);
				form.render();
			},"json");
			return false;
		});
	  
	  form.on('submit(ced)', function(data){
		  saveData();
	  });
	  
	  $(".delPic").bind("click",function(){
		  var id = $(this).attr("id");
		  var button = $(this);
		  var img = $(this).parent().find("img");
		  var file = $(this).parent().find("input[type='file']")[0];
		  $.ajax({
				url : jsBasePath + '/manager/company/delImg.html',
				method : 'POST',
				data : {
					companyId : $("#companyId").val(),
					buttonId : id
				},
				dataType : "json",
				success : function(data) {
					// 隐藏
					if (data.flag) {
						img.attr("src","").hide();
						button.hide();
					} else {
						layer.alert(data.message,{icon:2});
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					/* alert(2); */
				}
			});
		  
	  });
	  
	  function saveData(){
		  var files = [];
		  files.push($("#file1")[0]);
		  files.push($("#file2")[0]);
		  files.push($("#file3")[0]);
		  
		  var data = {
			"companyId":$("#companyId").val(),	
			"companyName":$("#companyName").val(),	
			"schoolSite":$("#schoolSite").val(),	
			"recruitSite":$("#recruitSite").val(),	
			"servicePhone":$("#servicePhone").val(),	
			"wechatNo":$("#wechatNo").val(),	
			"recruitEmail":$("#recruitEmail").val(),	
			"profile":layedit.getContent(editIndex),
			"schoolVideo":$("#schoolVideo").val(),
			"appid":$("#appid").val(),
			"password":$("#password").val(),
			"provinceCode":$("#provinceCode").val(),
			"cityCode":$("#cityCode").val(),
			"addr" :$("#addr").val(),
			"isBirthWish" :$("#isBirthWish").val(),
			"isJoinWish" :$("#isJoinWish").val()
		  };
		  s.action(files,"",data);
	  }
	});