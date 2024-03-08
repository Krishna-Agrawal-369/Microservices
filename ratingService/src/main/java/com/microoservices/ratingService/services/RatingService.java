package com.microoservices.ratingService.services;

import com.microoservices.ratingService.dto.Rating;
import com.microoservices.ratingService.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    void createRating(Rating rating);

    List<Rating> getAllRatings();

    ResponseEntity<Response> getRatingsByCustomerId(int customerId);

    ResponseEntity<Response> getRatingsByHotelId(int hotelId);

}
