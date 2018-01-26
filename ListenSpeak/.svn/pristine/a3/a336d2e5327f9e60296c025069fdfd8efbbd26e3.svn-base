package com.ls.spt.studentpc.student.serviceImpl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.ls.spt.question.entity.QuestionParseResult;
import com.ls.spt.question.entity.QuestionVoiceParam;
import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.studentpc.student.entity.ZuoyeQuestion;
import com.ls.spt.studentpc.student.service.StudentZuoyeService;
import com.ls.spt.studentzuoye.dao.LstStudentZuoyeDao;
import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.ls.spt.studentzuoye.entity.StudentGrowthTrajectory;
import com.ls.spt.utils.OSSUtil;
import com.ls.spt.utils.StudentPcConstants;
import com.ls.spt.utils.sogou.VoiceSence;
import com.ls.spt.zuoye.dao.LstZuoyeQuestionDao;
import com.ls.spt.zuoye.dao.LstZuoyeStudentAnswerDao;
import com.ls.spt.zuoye.entity.LstZuoyeQuestion;
import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;
import com.sogou.speech.SpeexInterface;

@Service
public class StudentZuoyeServiceImpl implements StudentZuoyeService {
    
    private final Logger log = LogManager.getLogger(StudentZuoyeServiceImpl.class);
    
    private static final String httpAddr = "https://evaleng.speech.sogou.com/index.sa";
    
    @Resource
    private OSSUtil ossUtil;

    @Resource
    private LstStudentZuoyeDao lstStudentZuoyeDao;
    
    @Resource
    private LstZuoyeStudentAnswerDao lstZuoyeStudentAnswerDao;
    
    @Resource
    private LstZuoyeQuestionDao lstZuoyeQuestionDao;
    
    @Value("${oss.bucket}")
    private String bucketName;

    @Value("${oss.lsStudentAnswerPath}")
    private String lsStudentAnswerPath;

    @Value("${oss.fileurl}")
    private String fileurl;

    public static byte[] jsonByteArray;
    
    private static final int REPLY_LENGTH = 2600;
    private static int CONNECTION_TIME_OUT = 20000;
    private static int READ_TIME_OUT = 8000;
    private static int FINAL_READ_TIME_OUT = 20000;
    
