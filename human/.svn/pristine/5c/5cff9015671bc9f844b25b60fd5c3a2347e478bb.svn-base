package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsEnrollInfoDao;
import com.human.ielts.dao.IeltsStudentInfoDao;
import com.human.ielts.entity.IeltsEnrollInfo;
import com.human.ielts.service.IeltsEnrollinfoService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class IeltsEnrollinfoServiceImpl implements IeltsEnrollinfoService {

    @Resource
    private IeltsEnrollInfoDao ieltsEnrollInfoDao;
    
    @Resource
    private IeltsStudentInfoDao ieltsStudentInfoDao;
    
    /**
     * 获取学生分数信息
     * @param pageView
     * @param ieltsEnrollInfo
     * @return
     */
    public PageView getenrollinfo(PageView pageView, IeltsEnrollInfo ieltsEnrollInfo) {
      //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsEnrollInfo);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsEnrollInfo> list = ieltsEnrollInfoDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 新增学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> insertenrollinfo(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请填写分数信息");
            return map;
        }
        
        if(ieltsEnrollInfo.getStudent_id() == null){
            map.put("flag", false);
            map.put("message", "未获取到学员");
            return map;
        }
        
        if(ieltsEnrollInfo.getTest_time() == null){
            map.put("flag", false);
            map.put("message", "请填写考试时间");
            return map;
        }
        
        try {
            ieltsEnrollInfoDao.insertSelective(ieltsEnrollInfo);
            map.put("flag", true);
            map.put("message", "新增成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增失败："+e);
        }
        
        return map;
    }

    /**
     * 删除学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> deleteenrollinfo(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选择具体的信息");
            return map;
        }
        
        if(ieltsEnrollInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择具体的信息");
            return map;
        }
        
        try {
            ieltsEnrollInfoDao.deleteByPrimaryKey(ieltsEnrollInfo.getId());
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败："+e);
        }
        return map;
    }
    
    /**
     * 批量删除学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> deletesenrollinfo(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请至少选择一条");
            return map;
        }
        
        try {
            
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsEnrollInfoDao.deleteByPrimaryKey(id);
            }
            
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败："+e);
        }
        return map;
    }

    /**
     * 修改学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> updateenrollinfo(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请填写分数信息");
            return map;
        }
        
        if(ieltsEnrollInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "未获取到学员");
            return map;
        }
        
        if(ieltsEnrollInfo.getTest_time() == null){
            map.put("flag", false);
            map.put("message", "请填写考试时间");
            return map;
        }
        
        try {
            ieltsEnrollInfoDao.updateByPrimaryKeySelective(ieltsEnrollInfo);
            map.put("flag", true);
            map.put("message", "修改成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改失败："+e);
        }
        return map;
    }

    /**
     * 查询学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectenrollinfo(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选择具体的信息");
            return map;
        }
        
        if(ieltsEnrollInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择具体的信息");
            return map;
        }
        
        try {
            IeltsEnrollInfo ieltsEnrollInfo1 = ieltsEnrollInfoDao.selectByPrimaryKey(ieltsEnrollInfo.getId());
            if(ieltsEnrollInfo1 != null){
                map.put("flag", true);
                map.put("message", "查询成功");
                map.put("ieltsEnrollInfo", ieltsEnrollInfo1);
            }else{
                map.put("flag", false);
                map.put("message", "没有要查询的信息");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询失败："+e);
        }
        return map;
    }

    /**
     * 查询成绩回收率
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectstudentrecovery(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            //获取有成绩的人
            Integer hassourcecount = ieltsEnrollInfoDao.selectStudentCount(mapparam);
            
            //获取有报名日期的人数
            Integer enrollcount = ieltsEnrollInfoDao.selectEnrollCount(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取成绩回收率成功");
            map.put("hassourcecount", hassourcecount);
            map.put("enrollcount", enrollcount);
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取成绩回收率出错："+e);
        }
        
        return map;
    }

    /**
     * 查询成绩平均分
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectstudentaverage(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            //获取听力平均分
            Double listenavg = ieltsEnrollInfoDao.selectStudentListenAvg(mapparam);
            
            //获取阅读平均分
            Double readavg = ieltsEnrollInfoDao.selectStudentReadAvg(mapparam);
            //获取写作平均分
            Double writeavg = ieltsEnrollInfoDao.selectStudentWriteAvg(mapparam);
            //获取口语平均分
            Double talkavg = ieltsEnrollInfoDao.selectStudentTalkAvg(mapparam);
            //获取总成绩平均分
            Double totalcount = ieltsEnrollInfoDao.selectStudentTotalAvg(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取成绩平均分成功");
            map.put("listenavg", listenavg);
            map.put("readavg", readavg);
            map.put("writeavg", writeavg);
            map.put("talkavg", talkavg);
            map.put("totalcount", totalcount);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取成绩平均分出错："+e);
        }
        
        return map;
    }

    /**
     * 查询成绩分布
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectstudentmaxtotal(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            //获取所有学员最高分
            List<Double> sourcelist = ieltsEnrollInfoDao.selectStudentMaxSourceList(mapparam);
            
            Integer a1 = 0;
            Integer a2 = 0;
            Integer a3 = 0;
            Integer a4 = 0;
            
            if(sourcelist != null && sourcelist.size() > 0){
                for(Double i : sourcelist){
                    if(i >= 7){
                        a1++;
                    }else if(i >= 6){
                        a2++;
                    }else if(i >= 5){
                        a3++;
                    }else{
                        a4++;
                    }
                }
            }
            map.put("flag", true);
            map.put("message", "获取成绩分布成功");
            map.put("a1", a1);
            map.put("a2", a2);
            map.put("a3", a3);
            map.put("a4", a4);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取成绩分布出错："+e);
        }
        
        return map;
    }

    /**
     * 雅思学员提分达分
     * @param ieltsClassEnroll
     * @return
     */
    public Map<String, Object> selectachievesource(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            //获取雅思学员数
            Integer ieltsallstudentcount = ieltsEnrollInfoDao.selectEnrollCount(mapparam);
            
            //获取雅思提分学员数
            Integer ieltsincreasestudentcount = ieltsEnrollInfoDao.selectEnrollIncreaseCount(mapparam);
            
            //获取计划内学员数
            Integer planallcount = ieltsEnrollInfoDao.selectStudentEnrollPlanCount(mapparam);
            
            //获取计划内提分学员数
            Integer planincreasecount = ieltsEnrollInfoDao.selectEnrollIncreasePlanCount(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取雅思学员提分达分成功");
            map.put("ieltsallstudentcount", ieltsallstudentcount);
            map.put("ieltsincreasestudentcount", ieltsincreasestudentcount);
            map.put("planallcount", planallcount);
            map.put("planincreasecount", planincreasecount);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取雅思学员提分达分出错："+e);
        }
        
        return map;
    }

    /**
     * 达分提分学员年级细分统计
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectgradeaverage(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            mapparam.put("grade_name", "初一");
            //获取
            Integer chuyi_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer chuyi_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "初二");
            Integer chuer_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer chuer_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "初三");
            Integer chusan_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer chusan_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "高一");
            Integer gaoyi_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer gaoyi_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "高二");
            Integer gaoer_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer gaoer_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "高三");
            Integer gaosan_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer gaosan_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "大一");
            Integer dayi_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer dayi_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "大二");
            Integer daer_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer daer_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "大三");
            Integer dasan_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer dasan_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "大四");
            Integer dasi_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer dasi_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "研究生");
            Integer yanjiusheng_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer yanjiusheng_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取提分学员年级细分成功");
            map.put("chuyi_count", chuyi_count);
            map.put("chuyi_i_count", chuyi_i_count);
            map.put("chuer_count", chuer_count);
            map.put("chuer_i_count", chuer_i_count);
            map.put("chusan_count", chusan_count);
            map.put("chusan_i_count", chusan_i_count);
            map.put("gaoyi_count", gaoyi_count);
            map.put("gaoyi_i_count", gaoyi_i_count);
            map.put("gaoer_count", gaoer_count);
            map.put("gaoer_i_count", gaoer_i_count);
            map.put("gaosan_count", gaosan_count);
            map.put("gaosan_i_count", gaosan_i_count);
            
            map.put("dayi_count", dayi_count);
            map.put("dayi_i_count", dayi_i_count);
            map.put("daer_count", daer_count);
            map.put("daer_i_count", daer_i_count);
            map.put("dasan_count", dasan_count);
            map.put("dasan_i_count", dasan_i_count);
            map.put("dasi_count", dasi_count);
            map.put("dasi_i_count", dasi_i_count);
            map.put("yanjiusheng_count", yanjiusheng_count);
            map.put("yanjiusheng_i_count", yanjiusheng_i_count);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取提分学员年级细分出错："+e);
        }
        
        return map;
    }

    public Map<String, Object> selectteacherstudentrecovery(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            mapparam.put("email_addr", myUser.getEmailAddr());
            //获取有成绩的人
            Integer hassourcecount = ieltsEnrollInfoDao.selectTeacherStudentCount(mapparam);
            
            //获取有报名日期的人数
            Integer enrollcount = ieltsEnrollInfoDao.selectTeacherEnrollCount(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取成绩回收率成功");
            map.put("hassourcecount", hassourcecount);
            map.put("enrollcount", enrollcount);
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取成绩回收率出错："+e);
        }
        
        return map;
    }

    public Map<String, Object> selectteacherstudentaverage(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            mapparam.put("email_addr", myUser.getEmailAddr());
            //获取听力平均分
            Double listenavg = ieltsEnrollInfoDao.selectTeacherStudentListenAvg(mapparam);
            
            //获取阅读平均分
            Double readavg = ieltsEnrollInfoDao.selectTeacherStudentReadAvg(mapparam);
            //获取写作平均分
            Double writeavg = ieltsEnrollInfoDao.selectTeacherStudentWriteAvg(mapparam);
            //获取口语平均分
            Double talkavg = ieltsEnrollInfoDao.selectTeacherStudentTalkAvg(mapparam);
            //获取总成绩平均分
            Double totalcount = ieltsEnrollInfoDao.selectTeacherStudentTotalAvg(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取成绩平均分成功");
            map.put("listenavg", listenavg);
            map.put("readavg", readavg);
            map.put("writeavg", writeavg);
            map.put("talkavg", talkavg);
            map.put("totalcount", totalcount);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取成绩平均分出错："+e);
        }
        
        return map;
    }

    public Map<String, Object> selectteacherstudentmaxtotal(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            mapparam.put("email_addr", myUser.getEmailAddr());
            //获取所有学员最高分
            List<Double> sourcelist = ieltsEnrollInfoDao.selectTeacherStudentMaxSourceList(mapparam);
            
            Integer a1 = 0;
            Integer a2 = 0;
            Integer a3 = 0;
            Integer a4 = 0;
            
            if(sourcelist != null && sourcelist.size() > 0){
                for(Double i : sourcelist){
                    if(i >= 7){
                        a1++;
                    }else if(i >= 6){
                        a2++;
                    }else if(i >= 5){
                        a3++;
                    }else{
                        a4++;
                    }
                }
            }
            map.put("flag", true);
            map.put("message", "获取成绩分布成功");
            map.put("a1", a1);
            map.put("a2", a2);
            map.put("a3", a3);
            map.put("a4", a4);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取成绩分布出错："+e);
        }
        
        return map;
    }

    public Map<String, Object> selectteacherachievesource(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            mapparam.put("email_addr", myUser.getEmailAddr());
            //获取雅思学员数
            Integer ieltsallstudentcount = ieltsEnrollInfoDao.selectTeacherEnrollCount(mapparam);
            
            //获取雅思提分学员数
            Integer ieltsincreasestudentcount = ieltsEnrollInfoDao.selectTeacherEnrollIncreaseCount(mapparam);
            
            //获取计划内学员数
            Integer planallcount = ieltsEnrollInfoDao.selectTeacherStudentEnrollPlanCount(mapparam);
            
            //获取计划内提分学员数
            Integer planincreasecount = ieltsEnrollInfoDao.selectTeacherEnrollIncreasePlanCount(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取雅思学员提分达分成功");
            map.put("ieltsallstudentcount", ieltsallstudentcount);
            map.put("ieltsincreasestudentcount", ieltsincreasestudentcount);
            map.put("planallcount", planallcount);
            map.put("planincreasecount", planincreasecount);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取雅思学员提分达分出错："+e);
        }
        
        return map;
    }

    public Map<String, Object> selectteachergradeaverage(IeltsEnrollInfo ieltsEnrollInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsEnrollInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsEnrollInfo.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsEnrollInfo.getLeft_test_time());
            mapparam.put("right_test_time", ieltsEnrollInfo.getRight_test_time());
            mapparam.put("grade_name", "初一");
            //获取
            Integer chuyi_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer chuyi_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "初二");
            Integer chuer_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer chuer_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "初三");
            Integer chusan_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer chusan_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "高一");
            Integer gaoyi_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer gaoyi_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "高二");
            Integer gaoer_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer gaoer_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "高三");
            Integer gaosan_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer gaosan_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "大一");
            Integer dayi_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer dayi_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "大二");
            Integer daer_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer daer_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "大三");
            Integer dasan_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer dasan_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "大四");
            Integer dasi_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer dasi_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            mapparam.put("grade_name", "研究生");
            Integer yanjiusheng_count = ieltsEnrollInfoDao.selectGradeCount(mapparam);
            Integer yanjiusheng_i_count = ieltsEnrollInfoDao.selectIncreaseGradeCount(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取提分学员年级细分成功");
            map.put("chuyi_count", chuyi_count);
            map.put("chuyi_i_count", chuyi_i_count);
            map.put("chuer_count", chuer_count);
            map.put("chuer_i_count", chuer_i_count);
            map.put("chusan_count", chusan_count);
            map.put("chusan_i_count", chusan_i_count);
            map.put("gaoyi_count", gaoyi_count);
            map.put("gaoyi_i_count", gaoyi_i_count);
            map.put("gaoer_count", gaoer_count);
            map.put("gaoer_i_count", gaoer_i_count);
            map.put("gaosan_count", gaosan_count);
            map.put("gaosan_i_count", gaosan_i_count);
            
            map.put("dayi_count", dayi_count);
            map.put("dayi_i_count", dayi_i_count);
            map.put("daer_count", daer_count);
            map.put("daer_i_count", daer_i_count);
            map.put("dasan_count", dasan_count);
            map.put("dasan_i_count", dasan_i_count);
            map.put("dasi_count", dasi_count);
            map.put("dasi_i_count", dasi_i_count);
            map.put("yanjiusheng_count", yanjiusheng_count);
            map.put("yanjiusheng_i_count", yanjiusheng_i_count);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取提分学员年级细分出错："+e);
        }
        
        return map;
    }
}
