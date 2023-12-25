package br.com.gabrielalmir.desafiopicpay.domain.entities.transfer;

import br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.strategy.TransferStrategy;

public class TransferToUsers extends Transfer {
    private TransferStrategy transferStrategy;

    public TransferToUsers(TransferStrategy transferStrategy) {
        this.transferStrategy = transferStrategy;
    }

    @Override
    public boolean validateTransaction(boolean authorized) {
        return transferStrategy.validateTransaction(getFromCustomer(), getToCustomer(), authorized);
    }

    @Override
    public void executeTransaction(boolean authorized) throws Exception {
        transferStrategy.executeTransaction(getFromCustomer(), getToCustomer(), getAmount(), authorized);
        this.setCompleted(true);
    }
}