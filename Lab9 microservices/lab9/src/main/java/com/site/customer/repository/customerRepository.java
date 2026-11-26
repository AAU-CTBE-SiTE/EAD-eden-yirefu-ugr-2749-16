package com.site.customer.repository;

import com.site.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface customerRepository extends MongoRepository<Customer, String> {

}

