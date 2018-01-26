    function editBasic(companyId){
    	var url = jsBasePath+"/manager/company/toEditBasic.html?companyId="+companyId;
    	layer.open({
    		type: 2,
    		shade : [ 0.5, '#000' ],offset : [ '10%' ],area: ['480px','350px'],
    		title: "修改机构ID",
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
    
    function editDetail(companyId){
    	var url = jsBasePath+"/manager/company/toEditDetail.html?companyId="+companyId;
    	layer.open({
    		type: 2,
    		shade : [ 0.5, '#000' ],offset : [ '10%' ],area: ['1000px','750px'],
    		title: "修改学校信息",
    		content:url,
    		cancel: function(index){
              layer.close(index);
            },
            end:function(){
            	initTable();
            }
       });
       return false;
    }


function freshOrg(companyId){
    	layer.confirm("确定更新组织数据", function(index){
    		layer.close(index);
    		var index1 = layer.load(3, {shade: [0.3]});
    		$.ajax({
    			url : jsBasePath + '/manager/company/refreshOrg.html',
    			async : false,
    			method : 'POST',
    			data : {
    				companyId : companyId
    			},
    			dataType : "json",
    			success : function(data) {
    				layer.close(index1);
    				// 隐藏
    				if (data.flag) {
    					layer.alert(data.message,{icon:1},function(index){
    						layer.close(index);
    					});
    				} else {
    					layer.alert(data.message,{icon:2});
    				}
    			},
    			error : function(jqXHR, textStatus, errorThrown) {
    				/* alert(2); */
    			}
    		});
    	});    
    }
    
    
    function freshUser(companyId){
    	layer.confirm("确定更新员工数据",{icon: 3, title:'提示'}, function(index){
    		layer.close(index);
    		$.ajax({
    			url : jsBasePath + '/manager/company/refreshUser.html',
    			async : false,
    			method : 'POST',
    			data : {
    				companyId : companyId
    			},
    			dataType : "json",
    			success : function(data) {
    				// 隐藏
    				if (data.flag) {
    					layer.alert(data.message,{icon:1},function(index){
    						layer.close(index);
    					});
    				} else {
    					layer.alert(data.message,{icon:2});
    				}
    			},
    			error : function(jqXHR, textStatus, errorThrown) {
    				/* alert(2); */
    			}
    		});
    	});   
    	
		
    }
    
    
    /**
     * 新增角色
     */
    function add(){
    	 var url = jsBasePath+"/manager/company/toAdd.html";
    	  layer.open({
          type: 2,
          shade : [ 0.5, '#000' ],
    		offset : [ '10%' ],
          area: ['480px','350px'],
          //scrollbar: false,
          title: "新增组织", //不显示标题
          content:url, //捕获的元素
          cancel: function(index){
              layer.close(index);
          },
          end:function(){
          	//query();
          }
      });
       return false;
    }	
    
    
    	function initTable() {
    		//初始化Table 不 
    		$('#companyTable').bootstrapTable('destroy');
    		$("#companyTable").bootstrapTable({
    			url : jsBasePath + '/manager/company/query.html', //请求后台的URL（*）
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
    			columns : [ {
    				field : 'companyName',
    				title : '机构名称',
    				align : 'center'
    			}, {
    				field : 'messageId',
    				title : '短信ID',
    				align : 'center'
    			}, {
    				field : 'companyId',
    				title : '机构ID',
    				align : 'center'
    			}, {
    				field : 'orgUpdateTime',
    				title : '学校部门数据',
    				align : 'center',
    				formatter : function(value, row, index) {
    					var opt = "<button class='layui-btn layui-btn-mini' onclick='return freshOrg(\"" + row.companyId + "\");'><i class='fa fa-refresh'></i>&nbsp;执行更新</button>&nbsp;&nbsp;&nbsp;&nbsp;";
    					if(!!value){
    						opt += "更新时间："+value;
    					}
    					return opt;
    				}
    			}, {
    				field : 'userUpdateTime',
    				title : '学校员工数据',
    				align : 'center',
    				formatter : function(value, row, index) {
    					var opt = "<button class='layui-btn layui-btn-mini' onclick='return freshUser(\"" + row.companyId + "\");'><i class='fa fa-refresh'></i>&nbsp;执行更新</button>&nbsp;&nbsp;&nbsp;&nbsp;";
    					if(!!value){
    						opt += "更新时间："+value;
    					}
    					return opt;
    				}
    			}, {
    				field : 'companyId',
    				title : '学校配置',
    				align : 'center',
    				formatter : function(value, row, index) {
    					var op = "";
    					op += "<button  class='layui-btn layui-btn-mini' onclick='return editBasic(\"" + row.companyId + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;修改ID</button >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    					op += "<button  class='layui-btn layui-btn-mini' onclick='return editDetail(\"" + row.companyId + "\");'><i class='fa fa-pencil-square-o'></i>&nbsp;学校信息配置</button >&nbsp;";
    					return op;
    				}
    			}],
    			onLoadSuccess : function(dataAll) {},
    			onLoadError : function() {
    				//mif.showErrorMessageBox("数据加载失败！");
    			}
    		});
    	}
    	;
    	//传递的参数
    	function queryParams(params) {
    		return {
    			pageSize : params.limit,
    			pageNow : params.offset / params.limit + 1
    		};
    	}