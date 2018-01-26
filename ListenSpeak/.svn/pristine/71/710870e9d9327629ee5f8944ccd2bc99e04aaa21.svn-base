function bfZdy(){
	if(!!zdSj){
		zdAudio.play();  
		zd_status = 1;
		setGd(zdSj,tgOrTopicSx);
	}else{
		setTimeout(bfZdy,50);
	}
}
var vm;
function startRecording() {
	$("#father").css("display","block");
	var i=1;
    var el;
    var  flag=true;
    if($("#father div").length==6){
     vm=setInterval(function(){           
          console.log(i);
          if(flag){
            el="#son"+i;
            console.log(el);
            $(el).css("background-color","#f5f5f5");
            i++;
            if(i==5){
              flag=false;
              // clearInterval(vm);
            }                 
          }else{
            i--;
            el="#son"+i;
            $(el).css("background-color","#64dcdc");
            if(i==0){
              flag=true;
            }
          }       
    },300) ;
    }
	currentTime = new Date().getTime();
	recorder && recorder.record();
	getBufferLoading = setInterval(function(){ 
		recorder.getBuffer(function(blob){
			if(blob[0].length-bufferdSendSize>47103){
				var blob = blob[0].slice(bufferdSendSize,bufferdSendSize+47102);
				bufferdSendSize += 47103; 
				sogouParseData(bufferdSendSize,getWavData(blob));
			}
		});
	},50); 
}

function getWavData(blob){
	  var samples = compress(blob);
      var dataview = encodeWAV(samples);
      return new Blob([dataview], { type:'audio/wav' });
}

function stopRecording() {
	 clearInterval(vm);
	 $("#father").css("display","none");
	currentIndex++;
	recorder && recorder.stop();
	clearInterval(getBufferLoading);
	recorder.getBuffer(function(blob){
		var alllength = blob[0].length;
		if(blob[0].length>bufferdSendSize){
			var blob = blob[0].slice(bufferdSendSize);
			setTimeout(function(){
				sogouParseData(-1*alllength,getWavData(blob),"1");
			},500);
		}
	});
}

function sogouParseData(index,blob,isLast){
	var data = new FormData();
	var scoreType = $("#scoreType").val();
	var parseText = $("#parseText").val();
	var content = $("#content").val();
	
	data.append("currentIndex",index);
	data.append("studentAnswer",blob);
	data.append("currentTime",currentTime);
	data.append("scoreType",scoreType);
	if(scoreType=="eng_topic"){
		data.append("refText",parseText);
	}else{
		data.append("refText",content);
	}
	isLast=="1" && (tjindex = layer.load(3, {shade: [0.3]})); 
	$.ajax({  
		url: jsBasePath+"/studentpc/voice/soGouSenceParse.html",
		type: 'POST',  
		data: data,  
		dataType: 'JSON',  
		cache: false,  
		processData: false,  
		contentType: false  
	}).done(function(res){  
		if(isLast=="1"){
			parseResult(res.data);
		}
	});  
	return false;
}


function parseResult(parseResult){
	 recorder && recorder.exportWAV(function(blob) {
		audioBlob = blob;
		var data = new FormData();
		var student_id = $("#student_id").val();
	    var test_id = $("#test_id").val();
	    var class_code = $("#class_code").val();
	    var question_id = $("#question_id").val();
	   	var isEnd = $("#isEnd").val();
	   	var student_test_id = $("#student_test_id").val();
	   	var tmNum = $("#tmNum").val();
   		var xh = $("#xh").val();
   		var bigXh = $("#bigXh").val();
   		
   		data.append("studentAnswer",audioBlob);
    	data.append("studentTestId",student_test_id);
    	data.append("studentId",student_id);
    	data.append("testId",test_id);
    	data.append("classCode",class_code);
    	data.append("questionId",question_id);
    	data.append("isQuestionEnd","1");
    	console.log((parseResult.accuracy/10).toFixed(2));
    	data.append("accuracy_sogou",(parseResult.accuracy/10).toFixed(2));
    	data.append("fluency_sogou",(parseResult.fluency/10).toFixed(2));
    	data.append("integrity_sogou",(parseResult.integrity/10).toFixed(2));
    	data.append("overall_sogou",(parseResult.overall/10).toFixed(2));
    	isEnd=="1" && data.append("isEnd",isEnd);
    	$.ajax({  
    		url: jsBasePath+"/studentpc/voice/tjParseResult.html",
    		type: 'POST',  
    		data: data,  
    		dataType: 'JSON',  
    		cache: false,  
    		processData: false,  
    		contentType: false  
    	}).done(function(res){  
    		recorder.clear();
  			layer.close(tjindex);
  			if(res.flag){  
  				if(isEnd=="1"){
  					location.href = jsBasePath+"/studentpc/studenttest/studenttestview.html";
  				}else{
  				//提交下一題
  	  				location.href = jsBasePath+"/studentpc/studenttest/toStudentKs.html?studentId="+student_id
  	  						+"&testId="+test_id+"&classCode="+class_code+"&questionId="+question_id
  	  						+"&tmNum="+tmNum+"&xh="+xh+"&bigXh="+bigXh+"&studentTestId="+student_test_id;

  				}
  			}else{  
  				layer.alert(res.message,{icon:2});
  			}  
      	});  
    	
	 });
}		

function tgOrTopicSx(){
	zd_status = 2;
	setGd(tgTime,playContent);
}

function playContent(){
	startRecording();
	zd_status = 3;
	setGd(answerTime,stopRecording);
}

function setGd(sj,callback){
	 $('.circleh').removeClass('clip-autoh'); 
     $('.righth').addClass('wth0h'); 
	var total = sj * 100/5;
	var djsSj = sj;
	var percent=0; 
	var loading=setInterval(function(){ 
		if(percent%20==0){
			tjbuttonChange(djsSj);
			djsSj--;
		}
        if(percent>=total){ 
           clearInterval(loading);
           typeof callback === "function" && callback();
        }else if(percent>total/2){ 
            $('.circleh').addClass('clip-autoh'); 
            $('.righth').removeClass('wth0h'); 
        } 
        $('.lefth').css("-webkit-transform","rotate("+(18/sj)*percent+"deg)"); 
        $('.numh>span').text(percent); 
        percent++; 
    },50); 
}


function tjbuttonChange(djsSj){
	if(zd_status=="1"){
		$("#tsmess").val("指导语导读中，不录音......."+djsSj+"s");
	}
	if(zd_status=="2"){
		$("#tsmess").val("题目阅读中，不录音......."+djsSj+"s");
	}
	if(zd_status=="3"){
		$("#tsmess").val("录音中，请作答......."+djsSj+"s");
	}
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