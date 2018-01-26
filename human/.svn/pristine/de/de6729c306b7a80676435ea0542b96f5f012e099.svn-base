layui.use(['element', 'layer','form', 'layedit'], function(){
	  var element = layui.element;
	  var layer = layui.layer;
	  var table = layui.table;
	  var form = layui.form,layedit = layui.layedit;
	  $(".content").first().addClass("layui-show")
});	
function search(){
	var qMonth=$("#qMonth").val();
	var qClasstype=$("#qClasstype").val();
	var qDept=$("#qDept").val();
	var qGrade=$("#qGrade").val();
	var qSubject=$("#qSubject").val();
	var qCode=$("#qCode").val();
	if(qCode==""){
		layer.alert("请输入题目编号",{icon:2});
		return false;
	}
	var url = jsBasePath+"/jzbTest/jpConfig/questionByCode.html?qMonth="+qMonth+"&qClasstype="+qClasstype
	+"&qDept="+qDept+"&qGrade="+qGrade+"&qSubject="+qSubject+"&qCode="+qCode;
	 layer.open({
	     type: 2,
	     shade : [ 0.5, '#000' ],
	     title: "查看题目", //
		 offset : [ '4%' ],
	     area: ['80%','80%'],	     
	     content:url, //捕获的元素
	     cancel: function(index){
	         layer.close(index);
	     },
	     end:function(){
	    	 
	     }
});
	  return false;
}

