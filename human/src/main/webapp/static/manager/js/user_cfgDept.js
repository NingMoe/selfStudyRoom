var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		async : {
			enable : true,
			url : jsBasePath+"/manager/user/showTree.html",
			type : "post",
			otherParam : [ "userId", userId]
		},
		check : {
			enable : true
		},
		data : {
			key : {
				checked : "isChecked",
				name : "name",
				title : "name",
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
		//zTreeObj.expandAll(true);
		/*var index=zTreeObj.getNodeIndex(treeNode);
		alert(index);
		if(index=='0'){
			zTreeObj.expandNode(treeNode,true,true,true);
		}*/
		return;
	}

	 function getfun() {
			var fids="";
			var nodes = zTreeObj.getCheckedNodes(true);;
          for (var i = 0; i < nodes.length; i++)
          {
          	fids +=$.trim(nodes[i].id)+",";
          }
         	 $.ajax({
            		async:false, //请勿改成异步，下面有些程序依赖此请数据
            		type : "POST",
            		data:{userId:userId,rescId:fids},
            		url:  jsBasePath+"/manager/user/saveUserDept.html",
            		dataType:'json',
            		success: function(json){
            			if(json == true){
            				layer.alert("分配成功",{icon:1},function(){
            					closeFrame();
            				});
                    	}else if(json == false){
                    		layer.alert("分配失败",{icon:2});
                      };
             		}
            	});
     }
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#tree"), setting, null);
	});