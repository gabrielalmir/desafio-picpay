package br.com.gabrielalmir.desafiopicpay.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;
import br.com.gabrielalmir.desafiopicpay.presentation.dtos.CreateCustomerDto;
import br.com.gabrielalmir.desafiopicpay.services.customer.CustomerService;


@Controller("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping(":id")
    public ResponseEntity<Customer> getCustomerById(Long id) {
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomers(CreateCustomerDto customer) {
        var createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping(":id")
    public ResponseEntity<Customer> updateCustomerById(Long id, Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomerById(id, customer));
    }

    @PostMapping(":id")
    public ResponseEntity<Void> deleteCustomerById(Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
