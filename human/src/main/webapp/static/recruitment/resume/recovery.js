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
	//监听简历回收类型
	form.on('radio(recoveryType)', function(data){
		if(data.value=="1"){
			$(".changePosition").hide();
			$(".rencai").hide();
			$(".eliminate").show();
		}		
		if(data.value=="2"){
			$(".rencai").hide();
			$(".eliminate").hide();
			$(".changePosition").show();
		}
		
		if(data.value=="3"){
			$(".changePosition").hide();
			$(".eliminate").hide();
			$(".rencai").show();
		}
		
	});
	//监听更换面试岗位的部门选择事件
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
    //监听放入人才库部门选择事件
	form.on('select(rencaiDept)', function(data){
    	var deptId = $("#rencaiDept").val();
    	if(deptId==""){
    		return;
    	}
		var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/recruit/elimination/getPositionList.html",{dept:deptId},function(data,status){
			layer.close(index); 
			var proHtml ="<option value=''>请选择</option>";
			$.each(data,function(i,org){
				proHtml += "<option value='" + org.id +"'>" +org.name + "</option>";
			})
			$("#position").html("").html(proHtml);
			form.render();
		},"json");
		return false;
	});
	//提交事件
    $("#tj").bind("click",function(){
    	var recover=$("input:radio").is(":checked");
    	if(!recover){
    		layer.alert("回收类型必选！",{icon:2});
			return false;
    	}
    	var url=jsBasePath+"/recruit/resume/";
    	var recoveryType=$('input:radio:checked').val();
    	var data = {
		    resumeId : $("#resumeId").val()
	    }; 
    	//直接淘汰
    	if(recoveryType==1){
    		url+="taotai.html";
    		data.remark = $("#remark").val().trim();
    		if(data.remark==""){
    			layer.alert("淘汰原因必填！",{icon:2});
    			return false;
    		}
    	}
    	//更换面试岗位
    	if(recoveryType==2){
    		url+="addNewProcess.html";
    		data.dept = $("#dept").val();
    		data.positionProcessId = $("#positionProcessId").val();
    		data.interviewTime = $("#interviewTime").val();
    		data.interviewLocation = $("#interviewLocation").val().trim();
    		//data.isNotice=$("#isNotice").is(":checked")?"1":"0";
    		if(data.dept=="" || data.position==""|| data.interviewTime==""|| data.interviewLocation==""){
    			layer.alert("面试部门、面试职位、面试时间、面试地点必填！",{icon:2});
    			return false;
    		}
    	}
    	//放入人才库
    	if(recoveryType==3){
    		url+="personnel.html";
    		data.remark = $("#rencaiRemark").val().trim();
    		data.dept = $("#rencaiDept").val();
    		data.position = $("#position").val();
    		data.isRencai = "1";
    		if(data.dept=="" || data.position==""){
    			layer.alert("部门、职位必选！",{icon:2});
    			return false;
    		}
    	}
    	$.ajax({
			url : url,
			data : data,
			async:false,
			dataType : "json",
			type : "post",
			success:function(res){
				if(!res.flag){
					layer.alert(res.message,{icon:2});
				}else{
					layer.alert(res.message,{icon:1},function(){						
						closeFrame();
					});
				}
			}
		});
    });
});
//取消
function cancle(){
	closeFrame();
}



