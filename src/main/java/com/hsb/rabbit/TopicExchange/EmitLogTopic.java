package com.hsb.rabbit.TopicExchange;

import java.util.UUID;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 注：主题类型的转发器非常强大，可以实现其他类型的转发器。
 * 当一个队列与绑定键#绑定，将会收到所有的消息，类似fanout类型转发器。
 * 当绑定键中不包含任何#与*时，类似direct类型转发器。
 * @author admin
 */
public class EmitLogTopic {

	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] argv) throws Exception {
		// 创建连接和频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 声明转发器的类型
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");

		String[] routing_keys = new String[] { "kernal.info", "cron.warning",
				"auth.info", "kernel.critical" };
		for (String routing_key : routing_keys) {
			String msg = UUID.randomUUID().toString();
			channel.basicPublish(EXCHANGE_NAME, routing_key, null,msg.getBytes());
			System.out.println("[x]Sent routingKey = " + routing_key+ " ,msg = " + msg + ".");
		}

		channel.close();
		connection.close();
	}
}
