package br.com.gabrielalmir.desafiopicpay.domain.customer.entities;

import br.com.gabrielalmir.desafiopicpay.domain.customer.interfaces.Account;

import java.math.BigDecimal;

public class MerchantCustomer extends CommonCustomer {
    @Override
    public boolean canSendMoney() {
        return false;
    }

    @Override
    public void sendMoney(Account toAccount, BigDecimal amount) {
        throw new RuntimeException("Merchant customer cannot transform between accounts");
    }
}
