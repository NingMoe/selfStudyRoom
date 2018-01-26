package com.human.utils.mailUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.human.basic.dao.ResumeKeywordDao;
import com.human.basic.dao.ResumeModularDao;
import com.human.basic.entity.ResumeKeyword;
import com.human.basic.entity.ResumeModular;
/**
 * 解析简历抽象公共类
 * @author liuwei63
 *
 */
@Component("commonReadResumeUtil")
public abstract class CommonReadResumeUtil {
    
    private static Logger logger = LogManager.getLogger(CommonReadResumeUtil.class);
      
    private ResumeModularDao rmDao;
    
    private ResumeKeywordDao rkDao;

    public CommonReadResumeUtil() {
      
    }

    public CommonReadResumeUtil(ResumeModularDao rmDao, ResumeKeywordDao rkDao) {
        this.rmDao = rmDao;
        this.rkDao = rkDao;
    }
    
    
    public abstract List<Object> dealHtmlResumeByResource(File input);
    
    
        
    /**
     * 获取简历模块关键词组
     * @return
     */
    public List<String> getResumeModularKeyWords(String website){
        logger.info("获取简历模块关键词组----");
        List<String> list = new ArrayList<String>(100);
        try{
            List<ResumeModular> rmLsit= rmDao.findResumeModularByCondition(website);
            if(CollectionUtils.isNotEmpty(rmLsit)){
                for(ResumeModular rm:rmLsit){
                    list.add(rm.getName().trim());
                }
            }   
        }catch(Exception e){
            e.printStackTrace();
            logger.error("获取简历模块关键词组异常:"+e.getMessage());
        }       
        return list;
    }
    
    
    /**
     * 获取简历关键词组
     * @return
     */
    public List<String> getResumeKeyWords(String website,String name){
        logger.info("获取简历关键词组----");
        List<String> list = new ArrayList<String>(100);        
        try{ 
            ResumeKeyword rk=new ResumeKeyword();
            rk.setWebsite(website);
            rk.setName(name);
            List<ResumeKeyword> rkLsit= rkDao.serachByCondition(rk);
            if(CollectionUtils.isNotEmpty(rkLsit)){
                for(ResumeKeyword rm:rkLsit){
                    list.add(rm.getName().trim());
                }
            }  
        }catch(Exception e){
            e.printStackTrace();
            logger.error("获取简历关键词组异常:"+e.getMessage());
        }      
        return list;
    }
    
    
}
