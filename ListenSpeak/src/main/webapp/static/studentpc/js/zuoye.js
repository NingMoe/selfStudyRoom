var nextquestion = "-1";
var zdSj = -1;
var zdSj1 = -1;
var qtype = 0;
var topic_time = -1;
var is_end = -1;
var is_zidong = -1;
var scoreType;
var refText;
var key;
var content;
var topic;
var question_id;
var th;

var loadindex;//加载层

$(function(){
	getZuoyeQuestion();
	btn_click();
})

//按钮初始化
function btn_click(){
	
}

//作业
function getZuoyeQuestion(){
	var zuoye_id = getQueryString("zuoye_id");
	var class_code = getQueryString("class_code");
	
	$.ajax({
		url : jsBasePath+"/studentpc/studentzuoye/getZuoyeQuestion.html",
		dataType : "json",
		type : "get",
		data : {
			class_code : class_code,
			zuoye_id : zuoye_id,
		},
		success:function(data){
			if(data.flag){
				th = data.th;
				var qlist = data.list;
				var zth = 0;
				if(qlist != null && qlist.length > 0){
					for(var i = 0; i < qlist.length; i++){
						if(qlist[i].answer_list != null && qlist[i].answer_list.length > 0){
							for(var j = 0; j < qlist[i].answer_list.length; j++){
								zth++;
								if(zth == th){
									scoreType = qlist[i].sogou_score_type;
									if(scoreType == "eng_wrd" || scoreType == "eng_snt" || scoreType == "eng_chp"){
										refText = qlist[i].answer_list[j].content;
									}else if(scoreType == "eng_pqan" ){
										refText = qlist[i].answer_list[j].parse_text;
										key = qlist[i].answer_list[j].content_keys;
										content = qlist[i].answer_list[j].content;
										topic = qlist[i].topic;
									}else if(scoreType == "eng_topic"){
										refText = qlist[i].answer_list[j].parse_text;
									}
									question_id = qlist[i].answer_list[j].id;
									
									if(qlist[i].answer_list.length == 1){
										$("#thisth").html("第"+(i + 1)+"大题"+qlist[i].tname);
									}else if(qlist[i].answer_list.length > 1){
										$("#thisth").html("第"+(i + 1)+"大题-第"+(j + 1)+"小题"+qlist[i].tname);
									}
									
									if(scoreType == "eng_chp" || scoreType == "eng_pqan" || scoreType == "eng_topic"){
										is_zidong = 1;
									}
									
									$("#zdmessage").html(qlist[i].zdmessage);
									$("#topic").html(qlist[i].topic);
									$("#content").html(qlist[i].answer_list[j].content);
									$("#zd_audio").attr("src",osspath + qlist[i].zd_audio);
									if(qlist[i].topic_time != null){
										topic_time = qlist[i].topic_time;
									}
									if(qlist[i].content_audio != null){
										$("#content_audio").attr("src",osspath + qlist[i].content_audio);
										var audio1 = $("#content_audio")[0];
										audio1.addEventListener("canplay", function(){
											zdSj1=parseInt(audio1.duration);
										});
										qtype = 1;
									}
									if(j == 0 && qlist[i].zd_audio != null){
										var audio = $("#zd_audio")[0];
										audio.addEventListener("canplay", function(){
											zdSj=parseInt(audio.duration);
										});
										getSesound();
									}else if(j != 0 && qlist[i].zd_audio != null){
										getSesoundtwo();
									}else{
										canRecording();
									}
									if(i == (qlist.length - 1) && j == (qlist[i].answer_list.length - 1)){
										is_end = 1;
										nextquestion = "1";
										$("#nextquestion").html("提交");
									}else{
										is_end =0;
										nextquestion = "0";
										$("#nextquestion").html("下一题");
									}
								}
							}
						}
					}
				}else{
					alert("题目结束");
				}
			}else{
				layer.alert(data.message,{icon:2});
			}
		}
	});
	
}

function getSesound(){
	if(zdSj >= 0){
		var sj = zdSj;
		var audio = $("#zd_audio")[0];
		audio.play();
		activitycircleh(sj,"recordbtn");
		if(qtype == 1){
			setTimeout(getSesoundtwo, (sj + 2) * 1000);
		}else{
			setTimeout(canRecording, (sj + 2) * 1000);
		}
	}else{
		setTimeout(getSesound, 50);
	}
}

function getSesoundtwo(){
	if(zdSj1 >= 0){
		var audio = $("#content_audio")[0];
		audio.play();
		activitycircleh(zdSj1,"recordbtn");
		if(qtype == 1){
			setTimeout(getSesoundthree, (zdSj1 + 3) * 1000);
		}
	}else{
		setTimeout(getSesoundtwo, 50);
	}
}

function getSesoundthree(){
	if(zdSj1 >= 0){
		var audio = $("#content_audio")[0];
		audio.play();
		activitycircleh(zdSj1,"recordbtn");
		setTimeout(getSesoundfour, (zdSj1 + 3) * 1000);
	}else{
		setTimeout(getSesoundthree, 50);
	}
}

