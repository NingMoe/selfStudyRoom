//打开新增页
function addview(){
	layer.open({
	    type: 2,
	    title: '新增排行榜规则',
	    shadeClose: false,
	    shade: 0.8,
	    offset : ['20%'],
		area: ['500px', '400px'],
		content: jsBasePath+'/teacher/rankinfo/addview.html',
		end:function(){
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//打开修改页
function changeview(id){
	layer.open({
	    type: 2,
	    title: '修改排行榜规则',
	    shadeClose: false,
	    shade: 0.8,
	    offset : ['20%'],
		area: ['500px', '400px'],
		content: jsBasePath+'/teacher/rankinfo/changeview.html?id='+id,
		end:function(){
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

function rankclassesview(id){
	window.location.href = jsBasePath+'/teacher/rankclasses/view.html?id='+id;
}

//删除规则
function deleterankinfo(id){
	layer.confirm("确认要删除该规则?", {
		btn: ['是','否'] ,//按钮
		offset: '10%',
		btnAlign:'c'
		}, function(index){
			$.ajax({
				url : jsBasePath + "/teacher/rankinfo/delete.html",
				type : "POST",
				dataType : "json",
				data : {
					id : id
				},
				success : function(data){
					layer.msg(data.message);
					if(data.flag){
						$("#processDefTable").bootstrapTable('refresh');
					}
				},
				error : function(date){
					alert("网络出错，请重新发送。");
				}
			});
		}, function(index){
			layer.close(index);
		});
}