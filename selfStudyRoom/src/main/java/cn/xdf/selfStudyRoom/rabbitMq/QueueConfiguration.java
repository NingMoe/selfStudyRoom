package cn.xdf.selfStudyRoom.rabbitMq;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.xdf.selfStudyRoom.constant.MQConstant;

/**
 * 自定义各种队列配置
 * @author liuwei63
 *
 */
@Configuration 
public class QueueConfiguration {
	
	private static Logger log = LogManager.getLogger(QueueConfiguration.class);
	
	/**
	 * 信道配置
	 * RabbitMQ提供了四种Exchange：fanout,direct,topic,header
	 * DirectExchange:任何发送到DirectExchange的消息都会被转发到RouteKey中指定的Queue
	 * FanoutExchange:任何发送到FanoutExchange的消息都会被转发到与该Exchange绑定(Binding)的所有Queue上。
	 * TopicExchange: 任何发送到TopicExchange的消息都会被转发到所有关心RouteKey中指定话题的Queue上
	 * @return
	 */
	@Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(MQConstant.DEFAULT_DIRECT_EXCHANGE,true,false);
    }
	
	@Bean
    public TopicExchange exchange() {
        return new TopicExchange(MQConstant.DEFAULT_TOPIC_EXCHANGE);
    }
	
	/********************************** 延迟队列  *******************************************************************/
    /**
     * 死信转发队列
     * @return
     */
    @Bean  
    public Queue repeatTradeQueue() {  
        Queue queue = new Queue(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME,true,false,false);  
        return queue;   
    }  
    
    /**
     * 死信转发队列绑定
     * @return
     */
    @Bean  
    public Binding  drepeatTradeBinding() {  
        return BindingBuilder.bind(repeatTradeQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);  
    }  
    
    /**
     * 死信队列
     * @return
     */
    @Bean  
    public Queue deadLetterQueue() {  
        Map<String, Object> arguments = new HashMap<String, Object>();  
        arguments.put("x-dead-letter-exchange", MQConstant.DEFAULT_DIRECT_EXCHANGE);  
        arguments.put("x-dead-letter-routing-key", MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);  
        Queue queue = new Queue(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME,true,false,false,arguments);  
        log.info("arguments :" + queue.getArguments());  
        return queue;   
    }  
    
    /**
     * 死信队列绑定
     * @return
     */
    @Bean  
    public Binding deadLetterBinding() {  
        return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME);  
    }
       
    
   /********************************** 默认队列  *******************************************************************/  
    
    /**
     * 默认队列
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(MQConstant.DEFAULT_QUEUE_NAME, true);//设置为ture表示队列持久化
    }
    
    /**
     * 默认队列的路由绑定
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(MQConstant.DEFAULT_ROUTING_KEY);
    }
    
    /********************************** 测试TopicExchange类型用到的队列  *******************************************************************/
    
    @Bean
    public Queue queue1() {
        return new Queue("topic.queue1");
    }

    @Bean
    public Queue queue2() {
        return new Queue("topic.queue2");
    }
    
    @Bean
    public Binding bindingQueue1() {
        return BindingBuilder.bind(queue1()).to(exchange()).with("topic.queue1");
    }

    @Bean
    public Binding bindingQueue2() {
        return BindingBuilder.bind(queue2()).to(exchange()).with("topic.#");
    }

    
}
