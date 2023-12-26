package br.com.gabrielalmir.desafiopicpay.domain.entities.transfer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.core.transfer.Transaction;
import br.com.gabrielalmir.desafiopicpay.domain.entities.customer.Customer;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Transfer implements Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer fromCustomer;

    @ManyToOne
    private Customer toCustomer;
    private BigDecimal amount;
    private boolean isCompleted;
}
