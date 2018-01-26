//package com.ls.spt.speech.controller;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
////import com.iflytek.cloud.speech.SpeechConstant;
////import com.iflytek.cloud.speech.SpeechError;
////import com.iflytek.cloud.speech.SpeechSynthesizer;
////import com.iflytek.cloud.speech.SynthesizeToUriListener;
//import com.ls.spt.utils.PcmToWavUtil;
//
//public class TtsSpeechController {
//    
//    private final  Logger logger = LogManager.getLogger(TtsSpeechController.class);
//    
//    @Autowired
//    private PcmToWavUtil pcmToWavUtil;
//    
//    public static final String ENG_TYPE = SpeechConstant.TYPE_CLOUD;
//    public static final String VOICE = "小燕";
//    public static final String BG_SOUND = "0";
//    public static final String SPEED = "50";
//    public static final String PITCH = "50";
//    public static final String VOLUME = "50";
//    public static final String SAVE = "0";
//    
//    
//    /**
//     * 进入题库类型列表页面
//     */
//    public Map<String,Object> index(HttpServletRequest request) {
//        String tempFilePath = request.getSession().getServletContext().getRealPath("/static/temp/test.pcm");
//        String tempFilePath1 = request.getSession().getServletContext().getRealPath("/static/temp/test.wav");
//        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
//        String mText = "周鑫你好";
//        SynthesizeToUriListener mSynListener = new SynthesizeToUriListener() {
//            @Override
//            public void onBufferProgress(int paramInt) {
//                // TODO Auto-generated method stub
//            }
//            @Override
//            public void onEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Object paramObject1, Object paramObject2) {
//                // TODO Auto-generated method stub
//                
//            }
//            @Override
//            public void onSynthesizeCompleted(String paramString, SpeechError paramSpeechError) {
//                try {
//                    pcmToWavUtil.convertAudioFiles(tempFilePath, tempFilePath1);
//                }
//                catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                
//            }
//        };
//        mTts.synthesizeToUri(mText, tempFilePath, mSynListener);
//        return null;
//        
//    }
//    
//    
//}
