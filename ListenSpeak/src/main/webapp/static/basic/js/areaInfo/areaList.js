layui.use([ 'table', 'laypage' ], function() {
	var table = layui.table, laypage = layui.laypage;
	var form = layui.form;
	  form.on('select(schoolProvince)', function(data){
		  var parentAreaCode=data.value;
		  if(""==parentAreaCode){
			  return;
		  }
		  var index = layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/basic/areaInfo/getArea.html",{parentAreaCode:parentAreaCode,areaLevel:2},function(data,status){
				layer.close(index); 
				var html ="<option value=''>请选择</option>";
				$.each(data,function(i,item){
					html+='<option value="'+item.id+'">'+item.areaName+'</option>'
				})
				 $("#parentAreaCode").html(html);
		         form.render();
			},"json");
			return false;
		}); 
	  
	var tableIns = table.render({
		elem : '#areaTable',
		url :	jsBasePath+'/basic/areaInfo/getAreaPage.html',
		where :{areaLevel : 3},
		page : {
			layout : [ 'count', 'prev', 'page', 'next', 'skip', 'limit' ],
			groups : 3, //只显示 1 个连续页码
			first : false, //不显示首页
			last : false
		},
		cols : [ [  {
			title : '序号',
			templet : '#indexTpl',
			align : "center",
			width : "30%",
		}, {
			field : 'areaName',
			width : "30%",
			title : '行政区名称',
			align : "center"
		},{
			fixed : 'right',
			width : "40%",
			title : '操作',
			align : 'center',
			toolbar : '#areaBar'
		} ] ]
	});
	table.on('tool(area)', function(obj) {
		var data = obj.data;
		if (obj.event === 'edit') {
			edit(data.id);
		} else if (obj.event === 'del') {
			del(data.id);
		} 
	});
	$('#searchBtn').bind('click', function() {
		reloadTable();
	});

	function reloadTable() {
		tableIns.reload({
			where : {
				areaLevel : 3,
				parentAreaCode : $("#parentAreaCode").val()
			}
		});
	}
	/**
	 * 区域编辑
	 * areaLevel 1未对应区域级别
	 * 1省份 2城市 3行政区
	 * ID  区域ID
	 */
	 function edit(id){
		var options = {
				   type: 2,
				   title: '编辑',
				   shadeClose: false,
				   shade:[ 0.5, '#000' ],
				   offset : ['20%'],
				   content: jsBasePath+'/basic/areaInfo/toUpdate.html?id='+id+"&areaLevel="+3
				};
			options.title = "修改区域";
			options.area = ['500px', '300px'];
			layer.open(options); 
	 };
	 /**
		 * 区域删除
		 * ID  区域ID
		 */
		function del(id){
			layer.confirm("确定删除吗?删除后相关联数据都删除且不可恢复!", {
				  btn: ['是','否'] ,//按钮
				  offset: '10%',
				  btnAlign:'c'
				}, function(index){
					$.post(jsBasePath+"/basic/areaInfo/delArea.html",{id:id,areaLevel:3},function(data,status){
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
		};
	});

 function areaLevel(){
	var titleName = "";
	var options = {
			   type: 2,
			   title: '新增',
			   shadeClose: false,
			   shade:[ 0.5, '#000' ],
			   offset : ['20%'],
			   content: jsBasePath+'/basic/areaInfo/toAdd.html?areaLevel='+3
			};
		options.title = "修改区域";
		options.area = ['500px', '300px'];
		layer.open(options); 
};