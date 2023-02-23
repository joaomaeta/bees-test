package br.com.bees.transfer.ms.amqp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.bees.transfer.ms.constants.TransferConstants;
import br.com.bees.transfer.ms.dto.PushNotificationDTO;
import br.com.bees.transfer.ms.dto.TransferDataDTO;
import br.com.bees.transfer.ms.service.TransferService;

@Component
public class TransferListener {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
    private TransferService service;
	
	@RabbitListener(queues = "transfer.request")
	public void transferRequest(@Payload TransferDataDTO transferData) {
		String messageResponse = service.sendTransfer(transferData);
		sendPushNotifications(new PushNotificationDTO(transferData, messageResponse));
	}
	
	private void sendPushNotifications(PushNotificationDTO push) {
		rabbitTemplate.convertAndSend(TransferConstants.EXCHANGE_NOTIFICATION.getDescription(), 
				TransferConstants.QUEUE_NOTIFICATION.getDescription(), 
				push);
	}

}
