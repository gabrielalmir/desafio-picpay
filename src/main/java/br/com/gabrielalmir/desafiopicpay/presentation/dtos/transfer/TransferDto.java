package br.com.gabrielalmir.desafiopicpay.presentation.dtos.transfer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.presentation.enums.TransferType;

public record TransferDto(Long from, Long to, BigDecimal amount, TransferType type) {
    public TransferDto(Long from, Long to, BigDecimal amount) {
        this(from, to, amount, TransferType.WIRE_TRANSFER);
    }
}
