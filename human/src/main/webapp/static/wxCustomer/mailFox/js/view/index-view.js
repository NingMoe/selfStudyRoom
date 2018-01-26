 define(["../template/index-tpl","../../lib/js/template"],function(tpl,template) {
   var view={};
	view.tempone=function(data,appendId,tempId){
	   var  t=tpl.init1(appendId);//调用模板模块中的函数往页面中动态添加script标签
	   var  html=template(tempId,data);//调用template函数生成模板
	   $("#"+appendId).append(html);//指定位置添加模板
	}
  return view;
 });