package org.mo.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 队列模式
 * @author mo
 *
 */
public class AppProducer {
	
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
			
			// 6. 创建一个生产者
			MessageProducer producer = session.createProducer(destination);
			
			for(int i = 0; i < 10; i++){
				// 7. 创建消息
				TextMessage msg = session.createTextMessage("msg:"+i);
				
				// 8. 发布消息
				producer.send(msg);
				
				System.out.println("发送消息："+msg.getText());
			}
			
			// 9. 关闭连接
			conn.close();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
