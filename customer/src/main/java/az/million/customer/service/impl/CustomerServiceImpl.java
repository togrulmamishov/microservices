package az.million.customer.service.impl;

import az.million.amqp.RabbitMQMessageProducer;
import az.million.clients.fraud.FraudClient;
import az.million.clients.notification.NotificationClient;
import az.million.clients.notification.NotificationRequest;
import az.million.customer.dto.CustomerRequest;
import az.million.customer.model.Customer;
import az.million.customer.repo.CustomerRepository;
import az.million.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerServiceImpl(CustomerRepository customerRepository,
                                  FraudClient fraudClient,
                                  RabbitMQMessageProducer rabbitMQMessageProducer) implements CustomerService {

    @Override
    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();

        customerRepository.saveAndFlush(customer);

        var fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                String.format("Hi %s, welcome to my tutorial", customer.getFirstName() + " " + customer.getLastName()),
                customer.getEmail(),
                customer.getId()
        );
        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");

    }
}
