package br.com.gabrielalmir.desafiopicpay.core.customer;

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
}
