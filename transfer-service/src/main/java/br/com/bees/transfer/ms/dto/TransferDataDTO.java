package br.com.bees.transfer.ms.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8145825865536744887L;
	private String clientRequestId;
	private String senderId;
	private String receiverId;
	private int amount;
}
