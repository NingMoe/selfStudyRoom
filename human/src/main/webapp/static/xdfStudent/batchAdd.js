//导入新东方学员数据execl文件
function uploadExcel(){	 
    var oInput = $("#fileUploadExecl").val();
    if (oInput != undefined && oInput != ""){
       var index = layer.load(3, {shade: [0.3]});
	   xdfStudentImport(index); 
	}else{
		layer.alert("请选择execl文件上传！",{icon:2});
	}		  	  	  
}

function xdfStudentImport(index){
	// 导入上传成功提醒
	var url=jsBasePath+"/xdfStudent/rule/uploadLoadXdfStudentExcel.html";
	// 表单的提交
	$('#addForm').form({
		url:url ,
		success: function(data){
			layer.close(index);
			var result = JSON.parse(data);
			$("#fileUploadExecl").val("");
			if(result.flag==false){
				layer.alert(result.message,{icon:2});
			}else{
				layer.alert(result.message,{icon:1},function(){
					parent.location.reload(); 
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
//下载新东方学员数据导入模板
function downLoadExcel(){
   var url=jsBasePath+"/xdfStudent/rule/downLoadXdfStudentExcel.html";
   window.location.href = url;    
}
//取消
function cancle(){
	closeFrame();
}