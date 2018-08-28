package com.fanshuai.pubsub;



import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * publish-subscribe（发布订阅） 模式消息的发送者
 * @author FANSHUAI
 *
 */
public class Sender {

	private static final String EXCHANGE_NAME ="pubsub";//交换机名称
	
	public static void main(String[] args) throws Exception {
		
		String message = "publish-subscribe模式发布的消息";
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//声明交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//定义一个交换机，类型为“fanout”，即订阅发布模式
		//订阅发布模式，因为消息是先发送到交换机中的，交换机没有保存功能，如果没有消费者的话消息会丢失
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		channel.close();
		connection.close();
	}
}
