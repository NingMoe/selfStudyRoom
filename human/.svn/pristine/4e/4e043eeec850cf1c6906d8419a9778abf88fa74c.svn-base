var index 
    layui.use('upload', function(){
    	  layui.upload({
    	    url:  jsBasePath + '/basic/employee/ImportTeach.html',
    	    isAuto: true,
    	    before: function(input){
    	    	index = layer.load(3, {shade: [0.3]});
    	      },success: function(res){ //上传成功后的回调
    	    	  layer.close(index); 
    	      if(res.flag){
    	    	  //导入成功
    	    	  layer.alert(res.msg,{icon:1},function(index1){
						closeFrame();
					});
    	      }else{
    	    	  //导入失败
    	    	  layer.alert(res.msg,{icon:2},function(index1){
    	    		  layer.close(index1); 
    	    		   $("#impFile").val("");
					});
    	      }
    	    }
    	  });
    });