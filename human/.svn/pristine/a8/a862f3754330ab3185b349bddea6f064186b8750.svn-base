package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.human.manager.entity.HomeSearchBean;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.NoAccDegree;
import com.human.recruitment.entity.PositionAlias;
import com.human.recruitment.entity.PositionHighLight;
import com.human.recruitment.entity.PositionHrUser;
import com.human.recruitment.entity.PositionJobCity;
import com.human.recruitment.entity.PositionMsUser;
import com.human.recruitment.entity.PositionWatcher;

@Repository
public interface HrPositionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HrPosition record);
    
    int insertJobCitys(List<PositionJobCity> jobCitys);
    
    int deleteJobCityByPositionId(Integer positionId);
    
    int deleteHighLightByPositionId(Integer positionId);
    
    int deleteAliasByPositionId(Integer positionId);
    
    int deleteNoaccDegreesByPositionId(Integer positionId);

    int insertNoaccDegrees(List<NoAccDegree> degrees);
    
    int insertHighLight(List<PositionHighLight> highLights);
    
    int insertPositionAlias(List<PositionAlias> aliases);
    
    HrPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrPosition record);

    List<HrPosition> selectPositionPage(Map<Object,Object> map);
    
    HrPosition selectDetailById(Integer id);
    
    int insertPositionHrUser(PositionHrUser hrUser);
    
    int deleteHrUser(PositionHrUser hrUser);
    
    int insertPositionWatcher(PositionWatcher watcher);
    
    int deleteWatcher(PositionWatcher watcher);
    
    int insertPositionMsUser(PositionMsUser msUser);
    
    int deleteMsUser(PositionMsUser msUser);
    
    List<PositionHrUser> getPositionHrUsers(Integer positionId);
    
    List<PositionWatcher> getPositionWatchers(Integer positionId);
    
    List<PositionMsUser> getPositionMsUsers(Integer positionId);
    
    List<PositionAlias> getPositionAliases(Integer positionId);
    
    List<NoAccDegree> getPositionNogrees(Integer positionId);
    
    PositionHrUser getHrUserByUserId(PositionHrUser hrUser);
    
    PositionWatcher getWatcherByWatcherId(PositionWatcher watcher);
    
    PositionMsUser getMsUserByMsId(PositionMsUser msUser);
    
    /**
     * 根据应聘职位匹配流程职位
     * @param recruiMailId
     * @param applyPosition
     * @return
     */
    Map<String,String> getPositionId(@Param("recruitMailId")String recruitMailId,@Param("applyPosition")String applyPosition);
    
    /**
     * 用于前端的查询DAO
     */
    List<HrPosition> getPositionForFrontByCompany(String companyId);
    
    
    /**
     * 用于前端的查询DAO
     */
    HrPosition getPositionForFrontById(Integer id);
    
    /**
     * 查找可利用的职位
     * @return
     */
    List<HrPosition> getValidPositionList(HrPosition position);
    /**
     * 查询我有权限查看的已配置流程的职位
     * @param userId
     * @return
     */
    List<Map<String,Object>> getMyPosition(HomeSearchBean  bean);
    
}