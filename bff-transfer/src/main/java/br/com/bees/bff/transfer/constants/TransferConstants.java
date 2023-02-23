package br.com.bees.bff.transfer.constants;

public enum TransferConstants {
	TRANSFER_OK("The request was received successfully and it's being processed."),
	QUEUE_TRANSFER("transfer.request"),
	EXCHANGE("transfer.ex"),
	CLIENT_REQUEST_ID("clientRequestId");
	
	private String description;
	
	TransferConstants(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
