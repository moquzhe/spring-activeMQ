package org.mo.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 主题模式 消费者
 * @author mo
 *
 */
public class AppConsumer {
	
	// 定义消费的常量
	private static final String URL= "tcp://127.0.0.1:61616";
	private static final String TOPICNAME = "topic-test";
	
	public static void main(String[] args) {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
			Connection conn = factory.createConnection();
			conn.start();
			
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createTopic(TOPICNAME);
			
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					TextMessage msg = (TextMessage) message;
					try {
						System.out.println("消费者消费:"+msg.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
