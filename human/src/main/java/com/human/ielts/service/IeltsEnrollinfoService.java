package com.human.ielts.service;

import java.util.Map;

import com.human.ielts.entity.IeltsEnrollInfo;
import com.human.utils.PageView;

public interface IeltsEnrollinfoService {

    /**
     * 获取学生分数信息
     * @param pageView
     * @param ieltsEnrollInfo
     * @return
     */
    public PageView getenrollinfo(PageView pageView, IeltsEnrollInfo ieltsEnrollInfo);

    /**
     * 新增学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> insertenrollinfo(IeltsEnrollInfo ieltsEnrollInfo);

    /**
     * 删除学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> deleteenrollinfo(IeltsEnrollInfo ieltsEnrollInfo);
    
    /**
     * 批量删除学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> deletesenrollinfo(String ids);

    /**
     * 修改学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> updateenrollinfo(IeltsEnrollInfo ieltsEnrollInfo);

    /**
     * 查询学员分数信息
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectenrollinfo(IeltsEnrollInfo ieltsEnrollInfo);

    /**
     * 查询成绩回收率
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectstudentrecovery(IeltsEnrollInfo ieltsEnrollInfo);

    /**
     * 查询成绩平均分
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectstudentaverage(IeltsEnrollInfo ieltsEnrollInfo);

    /**
     * 查询成绩分布
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectstudentmaxtotal(IeltsEnrollInfo ieltsEnrollInfo);

    /**
     * 雅思学员提分达分
     * @param ieltsClassEnroll
     * @return
     */
    public Map<String, Object> selectachievesource(IeltsEnrollInfo ieltsEnrollInfo);

    /**
     * 达分提分学员年级细分统计
     * @param ieltsEnrollInfo
     * @return
     */
    public Map<String, Object> selectgradeaverage(IeltsEnrollInfo ieltsEnrollInfo);

    public Map<String, Object> selectteacherstudentrecovery(IeltsEnrollInfo ieltsEnrollInfo);

    public Map<String, Object> selectteacherstudentaverage(IeltsEnrollInfo ieltsEnrollInfo);

    public Map<String, Object> selectteacherstudentmaxtotal(IeltsEnrollInfo ieltsEnrollInfo);

    public Map<String, Object> selectteacherachievesource(IeltsEnrollInfo ieltsEnrollInfo);

    public Map<String, Object> selectteachergradeaverage(IeltsEnrollInfo ieltsEnrollInfo);
}
