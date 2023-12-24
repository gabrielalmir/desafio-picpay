package br.com.gabrielalmir.desafiopicpay.core.contract;

import java.math.BigDecimal;
import java.util.UUID;

public interface Account {
    UUID getId();
    String getFirstName();
    String getLastName();
    String getDocument();
    String getEmail();
    String getPassword();
    BigDecimal getBalance();
    boolean canSendMoney();
    boolean canReceiveMoney();
    void sendMoney(Account toAccount, BigDecimal amount) throws Exception;
    void receiveMoney(Account fromAccount, BigDecimal amount) throws Exception;
}
