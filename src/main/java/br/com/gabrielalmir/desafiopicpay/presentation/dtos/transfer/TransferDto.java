package br.com.gabrielalmir.desafiopicpay.presentation.dtos.transfer;

import java.math.BigDecimal;

import br.com.gabrielalmir.desafiopicpay.presentation.enums.TransferType;
import lombok.Getter;

@Getter
public class TransferDto {
    private Long from;
    private Long to;
    private BigDecimal amount;
    private TransferType type = TransferType.WIRE_TRANSFER;

    public TransferDto(Long from, Long to, BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
