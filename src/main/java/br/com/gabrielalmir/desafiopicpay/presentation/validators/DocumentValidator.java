package br.com.gabrielalmir.desafiopicpay.presentation.validators;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidatorContext;

public class DocumentValidator implements jakarta.validation.ConstraintValidator<BrazilDocument, String> {
    @Override
    public void initialize(BrazilDocument constraint) {
    }

    @Override
    public boolean isValid(String document, ConstraintValidatorContext context) {
        return isCPF(document) || isCNPJ(document);
    }

    private boolean isCPF(String document) {
        var cpf = document.replaceAll("[^0-9]", "");
        return Pattern.matches("\\d{11}", cpf);
    }

    private boolean isCNPJ(String document) {
        var cnpj = document.replaceAll("[^0-9]", "");
        return Pattern.matches("\\d{14}", cnpj);
    }
}
