layui.use(['form'], function(){
    var form = layui.form(); 
    //监听提交
    form.on('submit(demo1)', function(data){
  	   var index =layer.load(3, {shade: [0.3]});
  	   var schoolSbqId=$("#schoolSbqId").val();
       var schoolSbqs=[];
       schoolSbqs=schoolSbqId.split("|");
	   var swindowperiodid=schoolSbqs[0];
	   var sstageid=schoolSbqs[1];
	   var checkText=$("#schoolSbqId").find("option:selected").text(); 
	   schoolSbqs=checkText.split("-");	  
	   var swindowperiodname=schoolSbqs[0];
	   var sstagename=schoolSbqs[1];
	   var nschoolid=$("#nschoolid").val();
	   var gradeSubjectId=$("#gradeSubjectId").val();
  	   $.post(jsBasePath+"/basic/jzbConfigUpClass/add.html",{
						  		  gradeSubjectId:gradeSubjectId,
						  		  nschoolid:nschoolid,
						  		  swindowperiodid:swindowperiodid,
						  		  sstageid:sstageid,
						  		  swindowperiodname:swindowperiodname,
						  		  sstagename:sstagename
			            },function(data,status){
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
