package com.human.bpm.service.impl;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.bpm.service.DispatchWorkflowService;

@Service
public class DispatchWorkflowServiceImpl implements DispatchWorkflowService{
	
	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected TaskService taskService;

	@Override
	public Boolean isYimianComplete(Execution execution) {
		if(taskService.getVariable(taskService.createTaskQuery().executionId(execution.getId()).list().get(0).getId(),"isyimianpass")!=null){
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean isErmianComplete(Execution execution) {
		if(taskService.getVariable(taskService.createTaskQuery().executionId(execution.getId()).list().get(0).getId(),"isermianpass")!=null){
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean isSanmianComplete(Execution execution) {
		if(taskService.getVariable(taskService.createTaskQuery().executionId(execution.getId()).list().get(0).getId(),"issanmianpass")!=null){
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean isSimianComplete(Execution execution) {
		if(taskService.getVariable(taskService.createTaskQuery().executionId(execution.getId()).list().get(0).getId(),"issimianpass")!=null){
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean isWumianComplete(Execution execution) {
	    if(taskService.getVariable(taskService.createTaskQuery().executionId(execution.getId()).list().get(0).getId(),"iswumianpass")!=null){
            return true;
        }
        return false;
	}
}
