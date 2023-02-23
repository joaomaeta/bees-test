package br.com.bees.transfer.ms.constants;

public enum TransferConstants {
	EXCHANGE_TRANSFER("transfer.ex"),
	EXCHANGE_NOTIFICATION("notification.ex"),
	EXCHANGE_DLX("dlx.ex"),
	QUEUE_TRANSFER("transfer.request"),
	QUEUE_TRANSFER_DLX("transfer.dlx"),
	QUEUE_NOTIFICATION("transfer.notification"),
	URI("http://baas.com"),
	URI_TRANSFER("/v1/transfers");
	
	private String description;
	
	TransferConstants(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
