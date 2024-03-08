package com.microoservices.ratingService.services;

import com.microoservices.ratingService.dto.Rating;
import com.microoservices.ratingService.dto.Response;
import com.microoservices.ratingService.repository.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public void createRating(Rating rating) {
        log.info("Rating Service :: createRating()");
        this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        log.info("Rating Service :: getAllRatings()");
        return this.ratingRepository.findAll();
    }

    @Override
    public ResponseEntity<Response> getRatingsByCustomerId(int customerId) {
        log.info("Rating Service :: getRatingsByCustomerId()");
        List<Rating> ratingList = this.ratingRepository.findByCustomerId(customerId);
        return !ratingList.isEmpty() ? new ResponseEntity<>(new Response(true, 0, "Rating details fetched successfully with given customer ID : " + customerId, ratingList), HttpStatus.OK) : new ResponseEntity<>(new Response(false, -1, "Rating not found"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Response> getRatingsByHotelId(int hotelId) {
        log.info("Rating Service :: getRatingsByHotelId()");
        List<Rating> ratingList = this.ratingRepository.findByHotelId(hotelId);
        return !ratingList.isEmpty() ? new ResponseEntity<>(new Response(true, 0, "Rating details fetched successfully with given hotel ID : " + hotelId, ratingList), HttpStatus.OK) : new ResponseEntity<>(new Response(false, -1, "Rating not found"), HttpStatus.BAD_REQUEST);
    }
}
