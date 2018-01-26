layui.use(['form','laydate'], function(){
    var form = layui.form();  
    var laydate = layui.laydate;
    var start = {
    		istoday : true,
    		format: 'YYYY-MM-DD hh:mm:ss', //日期格式
    		istime: true, //是否开启时间选择
    		choose : function(datas) {
    			
    		}
    	};
    $('#signTime').bind("click", function() {
		start.elem = this;
		laydate(start);
	});
    
    //监听是否签到类型
    form.on('select(isSign)', function(data){
  		if(data.value=="1"){
  			$("#signTimeDiv").show();
  		}else if(data.value=="2"){
  			$('#signTime').val("");
  			$("#signTimeDiv").hide();
  		}  		
  	});
    //监听提交
    form.on('submit(demo1)', function(data){
      var isSign=$("#isSign").val();
      if(isSign=="1"){
    	  var signTime=$("#signTime").val();
    	  if(signTime==""){
    		  layer.alert("签到时间必填！",{icon:2});
    		  return false;
    	  }
      }
  	  var index =layer.load(3, {shade: [0.3]});
  	  $.post(jsBasePath+"/sign/activity/editInfo.html",$("#editForm").serializeArray(),function(data,status){
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