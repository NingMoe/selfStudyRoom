package com.human.ielts.service;

import java.util.Map;

import com.human.ielts.entity.IeltsTeacherOperate;
import com.human.utils.PageView;

public interface IeltsTeacherOperateService {

    /**
     * 分页获取教学运营支持
     * @param pageView
     * @param ieltsTeacherOperate
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherOperate ieltsTeacherOperate);

    /**
     * 更新教学运营支持
     * @param ieltsTeacherOperate
     * @return
     */
    public Map<String, Object> updateteacheroperate(IeltsTeacherOperate ieltsTeacherOperate);

    /**
     * 批量删除教学运营支持
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteacheroperate(String ids);

}
