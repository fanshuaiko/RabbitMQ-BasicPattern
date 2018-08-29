package com.fanshuai.topics;



import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Topics(通配符)模式生产者
 * Topics模式的消费者而可以通过通配符类接受相应的消息
 * Routing模式只能100%匹配routingKey
 * @author FANSHUAI
 *
 */
public class Sender {

	private static final String EXCHANGE_NAME ="topics";//交换机名称
	
	public static void main(String[] args) throws Exception {
		
		String message = "topics模式发布的消息";
		String routingKey1 = "topic.one";
		String routingKey2 = "topic.two.one";
		String routingKey3 = "topic.two.two";
		String routingKey4 = "topic.three";
		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//声明交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");//定义路由格式的交换机
		//第二个参数设置路由名称
		channel.basicPublish(EXCHANGE_NAME, routingKey1, null, (message + routingKey1).getBytes());
		channel.basicPublish(EXCHANGE_NAME, routingKey2, null, (message + routingKey2).getBytes());
		channel.basicPublish(EXCHANGE_NAME, routingKey3, null, (message + routingKey3).getBytes());
		channel.basicPublish(EXCHANGE_NAME, routingKey4, null, (message + routingKey4).getBytes());
		channel.close();
		connection.close();
	}
}
