layui.use([ 'form', 'laydate', 'upload' ], function() {
	var form = layui.form(), laydate = layui.laydate;
	// 监听提交
	form.on('submit(demo1)', function(data) {
		var index = layer.load(3, {
			shade : [ 0.3 ]
		});
		var sClassCode=$("#sClassCode").val();
		var remark=$("#remark").val();
		var sLevel=$("#sLevel").val();
		$.post(jsBasePath + "/jw/xdf/class/edit.html", {"sClassCode":sClassCode,"remark":remark,"sLevel":sLevel}, function(data, status) {
			layer.close(index);
			if (data.flag == false) {
				layer.alert(data.message, {
					icon : 2
				});
			} else {
				layer.alert(data.message, {
					icon : 1
				}, function() {
					closeFrame();
				});
			}
		}, "json");
		return false;
	});

});

function download(){
	var stuName=$("#stuName").val();
	var sClassCode=$("#sClassCode").val();
	var testAcce=$("#testAcce").val();
	window.location.href=jsBasePath+"/stu/admin/download.html?stuName="+stuName+"&sClassCode="+sClassCode+"&testAcce="+testAcce;
}