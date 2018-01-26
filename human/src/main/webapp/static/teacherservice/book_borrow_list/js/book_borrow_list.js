layui.use(['form', 'layedit', 'laydate'], function(){
	      var form = layui.form()
	      ,layer = layui.layer
	      ,layedit = layui.layedit
	      ,laydate = layui.laydate;
	      
	      var start = {
	    	      istoday: false
	    	    , istime: true
	    	    , format: 'YYYY-MM-DD hh:mm:ss'
	    	    ,choose: function(datas){
	    	      end.min = datas; //开始日选好后，重置结束日的最小日期
	    	      end.start = datas //将结束日的初始值设定为开始日
	    	    }
	    	  };
	      var end = {
	    	      istoday: false
	    	    , istime: true
	    	    , format: 'YYYY-MM-DD hh:mm:ss'
	    	    ,choose: function(datas){
	    	      start.max = datas; //结束日选好后，重置开始日的最大日期
	    	    }
	    	  };
			  document.getElementById('left_borrow_time').onclick = function(){
			       start.elem = this;
			       laydate(start);
			  }
			  document.getElementById('right_borrow_time').onclick = function(){
			       end.elem = this
			       laydate(end);
			  }		  
	    });

layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form()
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate;
    
    var start = {
  	      istoday: false
  	    , istime: true
  	    , format: 'YYYY-MM-DD hh:mm:ss'
  	    ,choose: function(datas){
  	      end.min = datas; //开始日选好后，重置结束日的最小日期
  	      end.start = datas //将结束日的初始值设定为开始日
  	    }
  	  };
    var end = {
  	      istoday: false
  	    , istime: true
  	    , format: 'YYYY-MM-DD hh:mm:ss'
  	    ,choose: function(datas){
  	      start.max = datas; //结束日选好后，重置开始日的最大日期
  	    }
  	  };
		  document.getElementById('left_return_time').onclick = function(){
		       start.elem = this;
		       laydate(start);
		  }
		  document.getElementById('right_return_time').onclick = function(){
		       end.elem = this
		       laydate(end);
		  }
  });