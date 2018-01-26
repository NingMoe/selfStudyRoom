	layui.use([ 'table', 'laypage' ], function() {
		var table = layui.table, laypage = layui.laypage;  var form = layui.form;    
		    //监听省份选择事件
		    form.on('select(schoolProvinceId)', function(data){    	
		    	$("#schoolCity").empty();
				var index = layer.load(3, {shade: [0.3]});
				$.post(jsBasePath+"/basic/areaInfo/getArea.html",{areaLevel:2,parentAreaCode:data.value},function(data,status){
					layer.close(index); 
					var proHtml ="<option value=''>请选择</option>";
					$.each(data,function(i,org){
						proHtml += "<option value='" + org.id +"'>" +org.areaName + "</option>";
					})
					$("#schoolCity").html(proHtml);
					form.render();
				},"json");
				return false;
			});
		    //监听城市选择事件
		    form.on('select(schoolCityId)', function(data){
		    	$("#schoolArea").empty();
				var index = layer.load(3, {shade: [0.3]});
				$.post(jsBasePath+"/basic/areaInfo/getArea.html",{areaLevel:3,parentAreaCode:data.value},function(data,status){
					layer.close(index); 
					var proHtml ="<option value=''>请选择</option>";
					$.each(data,function(i,org){
						proHtml += "<option value='" + org.id +"'>" +org.areaName + "</option>";
					})
					$("#schoolArea").html(proHtml);
					form.render();
				},"json");
				return false;
			});
		var tableIns = table.render({
			elem : '#ccrTable',
			url : jsBasePath + '/basic/school/query.html',
			page : {
				layout : [ 'count', 'prev', 'page', 'next', 'skip', 'limit' ],
				groups : 3, //只显示 1 个连续页码
				first : false, //不显示首页
				last : false
			},
			cols : [ [ {
				field : 'schoolProvinceName',
				width : "15%",
				title : '省份',
				align : "center"
			}, {
				field : 'schoolCityName',
				width : "15%",
				title : '城市名称',
				align : "center"
			}, {
				field : 'schoolAreaName',
				width : "10%",
				title : '区域',
				align : "center"
			}, {
				field : 'schoolType',
				width : "10%",
				title : '学校类型',
				align : "center",
				templet : "#schoolTypeTpl"
			}, {
				field : 'schoolName',
				width : "20%",
				title : '学校名称',
				align : "center"
			}, {
				field : 'isValid',
				width : "10%",
				title : '状态',
				align : "center",
				templet : "#isValidTpl"
			},{
				fixed : 'right',
				width : "20%",
				title : '操作',
				align : 'center',
				toolbar : '#schoolBar'
			} ] ]
		});

		table.on('tool(school)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				edit(data.id);
			} else if (obj.event === 'forbidden') {
				del(data.id,2);
			} else if(obj.event === 'use'){
				del(data.id,1);
			}
		});
		$('#searchBtn').bind('click', function() {
			reloadTable();
		});

		function reloadTable() {
			tableIns.reload({
				where : {
					schoolProvince : $("#schoolProvince").val(),
					schoolCity : $("#schoolCity").val(),
					schoolArea : $("#schoolArea").val(),
					schoolType : $("#schoolType").val(),
					schoolName : $("#schoolName").val()
				}
			});
		}

		//编辑
		function edit(id){
			 var url = jsBasePath+"/basic/school/toEdit.html?id="+id;
			 layer.open({
			     type: 2,
			     shade : [ 0.5, '#000' ],
			     title: "编辑公立学校数据", //
				 offset : [ '10%' ],
			     area: ['600px','60%'],	     
			     content:url, //捕获的元素
			     cancel: function(index){
			         layer.close(index);
			     },
			     end:function(){
			    	 
			     }
		});
			  return false;
		}
		//禁用
		function del(id,status){
			var m="";
			if(status==2){
				m="确认要禁用该公立学校?";
			}
			if(status==1){
				m="确认要启用该公立学校?";
			}
			layer.confirm(m, {
				  btn: ['是','否'] ,//按钮
				  offset: '10%',
				  btnAlign:'c'
				}, function(index){
					$.post(jsBasePath+"/basic/school/delete.html",{deleteIds:id,status:status},function(data,status){
						layer.close(index);
						if(data!=null){
							layer.msg(data.message);
							if(data.flag==true){
								reloadTable();
							}
						}
					},"json");
				}, function(index){
					layer.close(index);
			});
		}

	});
	//批量导入
	function bath_add(){
		 var url = jsBasePath+"/basic/school/batch_add.html";
		 layer.open({
		     type: 2,
		     shade : [ 0.5, '#000' ],
		     title: "批量导入公立学校数据", //
			 offset : [ '10%' ],
		     area: ['500px','60%'],	     
		     content:url, //捕获的元素
		     cancel: function(index){
		         layer.close(index);
		     },
		     end:function(){
		    	 
		     }
	 });
		  return false;
	}
	//添加
	function add(){
		 var url = jsBasePath+"/basic/school/toAdd.html";
		 layer.open({
		     type: 2,
		     shade : [ 0.5, '#000' ],
		     title: "新增公立学校数据", //
			 offset : [ '10%' ],
		     area: ['600px','60%'],	     
		     content:url, //捕获的元素
		     cancel: function(index){
		         layer.close(index);
		     },
		     end:function(){
		    	 
		     }
	 });
		  return false;
	}
