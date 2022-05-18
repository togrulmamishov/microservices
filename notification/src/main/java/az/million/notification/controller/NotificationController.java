package az.million.notification.controller;

import  az.million.clients.notification.NotificationRequest;
import az.million.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@Slf4j
public record NotificationController(NotificationService notificationService) {

    @PostMapping(path = "api/v1/notification")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("New notification... {}", notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}
