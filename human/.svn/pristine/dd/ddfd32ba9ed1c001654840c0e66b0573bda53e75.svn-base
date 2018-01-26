/*
* @Author: Larry
* @Date:   2016-12-15 17:20:54
* @Last Modified by:   qinsh
* @Last Modified time: 2016-12-24 22:06:18
* +----------------------------------------------------------------------
* | LarryBlogCMS [ LarryCMS网站内容管理系统 ]
* | Copyright (c) 2016-2017 http://www.larrycms.com All rights reserved.
* | Licensed ( http://www.larrycms.com/licenses/ )
* | Author: qinshouwei <313492783@qq.com>
* +----------------------------------------------------------------------
*/
'use strict';
layui.use(['jquery','layer','element'],function(){
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
  var element = layui.element();
  
// larry-side-menu向左折叠
$('.larry-side-menu').click(function() {
  var sideWidth = $('#larry-side').width();
  if(sideWidth === 200) {
      $('#larry-body').animate({
        left: '0'
      }); //admin-footer
      $('#larry-footer').animate({
        left: '0'
      });
      $('#larry-side').animate({
        width: '0'
      });
  } else {
      $('#larry-body').animate({
        left: '200px'
      });
      $('#larry-footer').animate({
        left: '200px'
      });
      $('#larry-side').animate({
        width: '200px'
      });
  }
});
});

//进入修改密码页
function modifyPassword() {
	var url = jsBasePath + "/manager/toModifyPwd.html";
	layer.open({
		type : 2,
		shade : [ 0.5, '#000' ],
		offset : [ '10%' ],
		area : [ '520px', '300px' ],
		//scrollbar: false,
		title : "编辑角色", //不显示标题
		content : url, //捕获的元素
		cancel : function(index) {
			layer.close(index);
		},
		end : function() {
			//query();
		}
	});
	return;
}

$(function(){
	   /*//获取左侧菜单
		$.post(jsBasePath+"/manager/getMenu.html",{},function(data,stuts){
			var html="";
			$.each(data,function(index,firstm){
				html=html+"<li class=\"layui-nav-item\">";
				if(firstm.href==null){
					html=html+"<a href=\"javascript:;\">";
				}else{
					html=html+"<a href=\"javascript:;\"  data-url=\"personInfo.html\">";
				}
				if(firstm.icon!=null){
					html=html+"<i class=\"iconfont "+firstm.icon+"\" ></i>";
				}
				html=html+"<span>"+firstm.title+"</span>";
				if(firstm.menu.length>0){
					html=html+"<em class=\"layui-nav-more\"></em></a>";
					html=html+"<dl class=\"layui-nav-child\">";
					$.each(firstm.menu,function(i,sectm){
						html=html+ "<dd>";
						html=html+" <a href=\"javascript:;\" data-url=\"personInfo.html\">";
						if(sectm.icon!=null){
							html=html+" <i class=\"iconfont "+sectm.icon+"\" data-icon='"+sectm.icon+"'></i>"
						}
						html=html+"<span>"+sectm.text+"</span> </a>"
		                html=html+ "</dd>"; 
					});
					html=html+"</dl>";
				}else{
					html=html+"</a>";
				}
				html=html+"</li>";
				alert(html);
				$(".layui-nav-tree").append(html);
			});
		},"json");*/
	    
	});