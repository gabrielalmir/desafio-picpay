package br.com.gabrielalmir.desafiopicpay.domain.customer.entities;

import br.com.gabrielalmir.desafiopicpay.domain.customer.enums.AccountRole;
import br.com.gabrielalmir.desafiopicpay.domain.customer.interfaces.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "customers")
@Data
public abstract class Customer implements Account {
    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal balance;

    private AccountRole accountRole;
}

