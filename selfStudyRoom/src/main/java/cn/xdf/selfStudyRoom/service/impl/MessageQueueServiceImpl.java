package cn.xdf.selfStudyRoom.service.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import cn.xdf.selfStudyRoom.constant.MQConstant;
import cn.xdf.selfStudyRoom.exception.MyException;
import cn.xdf.selfStudyRoom.rabbitMq.DLXMessage;
import cn.xdf.selfStudyRoom.service.MessageQueueService;

@Service
public class MessageQueueServiceImpl implements MessageQueueService {

	@Autowired  
	private RabbitTemplate rabbitTemplate; 
	
	
	@Override
	public void send(String queueName, String message) throws MyException {
		try{
			rabbitTemplate.convertAndSend(MQConstant.DEFAULT_DIRECT_EXCHANGE,queueName, message);
		}catch(Exception e){
			e.printStackTrace();
			throw new MyException("发送队列失败！");
		}		
	}


	@Override
	public void send(String queueName, String msg, long times) throws MyException {
		try{
			DLXMessage dlxMessage = new DLXMessage(queueName,msg,times);  
	        MessagePostProcessor processor = new MessagePostProcessor(){  
	            @Override  
	            public Message postProcessMessage(Message message) throws AmqpException {  
	                message.getMessageProperties().setExpiration(times + "");  
	                return message;  
	            }  
	        };  
	        dlxMessage.setExchange(MQConstant.DEFAULT_DIRECT_EXCHANGE);  
	        String json=JSONObject.toJSONString(dlxMessage);
	        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_DIRECT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, json, processor);
		}catch(Exception e){
			e.printStackTrace();
			throw new MyException("发送延迟队列失败！");
		}				
	}

}
