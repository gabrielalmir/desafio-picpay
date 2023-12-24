package br.com.gabrielalmir.desafiopicpay.presentation.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.gabrielalmir.desafiopicpay.presentation.enums.TransferType;

public record TransferDto(UUID from, UUID to, BigDecimal amount, TransferType type) {
    public TransferDto(UUID from, UUID to, BigDecimal amount) {
        this(from, to, amount, TransferType.WIRE_TRANSFER);
    }
}
