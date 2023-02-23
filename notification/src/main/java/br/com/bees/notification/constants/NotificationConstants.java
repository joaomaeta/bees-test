package br.com.bees.notification.constants;

public enum NotificationConstants {
	URI("http://baas.com"),
	EXCHANGE_NOTIFICATION("notification.ex"),
	URI_NOTIFICATION("/v1/notifications/{customerId}"),
	CLIENT_REQUEST_ID("clientRequestId"),
	QUEUE_NOTIFICATION_DLX("notification.dlx"),
	QUEUE_NOTIFICATION("transfer.notification");
	
	private String description;
	
	NotificationConstants(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
