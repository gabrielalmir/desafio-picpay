package br.com.gabrielalmir.desafiopicpay.config.transfer;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gabrielalmir.desafiopicpay.domain.transfer.strategy.TransferStrategy;
import br.com.gabrielalmir.desafiopicpay.domain.transfer.strategy.WireTransferStrategy;

@Configuration
public class TransferConfig {

    @Bean
    Map<String, TransferStrategy> transferStrategies() {
        return Map.of(
            "WIRE_TRANSFER", new WireTransferStrategy()
        );
    }
}