function getSesoundfour(){
	if(topic_time >= 0){
		cannotRecording();
		activitycircleh(topic_time,"recordbtn");
		setTimeout(canRecording, (topic_time+2) * 1000);
	}else{
		setTimeout(getSesoundfour, 50);
	}
}

function canRecording(){
	$("#recordimg").attr("src", jsBasePath + "/static/studentpc/images/15X_04_03.png");
	$("#recordbtn").val("点击上方录音按钮，开始答题。");
}

function cannotRecording(){
	$("#recordimg").attr("src", jsBasePath + "/static/studentpc/images/14X_03.png");
	$("#recordbtn").val("学生阅读中，不录音......");
}

function isRecording(){
	$("#recordimg").attr("src", jsBasePath + "/static/studentpc/images/15X_03.png");
	$("#recordbtn").val("正在录音中......结束录音请再次点击上方按钮");
}

function recording(){
	var imgsrc = $("#recordimg").attr("src");
	if(imgsrc.indexOf("15X_04_03") != -1){
		startRecording();
		isRecording();
	}else if(imgsrc.indexOf("15X_03") != -1){
		stopRecording();
		canRecording();
	}else{
		layer.msg("请先阅读题目。");
	}
}

//下一题
function tonextquestion(){
	if(is_last == -1){
		layer.msg("请先答题");
		return false;
	}
	
	loadindex = layer.load(2);
	if(is_last == 0 || parseResult == null){
		setTimeout(function(){
			tonextquestion();
		},50);
	}
	
	var zuoye_id = getQueryString("zuoye_id");
	var class_code = getQueryString("class_code");
	if(zuoye_id == null || zuoye_id == ''){
		layer.msg("作业ID为空");
		return false;
	}
	
	if(class_code == null || class_code == ''){
		layer.msg("班号为空");
		return false;
	}
	
	if(nextquestion == "-1"){
		alert("稍等");
		return false;
	}
	
	var data = new FormData();
	
	data.append("studentAnswer",audioBlob);
	data.append("zuoyeId",zuoye_id);
	data.append("classCode",class_code);
	data.append("questionId",question_id);
	data.append("isEnd",is_end);
    data.append("accuracy",(parseResult.accuracy/10).toFixed(2));
    data.append("fluency",(parseResult.fluency/10).toFixed(2));
    data.append("integrity",(parseResult.integrity/10).toFixed(2));
    data.append("overall",(parseResult.overall/10).toFixed(2));	
	
	$.ajax({
		url: jsBasePath+"/studentpc/voice/tjZuoyeParseResult.html",
		type: 'POST',  
		data: data,  
		dataType: 'JSON',  
		cache: false,  
		processData: false,  
		contentType: false  
	}).done(function(res){  
			if(res.flag){  
				//提交下一題
				if(nextquestion == "0"){
					window.location.href = jsBasePath + "/studentpc/studentzuoye/zuoyeview.html?zuoye_id=" + zuoye_id + "&class_code=" + class_code;
				}else if(nextquestion == "1"){
					window.location.href = jsBasePath + "/studentpc/studentzuoye/studentzuoyeview.html";
				}
			}else{  
				layer.close(loadindex);
				layer.alert(res.message,{icon:2});
			}  
  	}); 
}

//返回
function goback(){
	window.location.href = jsBasePath + "/studentpc/studentzuoye/studentzuoyeview.html";
}

//录音倒计时改变
function activitycircleh(second,id){
	var percent = 0;
	var shuaxin = 50;
	var text = $("#"+id).val();
	$('.circleh').removeClass('clip-autoh');
	$('.righth').addClass('wth0h');
	var loading = setInterval(function() {
		$('.lefth').css("-webkit-transform", "rotate(" + (parseFloat(18 / second)) * percent + "deg)");
		if (percent >= ((1000 / shuaxin) * second)) {
			clearInterval(loading);
		}
		if (percent > ((500 / shuaxin) * second)) {
			$('.circleh').addClass('clip-autoh');
			$('.righth').removeClass('wth0h');
		}
//		$('.numh>span').text(percent);
		if(percent % 20 == 0){
			$("#"+id).val(text+"("+(second - (percent / 20))+"s)");
		}
		if(percent >= ((1000 / shuaxin) * second)){
			$("#"+id).val(text);
		}
		percent++;
	}, shuaxin);
}

//JavaScript Document
function getQueryString(key){
  var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
  var result = window.location.search.substr(1).match(reg);
  return result?decodeURIComponent(result[2]):null;
}

function startRecording() {
	currentTime = new Date().getTime();
	recorder && recorder.record();
	getBufferLoading = setInterval(function(){ 
		recorder.getBuffer(function(blob){
			if(blob[0].length-bufferdSendSize>47103){
				var blob = blob[0].slice(bufferdSendSize,bufferdSendSize+47102);
				bufferdSendSize += 47103; 
				is_last = 0;
				sogouParseData(bufferdSendSize,getWavData(blob));
			}
		});
	},50);
}

