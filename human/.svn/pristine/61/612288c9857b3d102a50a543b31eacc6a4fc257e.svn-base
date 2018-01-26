define([],function(){
	var  base={};
	//定义全局请求路径
	base.URL="http://9911.com.cn";
	//页面跳转
	base.openPage=function(name){
		window.location.href=name;
	}
	//获取数据 截取url地址中的参数  输入参数名就可以得到参数的值
	base.hasData=function(name){
      var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
      var r = window.location.search.substr(1).match(reg);
      if(r!=null)return  unescape(r[2]); return null;
	};
	return  base;
})