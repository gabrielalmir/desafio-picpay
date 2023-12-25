package br.com.gabrielalmir.desafiopicpay.domain.entities.customer;

public class CommonCustomer extends Customer {
    @Override
    public boolean canTransferMoney(Customer customer) {
        return this.isActive();
    }
}
