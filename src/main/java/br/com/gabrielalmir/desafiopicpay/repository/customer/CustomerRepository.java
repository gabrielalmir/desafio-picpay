package br.com.gabrielalmir.desafiopicpay.repository.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielalmir.desafiopicpay.domain.entities.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByDocument(String document);
}
