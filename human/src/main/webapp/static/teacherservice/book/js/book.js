    	
//新增图书弹出框
function add(){
	layer.open({
    type: 2,
    title: '新增图书',
    shadeClose: false,
    shade: 0.8,
    offset : ['20%'],
	area: ['500px', '400px'],
	content: jsBasePath+'/teacher/library/addview.html',
	end:function(){
		$("#processDefTable").bootstrapTable('refresh');
		}
	});
}
    	
//批量导入
function updateexcel(id){
	layer.open({
		type: 2,
		title: '批量导入',
		shadeClose: false,
		shade: 0.8,
		offset : ['20%'],
		area: ['300px', '200px'],
		content: jsBasePath+'/teacher/library/updateexcel.html?id='+id,
		end:function(){
			$("#processDefTable").bootstrapTable('refresh');
		}
	});
}

//修改活动信息
function change(id){
	layer.open({
    	type: 2,
    	title: '修改活动',
    	shadeClose: false,
    	shade: 0.8,
    	offset : ['20%'],
    	area: ['500px', '400px'],
    	content: jsBasePath+'/teacher/library/changeview.html?id='+id,
    	end:function(){
    		$("#processDefTable").bootstrapTable('refresh');
    	}
	}); 
}
    	
//删除图书
function deletebook(id){
	layer.confirm("确认要删除用户?", {
		btn: ['是','否'] ,//按钮
		offset: '10%',
		btnAlign:'c'
		}, function(index){
			$.ajax({
				url : jsBasePath + "/teacher/library/delete.html",
				type : "POST",
				dataType : "json",
				data : {
					id : id,
				},
				success : function(data){
					layer.close(index);
					if(data!=null){
						layer.msg(data.message);
						if(data.flag){
							$("#processDefTable").bootstrapTable('refresh');
						}
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

layui.use(['form',  'laydate'], function(){
	var form = layui.form()
	,layer = layui.layer
	,laydate = layui.laydate;
	//监听指定开关
	form.on('switch(switchTest)', function(data){
		if(this.checked){
			layer.msg("你打开了"+$(data.elem).attr("bookid"));
		}else{
			layer.msg("你关闭了"+$(data.elem).attr("bookid"));
		}
	});
  	      
	//重新加载样式
	initTable(form);
  	      
	$("#sr").click(function(){
		initTable(form);
	})
  	      
  	      
	var start = {
		istoday: false
		, istime: true
   	    , format: 'YYYY-MM-DD hh:mm:ss'
   	    ,choose: function(datas){
   	    	end.min = datas; //开始日选好后，重置结束日的最小日期
   	    	end.start = datas //将结束日的初始值设定为开始日
   	    }
	};

	var end = {
		istoday: false
		, istime: true
		, format: 'YYYY-MM-DD hh:mm:ss'
		,choose: function(datas){
			start.max = datas; //结束日选好后，重置开始日的最大日期
			}
	};

	document.getElementById('left_create_time').onclick = function(){
		start.elem = this;
		laydate(start);
	}

	document.getElementById('right_create_time').onclick = function(){
		end.elem = this
		laydate(end);
	}
});
    	
    	/**
    	 * 导出(批量)
    	 */
    	function select_load(){
    		var ids=getSelectId("processDefTable");
    		if(ids==""){
    			layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
    			return;
    		}
    		layer.confirm("确认导出？", {
    			  btn: ['是','否'] ,//按钮
    			  offset: '10%',
    			  btnAlign:'c'
    			}, function(index){
    				layer.close(index);
    				window.location.href = jsBasePath+"/teacher/library/downselect.html?ids="+ids;
    			}, function(index){
    				layer.close(index);
    			});
    	}
    	
    	/**
    	 * 禁用、启用用户(批量)
    	 */
    	function update_valid(valid){
    		var ids=getSelectId("processDefTable");
    		if(ids == ""){
    			layer.alert('您还未选择任何记录!', {icon: 2,offset:'10%'});
    			return;
    		}
    		var m="";
    		if(valid==0){
    			m="确认要禁用所选书籍?";
    		}
    		if(valid==1){
    			m="确认要启用所选书籍?";
    		}
    		layer.confirm(m, {
    			  btn: ['是','否'] ,//按钮
    			  offset: '10%',
    			  btnAlign:'c'
    			}, function(index){
    				$.ajax({
    					url : jsBasePath+"/teacher/library/openselect.html",
    					type : "POST",
    					dataType : "json",
    					data : {
    						ids : ids,
    						valid : valid
    					},
    					success : function(data){
    						layer.close(index);
    						if(data!=null){
    							layer.msg(data.message);
    							if(data.flag){
    								$("#processDefTable").bootstrapTable('refresh');
    							}
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
