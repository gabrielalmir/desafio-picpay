package br.com.gabrielalmir.desafiopicpay.repository.customer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
