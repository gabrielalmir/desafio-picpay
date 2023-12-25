package br.com.gabrielalmir.desafiopicpay.services.transfer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.gabrielalmir.desafiopicpay.domain.transfer.TransferToUsers;
import br.com.gabrielalmir.desafiopicpay.domain.transfer.strategy.TransferStrategy;
import br.com.gabrielalmir.desafiopicpay.presentation.dtos.TransferDto;
import br.com.gabrielalmir.desafiopicpay.repository.transfer.TransferRepository;
import br.com.gabrielalmir.desafiopicpay.services.customer.CustomerService;

@Service
public class TransferService {
    private final CustomerService customerService;
    private final TransferRepository transferRepository;
    private final TransferAuthorizationService transferAuthorizationService;

    private final Map<String, TransferStrategy> transferStrategies;

    public TransferService(
            CustomerService customerService, TransferRepository transferRepository,
            Map<String, TransferStrategy> transferStrategies,
            TransferAuthorizationService transferAuthorizationService) {
        this.customerService = customerService;
        this.transferRepository = transferRepository;
        this.transferStrategies = transferStrategies;
        this.transferAuthorizationService = transferAuthorizationService;
    }

    public void transfer(TransferDto transferDto) throws Exception {
        var fromCustomer = customerService.findCustomerById(transferDto.from());
        var toCustomer = customerService.findCustomerById(transferDto.to());
        var amount = transferDto.amount();

        var isAuthorizedTransaction = transferAuthorizationService.authorizeTransaction();

        var transfer = new TransferToUsers(fromCustomer, toCustomer, amount,
                transferStrategies.get(transferDto.type().name()));
        var isValidTransaction = transfer.validateTransaction(isAuthorizedTransaction);

        if (!isValidTransaction) {
            throw new Exception("Invalid transaction");
        }

        transfer.executeTransaction(isAuthorizedTransaction);
        transferRepository.save(transfer);

        customerService.saveCustomers(List.of(fromCustomer, toCustomer));
    }
}
