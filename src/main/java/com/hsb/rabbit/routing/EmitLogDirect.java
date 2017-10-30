package com.hsb.rabbit.routing;

import java.util.Random;
import java.util.UUID;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 之前我们使用fanout类型的转发器，但是并没有给我们带来更多的灵活性：仅仅可以愚蠢的转发。
 * 我们将会使用direct类型的转发器进行替代。
 * direct类型的转发器背后的路由转发算法很简单：消息会被推送至绑定键（binding key）和消息发
 * @author admin
 *
 */
public class EmitLogDirect {

	private static final String EXCHANGE_NAME = "ex_logs_direct";
	private static final String[] SEVERITIES = { "info", "warning", "error" };

	public static void main(String[] argv) {
		try {
			// 创建连接和频道
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			// 声明转发器的类型
			channel.exchangeDeclare(EXCHANGE_NAME, "direct");
			
			// 发送6条消息
			for (int i = 0; i < 6; i++) {
				String severity = getSeverity();
				String message = severity + "_log :"+ UUID.randomUUID().toString();
				// 发布消息至转发器，指定routingkey
				channel.basicPublish(EXCHANGE_NAME, severity, null,message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");
			}

			channel.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 随机产生一种日志类型
	 * 
	 * @return
	 */
	private static String getSeverity() {
		Random random = new Random();
		int ranVal = random.nextInt(3);
		return SEVERITIES[ranVal];
	}
}
