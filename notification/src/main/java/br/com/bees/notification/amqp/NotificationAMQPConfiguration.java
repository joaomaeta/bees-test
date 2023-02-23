package br.com.bees.notification.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.bees.notification.constants.NotificationConstants;

@Configuration
public class NotificationAMQPConfiguration {

	@Bean
	public RabbitAdmin createRabbitAdmin(ConnectionFactory connection) {
		return new RabbitAdmin(connection);
	}
	
	@Bean
	public ApplicationListener<ApplicationReadyEvent> initAdmin(RabbitAdmin rabbitAdmin) {
		return event -> rabbitAdmin.initialize();
	}
	
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connection,
										Jackson2JsonMessageConverter messageConverter) {
		
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connection);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}
	
	@Bean
	public Queue notificationQueue() {
		return QueueBuilder
				.nonDurable(NotificationConstants.QUEUE_NOTIFICATION.getDescription())
				.deadLetterExchange(NotificationConstants.QUEUE_NOTIFICATION_DLX.getDescription())
				.build();
	}
	
	@Bean
	public DirectExchange directExchangeNotification() {
		return ExchangeBuilder
				.directExchange(NotificationConstants.EXCHANGE_NOTIFICATION.getDescription())
				.build();
	}
	
	@Bean
	public DirectExchange directExchangeNotificationDlx() {
		return ExchangeBuilder
				.directExchange(NotificationConstants.QUEUE_NOTIFICATION_DLX.getDescription())
				.build();
	}
	
	@Bean
	public Binding bindingTransfer() {
		return BindingBuilder
                .bind(notificationQueue())
                .to(directExchangeNotification())
                .with(NotificationConstants.QUEUE_NOTIFICATION.getDescription());
	}
}
