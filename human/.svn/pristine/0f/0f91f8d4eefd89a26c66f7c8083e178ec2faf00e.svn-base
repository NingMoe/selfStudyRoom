			var rankinfo_id = getQueryString("rankinfo_id");
	    	layui.use(['form', 'layedit','upload'], function(){
	    		var form = layui.form()
	    		  ,layer = layui.layer
	    		  ,layedit = layui.layedit;
	    	  //监听提交
	    	  var s = layui.upload({
	    			url: jsBasePath+'/teacher/rankclasses/upload.html',
	    			isAuto : true,
	    			data :{
	    				"rankinfo_id" : rankinfo_id
	    			},
	    			file : $("#file")[0],
	    			change : function(file){
	    				var id = $(file).attr("id");
	    			},	    			
	    			success: function(res){ //上传成功后的回调
	    				if(!res.flag){
	    					layer.alert(res.message,{icon:2});
	    				}else{
	    					layer.alert(res.message,{icon:1},function(){
	    						closeFrame();
	    					});
	    				}
	    			}
	    		});
	    	  
	    	  $("#rank_classes_btn").bind("click",function(){
		  		  var files = [];
		  		  files.push($("#file")[0]);
		  		  var data = {
		  			"rankinfo_id" : rankinfo_id
		  		  };
		  		  s.action(files,"file",data);
		  	  });
	    	  
	    	});
	    	
	    	function downloadexcel(){
	    		downTemp("排行榜班级模板.xlsx","downloadrankclassesfile.xlsx");
	    	}
	    	
	    	//JavaScript Document
	    	function getQueryString(key) {
	    		var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	    		var result = window.location.search.substr(1).match(reg);
	    		return result ? decodeURIComponent(result[2]) : null;
	    	}