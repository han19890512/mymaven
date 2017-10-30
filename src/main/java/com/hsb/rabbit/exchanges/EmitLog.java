package com.hsb.rabbit.exchanges;

import java.util.Date;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 把一个消息发给多个消费者，这种模式称之为发布/订阅（类似观察者模式）。
 * @author admin
 *
 */
public class EmitLog {
	private final static String EXCHANGE_NAME = "ex_log";

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			// 创建连接和频道
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			// 声明转发器和类型
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
			//channel.queueDeclare(QUEUE_NAME, false, false, false, null);声明队列
			
			String message = new Date().toLocaleString() + " : log something";
			// 往转发器上发送消息
			channel.basicPublish(EXCHANGE_NAME,"", null, message.getBytes());

			System.out.println(" [x] Sent '" + message + "'");

			channel.close();
			connection.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
