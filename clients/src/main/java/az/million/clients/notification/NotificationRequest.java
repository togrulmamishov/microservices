package az.million.clients.notification;

public record NotificationRequest(String message,
                                  String toCustomerEmail,
                                  Integer toCustomerId) {
}
