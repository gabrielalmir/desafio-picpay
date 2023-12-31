package br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.strategy;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.domain.entities.customer.Customer;

public interface TransferStrategy {
    boolean validateTransaction(Customer fromCustomer, Customer toCustomer, boolean authorized);
    void executeTransaction(Customer fromCustomer, Customer toCustomer, BigDecimal amount, boolean authorized) throws Exception;
}
