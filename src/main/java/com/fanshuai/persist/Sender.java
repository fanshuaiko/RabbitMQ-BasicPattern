package com.fanshuai.persist;



import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * 消息的持久化
 * 消息持久化之后，即使RabbitMQ重启，消息也不会丢失
 * @author FANSHUAI
 *
 */
public class Sender {

	private static final String EXCHANGE_NAME ="topics";//交换机名称
	
	public static void main(String[] args) throws Exception {
		
		String message = "topics模式发布的消息";
		String routingKey1 = "topic.one";
		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//1.第三个参数为是否持久化
		channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
		//2.要将消息持久化，第二个参数设置如下
		channel.basicPublish(EXCHANGE_NAME, routingKey1, MessageProperties.PERSISTENT_TEXT_PLAIN, (message + routingKey1).getBytes());
		channel.close();
		connection.close();
	}
}
