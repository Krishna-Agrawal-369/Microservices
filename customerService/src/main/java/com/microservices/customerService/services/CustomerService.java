package com.microservices.customerService.services;

import com.microservices.customerService.dto.Customer;
import com.microservices.customerService.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    void addCustomer(Customer user);
    List<Customer> getAllCustomers();
    ResponseEntity<Response> getCustomerById(int id);

}
