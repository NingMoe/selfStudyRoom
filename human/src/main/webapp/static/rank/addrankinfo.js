layui.use([ 'form', 'layedit', 'upload' ], function() {
	var form = layui.form(), layer = layui.layer, layedit = layui.layedit;

	var getObjectURL = function(file) {
		var url = null;
		if (window.createObjectURL != undefined) {
			url = window.createObjectURL(file)
		} else if (window.URL != undefined) {
			url = window.URL.createObjectURL(file)
		} else if (window.webkitURL != undefined) {
			url = window.webkitURL.createObjectURL(file)
		}
		return url
	};

	// 监听提交
	var s = layui.upload({
		url : jsBasePath + '/teacher/rankinfo/insert.html',
		isAuto : false,
		change : function(file) {
			var id = $(file).attr("id");
			if (id == 'head_img') {
				$("#head_file_img").attr('src', getObjectURL(file.files[0]))
						.show();
			}

			if (id == 'foot_img') {
				$("#foot_file_img").attr('src', getObjectURL(file.files[0]))
						.show();
			}
		},
		success : function(res) { // 上传成功后的回调
			if (!res.flag) {
				layer.alert(res.message, {
					icon : 2
				});
			} else {
				layer.alert(res.message, {
					icon : 1
				}, function() {
					closeFrame();
				});
			}
		}
	});

	$("#res").bind("click", function() {
		$("#head_file_img").attr("src", "");
		$("#head_img").val("");
		$("#foot_file_img").attr("src", "");
		$("#foot_img").val("");
	});

	$("#mysubmit").bind("click", function() {
		var rank_name = $.trim($("#rank_name").val());
		var dept_name = $.trim($("#dept_name").val());
		var ranke_num = $.trim($("#ranke_num").val());
		var ranke_lastnum = $.trim($("#ranke_lastnum").val());
		var refresh_time = $.trim($("#refresh_time").val());
		var re = /^[0-9]+$/;
		if (rank_name == '') {
			layer.msg("请填写规则名称");
			return false;
		}

		if (dept_name == '') {
			layer.msg("请填写部门名称");
			return false;
		}

		if (ranke_num == '') {
			layer.msg("请填写显示数量");
			return false;
		}

		if (!re.test(ranke_num)) {
			layer.msg("显示数量只能填写正整数");
			return false;
		}

		if (ranke_lastnum == '') {
			layer.msg("请填写上榜班级剩余人数");
			return false;
		}

		if (!re.test(ranke_lastnum)) {
			layer.msg("上榜班级剩余人数只能填写正整数");
			return false;
		}
		
		if (refresh_time == '') {
			layer.msg("请填写刷新时间");
			return false;
		}

		if (!re.test(refresh_time)) {
			layer.msg("刷新时间只能填写正整数");
			return false;
		}

		var files = [];
		files.push($("#head_img")[0]);
		files.push($("#foot_img")[0]);
		var data = {
			"rank_name" : $.trim($("#rank_name").val()),
			"dept_name" : $.trim($("#dept_name").val()),
			"ranke_num" : $.trim($("#ranke_num").val()),
			"ranke_lastnum" : $.trim($("#ranke_lastnum").val()),
			"link_name" : $.trim($("#link_name").val()),
			"link_access" : $.trim($("#link_access").val()),
			"refresh_time" : $.trim($("#refresh_time").val()),
			"b_color_code" : $.trim($("#b_color_code").val()),
			"font_color" : $.trim($("#font_color").val())
		};
		s.action(files, "file", data);
	});
});