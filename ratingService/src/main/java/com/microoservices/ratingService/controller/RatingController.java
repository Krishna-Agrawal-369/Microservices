package com.microoservices.ratingService.controller;

import com.microoservices.ratingService.dto.Rating;
import com.microoservices.ratingService.dto.Response;
import com.microoservices.ratingService.services.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/getAllRatings")
    public ResponseEntity<Response> getAllRatings() {
        log.info("Rating Controller :: getAllRatings()");
        return new ResponseEntity<>(new Response(true,0,"Rating details fetched successfully", this.ratingService.getAllRatings()), HttpStatus.OK);
    }

    @PostMapping("/create/rating")
    public ResponseEntity<Response> createRating(@RequestBody Rating rating) {
        log.info("Rating Controller :: createRating()");
        this.ratingService.createRating(rating);
        return new ResponseEntity<>(new Response(true, 0, "Rating added successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/getRatingsByCustomerId/{id}")
    public ResponseEntity<Response> getRatingsByCustomerId(@PathVariable("id") int customerId) {
        log.info("Rating Controller :: getRatingsByCustomerId()");
        return this.ratingService.getRatingsByCustomerId(customerId);
    }

    @GetMapping("/getRatingsByHotelId/{id}")
    public ResponseEntity<Response> getRatingsByHotelId(@PathVariable("id") int hotelId) {
        log.info("Rating Controller :: getRatingsByCustomerId()");
        return this.ratingService.getRatingsByHotelId(hotelId);
    }

}
