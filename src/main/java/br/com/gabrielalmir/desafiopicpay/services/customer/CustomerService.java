package br.com.gabrielalmir.desafiopicpay.services.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gabrielalmir.desafiopicpay.domain.customer.Customer;
import br.com.gabrielalmir.desafiopicpay.presentation.dtos.CreateCustomerDto;
import br.com.gabrielalmir.desafiopicpay.repository.customer.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerById(Long id) {
        return customerRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Customer %s not found".formatted(id)));
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> upsertCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        var customer = createCustomerDto.customerType().createCustomer();
        return customerRepository.save(customer);
    }

    public Customer updateCustomerById(Long id, Customer customer) {
        var customerToUpdate = findCustomerById(id);
        customerToUpdate.setActive(customer.isActive());
        return customerRepository.save(customerToUpdate);
    }

    public void deleteCustomerById(Long id) {
        var customer = findCustomerById(id);
        customer.setActive(false);
        customerRepository.save(customer);
    }
}
