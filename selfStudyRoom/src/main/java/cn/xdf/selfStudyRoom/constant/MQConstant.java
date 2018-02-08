package cn.xdf.selfStudyRoom.constant;

/**
 * Rabbit消息队列相关常量
 * @author liuwei63
 *
 */
public class MQConstant {
	// 交换机名称(DIRECTEXCHANGE类型)
	public static final String DEFAULT_DIRECT_EXCHANGE = "exchange_callback";
	
	// 交换机名称(TOPICXCHANGE类型)
	public static final String DEFAULT_TOPIC_EXCHANGE = "topic_exchange";

	// DLX QUEUE(死信队列)
	public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "ssr.dead.letter.queue";

	// DLX repeat QUEUE(死信转发队列)
	public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "ssr.repeat.trade.queue";

	// 默认消息队列名称
	public static final String DEFAULT_QUEUE_NAME = "queue_callback";
	
	// 默认路由
	public static final String DEFAULT_ROUTING_KEY="routingKey_callback";
}
