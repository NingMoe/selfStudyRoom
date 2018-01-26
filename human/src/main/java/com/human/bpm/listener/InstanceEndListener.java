package com.human.bpm.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.resume.dao.ResumeBaseDao;

@Service
public class InstanceEndListener implements ExecutionListener{
    /**
     * 监听会签节点
     */
    private static final long serialVersionUID = 1L;
    
    public static final String RESULT_NOPASS = "0";
    public static final String RESULT_PASS = "1";
    public static final String RESULT_BACK = "2";
    public static final String RESULT_QUICKPASS = "3";
    
    @Autowired ResumeBaseDao resumeBaseDao;
     
    @Override
    public void notify(DelegateExecution arg0) throws Exception {
        // TODO Auto-generated method stub
        String flowCode = arg0.getProcessBusinessKey();
        System.out.println(flowCode);
        resumeBaseDao.unlockResumeBaseToFlow(flowCode);
    }
}
