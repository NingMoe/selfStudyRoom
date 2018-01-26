//初始化
$(function() {
	buttionclick();//按钮事件
});

//按钮事件
function buttionclick(){
	
}

layui.use(['form', 'layedit','upload'], function(){
	var form = layui.form()
	  ,layer = layui.layer
	  ,layedit = layui.layedit;
  
  //监听提交
  var s = layui.upload({
		url: jsBasePath+'/ielts/student/upstudentinfo.html',
		isAuto : false,
		change : function(file){
			$("#filename").html(getFileName(file.value));
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
  
  $("#studentinfo_tj").bind("click",function(){
	  var files = [];
	  files.push($("#fileexcel")[0]);
	  var data = {
			  };
	  s.action(files,"file",data);
  });
});

//下载模板
function downloadexcel(){
	downTemp("导入学员信息模板.xlsx","ieltsstudentinfo.xlsx");
}

//获取上传文件名称
function getFileName(o){
    var pos=o.lastIndexOf("\\");
    return o.substring(pos+1);  
}