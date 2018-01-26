package com.human.activity.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.activity.entity.BuyerInfo;
import com.human.activity.entity.BuyerInfoDto;
import com.human.activity.entity.PayInfoDto;

@Repository
public interface BuyerInfoDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(BuyerInfo record);

    BuyerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerInfo record);
    
    /**
     * 分页查询
     * @param map
     * @return
     */
    List<BuyerInfoDto>query(Map<Object, Object> map);
    
    /**
     * 通过订单号查询
     * @param orderNo
     * @return
     */
    BuyerInfo selectByOrderNo(String orderNo);
    
    /**
     * 通过活动Id及手机号码查询
     * @param record
     * @return
     */
    int selectByParams(BuyerInfo record);
    
    /**
     * 通过退款单Id查询
     * @param id
     * @return
     */
    BuyerInfo selectByOrderRefundId(long id);
    
    /**
     * 通过姓名及手机号查询
     * @param record
     * @return
     */
    int selectByNameAndPhone(BuyerInfo record);
    
    /**
     * 查询我的卡卷
     * @param record
     * @return
     */
    List<BuyerInfoDto> selectMyCard(BuyerInfo record);
    
    /**
     * 导出支付明细
     */
    List<Map<String,Object>> exportSelectPayInfo(Long activityId);
    
    /**
     * 根据订单Id查询支付成功发送短信需要的信息
     * @param id
     * @return
     */
    PayInfoDto sendPaySuccessMessage(long id);
}