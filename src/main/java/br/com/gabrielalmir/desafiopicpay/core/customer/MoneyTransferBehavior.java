package br.com.gabrielalmir.desafiopicpay.core.customer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;

public interface MoneyTransferBehavior {
    boolean canTransferMoney(Customer customer);
    void transferMoney(Customer fromCustomer, Customer toCustomer, BigDecimal amount);
}
