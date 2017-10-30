package com.hsb.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Work {
	// 队列名称
	private final static String QUEUE_NAME = "workqueue";

	public static void main(String[] args) {
		try {
			// 区分不同工作进程的输出
			int hashCode = Work.class.hashCode();
			// 创建连接
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			Connection connection = factory.newConnection();
			//打开频道
			Channel channel = connection.createChannel();
			//声明队列
			boolean durable = true;//持久化队列
			channel.queueDeclare("QUEUE_NAME", durable, false, false, null);
			//注：RabbitMQ不允许使用不同的参数重新定义一个队列，所以已经存在的队列，我们无法修改其属性。
			//channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println(hashCode+ " [*] Waiting for messages.");
			//产生一个消费者
			QueueingConsumer consumer = new QueueingConsumer(channel);
			//指定消费队列
			boolean ack = false ; //打开应答机制  
			channel.basicConsume(QUEUE_NAME, ack, consumer);
			//RabbitMQ默认是当消息到达队列进行转发
			//设置RabbitMQ不要在同一时间给一个消费者超过一条消息。换句话说，只有在消费者空闲的时候会发送下一条信息。
			int prefetchCount = 1;  
			channel.basicQos(prefetchCount);
			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				
				String message = new String(delivery.getBody());
				
				System.out.println(hashCode + " [x] Received '" + message + "'");
				//模拟操作
				doWork(message);
				
				System.out.println(hashCode + " [x] Done");
				 //另外需要在每次处理完成一个消息后，手动发送一次应答.任务已经完成
	            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);  

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 每个点耗时1s
	 * 
	 * @param task
	 * @throws InterruptedException
	 */
	private static void doWork(String task) throws InterruptedException {
		for (char ch : task.toCharArray()) {
			if (ch == '.')
				Thread.sleep(1000);
		}
	}
}
