package com.ls.spt.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.ls.spt.question.entity.QuestionVoiceParam;
import com.ls.spt.utils.sogou.VoiceSence;

public class MyTest {
    
    public static byte[] jsonByteArray;
    
    private static final int REPLY_LENGTH = 2600;
    private static int CONNECTION_TIME_OUT = 20000;
    private static int READ_TIME_OUT = 8000;
    private static int FINAL_READ_TIME_OUT = 20000;
    
    private static final String httpAddr = "https://evaleng.speech.sogou.com/index.sa";
    
    
    public static void main(String[] args) throws ParseException {
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);//得到前3个月
        Date  date = calendar.getTime();
        System.out.println(format.format(date));
        String a = "123456";
        System.out.println(a.substring(0,3));
        /*System.out.println(System.getProperty("java.library.path"));
        try {
            SpeexInterface s = new SpeexInterface();
            long p = s.createSpeex();
            InputStream is = new FileInputStream(new File("D://out_data.wav"));
            long currentTime = System.currentTimeMillis();
            byte[] bytes = new byte[6400];
            byte[] destArr = null;
            QuestionVoiceParam param = new QuestionVoiceParam();
            param.setScoreType("eng_wrd");
            param.setRefText("freedom");
            JSONObject jo = getJson(param);
            jsonByteArray = jo.toString().getBytes("utf-8");
            for (;;) {
                int read = is.read(bytes);
                if (read <= 0) {
                    break;
                }
                short[] shorts = new short[read / 2];
                ByteBuffer.wrap(bytes).order(ByteOrder.nativeOrder()).asShortBuffer().get(shorts);
                s.processIn(p, shorts, read / 2);
                byte[] resultYp = s.processOut(p, true);
                //os.write(resultYp);
                if (destArr == null) {
                    destArr = resultYp;
                } else {
                    destArr = addByteArr(destArr, resultYp);
                }
            }
            
            System.out.println("压缩后大小" + destArr.length);
            
            List<byte[]> voiceArr = new ArrayList<byte[]>();
            voiceArr = getDestVoice(destArr, voiceArr);
            for (int i = 0; i < voiceArr.size(); i++) {
                *//**
                 *调用接口
                 *//*
                int seqNo = (i == voiceArr.size() - 1) ? (-1 * (i + 1)) : (i + 1);
                Map<String,Object> parseMap =sendMessage(voiceArr.get(i), currentTime, seqNo, voiceArr.size());
                if(i==voiceArr.size()-1){
                    boolean isJxCg = (boolean) parseMap.get("flag");
                    if(isJxCg){
                        QuestionParseResult parseResult = (QuestionParseResult) parseMap.get("parseResult");
                        
                        LstZuoyeStudentAnswer lstZuoyeStudentAnswer = new LstZuoyeStudentAnswer();
                        BigDecimal accuracy_sogou = new BigDecimal(parseResult.getResult().getAccuracy() == null ? parseResult.getResult().getOverall() : parseResult.getResult().getAccuracy()).divide(new BigDecimal(10));
                        lstZuoyeStudentAnswer.setAccuracy_sogou(accuracy_sogou);
                        BigDecimal fluency_sogou = new BigDecimal(parseResult.getResult().getFluency() == null ? parseResult.getResult().getOverall() : parseResult.getResult().getFluency()).divide(new BigDecimal(10));
                        lstZuoyeStudentAnswer.setFluency_sogou(fluency_sogou);
                        BigDecimal integrity_sogou = new BigDecimal(parseResult.getResult().getIntegrity() == null ? parseResult.getResult().getOverall() : parseResult.getResult().getIntegrity()).divide(new BigDecimal(10));
                        lstZuoyeStudentAnswer.setIntegrity_sogou(integrity_sogou);
                        BigDecimal overall_sogou = new BigDecimal(parseResult.getResult().getOverall()).divide(new BigDecimal(10));
                        lstZuoyeStudentAnswer.setOverall_sogou(overall_sogou);
                    }else{
                        
                    }
                }
            }
                
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    
    
    private static Map<String,Object> sendMessage(byte[] voiceSentence, long currentTime, int seqNo, int voiceLength) {
        System.out.println(voiceSentence.toString());
        Map<String,Object> result = new HashMap<String,Object>();
        HttpURLConnection urlConn = null;
        String replyContent = "";
        try {
            VoiceSence vs = new VoiceSence();
            vs.setArea(0);
            vs.setBase_no("");
            vs.setCancel(0);
            vs.setImei_no("A000005E97A95C");
            vs.setResult_amount(voiceLength);
            vs.setSequence_no(seqNo);
            vs.setStart_time(currentTime);
            /*
             * String type_no_str = "0000000111001"; long type_no = Long.parseLong(type_no_str, 2);
             */
            vs.setType_no(16416);
            vs.setV(1600);
            vs.setVoice_content(voiceSentence);
            vs.setVoice_length(voiceSentence.length);
            String uriSuffix = formatQueryParam(vs);
            String postURI = httpAddr + "?" + uriSuffix;

