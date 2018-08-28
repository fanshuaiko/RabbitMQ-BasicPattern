package com.fanshuai.workqueue;

import java.io.IOException;

import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 消费者1
 * @author FANSHUAI
 *
 */
public class Reciever2 {
	private static final String QUEUE = "workqueue";

	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE, false, false, false, null);
		//告诉服务器，在我们没有确认消息之前不要给我发送新的消息
		channel.basicQos(1);
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				//当接受到消息时调用
			System.out.println("Rreciever2接受的消息：" + new String(body));
			try {
				//此处的模拟耗时的时间长于Receiver1，可以更好的看到“work queue 模式的能者多劳效果”
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			channel.basicAck(envelope.getDeliveryTag(), false);//手动确认消息，第二个参数为false即为确认消息,true为拒绝消息
			}
		};
		//第二个参数是否自动确认消息，此处设为不自动确认
		channel.basicConsume(QUEUE, false, consumer);
	}
}
