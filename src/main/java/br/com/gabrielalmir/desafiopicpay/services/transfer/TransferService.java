package br.com.gabrielalmir.desafiopicpay.services.transfer;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gabrielalmir.desafiopicpay.domain.transfer.InstantaneousTransferToUsers;
import br.com.gabrielalmir.desafiopicpay.domain.transfer.strategy.TransferStrategy;
import br.com.gabrielalmir.desafiopicpay.presentation.transfer.TransferDto;
import br.com.gabrielalmir.desafiopicpay.repository.transfer.TransferRepository;
import br.com.gabrielalmir.desafiopicpay.services.customer.CustomerService;

@Service
public class TransferService {
    private final CustomerService customerService;
    private final TransferRepository transferRepository;
    private final TransferStrategy transferStrategy;

    public TransferService(CustomerService customerService, TransferRepository transferRepository, TransferStrategy transferStrategy) {
        this.customerService = customerService;
        this.transferRepository = transferRepository;
        this.transferStrategy = transferStrategy;
    }

    public void transfer(TransferDto transferDto) throws Exception {
        var fromCustomer = customerService.findCustomerById(transferDto.from());
        var toCustomer = customerService.findCustomerById(transferDto.to());
        var amount = transferDto.amount();

        var isAuthorizedTransaction = authorizeTransaction();

        var transfer = new InstantaneousTransferToUsers(fromCustomer, toCustomer, amount, transferStrategy);
        var isValidTransaction = transfer.validateTransaction(isAuthorizedTransaction);

        if (!isValidTransaction) {
            throw new Exception("Invalid transaction");
        }

        transfer.executeTransaction(isAuthorizedTransaction);
        transferRepository.save(transfer);

        customerService.updateCustomers(List.of(fromCustomer, toCustomer));
    }

    public boolean authorizeTransaction() throws Exception {
        return true;
    }
}