            System.out.println("seqNo:" + seqNo + "请求地址:" + postURI);
            urlConn = openConnection(new URL(postURI), vs.getSequence_no());
            DataOutputStream tmpOut = new DataOutputStream(urlConn.getOutputStream());
            byte[] data = vs.getVoice_content();
            String contentPrefix = "voice_content=";
            tmpOut.writeBytes(contentPrefix);
            if (data != null && data.length > 0) {
                tmpOut.write(data);
            }
            String contentHeader = "info_content=";
            tmpOut.writeBytes(contentHeader);
            if (jsonByteArray != null && jsonByteArray.length > 0) {
                tmpOut.write(jsonByteArray);
            }
            tmpOut.flush();
            tmpOut.close();

            int responseStatus = urlConn.getResponseCode();
            if (responseStatus == 200) {
                InputStream tmpIn = urlConn.getInputStream();
                replyContent = inputStream2String(tmpIn, "GBK");
                System.out.println("############################"+replyContent);
                /*if(seqNo<0){
                    QuestionParseResult parseResult = JSON.parseObject(replyContent, QuestionParseResult.class);
                    result.put("flag", true);
                    result.put("parseResult", parseResult);
                }*/
                tmpIn.close();
            }
            else {
                result.put("flag", false);
                result.put("message", "SOGOU接口解析失敗");
            }
        }
        catch (Exception e) {
            result.put("flag", false);
            result.put("message", "SOGOU接口解析失敗");
        }
        finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        return result;
    }

    private static String inputStream2String(InputStream in, String encoding) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader inRead = new InputStreamReader(in, encoding);
        char[] buf = new char[REPLY_LENGTH];
        int readLen = 0;
        while ((readLen = inRead.read(buf)) > 0) {
            sb.append(buf, 0, readLen);
        }
        inRead.close();
        return sb.toString();
    }

    private static HttpURLConnection openConnection(URL url, int sequenceNo) throws IOException {
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setDoOutput(true);
        urlConn.setDoInput(true);
        urlConn.setRequestMethod("POST");
        urlConn.setConnectTimeout(CONNECTION_TIME_OUT);
        if (sequenceNo < 0) {
            urlConn.setReadTimeout(FINAL_READ_TIME_OUT);
        }
        else {
            urlConn.setReadTimeout(READ_TIME_OUT);
        }
        urlConn.setUseCaches(false);
        urlConn.setInstanceFollowRedirects(false);
        urlConn.addRequestProperty("Accept-Charset", "GBK");
        urlConn.setRequestProperty("Content-type", "application/x-java-serialized-object");
        /* urlConn.addRequestProperty("S-COOKIE", getEncScookie()); */
        urlConn.connect();
        return urlConn;
    }

    private static JSONObject getJson(QuestionVoiceParam param) {
        JSONObject jsonObject = new JSONObject();
        String jsonStr = "";
        try {
            if(("eng_wrd").equals(param.getScoreType())||("eng_snt").equals(param.getScoreType())||("eng_chp").equals(param.getScoreType())){
                jsonStr = "{\"scoretype\":\""+param.getScoreType()+"\",\"jsonlabels\":{\"refText\": \""+param.getRefText()+"\"}}"; 
            }
            if(("eng_pqan").equals(param.getScoreType())){
                jsonStr = "{\"scoretype\":\""+param.getScoreType()+"\",\"jsonlabels\":{\"lm\": [{\"text\":\""+param.getRefText()+"\"}],\"key\": [{\"text\":\""+param.getKey()+"\"}],\"quest_ans\":\""+param.getContent()+"\",\"para\":\""+param.getTopic()+"\"}}"; 
            }
            if(("eng_topic").equals(param.getScoreType())){
                jsonStr = "{\"scoretype\":\""+param.getScoreType()+"\",\"jsonlabels\":{\"lm\": [{\"answer\":1,\"text\":\""+param.getRefText()+"\"}]}}"; 
            }
            jsonObject = JSON.parseObject(jsonStr);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static List<byte[]> getDestVoice(byte[] src, List<byte[]> result) {
        if (src.length < 3000) {
            result.add(src);
        }
        else {
            byte[] newSrc = new byte[src.length - 3000];
            byte[] tmp = new byte[3000];
            System.arraycopy(src, 0, tmp, 0, 3000);
            result.add(tmp);
            System.arraycopy(src, 3000, newSrc, 0, src.length - 3000);
            getDestVoice(newSrc, result);
        }
        return result;
    }

    private static String formatQueryParam(VoiceSence voiceSence) throws UnsupportedEncodingException {
        /**
         * 固定base_no="", result_amount=5 , cancel=0, 删除net_type, f(fakeFlag)
         */
        final String baseNo = "";
        /**
         * 默认使用中文
         */
        String uriSuffix = String.format(
                "imei_no=%s&type_no=%s&area=%d&base_no=%s" + "&start_time=%s&sequence_no=%d&voice_length=%d&result_amount=%d&cancel=%d&v=%d&net_type=wifi&partial=1&token=%d&info_length=%d",
                voiceSence.getImei_no(), voiceSence.getType_no(), voiceSence.getArea(), baseNo, String.valueOf(voiceSence.getStart_time()), voiceSence.getSequence_no(), voiceSence.getVoice_length(),
                voiceSence.getResult_amount(), voiceSence.getCancel(), voiceSence.getV(), 123, jsonByteArray.length);
        return uriSuffix;
    }

    private static byte[] addByteArr(byte[] src1, byte[] src2) {
        byte[] dest = new byte[src1.length + src2.length];
        System.arraycopy(src1, 0, dest, 0, src1.length);
        System.arraycopy(src2, 0, dest, src1.length, src2.length);
        return dest;
    }

}
