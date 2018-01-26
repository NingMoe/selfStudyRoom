package com.human.jzbTest.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.human.jzbTest.dao.JzbMainConfigDao;
import com.human.jzbTest.entity.JzbMainConfig;
import com.human.jzbTest.service.JzbMainConfigService;
import com.human.manager.dao.UserDeptDao;
import com.human.utils.Common;
import com.human.utils.OSSUtil;

@Service
public class JzbMainConfigServiceImpl implements JzbMainConfigService {

    @Resource
    private UserDeptDao userDeptDao;
    
    @Resource
    private JzbMainConfigDao  configDao;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.jzbMainConfig.header}")
    private String headerPath;

    @Resource 
    private OSSUtil ossUtil;

    @Override
    public JzbMainConfig selectByCompanyId(String companyId) {       
        return configDao.selectByCompanyId(companyId);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> saveConfig(HttpServletRequest request,JzbMainConfig jmf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)){
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                String fileName = "photo";
                // 取得上传文件
                MultipartFile multiFile = multiRequest.getFile(fileName);
                if (null != multiFile && multiFile.getSize() > 0) {
                    String originalName = multiFile.getOriginalFilename();
                    if (!"".equals(originalName) && originalName != null) {
                        // 上传首页图
                        String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                        Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, multiFile);
                        if ((boolean) uploadResult.get("flag")) {
                            jmf.setHeadImg(newFileName);
                        }else {
                            map.put("flag", false);
                            map.put("msg", "首页图上传失败,请稍后重试!");
                            return map;
                        }
                    }
                }
                if (jmf.getId() == null) {
                    jmf.setCompanyId(Common.getMyUser().getCompanyId());
                    jmf.setCreateUser(Common.getMyUser().getUsername());
                    jmf.setCreateTime(new Date());
                    int id=configDao.insertSelective(jmf);
                    map.put("id", id);
                }else {
                    jmf.setUpdateUser(Common.getMyUser().getUsername());
                    jmf.setUpdateTime(new Date());
                    configDao.updateByPrimaryKeySelective(jmf);
                    map.put("id", jmf.getId());
                }
                map.put("flag", true);
                map.put("message", "保存首页配置成功!");
            }else{
                map.put("flag", false);
                map.put("message", "保存首页配置失败!"); 
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "保存首页配置失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
}
