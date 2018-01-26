var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		async : {
			enable : true,
			url : jsBasePath+"/manager/role/permissioRole.html",
			type : "post",
			otherParam : [ "id", roleId ]
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "p",
				"N" : "s"
			}
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
		zTreeObj.expandAll(true);
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
            		data:{roleId:roleId,rescId:fids},
            		url:  jsBasePath+"/manager/role/saveRoleRes.html",
            		dataType:'json',
            		success: function(json){
            			if(json == true){
            				layer.alert("分配成功,请重新登陆后生效",{icon:1},function(){
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