layui.use(['form','laydate'], function(){
    var form = layui.form();    
    var laydate = layui.laydate;
    var start = {
    		istoday : true,
    		format: 'YYYY-MM-DD hh:mm:ss', //日期格式
    		istime: true, //是否开启时间选择
    		choose : function(datas) {
    			end.min = datas; //开始日选好后，重置结束日的最小日期
    			end.start = datas //将结束日的初始值设定为开始日
    		}
    	};
    var end = {
    		istoday : true,
    		format: 'YYYY-MM-DD hh:mm:ss', //日期格式
    		istime: true, //是否开启时间选择
    		choose : function(datas) {
    			start.max = datas; //结束日选好后，重置开始日的最大日期
    		}
    	};
    
    $('#startTime').bind("click", function() {
    		start.elem = this;
    		laydate(start);
    	});
    
    $("#endTime").bind("click", function() {
    		end.elem = this
    		laydate(end);
    	});
    
    //监听提交
    form.on('submit(demo1)', function(data){
  	  var index =layer.load(3, {shade: [0.3]});
  	  var flag=checkData();
  	  if(!flag){
  		 layer.close(index); 
  	  }else{
  		var jstr=JSON.stringify(getData()).replace(/\"/g,"\'");  		
  		$.ajax({
			url : jsBasePath+"/basic/activity/add.html",
			data : {jsonStrings:jstr},
			async:false,
			dataType : "json",
			type : "post",
			success:function(res){
				layer.close(index);
				if(!res.flag){
					layer.alert(res.message,{icon:2});
				}else{
					layer.alert(res.message,{icon:1},function(){						
						parent.location.reload();	
						closeFrame();
					});
				}
			}
		});  		
  	  }  	   	 
  	  return false;
    });
});

//新增商品
function addRecord(obj,event){
	var divId=$(obj).parent().parent().parent().attr("id");
	$("#"+divId).append($("#copy_"+divId).html());
	event.stopPropagation();  
}
//删除商品
function removeRecord(t){
	$(t).parent().parent().remove();
}
//校验商品信息
function checkData(){
	var flag=false;
	$("div[id='productRecord']").children('div').each(function(i,n){
        var name=$(n).children('div').eq(0).find("input").val();
        var price=$(n).children('div').eq(1).find("input").val();	         
        var total=$(n).children('div').eq(2).find("input").val();       
        if($.trim(name) ==""){
        	layer.alert("商品名称必填!",{icon:2});
	    	flag=false;
	    	return false;
	     }
		 if($.trim(price)== ""){
			layer.alert("商品单价必填!",{icon:2});
	    	flag=false;
	    	return false;
	     }		 
		 if(parseInt(price)<1){
			 layer.alert("商品单价必须为正数!",{icon:2});
		     flag=false;
		     return false;
		 }
		 if($.trim(total)== ""){
			layer.alert("商品数目必填!",{icon:2});
	    	flag=false;
	    	return false;
	     }
		 if(parseInt(total)<0){
			 layer.alert("商品数目不能为负数!",{icon:2});
		     flag=false;
		     return false;
		 }
		flag=true;
	  });
	return flag;
}
//表单json化
function getData(){
	return $('#addForm').serializeJson();
}

