layui.use(['form','laydate'], function(){
    var form = layui.form(),laydate = layui.laydate;
    var start = {
		istoday : true,
		format: 'YYYY-MM-DD hh:mm:ss', //日期格式
		istime: true, //是否开启时间选择
		min: laydate.now(0),
		choose : function(datas) {			
			
		}
	};
    
	$('#interviewTime').bind("click", function() {
		start.elem = this;		
		laydate(start);
	});
	
	form.on('select(dept)', function(data){
    	var deptId = $("#dept").val();
    	if(deptId==""){
    		return;
    	}
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/recruit/resume/getPositionProcessList.html",{deptId:deptId},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.positionProcessName + "</option>";
			})
			$("#positionProcessId").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
	//监听提交
	form.on('submit(tj)', function(data){
		  var index =layer.load(3, {shade: [0.3]});
		  $.post(jsBasePath+"/recruit/resume/addNewProcess.html",$("#addForm").serializeArray(),function(data,status){
				layer.close(index); 
				if(data.flag==false){
					layer.alert(data.message,{icon:2});
				}else{					
					layer.alert(data.message,{icon:1},function(){
						closeFrame();
					});
				}
			},"json");
		 return false; 
	  });
});
//取消
function cancle(){
	closeFrame();
}



