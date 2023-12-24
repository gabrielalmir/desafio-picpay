package br.com.gabrielalmir.desafiopicpay.repository.transfer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielalmir.desafiopicpay.domain.transfer.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {

}