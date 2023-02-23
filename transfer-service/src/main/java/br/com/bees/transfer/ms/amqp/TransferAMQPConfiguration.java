package br.com.bees.transfer.ms.amqp;

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

import br.com.bees.transfer.ms.constants.TransferConstants;

@Configuration
public class TransferAMQPConfiguration {
	
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
	public Queue transferQueue() {
		return QueueBuilder
				.nonDurable(TransferConstants.QUEUE_TRANSFER.getDescription())
				.deadLetterExchange(TransferConstants.EXCHANGE_DLX.getDescription())
				.build();
	}
	
	@Bean
	public Queue transferQueueDlx() {
		return QueueBuilder
				.nonDurable(TransferConstants.QUEUE_TRANSFER_DLX.getDescription())
				.build();
	}
	
	@Bean
	public DirectExchange directExchangeTransfer() {
		return ExchangeBuilder
				.directExchange(TransferConstants.EXCHANGE_TRANSFER.getDescription())
				.build();
	}
	
	@Bean
	public DirectExchange directExchangeNotification() {
		return ExchangeBuilder
				.directExchange(TransferConstants.EXCHANGE_NOTIFICATION.getDescription())
				.build();
	}
	
	@Bean
	public DirectExchange directExchangeDLX() {
		return ExchangeBuilder
				.directExchange(TransferConstants.EXCHANGE_DLX.getDescription())
				.build();
	}
	
	@Bean
	public Binding bindingTransfer() {
		return BindingBuilder
                .bind(transferQueue())
                .to(directExchangeTransfer())
                .with(TransferConstants.QUEUE_TRANSFER.getDescription());
	}
}
