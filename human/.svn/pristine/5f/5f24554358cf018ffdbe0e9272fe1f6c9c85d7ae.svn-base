//搜索
function serach(){
	var studentCode=$.trim($("#studentCode").val());
	if(studentCode!=""){
		var index = layer.load(1, {shade: [0.8, '#393D49']});
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : jsBasePath+"/wechat/binding/xdfStudent/searchPhone.html",
			data : {
				studentCode : studentCode
			},
			success : function(data){
				layer.close(index);
				if(data.flag){
					$("#phoneList").empty();
					var html='';
					$.each(data.studentPhoneList, function(idx,item){
						html+='<li class="" style="margin-top: 0.1rem"><div class="border clearfix"><div class="list1Left">'
							 +'<p>'+item.studentName+'('+item.studentCode+')</p>'
							 +'<p>'+item.telephone1+'</p></div>'
                             +'<a href="tel:'+item.telephone1+'">'
                             +'<img src="'+jsBasePath+'/static/xdfStudent/wechat/images/3.png"/></a></div></li>';
					});	
					$("#phoneList").html(html);
				}else{				
					layer.alert(data.message,{icon:2});
				}
			},
			error : function(data){
				layer.close(index);
				layer.alert("网络错误,请联系管理员!",{icon:2});
			}
		});	
	}	
}






