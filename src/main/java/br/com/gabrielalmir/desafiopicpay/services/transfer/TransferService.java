package br.com.gabrielalmir.desafiopicpay.services.transfer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.Transfer;
import br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.TransferToUsers;
import br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.strategy.TransferStrategy;
import br.com.gabrielalmir.desafiopicpay.presentation.dtos.transfer.TransferDto;
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

    public List<Transfer> findAllTransfers() {
        return transferRepository.findAll();
    }

    public Transfer findTransferById(Long id) {
        return transferRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Transfer %s not found".formatted(id)));
    }

    public void createTransfer(TransferDto transferDto) throws Exception {
        var fromCustomer = customerService.findCustomerById(transferDto.from());
        var toCustomer = customerService.findCustomerById(transferDto.to());
        var amount = transferDto.amount();

        var isAuthorizedTransaction = transferAuthorizationService.authorizeTransaction();

        var transfer = new TransferToUsers(transferStrategies.get(transferDto.type().name()));
        transfer.setFromCustomer(fromCustomer);
        transfer.setToCustomer(toCustomer);
        transfer.setAmount(amount);

        var isValidTransaction = transfer.validateTransaction(isAuthorizedTransaction);

        if (!isValidTransaction) {
            throw new Exception("Invalid transaction");
        }

        transfer.executeTransaction(isAuthorizedTransaction);
        transferRepository.save(transfer);

        customerService.upsertCustomers(List.of(fromCustomer, toCustomer));
    }
}
