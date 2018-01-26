package com.human.jw.service;

import java.util.List;
import java.util.Map;

import com.human.jw.entity.JwClass;
import com.human.jw.entity.JwCourse;
import com.human.jw.entity.JwJyzUser;
import com.human.jw.entity.JwTeacherInfo;
import com.human.jw.entity.OrgEmployeeTree;
import com.human.utils.PageView;

public interface JwService {
    
    public List<OrgEmployeeTree> queryTreeById(String id);
    
    public Map<String,Object> initTeachData(String ids) throws Exception;
    
    public void refreshTechData(String ids) throws Exception;
    
    public void refreshAllTechData() throws Exception;
    
    public void jobRefresh() throws Exception;
    
    public void kstjRefresh() throws Exception;
    
    public String getTeacherCode(String email) throws Exception;
    
    public List<JwCourse> getTeacherJwCourses(String sCode,String start,String end) throws Exception;
    
    public List<JwClass> getJwClassByCodes(String classCode)throws Exception;
    
    public List<String> getStudentCodeByClass(String classCode)throws Exception;
    
    public PageView queryPageView(PageView pageView, JwTeacherInfo jwTeacherInfo)throws Exception;
    
    public JwTeacherInfo getTotalCnSj(JwTeacherInfo jwTeacherInfo);
    
    public PageView queryJyzPageView(PageView pageView, JwJyzUser jwJyzUser);
    
    public JwTeacherInfo getTeacherInfoById(Integer id);
    
    public void edit(JwTeacherInfo jwTeacherInfo);
    
    public void editSelective(JwTeacherInfo jwTeacherInfo);
    
    public void del(String teacherCode);
    
    public int delJyzUser(JwJyzUser jwJyzUser);
    
    public void addJyzUser(JwJyzUser jwJyzUser); 
    
    public List<JwJyzUser> getJwJyzUserByEmail(String email);
    
    public boolean isHasEditAu(String email);

    List<Map<String, Object>> getStudentInfoClass(String classCode) throws Exception; 
    
    JwTeacherInfo getTeacherKsTj(String teacherCode)throws Exception; 
    
}
