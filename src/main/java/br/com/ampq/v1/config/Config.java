package br.com.ampq.v1.config;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class Config {

	//@Value("${activemq.broker-url}")
	private String brokerUrl = "activemq.broker-url";
	
	@Bean
	public Queue queue(){
		return new ActiveMQQueue("standlone.queue");
	}
	
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory(){
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(brokerUrl);
		return factory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate(){
		return new JmsTemplate(activeMQConnectionFactory());
	}
	
}