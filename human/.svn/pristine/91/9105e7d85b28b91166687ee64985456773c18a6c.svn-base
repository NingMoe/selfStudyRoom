var zTreeObj;
var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			dblClickExpand: dblClickExpand,
			selectedMulti: false
	},
	async : {
		enable : true,
		url : jsBasePath+"/customer/select/query.html",
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
			drag: {
				autoExpandTrigger: true,
				prev: dropPrev,
				inner: dropInner,
				next: dropNext
			},
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn,
			removeTitle: "删除选项",
			renameTitle: "编辑选项"
		},
		data: {
			key : {
				checked : "isChecked",
				name : "name",
				title : "name",
			},
			simpleData : {
				enable : true,
				idKey : "name",
				pIdKey : "parentName",
				rootPId : 1
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop,
			beforeDragOpen: beforeDragOpen,
			onDrag: onDrag,
			onDrop: onDrop,
			onExpand: onExpand,
			onAsyncSuccess : zTreeOnAsyncSuccess,
			beforeEditName: toEdit,
			beforeRemove: deleteResource,
			beforeRename:beforeRename
		}
	};	

function beforeRename(treeId, treeNode, newName, isCancel) {
	className = (className === "dark" ? "":"dark");
	if (newName.length == 0) {
			zTreeObj.cancelEditName();
			layer.alert("选项值不能为空.",{icon:2});
			return false;
	}
	return true;
}
var className = "dark", curDragNodes, autoExpandNode;
function dblClickExpand(treeId, treeNode) {
	return treeNode.level > 0;
}
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		zTreeObj.expandAll(true);
	}

	var newCount = 1;
	//自定义增加按钮
	function addHoverDom(treeId, treeNode) {
		if(treeNode.level>=4){
			return;
		}
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='增加选项' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			treeNode=zTreeObj.addNodes(treeNode, {id:newCount,pId:treeNode.id, name:"新增选项-" + (newCount++)});
			zTreeObj.editName(treeNode[0]);
			//resourceAdd(treeNode.id,treeNode.name,treeNode.level);
		});
	};
	//移除新增按钮
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	//根节点不出现删除页面
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.level==0;
	}
	//根节点不出现编辑
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.level==0;
	}
	
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#tree"), setting, null);
	});	
	
	function toEdit(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		zTreeObj.selectNode(treeNode);
		zTreeObj.editName(treeNode);
		return false;
	}
	
	function deleteResource(treeId, treeNode){
		className = (className === "dark" ? "":"dark");
		layer.confirm('确认要该选项以及所有子选项么?', {
			  btn: ['是','否'] //按钮
			}, function(index){
				zTreeObj.removeNode(treeNode);
				layer.close(index);
				return true;
			}, function(index){
				layer.close(index);
			});
		return false;
		}
	function dropPrev(treeId, nodes, targetNode) {
		if (targetNode.level==0) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode()&&targetNode.level==0) {
					return false;
				}
			}
		}
		return true;
	}
	function dropInner(treeId, nodes, targetNode) {
		if (targetNode==null||(targetNode && targetNode.level >=4)) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				if (!targetNode) {
					return false;
				} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode) {
					return false;
				}
				
			}
		}
		return true;
	}
	function dropNext(treeId, nodes, targetNode) {
		if (targetNode.level==0) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode()&&targetNode.level==0) {
					return false;
				}
			}
		}
		return true;
	}

	function beforeDrag(treeId, treeNodes) {
		className = (className === "dark" ? "":"dark");
		curDragNodes = treeNodes;
		return true;
	}
	function beforeDragOpen(treeId, treeNode) {
		autoExpandNode = treeNode;
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
		className = (className === "dark" ? "":"dark");
		return true;
	}
	function onDrag(event, treeId, treeNodes) {
		className = (className === "dark" ? "":"dark");
	}
	function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
		className = (className === "dark" ? "":"dark");
	}
	function onExpand(event, treeId, treeNode) {
		if (treeNode === autoExpandNode) {
			className = (className === "dark" ? "":"dark");
		}
	}
	function refresh(){
		zTreeObj.reAsyncChildNodes(null, "refresh");
	}
	
	function save(){
		var nodes=zTreeObj.transformToArray(zTreeObj.getNodes());
		 var index =layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/customer/select/update.html",{nodes: JSON.stringify(nodes)},function(data,status){
				layer.close(index); 
				if(data.flag==false){
					layer.alert(data.msg,{icon:2});
				}else{
					layer.alert(data.msg,{icon:1});
				}
			},"json");
	}