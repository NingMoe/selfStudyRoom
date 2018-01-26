layui.use(['form'], function(){
    var form = layui.form;    
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
    
});

//导入公立学校数据execl文件
function uploadExcel(){	 
	var schoolProvince=$("#schoolProvince").val();
	if ($.trim(schoolProvince)==""){
		layer.alert("省份必选!",{icon:2});
		return;
	}
	var schoolCity=$("#schoolCity").val();
	if ($.trim(schoolCity)==""){
		layer.alert("城市必选!",{icon:2});
		return;
	}
	var schoolArea=$("#schoolArea").val();
	if ($.trim(schoolArea)==""){
		layer.alert("行政区必选!",{icon:2});
		return;
	}
    var oInput = $("#fileUploadExecl").val();
    if (oInput != undefined && oInput != ""){
       var index = layer.load(3, {shade: [0.3]});
       schoolImport(index,schoolProvince,schoolCity,schoolArea); 
	}else{
		layer.alert("请选择execl文件上传！",{icon:2});
		return;
	}		  	  	  
}

function  schoolImport(index,schoolProvince,schoolCity,schoolArea){
	// 导入上传成功提醒
	var url=jsBasePath+"/basic/school/uploadLoadSchoolExcel.html?schoolProvince="+schoolProvince+"&schoolCity="+schoolCity+"&schoolArea="+schoolArea;
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
//下载公立学校导入模板
function downLoadExcel(){
   var url=jsBasePath+"/basic/school/downLoadSchoolExcel.html";
   window.location.href = url;    
}
//取消
function cancle(){
	closeFrame();
}