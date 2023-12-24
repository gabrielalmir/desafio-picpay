package br.com.gabrielalmir.desafiopicpay.domain.transfer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;

public class InstantaneousTransferToUsers extends Transfer {
    public InstantaneousTransferToUsers(Customer fromCustomer, Customer toCustomer, BigDecimal amount) {
        super(fromCustomer, toCustomer, amount);
    }

    @Override
    public boolean validateTransaction(boolean authorized) {
        return this.getFromCustomer().canSendMoney() && this.getToCustomer().canReceiveMoney() && authorized;
    }

    @Override
    public void executeTransaction(boolean authorized) throws Exception {
        var fromCustomer = this.getFromCustomer();
        var toCustomer = this.getToCustomer();

        if (!this.validateTransaction(authorized)) {
            throw new RuntimeException("Invalid transaction");
        }

        fromCustomer.sendMoney(getFromCustomer(), getAmount());
        toCustomer.receiveMoney(getToCustomer(), getAmount());

        this.setCompleted(true);
    }
}
