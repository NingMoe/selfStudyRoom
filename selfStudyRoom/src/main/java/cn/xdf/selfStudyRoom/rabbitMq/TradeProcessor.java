package cn.xdf.selfStudyRoom.rabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import cn.xdf.selfStudyRoom.constant.MQConstant;
import cn.xdf.selfStudyRoom.service.MessageQueueService;

/**
 * 死信接收处理消费者
 * @author liuwei63
 *
 */
@Component  
@RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
public class TradeProcessor {

	@Autowired
	private MessageQueueService messageQueueService;

	@RabbitHandler
	public void process(String content) {
		try{
			DLXMessage message = JSONObject.parseObject(content, DLXMessage.class);
			messageQueueService.send(message.getQueueName(), message.getContent());
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

}
