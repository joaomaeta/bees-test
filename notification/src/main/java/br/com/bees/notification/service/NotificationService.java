package br.com.bees.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.bees.notification.constants.NotificationConstants;
import br.com.bees.notification.dto.PushNotificationDTO;
import reactor.core.publisher.Mono;

@Service
public class NotificationService {

	@Autowired
	private WebClient webClient;
	
	public String sendNotification(PushNotificationDTO push) {
		Mono<String> pushNotification = this.webClient
				.post()
				.uri(uriBuilder -> uriBuilder
						.path(NotificationConstants.URI_NOTIFICATION.getDescription())
						.build(push.getCustomerId()))
				.header(NotificationConstants.CLIENT_REQUEST_ID.getDescription(), push.getClientRequestId())
				.body(BodyInserters.fromValue(push.getDescription()))
				.retrieve()
				.bodyToMono(String.class);

		return pushNotification.block();
	}
}
