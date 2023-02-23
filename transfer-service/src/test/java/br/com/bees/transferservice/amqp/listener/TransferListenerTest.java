package br.com.bees.transferservice.amqp.listener;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import br.com.bees.transfer.ms.amqp.listener.TransferListener;
import br.com.bees.transfer.ms.dto.PushNotificationDTO;
import br.com.bees.transfer.ms.dto.TransferDataDTO;
import br.com.bees.transfer.ms.service.TransferService;

@RunWith(MockitoJUnitRunner.class)
public class TransferListenerTest {

	@InjectMocks
	private TransferListener listener;
	@Mock
	private RabbitTemplate rabbitTemplate;
	@Mock
	private TransferService transferService;
	@Mock
	TransferDataDTO data;
	
	@Test
	public void transferRequestTest() {
		when(transferService.sendTransfer(data)).thenReturn("response.OK");
		listener.transferRequest(data);
		
		Mockito.verify(rabbitTemplate).convertAndSend(anyString(), 
														anyString(), 
														any(PushNotificationDTO.class));
	}
}
