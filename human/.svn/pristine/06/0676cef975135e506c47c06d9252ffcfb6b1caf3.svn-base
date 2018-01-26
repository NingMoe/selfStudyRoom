package com.human.rank.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.rank.entity.RankInfo;
import com.human.rank.service.RankInfoService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/teacher/rankinfo")
public class RankInfoController {
    
    @Resource
    private RankInfoService rankInfoService;
    
    /**
     * 
     * @param requert
     * @param respones
     * @return
     */
    @RequestMapping(value="/infoview")
    public ModelAndView infoview(HttpServletRequest requert, HttpServletResponse respones){
        return new ModelAndView("/rank/rankinfo");
    }
    
    /**
     * 规则新增页
     * @param requert
     * @param respones
     * @return
     */
    @RequestMapping(value="/addview")
    public ModelAndView addview(HttpServletRequest requert, HttpServletResponse respones){
        return new ModelAndView("/rank/addrankinfo");
    }
    
    /**
     * 规则修改页
     * @param requert
     * @param respones
     * @return
     */
    @RequestMapping(value="/changeview")
    public ModelAndView changeview(HttpServletRequest requert, HttpServletResponse respones){
        return new ModelAndView("/rank/changerankinfo");
    }
    
    /**
     * 分页获取排行规则
     * @param page
     * @param rankInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView page, RankInfo rankInfo){
        return rankInfoService.query(page, rankInfo);
    }
    
    /**
     * 新增排行榜规则
     * @param rankInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insert")
    public Map<String, Object> insert(RankInfo rankInfo, HttpServletRequest request){
        return rankInfoService.insert(rankInfo, request);
    }
    
    /**
     * 查询排行榜规则
     * @param rankInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/select")
    public Map<String, Object> select(RankInfo rankInfo){
        return rankInfoService.select(rankInfo);
    }

    /**
     * 修改排行榜规则
     * @param rankInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/update")
    public Map<String, Object> update(RankInfo rankInfo, HttpServletRequest request){
        return rankInfoService.update(rankInfo, request);
    }
    
    /**
     * 修改排行榜是否禁用
     * @param rankInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updatevalid")
    public Map<String, Object> updatevalid(RankInfo rankInfo){
        return rankInfoService.updatevalid(rankInfo);
    }
    
    /**
     * 删除排行规则
     * @param rankInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete")
    public Map<String, Object> delete(RankInfo rankInfo){
        return rankInfoService.delete(rankInfo);
    }
}
