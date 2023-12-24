package br.com.gabrielalmir.desafiopicpay.domain.customer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.core.customer.Account;

public class CommonCustomer extends Customer {
    @Override
    public boolean canSendMoney() {
        return true;
    }

    @Override
    public boolean canReceiveMoney() {
        return true;
    }

    @Override
    public void sendMoney(Account toAccount, BigDecimal amount) {
        var balanceAfterTransfer = this.getBalance().subtract(amount);

        if (!this.canSendMoney()) {
            throw new RuntimeException("Common customer cannot send money");
        } else if (balanceAfterTransfer.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        this.setBalance(balanceAfterTransfer);
    }

    @Override
    public void receiveMoney(Account fromAccount, BigDecimal amount) {
        var balanceAfterTransfer = this.getBalance().add(amount);

        if (!this.canReceiveMoney()) {
            throw new RuntimeException("Common customer cannot receive money");
        }

        this.setBalance(balanceAfterTransfer);
    }
}
