var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		view: {
		    selectedMulti: true, //设置是否能够同时选中多个节点
		    showIcon: true, //设置是否显示节点图标
		},
		async : {
			enable : true,
			url : jsBasePath+"/jw/queryTreeById.html",
			type : "post",
			autoParam: ["id"],
			dataType: 'json'
		},
		checkable: true,
		showIcon: true,
		expandSpeed: "",
		check : {
			enable : true,
			chkStyle : "checkbox"
		},
		data : {
			keep: {
				parent: true
			},
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
				rootPId : 0
			}
		},
		callback: {
			onClick: zTreeOnClick,
			onCheck: zTreeOnCheck
		}
	};
	
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#tree"), setting, null);
		$("#tj").bind("click",function(){
			var teachers = $("#teach").find("input[name='teacher']");
			if(teachers.size()==0){
				layer.alert("请选择需要初始化数据的老师!");
			}else{
				var ids = teachers.map(function(){
					return this.value;
				}).get().join(",");
				var index = layer.load(1, {shade: [0.8, '#393D49']});
				$.ajax({
					type : "post",
					dataType : "json",
					url : jsBasePath+"/jw/initTeachData.html",
					data : {			
						ids:ids
					},
					success: function(data){
						layer.close(index); 
						var message = "";
						if(!!data.errorEmails){
							message = "其中"+data.errorEmails+"不存在教师编号";
							layer.alert(message); 
						}else{
							layer.alert("初始化成功"); 
						}
					},
					error : function(data, status, e) {	
						layer.close(index);
					}
				});	
			}
		});
	});	
	
	function zTreeOnClick(event, treeId, treeNode) {
	   //console.log(treeNode);
	};
	
	function zTreeOnCheck(event, treeId, treeNode) {
		var nodes = zTreeObj.getCheckedNodes(true);
		var i = 0;
		var teachers = $(nodes).map(function(){
			if(!this.isParent){
				i++;
				if(i%10==0){
					return "<input type='hidden' name='teacher' value='"+this.id+"'><span style='margin-left:20px;width:60px;display:block;float:left;height:30px;'>"+this.name+"</span><br>";
				}else{
					return "<input type='hidden' name='teacher' value='"+this.id+"'><span style='margin-left:20px;width:60px;display:block;float:left;height:30px;'>"+this.name+"</span>";
				}
			}
		}).get().join("");
		
		$("#teach").html(teachers);
	}