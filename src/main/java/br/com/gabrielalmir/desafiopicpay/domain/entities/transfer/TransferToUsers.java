package br.com.gabrielalmir.desafiopicpay.domain.entities.transfer;

import br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.strategy.TransferStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="transfers")
public class TransferToUsers extends Transfer {
    private transient TransferStrategy transferStrategy;

    public TransferToUsers(TransferStrategy transferStrategy) {
        this.transferStrategy = transferStrategy;
    }

    @Override
    public boolean validateTransaction(boolean authorized) {
        return transferStrategy.validateTransaction(getFromCustomer(), getToCustomer(), authorized);
    }

    @Override
    public void executeTransaction(boolean authorized) throws Exception {
        if (getFromCustomer().equals(getToCustomer())) {
            throw new Exception("Invalid transaction: cannot transfer money to yourself");
        }

        transferStrategy.executeTransaction(getFromCustomer(), getToCustomer(), getAmount(), authorized);
        this.setCompleted(true);
    }
}
