package com.microservices.customerService.services;

import com.microservices.customerService.dto.Customer;
import com.microservices.customerService.dto.Hotel;
import com.microservices.customerService.dto.Rating;
import com.microservices.customerService.dto.Response;
import com.microservices.customerService.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public void addCustomer(Customer customer) {
        log.info("Customer Service :: addCustomer()");
        this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        log.info("Customer Service :: getAllCustomers()");
        return this.customerRepository.findAll();
    }

    @Override
    public ResponseEntity<Response> getCustomerById(int id) {
        log.info("Customer Service :: getCustomerById()");
        Optional<Customer> customer = this.customerRepository.findById(id);
        ArrayList ratings = (ArrayList) Objects.requireNonNull(restTemplate.getForObject("http://RATING-SERVICE/rating/getRatingsByCustomerId/" + id, Response.class)).getData();
        customer.ifPresent(value -> value.setRatings(ratings));
        logger.info("Customer with id : " + id + " => {} ", customer.get());
        return customer.map(value -> new ResponseEntity<>(new Response(true, 0, "Customer details fetched successfully with given customer ID : " + id, value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Response(false, -1, "Customer not found"), HttpStatus.BAD_REQUEST));
    }
}
