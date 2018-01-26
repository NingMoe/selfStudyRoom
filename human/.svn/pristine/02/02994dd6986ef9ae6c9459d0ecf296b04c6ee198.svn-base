package com.human.stuexam.service.impl;

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

import com.human.datamanger.entity.DataManger;
import com.human.examineelist.entity.ExamineeList2;
import com.human.stuexam.dao.StuExamDao;
import com.human.stuexam.entity.StuExam;
import com.human.stuexam.service.StuExamService;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;

@Service
public class StuExamServiceImpl implements StuExamService {
    private final Logger logger = LogManager.getLogger(StuExamServiceImpl.class);

    @Resource
    private StuExamDao seDao;

    /**
     * 分页查询
     */
    @Override
    public PageView query(PageView pageView, StuExam se) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", se);
        List<StuExam> list = seDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Map<String, Object> delete(String deleteIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            seDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public int addData(StuExam se) {
        return seDao.insert(se);
    }

    @Override
    public Map<String, Object> update(StuExam se) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            seDao.updateByPrimaryKeySelective(se);
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public StuExam selectByPrimaryKey(long id) {
        // TODO Auto-generated method stub
        return seDao.selectByPrimaryKey(id);
    }

    @Override
    public List<StuExam> queryInfoByCode(String classCode) {
        // TODO Auto-generated method stub
        return seDao.queryInfoByCode(classCode);
    }

    @Override
    public List<StuExam> lookforTearcher(StuExam se) {
        // TODO Auto-generated method stub
        return seDao.lookforTearcher(se);
    }

    @Override
    @SuppressWarnings("all")
    @Transactional
    public Map<String, Object> addexcle(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取上传的excel
        logger.info("数据管理->上传数据:获取excel开始");

        boolean flag = false;
        String msg = "未知错误";
        int index = 1;
        try {
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if (file.isEmpty()) {
                map.put("message", "文件内容为空，请重新选择文件!");
                return map;
            }
            ExcelUtil<StuExam> ex = new ExcelUtil<StuExam>(1, 0);
            // 获取数据管理数据
            Map<String, Object> empTeachMap = ex.checkAccount(file, StuExam.class);
            if (null != empTeachMap && empTeachMap.get("flag").toString().equals("false")) {
                return empTeachMap;
            }
            List<StuExam> list = (List<StuExam>) empTeachMap.get("list");
            if (list.size() == 0) {
                map.put("message", "导入文件为空文件!");
                return map;
            }
            for (StuExam stuExam : list) {
                seDao.insert(stuExam);
            }
            flag = true;
            msg = "导入成功,成功导入" + list.size() + "条数据";
        }
        catch (Exception e) {
            logger.error("导入异常行：" + index);
            throw new RuntimeException(e);
        }
        finally {
            if (!flag) {
                // 手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            map.put("flag", flag);
            map.put("message", msg);
            logger.info("数据管理->上传数据:获取excel结束");
            return map;
        }
    }

    @Override
    public Map<String, Object> getBeginAndEndTime(String classCode) {
        // TODO Auto-generated method stub
        return seDao.getBeginAndEndTime(classCode);
    }
}