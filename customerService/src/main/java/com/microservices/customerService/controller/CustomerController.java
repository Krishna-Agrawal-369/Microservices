package com.microservices.customerService.controller;

import com.microservices.customerService.dto.Customer;
import com.microservices.customerService.dto.Response;
import com.microservices.customerService.services.CustomerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private int retryCount = 1;

    @GetMapping("getAllCustomers")
    public ResponseEntity<Response> getAllCustomers() {
        log.info("Customer Controller :: getAllCustomers()");
        return new ResponseEntity<>(new Response(true, 0, "Customers details fetched successfully", this.customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("getCustomerById/{id}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Response> getCustomerById(@PathVariable int id) {
        log.info("Customer Controller :: getCustomerById()");
        log.info("Retry Count : " + retryCount);
        retryCount++;
        return this.customerService.getCustomerById(id);
    }

    public ResponseEntity<Response> ratingHotelFallback(Exception ex) {
        log.info("Fallback is executed because service is down : " + ex.getMessage());
        Customer customer = Customer.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").build();
        return new ResponseEntity<>(new Response(false,-1,"Some service is down", customer), HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/save/customer")
    public ResponseEntity<Response> saveUser(@RequestBody Customer customer) {
        log.info("Customer Controller :: saveCustomer()");
        this.customerService.addCustomer(customer);
        return new ResponseEntity<>(new Response(true, 0, "Customer added successfully"),HttpStatus.OK);
    }
}
