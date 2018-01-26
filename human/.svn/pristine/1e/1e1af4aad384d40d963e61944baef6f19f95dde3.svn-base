layui.use('form', function(){
  var form = layui.form();
  
  //监听邮箱类型
  form.on('select(mailType)', function(data){
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

  //提交事件
  $("#tj").bind("click",function(){
	var hrCompanyId=$("#hrCompanyId").val();
	var mailType=$("#mailType").val();
	var mailUser=$("#mailUser").val().trim();
	var mailPassword=$("#mailPassword").val().trim();
	var domain=$("#domain").val().trim();
	if(hrCompanyId==""||mailType==""||mailUser==""||mailPassword==""||domain==""){
		layer.alert("机构名称、邮箱类型、用户账号、用户密码、域名必填！",{icon:2});
		return false;
	}
  	var data = {
  			hrCompanyId : hrCompanyId,
  			mailType : mailType,
  			mailUser : mailUser,
  			mailPassword : mailPassword,
  			domain : domain		    
	  }; 
  	//接收邮箱  	
  	if(mailType==1){
  		data.pop3MailServerHost = $("#pop3MailServerHost").val().trim();
  		data.exchangeMailServerHost = $("#exchangeMailServerHost").val().trim();  		
  		if(data.pop3MailServerHost==""||data.exchangeMailServerHost==""){
  			layer.alert("pop3服务器地址、Exchange服务器地址必填！",{icon:2});
  			return false;
  		}
  	}
  	//发送邮箱
  	if(mailType==2){
  		data.sendMailServerHost = $("#sendMailServerHost").val().trim();
  		if(data.sendMailServerHost==""){
  			layer.alert("发件服务器地址必填！",{icon:2});
  			return false;
  		}
  	}
	  	$.ajax({
				url : jsBasePath+"/basic/recruitMail/add.html",
				data : data,
				async:false,
				dataType : "json",
				type : "post",
				success:function(res){
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
   });
});	