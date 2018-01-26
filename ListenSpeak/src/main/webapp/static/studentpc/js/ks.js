/*勿删，用于rem样式*/
var  deviceWidth = document.documentElement.clientWidth;
if(deviceWidth >750) deviceWidth =750;
document.documentElement.style.fontSize = deviceWidth /7.5+'px';
/*用于圆环,实现百分比可以直接改下边percent*/
    var percent=25 
    var loading=setInterval(function(){ 
        if(percent>100){ 
            percent=0; 
            $('.circle').removeClass('clip-auto'); 
            $('.right').addClass('wth0'); 
        }else if(percent>50){ 
            $('.circle').addClass('clip-auto'); 
            $('.right').removeClass('wth0'); 
        } 
        $('.left').css("-webkit-transform","rotate("+(18/5)*percent+"deg)"); 
        $('.num>span').text(percent);  
    });
/*左侧跳转*/
function tiaozhuan(a){
	var b='';
	if (a==1) {
		b='08myclass.html';
		$(".right-main").attr('src',b);
	}else if (a==2) {
		b='13myhomework.html';
		$(".right-main").attr('src',b);
	}else if (a==3) {
		b='18mykaoshi.html';
		$(".right-main").attr('src',b);
	};
}
/*左侧一级图标展开*/
function kuozhan(a){
	$(a).parent().find('.public-lmain2').toggle()
}