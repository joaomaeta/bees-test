package br.com.bees.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.bees.notification.constants.NotificationConstants;

@SpringBootApplication
@EnableEurekaClient
public class NotificationApplication {
	
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder
				.baseUrl(NotificationConstants.URI.getDescription())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

}