    /**
     * 获取正在做题的题目
     * @param request
     * @param response
     * @param class_code
     * @param zuoye_id
     * @param question_id
     * @return
     */
    public Map<String, Object> getZuoyeQuestion(HttpServletRequest request, HttpServletResponse response, String class_code, Integer zuoye_id, Integer th) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "请重新登录");
            return map;
        }
        
        if(StringUtils.isEmpty(class_code)){
            map.put("flag", false);
            map.put("message", "班号为空");
            return map;
        }
        
        if(zuoye_id == null){
            map.put("flag", false);
            map.put("message", "作业ID为空");
            return map;
        }
        
        try {
            //验证作业
            Map<String, Object> mapparams = new HashMap<String, Object>();
            mapparams.put("class_code", class_code);
            mapparams.put("zuoye_id", zuoye_id);
            mapparams.put("student_id", studentUser.getId());
            LstStudentZuoye lstStudentZuoye = lstStudentZuoyeDao.selectZuoyeinfo(mapparams);
            if(lstStudentZuoye == null){
                map.put("flag", false);
                map.put("message", "没有找到对应的作业");
                return map;
            }
            
            if(lstStudentZuoye.getTj_status() == 2 || lstStudentZuoye.getTj_status() == 3 || lstStudentZuoye.getTj_tme() != null){
                map.put("flag", false);
                map.put("message", "该作业已经提交");
                return map;
            }
            
            /*if(lstStudentZuoye.getEnd_time().getTime() > new Date().getTime()){
                map.put("flag", false);
                map.put("message", "已经到达截止时间");
                return map;
            }*/
            List<LstZuoyeQuestion> list = lstZuoyeQuestionDao.selectStudentQuestion(mapparams);
            if(list != null && list.size() > 0){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("list", list);
                int i = 0;
                for(LstZuoyeQuestion l : list){
                    boolean b = true;
                    for(LstZuoyeStudentAnswer a : l.getAnswer_list()){
                        i++;
                        if(StringUtils.isEmpty(a.getStudent_answer())){
                            b = false;
                            break;
                        }
                    }
                    if(!b){
                        break;
                    }
                }
                map.put("th", i);
            }else{
                map.put("flag", false);
                map.put("message", "查询结果为空");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "异常：" + e);
        }
        
        return map;
    }
    
    /**
     * 作业基础信息
     * @param request
     * @param response
     * @return
     */
    public Object getZuoyeInfo(HttpServletRequest request, HttpServletResponse response, Integer zuoye_id, String class_code) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "请重新登录");
            return map;
        }
        
        if(zuoye_id == null){
            map.put("flag", false);
            map.put("message", "作业ID为空");
            return map;
        }
        
        if(StringUtils.isEmpty(class_code)){
            map.put("flag", false);
            map.put("message", "班号为空");
            return map;
        }
        
        try {
            Map<String, Object> mapparams = new HashMap<String, Object>();
            mapparams.put("student_id", studentUser.getId());
            mapparams.put("zuoye_id", zuoye_id);
            mapparams.put("class_code", class_code);
            LstStudentZuoye listStudentZuoye = lstStudentZuoyeDao.selectByParams(mapparams);//获取作业情况
            List<LstZuoyeStudentAnswer> alist = lstZuoyeStudentAnswerDao.selectComplete(mapparams);//获取完成情况
            List<LstZuoyeQuestion> qlist = lstZuoyeQuestionDao.selectStudentQuestion(mapparams);//获取作业详情
            Double count = lstStudentZuoyeDao.getTranscendCount(mapparams);//超过班级内多少人
            map.put("flag", true);
            map.put("message", "查询成功");
            map.put("studentZuoye", listStudentZuoye);
            map.put("answerlist", alist);
            map.put("questionlist", qlist);
            map.put("transcendCount", count);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "异常："+e);
        }
        
        return map;
    }

    /**
     * 获取学生成长轨迹
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> selectGrowthTrajectory(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
        try {
            //获取学生成长轨迹
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            List<StudentGrowthTrajectory> list = new ArrayList<StudentGrowthTrajectory>();
            List<StudentGrowthTrajectory> list1 = lstStudentZuoyeDao.selectGrowthTrajectory(mapparam);
            for(int i = 0; i < list1.size(); i++){
                StudentGrowthTrajectory s = list1.get(i);
                BigDecimal a = new BigDecimal("0");
                Integer y = 0;
                Integer z = 0 ;
                for(int j = 0; j < list1.size(); j++){
                    if(list1.get(j).getOverall() != null){
                        BigDecimal b = list1.get(j).getOverall();
                        a = a.add(b);
                        y += list1.get(j).getZuoye_end_num();
                        z += list1.get(j).getZuoye_num();
                    }
                    if(i == j){
                        break;
                    }
                }
                a = a.divide(new BigDecimal((i+1)+""), 2);
                s.setOverall_all(a);
                s.setZuoye_end_num_all(y);
                s.setZuoye_num_all(z);
                list.add(s);
            }
            map.put("flag", false);
            map.put("message", "获取成功");
            map.put("list", JSON.toJSONString(list));
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "异常："+e);
        }
        return map;
    }

    /**
     * 获取学生已完成作业
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentEndZuoye(HttpServletRequest request, HttpServletResponse response, Integer pageNow, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "please login again");
            return map;
        }
        
        if(pageNow == null || pageNow <= 0){
            map.put("flag", false);
            map.put("message", "pageNow must greater than zero");
            return map;
        }
        
        if(pageSize == null || pageSize <= 0){
            map.put("flag", false);
            map.put("message", "pageSize must greater than zero");
            return map;
        }
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            
            Integer count = lstStudentZuoyeDao.selectStudentEndZuoyeCount(mapparam);
            count = count == null ? 0 : count;
            map.put("count", count);
            if(count > 0){
                mapparam.put("pageStart", (pageNow - 1) * pageSize);
                mapparam.put("pageSize", pageSize);
                List<LstStudentZuoye> list = lstStudentZuoyeDao.selectStudentEndZuoye(mapparam);
                map.put("flag", true);
                map.put("message", "ok");
                map.put("list", list);
            }else{
                map.put("flag", false);
                map.put("message", "count is zero");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取已结束作业异常："+e);
        }
        
        return map;
    }

    /**
     * 获取学生未完成作业
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotEndZuoye(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
        try {
            Map<String ,Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            List<LstStudentZuoye> list = lstStudentZuoyeDao.selectStudentNotEndZuoye(mapparam);
            map.put("flag", true);
            map.put("message", "ok");
            map.put("list", list);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取未完成作业异常："+e);
        }
        
        return map;
    }
    
    /**
     * 作业答案提交
     * @param zuoyeQuestion
     * @param request
     * @return
     */
    public Map<String, Object> tjZuoyeParseResult(ZuoyeQuestion zuoyeQuestion, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        String newFileName = "";
        
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "请重新登录");
            return map;
        }
        if(StringUtils.isEmpty(zuoyeQuestion.getClassCode())){
            map.put("flag", false);
            map.put("message", "班号为空");
            return map;
        }
        
        if(zuoyeQuestion.getZuoyeId() == null){
            map.put("flag", false);
            map.put("message", "作业ID为空");
            return map;
        }
                
        
        
        try{
            
            OSSClient ossClient = ossUtil.getClient();
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if ("".equals(originalName) || originalName == null) {
                        continue;
                    }
                    newFileName = lsStudentAnswerPath + System.currentTimeMillis() + ".wav";
                    System.out.println(fileurl+newFileName);
                    ossUtil.asyncUploadFile(ossClient, bucketName, newFileName, multiFile);
                }
            }
            
            LstZuoyeStudentAnswer lstZuoyeStudentAnswer = new LstZuoyeStudentAnswer();
            if(zuoyeQuestion.getAccuracy() == null){
                
            }
            BigDecimal accuracy_sogou = (zuoyeQuestion.getAccuracy() == null ? zuoyeQuestion.getOverall() : zuoyeQuestion.getAccuracy()).divide(new BigDecimal(10));
            lstZuoyeStudentAnswer.setAccuracy_sogou(accuracy_sogou);
            BigDecimal fluency_sogou = (zuoyeQuestion.getFluency() == null ? zuoyeQuestion.getOverall() : zuoyeQuestion.getFluency()).divide(new BigDecimal(10));
            lstZuoyeStudentAnswer.setFluency_sogou(fluency_sogou);
            BigDecimal integrity_sogou = (zuoyeQuestion.getIntegrity() == null ? zuoyeQuestion.getOverall() : zuoyeQuestion.getIntegrity()).divide(new BigDecimal(10));
            lstZuoyeStudentAnswer.setIntegrity_sogou(integrity_sogou);
            BigDecimal overall_sogou = (zuoyeQuestion.getOverall()).divide(new BigDecimal(10));
            lstZuoyeStudentAnswer.setOverall_sogou(overall_sogou);
            
            lstZuoyeStudentAnswer.setStudent_id(studentUser.getId());
            lstZuoyeStudentAnswer.setClass_code(zuoyeQuestion.getClassCode());
            lstZuoyeStudentAnswer.setZuoye_id(zuoyeQuestion.getZuoyeId());
            lstZuoyeStudentAnswer.setQuestion_id(zuoyeQuestion.getQuestionId());
            
            lstZuoyeStudentAnswer.setStudent_answer(newFileName);
            lstZuoyeStudentAnswerDao.updateByStudentidClasscodeZuoyeidAndQuestionid(lstZuoyeStudentAnswer);
            
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            mapparam.put("zuoye_id", zuoyeQuestion.getZuoyeId());
            mapparam.put("class_code", zuoyeQuestion.getClassCode());
            List<LstZuoyeQuestion> list = lstZuoyeQuestionDao.selectStudentQuestion(mapparam);
            if(("1").equals(zuoyeQuestion.getIsEnd())){
                //计算平均分，并更新进lst_student_zuoye表
                
                //获取所有分数
                BigDecimal accuracy_sogou_avg = new BigDecimal("0");
                BigDecimal fluency_sogou_avg = new BigDecimal("0");
                BigDecimal integrity_sogou_avg = new BigDecimal("0");
                BigDecimal overall_sogou_avg = new BigDecimal("0");
                BigDecimal accuracy_sogou_all = new BigDecimal("0");
                BigDecimal fluency_sogou_all = new BigDecimal("0");
                BigDecimal integrity_sogou_all = new BigDecimal("0");
                BigDecimal overall_sogou_all = new BigDecimal("0");
                for(LstZuoyeQuestion l : list){
                    BigDecimal accuracy_sogou_all1 = new BigDecimal("0");
                    BigDecimal fluency_sogou_all1 = new BigDecimal("0");
                    BigDecimal integrity_sogou_all1 = new BigDecimal("0");
                    BigDecimal overall_sogou_all1 = new BigDecimal("0");
                    for(LstZuoyeStudentAnswer answer :l.getAnswer_list()){
                        accuracy_sogou_all1 = accuracy_sogou_all1.add(answer.getAccuracy_sogou());
                        fluency_sogou_all1 = fluency_sogou_all1.add(answer.getFluency_sogou());
                        integrity_sogou_all1 = integrity_sogou_all1.add(answer.getIntegrity_sogou());
                        overall_sogou_all1 = overall_sogou_all1.add(answer.getOverall_sogou());
                    }
                    accuracy_sogou_all = accuracy_sogou_all.add(accuracy_sogou_all1.divide(new BigDecimal(l.getAnswer_list().size()), 2, BigDecimal.ROUND_HALF_UP));
                    fluency_sogou_all = fluency_sogou_all.add(fluency_sogou_all1.divide(new BigDecimal(l.getAnswer_list().size()), 2, BigDecimal.ROUND_HALF_UP));
                    integrity_sogou_all = integrity_sogou_all.add(integrity_sogou_all1.divide(new BigDecimal(l.getAnswer_list().size()), 2, BigDecimal.ROUND_HALF_UP));
                    overall_sogou_all = overall_sogou_all.add(overall_sogou_all1.divide(new BigDecimal(l.getAnswer_list().size()), 2, BigDecimal.ROUND_HALF_UP));
                }
                BigDecimal listsizeb = new BigDecimal(list.size());
                accuracy_sogou_avg = accuracy_sogou_all.divide(listsizeb, 2, BigDecimal.ROUND_HALF_UP);
                fluency_sogou_avg = fluency_sogou_all.divide(listsizeb, 2, BigDecimal.ROUND_HALF_UP);
                integrity_sogou_avg = integrity_sogou_all.divide(listsizeb, 2, BigDecimal.ROUND_HALF_UP);
                overall_sogou_avg = overall_sogou_all.divide(listsizeb, 2, BigDecimal.ROUND_HALF_UP);
                
                mapparam.put("accuracy_sogou", accuracy_sogou_avg);
                mapparam.put("fluency_sogou", fluency_sogou_avg);
                mapparam.put("integrity_sogou", integrity_sogou_avg);
                mapparam.put("overall_sogou", overall_sogou_avg);
                
                LstStudentZuoye lstStudentZuoye = lstStudentZuoyeDao.selectByParams(mapparam);
                if(new Date().getTime() < lstStudentZuoye.getEnd_time().getTime()){
                    //按时提交
                    mapparam.put("tj_status", 2);
                }else{
                    //延时提交
                    mapparam.put("tj_status", 3);
                }
                mapparam.put("tj_tme", new Date());
            }
            int zuoye_end_num = 0;
            for(LstZuoyeQuestion l : list){
                Boolean b = true;
                for(LstZuoyeStudentAnswer answer :l.getAnswer_list()){
                    if(StringUtils.isEmpty(answer.getStudent_answer())){
                        b = false;
                        break;
                    }
                }
                if(b){
                    zuoye_end_num++;
                }else{
                    break;
                }
            }
            mapparam.put("zuoye_end_num", zuoye_end_num);
            lstStudentZuoyeDao.updateByStudentidZuoyeidAndClassCode(mapparam);
            map.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message","系统错误，请联系系统管理员");
        }
        return map;
    }

    /**
     * 作业上传语音
     * @param param
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> sogouParseZuoye(QuestionVoiceParam param, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "请重新登录");
            return map;
        }
        if(StringUtils.isEmpty(param.getClass_code())){
            map.put("flag", false);
            map.put("message", "班号为空");
            return map;
        }
        
        if(param.getZuoye_id() == null){
            map.put("flag", false);
            map.put("message", "作业ID为空");
            return map;
        }
        
        log.info("作业语音上传进来了");
        System.out.println(System.getProperty("java.library.path"));
        long currentTime = System.currentTimeMillis();
        try {
            JSONObject jo = getJson(param);
            System.out.println(jo.toJSONString());
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
                    String newFileName = lsStudentAnswerPath + System.currentTimeMillis() + ".wav";
                    ossUtil.uploadFile(ossClient, bucketName, newFileName, multiFile);
                    InputStream is = multiFile.getInputStream();
                    //is.skip(44);
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
                        //os.write(resultYp);
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
                        Map<String,Object> parseMap =sendMessage(voiceArr.get(i), currentTime, seqNo, voiceArr.size());
                        if(i==voiceArr.size()-1){
                            boolean isJxCg = (boolean) parseMap.get("flag");
                            if(isJxCg){
                                QuestionParseResult parseResult = (QuestionParseResult) parseMap.get("parseResult");
                                parseResult.setFlePath(newFileName);
                                LstZuoyeStudentAnswer lstZuoyeStudentAnswer = new LstZuoyeStudentAnswer();
                                BigDecimal accuracy_sogou = new BigDecimal(parseResult.getResult().getAccuracy() == null ? parseResult.getResult().getOverall() : parseResult.getResult().getAccuracy()).divide(new BigDecimal(10));
                                lstZuoyeStudentAnswer.setAccuracy_sogou(accuracy_sogou);
                                BigDecimal fluency_sogou = new BigDecimal(parseResult.getResult().getFluency() == null ? parseResult.getResult().getOverall() : parseResult.getResult().getFluency()).divide(new BigDecimal(10));
                                lstZuoyeStudentAnswer.setFluency_sogou(fluency_sogou);
                                BigDecimal integrity_sogou = new BigDecimal(parseResult.getResult().getIntegrity() == null ? parseResult.getResult().getOverall() : parseResult.getResult().getIntegrity()).divide(new BigDecimal(10));
                                lstZuoyeStudentAnswer.setIntegrity_sogou(integrity_sogou);
                                BigDecimal overall_sogou = new BigDecimal(parseResult.getResult().getOverall()).divide(new BigDecimal(10));
                                lstZuoyeStudentAnswer.setOverall_sogou(overall_sogou);
                                
                                lstZuoyeStudentAnswer.setStudent_id(studentUser.getId());
                                lstZuoyeStudentAnswer.setClass_code(param.getClass_code());
                                lstZuoyeStudentAnswer.setZuoye_id(param.getZuoye_id());
                                lstZuoyeStudentAnswer.setQuestion_id(param.getQuestion_id());
                                
                                lstZuoyeStudentAnswer.setStudent_answer(parseResult.getFlePath());
                                lstZuoyeStudentAnswerDao.updateByStudentidClasscodeZuoyeidAndQuestionid(lstZuoyeStudentAnswer);
                                
                                Map<String, Object> mapparam = new HashMap<String, Object>();
                                mapparam.put("student_id", studentUser.getId());
                                mapparam.put("zuoye_id", param.getZuoye_id());
                                mapparam.put("class_code", param.getClass_code());
                                List<LstZuoyeQuestion> list = lstZuoyeQuestionDao.selectStudentQuestion(mapparam);
                                if(("1").equals(param.getIsEnd())){
                                    //计算平均分，并更新进lst_student_zuoye表
                                    
                                    //获取所有分数
                                    BigDecimal accuracy_sogou_avg = new BigDecimal("0");
                                    BigDecimal fluency_sogou_avg = new BigDecimal("0");
                                    BigDecimal integrity_sogou_avg = new BigDecimal("0");
                                    BigDecimal overall_sogou_avg = new BigDecimal("0");
                                    BigDecimal accuracy_sogou_all = new BigDecimal("0");
                                    BigDecimal fluency_sogou_all = new BigDecimal("0");
                                    BigDecimal integrity_sogou_all = new BigDecimal("0");
                                    BigDecimal overall_sogou_all = new BigDecimal("0");
                                    for(LstZuoyeQuestion l : list){
                                        BigDecimal accuracy_sogou_all1 = new BigDecimal("0");
                                        BigDecimal fluency_sogou_all1 = new BigDecimal("0");
                                        BigDecimal integrity_sogou_all1 = new BigDecimal("0");
                                        BigDecimal overall_sogou_all1 = new BigDecimal("0");
                                        for(LstZuoyeStudentAnswer answer :l.getAnswer_list()){
                                            accuracy_sogou_all1 = accuracy_sogou_all1.add(answer.getAccuracy_sogou());
                                            fluency_sogou_all1 = fluency_sogou_all1.add(answer.getFluency_sogou());
                                            integrity_sogou_all1 = integrity_sogou_all1.add(answer.getIntegrity_sogou());
                                            overall_sogou_all1 = overall_sogou_all1.add(answer.getOverall_sogou());
                                        }
                                        accuracy_sogou_all = accuracy_sogou_all.add(accuracy_sogou_all1.divide(new BigDecimal(l.getAnswer_list().size()), 2, BigDecimal.ROUND_HALF_UP));
                                        fluency_sogou_all = fluency_sogou_all.add(fluency_sogou_all1.divide(new BigDecimal(l.getAnswer_list().size()), 2, BigDecimal.ROUND_HALF_UP));
                                        integrity_sogou_all = integrity_sogou_all.add(integrity_sogou_all1.divide(new BigDecimal(l.getAnswer_list().size()), 2, BigDecimal.ROUND_HALF_UP));
                                        overall_sogou_all = overall_sogou_all.add(overall_sogou_all1.divide(new BigDecimal(l.getAnswer_list().size()), 2, BigDecimal.ROUND_HALF_UP));
                                    }
                                    BigDecimal listsizeb = new BigDecimal(list.size());
                                    accuracy_sogou_avg = accuracy_sogou_all.divide(listsizeb, 2, BigDecimal.ROUND_HALF_UP);
                                    fluency_sogou_avg = fluency_sogou_all.divide(listsizeb, 2, BigDecimal.ROUND_HALF_UP);
                                    integrity_sogou_avg = integrity_sogou_all.divide(listsizeb, 2, BigDecimal.ROUND_HALF_UP);
                                    overall_sogou_avg = overall_sogou_all.divide(listsizeb, 2, BigDecimal.ROUND_HALF_UP);
                                    
                                    mapparam.put("accuracy_sogou", accuracy_sogou_avg);
                                    mapparam.put("fluency_sogou", fluency_sogou_avg);
                                    mapparam.put("integrity_sogou", integrity_sogou_avg);
                                    mapparam.put("overall_sogou", overall_sogou_avg);
                                    
                                    LstStudentZuoye lstStudentZuoye = lstStudentZuoyeDao.selectByParams(mapparam);
                                    if(new Date().getTime() < lstStudentZuoye.getEnd_time().getTime()){
                                        //按时提交
                                        mapparam.put("tj_status", 2);
                                    }else{
                                        //延时提交
                                        mapparam.put("tj_status", 3);
                                    }
                                    mapparam.put("tj_tme", new Date());
                                }
                                int zuoye_end_num = 0;
                                for(LstZuoyeQuestion l : list){
                                    Boolean b = true;
                                    for(LstZuoyeStudentAnswer answer :l.getAnswer_list()){
                                        if(StringUtils.isEmpty(answer.getStudent_answer())){
                                            b = false;
                                            break;
                                        }
                                    }
                                    if(b){
                                        zuoye_end_num++;
                                    }else{
                                        break;
                                    }
                                }
                                mapparam.put("zuoye_end_num", zuoye_end_num);
                                lstStudentZuoyeDao.updateByStudentidZuoyeidAndClassCode(mapparam);
                                map.put("flag", true);
                                
                            }else{
                                map.put("flag", false);
                                map.put("message", "语音上传失败");
                            }
                        }
                    }
                }
            }
            log.info("语音上传上传结束");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "语音上传异常："+e);
            log.info("语音上传异常："+e);
        }
        return map;
    }
    
    private Map<String,Object> sendMessage(byte[] voiceSentence, long currentTime, int seqNo, int voiceLength) {
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
                log.info("sequenceNo:" + seqNo + ",replycontent:" + replyContent);
                if(seqNo<0){
                    QuestionParseResult parseResult = JSON.parseObject(replyContent, QuestionParseResult.class);
                    result.put("flag", true);
                    result.put("parseResult", parseResult);
                }
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

    private JSONObject getJson(QuestionVoiceParam param) {
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

    private byte[] addByteArr(byte[] src1, byte[] src2) {
        byte[] dest = new byte[src1.length + src2.length];
        System.arraycopy(src1, 0, dest, 0, src1.length);
        System.arraycopy(src2, 0, dest, src1.length, src2.length);
        return dest;
    }

}
