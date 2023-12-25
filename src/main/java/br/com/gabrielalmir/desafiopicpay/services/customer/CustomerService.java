package br.com.gabrielalmir.desafiopicpay.services.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.gabrielalmir.desafiopicpay.domain.entities.customer.Customer;
import br.com.gabrielalmir.desafiopicpay.presentation.dtos.customer.CreateCustomerDto;
import br.com.gabrielalmir.desafiopicpay.repository.customer.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> upsertCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        var customer = new Customer(createCustomerDto);
        return customerRepository.save(customer);
    }

    public Customer updateCustomerById(Long id, Customer customer) {
        var customerToUpdate = findCustomerById(id);
        customerToUpdate.ifPresent(customerRepository::save);
        return customer;
    }

    public void deleteCustomerById(Long id) {
        var customerToDelete = findCustomerById(id);
        customerToDelete.ifPresent(customer -> {
            customer.setActive(false);
            customerRepository.save(customer);
        });
    }
}
