package com.fanshuai.routing;

import java.io.IOException;

import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * Routing模式消费者
 * @author FANSHUAI
 *
 */
public class Reciever2 {
	private static final String QUEUE = "routingqueue2";//队列名称
	private static final String EXCHANGE_NAME ="routing";//交换机名称

	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE, false, false, false, null);
		//将当前队列绑定到交换机		
		channel.queueBind(QUEUE, EXCHANGE_NAME, "route2");
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				//当接受到消息时调用
			System.out.println("Rreciever2接受的route消息：" + new String(body));
			}
		};
		//第二个参数是否自动确认消息，此处设为自动确认
		channel.basicConsume(QUEUE, true, consumer);
	}
}
