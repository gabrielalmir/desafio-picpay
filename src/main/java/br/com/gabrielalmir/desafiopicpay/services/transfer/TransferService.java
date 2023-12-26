package br.com.gabrielalmir.desafiopicpay.services.transfer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.gabrielalmir.desafiopicpay.domain.entities.customer.Customer;
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

    public void saveTransfer(Transfer transfer, Customer fromCustomer, Customer toCustomer) {
        customerService.upsertCustomers(List.of(fromCustomer, toCustomer));
        transferRepository.save(transfer);
    }

    public void createTransfer(TransferDto transferDto) throws Exception {
        var optionalFromCustomer = customerService.findCustomerById(transferDto.getFrom());
        var optionalToCustomer = customerService.findCustomerById(transferDto.getTo());

        var fromCustomer = optionalFromCustomer.orElseThrow(() -> new Exception("From Customer not found"));
        var toCustomer = optionalToCustomer.orElseThrow(() -> new Exception("To Customer not found"));

        var amount = transferDto.getAmount();

        var isAuthorizedTransaction = transferAuthorizationService.authorizeTransaction();

        var transferStrategy = transferStrategies.get(transferDto.getType().name());
        var transfer = new TransferToUsers(transferStrategy);
        transfer.setFromCustomer(fromCustomer);
        transfer.setToCustomer(toCustomer);
        transfer.setAmount(amount);

        var isValidTransaction = transfer.validateTransaction(isAuthorizedTransaction);

        if (!isValidTransaction) {
            throw new Exception("Invalid transaction");
        }

        transfer.executeTransaction(isAuthorizedTransaction);

        if (transfer.isCompleted()) {
            saveTransfer(transfer, fromCustomer, toCustomer);
        }
    }
}
