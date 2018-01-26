package com.human.rank.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.rank.service.RankClassesService;
import com.human.rank.service.RankInfoService;

@Controller
@RequestMapping(value="/user/rankclasses")
public class UserClassesController {
    
    @Resource
    private RankInfoService rankInfoService;
    
    @Resource
    private RankClassesService rankClassesService;
    
    @RequestMapping(value="/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = rankInfoService.getRankInfo(request);
        String url = "/rank/error";
        if((boolean) map.get("flag")){
            url = "/rank/userrankclasses";
        }
        ModelAndView mav = new ModelAndView(url);
        mav.addAllObjects(map);
        return mav;
    }
    
    /**
     * 页面刷新获取班级信息
     * @param rankClasses
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/select")
    public Map<String, Object> select(Integer id, Integer rank_num, Integer rank_lastnum){
        return rankClassesService.selectclasses(id, rank_num, rank_lastnum);
    }
}
