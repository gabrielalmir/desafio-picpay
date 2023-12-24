package br.com.gabrielalmir.desafiopicpay.domain.transfer;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.gabrielalmir.desafiopicpay.core.contract.Transaction;
import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity(name="transfers")
@Table(name="transfers")
@Data
public abstract class Transfer implements Transaction {
    @Id
    @GeneratedValue
    private UUID id;

    private Customer fromCustomer;
    private Customer toCustomer;
    private BigDecimal amount;

    private boolean isCompleted;

    public Transfer(Customer fromCustomer, Customer toCustomer, BigDecimal amount) {
        this.fromCustomer = fromCustomer;
        this.toCustomer = toCustomer;
        this.amount = amount;
    }
}
