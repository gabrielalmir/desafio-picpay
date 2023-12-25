package br.com.gabrielalmir.desafiopicpay.config.transfer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.strategy.TransferStrategy;
import br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.strategy.WireTransferStrategy;
import lombok.Getter;

@Configuration
@Getter
public class TransferConfig {
    private final String authorizationServiceUrl;
    private final String authorizedMessage = "Autorizado";

    public TransferConfig(@Value("${transfer.authorization.service.url}") String authorizationServiceUrl) {
        this.authorizationServiceUrl = authorizationServiceUrl;
    }

    @Bean
    Map<String, TransferStrategy> transferStrategies() {
        return Map.of(
            "WIRE_TRANSFER", new WireTransferStrategy()
        );
    }
}
