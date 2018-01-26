layui.use([ 'form', 'laydate', 'upload' ], function() {
	var form = layui.form(), laydate = layui.laydate;
	// 监听提交
	var s = layui.upload({
		url : jsBasePath + '/stu/admin/upload.html?sClassCode='
				+ $("#sClassCode").val() + "&stuName=" + $("#stuName").val(),
		isAuto : true,
		change : function(file) {
			var id = $(file).attr("id");
		},
		success : function(res) { // 上传成功后的回调
			if (res.flag != true) {
				layer.alert("上传失败", {
					icon : 2
				});
			} else {
				layer.alert("上传成功", {
					icon : 1
				});
				if ($("#testAcce").val() != '') {
					var testAcce = $("#testAcce").val() + '</br>'
							+ res.testAcce;
					$("#testAcce").val(testAcce);
				} else {
					$("#testAcce").val(res.testAcce);
				}
				$("[name=isSub]").val("是");
				layui.form().render();
				return false;
			}
		}
	});
	
	// 监听提交
	form.on('submit(demo1)', function(data) {
		var index = layer.load(3, {
			shade : [ 0.3 ]
		});
		var marAcc=$("#marAcc").val();
		var isSub=	$("#isSub").val();
		if(isSub==""&&marAcc==""){
			alert("阅读账号及密码与是否提交测试不能同时为 空");
			return ;
		}
		$.post(jsBasePath + "/stu/admin/edit.html", $("#addForm")
				.serializeArray(), function(data, status) {
			layer.close(index);
			if (data.flag == false) {
				layer.alert(data.message, {
					icon : 2
				});
			} else {
				layer.alert(data.message, {
					icon : 1
				}, function() {
					parent.location.reload();
					closeFrame();
				});
			}
		}, "json");
		return false;
	});

});

