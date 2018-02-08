package cn.xdf.selfStudyRoom.rabbitMq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.xdf.selfStudyRoom.constant.MQConstant;
import cn.xdf.selfStudyRoom.domain.entity.User;

/**
 * 不带回调的简单消息发送者
 * @author liuwei63
 *
 */
@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	/**
	 * 发送字符串
	 * @param msg
	 */
	public void send(String msg) {
		System.out.println("Sender String: " + msg);
		this.rabbitTemplate.convertAndSend("hello",msg);
	}
	
	/**
	 * 发送对象
	 * @param object
	 */
	public void send(User user) {
	    System.out.println("Sender object: " + user.toString());
	    this.rabbitTemplate.convertAndSend("hello", user);
	}
	
	public void sendQueue1(String msg) {	
	    System.out.println("Sender1 : " + msg);
	    this.rabbitTemplate.convertAndSend(MQConstant.DEFAULT_TOPIC_EXCHANGE, "topic.queue1",msg);
	}

	public void sendQueue2(String msg) {
	    System.out.println("Sender2 : " + msg);
	    this.rabbitTemplate.convertAndSend(MQConstant.DEFAULT_TOPIC_EXCHANGE, "topic.queue2", msg);
	}
	

}