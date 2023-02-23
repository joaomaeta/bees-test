package br.com.bees.notification.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushNotificationDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2009098211073675660L;
	private String clientRequestId;
	private String customerId;
	private String description;
}
