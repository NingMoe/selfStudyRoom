/*window.onload=function(){
	var gaodu=document.body.scrollHeight*0.7
	$('.list-main').css('max-height', gaodu);
	$('.list-main').css('min-height', gaodu);
}*/
function changeSes(a,b){
	if(b==1){
		$('.main-content').fadeIn();
		$('.main-nextclass').fadeOut();
	}else if (b==2) {
		$('.main-content').fadeOut();
		$('.main-nextclass').fadeIn();
	}
	$(a).addClass('main-act');
	$(a).siblings().removeClass('main-act');
}
//红色对勾选择
function fuzhi(bianliang){
	var b= xuanze.testClick(bianliang.src);
	bianliang.src=b;
	}

//原班，选中班级，续班获取及处理
var sumyuan=[];//此处接受原选中班级
var sumxv=[]//此处接受续班选中班级
var xuanze={
//切换选中按钮
	testClick:function(val){
		var a=[];
		a=val.split('_0');
		if(a[1]=='3.jpg'){
			return a[0]+'_06.jpg'
		}else if(a[1]=='6.jpg'){
			return a[0]+'_03.jpg'
		}
		},
//首页点击班班关系获取已选中原班
	bbguanxi:function(){
		 $('.yblist tr td').each(function(i,t){
               var  dizhi=$(t).html()
				if(dizhi.indexOf('images/1_03.jpg')>0){
               	sumyuan.push( $(t).find("input").val());
                }
		 });
	},
//首页点击提交获取续班选中班级
	bbsubmit:function(){
		$('.xblist tr').each(function(index, val) {
			 var dizhi=$(val).html();
			 if(dizhi.indexOf('images/1_03.jpg')>0){
			 	sumxv.push( $(val).find("input").val());
			 }
		});
	},
//确认页面添加新人班关系
	xbguanxi:function(a){
		var xvbanArr=[];
		var studentCode=$(a).parent('div').parent('td').parent('tr').find('.main-display').text();
  		 $(a).parent('div').parent('td').children('div').each(function(i,n){         
         var xvban=$(n).find('input:eq(0)').val()
         if (xvban!=undefined) {
         	xvbanArr.push(xvban)
         }
   });
	}
}
function chooseban(a){
	if(a==1){//点击首页班班按钮
		$('.banban').show();
		xuanze.bbguanxi()
	}else if(a==2){//选好班班中，续班关系提交按钮
		$('.liebiao-queren').show();
		xuanze.bbsubmit()
	}else if(a==3){//选好班班时点击取消选择按钮
		$('.banban').hide();
	}else if(a==4){//续班关系提交后，继续添加按钮
		$('.liebiao-queren').hide();
		$('.banban').hide();
	}else if(a==5){//续班关系提交后，查看结果跳转
		window.location.href='/wechat/binding/coutinuationuser/listview.html'
	}
}
//点击删除
$(document).ready(function(){
	$(".list-one img").click(function() {
		$(this).parent('.list-one').remove()
});
	});
//
function chakan(){
	$('.chakan').show()
}
function addclass(a){
	xuanze.xbguanxi(a)
}
//                   _ooOoo_
//                  o8888888o
//                  88" . "88
//                  (| -_- |)
//                  O\  =  /O
//               ____/`---'\____
//             .'  \\|     |//  `.
//            /  \\|||  :  |||//  \
//           /  _||||| -:- |||||-  \
//           |   | \\\  -  /// |   |
//           | \_|  ''\---/''  |   |
//           \  .-\__  `-`  ___/-. /
//         ___`. .'  /--.--\  `. . __
//      ."" '<  `.___\_<|>_/___.'  >'"".
//     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//     \  \ `-.   \_ __\ /__ _/   .-` /  /
//======`-.____`-.___\_____/___.-`____.-'======
//                   `=---='
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//         佛祖保佑       永无BUG
//  本模块已经经过开光处理，绝无可能再产生bug
//=============================================