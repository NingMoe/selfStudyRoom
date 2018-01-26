package com.human.front.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.AreaInfo;
import com.human.basic.service.AreaInfoService;
import com.human.front.service.FrontService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.HrCompanyService;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.HrPositionService;
import com.human.utils.Constants;

@Controller
@RequestMapping(value = "/front/home/")
public class HomePageController {
    
    @Autowired
    private AreaInfoService areaInfoService;
    
    @Autowired
    private HrCompanyService companyService;
    
    @Autowired
    private HrPositionService positionService;
    
    @Autowired
    private FrontService frontService;
    
    private final  Logger logger = LogManager.getLogger(HomePageController.class);
    
    /**
     * 进入地区选择页面
     * @return
     */
    @RequestMapping(value="jump")
    public ModelAndView toJump(HttpServletRequest request,HttpServletResponse response){
        return new ModelAndView("/front/home/jump");
    }
    
    /**
     * 进入首页
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView toMain(HttpServletRequest request,String areaName){
        System.out.println();
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute(Constants.OPENID);
        String bindStatus = (String) session.getAttribute(Constants.BINDSTATUS);
        AreaInfo area = null;
        Object o = session.getAttribute("selectedArea");
        if(o!=null){
            area = (AreaInfo) o;
        }else{
            area = new AreaInfo();
            area.setAreaLevel(2);
            if(StringUtils.isNotEmpty(areaName)){
                area.setAreaName(areaName);
            }else{
                area.setAreaName("合肥市");
            }
            List<AreaInfo> areas = areaInfoService.getArea(area);
            if(areas!=null && areas.size()>0){
                area = areas.get(0);
            }else{
                area.setAreaName("合肥市");
                area = areaInfoService.getArea(area).get(0);
            }
        }
        HrCompany company = companyService.selectByCityId(area.getId());
        if(company==null){
            area = new AreaInfo();
            area.setAreaLevel(2);
            area.setAreaName("合肥市");
            area = areaInfoService.getArea(area).get(0);
            company = companyService.selectByCityId(660);
        }
        List<HrPosition> positions = positionService.getCachePositionList(company.getCompanyId());
        ModelAndView mav = new ModelAndView("/front/home/index");
        if(o==null){
            session.setAttribute("selectedArea", area);
        }
        mav.addObject("positionMap", groupPositionsByAttr(positions,"1"));
        return mav;
    }
    
    
    /**
     * 进入城市切换页面
     * @return
     */
    @RequestMapping(value="toSelectCitys")
    public ModelAndView toGetCitys(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/front/home/city");
        Map<String,List<AreaInfo>> areaMap= areaInfoService.getAreaMap();
        mav.addObject("areaMap", areaMap);
        return mav;
    }
    
    /**
     * 进入搜索页面
     * @return
     */
    @RequestMapping(value="toSearch")
    public ModelAndView toSearch(HttpServletRequest request){
        AreaInfo area = (AreaInfo) request.getSession().getAttribute("selectedArea");
        if(area==null){
            return new ModelAndView("/front/home/jump");
        }
        HrCompany company = companyService.selectByCityId(area.getId());
        List<HrPosition> positions = positionService.getCachePositionList(company.getCompanyId());
        ModelAndView mav = new ModelAndView("/front/home/search");
        mav.addObject("positionMap", groupPositionsByAttr(positions,"2"));
        return mav;
    }
    
    
    /**
     * 进入搜索页面
     * @return
     */
    @RequestMapping(value="toResult")
    public ModelAndView toResult(HttpServletRequest request,String search){
        AreaInfo area = (AreaInfo) request.getSession().getAttribute("selectedArea");
        if(area==null){
            return new ModelAndView("/front/home/jump");
        }
        HrCompany company = companyService.selectByCityId(area.getId());
        List<HrPosition> positions = positionService.getCachePositionList(company.getCompanyId());
        ModelAndView mav = new ModelAndView("/front/home/result");
        mav.addObject("positions", searchPositionsBySearch(positions,search));
        mav.addObject("search", search);
        return mav;
    }
    
    
    /**
     * 进入查询结果页面
     * @return
     */
    @RequestMapping(value="toAttrResult")
    public ModelAndView toAttrResult(HttpServletRequest request,String classfication){
        AreaInfo area = (AreaInfo) request.getSession().getAttribute("selectedArea");
        if(area==null){
            return new ModelAndView("/front/home/jump");
        }
        HrCompany company = companyService.selectByCityId(area.getId());
        List<HrPosition> positions = positionService.getCachePositionList(company.getCompanyId());
        ModelAndView mav = new ModelAndView("/front/home/result");
        mav.addObject("positions", searchPositionsByClassfication(positions,classfication));
        mav.addObject("search", "");
        return mav;
    }
    
