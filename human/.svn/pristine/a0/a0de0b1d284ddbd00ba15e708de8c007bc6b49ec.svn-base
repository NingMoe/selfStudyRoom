package com.human.sign.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.human.sign.entity.SignInfo;
import com.human.utils.PageView;

public interface SignInfoService {
    /**
     * 分页查询
     * @param pageView
     * @param info
     * @return
     */
    PageView querySignInfoByPage(PageView pageView,SignInfo info);
    
    /**
     * 新增签到人员
     * @param info
     * @return
     */
    Map<String, Object> addSignInfo(SignInfo info);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    SignInfo selectByPrimaryKey(Long id);
    
    /**
     * 编辑签到人员
     * @param jsonStrings
     * @param request
     * @return
     */
    Map<String, Object> editSignInfo(SignInfo info);
    
    /**
     * 删除签到人员
     * @param deleteIds
     * @return
     */
    Map<String, Object> updateStatus(String deleteIds);
    
    /**
     * 下载签到人员导入模板
     * @param request
     * @param response
     */
    void downLoadSignInfoExcel(HttpServletRequest request,HttpServletResponse response);
    
    /**
     * 上传签到人员数据
     * @param cf
     * @param multipartFile
     * @return
     */
    Map<String, Object> uploadLoadSignInfoExcel(Long activityId, MultipartFile multipartFile);
    
    /**
     * 导出签到人员数据
     * @param activityId
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportData(Long activityId, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 后台快速签到
     * @param activityId
     * @param telOrCard
     * @return
     */
    Map<String, Object> fastSign(SignInfo info);
    
    /**
     * 前台签到活动首页校验
     * @param activityId
     * @param telOrCardNo
     * @return
     */
    Map<String, Object> checkSignTime(Long activityId,String telOrCardNo);
    
    /**
     * 前台撤销签到
     * @param id
     * @return
     */
    Map<String, Object> revoke(Long id);
    
    /**
     * 通过id批量查询
     * @param ids
     * @return
     */
    List<SignInfo> selectByIds(String ids);
    
    /**
     * 确认签到(后4位重复)
     * @param id
     * @return
     */
    Map<String, Object> confirmMySign(Long id);
    
    /**
     * 查询某部门签到明细
     * @param info
     * @return
     */
    Map<String, Object> selectDeptSignDetails(SignInfo info);
    
    /**
     * 更新转化并处理相应逻辑
     * @param activityId
     * @return
     */
    Map<String, Object> updateAndChange(Long activityId);
    
}
