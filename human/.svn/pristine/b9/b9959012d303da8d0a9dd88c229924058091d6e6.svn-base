package com.human.utils;

import java.net.URLEncoder;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.human.recruitment.dao.HrResumeFlowDao;
import com.human.recruitment.entity.HrResumeFlow;

@Component
public class SecurityHelper {
    protected final static Log logger = LogFactory.getLog(SecurityHelper.class);
    
    @Value("${resumeUrlPre}")
    private String resumeUrlPre;
    
    @Value("${urlPreKey}")
    private String urlPreKey;
    
    @Autowired
    private HrResumeFlowDao flowDao;
    // 加密深度
    private final static int ITERATIONS = 20;

    public static String encrypt(String key, String plainText) throws Exception {
        try {
            byte[] salt = new byte[8];
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(key.getBytes());
            byte[] digest = md.digest();
            for (int i = 0; i < 8; i++) {
                salt[i] = digest[i];
            }
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey skey = keyFactory.generateSecret(pbeKeySpec);
            PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes());
            String saltString = new String(Base64.encode(salt));
            String ciphertextString = new String(Base64.encode(cipherText));
            return saltString + ciphertextString;
        }
        catch (Exception e) {
            throw new Exception("Encrypt Text Error:" + e.getMessage(), e);
        }
    }

    public static String decrypt(String key, String encryptTxt) throws Exception {
        int saltLength = 12;
        try {
            String salt = encryptTxt.substring(0, saltLength);
            String ciphertext = encryptTxt.substring(saltLength, encryptTxt.length());
            byte[] saltarray = Base64.decode(salt.getBytes());
            byte[] ciphertextArray = Base64.decode(ciphertext.getBytes());
            PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey skey = keyFactory.generateSecret(keySpec);
            PBEParameterSpec paramSpec = new PBEParameterSpec(saltarray, ITERATIONS);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);
            byte[] plaintextArray = cipher.doFinal(ciphertextArray);
            return new String(plaintextArray);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    /**
     * 创建简历详情免登陆URL链接
     * @param resumeId 简历ID
     * @param flowCode 流程节点Code
     * @param operUser  操作人用户名
     * @return
     */
    public  String CreateResumeUrl(String flowCode,String operUser) {
        System.out.println(flowCode+"----"+operUser);
        HrResumeFlow flow = flowDao.selectByFlowCode(flowCode);
        String encryptTxt = "";
        String url=resumeUrlPre+"free/resume/jdDesc.html?id=${resumeId}&flowCode=${flowCode}&userName=${operUser}";
        try {
         encryptTxt = URLEncoder.encode(encrypt(urlPreKey, String.valueOf(flow.getResumeId())),"UTF-8");
         url=url.replace("${resumeId}", encryptTxt);
         encryptTxt = URLEncoder.encode(encrypt(urlPreKey, flowCode),"UTF-8");
         url=url.replace("${flowCode}", encryptTxt);
         encryptTxt = URLEncoder.encode(encrypt(urlPreKey, operUser),"UTF-8");
         url= url.replace("${operUser}", encryptTxt);
         System.out.println(url);
        } catch (Exception e) {
         e.printStackTrace();
         System.exit(-1);
        }
        return url;
      }
    
    /**
     * 创建简历详情免登陆URL链接
     * @param resumeId 简历ID
     * @param flowCode 流程节点Code
     * @param operUser  操作人用户名
     * @return
     */
    public  String CreateAcceptOfferUrl(Integer id,String seekerName) {
        String url = "";
        try {
         String id1 = URLEncoder.encode(encrypt(urlPreKey, String.valueOf(id)),"UTF-8");
         String name = URLEncoder.encode(encrypt(urlPreKey,seekerName),"UTF-8");
         url = resumeUrlPre+ "free/entry/toSeekerDetail.html?id="+id1+"&seekerName="+name;
         System.out.println(url);
        } catch (Exception e) {
         e.printStackTrace();
         System.exit(-1);
        }
        return url;
      }
    
    /**
     * 创建简历详情免登陆URL链接
     * @param resumeId 简历ID
     * @param flowCode 流程节点Code
     * @param operUser  操作人用户名
     * @return
     */
    public  String CreateRefuseOfferUrl(Integer id) {
        String url = "";
        try {
         String id1 = URLEncoder.encode(encrypt(urlPreKey, String.valueOf(id)),"UTF-8");
         url = resumeUrlPre+ "free/entry/toRefuse.html?id="+id1;
         System.out.println(url);
        } catch (Exception e) {
         e.printStackTrace();
         System.exit(-1);
        }
        return url;
      }
    
    /**
     * 创建校长信箱反馈列表免登陆
     * @param userName
     * @return
     */
    public  String CreateFeedBackListURL(String userName) {
        String encryptTxt = "";
        String url=resumeUrlPre+"free/feedBack/hfFeedback.html?userName=${userName}";
        try {
         encryptTxt = URLEncoder.encode(encrypt(urlPreKey, userName),"UTF-8");
         url=url.replace("${userName}", encryptTxt);
         System.out.println(url);
        } catch (Exception e) {
         e.printStackTrace();
         System.exit(-1);
        }
        return url;
      }
    
    
    /**
     * 创建校长信箱反馈列表免登陆
     * @param userName
     * @return
     */
    public  String CreateFeedBackDetailURL(String userName,Long id) {
        String encryptTxt = "";
        String url=resumeUrlPre+"free/feedBack/feedBackOperDetail.html?userName=${userName}&id="+id;
        try {
         encryptTxt = URLEncoder.encode(encrypt(urlPreKey, userName),"UTF-8");
         url=url.replace("${userName}", encryptTxt);
         System.out.println(url);
        } catch (Exception e) {
         e.printStackTrace();
         System.exit(-1);
        }
        return url;
      }
    
    /**
     * 创建用户查看反馈详情列表
     * @param userName
     * @return
     */
    public  String CreateUserFeedBackDetailURL(String userName,Long id) {
        String encryptTxt = "";
        String url=resumeUrlPre+"free/feedBack/feedBackDetail.html?userName=${userName}&id="+id;
        try {
         encryptTxt = URLEncoder.encode(encrypt(urlPreKey, userName),"UTF-8");
         url=url.replace("${userName}", encryptTxt);
         System.out.println(url);
        } catch (Exception e) {
         e.printStackTrace();
         System.exit(-1);
        }
        return url;
      }
}