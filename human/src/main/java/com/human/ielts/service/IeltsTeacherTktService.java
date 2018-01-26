package com.human.ielts.service;

import java.util.Map;

import com.human.ielts.entity.IeltsTeacherTkt;
import com.human.utils.PageView;

public interface IeltsTeacherTktService {
    
    /**
     * 分页获取教师tkt详情
     * @param ieltsTeacherTkt
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherTkt ieltsTeacherTkt);

    /**
     * 获取教师所有tkt成绩
     * @param ieltsTeacherTkt
     * @return
     */
    public Map<String, Object> selectteachertkt(IeltsTeacherTkt ieltsTeacherTkt);

    /**
     * 更新教师所有tkt成绩
     * @param ieltsTeacherTkt
     * @return
     */
    public Map<String, Object> updateteachertkt(IeltsTeacherTkt ieltsTeacherTkt);

    /**
     * 批量删除tkt成绩
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteachertkt(String ids);

    /**
     * 查询TKT高分教师人数占比
     * @param ieltsTeacherTkt
     * @return
     */
    public Map<String, Object> selecttktteacher(IeltsTeacherTkt ieltsTeacherTkt);

}
