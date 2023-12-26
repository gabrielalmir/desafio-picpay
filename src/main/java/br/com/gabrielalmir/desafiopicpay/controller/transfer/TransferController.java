package br.com.gabrielalmir.desafiopicpay.controller.transfer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielalmir.desafiopicpay.presentation.dtos.transfer.TransferDto;
import br.com.gabrielalmir.desafiopicpay.services.transfer.TransferService;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public void createTransfer(@RequestBody TransferDto transferDto) throws Exception {
        transferService.createTransfer(transferDto);
    }
}
