package com.human.sign.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.sign.entity.SignInfo;
import com.human.sign.entity.SignInfoDto;

@Repository
public interface SignInfoDao {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(SignInfo record);

    SignInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SignInfo record);
    
    /**
     * 分页查询
     * @param map
     * @return
     */
    List<SignInfo>query(Map<Object, Object> map);
    
    /**
     * 删除签到人员(逻辑删除)
     * @param paraMap
     * @return
     */
    int updateStatusByIds(Map<String, Object> paraMap);
    
    /**
     * 批量插入签到人员
     * @param list
     */
    void insertByBatch(List<SignInfo> list);
    
    /**
     * 导出签到人员明细
     * @param activityId
     * @return
     */
    List<Map<String,Object>> exportSelectInfo(Long activityId);
    
    /**
     * 通过手机号检查对应的签到人员
     * @param record
     * @return
     */
    SignInfo checkSignInfo(SignInfo record);
    
    /**
     * 查询转化人次
     * @param activityId
     * @return
     */
    List<String> getClassCodeList(Long activityId);
    
    /**
     * 撤销签到
     * @param id
     * @return
     */
    int updateIsSign(Long id);
    
    /**
     * 通过手机号码或者身份证号码后四位查询
     * @param paraMap
     * @return
     */
    List<SignInfo> selectByPamas(Map<String, Object> paraMap);
    
    /**
     * 通过id批量查询
     * @param map
     * @return
     */
    List<SignInfo> selectByIds(Map<String, Object> paraMap);
    
    /**
     * 按部门分组统计各个部门的人数
     * @param activityId
     * @return
     */
    List<SignInfoDto> selectInfoGroupByDept(Long activityId);
    
    /**
     * 统计部门的签到人数
     * @param paraMap
     * @return
     */
    long selectHasSignGroupByDept(Map<String, Object> paraMap);
    
    /**
     * 查询部门的签到明细
     * @param record
     * @return
     */
    List<SignInfo> selectDeptSignDetails(SignInfo record);
    
    /**
     * 通过活动ID查询所有的签到人员
     * @param activityId
     * @return
     */
    List<SignInfo> selectByActivityId(Long activityId);

}