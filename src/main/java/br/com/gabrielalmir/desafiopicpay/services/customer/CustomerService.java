package br.com.gabrielalmir.desafiopicpay.services.customer;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;
import br.com.gabrielalmir.desafiopicpay.repository.customer.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerById(UUID id) {
        return customerRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Customer %s not found".formatted(id)));
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> saveCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    public void deleteCustomerById(UUID id) {
        var customer = findCustomerById(id);
        customer.setActive(false);
        customerRepository.save(customer);
    }
}
