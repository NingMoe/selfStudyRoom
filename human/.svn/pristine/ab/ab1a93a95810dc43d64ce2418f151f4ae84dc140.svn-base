package com.human.northamerica.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.northamerica.entity.MentionInfo;
import com.human.utils.PageView;

public interface MentionService {

    PageView queryInfoPage(PageView pageView, MentionInfo info);

    Map<String, Object> importScore(HttpServletRequest request);

    void saveMention(MentionInfo info);

    MentionInfo queryById(Long id);

    void editMention(MentionInfo info);

    void delMentionByIds(String deleteIds);

    PageView queryMentionReport(PageView pageView, MentionInfo info);

    List<MentionInfo> queryMentionInfo(MentionInfo info);

    List<String> refreshClassInfo();

    void exportMentionReport(HttpServletRequest request, HttpServletResponse response, MentionInfo info);

    void exportMentionInfo(HttpServletRequest request, HttpServletResponse response, MentionInfo info);

    PageView queryMentionTeachReport(PageView pageView, MentionInfo info);

    void exportTeachMentionReport(HttpServletRequest request, HttpServletResponse response, MentionInfo info);

}
