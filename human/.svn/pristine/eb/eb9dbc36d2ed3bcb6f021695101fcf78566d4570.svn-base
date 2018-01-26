layui.use(['form','laydate','upload'], function(){
	var form = layui.form(),layer = layui.layer;
	var s = layui.upload({
		  url: jsBasePath+'/recruit/seekerEntry/uploadOffer.html',
		  isAuto:true,
		  data:{
			  "id":$("#id").val(),
			  "offer":$("#offer").val()
		  },
		 /* change:function(file){
			  var id = $(file).attr("id");
			  $("#"+id+"Img").attr('src',getObjectURL(file.files[0])).show();
			  $("#"+id+"Button").show();
		  },*/
		  success: function(res){ //上传成功后的回调、
			  s.init();
			  if(!res.flag){
				  layer.alert(res.message,{icon:2});
			  }else{
				 /* $("#offer").val(res.entryhandler.offer);
				  $("#fileDesc").html("").html('<a id="dnBtn" href="'+jsBasePath+'/recruit/seekerEntry/downLoadOffer.html?id='+res.entryhandler.id
						  +'&offer='+res.entryhandler.offer+'&offerName='+res.entryhandler.offerName+'">'+res.entryhandler.offerName+'<a>');*/
				  location.href = jsBasePath+"/recruit/seekerEntry/toEmailConfig.html?id="+$("#id").val()+"&seekerName="+$("#seekerName").val();
			  }
		  }
	   });
	
	($("#dnBtn").length>0) && $("#dnBtn").attr('href',jsBasePath+"/recruit/seekerEntry/downLoadOffer.html?id="+$("#id").val()
			+"&offer="+$("#offer").val()+"&offerName="+$("#offerName").val());
	
	$("#entryDept").val() && $("#deptName").val($('#entryDept option:selected').text());
	
	$("#entryPosition").val() && $("#positionName").val($('#entryPosition option:selected').text());
	
	form.on('submit(emailform0)', function(){
		 var index = layer.load(3, {shade: [0.3]});
		 savaData(index,0);
		return false;
	 });
	
	form.on('select(entryDept)', function(data){
		$("#deptName").val($(data.elem.selectedOptions).text());
	});
	
	form.on('select(entryPosition)', function(data){
		$("#positionName").val($(data.elem.selectedOptions).text());
	});
	 
	 $("#emailform1").size()>0 && form.on('submit(emailform1)', function(){
		 if($("#dnBtn").length==0){
			 layer.alert("请上传OFFER附件",{icon:2});
		 }else{
			 var index = layer.load(3, {shade: [0.3]});
			 savaData(index,1);
		 }
		 return false;
	 });
	 
	 $("#qx").bind("click",function(){
		closeFrame();
	 });
	
	 
	 function savaData(index,isSendOffer){
			var data = $("#emailForm").serializeJson();		
			$.post(jsBasePath+"/recruit/seekerEntry/editEmailConfig.html",{emailConfigStr : JSON.stringify(data),isSendOffer:isSendOffer},function(data,status){
				 layer.close(index); 
				 if(data.flag==false){
					 layer.alert(data.message,{icon:2});
				}else{
					layer.alert(data.message,{icon:1},function(){
						closeFrame();
					});
				}
			},"json");
		}
	
});

