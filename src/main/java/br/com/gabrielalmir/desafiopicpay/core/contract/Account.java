package br.com.gabrielalmir.desafiopicpay.core.contract;

import java.math.BigDecimal;

public interface Account {
    boolean canSendMoney();
    boolean canReceiveMoney();
    void sendMoney(Account toAccount, BigDecimal amount) throws Exception;
    void receiveMoney(Account fromAccount, BigDecimal amount) throws Exception;
}
