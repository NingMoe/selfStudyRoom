//上传续班卡背景图
function uploadBackPhoto(){	 
    var oInput = $("#fileUploadPhoto").val();
    if (oInput != undefined && oInput != ""){
       var index = layer.load(3, {shade: [0.3]});
	   photoImport(index); 
	}else{
		layer.alert("请选择图片文件上传！",{icon:2});
	}		  	  	  
}

function  photoImport(index){
	// 导入上传成功提醒
	var ruleId=$("#ruleId").val();
	var url=jsBasePath+"/continued_class/matchData/saveBackPhoto.html?ruleId="+ruleId;
	// 表单的提交
	$('#addForm').form({
		url:url ,
		success: function(data){
			layer.close(index);
			var result = JSON.parse(data);
			$("#fileUploadPhoto").val("");
			if(result.flag==false){
				layer.alert(result.message,{icon:2});
			}else{
				layer.alert(result.message,{icon:1},function(){
					closeFrame();
				});
			}
		},
	   error: function (data){//服务器响应失败处理函数
     	 layer.close(index);     
     	 layer.alert("服务器响应失败!",{icon:2});
       }
	});
	$("#addForm").submit();
}
//取消
function cancle(){
	closeFrame();
}