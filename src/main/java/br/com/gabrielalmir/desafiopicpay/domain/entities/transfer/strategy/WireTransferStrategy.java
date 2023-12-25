package br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.strategy;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.domain.entities.customer.Customer;

public class WireTransferStrategy implements TransferStrategy {

    @Override
    public boolean validateTransaction(Customer fromCustomer, Customer toCustomer, boolean authorized) {
        return fromCustomer.canTransferMoney(toCustomer) && authorized;
    }

    @Override
    public void executeTransaction(Customer fromCustomer, Customer toCustomer, BigDecimal amount, boolean authorized) throws Exception {
        if (!this.validateTransaction(fromCustomer, toCustomer, authorized)) {
            throw new Exception("Invalid transaction");
        }

        fromCustomer.transferMoney(fromCustomer, toCustomer, amount);
    }
}
