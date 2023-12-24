package br.com.gabrielalmir.desafiopicpay.domain.customer;

import java.math.BigDecimal;

public class CommonCustomer extends Customer {
    @Override
    public boolean canTransferMoney(Customer customer) {
        return true;
    }

    @Override
    public void transferMoney(Customer fromCustomer, Customer toCustomer, BigDecimal amount) {
        var balanceAfterTransfer = fromCustomer.getBalance().subtract(amount);

        if (!this.canTransferMoney(fromCustomer)) {
            throw new RuntimeException("Common customer cannot transfer money");
        } else if (balanceAfterTransfer.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        fromCustomer.setBalance(balanceAfterTransfer);
        toCustomer.setBalance(toCustomer.getBalance().add(amount));
    }
}
