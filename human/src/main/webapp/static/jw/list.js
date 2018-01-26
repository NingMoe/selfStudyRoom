function initTable(editFlag,form) {
	var refreshFlag = $("#refreshFlag").val();
	var columns = [ {
		checkbox : true,
		fieId : 'id',
		class : "noexport"
	},{
		field : 'teacherName',
		title : '姓名',
		align : 'center',
		formatter : function(value, row, index) {
			return "<a href='javascript:void(0)' onclick='viewKb(\""+row.teacherCode+"\")'>"+value+"</a>";
		}
	}/*,{
		field : 'teacherCode',
		title : '课时统计',
		align : 'center',
		formatter : function(value, row, index) {
			return "<a href='javascript:void(0)' onclick='viewKsTj(\""+row.teacherCode+"\",\""+row.cnHours+"\",\""+row.teacherName+"\")'>"+value+"</a>";
		}
	}*/, {
		field : 'sex',
		title : '性别',
		align : 'center' ,
		formatter : function(value, row, index) {
			if (value == 'M') {
				return "男";
			}
			if (value == 'F') {
				return "女";
			}
		}
	}, {
		field : 'dksx',
		title : '带课属性',
		align : 'center' 
	}, {
		field : 'jyz',
		title : '教研组',
		align : 'center' 
	}, {
		field : 'email',
		title : '邮箱',
		align : 'center'
	}, {
		field : 'dept',
		title : '子部门',
		align : 'center'
	}, {
		field : 'grades',
		title : '带课年级',
		align : 'center'
	}, {
		field : 'sites',
		title : '带课校区',
		align : 'center'
	}, {
		field : 'sexCurr',
		title : '1对6班量',
		align : 'center'
	}, {
		field : 'oneCurr',
		title : '1对1班量',
		align : 'center'
	}, {
		field : 'oneSx',
		title : '1对1上限',
		align : 'center'
	}, {
		field : '',
		title : '1对1缺口',
		align : 'center',
		formatter : function(value, row, index) {
			if(row.oneSx){
				var qk = row.oneSx - row.oneCurr;
				if(qk>0){
					return "<font style='color:green'>"+qk+"</font>";
				}else{
					return "<font style='color:red'>"+qk+"</font>";
				}
			}else{
				return "";
			}
		}
	}];
	if(editFlag=="true"){
		columns.push({
			field : 'szSj',
			title : '上周课时',
			align : 'center'
		});
		columns.push({
			field : 'sySj',
			title : '上月课时',
			align : 'center'
		});
		columns.push({
			field : 'xySj',
			title : '本月课时',
			align : 'center'
		});
		columns.push({
			field : 'yjSj',
			title : '财年月均',
			align : 'center'
		});
		columns.push({
			field : 'wsSj',
			title : '财年预排',
			align : 'center'
		});
		columns.push({
			field : 'cnSj',
			title : '财年进度',
			align : 'center',
			formatter : function(value, row, index) {
				var penCnt = value*100/row.cnHours;
				return '<div style="margin-top:10%;width:100px" class="layui-progress"><div class="layui-progress-bar" lay-percent="'+penCnt+'%" style="width:'+penCnt+'%;"></div><span class="layui-progress-text">'+value+'/'+row.cnHours+'</span></div>';
			}
		});
	};
	columns.push({
		field : 'remark',
		title : '备注',
		align : 'center'
	});
	var jyzFlag = $("#jyzEdit").val();
	if(jyzFlag=="1"){
		columns.push({
			field : '',
			align : 'center',
			title : '分配教研组',
			switchable : false,
			formatter : function(value, row, index) {
				var op = "";
				op += "<button  class='layui-btn layui-btn-mini' onclick='return fpjyz(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;分配教研组</button >&nbsp;";
				return op;
			},
			class : "noexport"
		});
	}
	
	/*var jyzLock = $("#jyzLock").val();
	var currUser = $("#currUser").val();
	if(jyzLock=="1"){
		columns.push({
			field : 'lockState',
			align : 'center',
			title : '数据锁定',
			switchable : false,
			formatter : function(value, row, index) {
				if(value=="1"){
					return "<button  class='layui-btn layui-btn-mini' onclick='return lock(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;锁定数据</button >&nbsp;";
				}
				if(value=="2"){
					return "<button  class='layui-btn layui-btn-mini' onclick='return unlock(\"" + row.id + "\",\""+row.lockUser+"\");'><i class='fa fa-pencil-square'></i>&nbsp;解锁数据</button >&nbsp;";
				}
			},
			class : "noexport"
		});
	}*/
	
	if(editFlag=="true"){
		columns.push({
			field : '',
			align : 'center',
			title : '操作',
			switchable : false,
			formatter : function(value, row, index) {
				var op = "";
				op += "<button  class='layui-btn layui-btn-mini' onclick='return edit(\"" + row.id + "\");'><i class='fa fa-pencil-square'></i>&nbsp;编辑</button >&nbsp;";
				op += "<button  class='layui-btn layui-btn-mini' onclick='return refreshData(\"" + row.id + "\");'><i class='fa fa-refresh'></i>&nbsp;刷新</button >&nbsp;";
				op += "<button  class='layui-btn layui-btn-mini' onclick='return del(\"" + row.teacherCode + "\");'><i class='fa fa-remove'></i>&nbsp;删除</button >&nbsp;";
				return op;
			},
			class : "noexport"
		});
	}else if(refreshFlag=="1"){
		columns.push({
			field : '',
			align : 'center',
			title : '操作',
			switchable : false,
			formatter : function(value, row, index) {
				var op = "";
				op += "<button  class='layui-btn layui-btn-mini' onclick='return refreshData(\"" + row.id + "\");'><i class='fa fa-refresh'></i>&nbsp;刷新</button >&nbsp;";
				return op;
			},
			class : "noexport"
		});
	}
	
    		//初始化Table 不 
    		$('#teacherTable').bootstrapTable('destroy');
    		$("#teacherTable").bootstrapTable({
    			url : jsBasePath + '/jw/query.html', //请求后台的URL（*）
    			//method: 'get',      //请求方式（*）
    			method : 'post',
    			contentType : "application/x-www-form-urlencoded", //必须的,post
    			striped : true, //是否显示行间隔色
    			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    			pagination : true, //是否显示分页（*）
    			sortable : false, //是否启用排序
    			//sortOrder : "asc", //排序方式
    			queryParams : queryParams, //传递参数（*）
    			sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
    			pageNumber : 1, //初始化加载第一页，默认第一页
    			pageSize : 10, //每页的记录行数（*）
    			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
    			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    			strictSearch : false,
    			showColumns : true, //是否显示所有的列
    			showRefresh : false, //是否显示刷新按钮
    			minimumCountColumns : 2, //最少允许的列数
    			clickToSelect : false, //是否启用点击选中行
    			//height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    			uniqueId : "id", //每一行的唯一标识，一般为主键列
    			showToggle : false, //是否显示详细视图和列表视图的切换按钮
    			cardView : false, //是否显示详细视图
    			detailView : false, //是否显示父子表
    			smartDisplay : true, // 智能显示 pagination 和 cardview 等
    			toolbar : '#toolbar', //工具按钮用哪个容器
    			toolbarAlign : 'left',
    			showExport: true,
    			showType : "all",
    			fileName : "教师带班信息表",
    			fixedColumns: true,
    	        fixedNumber:3,
    			columns : columns,
    			onLoadSuccess : function(dataAll) {
    				if(editFlag=="true"){
    					setTotalCn();
    				}
    			},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	}
    	//传递的参数
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1,
    			teacherName :$("#teacherName").val(),
    			sex :$("#sex").val(),
    			dksx : $("#dksx").val(),
    			jyz : $("#jyz").val(),
    			dept :$("#dept").val(),
    			grades :$("#grades").val(),
    			sites :$("#sites").val(),
    			subject :$("#subject").val(),
    			oneCurr :$("#oneCurr").val(),
    			oneSx :$("#oneSx").val(),
    			sexCurr :$("#sexCurr").val(),
    			oneQk :$("#oneQk").val()
    		};
    	}
    	
    	function setTotalCn(){
    		$.ajax({
				url : jsBasePath+"/jw/getTotal.html",
				data : {
					teacherName :$("#teacherName").val(),
	    			sex :$("#sex").val(),
	    			dksx : $("#dksx").val(),
	    			jyz : $("#jyz").val(),
	    			dept :$("#dept").val(),
	    			grades :$("#grades").val(),
	    			sites :$("#sites").val(),
	    			subject :$("#subject").val(),
	    			oneCurr :$("#oneCurr").val(),
	    			oneSx :$("#oneSx").val(),
	    			sexCurr :$("#sexCurr").val(),
	    			oneQk :$("#oneQk").val()
				},
				dataType : "json",
				type : "post",
				async:false,
				success : function(res) {
					console.log(res);
					var penCnt = (res.cnSj*100/res.cnHours).toFixed(2);
					var totalDiv = '<div id="cfzCnjdxs" style="float:left;width:600px;"><p style="float:left;margin-top:20px;margin-left:30px;">财年进度合计：</p><div style="float:left;width:160px;margin-top:20px;" class="layui-progress layui-progress-big"><div class="layui-progress-bar" lay-percent="'+penCnt+'%" style="width:'+penCnt+'%;"></div></div><p style="float:left;margin-top:20px;margin-left:5px;"><span>'+res.cnSj+'/'+res.cnHours+'&nbsp;&nbsp;&nbsp;&nbsp;财年预排合计：'+res.wsSj+'</span></p></div>';
					if(!$("#cfzCnjdxs").size()){
						$(totalDiv).insertAfter(".bs-bars");
					}
					
				}
			});
    	}
    	
    	function edit(id){
        	var url = jsBasePath+"/jw/toEdit.html?id="+id;
        	layer.open({
        		type: 2,
        		shade : [ 0.5, '#000' ],
        		offset : [ '10%' ],
        		area: ['1000px','80%'],
        		title: "修改老师信息",
        		content:url,
        		cancel: function(index){
                  layer.close(index);
                },
                end:function(){
                	//query();
                }
           });
           return false;
        }
    	
    	function fpjyz(id){
    		var url = jsBasePath+"/jw/toFpjyz.html?id="+id;
        	layer.open({
        		type: 2,
        		shade : [ 0.5, '#000' ],
        		offset : [ '10%' ],
        		area: ['480px','380px'],
        		title: "分配教研组",
        		content:url,
        		cancel: function(index){
                  layer.close(index);
                },
                end:function(){
                	//query();
                }
           });
           return false;
    	}
    	
    	function viewKsTj(teacherCode,cnHours,teacherName){
    		var url = jsBasePath+"/jw/viewKsTj.html?teacherCode="+teacherCode+"&cnHours="+cnHours+"&teacherName="+teacherName;
    		layer.open({
        		type: 2,
        		shade : [ 0.5, '#000' ],
        		offset : [ '20%' ],
        		area: ['400px','330px'],
        		title: "课时统计",
        		content:url,
        		cancel: function(index){
                  layer.close(index);
                },
                end:function(){
                	//query();
                }
           });
           return false;
    	}
    	
    	function viewKb(teacherCode){
    		var url = jsBasePath+"/jw/viewKb.html?teacherCode="+teacherCode;
    		layer.open({
        		type: 2,
        		shade : [ 0.5, '#000' ],
        		offset : [ '10%' ],
        		area: ['80%','80%'],
        		title: "查看老师课表",
        		content:url,
        		cancel: function(index){
                  layer.close(index);
                },
                end:function(){
                	//query();
                }
           });
           return false;
    	}
    	
    	
    	function refreshData(id){
    		var index =layer.load(3, {shade: [0.3]});
    		var editflag = $("#editFlag").val();
    		$.post(jsBasePath+"/jw/refreshData.html",{ids:id},
    			function(data,status){
    				layer.close(index); 
    				if(data.flag==false){
    					layer.alert(data.message,{icon:2});
    				}else{
    					layer.alert(data.message,{icon:1},function(index1){
    						layer.close(index1); 
    						initTable(editflag);
    					});
    				}
    		},"json");
    		return false;
    	}
    	
    	function refreshAllData(){
    		var index =layer.load(3, {shade: [0.3]});
    		var editflag = $("#editFlag").val();
    		$.post(jsBasePath+"/jw/refreshAllData.html",
    			function(data,status){
    				layer.close(index); 
    				if(data.flag==false){
    					layer.alert(data.message,{icon:2});
    				}else{
    					layer.alert(data.message,{icon:1},function(index1){
    						layer.close(index1); 
    						initTable(editflag);
    					});
    				}
    		},"json");
    		return false;
    	}
    	
    	function del(teacherCode){
    		layer.confirm('确定要删除该老师吗？', {
    		    btn: ['确定','取消'], //按钮
    		    shade: false //不显示遮罩
    		}, function(index){
    			var index1 =layer.load(3, {shade: [0.3]});
        		var editflag = $("#editFlag").val();
        		$.post(jsBasePath+"/jw/del.html",{teacherCode:teacherCode},
        			function(data,status){
        				layer.close(index); 
        				layer.close(index1);
        				if(data.flag==false){
        					layer.alert(data.message,{icon:2});
        				}else{
        					layer.alert(data.message,{icon:1},function(index2){
        						layer.close(index2); 
        						initTable(editflag);
        					});
        				}
        		},"json");
        		return false;
    		});
    	}
    	
    	function lock(id){
    		layer.confirm('确定锁定老师的编辑信息吗？', {
    		    btn: ['确定','取消'], //按钮
    		    shade: false //不显示遮罩
    		}, function(index){
    			var index1 =layer.load(3, {shade: [0.3]});
        		var editflag = $("#editFlag").val();
        		$.post(jsBasePath+"/jw/lock.html",{id:id,lockState:"2"},
        			function(data,status){
        				layer.close(index); 
        				layer.close(index1);
        				if(data.flag==false){
        					layer.alert(data.message,{icon:2});
        				}else{
        					layer.alert(data.message,{icon:1},function(index2){
        						layer.close(index2); 
        						initTable(editflag);
        					});
        				}
        		},"json");
        		return false;
    		});
    	}
    	
    	function unlock(id,lockUser){
    		var currUser = $("#currUser").val();
    		if(currUser != lockUser){
    			layer.alert("请联系"+lockUser+"解锁",{icon:2});
    			return;
    		}
    		layer.confirm('确定解锁老师的编辑信息吗？', {
    		    btn: ['确定','取消'], //按钮
    		    shade: false //不显示遮罩
    		}, function(index){
    			var index1 =layer.load(3, {shade: [0.3]});
        		var editflag = $("#editFlag").val();
        		$.post(jsBasePath+"/jw/lock.html",{id:id,lockState:"1"},
        			function(data,status){
        				layer.close(index); 
        				layer.close(index1);
        				if(data.flag==false){
        					layer.alert(data.message,{icon:2});
        				}else{
        					layer.alert(data.message,{icon:1},function(index2){
        						layer.close(index2); 
        						initTable(editflag);
        					});
        				}
        		},"json");
        		return false;
    		});
    	}
