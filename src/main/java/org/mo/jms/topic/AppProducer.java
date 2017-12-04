package org.mo.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 主题模式 生产者
 * @author mo
 *
 */
public class AppProducer {

	// 定义常量
	private static final String URL = "tcp://127.0.0.1:61616";
	private static final String TOPICNAME = "topic-test";
	
	public static void main(String[] args) {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
			Connection conn = factory.createConnection();
			conn.start();
			Session session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Destination detination = session.createTopic(TOPICNAME);
			
			MessageProducer producer = session.createProducer(detination);
			
			for (int i = 0; i < 10; i++) {
				TextMessage textMessage = session.createTextMessage("topic-msg-producer:"+i);
				producer.send(textMessage);
				System.out.println("生产者生产:"+textMessage.getText());
			}
			
			conn.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
