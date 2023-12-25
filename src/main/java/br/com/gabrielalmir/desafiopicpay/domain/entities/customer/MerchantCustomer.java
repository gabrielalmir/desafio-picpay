package br.com.gabrielalmir.desafiopicpay.domain.entities.customer;

public class MerchantCustomer extends CommonCustomer {
    @Override
    public boolean canTransferMoney(Customer customer) {
        return false;
    }
}
