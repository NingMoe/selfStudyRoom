layui.use(['form'], function(){
	var form = layui.form();
	form.on('submit(rf)', function(){
		var index = layer.load(3, {shade: [0.3]});
		var refuseReason = $("#refuseReason").val();
		var actId = $("#actid").val();
		$.ajax({
			data:{"refuseReason":refuseReason,"actId":actId},
			url:jsBasePath+"/free/entry/refuseOffer.html",
			type:"post",
			dataType:"json",
			success:function(data){
				layer.close(index); 
				closeWindow();
			}
		});
		return false;
	 });
});

function closeWindow() {
	var userAgent = navigator.userAgent;
	if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") !=-1) {
	   window.location.href="about:blank";
	} else {
	   window.opener = null;
	   window.open("", "_self");
	   window.close();
	}
}