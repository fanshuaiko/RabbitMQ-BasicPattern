package com.fanshuai.hellowork;



import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * hello简单模式消息的发送者
 * @author FANSHUAI
 *
 */
public class Sender {

	private static final String QUEUE ="testhello";
	
	public static void main(String[] args) throws Exception {
		String message = "hello word3";
		//1.获取连接
		Connection connection = ConnectionUtil.getConnection();
		//2.创建通道
		Channel channel = connection.createChannel();
		//3.声明队列,如果不存在才会创建
		//第一个参数：队列名称，第二个参数：是否持久化队列，rabbitmq的队列是在内存中的，如果重启消息则会丢失，如果设置为true则会保存到erlang自带的数据库中，重启会重新读取
		//第三个参数：是否排外，有两个作用，第一个作用:当连接关闭后是否自动删除队列，作用二:是否私有化当前队列，如果为true，则其他通道不能访问当前队列，一般是一个队列只适用于一个消费者
		//第四个参数：是否自动删除
		channel.queueDeclare(QUEUE, false, false, false, null);
		//4.发送消息
		channel.basicPublish("", QUEUE, null, message.getBytes());
		//打印消息
		System.out.println("Sender发送消息：" + message);
		//5.关闭连接
		connection.close();
		channel.close();
		
	}
}
