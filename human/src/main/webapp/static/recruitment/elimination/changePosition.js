layui.use([ 'form', 'laydate' ], function() {
	var form = layui.form();

	form.on('select(dept)', function(data) {
		var dept = $("#dept").val();
		var index = layer.load(3, {
			shade : [ 0.3 ]
		});
		$.post(jsBasePath + "/recruit/elimination/getPositionProcessList.html",
				{
					deptId : dept
				}, function(data, status) {
					layer.close(index);
					var proHtml = "<option value=''>请选择</option>";
					$.each(data, function(i, org) {
						proHtml += "<option value='" + org.id + "'>"
								+ org.positionProcessName + "</option>";
					})
					$("#process").html("").html(proHtml);
					form.render();
				}, "json");
		return false;
	});
	
	form.on('submit(tj)', function(){
		 var index = layer.load(3, {shade: [0.3]});
		 var data = {
			flowId : $("#flowId").val(),
			resumeId : $("#resumeId").val(),
			flowCode : $("#flowCode").val(),
			positionProcessId : $("#process").val(),
			interviewTime : $("#interviewTime").val(),
			interviewLocation : $("#interviewLocation").val(),
			isNotice : $("#isNotice").is(":checked")?"1":"0"
		};
		$.ajax({
				url : jsBasePath + "/recruit/elimination/changePosition.html",
				data : data,
				dataType : "json",
				type : "post",
				success : function(res) {
					layer.close(index); 
					if (!res.flag) {
						layer.alert(res.message);
					} else {
						layer.alert(res.message, {
							icon : 1
						}, function() {
							closeFrame();
						});
					}
				}
			});
		return false;
	 });
	$("#qx").bind("click", function() {
		closeFrame();
	});
});