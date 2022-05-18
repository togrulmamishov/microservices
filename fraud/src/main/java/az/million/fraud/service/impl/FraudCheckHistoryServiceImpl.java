package az.million.fraud.service.impl;

import az.million.fraud.model.FraudCheckHistory;
import az.million.fraud.repo.FraudCheckHistoryRepository;
import az.million.fraud.service.FraudCheckService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckHistoryServiceImpl(FraudCheckHistoryRepository repository)
        implements FraudCheckService {

    @Override
    public boolean isFraudulentCustomer(Integer id) {
        repository.save(
                FraudCheckHistory.builder()
                        .customerId(id)
                        .createdAt(LocalDateTime.now())
                        .isFraudster(false)
                        .build()
        );
        return false;
    }
}
