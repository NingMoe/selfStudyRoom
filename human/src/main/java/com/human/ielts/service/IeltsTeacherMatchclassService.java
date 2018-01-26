package com.human.ielts.service;

import java.util.Map;

import com.human.ielts.entity.IeltsTeacherMatchclass;
import com.human.utils.PageView;

public interface IeltsTeacherMatchclassService {

    /**
     * 分页获取赛课信息
     * @param pageView
     * @param ieltsTeacherMatchclass
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherMatchclass ieltsTeacherMatchclass);

    /**
     * 更新赛课信息
     * @param ieltsTeacherMatchclass
     * @return
     */
    public Map<String, Object> updateteachermatchclass(IeltsTeacherMatchclass ieltsTeacherMatchclass);

    /**
     * 批量删除赛课信息
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteachermatchclass(String ids);

}
