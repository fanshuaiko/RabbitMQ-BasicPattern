package com.fanshuai.util;
/**
 * 
 * @author FANSHUAI
 *连接RabbitMQ server工具类
 */

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			connectionFactory.setHost("localhost");//连接server地址
			connectionFactory.setPort(5672);//
			connectionFactory.setUsername("testUser");
			connectionFactory.setPassword("testUser");
			connectionFactory.setVirtualHost("/testVirtualHost");
			connection = connectionFactory.newConnection();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (TimeoutException e) {
			
			e.printStackTrace();
		}
		return connection;
	}
}
