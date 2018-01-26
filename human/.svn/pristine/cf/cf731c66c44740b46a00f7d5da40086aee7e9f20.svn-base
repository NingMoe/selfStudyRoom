layui.use([ 'form','layedit', 'laydate' ], function() {
	var form = layui.form(), laydate = layui.laydate,layedit=layui.layedit;
//	layedit.build('daswComments'); //建立编辑器
	var preStarttime = {
		istime : true,
		format : 'YYYY-MM-DD',
		choose : function(datas) {
		}
	};
	document.getElementById('preStarttime').onclick = function() {
		preStarttime.elem = this;
		laydate(preStarttime);
	}
	var preEndtime = {
		istime : true,
		format : 'YYYY-MM-DD',
		choose : function(datas) {
		}
	};

	document.getElementById('preEndtime').onclick = function() {
		preEndtime.elem = this;
		laydate(preEndtime);
	}
	var basStarttime = {
		istime : true,
		format : 'YYYY-MM-DD',
		choose : function(datas) {
		}
	};

	document.getElementById('basStarttime').onclick = function() {
		basStarttime.elem = this;
		laydate(basStarttime);
	}
	var basEndtime = {
		istime : true,
		format : 'YYYY-MM-DD',
		choose : function(datas) {
		}
	};

	document.getElementById('basEndtime').onclick = function() {
		basEndtime.elem = this;
		laydate(basEndtime);
	}
	var strStarttime = {
		istime : true,
		format : 'YYYY-MM-DD',
		choose : function(datas) {
		}
	};

	document.getElementById('strStarttime').onclick = function() {
		strStarttime.elem = this;
		laydate(strStarttime);
	}
	var strEndtime = {
		istime : true,
		format : 'YYYY-MM-DD',
		choose : function(datas) {
		}
	};

	document.getElementById('strEndtime').onclick = function() {
		strEndtime.elem = this;
		laydate(strEndtime);
	}
	var dasStarttime = {
			istime : true,
			format : 'YYYY-MM-DD',
			choose : function(datas) {
			}
		};

		document.getElementById('dasStarttime').onclick = function() {
			dasStarttime.elem = this;
			laydate(dasStarttime);
		}
		var dasEndtime = {
				istime : true,
				format : 'YYYY-MM-DD',
				choose : function(datas) {
				}
			};

			document.getElementById('dasEndtime').onclick = function() {
				dasEndtime.elem = this;
				laydate(dasEndtime);
			}
		
});


function btnsumbit() {
		var pretkComments=$("#pretkComments").val();
		var preyfComments=$("#preyfComments").val();
		var predComments=$("#predComments").val();
		//基础阶段
		var baslComments=$("#baslComments").val();
		var bassComments=$("#bassComments").val();
		var basrComments=$("#basrComments").val();
		var baswComments=$("#baswComments").val();
		//强化
		var strlComments=$("#strlComments").val();
		var strsComments=$("#strsComments").val();
		var strrComments=$("#strrComments").val();
		var strwComments=$("#strwComments").val();
		//冲刺
		var daslComments=$("#daslComments").val();
		var dassComments=$("#dassComments").val();
		var dasrComments=$("#dasrComments").val();
		var daswComments=$("#daswComments").val();
		if(pretkComments.length>500 ||preyfComments.length>500||predComments.length>500){
			layer.alert("评语长度不能超过500",{icon:2});
			return false;
		}
		if(baslComments.length>500 ||bassComments.length>500||basrComments.length>500||baswComments.length>500){
			layer.alert("评语长度不能超过500",{icon:2});
			return false;
		}
		if(strlComments.length>500 ||strsComments.length>500||strrComments.length>500||strwComments.length>500){
			layer.alert("评语长度不能超过500",{icon:2});
			return false;
		}
		if(daslComments.length>500 ||dassComments.length>500||dasrComments.length>500||daswComments.length>500){
			layer.alert("评语长度不能超过500",{icon:2});
			return false;
		}
		var index =layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/comments/List/toedit.html",$("#addForm").serializeArray(),function (data) {
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert(data.message,{icon:1},function(){
					parent.location.reload(); 
					closeFrame();
				});
			}
		}, "json");
	return false;
}