package br.com.gabrielalmir.desafiopicpay.domain.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.gabrielalmir.desafiopicpay.core.customer.Account;
import br.com.gabrielalmir.desafiopicpay.core.customer.MoneyTransferBehavior;

@Entity(name = "customers")
@Table(name = "customers")
@Data
public abstract class Customer implements Account, MoneyTransferBehavior {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 20)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(unique = true, length = 20)
    private String document;

    @Column(unique = true, length = 50)
    private String email;

    private String password;
    private BigDecimal balance;

    private MoneyTransferBehavior moneyTransferBehavior;
}

