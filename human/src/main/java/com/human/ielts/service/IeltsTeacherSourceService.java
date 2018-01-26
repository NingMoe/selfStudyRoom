package com.human.ielts.service;

import java.util.Map;

import com.human.ielts.entity.IeltsTeacherSource;
import com.human.utils.PageView;

public interface IeltsTeacherSourceService {

    /**
     * 分页获取雅思成绩
     * @param pageView
     * @param ieltsTeacherSource
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherSource ieltsTeacherSource);

    /**
     * 更新教师雅思成绩
     * @param ieltsTeacherSource
     * @return
     */
    public Map<String, Object> updateteachersource(IeltsTeacherSource ieltsTeacherSource);

    /**
     * 批量删除教师雅思成绩
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteachersource(String ids);

    /**
     * 查询雅思高分教师人数占比
     * @param ieltsTeacherSource
     * @return
     */
    public Map<String, Object> selectieltsteacher(IeltsTeacherSource ieltsTeacherSource);

}
