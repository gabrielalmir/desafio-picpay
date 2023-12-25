package br.com.gabrielalmir.desafiopicpay.domain.entities.customer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.core.customer.MoneyTransferBehavior;
import br.com.gabrielalmir.desafiopicpay.presentation.dtos.customer.CreateCustomerDto;
import br.com.gabrielalmir.desafiopicpay.presentation.enums.CustomerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer implements MoneyTransferBehavior {
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

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    public Customer(CreateCustomerDto dto) {
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.document = dto.document();
        this.email = dto.email();
        this.password = dto.password();
        this.customerType = dto.customerType();
        this.balance = BigDecimal.ZERO;
        this.active = true;
    }

    @Override
    public boolean canTransferMoney(Customer customer) {
        return this.isActive();
    }

    @Override
    public void transferMoney(Customer fromCustomer, Customer toCustomer, BigDecimal amount) {
        var balanceAfterTransfer = fromCustomer.getBalance().subtract(amount);

        if (!this.canTransferMoney(fromCustomer)) {
            throw new RuntimeException("Common customer cannot transfer money");
        } else if (balanceAfterTransfer.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        fromCustomer.setBalance(balanceAfterTransfer);
        toCustomer.setBalance(toCustomer.getBalance().add(amount));
    }
}

