package br.com.gabrielalmir.desafiopicpay.presentation.dtos.customer;

import br.com.gabrielalmir.desafiopicpay.presentation.enums.CustomerType;
import br.com.gabrielalmir.desafiopicpay.presentation.validators.BrazilDocument;
import jakarta.validation.constraints.Email;

public record CreateCustomerDto (
    String firstName,
    String lastName,
    @BrazilDocument
    String document,
    @Email
    String email,
    String password,
    CustomerType customerType
) {
    public CreateCustomerDto {
        if (customerType == null) {
            customerType = CustomerType.COMMON;
        }
    }
}
