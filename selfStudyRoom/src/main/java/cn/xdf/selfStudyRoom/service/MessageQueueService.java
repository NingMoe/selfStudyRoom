package cn.xdf.selfStudyRoom.service;

import cn.xdf.selfStudyRoom.exception.MyException;

/**
 * 消息队列服务接口
 * @author liuwei63
 *
 */
public interface MessageQueueService {
	/** 
     * 发送消息到队列 
     * @param queue 队列名称 
     * @param message 消息内容 
     */  
    public void send(String queueName,String message) throws MyException; 
    
    /** 
     * 延迟发送消息到队列 
     * @param queue 队列名称 
     * @param message 消息内容 
     * @param times 延迟时间 单位毫秒 
     * @throws MyException 
     */  
    public void send(String queueName,String message,long times) throws MyException;
}
