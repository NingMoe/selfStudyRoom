package com.human.basic.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.basic.entity.EmpSuper;
import com.human.basic.entity.EmpTeach;
import com.human.manager.entity.HrUser;
import com.human.manager.entity.Users;
import com.human.utils.PageView;

public interface EmployeeService {

    /**
     * 分页查询员工
     * @param pageView
     * @param user
     * @return
     */
    PageView queryEmp(PageView pageView, Users user,String deptIds);

    /**
     * 获取员工汇报对象
     * @param userId
     * @return
     */
    List<EmpSuper> querySupserList(String empId);

    /**
     * 根据邮箱获取用户信息
     * @param emailAddr
     * @return
     */
    HrUser queryUserByEmail(String emailAddr);

    /**
     * 保存用户汇报信息
     * @param empSupser
     * @return
     */
    int saveUserSupser(EmpSuper empSuper);

    /**
     * 解除汇报对象关系
     * @param empSuper
     */
    int delSuper(EmpSuper empSuper);

    /**
     * 获取员工导师
     * @param userId
     * @return
     */
    List<EmpTeach> queryTeachList(String empId);

    /**
     * 保存用户导师
     * @param empSupser
     * @return
     */
    int saveUserTeach(EmpTeach empTeach);
    /**
     * 解除导师
     * @param empSuper
     */
    int delTeach(EmpTeach empTeach);

    /**
     * 导入汇报关系配置
     * @param request
     * @return
     */
    Map<String, Object> ImportSuper(HttpServletRequest request);

    /**
     * 导入导师配置
     * @param request
     * @return
     */
    Map<String, Object> ImportTeach(HttpServletRequest request);

    /**
     * 导入员工手机号
     * @param request
     * @return
     */
    Map<String, Object> ImportPhone(HttpServletRequest request);

}
