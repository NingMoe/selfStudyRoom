package com.human.questionnaire.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.human.questionnaire.entity.DisableIP;
import com.human.questionnaire.entity.FormParam;
import com.human.questionnaire.entity.ParamBean;
import com.human.questionnaire.entity.Qform;

@Repository
public interface QformDao {

    List<ParamBean> query(Map<Object, Object> map);

    int insert(Qform bean);

    int insertFormParam(FormParam fp);

    List<Qform> queryNoPage(Qform bean);

    int updateForm(Qform bean);

    void deleteFormParam(Qform bean);

    List<DisableIP> queryDisableIP(DisableIP bean);

    List<DisableIP> queryDisableIpList(Map<String, Object> map);
    
    int delIpById(Map<String, Object> map);

    int addDisableIp(DisableIP bean);

    List<Map<String, Object>> queryResult(Long id);

    void deleteFormById(@Param("ids")String[] ids);

    void delCollect(@Param("ids")String[] ids);
    
}
