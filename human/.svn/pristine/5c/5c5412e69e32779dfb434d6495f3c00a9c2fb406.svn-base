var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		async : {
			enable : true,
			url : jsBasePath+"/customer/centerManager/showTree.html",
			type : "post",
			otherParam : [ "menuId", menuId]
		},
		check : {
			enable : true
		},
		data : {
			key : {
				checked : "isChecked",
				name : "deptName",
				title : "deptName",
			},
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
				rootPId : 0
			}
		},
		callback : {
			onAsyncSuccess : zTreeOnAsyncSuccess
		}
	};

	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		$("#treeDiv").height($(window).height()-80);
		return;
	}

	 function getfun() {
		 var deptids="";
		 var nodes = zTreeObj.getCheckedNodes(true);
		 for (var i = 0; i < nodes.length; i++){
			deptids +=$.trim(nodes[i].id)+",";
         }
     	 $.ajax({
        		async:false, //请勿改成异步，下面有些程序依赖此请数据
        		type : "POST",
        		data:{menuId:menuId,deptids:deptids},
        		url:  jsBasePath+"/customer/centerManager/saveMenuDept.html",
        		dataType:'json',
        		success: function(data){
        			if(data.flag){
        				layer.alert(data.msg,{icon:1},function(){
        					closeFrame();
        				});
                	}else if(data.flag == false){
                		layer.alert("分配失败",{icon:2});
                  };
         		}
        	});
     }
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#tree"), setting, null);
	});