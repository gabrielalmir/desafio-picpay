package br.com.gabrielalmir.desafiopicpay.domain.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.gabrielalmir.desafiopicpay.core.contract.Account;

@Entity(name = "customers")
@Data
public abstract class Customer implements Account {
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
}

