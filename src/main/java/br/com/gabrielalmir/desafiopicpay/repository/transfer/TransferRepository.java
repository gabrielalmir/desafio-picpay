package br.com.gabrielalmir.desafiopicpay.repository.transfer;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielalmir.desafiopicpay.domain.entities.transfer.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
