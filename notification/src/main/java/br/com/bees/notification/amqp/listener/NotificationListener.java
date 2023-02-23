package br.com.bees.notification.amqp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bees.notification.dto.PushNotificationDTO;
import br.com.bees.notification.service.NotificationService;

@Component
public class NotificationListener {
	
	@Autowired
	private NotificationService service;
	
	@RabbitListener(queues = "transfer.notification")
	public void pushNotificationRequest(PushNotificationDTO push) {
		String responseMessage = service.sendNotification(push);
		System.out.println(push + " " + responseMessage);
	}
}
