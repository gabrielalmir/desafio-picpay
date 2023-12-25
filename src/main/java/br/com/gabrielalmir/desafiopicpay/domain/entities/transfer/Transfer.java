package br.com.gabrielalmir.desafiopicpay.domain.entities.transfer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.core.transfer.Transaction;
import br.com.gabrielalmir.desafiopicpay.domain.entities.customer.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="transfers")
@Data
public abstract class Transfer implements Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Customer fromCustomer;
    @ManyToOne(optional = false)
    private Customer toCustomer;
    private BigDecimal amount;

    private boolean isCompleted;
}
