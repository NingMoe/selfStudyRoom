package com.human.ielts.service;

import java.util.Map;

import com.human.ielts.entity.IeltsTeacherArticle;
import com.human.utils.PageView;

public interface IeltsTeacherArticleService {

    /**
     * 分页获取教师教研文章
     * @param pageView
     * @param ieltsTeacherArticle
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherArticle ieltsTeacherArticle);

    /**
     * 更新教师教研文章
     * @param ieltsTeacherArticle
     * @return
     */
    public Map<String, Object> updateteacherarticle(IeltsTeacherArticle ieltsTeacherArticle);

    /**
     * 批量删除教师教研文章
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteacherarticle(String ids);

}
