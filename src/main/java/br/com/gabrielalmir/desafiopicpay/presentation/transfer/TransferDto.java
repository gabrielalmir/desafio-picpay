package br.com.gabrielalmir.desafiopicpay.presentation.transfer;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferDto(
    BigDecimal amount,
    UUID from,
    UUID to
) {}
