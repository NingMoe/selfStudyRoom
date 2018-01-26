package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.human.jzbTest.dao.JzbPaperQuestionDao;
import com.human.jzbTest.entity.JzbPaperErrorDto;
import com.human.jzbTest.entity.JzbPaperQuestion;
import com.human.jzbTest.entity.JzbPaperQuestionDto;
import com.human.jzbTest.service.JzbPaperQuestionService;

@Service
public class JzbPaperQuestionServiceImpl implements JzbPaperQuestionService {
    
    @Resource
    private JzbPaperQuestionDao jpqDao;

    @Override
    public List<JzbPaperErrorDto> selectByPaperId(Integer paperId) {    
        List<JzbPaperQuestionDto> list=jpqDao.selectByPaperId(paperId);
        List<JzbPaperErrorDto> errorlist=new ArrayList<JzbPaperErrorDto>();
        Map<String,List<JzbPaperErrorDto>> commonlist=new HashMap<String,List<JzbPaperErrorDto>>();
         //处理数据
          if(CollectionUtils.isNotEmpty(list)){
            for(JzbPaperQuestionDto jqd:list){
                JzbPaperErrorDto jed=new JzbPaperErrorDto();
                deal(jqd,jed);
                if("2".equals(jqd.getqType())){//阅读理解、完型填空
                    String qCode=jqd.getqCode();
                    if(commonlist.containsKey(qCode)){
                        List<JzbPaperErrorDto> errorlist2=commonlist.get(qCode);
                        errorlist2.add(jed); 
                    }else{
                        List<JzbPaperErrorDto> subList=new ArrayList<JzbPaperErrorDto>();
                        subList.add(jed);
                        commonlist.put(qCode,subList);
                    }
                    
                }
                if("1".equals(jqd.getqType())){//单选题
                    errorlist.add(jed);
                }                
            }
            if(commonlist.size()>0){
                for(Map.Entry<String, List<JzbPaperErrorDto>> entry : commonlist.entrySet()){
                    JzbPaperErrorDto jed=new JzbPaperErrorDto();
                    jed.setqType("2");
                    jed.setqCode(entry.getKey());
                    jed.setErrorList(entry.getValue());
                    jed.setqMainDesc(entry.getValue().get(0).getqMainDesc());
                    errorlist.add(jed);
                }
            }
        }
        return errorlist;
    }
    
    
    
    public void deal(JzbPaperQuestionDto jqd,JzbPaperErrorDto jed){
        jed.setqType(jqd.getqType());
        jed.setaContent1(jqd.getaContent1());
        jed.setaContent2(jqd.getaContent2());
        jed.setPaperId(jqd.getPaperId());
        jed.setqCode(jqd.getqCode());
        jed.setqContent(jqd.getqContent());
        jed.setqMainDesc(jqd.getqMainDesc());
        jed.setSort(jqd.getSort());
        jed.setXh1(jqd.getXh1());
        jed.setXh2(jqd.getXh2());
        jed.setaImg1(jqd.getaImg1());
        jed.setaImg2(jqd.getaImg2());
    }
    
    
    
    
    
    @Override
    public int updateByPrimaryKeySelective(JzbPaperQuestion jpq) {
        return jpqDao.updateByPrimaryKeySelective(jpq);
    }
    
    
    @Override
    public int setStuAnswer(JzbPaperQuestion jpq) {
        return jpqDao.setStuAnswer(jpq);
    }
    
    @Override
    public Integer selectPassNum(Integer paperId) {
        return jpqDao.selectPassNum(paperId);
    }
    
}
