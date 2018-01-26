var audio_context,recorder;
initNavigatorRecorder();
function initNavigatorRecorder(){
	try {
		window.AudioContext = window.AudioContext || window.webkitAudioContext;
		navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia 
		|| navigator.mozGetUserMedia || navigator.msGetUserMedia||navigator.mediaDevices.getUserMedia;
	    window.URL = window.URL || window.webkitURL;
	    audio_context = new AudioContext;
	    if(!!navigator.mediaDevices){
			navigator.mediaDevices.getUserMedia({audio: true}).then(function(mediaStream) {
				startUserMedia(mediaStream);
			});
		}else if(!!navigator.getUserMedia){
			navigator.getUserMedia({audio: true}, startUserMedia, function(e) {});
		}
	}catch (e) {
		alert('你的浏览器不支持在线录音，请联系管理员!');
	}
}


function startUserMedia(stream) {
	var input = audio_context.createMediaStreamSource(stream);
	recorder = new Recorder(input);
}