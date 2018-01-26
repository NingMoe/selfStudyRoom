package com.human.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.human.bpm.dao.BpmNodeConfigDao;
import com.human.bpm.entity.BpmNodeConfig;
import com.human.manager.entity.EcharsLinkDist;
import com.human.manager.entity.HomeSearchBean;
import com.human.manager.entity.Lable;
import com.human.manager.entity.Normal;
import com.human.manager.entity.SeriesData;
import com.human.manager.entity.Tooltip;
import com.human.manager.entity.UserDept;
import com.human.manager.service.HomeService;
import com.human.manager.service.UserDeptService;
import com.human.recruitment.dao.HrPositionDao;
import com.human.security.MyUser;
import com.human.utils.Common;

@Service
public class HomeServiceImpl implements HomeService {
    
    @Resource
    private UserDeptService udService;
    
    @Resource
    private BpmNodeConfigDao nodeDao;
    
    @Resource
    private HrPositionDao hpDao;
    
    private  SimpleDateFormat longSdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Map<String, Object> getLinkDistReport(HomeSearchBean  bean) {
       MyUser user=Common.getMyUser();
        List<UserDept> ud = udService.getUserAllOrg(user.getUserid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", false);
        if (ud == null) {
            return map;
        }
        // 查询所有环节
        List<BpmNodeConfig>  nodeList=nodeDao.getMyAllNode(user.getUserid());
        
        SimpleDateFormat   formatter =   new   SimpleDateFormat("yyyy-MM-dd");       
        bean.setUserId(user.getUserid());
        Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间       
        if(bean.getType()==0){
            //根据输入框开始和结束时间查询,不用做处理
            map.put("startDate", bean.getStartDate());
            map.put("endDate", bean.getEndDate());
        }else if (bean.getType()==1){
            //查询今天
            String   str   =   formatter.format(curDate); 
            bean.setStartDate(str);
            bean.setEndDate(str);
            map.put("startDate", str);
            map.put("endDate", str);
        }else if(bean.getType()==2){
            //查询本周
            String   startStr =formatter.format(getCurrentWeekDayStartTime());
            String   endStr =formatter.format(getCurrentWeekDayEndTime());
            bean.setStartDate(startStr);
            bean.setEndDate(endStr);
            map.put("startDate", startStr);
            map.put("endDate", endStr);
        }else if (bean.getType()==3){
          //查询本月
            String   startStr =formatter.format(getCurrentMonthStartTime());
            String   endStr =formatter.format(getCurrentMonthEndTime());
            bean.setStartDate(startStr);
            bean.setEndDate(endStr);
            map.put("startDate", startStr);
            map.put("endDate", endStr);
        }else if (bean.getType()==4){
            //查询本季度
            String   startStr =formatter.format(getCurrentQuarterStartTime());
            String   endStr =formatter.format(getCurrentQuarterEndTime());
            bean.setStartDate(startStr);
            bean.setEndDate(endStr);
            map.put("startDate", startStr);
            map.put("endDate", endStr);
        }else if (bean.getType()==5){
            //查询本财年
            String   startStr =formatter.format(getCurrentYearStartTime());
            String   endStr =formatter.format(getCurrentYearEndTime());
            bean.setStartDate(startStr);
            bean.setEndDate(endStr);
            map.put("startDate", startStr);
            map.put("endDate", endStr);
        }
        SimpleDateFormat formatter1= new   SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        map.put("curDate", formatter1.format(curDate).toString());
        //查询职位简历分布
        List<Map<String,Object>> postionList=hpDao.getMyPosition(bean);
        
        Set<String> legendList=new LinkedHashSet<String>();

        Set<String> keyList=new LinkedHashSet<String>();
        
        Set<String> yAxisList=new LinkedHashSet<String>(); 
        
        for(Map<String,Object> pos:postionList){
            yAxisList.add(String.valueOf(pos.get("positionName")));
        }
        
        for(BpmNodeConfig bnc:nodeList){
            legendList.add(bnc.getNodeName());
            keyList.add(bnc.getNodeId());
        }
        
        List<String> keyList2=new ArrayList<String>();
        keyList2.addAll(keyList);
        
        List<String> yAxisList2=new ArrayList<String>();
        yAxisList2.addAll(yAxisList);
        
        List<String> legendList2=new ArrayList<String>();
        legendList2.addAll(legendList);
        
        Lable l=new Lable();
        Normal n=new Normal();
        n.setShow(true);
        n.setPosition("inside");
        l.setNormal(n);
        List<EcharsLinkDist> eList=new ArrayList<EcharsLinkDist>();
                for (String k : keyList2) {
                    EcharsLinkDist ed=new EcharsLinkDist();
                    ed.setName(legendList2.get(keyList2.indexOf(k)));
                    ed.setStack("总量");
                    ed.setType("bar");
                    ed.setLabel(l);
                    List<SeriesData> sdList=new ArrayList<SeriesData>();
                    for (String s : yAxisList2) {
                        //报表传0会有问题，所以这里没有值的用null处理
                    Integer i=null;
                   // String fmat="{b}<br>{a}:{c}</br>";
                    String fmat="{b}【{a}】</br>";
                    for (Map<String, Object> mm : postionList) {
                        if (s.equals(String.valueOf(mm.get("positionName"))) && k.equals(String.valueOf(mm.get("node_id")))) {
                            if(i==null){
                                i=0;
                            }
                            i = i + Integer.valueOf(String.valueOf(mm.get("coum")));
                            fmat+=String.valueOf(mm.get("source"))+":"+String.valueOf(mm.get("coum"))+"</br>";
                        }
                    }
                    SeriesData sd=new SeriesData();
                    sd.setValue(i);
                    Tooltip tt=new Tooltip();
                    tt.setFormatter(fmat);
                    sd.setTooltip(tt);
                    sdList.add(sd);
                }
                    ed.setData(sdList); 
                    eList.add(ed);
        }
        
        map.put("eList", eList);
        map.put("flag", true);
        map.put("legendList", legendList);
        map.put("yAxisList", yAxisList);
        map.put("nodeList", nodeList);
        map.put("postionList", postionList);
        return map;
    }
    
    public   Date getCurrentWeekDayStartTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本周的最后一天，周日
     * 
     * @return
     */
    public   Date getCurrentWeekDayEndTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 8 - weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }
  
    
    /**
     * 获得本月的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public   Date getCurrentMonthStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    
    /**
     * 当前月的结束时间，即2012-01-31 23:59:59
     * 
     * @return
     */
    public   Date getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }
    
    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     * 
     * @return
     */
    public   Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

  
    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     * 
     * @return
     */
    public   Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }
    
    /**
     * 当前年的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public   Date getCurrentYearStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            int currentMonth = c.get(Calendar.MONTH) + 1;
            if(currentMonth<=6){
                c.add(Calendar.YEAR, -1);
            }
            c.set(Calendar.MONTH, 5);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     * 
     * @return
     */
    public   Date getCurrentYearEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            int currentMonth = c.get(Calendar.MONTH) + 1;
            if(currentMonth>=6){
                c.add(Calendar.YEAR, +1);
            }
            c.set(Calendar.MONTH, 4);
            c.set(Calendar.DATE, 31);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }
}
