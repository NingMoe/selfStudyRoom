var timeout;
var zdSj;

$(function(){
	btn_click();
	bingzhuangtu(transcendCount * 100);
})

//按钮初始化
function btn_click(){
	
}

//饼状图
function bingzhuangtu(percent){
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
}

//返回
function goback(){
	window.location.href  = jsBasePath + "/studentpc/studentzuoye/studentzuoyeview.html";
}

function bofang(thisd){
	var imgsrc = $(thisd).attr("src");
	clearTimeout(timeout);
	if(imgsrc.indexOf("17_03.png") != -1){
		$("audio").each(function(index){
			var audio = $(this)[0];
			audio.pause();
			audio.load();
			$(this).siblings("img").attr("src",jsBasePath + "/static/studentpc/images/17_03.png");
		});
		var audio = $(thisd).next()[0];
		audio.play(); 
		$(thisd).attr("src",jsBasePath + "/static/studentpc/images/17_03_01.png");
		audio.addEventListener("canplay", function(){
			zdSj=parseInt(audio.duration);
		});
		zantingtime(audio);
	}else{
		var audio = $(thisd).next()[0];
		audio.pause(); 
		audio.load();
		$(thisd).attr("src",jsBasePath + "/static/studentpc/images/17_03.png");
	}
}

function zanting(){
	$("audio").each(function(index){
		$(this).siblings("img").attr("src",jsBasePath + "/static/studentpc/images/17_03.png");
	});
}

function zantingtime(audio){
	if(zdSj >= 0){
		timeout = setTimeout(zanting, zdSj * 1000 + 600);
	}else{
		setTimeout(zantingtime, 50);
	}
}