function stopRecording() {
	currentIndex++;
	recorder && recorder.stop();
	clearInterval(getBufferLoading);
	recorder.getBuffer(function(blob){
		var alllength = blob[0].length;
		if(blob[0].length>bufferdSendSize){
			var blob = blob[0].slice(bufferdSendSize);
			setTimeout(function(){
				is_last = 1;
				sogouParseData(-1*alllength,getWavData(blob), "1");
			},500);
		}
	});
	createDownloadLink();
}

function getWavData(blob){
	  var samples = compress(blob);
  var dataview = encodeWAV(samples);
  return new Blob([dataview], { type:'audio/wav' });
}

function compress(samples){
	var sampleRate = $("#sampleRate").val();
	var compression = parseInt(sampleRate / 16000);
    var length = samples.length / compression;
    var result = new Float32Array(length);
    var index = 0, j = 0;
    while (index < length) {
        result[index] = samples[j];
        j += compression;
        index++;
    }
    return result;
}


function floatTo16BitPCM(output, offset, input) {
    for (var i = 0; i < input.length; i++, offset += 2) {
        var s = Math.max(-1, Math.min(1, input[i]));
        output.setInt16(offset, s < 0 ? s * 0x8000 : s * 0x7FFF, true);
    }
}

function writeString(view, offset, string) {
    for (var i = 0; i < string.length; i++) {
        view.setUint8(offset + i, string.charCodeAt(i));
    }
}

function encodeWAV(samples) {
	var dataLength = samples.length * 2;
    var buffer = new ArrayBuffer(44 + dataLength);
    var view = new DataView(buffer);

    var sampleRateTmp = 16000;
    var sampleBits = 16;
    var channelCount = 1;
    var offset = 0;
    /* 资源交换文件标识符 */
    writeString(view, offset, 'RIFF'); offset += 4;
    /* 下个地址开始到文件尾总字节数,即文件大小-8 */
    view.setUint32(offset, /*32这里地方栗子中的值错了,但是不知道为什么依然可以运行成功*/ 36 + dataLength, true); offset += 4;
    /* WAV文件标志 */
    writeString(view, offset, 'WAVE'); offset += 4;
    /* 波形格式标志 */
    writeString(view, offset, 'fmt '); offset += 4;
    /* 过滤字节,一般为 0x10 = 16 */
    view.setUint32(offset, 16, true); offset += 4;
    /* 格式类别 (PCM形式采样数据) */
    view.setUint16(offset, 1, true); offset += 2;
    /* 通道数 */
    view.setUint16(offset, channelCount, true); offset += 2;
    /* 采样率,每秒样本数,表示每个通道的播放速度 */
    view.setUint32(offset, sampleRateTmp, true); offset += 4;
    /* 波形数据传输率 (每秒平均字节数) 通道数×每秒数据位数×每样本数据位/8 */
    view.setUint32(offset, sampleRateTmp * channelCount * (sampleBits / 8), true); offset += 4;
    /* 快数据调整数 采样一次占用字节数 通道数×每样本的数据位数/8 */
    view.setUint16(offset, channelCount * (sampleBits / 8), true); offset += 2;
    /* 每样本数据位数 */
    view.setUint16(offset, sampleBits, true); offset += 2;
    /* 数据标识符 */
    writeString(view, offset, 'data'); offset += 4;
    /* 采样数据总数,即数据总大小-44 */
    view.setUint32(offset, dataLength, true); offset += 4;
    /* 采样数据 */
    floatTo16BitPCM(view, 44, samples);

    return view;
}

function sogouParseData(index,blob, isLastthis){
	var data = new FormData();
	var zuoye_id = getQueryString("zuoye_id");
	var class_code = getQueryString("class_code");
	if(zuoye_id == null || zuoye_id == ''){
		layer.msg("作业ID为空");
		return false;
	}
	
	if(class_code == null || class_code == ''){
		layer.msg("班号为空");
		return false;
	}
	data.append("currentIndex",index);
	data.append("currentTime",currentTime);
	data.append("studentAnswer",blob);
	data.append("scoreType",scoreType);
	data.append("zuoye_id",zuoye_id);
	data.append("class_code",class_code);
	data.append("question_id",question_id);
	data.append("refText",refText);
	data.append("key",key);
	data.append("content",content);
	data.append("topic",topic);
	data.append("isEnd",is_end);
	$.ajax({
		url: jsBasePath+"/studentpc/voice/soGouSenceParse.html",
		type: 'POST',
		data: data,
		dataType: 'JSON',
		cache: false,  
		processData: false,  
		contentType: false
	}).done(function(res){  
		if(isLastthis=="1"){
			parseResult = res.data;
		}
	});  
	return false;
}



function createDownloadLink() {
    recorder && recorder.exportWAV(function(blob) {
    	audioBlob = blob;
    	recorder.clear();
    /*	 var url = URL.createObjectURL(blob);
    	var au = document.createElement('audio');
    	au.controls = true;
    	au.src = url;
    	$("#answerDiv").html("").append(au);*/
  	});
}