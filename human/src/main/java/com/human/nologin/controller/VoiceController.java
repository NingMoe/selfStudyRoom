package com.human.nologin.controller;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.human.basic.entity.XdfClassInfo;
import com.human.basic.service.XdfClassInfoService;
import com.human.utils.OSSUtil;
import com.human.utils.sogou.VoiceSence;
import com.sogou.speech.SpeexInterface;

@Controller
@RequestMapping("/free/voice/")
public class VoiceController {

    private static final int REPLY_LENGTH = 2600;
    private static int CONNECTION_TIME_OUT = 20000;
    private static int READ_TIME_OUT = 8000;
    private static int FINAL_READ_TIME_OUT = 20000;
    private static int MAX_ENCRYPT_STR_LEN = 1025;
    private static int API_VERSION = 6040; // sogou search translation , andriod-6040, ios-6140
    private static int ERROR_NETWORK_RESPONSE_STATUS_CODE_ERROR = -2004;
    private static int ERROR_EXCEPTION_DURING_HTTP_REQUEST = -2005;

    private static final String httpAddr = "https://opentest.speech.sogou.com/index.sa";
    private static String PQAN_1 = "{\"scoretype\":\"eng_wrd\",\"jsonlabels\":{\"refText\": \"freedom\"}}";

    /*
     * private static String PQAN_2 =
     * "{\"scoretype\":\"eng_pqan\",\"jsonlabels\":{\"lm\": [{\"text\": \"What do you like doing with your friend Max?\"},{\"text\": \"I like playing table tennis with him.\"},{\"text\": \"How often do you play it?\"},{\"text\": \"We play it once a week\"}],\"key\": [{\"text\": \"once a week\"},{\"text\": \"once\"}],  \"quest_ans\": \"How often do you play it?\"}}"
     * ; private static String TOPIC =
     * "{\"scoretype\":\"eng_topic\",\"jsonlabels\":{\"lm\": [{\"text\": \"In order to be more healthy, I have changed the diet. I seldom ate fruits and vegetables in the past. I liked cakes, sweets and cola. But now, I usually eat a banana, some bread and a cup of milk in the breakfast. At lunch, I usually eat fish and vegetables. Now I'm getting more and more healthy. My study therefore is getting better and better.\"},{\"text\": \"In order to be healthier, I have changed the diet. I seldom ate fruits and vegetables before. I liked cakes, candies and cola. But I always have a banana, some bread and a glass of milk for the breakfast at present. I usually have fish and vegetables for lunch. Now I'm getting healthier and healthier. My study is becoming better and better.\"},{\"text\": \"In order to be more healthy, I have changed my diet;  Previously, I seldom eat fruits and vegetables, like cakes, candy and coke. Now, I always eat a banana, some bread, and a glass of milk for breakfast. Usually eat fish and vegetables; I am becoming more and more healthy now, and the learning situation is getting better and better.\"}]}}"
     * ;
     */

    @Autowired
    private XdfClassInfoService xdfClassInfoService;

    @Autowired
    private OSSUtil ossUtil;

    @Value("${oss.bucket}")
    private String bucketName;

    @Value("${oss.kinerEditorPath}")
    private String kinerEditorPath;

    @Value("${oss.fileurl}")
    private String fileurl;

    public static byte[] jsonByteArray;

    private final Logger logger = LogManager.getLogger(VoiceController.class);

