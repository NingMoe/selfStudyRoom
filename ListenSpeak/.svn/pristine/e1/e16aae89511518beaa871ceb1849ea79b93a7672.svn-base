package com.ls.spt.manager.service;

import java.util.Map;

import com.ls.spt.manager.entity.MessageRecord;
import com.ls.spt.manager.entity.TeacherUser;

public interface MessageRecordService {
	
    /*
     * 发送注册验证码
     */
	public Map<String,Object> sendRegisterMsg(String telNumber);
	
	/*
	 * 查询验证码
	 */
	public MessageRecord getMsg(String telNumber);
	
	/*
	 * 发送忘记密码验证码
	 */
	public Map<String, Object> sendChangePasswordMsg(String telNumber);

    /*
     * 更新教师密码（忘记密码）
     * @param tu
     * @param msg
     * @return
     */
    Map<String,Object> changePasswordForMsg(TeacherUser tu,String msg);
}
