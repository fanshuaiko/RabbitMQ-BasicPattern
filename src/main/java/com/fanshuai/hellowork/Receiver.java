package com.fanshuai.hellowork;

import java.io.IOException;


import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 消息的消费者
 * @author FANSHUAI
 *
 */
public class Receiver {

	private static final String QUEUE = "testhello";
	
	
	public static void main(String[] args) throws Exception{
		//获得连接
		Connection connection = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = connection.createChannel();
		//声明队列
		channel.queueDeclare(QUEUE, false, false, false, null);
		Consumer consumer = new DefaultConsumer(channel) {
			  @Override
			  public void handleDelivery(String consumerTag, Envelope envelope,
			                             AMQP.BasicProperties properties, byte[] body)
			      throws IOException {
			    String message = new String(body, "UTF-8");
			    System.out.println("Receiver接受消息：" + message);//输出消息
			  }
			};
			
		//接收消息
		channel.basicConsume(QUEUE, true, consumer);
		
	}
}
