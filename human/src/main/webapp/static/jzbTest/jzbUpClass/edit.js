layui.use(['form','laydate'], function(){
    var form = layui.form();    
    var laydate = layui.laydate;
    var start = {
    		istoday : true,
    		format: 'YYYY-MM-DD', //日期格式
    		istime: true, //是否开启时间选择
    		choose : function(datas) {
    			end.min = datas; //开始日选好后，重置结束日的最小日期
    			end.start = datas //将结束日的初始值设定为开始日
    		}
    	};
    var end = {
    		istoday : true,
    		format: 'YYYY-MM-DD', //日期格式
    		istime: true, //是否开启时间选择
    		choose : function(datas) {
    			start.max = datas; //结束日选好后，重置开始日的最大日期
    		}
    	};
    $('#startDateTime').bind("click", function() {
    		start.elem = this;
    		laydate(start);
    	});
    $("#endDateTime").bind("click", function() {
    		end.elem = this
    		laydate(end);
    	});
    
    //监听提交
    form.on('submit(demo1)', function(data){
  	  var index =layer.load(3, {shade: [0.3]});
  	  $.post(jsBasePath+"/basic/jzbConfigClass/edit.html",$("#addForm").serializeArray(),function(data,status){
  			layer.close(index); 
  			if(data.flag==false){
  				layer.alert(data.message,{icon:2});
  			}else{
  				layer.alert(data.message,{icon:1},function(){
  					parent.location.reload(); 
  					closeFrame();
  				});
  			}
  		},"json");
  		return false;
    });
});