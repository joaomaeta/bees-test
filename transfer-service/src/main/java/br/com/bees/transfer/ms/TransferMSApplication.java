package br.com.bees.transfer.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.bees.transfer.ms.constants.TransferConstants;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootApplication
@EnableEurekaClient
public class TransferMSApplication {

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder
				.baseUrl(TransferConstants.URI.getDescription())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(TransferMSApplication.class, args);
	}

}
