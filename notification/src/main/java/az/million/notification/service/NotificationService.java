package az.million.notification.service;

import az.million.clients.notification.NotificationRequest;

public interface NotificationService {

    void send(NotificationRequest notificationRequest);
}
