package br.com.gabrielalmir.desafiopicpay.services.transfer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gabrielalmir.desafiopicpay.config.transfer.TransferConfig;
import br.com.gabrielalmir.desafiopicpay.presentation.dtos.transfer.TransferAuthorizationDto;

@Service
public class TransferAuthorizationService {
    private final String authorizationServiceUrl;
    private final RestTemplate restTemplate;
    private final String authorizedMessage;

    public TransferAuthorizationService(TransferConfig transferConfig) {
        this.restTemplate = new RestTemplate();
        this.authorizationServiceUrl = transferConfig.getAuthorizationServiceUrl();
        this.authorizedMessage = transferConfig.getAuthorizedMessage();
    }

    public boolean authorizeTransaction() throws Exception {
        var response = restTemplate.getForEntity(authorizationServiceUrl, String.class);
        var mapper = new ObjectMapper();
        var authorizationResponse = mapper.readValue(response.getBody(), TransferAuthorizationDto.class);

        return authorizationResponse.message().equals(authorizedMessage);
    }
}
