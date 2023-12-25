package br.com.gabrielalmir.desafiopicpay.controller.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielalmir.desafiopicpay.domain.entities.customer.Customer;
import br.com.gabrielalmir.desafiopicpay.presentation.dtos.customer.CreateCustomerDto;
import br.com.gabrielalmir.desafiopicpay.services.customer.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        var customer = customerService.findCustomerById(id);

        return customer
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomers(@RequestBody CreateCustomerDto customer) {
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
