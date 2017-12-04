package org.mo.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消费者
 * @author mo
 *
 */
public class AppConsumer {
	
	private static final String URL = "tcp://127.0.0.1:61616";
	private static final String QUEUENAME = "queue-test";
	
	public static void main(String[] args) {
		try {
			// 1. 创建ConnectionFactory
			ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
			
			// 2. 创建连接
			Connection conn = factory.createConnection();
			
			// 3. 启动连接
			conn.start();
			
			// 4. 创建会话(false,会话模式（自动应答模式）)
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// 5. 创建一个目标
			Destination destination = session.createQueue(QUEUENAME);
			
			// 6. 创建一个消费者
			MessageConsumer consumer = session.createConsumer(destination);
			
			// 7. 创建一个监听器
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message msg) {
					TextMessage textMessage = (TextMessage) msg;
					try {
						System.out.println("消费消息："+textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
