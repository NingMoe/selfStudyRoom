var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
		},
		async : {
			enable : true,
			url : "/manager/resource/queryResource",
			type : "post"
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "p",
				"N" : "s"
			}
		},
		edit: {
			enable: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn,
			removeTitle: "删除资源",
			renameTitle: "编辑资源"
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
			onAsyncSuccess : zTreeOnAsyncSuccess,
			beforeEditName: toEdit,
			beforeRemove: deleteResource,
			onClick:getSelectId
		}
	};

	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		$("#resourceDescTable").hide();
		zTreeObj.expandAll(true);
	}

	//自定义增加按钮
	function addHoverDom(treeId, treeNode) {
		if(treeNode.level>=3){
			return;
		}
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='增加资源' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			resourceAdd(treeNode.id,treeNode.name,treeNode.level);
		});
	};
	//移除新增按钮
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	//父节点不出现删除页面
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isParent;
	}
	//根节点不出现编辑
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.level==0;
	}
	
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#tree"), setting, null);
	});	
	
	function resourceAdd(parentId,parentName,newLevel){
		 var url = "/manager/resource/addUI?parentId="+parentId+"&parentName="+parentName+"&type="+newLevel;
		  layer.open({
            type: 2,
            shade : [ 0.5, '#000' ],
			offset : [ '20px' ],
            area: ['520px','80%'],
            title: "资源新增", //不显示标题
            content:url, //捕获的元素
            cancel: function(index){
                layer.close(index);
            },
            end:function(){
            }
        });
	}
	function toEdit(treeId, treeNode){
		 var url = "/manager/resource/toEdit?id="+treeNode.id;
		  layer.open({
            type: 2,
            shade : [ 0.5, '#000' ],
			offset : [ '20px' ],
            area: ['520px','80%'],
            title: "资源编辑", //不显示标题
            content:url, //捕获的元素
            cancel: function(index){
                layer.close(index);
            },
            end:function(){

            }
        });
		  return false;
	}
	function getSelectId(event, treeId, treeNode,clickFlag){
		if(clickFlag==1){
			$("#Pid").html(treeNode.parentId);
			$("#Pname").html(treeNode.parentName);
			$("#rid").html(treeNode.id);
			$("#rname").html(treeNode.name);
			$("#key").html(treeNode.resKey);
			$("#url").html(treeNode.resUrl);
			$("#sort").html(treeNode.sort);
			if(treeNode.type==0){
				$("#type").html("目录");
			}
			if(treeNode.type==1){
				$("#type").html("菜单");
			}
			if(treeNode.type==2){
				$("#type").html("按钮");
			}
			$("#iconUrl").html("<i class='fa "+treeNode.iconUrl+"' aria-hidden='true'></i>");
			$("#desc").html(treeNode.rescDesc);
			$("#resourceDescTable").show();
		};
	}
	
	function deleteResource(treeId, treeNode){
		layer.confirm('确认要删除所选资源?', {
			  btn: ['是','否'] //按钮
			}, function(index){
				var index1 =layer.load(3, {shade: [0.3]});
				$.post("/manager/resource/delete",{id:treeNode.id},function(data,status){
					layer.close(index1);
					if(data!=null){
						layer.msg(data.message);
						if(data.flag==true){
							refresh();
						}
					}
				},"json");
			}, function(index){
				layer.close(index);
			});
		return false;
		}
	
	function refresh(){
		zTreeObj.reAsyncChildNodes(null, "refresh");
	}