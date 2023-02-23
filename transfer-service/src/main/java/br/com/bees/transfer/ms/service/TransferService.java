package br.com.bees.transfer.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.bees.transfer.ms.constants.TransferConstants;
import br.com.bees.transfer.ms.dto.TransferDataDTO;
import reactor.core.publisher.Mono;

@Service
public class TransferService {

	@Autowired
	private WebClient webClient;
	
	public String sendTransfer(TransferDataDTO data) {
		Mono<String> transferValue = this.webClient
				.post()
				.uri(TransferConstants.URI_TRANSFER.getDescription())
				.body(BodyInserters.fromValue(data))
				.retrieve()
				.bodyToMono(String.class);
		
		return transferValue.block();
	}
}
