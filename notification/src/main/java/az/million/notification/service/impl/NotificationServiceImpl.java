package az.million.notification.service.impl;

import az.million.clients.notification.NotificationRequest;
import az.million.notification.entity.Notification;
import az.million.notification.repo.NotificationRepository;
import az.million.notification.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationServiceImpl(NotificationRepository notificationRepository)
        implements NotificationService {

    @Override
    public void sendNotification(NotificationRequest notificationRequest) {

        notificationRepository.save(Notification.builder()
                .sender("Togrul Mamishov")
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .toCustomerId(notificationRequest.toCustomerId())
                .build());
    }
}
