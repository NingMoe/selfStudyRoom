package com.human.datamanger.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.human.datamanger.dao.DataMangerDao;
import com.human.datamanger.entity.DataManger;
import com.human.datamanger.service.DataMangerService;
import com.human.manager.dao.UserDao;
import com.human.manager.entity.Users;
import com.human.security.MyUser;
import com.human.teacherservice.service.impl.LibraryServiceImpl;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;

@Service
public class DataMangerServiceImpl implements DataMangerService {
    private final  Logger logger = LogManager.getLogger(LibraryServiceImpl.class);
    
    @Resource
    private DataMangerDao dmDao;
    
    @Resource
    private UserDao userDao;
    
    /**
     * 分页查询
     */
    @Override
    public PageView query(PageView pageView, DataManger dm) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", dm);
        List<DataManger> list = dmDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    /**
     * 删除
     */
    @Override
    public Map<String, Object> delete(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            dmDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    public DataManger selectByPrimaryKey(long id) {
        return dmDao.selectByPrimaryKey(id);
    }

    /**
     * 编辑数据
     */
    @Override
    public Map<String, Object> edit(DataManger dm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            dmDao.updateByPrimaryKeySelective(dm); 
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    /**
     * 新增数据
     */
    @Override
    public int addData(DataManger dm) {
        return dmDao.insert(dm);
    }


    /**
     * 导入excel
     */
    @SuppressWarnings("all")
    @Transactional
    public Map<String, Object> upexcel(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取上传的excel
        logger.info("数据管理->上传数据:获取excel开始");
        
        
        boolean flag=false;
        String msg="未知错误";
        int index = 1;
        try{
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if(file.isEmpty()){
                map.put("message","文件内容为空，请重新选择文件!");
               return map;
            }
            ExcelUtil<DataManger> ex=new ExcelUtil<DataManger>(1,0);
            //获取数据管理数据
            Map<String,Object> empTeachMap=ex.checkAccount(file,DataManger.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                return empTeachMap;
            }
            List<DataManger> list=(List<DataManger>) empTeachMap.get("list");
            if(list.size()==0){
                map.put("message", "导入文件为空文件!");
               return map;
            }
            for(DataManger datamanger : list){
                dmDao.insert(datamanger);
            }
            flag=true;
            msg="导入成功,成功导入"+list.size()+"条数据";
        }catch(Exception e){
            logger.error("导入异常行："+index);
           throw new RuntimeException(e);
        }finally {
            if(!flag){
                //手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            map.put("flag", flag);
            map.put("message", msg);
            logger.info("数据管理->上传数据:获取excel结束");
            return map;
        }
    }


}
