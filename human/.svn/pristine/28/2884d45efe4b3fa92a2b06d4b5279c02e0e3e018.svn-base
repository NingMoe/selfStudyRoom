package com.human.ielts.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.ielts.entity.IeltsBookInfo;
import com.human.ielts.entity.IeltsClassType;
import com.human.ielts.entity.IeltsTeacherInfo;
import com.human.ielts.service.IeltsClassTypeService;
import com.human.ielts.service.IeltsStudentBookService;
import com.human.ielts.service.IeltsTeacherInfoService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/manager")
public class IeltsManagerController {
    
    @Resource
    private IeltsClassTypeService ieltsClassTypeService;
    
    @Resource
    private IeltsStudentBookService ieltsStudentBookService;
    
    @Resource
    private IeltsTeacherInfoService IeltsTeacherInfoService;
    
    /**
     * 班级类型管理页
     * @return
     */
    @RequestMapping(value="/classview")
    public ModelAndView classview(){
        return new ModelAndView("/ielts/manager/classtype");
    }
    
    /**
     * 班级类型新增页
     * @return
     */
    @RequestMapping(value="/addclassview")
    public ModelAndView addclassview(){
        return new ModelAndView("/ielts/manager/addclasstype");
    }
    
    /**
     * 班级类型修改页
     * @return
     */
    @RequestMapping(value="/changeclassview")
    public ModelAndView changeclassview(){
        return new ModelAndView("/ielts/manager/changeclasstype");
    }
    
    /**
     * 教材管理页
     * @return
     */
    @RequestMapping(value="/bookview")
    public ModelAndView bookview(){
        return new ModelAndView("/ielts/manager/book");
    }
    
    /**
     * 教材新增页
     * @return
     */
    @RequestMapping(value="/addbookview")
    public ModelAndView addbookview(){
        return new ModelAndView("/ielts/manager/addbook");
    }
    
    /**
     * 教材修改页
     * @return
     */
    @RequestMapping(value="/changebookview")
    public ModelAndView changebookview(){
        return new ModelAndView("/ielts/manager/changebook");
    }
    
    /**
     * 教师管理页
     * @return
     */
    @RequestMapping(value="/teacherview")
    public ModelAndView teacherview(){
        return new ModelAndView("/ielts/manager/teacher");
    }
    
    /**
     * 教师新增页
     * @return
     */
    @RequestMapping(value="/addteacherview")
    public ModelAndView addteacherview(){
        return new ModelAndView("/ielts/manager/teacheradd");
    }
    
    /**
     * 教师修改页
     * @return
     */
    @RequestMapping(value="/changeteacherview")
    public ModelAndView changeteacherview(){
        return new ModelAndView("/ielts/manager/teacherchange");
    }
    
    /**
     * 教师上传页
     * @return
     */
    @RequestMapping(value="/upteacherview")
    public ModelAndView upteacherview(){
        return new ModelAndView("/ielts/manager/teacherup");
    }
    
    /**
     * 管理员查看页面
     * @return
     */
    @RequestMapping(value="/managerview")
    public ModelAndView managerview(){
        return new ModelAndView("manager.jsp");
    }
    
    /**
     * 分页获取书籍信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/querybook")
    public PageView querybook(PageView pageView,IeltsBookInfo ieltsBookInfo){
        return ieltsStudentBookService.querybook(pageView,ieltsBookInfo);
    }
    
    /**
     * 新增书籍信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertbook")
    public Map<String, Object> insertbook(IeltsBookInfo ieltsBookInfo){
        return ieltsStudentBookService.insertbook(ieltsBookInfo);
    }
    
    /**
     * 删除书籍信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletebook")
    public Map<String, Object> deletebook(IeltsBookInfo ieltsBookInfo){
        return ieltsStudentBookService.deletebook(ieltsBookInfo);
    }
    
    /**
     * 修改书籍信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updatebook")
    public Map<String, Object> updatebook(IeltsBookInfo ieltsBookInfo){
        return ieltsStudentBookService.updatebook(ieltsBookInfo);
    }
    
    /**
     * 查询书籍信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectbook")
    public Map<String, Object> selectbook(IeltsBookInfo ieltsBookInfo){
        return ieltsStudentBookService.selectbook(ieltsBookInfo);
    }
    
    /**
     * 分页获取班级类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/queryclasstype")
    public PageView queryclasstype(PageView pageView,IeltsClassType ieltsClassType){
        return ieltsClassTypeService.queryclasstype(pageView,ieltsClassType);
    }
    
    /**
     * 新增班级类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertclasstype")
    public Map<String, Object> insertclasstype(IeltsClassType ieltsClassType){
        return ieltsClassTypeService.insertclasstype(ieltsClassType);
    }
    
    /**
     * 删除班级类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteclasstype")
    public Map<String, Object> deleteclasstype(IeltsClassType ieltsClassType){
        return ieltsClassTypeService.deleteclasstype(ieltsClassType);
    }
    
    /**
     * 修改班级类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateclasstype")
    public Map<String, Object> updateclasstype(IeltsClassType ieltsClassType){
        return ieltsClassTypeService.updateclasstype(ieltsClassType);
    }
    
    /**
     * 查询书籍信息班级类型
     */
    @ResponseBody
    @RequestMapping(value="/selectclasstype")
    public Map<String, Object> selectclasstype(IeltsClassType ieltsClassType){
        return ieltsClassTypeService.selectclasstype(ieltsClassType);
    }
    
    /**
     * 分页获取教师信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/queryteacher")
    public PageView queryteacher(PageView pageView,IeltsTeacherInfo ieltsTeacherInfo){
        return IeltsTeacherInfoService.queryteacher(pageView,ieltsTeacherInfo);
    }
    
    /**
     * 新增教师信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertteacher")
    public Map<String, Object> insertteacher(IeltsTeacherInfo ieltsTeacherInfo){
        return IeltsTeacherInfoService.insertteacher(ieltsTeacherInfo);
    }
    
    /**
     * 删除教师信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteteacher")
    public Map<String, Object> deleteteacher(IeltsTeacherInfo ieltsTeacherInfo){
        return IeltsTeacherInfoService.deleteteacher(ieltsTeacherInfo);
    }
    
    /**
     * 批量删除教师信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteteachers")
    public Map<String, Object> deleteteachers(String ids){
        return IeltsTeacherInfoService.deleteteachers(ids);
    }
    
    /**
     * 修改教师信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteacher")
    public Map<String, Object> updateteacher(IeltsTeacherInfo ieltsTeacherInfo){
        return IeltsTeacherInfoService.updateteacher(ieltsTeacherInfo);
    }
    
    /**
     * 查询教师信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectteacher")
    public Map<String, Object> selectteacher(IeltsTeacherInfo ieltsTeacherInfo){
        return IeltsTeacherInfoService.selectteacher(ieltsTeacherInfo);
    }

}
