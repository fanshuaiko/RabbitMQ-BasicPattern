package com.fanshuai.pubsub;

import java.io.IOException;

import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 订阅者1
 * @author FANSHUAI
 *
 */
public class Reciever1 {
	private static final String QUEUE = "pubsubqueue1";//队列名称
	private static final String EXCHANGE_NAME ="pubsub";//交换机名称

	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE, false, false, false, null);
		//将当前队列绑定到交换机		
		channel.queueBind(QUEUE, EXCHANGE_NAME, "");
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				//当接受到消息时调用
			System.out.println("Rreciever1接受的订阅消息：" + new String(body));
			}
		};
		//第二个参数是否自动确认消息，此处设为自动确认
		channel.basicConsume(QUEUE, true, consumer);
	}
}