    @RequestMapping(value = "upload")
    public Map<String, Object> fileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
        logger.info("语音上传进来了");
        System.out.println(System.getProperty("java.library.path"));
        Map<String, Object> result = new HashMap<String, Object>();
        long currentTime = System.currentTimeMillis();
        try {
            JSONObject jo = getJson("eng_wrd", "freedom");
            jsonByteArray = jo.toString().getBytes("utf-8");
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                OSSClient ossClient = ossUtil.getClient();
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    SpeexInterface s = new SpeexInterface();
                    long p = s.createSpeex();
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if ("".equals(originalName) || originalName == null) {
                        continue;
                    }
                    String newFileName = kinerEditorPath + System.currentTimeMillis() + ".wav";
                    String tempFilePath = request.getSession().getServletContext().getRealPath("/static/temp/test.pcm");
                    FileOutputStream os = new FileOutputStream(tempFilePath, true);
                    System.out.println(fileurl + newFileName);
                    ossUtil.uploadFile(ossClient, bucketName, newFileName, multiFile);
                    System.out.println("压缩前大小" + multiFile.getSize());
                    InputStream is = multiFile.getInputStream();
                    is.skip(44);
                    byte[] bytes = new byte[6400];
                    byte[] destArr = null;
                    for (;;) {
                        int read = is.read(bytes);
                        if (read <= 0) {
                            break;
                        }
                        short[] shorts = new short[read / 2];
                        ByteBuffer.wrap(bytes).order(ByteOrder.nativeOrder()).asShortBuffer().get(shorts);
                        s.processIn(p, shorts, read / 2);
                        byte[] resultYp = s.processOut(p, true);
                        os.write(resultYp);
                        if (destArr == null) {
                            destArr = resultYp;
                        }
                        else {
                            destArr = addByteArr(destArr, resultYp);
                        }
                    }

                    System.out.println("压缩后大小" + destArr.length);

                    List<byte[]> voiceArr = new ArrayList<byte[]>();
                    voiceArr = getDestVoice(destArr, voiceArr);
                    for (int i = 0; i < voiceArr.size(); i++) {
                        /**
                         * 调用接口
                         */
                        int seqNo = (i == voiceArr.size() - 1) ? (-1 * (i + 1)) : (i + 1);
                        sendMessage(voiceArr.get(i), currentTime, seqNo, voiceArr.size());
                    }
                }
            }
            logger.info("语音上传上传结束");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info("语音上传失败");
        }
        return result;
    }

    private String sendMessage(byte[] voiceSentence, long currentTime, int seqNo, int voiceLength) {
        System.out.println(voiceSentence.toString());
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
                logger.info("sequenceNo:" + seqNo + ",replycontent:" + replyContent);
                tmpIn.close();
            }
            else {
                return "{\"succeed\":\"false\"}";
            }
        }
        catch (Exception e) {
            return "{\"succeed\":\"false\"}";
        }
        finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        return "{\"succeed\":\"true\"}";
    }

    private String inputStream2String(InputStream in, String encoding) throws IOException {
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

    private HttpURLConnection openConnection(URL url, int sequenceNo) throws IOException {
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

    private JSONObject getJson(String scoreType, String refText) {
        JSONObject jsonObject = new JSONObject();
        try {
            /*
             * if (scoreType.equals("eng_pqan")) { if(refText.equals("What do you like doing with your friend Max?")) { jsonObject = JSON.parseObject(PQAN_1); }else{ jsonObject =
             * JSON.parseObject(PQAN_2); }
             * 
             * } else if(scoreType.equals("eng_topic")){ jsonObject = JSON.parseObject(TOPIC);
             * 
             * } else { jsonObject.put("scoretype", scoreType); jsonObjectRefText.put("refText", refText); jsonObject.put("jsonlabels", jsonObjectRefText); }
             */
            jsonObject = JSON.parseObject(PQAN_1);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private List<byte[]> getDestVoice(byte[] src, List<byte[]> result) {
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

    private String formatQueryParam(VoiceSence voiceSence) throws UnsupportedEncodingException {
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

    @RequestMapping("/getClass")
    public void getClass(HttpServletRequest request, HttpServletResponse response, XdfClassInfo xci) {
        try {
            response.setContentType("text/plain");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            Map<String, String> map = new HashMap<String, String>();
            map.put("result", "content");
            PrintWriter out = response.getWriter();
            List<XdfClassInfo> classList = xdfClassInfoService.queryClassByCondition(xci);
            String jsonpCallback = request.getParameter("jsonpCallback");// 客户端请求参数
            out.println(jsonpCallback + "(" + JSON.toJSONString(classList) + ")");// 返回jsonp格式数据
            out.flush();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] addByteArr(byte[] src1, byte[] src2) {
        byte[] dest = new byte[src1.length + src2.length];
        System.arraycopy(src1, 0, dest, 0, src1.length);
        System.arraycopy(src2, 0, dest, src1.length, src2.length);
        return dest;
    }
}