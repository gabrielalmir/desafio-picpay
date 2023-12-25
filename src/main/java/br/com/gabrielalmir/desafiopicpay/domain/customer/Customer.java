package br.com.gabrielalmir.desafiopicpay.domain.customer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.core.customer.MoneyTransferBehavior;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "customers")
@Data
public abstract class Customer implements MoneyTransferBehavior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(unique = true, length = 20)
    private String document;

    @Column(unique = true, length = 50)
    private String email;

    private String password;

    @Column(precision = 19, scale = 2)
    private BigDecimal balance;

    private boolean active;
}

