package br.com.bees.notification.amqp.listener;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import br.com.bees.notification.dto.PushNotificationDTO;
import br.com.bees.notification.service.NotificationService;

@RunWith(MockitoJUnitRunner.class)
public class NotificationListenerTest {

	
	@InjectMocks
	private NotificationListener listener;
	@Mock
	private RabbitTemplate rabbitTemplate;
	@Mock
	private NotificationService service;
	@Mock
	private PushNotificationDTO push;
	
	@Test
	public void pushNotificationRequestTest() {
		when(service.sendNotification(push)).thenReturn("response.ok");
		listener.pushNotificationRequest(push);
	}
}
