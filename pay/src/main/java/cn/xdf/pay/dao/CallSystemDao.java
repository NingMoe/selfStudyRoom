package cn.xdf.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.xdf.pay.domain.CallSystem;

@Mapper
public interface CallSystemDao {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(CallSystem record);

    CallSystem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CallSystem record);
    
    /**
     * 通过appId及appKey查找调用系统
     * @param callSystem
     * @return
     */
    CallSystem selectByCallSystem(CallSystem callSystem);

}