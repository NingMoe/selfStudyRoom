package cn.xdf.selfStudyRoom.rabbitMq;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.xdf.selfStudyRoom.constant.MQConstant;

/**
 * 带回调的发送者
 * @author liuwei63
 *
 */
@Component
public class CallBackSender  implements  ConfirmCallback{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(String msg){
		rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());  
        this.rabbitTemplate.convertAndSend(MQConstant.DEFAULT_DIRECT_EXCHANGE, MQConstant.DEFAULT_ROUTING_KEY, msg, correlationData); 
	}
	

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if(ack){
			System.out.println("callback confirm: " + correlationData.getId());
		}else{
			System.out.println("消息发送确认失败: " +cause);
		}			
	}

}
