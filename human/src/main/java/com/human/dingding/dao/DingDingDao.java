package com.human.dingding.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.dingding.bean.CheckInOut;
import com.human.dingding.bean.DeptInfo;
import com.human.dingding.bean.GroupInfo;
import com.human.dingding.bean.LocalDeptInfo;
import com.human.dingding.bean.LocalEmp;

@Repository
public interface DingDingDao {

    List<LocalDeptInfo> queryLocalFirstDeptList();
    
    List<LocalDeptInfo> queryLocalSecDeptList();

    int insertDingdingId(LocalDeptInfo local);

    List<LocalEmp> queryEmpList();

    List<GroupInfo> queryLocalGroup();

    int insertGroupDingdingId(GroupInfo g);
    
    List<DeptInfo> queryManager();
    
    List<Long> getUserDept(String empId);

    Integer queryKQuserId(String userid);

    void insertKQ(CheckInOut ci);

}
