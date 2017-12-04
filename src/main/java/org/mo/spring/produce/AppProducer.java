package org.mo.spring.produce;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * 启动类
 * @author mo
 *
 */
public class AppProducer {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:produce.xml");
		ProducerService producerService = applicationContext.getBean(ProducerService.class);
		for (int i = 0; i < 10; i++) {
			producerService.sendMessage("hello:"+i);
		}
		applicationContext.close();
	}
}
