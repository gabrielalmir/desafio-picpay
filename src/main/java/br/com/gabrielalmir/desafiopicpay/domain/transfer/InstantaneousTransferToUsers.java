package br.com.gabrielalmir.desafiopicpay.domain.transfer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;
import br.com.gabrielalmir.desafiopicpay.domain.transfer.strategy.TransferStrategy;

public class InstantaneousTransferToUsers extends Transfer {
    private TransferStrategy transferStrategy;

    public InstantaneousTransferToUsers(Customer fromCustomer, Customer toCustomer, BigDecimal amount, TransferStrategy transferStrategy) {
        super(fromCustomer, toCustomer, amount);
        this.transferStrategy = transferStrategy;
    }

    @Override
    public boolean validateTransaction(boolean authorized) {
        return transferStrategy.validateTransaction(getFromCustomer(), getToCustomer(), authorized);
    }

    @Override
    public void executeTransaction(boolean authorized) throws Exception {
        transferStrategy.executeTransaction(getFromCustomer(), getToCustomer(), getAmount(), authorized);
        this.setCompleted(true);
    }
}
