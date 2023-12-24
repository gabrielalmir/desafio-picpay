package br.com.gabrielalmir.desafiopicpay.domain.customer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.core.contract.Account;

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
