package br.com.bees.bff.transfer.controller;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bees.bff.transfer.constants.TransferConstants;
import br.com.bees.bff.transfer.dto.TransferDataDTO;


@RestController
@RequestMapping("/v1/transfers")
public class TransferController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping("/{customerId}")
	public ResponseEntity<String> transferValue(@RequestHeader Map<String, String> headers, 
												@PathVariable String customerId, 
												@RequestBody TransferDataDTO data) throws Exception {
		try {
			data.setClientRequestId(headers.get(TransferConstants.CLIENT_REQUEST_ID.getDescription().toLowerCase()));
			data.setSenderId(customerId);
			
			rabbitTemplate.convertAndSend(TransferConstants.EXCHANGE.getDescription(), 
											TransferConstants.QUEUE_TRANSFER.getDescription(), 
											data);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(TransferConstants.TRANSFER_OK.getDescription());
		}catch(Exception ex) {
			throw new Exception("ERRO: " + ex.getMessage());
		}
	}
}
