package cn.xdf.selfStudyRoom.rabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import cn.xdf.selfStudyRoom.constant.MQConstant;

@Component
@RabbitListener(queues =MQConstant.DEFAULT_QUEUE_NAME)
public class CallBackReceiver {
	
	@RabbitHandler
    public void process(String msg) {
        System.out.println("callBackReceiver String: " + msg);
    }
}
