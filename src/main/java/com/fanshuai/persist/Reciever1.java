package com.fanshuai.persist;

import java.io.IOException;

import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * @author FANSHUAI
 *
 */
public class Reciever1 {
	private static final String QUEUE = "topicqueue2";//队列名称
	private static final String EXCHANGE_NAME ="topics";//交换机名称

	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//3.第二个参数为是否持久化
		channel.queueDeclare(QUEUE, true, false, false, null);
		channel.queueBind(QUEUE, EXCHANGE_NAME, "topic.*");
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
			System.out.println("Rreciever1接受的消息：" + new String(body));
			}
		};
		channel.basicConsume(QUEUE, true, consumer);
	}
}
