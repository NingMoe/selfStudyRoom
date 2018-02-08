package cn.xdf.selfStudyRoom.rabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cn.xdf.selfStudyRoom.domain.entity.User;


/**
 * 
 * @author liuwei63
 *
 */
@Component
@RabbitListener(queues ="hello")
public class Receiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("Receiver String: " + msg);
    }
    
    @RabbitHandler
    public void process(User user) {
        System.out.println("Receiver object : " + user);
    }
}
