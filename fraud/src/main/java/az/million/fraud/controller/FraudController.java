package az.million.fraud.controller;

import az.million.clients.fraud.FraudCheckResponse;
import az.million.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public record FraudController(FraudCheckService service) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        var isFraudulentCustomer = service.isFraudulentCustomer(customerId);

        log.info("fraud check request for customer {}", customerId);

        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
