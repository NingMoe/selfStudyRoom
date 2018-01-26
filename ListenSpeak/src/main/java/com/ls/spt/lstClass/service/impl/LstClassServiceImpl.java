package com.ls.spt.lstClass.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.DictionaryService;
import com.ls.spt.lstClass.dao.LstClassDao;
import com.ls.spt.lstClass.entity.LstClass;
import com.ls.spt.lstClass.service.LstClassService;
import com.ls.spt.utils.Common;

@Service
public class LstClassServiceImpl implements LstClassService {

    private final Logger logger = LogManager.getLogger(LstClassServiceImpl.class);

    @Resource
    LstClassDao lcDao;

    @Resource
    private DictionaryService dictionaryService;

    @Override
    public PageView query(PageView pageView, LstClass lc) {
        logger.info("---------班级管理分页查询开始-------");
        Map<Object, Object> map = new HashMap<Object, Object>();
        List<DicData> subjectDic = dictionaryService.getDataByKey("subject");
        List<DicData> gradeDic = dictionaryService.getDataByKey("grade");
        Integer createUser= Common.getMyUser().getUserid();
        map.put("paging", pageView);
        map.put("t", lc);
        lc.setTeacher(createUser);
        List<LstClass> list = lcDao.query(map);
        for (LstClass lstClass : list) {
            String grade = lstClass.getGrade();
            String subject = lstClass.getSubject();
            String classCode = lstClass.getClassCode();
            for (DicData data : subjectDic) {
                if (data.getDataValue().equals(subject)) {
                    lstClass.setSubject(data.getName());
                }
            }
            for (DicData data : gradeDic) {
                if (data.getDataValue().equals(grade)) {
                    lstClass.setGrade(data.getName());
                }
            }
            String count = lcDao.selectCount(classCode);
            lstClass.setCount(count);
        }
        pageView.setData(list);
        return pageView;
    }

    @Override
    public int insert(LstClass lc) {
        // 生成邀请码
        String invitationCode = getInvitationCode();
        // 生成班号
        String classCode="TK";
        long time=System.currentTimeMillis();
        classCode+=String.valueOf(time);
        lc.setClassCode(classCode);
        lc.setInvitationCode(invitationCode);
        return lcDao.insertSelective(lc);
    }

    @Override
    public Map<String, Object> selectPrimaryKey(long id) {
        // TODO Auto-generated method stub
        return lcDao.selectByPrimaryKeyMap(id);
    }

    @Override
    public int update(LstClass lc) {
        // TODO Auto-generated method stub
        return lcDao.updateByPrimaryKeySelective(lc);
    }

    @Override
    public Map<String, Object> delete(String deleteIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            lcDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "禁用成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "禁用失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    @Override
    public Map<String, Object> goUse(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            lcDao.goUse(id);
            map.put("flag", true);
            map.put("message", "启用成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "启用失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    public static String getInvitationCode() {
        Set<String> store = getletterandnum(6);
        return printSet(store);
    }

    public static Set<String> getletterandnum(int length) {
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < length; i++) {
            String value = getrandom();
            set.add(value);
        }
        if (set.size() < length) { // 如果没有生成6位
            String value = getrandom();// 继续调用生成随机数的方法
            set.add(value);
        }
        return set;
    }

    private static String getrandom() { // 生成随机字母和数字方法
        String value = "";
        Random random = new Random();
        int gen = random.nextInt(2);// 0、1、2
        String charornum = gen % 2 == 0 ? "char" : "num";
        if ("char".equals(charornum)) {
            int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
            int ascii = random.nextInt(26);
            value += (char) (ascii + temp);
        }
        else if ("num".equalsIgnoreCase(charornum)) {
            value += String.valueOf(random.nextInt(10));
        }
        return value;
    }
    //生成验证码
    public static String printSet(Set set) { 
        Iterator iterator = set.iterator();
        String identCode = "";
        while (iterator.hasNext()) {
            String ele = (String) iterator.next();
            identCode += ele;
        }
        return identCode;
    }

    @Override
    public List<LstClass> selectPrimaryKeyInfo(long userId) {
        return lcDao.selectPrimaryKeyInfo(userId);
    }


}
