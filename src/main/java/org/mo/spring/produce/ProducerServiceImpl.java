package org.mo.spring.produce;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class ProducerServiceImpl implements ProducerService{

	@Autowired
	private JmsTemplate jmsTemplate;
	
	/**
	 * 生产者运行主题模式：topicDestination
	 * 		  队列模式：queueDestination
	 */
	@Resource(name="topicDestination")
	private Destination destination;
	
	@Override
	public void sendMessage(final String message) {
		// 使用jmsTemplate 发送消息
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			// 创建消息
			public Message createMessage(Session session) throws JMSException {
				// 使用会话创建message
				TextMessage textMessage = session.createTextMessage(message);
				return textMessage;
			}
		});
		System.out.println("发送消息："+message);
	}

}
