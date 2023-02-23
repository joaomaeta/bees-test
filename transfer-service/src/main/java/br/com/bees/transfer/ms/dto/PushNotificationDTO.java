package br.com.bees.transfer.ms.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushNotificationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -942492829455972004L;
	public PushNotificationDTO(TransferDataDTO transferData, String description) {
		this.clientRequestId = transferData.getClientRequestId();
		this.customerId = transferData.getSenderId();
		this.description = description;
	}
	private String clientRequestId;
	private String customerId;
	private String description;
}
