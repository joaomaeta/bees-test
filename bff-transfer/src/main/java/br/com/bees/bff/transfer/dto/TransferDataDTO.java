package br.com.bees.bff.transfer.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4389015532628401361L;
	private String clientRequestId;
	private String senderId;
	private String receiverId;
	private int amount;
}
