package br.com.gabrielalmir.desafiopicpay.presentation.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.gabrielalmir.desafiopicpay.presentation.enums.TransferType;

public record TransferDto(
    BigDecimal amount,
    UUID from,
    UUID to,
    TransferType type
) {}
