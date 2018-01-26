package com.human.rank.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.rank.entity.RankInfo;
import com.human.utils.PageView;

public interface RankInfoService {

    /**
     * 分页获取排行规则
     * @param page
     * @param rankInfo
     * @return
     */
    public PageView query(PageView page, RankInfo rankInfo);

    /**
     * 新增排行规则
     * @param rankInfo
     * @return
     */
    public Map<String, Object> insert(RankInfo rankInfo, HttpServletRequest request);

    /**
     * 查询排行规则
     * @param rankInfo
     * @return
     */
    public Map<String, Object> select(RankInfo rankInfo);

    /**
     * 修改排行规则
     * @param rankInfo
     * @return
     */
    public Map<String, Object> update(RankInfo rankInfo, HttpServletRequest request);
    
    /**
     * 修改排行榜是否禁用
     * @param rankInfo
     * @return
     */
    public Map<String, Object> updatevalid(RankInfo rankInfo);

    /**
     * 删除排行规则
     * @param rankInfo
     * @return
     */
    public Map<String, Object> delete(RankInfo rankInfo);

    /**
     * 前端获取排行榜配置
     * @param request
     * @return
     */
    public Map<String, Object> getRankInfo(HttpServletRequest request);
}
