package com.human.basic.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.basic.entity.EmpSuper;
import com.human.basic.entity.EmpTeach;
import com.human.manager.entity.Users;

@Repository
public interface EmployeeDao {

    List<Users> query(Map<String, Object> map);

    /**
     * 获取员工汇报对象
     * @param userId
     * @return
     */
    List<EmpSuper> querySupserList(String empId);

    /**
     * 保存员工汇报对象
     * @param userId
     * @return
     */
    int saveUserSupser(EmpSuper empSupser);

    /**
     * 解除员工汇报对象
     * @param empSuper
     * @return
     */
    int delSuper(EmpSuper empSuper);

    /**
     * 获取员工导师
     * @param userId
     * @return
     */
    List<EmpTeach> queryTeachList(String empId);

    /**
     * 保存员工导师
     * @param userId
     * @return
     */
    int saveUserTeach(EmpTeach empTeach);

    /**
     * 解除员工导师
     * @param empSuper
     * @return
     */
    int delTeach(EmpTeach empTeach);
    
    /**
     * 获取用户有权限的员工列表
     * @param map
     * @return
     */
    List<Users> queryUserEmps(Map<String, Object> map);
    
    /**
     * 根据员工姓名查找其邮箱
     * @param name
     * @return
     */
    Users queryUserByName(String name);
    
}
