package com.site.customer.controller;

import com.site.customer.model.Customer;
import com.site.customer.repository.CustomerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // CREATE CUSTOMER
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // GET ALL CUSTOMERS
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // GET CUSTOMER BY ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {

        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"));
    }

    // UPDATE CUSTOMER
    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable String id,
            @RequestBody Customer customer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"));

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    // DELETE CUSTOMER
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String id) {

        if (!customerRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer not found");
        }

        customerRepository.deleteById(id);
    }
}