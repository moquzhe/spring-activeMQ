package org.mo.spring.consumer;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class AppConsumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
	}
}
