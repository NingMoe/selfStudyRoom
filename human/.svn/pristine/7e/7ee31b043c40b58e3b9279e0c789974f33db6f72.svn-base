package com.human.teacherservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.human.manager.dao.UserDao;
import com.human.teacherservice.dao.LibBookErrorDao;
import com.human.teacherservice.entity.LibBook;
import com.human.teacherservice.entity.LibBookError;
import com.human.teacherservice.service.LibraryBookErrorService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class LibraryBookErrorServiceImpl implements LibraryBookErrorService {
    
    private final  Logger logger = LogManager.getLogger(LibraryBookErrorServiceImpl.class);
    
    @Resource
    private UserDao userDao;
    
    @Resource
    private LibBookErrorDao libBookErrorDao;
    
    /**
     * 分页获取异常书籍
     */
    public PageView query(PageView pageView, LibBookError libBookError) {
        logger.info("分页获取书籍异常开始......");
        Map<Object, Object> map = new HashMap<Object, Object>();
        libBookError.setSchool_id(Common.getMyUser().getCompanyId());        
        map.put("paging", pageView); 
        map.put("t", libBookError);
        logger.info("参数："+libBookError.toString());
        List<LibBookError> list = libBookErrorDao.query(map);
        pageView.setRecords(list);
        logger.info("分页获取书籍异常结束......");
        return pageView;
    }

    /**
     * 图书报错
     */
    public Map<String, Object> errorbook(HttpServletRequest request,HttpServletResponse response ,LibBook libBook) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(libBook == null){
            map.put("flag", false); 
            map.put("message", "请选择要报错的书籍。");
            return map;
        }
        
        if(libBook.getId() == null){
            map.put("flag", false); 
            map.put("message", "请选择要报错的书籍。");
            return map;
        }
        LibBookError libBookError = new LibBookError();        
        libBookError.setSchool_id(Common.getMyUser().getCompanyId());
        libBookError.setBook_id(libBook.getId());
        libBookError.setBook_name(libBook.getBook_name());
        libBookError.setReport_empl_id(Integer.valueOf(String.valueOf(Common.getMyUser().getUserid())));
        libBookError.setReport_name(Common.getMyUser().getName());        
        try {
            libBookErrorDao.insertSelective(libBookError);
            map.put("flag", true); 
            map.put("message", "已成功提交书籍异常。");
        }catch (Exception e) {
            map.put("flag", false); 
            map.put("message", "失败，请联系管理员："+e);
        }
        return map;
    }

    /**
     * 微图书馆异常解决
     * @param ids
     * @return
     */
    public Map<String, Object> selecttrue(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要解决的异常");
            return map;
        }        
        try {
            String[] idarray = ids.split(",");
            for(String idstring : idarray){
                Integer id = Integer.valueOf(idstring);
                LibBookError libBookError = new LibBookError();
                libBookError.setId(id);
                libBookError.setValid(1);
                libBookErrorDao.updateByPrimaryKeySelective(libBookError);
            }
            map.put("flag", true);
            map.put("message", "解决完成");
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "失败，联系管理员");
        }       
        return map;
    }

}
