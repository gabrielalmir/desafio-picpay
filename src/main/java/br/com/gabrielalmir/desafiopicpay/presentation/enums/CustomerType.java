package br.com.gabrielalmir.desafiopicpay.presentation.enums;

import br.com.gabrielalmir.desafiopicpay.domain.customer.CommonCustomer;
import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;
import br.com.gabrielalmir.desafiopicpay.domain.customer.MerchantCustomer;

public enum CustomerType {
    COMMON,
    MERCHANT;

    public Customer createCustomer() {
        return switch (this) {
            case COMMON -> new CommonCustomer();
            case MERCHANT -> new MerchantCustomer();
            default -> new CommonCustomer();
        };
    }
}
