package com.human.rank.serviceImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aliyun.oss.OSSClient;
import com.human.rank.dao.RankInfoDao;
import com.human.rank.entity.RankInfo;
import com.human.rank.service.RankInfoService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Service
public class RankInfoServiceImpl implements RankInfoService {
    
    @Resource
    private RankInfoDao rankInfoDao;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.rankPath}")
    private  String rankPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;

    /**
     * 分页获取排行规则
     * @param page
     * @param rankInfo
     * @return
     */
    public PageView query(PageView page, RankInfo rankInfo) {
        //验证参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
          //获取用户权限范围
            MyUser myUser = Common.getMyUser();
            String school_id = myUser.getCompanyId();
            rankInfo.setSchool_id(school_id);
            
            map.put("paging", page); 
            map.put("t", rankInfo);
            
            List<RankInfo> list = rankInfoDao.query(map);
            page.setRecords(list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }

    /**
     * 新增排行规则
     * @param rankInfo
     * @return
     */
    public Map<String, Object> insert(RankInfo rankInfo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(rankInfo == null){
            map.put("flag", false);
            map.put("message", "请填写规则名称。");
            return map;
        }
        
        if(StringUtils.isEmpty(rankInfo.getRank_name())){
            map.put("flag", false);
            map.put("message", "请填写规则名称。");
            return map;
        }
        
        if(StringUtils.isEmpty(rankInfo.getRank_name())){
            map.put("flag", false);
            map.put("message", "请填写规则名称。");
            return map;
        }
        
        if(rankInfo.getRanke_num() == null){
            map.put("flag", false);
            map.put("message", "请填写显示条数");
            return map;
        }
        
        if(rankInfo.getRanke_lastnum() == null){
            map.put("flag", false);
            map.put("message", "请填写上榜班级剩余人数");
            return map;
        }
        
        if(rankInfo.getRefresh_time() == null){
            map.put("flag", false);
            map.put("message", "请填写刷新时间");
            return map;
        }
        
        OSSClient ossClient = ossUtil.getClient();
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                String fileName = iter.next();
                // 取得上传文件
                MultipartFile multiFile = multiRequest.getFile(fileName);
                String originalName = multiFile.getOriginalFilename();
                if("".equals(originalName)||originalName==null){
                    continue;
                }
                String newFileName = rankPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                Map<String,Object> uploadResult = ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                if((boolean) uploadResult.get("flag")){
                    if("fileA".equals(fileName)){
                        //封装参数
                        rankInfo.setHead_img(newFileName);
                    }
                    
                    if("fileB".equals(fileName)){
                        //封装参数
                        rankInfo.setFoot_img(newFileName);
                    }
                }else{
                    map.put("flag", false);
                    map.put("message", "图片上传失败");
                    return map;
                }
            }
        }
        
        try {
          //获取用户权限范围
            MyUser myUser = Common.getMyUser();
            String school_id = myUser.getCompanyId();
            String email_addr = myUser.getEmailAddr();
            rankInfo.setSchool_id(school_id);
            rankInfo.setCreate_user(email_addr);
            rankInfoDao.insertSelective(rankInfo);
            map.put("flag", true);
            map.put("message", "上传成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增规则异常:"+e);
        }
        
        return map;
    }

    /**
     * 查询排行规则
     * @param rankInfo
     * @return
     */
    public Map<String, Object> select(RankInfo rankInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(rankInfo == null){
            map.put("flag", false);
            map.put("message", "请选择要查询的规则");
            return map;
        }
        
        if(rankInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要查询的规则");
            return map;
        }
        
        try {
            RankInfo rankInfos = rankInfoDao.selectByPrimaryKey(rankInfo.getId());
            if(rankInfos == null){
                map.put("flag", false);
                map.put("message", "查询结果为空");
            }else{
                map.put("flag", true);
                map.put("message", "查询成功");
                map.put("rankInfo", rankInfos);
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询规则异常：" + e);
        }
        
        return map;
    }

    /**
     * 修改排行规则
     * @param rankInfo
     * @return
     */
    public Map<String, Object> update(RankInfo rankInfo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(rankInfo == null){
            map.put("flag", false);
            map.put("message", "请选择要修改的规则");
            return map;
        }
        
        if(rankInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要修改的规则");
            return map;
        }
        
        if(StringUtils.isEmpty(rankInfo.getRank_name())){
            map.put("flag", false);
            map.put("message", "请填写规则名称。");
            return map;
        }
        
        if(StringUtils.isEmpty(rankInfo.getRank_name())){
            map.put("flag", false);
            map.put("message", "请填写规则名称。");
            return map;
        }
        
        if(rankInfo.getRanke_num() == null){
            map.put("flag", false);
            map.put("message", "请填写显示条数");
            return map;
        }
        
        if(rankInfo.getRanke_lastnum() == null){
            map.put("flag", false);
            map.put("message", "请填写上榜班级剩余人数");
            return map;
        }
        
        if(rankInfo.getRefresh_time() == null){
            map.put("flag", false);
            map.put("message", "请填写刷新时间");
            return map;
        }
        
        OSSClient ossClient = ossUtil.getClient();
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                String fileName = iter.next();
                // 取得上传文件
                MultipartFile multiFile = multiRequest.getFile(fileName);
                String originalName = multiFile.getOriginalFilename();
                if("".equals(originalName)||originalName==null){
                    continue;
                }
                String newFileName = rankPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                Map<String,Object> uploadResult = ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                if((boolean) uploadResult.get("flag")){
                    RankInfo rankInfos = rankInfoDao.selectByPrimaryKey(rankInfo.getId());
                    if("fileA".equals(fileName)){
                        if(rankInfos != null && StringUtils.isNotEmpty(rankInfos.getHead_img())){
                            ossUtil.deleteObject(ossClient, bucketName, rankInfos.getHead_img());
                        }
                        rankInfo.setHead_img(newFileName);
                    }
                    
                    if("fileB".equals(fileName)){
                        if(rankInfos != null && StringUtils.isNotEmpty(rankInfos.getFoot_img())){
                            ossUtil.deleteObject(ossClient, bucketName, rankInfos.getHead_img());
                        }
                        rankInfo.setFoot_img(newFileName);
                    }
                }else{
                    map.put("flag", false);
                    map.put("message", "图片上传失败");
                    return map;
                }
            }
        }
        
        try {
            rankInfoDao.updateByPrimaryKeySelective(rankInfo);
            map.put("flag", true);
            map.put("message", "修改成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改规则异常：" + e);
        }
        
        return map;
    }
    
    /**
     * 修改排行榜是否禁用
     * @param rankInfo
     * @return
     */
    public Map<String, Object> updatevalid(RankInfo rankInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(rankInfo == null){
            map.put("flag", false);
            map.put("message", "请选择要修改的规则");
            return map;
        }
        
        if(rankInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要修改的规则");
            return map;
        }
        
        if(rankInfo.getIs_valid() == null){
            map.put("flag", false);
            map.put("message", "请选择是否禁用");
            return map;
        }
        
        try {
            rankInfoDao.updateByPrimaryKeySelective(rankInfo);
            map.put("flag", true);
            map.put("message", "修改成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改禁用规则异常：" + e);
        }
        return map;
    }

    /**
     * 删除排行规则
     * @param rankInfo
     * @return
     */
    public Map<String, Object> delete(RankInfo rankInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(rankInfo == null){
            map.put("flag", false);
            map.put("message", "请选择要删除的规则");
            return map;
        }
        
        if(rankInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要删除的规则");
            return map;
        }
        
        try {
            OSSClient ossClient = ossUtil.getClient();
            RankInfo rankInfos = rankInfoDao.selectByPrimaryKey(rankInfo.getId());
            if(rankInfos != null && StringUtils.isNotEmpty(rankInfos.getHead_img())){
                ossUtil.deleteObject(ossClient, bucketName, rankInfos.getHead_img());
            }
        
            if(rankInfos != null && StringUtils.isNotEmpty(rankInfos.getFoot_img())){
                ossUtil.deleteObject(ossClient, bucketName, rankInfos.getFoot_img());
            }
            rankInfoDao.deleteByPrimaryKey(rankInfo.getId());
            map.put("flag", true);
            map.put("message", "删除成功");
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除规则异常：" + e);
        }
        
        return map;
    }

    /**
     * 前端获取排行榜配置
     * @param request
     * @return
     */
    public Map<String, Object> getRankInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String rankinfo_id = request.getParameter("id");
        
        //验证参数
        if(StringUtils.isEmpty(rankinfo_id)){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        try {
            Integer id = Integer.valueOf(rankinfo_id);
            RankInfo rankInfos = rankInfoDao.selectByPrimaryKey(id);
            if(rankInfos == null){
                map.put("flag", false);
                map.put("message", "查询结果为空");
            }else if(rankInfos.getIs_valid() != null && rankInfos.getIs_valid() == 0){
                map.put("flag", false);
                map.put("message", "链接已失效：已被禁用");
            }else if(rankInfos.getIs_valid() != null && rankInfos.getIs_valid() == 1){
                map.put("flag", true);
                map.put("message", "查询成功");
                map.put("rankInfo", rankInfos);
            }else{
                map.put("flag", false);
                map.put("message", "链接异常");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询规则异常：" + e);
        }
        return map;
    }
}