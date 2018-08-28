package com.fanshuai.routing;



import com.fanshuai.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Routing模式生产者
 * @author FANSHUAI
 *
 */
public class Sender {

	private static final String EXCHANGE_NAME ="routing";//交换机名称
	
	public static void main(String[] args) throws Exception {
		
		String message1 = "routing模式发布的route1消息";
		String message2 = "routing模式发布的route2消息";
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//声明交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");//定义路由格式的交换机
		//第二个参数设置路由名称
		channel.basicPublish(EXCHANGE_NAME, "route1", null, message1.getBytes());
		channel.basicPublish(EXCHANGE_NAME, "route2", null, message2.getBytes());
		channel.close();
		connection.close();
	}
}
