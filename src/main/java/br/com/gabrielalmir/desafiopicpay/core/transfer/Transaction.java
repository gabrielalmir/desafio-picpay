package br.com.gabrielalmir.desafiopicpay.core.transfer;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;

public interface Transaction {
    UUID getId();
    Customer getFromCustomer();
    Customer getToCustomer();
    BigDecimal getAmount();
    boolean isCompleted();
    boolean validateTransaction(boolean authorized);
    void executeTransaction(boolean authorized) throws Exception;
}
