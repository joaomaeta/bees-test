package br.com.bees.bfftransfer.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import br.com.bees.bff.transfer.constants.TransferConstants;
import br.com.bees.bff.transfer.controller.TransferController;
import br.com.bees.bff.transfer.dto.TransferDataDTO;

@RunWith(MockitoJUnitRunner.class)
public class TransferControllerTest {

	@InjectMocks
	private TransferController controller;
	
	@Mock
	private RabbitTemplate rabbitTemplate;
	
	@Mock
	private Map<String, String> headers;
	
	@Mock
	TransferDataDTO data;
	
	private static final String TESTE = "testeID";
	
	@Test
    public void transferValueTest() throws Exception {
		when(headers.get(anyString())).thenReturn(anyString());
		
		controller.transferValue(headers, TESTE, data);
		Mockito.verify(rabbitTemplate).convertAndSend(TransferConstants.EXCHANGE.getDescription(), 
														TransferConstants.QUEUE_TRANSFER.getDescription(), 
														data);
	}
}