    /**
     * 进入详情页
     * @return
     */
    @RequestMapping(value="toPositionDetail")
    public ModelAndView toPositionDetail(HttpServletRequest request,Integer positionId){
        ModelAndView mav = new ModelAndView("/front/home/position_detail");
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute(Constants.OPENID);
        boolean isHasTd = frontService.isPositionHasTd(openId, positionId);
        HrPosition position = positionService.getPositionForFrontById(positionId);
        mav.addObject("position", position);
        mav.addObject("isHasTd", isHasTd);
        return mav;
    }
    
    
    /**
     * 进入OPENID绑定页面
     * @return
     */
    @RequestMapping(value="toBind")
    public ModelAndView toBind(HttpServletRequest request,Integer positionId){
        ModelAndView mav = new ModelAndView("/front/home/bind");
        String requestUri = request.getParameter("requestUri");
        String openId = request.getParameter("openId");
        if(StringUtils.isNotEmpty(requestUri)){
            mav.addObject("requestUri", URLDecoder.decode(requestUri));
        }
        if(StringUtils.isNotEmpty(openId)){
            mav.addObject("openId", openId);
        }
        return mav;
    }
    
    /**
     * 校验用户是否存在
     * @param telNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value="checkSeekerUser")
    public Map<String,Object> checkSeekerUser(String name,String phone){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            ResumeSeeker rs = frontService.getSeekerBaseByNameAndPhone(name, phone);
            if(rs==null){
                map.put("flag", false);
                map.put("message", "不存在该用户");
            }else{
                map.put("flag", true);
                map.put("seekerId", rs.getId());
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("校验求职者失败");
            map.put("flag", false);
            map.put("message", "系统异常，请联系管理员!");
        }
        return map;
    }
    
    
    /**
     * 发送绑定短信验证码
     * @param telNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value="sendWxBindMsg")
    public Map<String,Object> sendWxBindMsg(String telephone){
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(telephone)){
            map.put("flag", false);
            map.put("message", "手机号不能为空!");
            return map;
        }
        try{
            return frontService.sendBindMsg(telephone);
        }catch(Exception e){
            map.put("flag", false);
            map.put("message", "系统异常，请联系管理员!");
        }
        return map;
    }
    
    
    /**
     * 绑定
     * @param telNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value="bindOpenId")
    public Map<String,Object> bindOpenId(HttpServletRequest request,ResumeSeeker resumeSeeker){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String openId = (String) request.getSession().getAttribute(Constants.OPENID);
            logger.info("---------------------------OPENID-----------"+openId);
            resumeSeeker.setOpenId(openId);
            frontService.bindOpenId(resumeSeeker);
            request.getSession().setAttribute(Constants.BINDSTATUS, Constants.HASBIND);
            map.put("flag", true);
            map.put("message","绑定成功");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("绑定失败");
            map.put("flag", false);
            map.put("message", "绑定失败");
        }
        return map;
    }
    
    
    
    private Map<String,List<HrPosition>> groupPositionsByAttr(List<HrPosition> positions,String type){
        Map<String,List<HrPosition>> result = new HashMap<String,List<HrPosition>>();
        //将职位列表按照 权重进行排序
        positions.sort(new Comparator<HrPosition>() {
            public int compare(HrPosition p1, HrPosition p2) {
                return p1.getPriority() - p2.getPriority();
            };
        });
        for(HrPosition p:positions){
            List<HrPosition> tmpList = result.get(p.getPositionAttribute());
            if(tmpList==null || tmpList.size()==0){
                List<HrPosition> newList = new ArrayList<HrPosition>();
                newList.add(p);
                result.put(p.getPositionAttribute(), newList);
            }else{
                /*if("1".equals(type)){
                    if(tmpList.size()<2){
                        tmpList.add(p);
                        result.put(p.getPositionAttribute(), tmpList);
                    }
                }*/
                if("2".equals(type)){
                    if(tmpList.size()<6){
                        tmpList.add(p);
                        result.put(p.getPositionAttribute(), tmpList);
                    }
                }else{
                    tmpList.add(p);
                    result.put(p.getPositionAttribute(), tmpList);
                }
            }
        }
        return result;
    }
    
    private List<HrPosition> searchPositionsBySearch(List<HrPosition> positions,String search){
        List<HrPosition> result = new ArrayList<HrPosition>();
        for(HrPosition p:positions){
            if(p.getName().contains(search)){
                result.add(p);
            }
        }
        return result;
    }
    
    private List<HrPosition> searchPositionsByClassfication(List<HrPosition> positions,String classfication){
        List<HrPosition> result = new ArrayList<HrPosition>();
        for(HrPosition p:positions){
            if(classfication.equals("社会招聘")){
                if(p.getPostionClassification().contains("社会")){
                    result.add(p);
                }
            }else if(classfication.equals("校园招聘")){
                if(p.getPostionClassification().contains("校园")){
                    result.add(p);
                }
            }else{
                if(p.getPostionClassification().equals(classfication)){
                    result.add(p);
                }
            }
        }
        return result;
    }
    
}
